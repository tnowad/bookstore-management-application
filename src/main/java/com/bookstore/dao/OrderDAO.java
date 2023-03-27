package com.bookstore.dao;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.OrderModel;
import com.bookstore.model.OrderModel.Status;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
        rs.getInt("cart_id"),
        rs.getInt("customer_id"),
        rs.getInt("employee_id"),
        rs.getInt("total"),
        rs.getInt("paid"),
        rs.getTimestamp("created_at"),
        rs.getTimestamp("updated_at"),
        OrderModel.Status.valueOf(rs.getString("status")));
  }

  @Override
  public ArrayList<OrderModel> readDatabase() throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM orders";
    try (ResultSet rs = DatabaseConnection.executeQuery(query)) {
      ArrayList<OrderModel> orderList = new ArrayList<>();
      while (rs.next()) {
        OrderModel orderModel = createOrderModelFromResultSet(rs);
        orderList.add(orderModel);
      }
      if (orderList.isEmpty()) {
        System.out.println("No records found!");
      }
      return orderList;
    }
  }

  @Override
  public int insert(OrderModel order) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO orders (cart_id, customer_id, employee_id, total, paid, status)"
        + "VALUES (?, ?, ?, ?, ?, ?)";
    Object[] args = { order.getCartId(), order.getCustomerId(), order.getEmployeeId(), order.getTotal(),
        order.getPaid(), order.getStatus().name() };
    return DatabaseConnection.executeUpdate(insertSql, args);
  }

  @Override
  public int update(OrderModel order) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE orders SET cart_id=?, customer_id=?, employee_id=?, total=?, paid=?, status=? WHERE id=?";
    Object[] args = { order.getCartId(), order.getCustomerId(), order.getEmployeeId(), order.getTotal(),
        order.getPaid(), order.getStatus().name(), order.getId() };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  public int updateStatus(int cartId, Status status) {
    String updateSql = "UPDATE orders SET status = ? WHERE cart_id = ?";
    Object[] args = { status, cartId };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM orders WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnection.executeUpdate(deleteSql, args);
  }

  @Override
  public List<OrderModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {

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
    }
  }

}
