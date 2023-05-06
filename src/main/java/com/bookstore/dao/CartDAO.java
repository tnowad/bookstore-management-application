package com.bookstore.dao;

import com.bookstore.enums.CartStatus;
import com.bookstore.interfaces.IDAO;
import com.bookstore.models.CartModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CartDAO implements IDAO<CartModel> {

  private static CartDAO instance;

  public static CartDAO getInstance() {
    if (instance == null) {
      instance = new CartDAO();
    }
    return instance;
  }

  private CartModel createCartModelFromResultSet(ResultSet rs)
    throws SQLException {
    int id = rs.getInt("id");
    int userId = rs.getInt("user_id");
    LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
    CartStatus status = CartStatus.valueOf(
      rs.getString("status").toUpperCase()
    );
    LocalDateTime expires = null;
    if (rs.getTimestamp("expires") != null) {
      expires = rs.getTimestamp("expires").toLocalDateTime();
    }
    int promotionId = rs.getInt("promotion_id");
    return new CartModel(id, userId, createdAt, status, expires, promotionId);
  }

  @Override
  public ArrayList<CartModel> readDatabase() {
    ArrayList<CartModel> cartList = new ArrayList<>();
    try (
      ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM carts")
    ) {
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
    String insertSql =
      "INSERT INTO carts (user_id, status, expires) VALUES (?, ?, ?)";
    Object[] args = {
      cart.getUserId(),
      cart.getStatus().name(),
      cart.getExpires(),
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(CartModel cart) {
    String updateSql =
      "UPDATE carts SET user_id = ?, status = ?, expires = ?, promotion_id = ? WHERE id = ?";
    Object[] args = {
      cart.getUserId(),
      cart.getStatus().name(),
      cart.getExpires(),
      cart.getPromotionId(),
      cart.getId(),
    };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateStatus(int cartId, String status) {
    String updateSql = "UPDATE carts SET status = ? WHERE id = ?";
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
    String deleteSql = "DELETE FROM carts WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<CartModel> search(String condition, String[] columnNames) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException(
        "Search condition cannot be empty or null"
      );
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query =
        "SELECT * FROM carts WHERE CONCAT(id, user_id, created_at, status, promotion_id, expires) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in carts table
      String column = columnNames[0];
      query = "SELECT * FROM carts WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in carts table
      query =
        "SELECT id, user_id, created_at, status, promotion_id, expires FROM carts WHERE CONCAT(" +
        String.join(", ", columnNames) +
        ") LIKE ?";
    }

    try (
      PreparedStatement pst = DatabaseConnection.getPreparedStatement(
        query,
        "%" + condition + "%"
      )
    ) {
      try (ResultSet rs = pst.executeQuery()) {
        List<CartModel> cartList = new ArrayList<>();
        while (rs.next()) {
          CartModel cartModel = createCartModelFromResultSet(rs);
          cartList.add(cartModel);
        }
        if (cartList.isEmpty()) {
          throw new SQLException(
            "No records found for the given condition: " + condition
          );
        }
        return cartList;
      }
    } catch (SQLException e) {
      return Collections.emptyList();
    }
  }
}
