package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.PromotionsModel;

public class PromotionDAO implements DAOInterface<PromotionsModel> {
  public static PromotionDAO getInstance() {
    return new PromotionDAO();
  }

  private PromotionsModel createPromotionsModelFromResultSet(ResultSet rs) throws SQLException {
    return new PromotionsModel(
        rs.getString("promotionId"),
        rs.getInt("amount"),
        rs.getDate("endDate"),
        rs.getDate("startDate"),
        rs.getString("offerDescription"),
        rs.getString("promotionType"),
        rs.getString("invoiceID"),
        rs.getFloat("discountAmount"));
  }

  @Override
  public ArrayList<PromotionsModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<PromotionsModel> promotionList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * from promotion")) {
      while (rs.next()) {
        PromotionsModel promotionModel = createPromotionsModelFromResultSet(rs);
        promotionList.add(promotionModel);
      }
    }
    return promotionList;
  }

  @Override
  public int insert(PromotionsModel promotion) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO promotion "
        + "(promotion_id, amount, end_date, start_date, offer_description, promotion_type, invoice_id, discount_amount) "
        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    Object[] args = { promotion.getPromotionId(), promotion.getAmount(), promotion.getEndDate(),
        promotion.getStartDate(), promotion.getOfferDescription(), promotion.getPromotionType(),
        promotion.getInvoiceId(), promotion.getDiscountAmount() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(PromotionsModel promotion) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE promotion SET amount = ?, end_date = ?, start_date = ?, "
        + "offer_description = ?, promotion_type = ?, invoice_id = ?, discount_amount = ? WHERE promotion_id = ?";
    Object[] args = { promotion.getAmount(), promotion.getEndDate(), promotion.getStartDate(),
        promotion.getOfferDescription(), promotion.getPromotionType(), promotion.getInvoiceId(),
        promotion.getDiscountAmount(), promotion.getPromotionId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String promotion_id) throws SQLException, ClassNotFoundException {
    String deleteQuery = "DELETE FROM promotion WHERE promotion_id = ?";
    Object[] args = { promotion_id };
    return DatabaseConnect.executeUpdate(deleteQuery, args);
  }

  @Override
  public List<PromotionsModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM promotion";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<PromotionsModel> promotionList = new ArrayList<>();
      while (rs.next()) {
        PromotionsModel promotionModel = createPromotionsModelFromResultSet(rs);
        promotionList.add(promotionModel);
      }
      if (promotionList.isEmpty()) {
        throw new SQLException("No promotions found for the given condition: " + condition);
      }
      return promotionList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<PromotionsModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM promotion WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<PromotionsModel> promotionList = new ArrayList<>();
        while (rs.next()) {
          PromotionsModel promotionModel = createPromotionsModelFromResultSet(rs);
          promotionList.add(promotionModel);
        }
        if (promotionList.isEmpty()) {
          System.out.println("No records found for the given condition: " + condition);
        }
        return promotionList;
      }
    }
  }

}
