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

  

}
