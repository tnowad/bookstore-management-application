package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.GoodNotesReceiptModel;

public class GoodNotesReceiptDAO implements DAOInterface<GoodNotesReceiptModel> {

  public static GoodNotesReceiptDAO getInstance() {
    return new GoodNotesReceiptDAO();
  }

  // Helper method to create UserModel object from ResultSet
  private GoodNotesReceiptModel createGNRFromResultSet(ResultSet rs) throws SQLException {
    return new GoodNotesReceiptModel(
        rs.getDate("importDate"),
        rs.getString("gnrId"));
  }

  @Override
  public ArrayList<GoodNotesReceiptModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<GoodNotesReceiptModel> gnrList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM `good_notes_receipts`")) {
      while (rs.next()) {
        GoodNotesReceiptModel goodNodesReceiptModel = createGNRFromResultSet(rs);
        gnrList.add(goodNodesReceiptModel);
      }
    }
    return gnrList;
  }

  @Override
  public int insert(GoodNotesReceiptModel goodNotesReceiptModel) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO `good_notes_receipt` (`gnr_id`, `import_date`) VALUES (?, ?)";
    Object[] args = { goodNotesReceiptModel.getGnrId(), goodNotesReceiptModel.getImportDate() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(GoodNotesReceiptModel goodNotesReceiptModel) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE `good_notes_receipt` SET `import_date`=? WHERE `accountId`=?";
    Object[] args = { goodNotesReceiptModel.getGnrId(), goodNotesReceiptModel.getImportDate() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String gnrId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM `account` WHERE `accountId`=?";
    Object[] args = { gnrId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<GoodNotesReceiptModel> searchByCondition(String condition) throws SQLException {
    String query = "SELECT * FROM `goodnotesreceipt` WHERE " + condition;
    try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
        PreparedStatement pst = conn.prepareStatement(query); // Prepares a SELECT query with a WHERE clause
        ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
      List<GoodNotesReceiptModel> gnrList = new ArrayList<>();
      while (rs.next()) { // Loops through the ResultSet and creates BookModel objects from each row
        GoodNotesReceiptModel goodNotesReceiptModel = createGNRFromResultSet(rs);
        gnrList.add(goodNotesReceiptModel); // Adds each BookModel object to the ArrayList
      }
      if (gnrList.isEmpty()) { // Prints a message if no records were found
        System.out.println("No records found for the given condition: " + condition);
      }
      return gnrList; // Returns the ArrayList of BookModels
    } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
      throw e;
    }
  }

  @Override
  public List<GoodNotesReceiptModel> searchByCondition(String condition, String columnName) throws SQLException {
    String query = "SELECT * FROM `goodnotesreceipt` WHERE " + columnName + " LIKE ?";
    try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
        PreparedStatement pst = conn.prepareStatement(query)) { // Prepares a SELECT query with a WHERE clause
                                                                // using a LIKE operator
      pst.setString(1, "%" + condition + "%"); // Sets the value of the placeholder in the query with the given
                                               // condition
      try (ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
        List<GoodNotesReceiptModel> invoiceList = new ArrayList<>();
        while (rs.next()) { // Loops through the ResultSet and creates InvoiceModel objects from each row
          GoodNotesReceiptModel invoiceModel = createGNRFromResultSet(rs);
          invoiceList.add(invoiceModel); // Adds each InvoiceModel object to the List
        }
        if (invoiceList.isEmpty()) { // Throws a SQLException if no records were found
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return invoiceList; // Returns the List of InvoiceModels
      }
    } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
      throw e;
    }
  }

}
