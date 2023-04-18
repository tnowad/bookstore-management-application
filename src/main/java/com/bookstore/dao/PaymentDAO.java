package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.PaymentModel;
import com.bookstore.models.PaymentModel.*;

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
        PaymentMethod.valueOf(rs.getString("payment_method").toUpperCase()),
        rs.getInt("payment_method_id"),
        PaymentStatus.valueOf(rs.getString("status").toUpperCase()),
        rs.getTimestamp("created_at"),
        rs.getTimestamp("updated_at"));
  }

  @Override
  public ArrayList<PaymentModel> readDatabase() {
    ArrayList<PaymentModel> paymentList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM payments")) {
      while (rs.next()) {
        PaymentModel paymentModel = createPaymentModelFromResultSet(rs);
        paymentList.add(paymentModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return paymentList;
  }

  @Override
  public int insert(PaymentModel payment) {
    String insertSql = "INSERT INTO payments (order_id, user_id, amount, payment_method, payment_method_id, status) VALUES (?, ?, ?, ?, ?, ?)";
    Object[] args = { payment.getOrderId(), payment.getUserId(), payment.getAmount(),
        payment.getPaymentMethod().toString().toUpperCase(), payment.getPaymentMethodId(),
        payment.getStatus().toString().toUpperCase() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(PaymentModel payment) {
    String updateSql = "UPDATE payments SET order_id = ?, user_id = ?, amount = ?, payment_method = ?, payment_method_id = ?, status = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
    Object[] args = { payment.getOrderId(), payment.getUserId(), payment.getAmount(),
        payment.getPaymentMethod().toString().toUpperCase(), payment.getPaymentMethodId(),
        payment.getStatus().toString().toUpperCase(), payment.getId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  public int updateStatus(int orderId, PaymentStatus status) {
    String updateSql = "UPDATE payments SET status = ? WHERE order_id = ?";
    Object[] args = { status.toString().toUpperCase(), orderId };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM payments WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<PaymentModel> search(String condition, String[] columnNames) {

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
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
