package com.bookstore.dao;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.OrderModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class OrderDAO implements IDAO<OrderModel> {
  private static OrderDAO instance;

  public static OrderDAO getInstance() {
    if (instance == null) {
      instance = new OrderDAO();
    }
    return instance;
  }

  private OrderModel createOrderModelFromResultSet(ResultSet rs) throws SQLException {
    return new OrderModel(
        rs.getInt("id"),
        rs.getInt("cart_id"),
        rs.getInt("customer_id"),
        rs.getInt("employee_id"),
        rs.getInt("total"),
        rs.getInt("paid"),
        rs.getDate("created_at"),
        rs.getDate("updated_at"),
        OrderModel.Status.valueOf(rs.getString("status").toUpperCase()));
  }

  @Override
  public ArrayList<OrderModel> readDatabase() {
    ArrayList<OrderModel> orderList = new ArrayList<>();
    String query = "SELECT * FROM orders";
    try (ResultSet rs = DatabaseConnection.executeQuery(query)) {
      while (rs.next()) {
        OrderModel orderModel = createOrderModelFromResultSet(rs);
        orderList.add(orderModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return orderList;
  }

  @Override
  public int insert(OrderModel order) {
    String insertSql = "INSERT INTO orders (cart_id, customer_id, employee_id, total, paid, status)"
        + "VALUES (?, ?, ?, ?, ?, ?)";
    Object[] args = { order.getCartId(), order.getCustomerId(), order.getEmployeeId(), order.getTotal(),
        order.getPaid(), order.getStatus().name() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(OrderModel order) {
    String updateSql = "UPDATE orders SET cart_id=?, customer_id=?, employee_id=?, total=?, paid=?, updated_at = CURRENT_TIMESTAMP, status=? WHERE id=?";
    Object[] args = { order.getCartId(), order.getCustomerId(), order.getEmployeeId(), order.getTotal(),
        order.getPaid(), order.getStatus().name(), order.getId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateStatus(int cartId, String status) {
    String updateSql = "UPDATE orders SET status = ? WHERE cart_id = ?";
    Object[] args = { status, cartId };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM orders WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<OrderModel> search(String condition, String[] columnNames) {

    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM orders WHERE CONCAT(cart_id, customer_id, employee_id, total, paid, status, created_at, updated_at) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in orders table
      String column = columnNames[0];
      query = "SELECT * FROM orders WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in orders table
      query = "SELECT cart_id, customer_id, employee_id, total, paid, status, created_at, updated_at FROM orders WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }
    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<OrderModel> orderList = new ArrayList<>();
        while (rs.next()) {
          OrderModel orderModel = createOrderModelFromResultSet(rs);
          orderList.add(orderModel);
        }
        if (orderList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return orderList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}
