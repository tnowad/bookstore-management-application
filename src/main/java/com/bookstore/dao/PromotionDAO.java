package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.PromotionModel;

public class PromotionDAO implements IDAO<PromotionModel> {
  private static PromotionDAO instance;

  public static PromotionDAO getInstance() {
    if (instance == null) {
      instance = new PromotionDAO();
    }
    return instance;
  }

  private PromotionModel createPromotionModelFromResultSet(ResultSet rs) throws SQLException {
    return new PromotionModel(
        rs.getInt("id"),
        rs.getString("description"),
        rs.getInt("quantity"),
        rs.getDate("start_date"),
        rs.getDate("end_date"),
        rs.getString("condition_apply"),
        rs.getInt("discount_percent"),
        rs.getInt("discount_amount"));
  }

  @Override
  public ArrayList<PromotionModel> readDatabase() {
    ArrayList<PromotionModel> promotionList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM promotions")) {
      while (rs.next()) {
        PromotionModel promotionModel = createPromotionModelFromResultSet(rs);
        promotionList.add(promotionModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return promotionList;
  }

  @Override
  public int insert(PromotionModel promotion) {
    String insertSql = "INSERT INTO promotions "
        + "(description, quantity, start_date, end_date, condition_apply, discount_percent, discount_amount)"
        + " VALUES (?, ?, ?, ?, ?, ?, ?)";
    Object[] args = {
        promotion.getDescription(),
        promotion.getQuantity(),
        promotion.getStartDate(),
        promotion.getEndDate(),
        promotion.getConditionApply(),
        promotion.getDiscountPercent(),
        promotion.getDiscountAmount()
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(PromotionModel promotion) {
    String updateSql = "UPDATE promotions SET description = ?, quantity = ?, start_date = ?, end_date = ?, condition_apply = ?, [...] WHERE id = ?";
    Object[] args = {
        promotion.getDescription(),
        promotion.getQuantity(),
        promotion.getStartDate(),
        promotion.getEndDate(),
        promotion.getConditionApply(),
        promotion.getDiscountPercent(),
        promotion.getDiscountAmount(),
        promotion.getId()
    };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateQuantity(int id, int quantity) {
    String updateSql = "UPDATE promotions SET quantity = ? WHERE id = ?";
    Object[] args = { quantity, id };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM promotions WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<PromotionModel> search(String condition, String[] columnNames)
      {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM promotions WHERE CONCAT(id, description, quantity, start_date, end_date, condition_apply, discount_percent, discount_amount) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in promotions table
      String column = columnNames[0];
      query = "SELECT * FROM promotions WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in promotions table
      query = "SELECT id, description, quantity, start_date, end_date, condition_apply, discount_percent, discount_amount FROM promotions WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<PromotionModel> promotionList = new ArrayList<>();
        while (rs.next()) {
          PromotionModel promotionModel = createPromotionModelFromResultSet(rs);
          promotionList.add(promotionModel);
        }
        if (promotionList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return promotionList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
