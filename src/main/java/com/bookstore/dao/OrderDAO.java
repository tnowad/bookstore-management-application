package com.bookstore.dao;

import com.bookstore.model.OrderModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO implements DAOInterface<OrderModel> {

  public static OrderDAO getInstance() {
    return new OrderDAO();
  }

  private OrderModel createOrderModelFromResultSet(ResultSet rs) throws SQLException {
    return new OrderModel(
        rs.getInt("cart_id"),
        rs.getInt("customer_id"),
        rs.getInt("employee_id"),
        rs.getInt("total"),
        rs.getInt("paid"),
        rs.getTimestamp("created_at").toLocalDateTime(),
        rs.getTimestamp("updated_at").toLocalDateTime(),
        OrderModel.Status.valueOf(rs.getString("status")));
  }

  @Override
  public ArrayList<OrderModel> readDatabase() throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM orders";
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
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
    String insertSql = "INSERT INTO orders (cart_id, customer_id, employee_id, total, paid, created_at, updated_at, status)"
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    Object[] args = { order.getCart_id(), order.getCustomer_id(), order.getEmployee_id(), order.getTotal(),
        order.getPaid(), Timestamp.valueOf(order.getCreated_at()), Timestamp.valueOf(order.getUpdated_at()),
        order.getStatus().name() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(OrderModel order) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE orders SET cart_id=?, customer_id=?, employee_id=?, total=?, paid=?, created_at=?, "
        + "updated_at=?, status=? WHERE id=?";
    Object[] args = { order.getCart_id(), order.getCustomer_id(), order.getEmployee_id(), order.getTotal(),
        order.getPaid(), Timestamp.valueOf(order.getCreated_at()), Timestamp.valueOf(order.getUpdated_at()),
        order.getStatus().name(), order.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM orders WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<OrderModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM orders";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<OrderModel> orderList = new ArrayList<>();
      while (rs.next()) {
        OrderModel orderModel = createOrderModelFromResultSet(rs);
        orderList.add(orderModel);
      }
      if (orderList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return orderList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<OrderModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM orders WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
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
