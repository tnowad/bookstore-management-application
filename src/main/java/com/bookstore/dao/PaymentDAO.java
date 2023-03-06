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
    ArrayList<PaymentModel> payments = new ArrayList<>();
    String selectSql = "SELECT * FROM `payments`";
    ResultSet resultSet = DatabaseConnect.executeQuery(selectSql, null);
    while (resultSet.next()) {
      PaymentModel payment = new PaymentModel();
      payment.setPaymentId(resultSet.getString("payment_id"));
      payment.setOrderId(resultSet.getString("order_id"));
      payment.setPaymentDate(resultSet.getDate("payment_date"));
      payment.setPaymentAmount(resultSet.getFloat("payment_amount"));
      payments.add(payment);
    }
    return payments;
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

}
