package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.PaymentModel;
import com.bookstore.model.PaymentModel.*;

public class PaymentDAO implements DAOInterface<PaymentModel> {

  public static PaymentDAO getInstance() {
    return new PaymentDAO();
  }

  private PaymentModel createPaymentModelFromResultSet(ResultSet rs) throws SQLException {
    return new PaymentModel(
        rs.getInt("id"),
        rs.getInt("order_id"),
        rs.getInt("user_id"),
        rs.getInt("amount"),
        PaymentMethod.valueOf(rs.getString("payment_method")),
        rs.getInt("payment_method_id"),
        PaymentStatus.valueOf(rs.getString("status")),
        rs.getTimestamp("created_at"),
        rs.getTimestamp("updated_at"));
  }

  @Override
  public ArrayList<PaymentModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<PaymentModel> paymentList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM payments")) {
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
        payment.getPaymentMethod().toString(), payment.getPaymentMethodId(),
        payment.getStatus().toString() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(PaymentModel payment) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE payments SET order_id = ?, user_id = ?, amount = ?, payment_method = ?, payment_method_id = ?, status = ?, updated_at = CURRENT_TIMESTAMP WHERE id = ?";
    Object[] args = { payment.getOrderId(), payment.getUserId(), payment.getAmount(),
        payment.getPaymentMethod().toString(), payment.getPaymentMethodId(),
        payment.getStatus().toString(), payment.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM payments WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<PaymentModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    if (columnName == null || columnName.isEmpty()) {
      throw new IllegalArgumentException("Column name cannot be empty");
    } else if (condition == null || condition.isEmpty()) {
      throw new IllegalArgumentException("Condition cannot be empty");
    }

    String query = "SELECT * FROM payments WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
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
