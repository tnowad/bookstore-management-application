package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.PaymentMethodModel;

public class PaymentMethodDAO implements DAOInterface<PaymentMethodModel> {

  public static PaymentMethodDAO getInstance() {
    return new PaymentMethodDAO();
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
  public List<PaymentMethodModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    if (columnName == null || columnName.isEmpty()) {
      throw new IllegalArgumentException("Column name cannot be empty");
    } else if (condition == null || condition.isEmpty()) {
      throw new IllegalArgumentException("Condition cannot be empty");
    }

    String query = "SELECT * FROM payment_methods WHERE " + columnName + " LIKE ?";
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
