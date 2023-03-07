package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.PaymentModel;

public class PaymentDAO implements DAOInterface<PaymentModel> {

  public static PaymentDAO getInstance() {
    return new PaymentDAO();
  }

  private PaymentModel createPaymentModelFromResultSet(ResultSet rs) throws SQLException {
    return new PaymentModel(
        rs.getString("paymentId"),
        rs.getString("orderId"),
        rs.getDate("paymentDate"),
        rs.getFloat("paymentAmount"));
  }

  @Override
  public ArrayList<PaymentModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<PaymentModel> paymentList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM `payments`")) {
      while (rs.next()) {
        PaymentModel payment = createPaymentModelFromResultSet(rs);
        paymentList.add(payment);
      }
    }
    return paymentList;
  }

  @Override
  public int insert(PaymentModel payment) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO `payments` (`payment_id`, `order_id`, `payment_date`, `payment_amount`) VALUES (?, ?, ?, ?)";
    Object[] args = { payment.getPaymentId(), payment.getOrderId(), payment.getPaymentDate(),
        payment.getPaymentAmount() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(PaymentModel payment) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE `payments` SET `payment_date`=?, `payment_amount`=? WHERE `payment_id`=?";
    Object[] args = { payment.getPaymentDate(), payment.getPaymentAmount(), payment.getPaymentId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(String paymentId) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM `payments` WHERE `payment_id`=?";
    Object[] args = { paymentId };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  public List<PaymentModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM payments";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<PaymentModel> paymentList = new ArrayList<>();
      while (rs.next()) {
        PaymentModel paymentModel = createPaymentModelFromResultSet(rs);
        paymentList.add(paymentModel);
      }
      if (paymentList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return paymentList;
    } catch (SQLException e) {
      throw e;
    }
  }

  @Override
  public List<PaymentModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM payment";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    query += " ORDER BY " + columnName;
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<PaymentModel> paymentList = new ArrayList<>();
        while (rs.next()) {
          PaymentModel paymentModel = createPaymentModelFromResultSet(rs);
          paymentList.add(paymentModel);
        }
        if (paymentList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return paymentList;
      }
    }
  }

}
