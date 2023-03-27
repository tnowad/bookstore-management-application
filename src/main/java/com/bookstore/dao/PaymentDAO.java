package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.PaymentModel;
import com.bookstore.model.PaymentModel.*;

public class PaymentDAO implements IDAO<PaymentModel> {

  private static PaymentDAO instance;

  public static PaymentDAO getInstance() {
    if (instance == null) {
      instance = new PaymentDAO();
    }
    return instance;
  }

  private PaymentModel createPaymentModelFromResultSet(ResultSet rs) throws SQLException {
    return new PaymentModel(
        rs.getInt("id"),
        rs.getInt("order_id"),
        rs.getInt("user_id"),
        rs.getInt("amount"),
        PaymentMethod.valueOf(rs.getString("payment_method")),
        rs.getInt("payment_method_id"),
        PaymentStatus.valueOf(rs.getString("status").toUpperCase()),
        rs.getTimestamp("created_at"),
        rs.getTimestamp("updated_at"));
  }

  @Override
  public ArrayList<PaymentModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<PaymentModel> paymentList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM payments")) {
      while (rs.next()) {
        PaymentModel paymentModel = createPaymentModelFromResultSet(rs);
        paymentList.add(paymentModel);
      }
    }
    return paymentList;
  }

  @Override
  public int insert(PaymentModel payment) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO payments (order_id, user_id, amount, payment_method, payment_method_id, status) VALUES (?, ?, ?, ?, ?, ?)";
    Object[] args = { payment.getOrderId(), payment.getUserId(), payment.getAmount(),
        payment.getPaymentMethod().toString().toUpperCase(), payment.getPaymentMethodId(),
        payment.getStatus().toString().toUpperCase() };
    return DatabaseConnection.executeUpdate(insertSql, args);
  }

  @Override
  public int update(PaymentModel payment) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE payments SET order_id = ?, user_id = ?, amount = ?, payment_method = ?, payment_method_id = ?, status = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
    Object[] args = { payment.getOrderId(), payment.getUserId(), payment.getAmount(),
        payment.getPaymentMethod().toString().toUpperCase(), payment.getPaymentMethodId(),
        payment.getStatus().toString().toUpperCase(), payment.getId() };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  public int updateStatus(int orderId, PaymentStatus status) {
    String updateSql = "UPDATE payments SET status = ? WHERE order_id = ?";
    Object[] args = { status, orderId };
    return DatabaseConnection.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM payments WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnection.executeUpdate(deleteSql, args);
  }

  @Override
  public List<PaymentModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {

    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM payments WHERE CONCAT(id, order_id, user_id, amount, payment_method, payment_method_id, status, created_at, updated_at) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in payments table
      String column = columnNames[0];
      query = "SELECT * FROM payments WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in payments table
      query = "SELECT id, order_id, user_id, amount, payment_method, payment_method_id, status, created_at, updated_at FROM payments WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<PaymentModel> paymentList = new ArrayList<>();
        while (rs.next()) {
          PaymentModel paymentModel = createPaymentModelFromResultSet(rs);
          paymentList.add(paymentModel);
        }
        if (paymentList.isEmpty()) {
          throw new SQLException(
              "No records found for the given condition: " + condition);
        }
        return paymentList;
      }
    }
  }
}
