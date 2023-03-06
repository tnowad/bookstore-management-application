package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.OrderModel;

public class OrderDAO implements DAOInterface<OrderModel> {

  public static OrderDAO getInstance() {
    return new OrderDAO();
  }

  private OrderModel createOrderModelFromResultSet(ResultSet rs) throws SQLException {
    return new OrderModel(
        rs.getString("shippingInformation"),
        rs.getDate("orderDate"),
        rs.getString("invoiceID"),
        rs.getString("ISBN"),
        rs.getString("userID"),
        rs.getString("orderID"));
  }

  @Override
  public ArrayList<OrderModel> readDatabase() throws SQLException {
    ArrayList<OrderModel> orderList = new ArrayList<>();
    try (
        Connection con = DatabaseConnect.getConnection(); // Established connection with Database
        PreparedStatement pst = con.prepareStatement("SELECT * FROM `Order`"); // SQL Statement to execute
        ResultSet rs = pst.executeQuery() // Executing the SQL Statement
    ) {
      while (rs.next()) {
        OrderModel orderModel = createOrderModelFromResultSet(rs); // Creating UserModel object from ResultSet
        orderList.add(orderModel); // Adding OrderModel object into ArrayList
      }
    } catch (SQLException e) {
      throw e;
    }
    return orderList; // Returning ArrayList of OrderModel objects
  }

  public int insert(OrderModel order) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO `orders` (`shipping_information`, `order_date`, `invoice_id`, `ISBN`, `user_id`) VALUES (?, ?, ?, ?, ?)";
    Object[] args = { order.getShippingInformation(), order.getOrderDate(), order.getInvoiceId(), order.getISBN(),
        order.getUserId() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  public int update(OrderModel order) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE `orders` SET `shipping_information` = ?, `order_date` = ?, `invoice_id` = ?, `ISBN` = ?, `user_id` = ? WHERE `order_id` = ?";
    Object[] args = { order.getShippingInformation(), order.getOrderDate(), order.getInvoiceId(), order.getISBN(),
        order.getUserId(), order.getOrderId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  public int delete(String orderId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM `orders` WHERE `order_id` = ?";
    Object[] args = { orderId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<OrderModel> searchByCondition(String condition) throws SQLException {
    // Build the SQL query with passed condition for searching order data
    String query = "SELECT userID, orderID, shippingInformation, orderDate, invoiceID, ISBN FROM `order` WHERE "
        + condition;
    try (Connection con = DatabaseConnect.getConnection(); // Get a database connection
        PreparedStatement pst = con.prepareStatement(query)) { // Prepare the SQL statement with the built query
      // Execute the SQL statement and get result set
      ResultSet resultSet = pst.executeQuery();
      // Create an ArrayList of orders to hold the retrieved ones
      ArrayList<OrderModel> orderModels = new ArrayList<>();
      // Loop through result set and retrieve order data into OrderModel class
      while (resultSet.next()) {
        OrderModel orderModel = createOrderModelFromResultSet(resultSet);
        orderModels.add(orderModel);
      }
      // Print a message if no records are found for the given search criteria
      if (orderModels.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      // Return the ArrayList of orders that meet the search criteria
      return orderModels;
    } catch (SQLException e) {
      throw new SQLException("Failed to search orders by condition: " + condition, e);
    }
  }

  @Override
  public List<OrderModel> searchByCondition(String condition, String columnName) throws SQLException {
    // Build the SQL query with given condition and column name to search only for
    // order Data
    String query = "SELECT * FROM `order` WHERE " + columnName + " LIKE ?";
    try (
        // Get a database connection
        Connection con = DatabaseConnect.getConnection();
        // Prepare the SQL statement with the built query
        PreparedStatement pst = con.prepareStatement(query);) {
      // Set wildcarded value to the prepared statement
      pst.setString(1, "%" + condition + "%");
      // Execute the SQL statement and get result set
      ResultSet resultSet = pst.executeQuery();
      // Create an ArrayList of customers to hold the retrieved ones
      ArrayList<OrderModel> orderList = new ArrayList<>();
      // Loop through result set and retrieve customer data into OrderModel class
      while (resultSet.next()) {
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(resultSet.getString("userID"));
        orderModel.setInvoiceId(resultSet.getString("invoice_id"));
        orderModel.setOrderDate(resultSet.getDate("orderDate"));
        orderModel.setISBN(resultSet.getString("ISBN"));
        orderModel.setOrderId(resultSet.getString("orderID"));
        orderModel.setShippingInformation(resultSet.getString("shippingInformation"));
        orderList.add(orderModel);
      }
      // Return the ArrayList of orders that meet the search criteria
      return orderList;
    } catch (SQLException e) {
      throw e;
    }
  }

}
