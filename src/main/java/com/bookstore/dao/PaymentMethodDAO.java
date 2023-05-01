package com.bookstore.dao;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.PaymentMethodModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentMethodDAO implements IDAO<PaymentMethodModel> {

  private static PaymentMethodDAO instance;

  public static PaymentMethodDAO getInstance() {
    if (instance == null) {
      instance = new PaymentMethodDAO();
    }
    return instance;
  }

  private PaymentMethodModel createPaymentMethodModelFromResultSet(
    ResultSet rs
  ) throws SQLException {
    PaymentMethodModel paymentMethodModel = new PaymentMethodModel(
      rs.getInt("id"),
      rs.getString("payment_id"),
      rs.getString("card_number"),
      rs.getString("card_holder"),
      rs.getDate("expiration_date").toLocalDate(),
      rs.getInt("customer_id")
    );
    return paymentMethodModel;
  }

  @Override
  public ArrayList<PaymentMethodModel> readDatabase() {
    ArrayList<PaymentMethodModel> paymentMethodList = new ArrayList<>();
    String query = "SELECT * FROM payment_methods";
    try (ResultSet rs = DatabaseConnection.executeQuery(query)) {
      while (rs.next()) {
        PaymentMethodModel paymentMethodModel = createPaymentMethodModelFromResultSet(
          rs
        );
        paymentMethodList.add(paymentMethodModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return paymentMethodList;
  }

  @Override
  public int insert(PaymentMethodModel paymentMethod) {
    String insertSql =
      "INSERT INTO payment_methods (payment_id, card_number, card_holder, expiration_date, customer_id)" +
      " VALUES (?, ?, ?, ?, ?)";
    Object[] args = {
      paymentMethod.getPaymentId(),
      paymentMethod.getCardNumber(),
      paymentMethod.getCardHolder(),
      paymentMethod.getExpirationDate(),
      paymentMethod.getCustomerId(),
    };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(PaymentMethodModel paymentMethod) {
    String updateSql =
      "UPDATE payment_methods SET payment_id = ?, card_number = ?, card_holder = ?, expiration_date = ?, customer_id = ? WHERE id = ?";
    Object[] args = {
      paymentMethod.getPaymentId(),
      paymentMethod.getCardNumber(),
      paymentMethod.getCardHolder(),
      paymentMethod.getExpirationDate(),
      paymentMethod.getCustomerId(),
      paymentMethod.getId(),
    };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM payment_methods WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<PaymentMethodModel> search(
    String condition,
    String[] columnNames
  ) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException(
        "Search condition cannot be empty or null"
      );
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query =
        "SELECT * FROM payment_methods WHERE CONCAT(id, payment_id, card_number, card_holder, expiration_date, customer_id) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in payment_methods table
      String column = columnNames[0];
      query = "SELECT * FROM payment_methods WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in payment_methods table
      query =
        "SELECT id, payment_id, card_number, card_holder, expiration_date, customer_id FROM payment_methods WHERE CONCAT(" +
        String.join(", ", columnNames) +
        ") LIKE ?";
    }

    try (
      PreparedStatement pst = DatabaseConnection.getPreparedStatement(
        query,
        "%" + condition + "%"
      )
    ) {
      try (ResultSet rs = pst.executeQuery()) {
        List<PaymentMethodModel> paymentMethodList = new ArrayList<>();
        while (rs.next()) {
          PaymentMethodModel paymentMethodModel = createPaymentMethodModelFromResultSet(
            rs
          );
          paymentMethodList.add(paymentMethodModel);
        }
        if (paymentMethodList.isEmpty()) {
          throw new SQLException(
            "No records found for the given condition: " + condition
          );
        }
        return paymentMethodList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
