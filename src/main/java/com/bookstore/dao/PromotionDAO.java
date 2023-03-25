package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.PromotionModel;

public class PromotionDAO implements IDAO<PromotionModel> {

  public static PromotionDAO getInstance() {
    return new PromotionDAO();
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
  public ArrayList<PromotionModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<PromotionModel> promotionList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM promotions")) {
      while (rs.next()) {
        PromotionModel promotionModel = createPromotionModelFromResultSet(rs);
        promotionList.add(promotionModel);
      }
    }
    return promotionList;
  }

  @Override
  public int insert(PromotionModel promotion) throws SQLException, ClassNotFoundException {
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
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(PromotionModel promotion) throws SQLException, ClassNotFoundException {
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
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM promotions WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<PromotionModel> search(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    if (columnName == null || columnName.isEmpty()) {
      throw new IllegalArgumentException("Column name cannot be empty");
    } else if (condition == null || condition.isEmpty()) {
      throw new IllegalArgumentException("Condition cannot be empty");
    }

    String query = "SELECT * FROM promotions WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
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
    }
  }
}
