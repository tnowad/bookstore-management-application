package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.ShippingModel;
import com.bookstore.enums.ShippingStatus;

public class ShippingDAO implements IDAO<ShippingModel> {
  private static ShippingDAO instance;

  public static ShippingDAO getInstance() {
    if (instance == null) {
      instance = new ShippingDAO();
    }
    return instance;
  }

  private ShippingModel createShippingModelFromResultSet(ResultSet rs) throws SQLException {
    int id = rs.getInt("id");
    int orderId = rs.getInt("order_id");
    String shippingMethod = rs.getString("shipping_method");
    int addressId = rs.getInt("address_id");
    String statusString = rs.getString("status").toLowerCase();
    ShippingStatus status = ShippingStatus.valueOf(statusString.toUpperCase());
    return new ShippingModel(id, orderId, addressId, shippingMethod, status);
  }

  @Override
  public ArrayList<ShippingModel> readDatabase() {
    ArrayList<ShippingModel> shippingList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM shipping")) {
      while (rs.next()) {
        ShippingModel shippingModel = createShippingModelFromResultSet(rs);
        shippingList.add(shippingModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return shippingList;
  }

  @Override
  public int insert(ShippingModel shipping) {
    String insertSql = "INSERT INTO shipping (order_id, shipping_method, address_id, status) VALUES (?, ?, ?, ?)";
    Object[] args = { shipping.getOrderId(), shipping.getShippingMethod(), shipping.getAddressId(),
        shipping.getStatus().toString().toUpperCase() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(ShippingModel shipping) {
    String updateSql = "UPDATE shipping SET shipping_method = ?, address_id = ?, status = ? WHERE id = ?";
    Object[] args = { shipping.getShippingMethod(), shipping.getAddressId(),
        shipping.getStatus().toString().toUpperCase(),
        shipping.getId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateStatus(int orderId, ShippingStatus status) {
    String updateSql = "UPDATE shipping SET status = ? WHERE id = ?";
    Object[] args = { status, orderId };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM shipping WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<ShippingModel> search(String condition, String[] columnNames) {
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

    try {
      PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%");
      try {
        ResultSet rs = pst.executeQuery();
        List<ShippingModel> shippingList = new ArrayList<>();
        while (rs.next()) {
          ShippingModel shippingModel = createShippingModelFromResultSet(rs);
          shippingList.add(shippingModel);
        }
        if (shippingList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return shippingList;
      } catch (SQLException e) {
        throw new RuntimeException("Error executing SQL query", e);
      } finally {
        pst.close();
      }
    } catch (SQLException e) {
      throw new RuntimeException("Error preparing SQL statement", e);
    }
  }

}
