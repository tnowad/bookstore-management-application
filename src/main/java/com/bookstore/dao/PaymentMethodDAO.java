package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.model.PaymentMethodModel;

public class PaymentMethodDAO implements IDAO<PaymentMethodModel> {

  private static PaymentMethodDAO instance;

  public static PaymentMethodDAO getInstance() {
    if (instance == null) {
      instance = new PaymentMethodDAO();
    }
    return instance;
  }

  private PaymentMethodModel createPaymentMethodModelFromResultSet(ResultSet rs) throws SQLException {
    PaymentMethodModel paymentMethodModel = new PaymentMethodModel(
        rs.getString("payment_id"),
        rs.getString("card_number"),
        rs.getString("card_holder"),
        rs.getDate("expiration_date"),
        rs.getInt("customer_id"));
    paymentMethodModel.setId(rs.getInt("id"));
    return paymentMethodModel;
  }

  @Override
  public ArrayList<PaymentMethodModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<PaymentMethodModel> paymentMethodList = new ArrayList<>();
    String query = "SELECT * FROM payment_methods";
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      while (rs.next()) {
        PaymentMethodModel paymentMethodModel = createPaymentMethodModelFromResultSet(rs);
        paymentMethodList.add(paymentMethodModel);
      }
    }
    return paymentMethodList;
  }

  @Override
  public int insert(PaymentMethodModel paymentMethod) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO payment_methods (payment_id, card_number, card_holder, expiration_date, customer_id)"
        + " VALUES (?, ?, ?, ?, ?)";
    Object[] args = {
        paymentMethod.getPaymentId(), paymentMethod.getCardNumber(), paymentMethod.getCardHolder(),
        paymentMethod.getExpirationDate(), paymentMethod.getCustomerId()
    };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(PaymentMethodModel paymentMethod) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE payment_methods SET payment_id = ?, card_number = ?, card_holder = ?, expiration_date = ?, customer_id = ? WHERE id = ?";
    Object[] args = {
        paymentMethod.getPaymentId(), paymentMethod.getCardNumber(), paymentMethod.getCardHolder(),
        paymentMethod.getExpirationDate(), paymentMethod.getCustomerId(), paymentMethod.getId()
    };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM payment_methods WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<PaymentMethodModel> search(String condition, String[] columnNames)
      throws SQLException, ClassNotFoundException {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM payment_methods WHERE CONCAT(id, payment_id, card_number, card_holder, expiration_date, customer_id) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in payment_methods table
      String column = columnNames[0];
      query = "SELECT * FROM payment_methods WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in payment_methods table
      query = "SELECT id, payment_id, card_number, card_holder, expiration_date, customer_id FROM payment_methods WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<PaymentMethodModel> paymentMethodList = new ArrayList<>();
        while (rs.next()) {
          PaymentMethodModel paymentMethodModel = createPaymentMethodModelFromResultSet(rs);
          paymentMethodList.add(paymentMethodModel);
        }
        if (paymentMethodList.isEmpty()) {
          throw new SQLException(
              "No records found for the given condition: " + condition);
        }
        return paymentMethodList;
      }
    }
  }

}
