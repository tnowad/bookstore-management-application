package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.OrderModel;

public class OrderDAO implements DAOInterface<OrderModel> {

  public static OrderDAO getInstance() {
    return new OrderDAO();
  }

  private OrderModel createOrderFromResultSet(ResultSet rs) throws SQLException {
    OrderModel orderModel = new OrderModel();
    orderModel.setOrderId(rs.getString("order_id"));
    orderModel.setUserId(rs.getString("user_id"));
    orderModel.setISBN(rs.getString("ISBN"));
    orderModel.setInvoiceId(rs.getString("invoice_id"));
    orderModel.setOrderDate(rs.getDate("order_date"));
    orderModel.setShippingInformation(rs.getString("shipping_information"));
    return orderModel;
  }

  public ArrayList<OrderModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<OrderModel> orderList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM `orders`")) {
      while (rs.next()) {
        OrderModel order = createOrderFromResultSet(rs);
        orderList.add(order);
      }
    }
    return orderList;
  }

  public int insert(OrderModel order) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO `orders` (`shipping_information`, `order_date`, `invoice_id`, `ISBN`, `user_id`, `order_id`) VALUES (?, ?, ?, ?, ?, ?)";
    Object[] args = { order.getShippingInformation(), order.getOrderDate(), order.getInvoiceId(), order.getISBN(),
        order.getUserId(), order.getOrderId() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  public int update(OrderModel order) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE `orders` SET `shipping_information`=?, `order_date`=?, `invoice_id`=?, `ISBN`=?, `user_id`=? WHERE `order_id`=?";
    Object[] args = { order.getShippingInformation(), order.getOrderDate(), order.getInvoiceId(), order.getISBN(),
        order.getUserId(), order.getOrderId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  public int delete(String orderId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM `orders` WHERE `order_id`=?";
    Object[] args = { orderId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  public List<OrderModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM `orders`";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<OrderModel> orderList = new ArrayList<>();
      while (rs.next()) {
        OrderModel order = createOrderFromResultSet(rs);
        orderList.add(order);
      }
      if (orderList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return orderList;
    } catch (SQLException e) {
      throw e;
    }
  }

  public List<OrderModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM orders WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<OrderModel> orderList = new ArrayList<>();
        while (rs.next()) {
          OrderModel order = createOrderFromResultSet(rs);
          orderList.add(order);
        }
        if (orderList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return orderList;
      }
    }
  }
}
