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
  public int update(GoodNotesReceiptDetailModel goodNotesReceiptDetailModel)
      throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE `good_notes_receipt_detail` SET `amount`=?, `price`=? WHERE `dgnr_id`=?";
    Object[] args = { goodNotesReceiptDetailModel.getPrice(), goodNotesReceiptDetailModel.getAmount() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String dgnrId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM `good_notes_receipt_detail` WHERE `dgnr_id`=?";
    Object[] args = { dgnrId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<GoodNotesReceiptDetailModel> searchByCondition(String condition)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM good_notes_receipt";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<GoodNotesReceiptDetailModel> goodNotesReceiptList = new ArrayList<>();
      while (rs.next()) {
        GoodNotesReceiptDetailModel goodNotesReceiptModel = createDGNRFromResultSet(rs);
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
  public List<GoodNotesReceiptDetailModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM good_notes_receipt WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<GoodNotesReceiptDetailModel> receiptList = new ArrayList<>();
        while (rs.next()) {
          GoodNotesReceiptDetailModel receiptModel = createDGNRFromResultSet(rs);
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
