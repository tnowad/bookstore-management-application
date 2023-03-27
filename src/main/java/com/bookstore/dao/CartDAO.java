package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.CartModel;
import com.bookstore.model.CartModel.Status;

public class CartDAO implements IDAO<CartModel> {
  private static CartDAO instance;

  public static CartDAO getInstance() {
    if (instance == null) {
      instance = new CartDAO();
    }
    return instance;
  }

  private CartModel createCartModelFromResultSet(ResultSet rs) throws SQLException {
    return new CartModel(
        rs.getInt("id"),
        rs.getInt("user_id"),
        rs.getTimestamp("created_at"),
        CartModel.Status.valueOf(rs.getString("status").toLowerCase()),
        rs.getTimestamp("expires"),
        rs.getInt("promotion_id"));
  }

  @Override
  public ArrayList<CartModel> readDatabase() {
    ArrayList<CartModel> cartList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM carts")) {
      while (rs.next()) {
        CartModel cartModel = createCartModelFromResultSet(rs);
        cartList.add(cartModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return cartList;
  }

  @Override
  public int insert(CartModel cart) {
    String insertSql = "INSERT INTO carts (user_id, status, promotion_id, expires) VALUES (?, ?, ?, ?)";
    Object[] args = { cart.getUserId(), cart.getStatus().name(), cart.getPromotionId(), cart.getExpires() };
    return DatabaseConnection.executeUpdate(insertSql, args);
  }

  @Override
  public int update(CartModel cart) {
    String updateSql = "UPDATE carts SET user_id = ?, status = ?, expires = ?, promotion_id = ? WHERE id = ?";
    Object[] args = { cart.getUserId(), cart.getStatus().name(), cart.getExpires(), cart.getPromotionId(),
        cart.getId() };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  public int updateStatus(int userId, Status status) {
    String updateSql = "UPDATE carts SET status = ? WHERE userId = ?";
    Object[] args = { status, userId };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM carts WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnection.executeUpdate(deleteSql, args);
  }

  @Override
  public List<CartModel> search(String condition, String[] columnNames) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM carts WHERE CONCAT(id, user_id, created_at, status, promotion_id, expires) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in carts table
      String column = columnNames[0];
      query = "SELECT * FROM carts WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in carts table
      query = "SELECT id, user_id, created_at, status, promotion_id, expires FROM carts WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<CartModel> cartList = new ArrayList<>();
        while (rs.next()) {
          CartModel cartModel = createCartModelFromResultSet(rs);
          cartList.add(cartModel);
        }
        if (cartList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return cartList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return Collections.emptyList();
  }
}
