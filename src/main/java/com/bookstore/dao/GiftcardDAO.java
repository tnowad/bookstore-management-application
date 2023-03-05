package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.GiftcardModel;

public class GiftcardDAO implements DAOInterface<GiftcardModel> {

  public static GiftcardDAO getInstance() {
    return new GiftcardDAO();
  }

  private GiftcardModel createGiftcardModelFromResultSet(ResultSet rs) throws SQLException {
    return new GiftcardModel(
        rs.getString("gift_card_id"),
        rs.getString("email"),
        rs.getFloat("value"),
        rs.getDate("redemption_history"),
        rs.getDate("expiration_date"));
  }

  @Override
  public ArrayList<GiftcardModel> readDatabase() throws SQLException {
    ArrayList<GiftcardModel> giftCardList = new ArrayList<>();
    try (
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement pst = conn.prepareStatement("SELECT * FROM `giftcard`");
        ResultSet rs = pst.executeQuery();) {
      while (rs.next()) {
        GiftcardModel giftCardModel = createGiftcardModelFromResultSet(rs);
        giftCardList.add(giftCardModel);
      }
    } catch (SQLException e) {
      throw e;
    }
    return giftCardList;
  }

  @Override
  public int insert(GiftcardModel giftCardModel) throws SQLException {
    try (
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement pst = conn.prepareStatement(
            "INSERT INTO `gift_card_table` (`gift_card_id`, `email`, `value`, `redemption_history`, `expiration_date`) VALUES (?, ?, ?, ?, ?)")) {
      pst.setString(1, giftCardModel.getGiftcardID());
      pst.setString(2, giftCardModel.getEmail());
      pst.setFloat(3, giftCardModel.getValue());
      pst.setDate(4, new java.sql.Date(giftCardModel.getRemdemption_history().getTime()));
      pst.setDate(5, new java.sql.Date(giftCardModel.getExpiration_date().getTime()));
      // Execute the query and return the number of rows affected
      return pst.executeUpdate();
    } catch (SQLException ex) {
      System.out.println("Error inserting gift card: " + ex.getMessage());
      return 0;
    }
  }

  @Override
  public int update(GiftcardModel giftCardModel) throws SQLException {
    try (
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement pst = conn.prepareStatement(
            "UPDATE `gift_card` SET `email` = ?, `value` = ?, `redemption_history` = ?, `expiration_date` = ? WHERE `giftcard_id` = ?")) {
      pst.setString(1, giftCardModel.getEmail());
      pst.setFloat(2, giftCardModel.getValue());
      pst.setDate(3, new java.sql.Date(giftCardModel.getRemdemption_history().getTime()));
      pst.setDate(4, new java.sql.Date(giftCardModel.getExpiration_date().getTime()));
      pst.setString(5, giftCardModel.getGiftcardID());
      return pst.executeUpdate();
    } catch (SQLException ex) {
      System.out.println("Error updating gift card: " + ex.getMessage());
      throw ex;
    }
  }

  @Override
  public int delete(String giftCardId) throws SQLException {
    try (
        Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM `Giftcard` WHERE gift_card_id = ?")) {
      pst.setString(1, giftCardId);
      return pst.executeUpdate();
    }
  }

  @Override
  public List<GiftcardModel> searchByCondition(String condition) throws SQLException {
    // Build the SQL query with passed condition for searching gift_card data
    String query = "SELECT * FROM `Giftcard` WHERE " + condition;
    try (
        // Get a database connection
        Connection conn = DatabaseConnect.getConnection();
        // Prepare the SQL statement with the built query
        PreparedStatement pst = conn.prepareStatement(query);
        // Execute the SQL statement and get result set
        ResultSet rs = pst.executeQuery()) {

      // Create an ArrayList of gift_cards to hold the retrieved ones
      List<GiftcardModel> giftCardList = new ArrayList<>();

      // Loop through the result set and retrieve giftcard data into GiftcardsModel
      // class
      while (rs.next()) {
        GiftcardModel giftCardModel = new GiftcardModel();
        giftCardModel.setGiftcardID(rs.getString("giftcard_id"));
        giftCardModel.setEmail(rs.getString("email"));
        giftCardModel.setValue(rs.getFloat("value"));
        giftCardModel.setRemdemption_history(rs.getDate("redemptionHistory"));
        giftCardModel.setExpiration_date(rs.getDate("expirationDate"));

        giftCardList.add(giftCardModel);
      }

      // Print a message if no records are found for the given search criteria
      if (giftCardList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }

      // Return the ArrayList of giftcards that meet the search criteria
      return giftCardList;

    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<GiftcardModel> searchByCondition(String condition, String columnName) throws SQLException {
    // Build the SQL query with given condition and column name to search only for
    // promotion data
    String query = "SELECT * FROM `Giftcard` WHERE " + columnName + " LIKE ?";
    try (
        // Get a database connection
        Connection con = DatabaseConnect.getConnection();
        // Prepare the SQL statement with the built query
        PreparedStatement pst = con.prepareStatement(query);) {
      // Set wildcarded value to the prepared statement
      pst.setString(1, "%" + condition + "%");
      // Execute the SQL statement and get result set
      ResultSet rs = pst.executeQuery();
      // Create an ArrayList of promotions to hold the retrieved ones
      List<GiftcardModel> giftCardList = new ArrayList<>();
      // Loop through result set and retrieve promotion data into PromotionsModel
      // class
      while (rs.next()) {
        GiftcardModel giftcardModel = new GiftcardModel();
        giftcardModel.setGiftcardID(rs.getString("giftcard_id"));
        giftcardModel.setEmail(rs.getString("email"));
        giftcardModel.setValue(rs.getFloat("value"));
        giftcardModel.setRemdemption_history(rs.getDate("redemptionHistory"));
        giftcardModel.setExpiration_date(rs.getDate("expirationDate"));

        giftCardList.add(giftcardModel);
      }
      // Return the ArrayList of promotions that meet the search criteria
      return giftCardList;
    } catch (SQLException e) {
      throw e;
    }
  }

}
