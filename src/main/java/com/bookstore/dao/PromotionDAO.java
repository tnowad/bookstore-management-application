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
        rs.getFloat("discount_amount"));
  }

  @Override
  public ArrayList<PromotionsModel> readDatabase() throws SQLException {
    ArrayList<PromotionsModel> promotions = new ArrayList<>();
    try (
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM `promotion`");
        ResultSet rs = pst.executeQuery();) {
      while (rs.next()) {
        PromotionsModel promotionsModel = createPromotionsModelFromResultSet(rs);
        promotions.add(promotionsModel);
      }
    } catch (SQLException e) {
      throw e;
    }
    return promotions;
  }

  @Override
  public int insert(PromotionsModel promotionsModel) throws SQLException {
    try (Connection conn = DatabaseConnect.getConnection();
        PreparedStatement pst = conn.prepareStatement(
            "INSERT INTO `promotion` (`amount`, `end_date`, `start_date`, `offer_description`, `promotion_type`, `invoice_id`, `promotion_id`, `discount_amount`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
      pst.setInt(1, promotionsModel.getAmount());
      pst.setDate(3, new java.sql.Date(promotionsModel.getEndDate().getTime()));
      pst.setDate(4, new java.sql.Date(promotionsModel.getStartDate().getTime()));
      pst.setString(5, promotionsModel.getOfferDescription());
      pst.setString(6, promotionsModel.getPromotionType());
      pst.setString(7, promotionsModel.getInvoiceId());
      pst.setString(8, promotionsModel.getInvoiceId());
      pst.setFloat(9, promotionsModel.getDiscountAmount());
      // Step-3: Execute the query
      return pst.executeUpdate();
    } catch (SQLException ex) {
      System.out.println("Error inserting promotion: " + ex.getMessage());
      return 0;
    }
  }

  @Override
  public int update(PromotionsModel promotionsModel) throws SQLException {
    try (
        Connection conn = DatabaseConnect.getConnection(); // Established connection with Database
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE `promotion` SET `amount` = ?,`end_date` = ?, `start_date` = ?, `offer_description` = ?, `promotion_type` = ?, `invoice_id` = ?, `discount_amount` = ? WHERE `promotion_id` = ?")) {
      pst.setInt(1, promotionsModel.getAmount());
      pst.setDate(3, new java.sql.Date(promotionsModel.getEndDate().getTime()));
      pst.setDate(4, new java.sql.Date(promotionsModel.getStartDate().getTime()));
      pst.setString(5, promotionsModel.getOfferDescription());
      pst.setString(6, promotionsModel.getPromotionType());
      pst.setString(7, promotionsModel.getInvoiceId());
      pst.setFloat(8, promotionsModel.getDiscountAmount());
      pst.setString(9, promotionsModel.getPromotionId());

      return pst.executeUpdate(); // Returns number of rows updated
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public int delete(String promotion_id) throws SQLException {
    try (
        Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM `promotion` WHERE promotion_id=?")) {
      pst.setString(1, promotion_id);
      return pst.executeUpdate();
    }
  }

  @Override
  public List<PromotionsModel> searchByCondition(String condition) throws SQLException {
    // Build the SQL query with passed condition for searching promotions data
    StringBuilder sb = new StringBuilder("SELECT * FROM `promotion` WHERE ");
    sb.append(condition);
    String query = sb.toString();
    try (
        // Get a database connection
        Connection conn = DatabaseConnect.getConnection();
        // Prepare the SQL statement with the built query
        PreparedStatement pst = conn.prepareStatement(query);
        // Execute the SQL statement and get result set
        ResultSet rs = pst.executeQuery()) {
      // Create an ArrayList of promotions to hold the retrieved ones
      List<PromotionsModel> promotionList = new ArrayList<>();
      // Loop through the result set and retrieve promotion data into PromotionsModel
      // class
      while (rs.next()) {
        PromotionsModel promoton = new PromotionsModel();
        promoton.setAmount(rs.getInt("amount"));
        promoton.setEndDate(rs.getDate("end_date"));
        promoton.setStartDate(rs.getDate("start_date"));
        promoton.setOfferDescription(rs.getString("offer_description"));
        promoton.setPromotionType(rs.getString("promotion_type"));
        promoton.setInvoiceId(rs.getString("invoice_id"));
        promoton.setDiscountAmount(rs.getFloat("discount_amount"));
        promoton.setPromotionId(rs.getString("promotion_id"));

        promotionList.add(promoton);
      }
      // Print a message if no records are found for the given search criteria
      if (promotionList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      // Return the ArrayList of promotions that meet the search criteria
      return promotionList;
    } catch (SQLException e) {
      throw e;
    }

  }

  @Override
  public List<PromotionsModel> searchByCondition(String condition, String columnName) throws SQLException {
    // Build the SQL query with given condition and column name to search only for
    // promotion data
    String query = "SELECT * FROM `Promotion` WHERE " + columnName + " LIKE ?";
    try (
        // Get a database connection
        Connection con = DatabaseConnect.getConnection();
        // Prepare the SQL statement with the built query
        PreparedStatement pst = con.prepareStatement(query);) {
      // Set wildcarded value to the prepared statement
      pst.setString(1, "%" + condition + "%");
      // Execute the SQL statement and get result set
      ResultSet resultSet = pst.executeQuery();
      // Create an ArrayList of promotions to hold the retrieved ones
      List<PromotionsModel> promotionList = new ArrayList<>();
      // Loop through result set and retrieve promotion data into PromotionsModel
      // class
      while (resultSet.next()) {
        PromotionsModel promotion = new PromotionsModel();
        promotion.setAmount(resultSet.getInt("amount"));
        promotion.setEndDate(resultSet.getDate("end_date"));
        promotion.setStartDate(resultSet.getDate("start_date"));
        promotion.setOfferDescription(resultSet.getString("offer_description"));
        promotion.setPromotionType(resultSet.getString("promotion_type"));
        promotion.setInvoiceId(resultSet.getString("invoice_id"));
        promotion.setDiscountAmount(resultSet.getFloat("discount_amount"));
        promotion.setPromotionId(resultSet.getString("promotion_id"));
        promotionList.add(promotion);
      }
      // Return the ArrayList of promotions that meet the search criteria
      return promotionList;
    } catch (SQLException e) {
      throw e;
    }
  }

}
