package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.ShippingModel;
import com.bookstore.model.ShippingModel.Status;

public class ShippingDAO implements IDAO<ShippingModel> {
  private static ShippingDAO instance;

  public static ShippingDAO getInstance() {
    if (instance == null) {
      instance = new ShippingDAO();
    }
    return instance;
  }

  private ShippingModel createShippingModelFromResultSet(ResultSet rs) throws SQLException {
    return new ShippingModel(
        rs.getInt("id"),
        rs.getInt("order_id"),
        rs.getString("shipping_method"),
        rs.getInt("address_id"),
        ShippingModel.Status.valueOf(rs.getString("status").toLowerCase()));
  }

  @Override
  public ArrayList<ShippingModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<ShippingModel> shippingList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM shipping")) {
      while (rs.next()) {
        ShippingModel shippingModel = createShippingModelFromResultSet(rs);
        shippingList.add(shippingModel);
      }
    }
    return shippingList;
  }

  @Override
  public int insert(ShippingModel shipping) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO shipping (order_id, shipping_method, address_id, status) VALUES (?, ?, ?, ?)";
    Object[] args = { shipping.getOrderId(), shipping.getShippingMethod(), shipping.getAddressId(),
        shipping.getStatus().name() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(ShippingModel shipping) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE shipping SET shipping_method = ?, address_id = ?, status = ? WHERE id = ?";
    Object[] args = { shipping.getShippingMethod(), shipping.getAddressId(), shipping.getStatus().name(),
        shipping.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  public int updateStatus(int orderId, Status status) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE shipping SET status = ? WHERE status = ?";
    Object[] args = { status, status };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM shipping WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<ShippingModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM shipping WHERE CONCAT(id, order_id, shipping_method, address_id, status) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in shipping table
      String column = columnNames[0];
      query = "SELECT * FROM shipping WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in shipping table
      query = "SELECT id, order_id, shipping_method, address_id, status FROM shipping WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<ShippingModel> shippingList = new ArrayList<>();
        while (rs.next()) {
          ShippingModel shippingModel = createShippingModelFromResultSet(rs);
          shippingList.add(shippingModel);
        }
        if (shippingList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return shippingList;
      }
    }
  }
}
