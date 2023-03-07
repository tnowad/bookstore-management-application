package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.InvoiceModel;

public class InvoiceDAO implements DAOInterface<InvoiceModel> {

  public static InvoiceDAO getInstance() {
    return new InvoiceDAO();
  }

  private InvoiceModel createInvoiceModelFromResultSet(ResultSet rs) throws SQLException {
    return new InvoiceModel(
        rs.getDate("InvoiceDate").toLocalDate(),
        rs.getBoolean("PaymentStatus"),
        rs.getFloat("TotalAmountDue"),
        rs.getString("InvoiceID"));
  }

  @Override
  public ArrayList<InvoiceModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<InvoiceModel> invoiceList = new ArrayList<>();

    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM `invoice`")) {
      while (rs.next()) {
        InvoiceModel invoiceModel = createInvoiceModelFromResultSet(rs);
        invoiceList.add(invoiceModel);
      }
    }
    return invoiceList;
  }

  @Override
  public int insert(InvoiceModel invoiceModel)
      throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO `invoice` (`total_amount_due`, `payment_status`, `total_amount_due`, `invoice_id`) VALUES ?, ?, ?, ?)";
    Object[] args = { invoiceModel.getInvoiceDate(),
        invoiceModel.getTotalAmountDue(),
        invoiceModel.getPaymentStatus(), invoiceModel.getTotalAmountDue(), invoiceModel.getInvoiceID() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(InvoiceModel invoiceModel) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE `invoice` SET `total_amount_due` = ?, `payment_status` = ? WHERE `invoice_id` = ?";
    Object[] args = { invoiceModel.getTotalAmountDue(), invoiceModel.getPaymentStatus(), invoiceModel.getInvoiceID() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String invoiceId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM `invoice` WHERE `invoice_id` = ?";
    Object[] args = { invoiceId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<InvoiceModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM invoice";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<InvoiceModel> invoiceList = new ArrayList<>();
      while (rs.next()) {
        InvoiceModel invoice = createInvoiceModelFromResultSet(rs);
        invoiceList.add(invoice);
      }
      if (invoiceList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return invoiceList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<InvoiceModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM good_notes_receipt WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<InvoiceModel> invoiceList = new ArrayList<>();
        while (rs.next()) {
          InvoiceModel invoiceModel = createInvoiceModelFromResultSet(rs);
          invoiceList.add(invoiceModel);
        }
        if (invoiceList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return invoiceList;
      }
    }
  }
}