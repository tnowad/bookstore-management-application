package com.bookstore.dao;

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
  public List<GoodNotesReceiptModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM good_notes_receipt";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<GoodNotesReceiptModel> goodNotesReceiptList = new ArrayList<>();
      while (rs.next()) {
        GoodNotesReceiptModel goodNotesReceiptModel = createGNRFromResultSet(rs);
        goodNotesReceiptList.add(goodNotesReceiptModel);
      }
      if (goodNotesReceiptList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return goodNotesReceiptList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<GoodNotesReceiptModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM good_notes_receipt WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<GoodNotesReceiptModel> receiptList = new ArrayList<>();
        while (rs.next()) {
          GoodNotesReceiptModel receiptModel = createGNRFromResultSet(rs);
          receiptList.add(receiptModel);
        }
        if (receiptList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return receiptList;
      }
    }
  }

}
