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

  @Override
  public int insert(OrderModel orderModel) throws SQLException {
    int result = 0;
    try (
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(
            "INSERT INTO `order` (`shippingInformation`, `orderDate`, `invoiceID`, `ISBN`, `userID`) VALUES (?,?,?,?,?)",
            Statement.RETURN_GENERATED_KEYS // important to retrieve auto-generated key
        )) {
      pstmt.setString(1, orderModel.getShippingInformation());
      pstmt.setDate(2, orderModel.getOrderDate());
      pstmt.setString(3, orderModel.getInvoiceID());
      pstmt.setString(4, orderModel.getISBN());
      pstmt.setString(5, orderModel.getUserID());
      result = pstmt.executeUpdate();

      // Retrieve auto-generated key and set it in the orderModel instance.
      ResultSet generatedKeys = pstmt.getGeneratedKeys();
      if (generatedKeys.next()) {
        orderModel.setOrderID(generatedKeys.getString(1));
      }
    } catch (SQLException e) {
      throw e;
    }
    return result;
  }

  @Override
  public int update(OrderModel orderModel) throws SQLException {
    int result = 0;
    try (
        Connection conn = DatabaseConnect.getConnection(); // Establish connection with Database
        PreparedStatement pstmt = conn.prepareStatement(
            "UPDATE `order` SET `shippingInformation`=?, `orderDate`=?, `invoiceID`=?, `ISBN`=?, `userID`=? WHERE `id`=?");) {
      pstmt.setString(1, orderModel.getShippingInformation()); // Setting the value of parameters in the query
      pstmt.setDate(2, orderModel.getOrderDate());
      pstmt.setString(3, orderModel.getInvoiceID());
      pstmt.setString(4, orderModel.getISBN());
      pstmt.setString(5, orderModel.getUserID());
      pstmt.setString(6, orderModel.getOrderID());
      result = pstmt.executeUpdate(); // Return number of rows updated
    } catch (SQLException e) {
      throw e;
    }
    return result;
  }

  @Override
  public int delete(String orderID) throws SQLException {
    try (
        Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM `order` WHERE `orderID`=?")) {
      pst.setString(1, orderID);
      return pst.executeUpdate();
    }
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
        orderModel.setUserID(resultSet.getString("userID"));
        orderModel.setInvoiceID(resultSet.getString("invoice_id"));
        orderModel.setOrderDate(resultSet.getDate("orderDate"));
        orderModel.setISBN(resultSet.getString("ISBN"));
        orderModel.setOrderID(resultSet.getString("orderID"));
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
