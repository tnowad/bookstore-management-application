package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.CartItemsModel;

public class CartItemsDAO implements IDAO<CartItemsModel> {
  private static CartItemsDAO instance;

  public static CartItemsDAO getInstance() {
    if (instance == null) {
      instance = new CartItemsDAO();
    }
    return instance;
  }

  private CartItemsModel createCartItemsModelFromResultSet(ResultSet rs) throws SQLException {
    return new CartItemsModel(
        rs.getInt("cart_id"),
        rs.getString("book_isbn"),
        rs.getInt("price"),
        rs.getInt("quantity"),
        rs.getInt("discount"));
  }

  @Override
  public ArrayList<CartItemsModel> readDatabase() {
    ArrayList<CartItemsModel> cartItemsList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM cart_items")) {
      while (rs.next()) {
        CartItemsModel CartItemsModel = createCartItemsModelFromResultSet(rs);
        cartItemsList.add(CartItemsModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return cartItemsList;
  }

  @Override
  public int insert(CartItemsModel cartItem) {
    String insertSql = "INSERT INTO cart_items (cart_id, book_isbn, price, quantity, discount) VALUES (?, ?, ?, ?, ?)";
    Object[] args = { cartItem.getCartId(), cartItem.getBookIsbn(), cartItem.getPrice(),
        cartItem.getQuantity(), cartItem.getDiscount() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(CartItemsModel cartItem) {
    String updateSql = "UPDATE cart_items SET cart_id = ?, book_isbn = ?, price = ?, quantity = ?, discount = ? WHERE id = ?";
    Object[] args = { cartItem.getCartId(), cartItem.getBookIsbn(), cartItem.getPrice(),
        cartItem.getQuantity(), cartItem.getDiscount() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM cart_items WHERE cart_id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<CartItemsModel> search(String condition, String[] columnNames) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM cart_items WHERE CONCAT(cart_id, book_isbn, price, quantity, discount) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in cart_items table
      String column = columnNames[0];
      query = "SELECT * FROM cart_items WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in cart_items table
      query = "SELECT cart_id, book_isbn, price, quantity, discount FROM cart_items WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<CartItemsModel> cartItemsList = new ArrayList<>();
        while (rs.next()) {
          CartItemsModel CartItemsModel = createCartItemsModelFromResultSet(rs);
          cartItemsList.add(CartItemsModel);
        }
        if (cartItemsList.isEmpty()) {
          throw new SQLException("No cartItems found for the given search condition: " + condition);
        }
        return cartItemsList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }

}
