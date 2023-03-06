package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.GoodNotesReceiptDetailModel;

public class GoodNotesReceiptDetailDAO implements DAOInterface<GoodNotesReceiptDetailModel> {

  public static GoodNotesReceiptDAO getInstance() {
    return new GoodNotesReceiptDAO();
  }

  private GoodNotesReceiptDetailModel createDGNRFromResultSet(ResultSet rs) throws SQLException {
    return new GoodNotesReceiptDetailModel(
        rs.getString("dgnrId"),
        rs.getString("productId"),
        rs.getInt("amount"),
        rs.getFloat("price"));
  }

  @Override
  public ArrayList<GoodNotesReceiptDetailModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<GoodNotesReceiptDetailModel> dgnrList = new ArrayList<>();

    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM `good_notes_receipts_detail`")) {
      while (rs.next()) {
        GoodNotesReceiptDetailModel goodNodesReceiptDetailModel = createDGNRFromResultSet(rs);
        dgnrList.add(goodNodesReceiptDetailModel);
      }
    }
    return dgnrList;
  }

  @Override
  public int insert(GoodNotesReceiptDetailModel goodNotesReceiptDetailModel)
      throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO `good_notes_receipt` (`dgnr_id`, `product_id`,`amount`,`price`) VALUES (?, ?,?,?)";
    Object[] args = { goodNotesReceiptDetailModel.getDgnrId(), goodNotesReceiptDetailModel.getProductId() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(GoodNotesReceiptDetailModel goodNotesReceiptDeatailModel) throws SQLException {
    String updateSql = "UPDATE `account` SET `username`=?, `password`=?, `status`=? WHERE `accountId`=?";
    Object[] args = { goodNotesReceiptModel.getGnrId(), goodNotesReceiptModel.getImportDate() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String dgnrId) throws SQLException {
    try (
        Connection con = DatabaseConnect.getConnection();
        PreparedStatement pst = con.prepareStatement("DELETE FROM `GoodNotesReceiptDetail` WHERE `dgnrId`=?")) {
      pst.setString(1, dgnrId);
      return pst.executeUpdate();
    }
  }

  @Override
  public List<GoodNotesReceiptDetailModel> searchByCondition(String condition) throws SQLException {
    String query = "SELECT * FROM `GoodNotesReceiptDetail` WHERE " + condition;
    try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
        PreparedStatement pst = conn.prepareStatement(query); // Prepares a SELECT query with a WHERE clause
        ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
      List<GoodNotesReceiptDetailModel> dgnrList = new ArrayList<>();
      while (rs.next()) { // Loops through the ResultSet and creates BookModel objects from each row
        GoodNotesReceiptDetailModel goodNotesReceiptDetailModel = createDGNRFromResultSet(rs);
        dgnrList.add(goodNotesReceiptDetailModel); // Adds each BookModel object to the ArrayList
      }
      if (dgnrList.isEmpty()) { // Prints a message if no records were found
        System.out.println("No records found for the given condition: " + condition);
      }
      return dgnrList; // Returns the ArrayList of BookModels
    } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
      throw e;
    }
  }

  @Override
  public List<GoodNotesReceiptDetailModel> searchByCondition(String condition, String columnName)
      throws SQLException {
    String query = "SELECT * FROM `GoodNotesReceiptDetail` WHERE " + columnName + " LIKE ?";
    try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
        PreparedStatement pst = conn.prepareStatement(query)) { // Prepares a SELECT query with a WHERE clause
                                                                // using a LIKE operator
      pst.setString(1, "%" + condition + "%"); // Sets the value of the placeholder in the query with the given
                                               // condition
      try (ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
        List<GoodNotesReceiptDetailModel> dgnrList = new ArrayList<>();
        while (rs.next()) { // Loops through the ResultSet and creates InvoiceModel objects from each row
          GoodNotesReceiptDetailModel goodNotesReceiptDeatailModel = createDGNRFromResultSet(rs);
          dgnrList.add(goodNotesReceiptDeatailModel); // Adds each InvoiceModel object to the List
        }
        if (dgnrList.isEmpty()) { // Throws a SQLException if no records were found
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return dgnrList; // Returns the List of InvoiceModels
      }
    } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
      throw e;
    }
  }

}
