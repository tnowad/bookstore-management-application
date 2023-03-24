package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.CartModel;

public class CartDAO implements DAOInterface<CartModel> {

  public static CartDAO getInstance() {
    return new CartDAO();
  }

  private CartModel createCartModelFromResultSet(ResultSet rs) throws SQLException {
    return new CartModel(
        rs.getInt("id"),
        rs.getInt("user_id"),
        rs.getTimestamp("created_at"),
        CartModel.Status.valueOf(rs.getString("status").toUpperCase()),
        rs.getTimestamp("expires"),
        rs.getInt("promotion_id"));
  }

  @Override
  public ArrayList<CartModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<CartModel> cartList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM carts")) {
      while (rs.next()) {
        CartModel cartModel = createCartModelFromResultSet(rs);
        cartList.add(cartModel);
      }
    }
    return cartList;
  }

  @Override
  public int insert(CartModel cart) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO carts (user_id, status, promotion_id) VALUES (?, ?, ?)";
    Object[] args = { cart.getUserId(), cart.getStatus().name(), cart.getPromotionId() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(CartModel cart) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE carts SET user_id = ?, status = ?, expires = ?, promotion_id = ? WHERE id = ?";
    Object[] args = { cart.getUserId(), cart.getStatus().name(), cart.getExpires(), cart.getPromotionId(),
        cart.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM carts WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<CartModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    if (columnName == null || columnName.isEmpty()) {
      throw new IllegalArgumentException("Column name cannot be empty");
    } else if (condition == null || condition.isEmpty()) {
      throw new IllegalArgumentException("Condition cannot be empty");
    }

    String query = "SELECT * FROM carts WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
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
    }
  }
}
