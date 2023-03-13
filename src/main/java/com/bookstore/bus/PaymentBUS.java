package com.bookstore.bus;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.PaymentDAO;
import com.bookstore.model.PaymentModel;

public class PaymentBUS extends BUSAbstract<PaymentModel> {

  private final List<PaymentModel> paymentList = new ArrayList<>();
  private final PaymentDAO paymentDAO;

  protected PaymentBUS(PaymentDAO paymentDAO) throws SQLException, ClassNotFoundException {
    this.paymentDAO = paymentDAO;
    this.paymentList.addAll(paymentDAO.readDatabase());
  }

  @Override
  protected ArrayList<PaymentModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return paymentDAO.readDatabase();
  }

  @Override
  protected int getId(PaymentModel paymentModel) {
    return paymentModel.getId();
  }

  @Override
  protected PaymentModel mapToEntity(PaymentModel from) {
    PaymentModel to = new PaymentModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(PaymentModel from, PaymentModel to) {
    to.setOrderId(from.getOrderId());
    to.setUserId(from.getUserId());
    to.setAmount(from.getAmount());
    to.setPaymentMethod(from.getPaymentMethod());
    to.setPaymentMethodId(from.getPaymentMethodId());
    to.setStatus(from.getStatus());
    to.setCreatedAt(from.getCreatedAt());
    to.setUpdatedAt(from.getUpdatedAt());
  }

  @Override
  protected boolean checkFilter(PaymentModel paymentModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id":
        return paymentModel.getId() == Integer.parseInt(value);
      case "order_id":
        return paymentModel.getOrderId() == Integer.parseInt(value);
      case "user_id":
        return paymentModel.getUserId() == Integer.parseInt(value);
      case "amount":
        return paymentModel.getAmount() == Integer.parseInt(value);
      case "payment_method":
        return paymentModel.getPaymentMethod().toString().equalsIgnoreCase(value);
      case "payment_method_id":
        return paymentModel.getPaymentMethodId() == Integer.parseInt(value);
      case "status":
        return paymentModel.getStatus().toString().equalsIgnoreCase(value);
      case "created_at":
        // Assuming that the input value is a timestamp string in the format of
        // "yyyy-MM-dd HH:mm:ss"
        return paymentModel.getCreatedAt().equals(Timestamp.valueOf(value));
      case "updated_at":
        // Assuming that the input value is a timestamp string in the format of
        // "yyyy-MM-dd HH:mm:ss"
        return paymentModel.getUpdatedAt().equals(Timestamp.valueOf(value));
      default:
        return checkAllColumns(paymentModel, value);
    }
  }

  protected boolean checkAllColumns(PaymentModel paymentModel, String value) {
    return paymentModel.getId() == Integer.parseInt(value)
        || paymentModel.getOrderId() == Integer.parseInt(value)
        || paymentModel.getUserId() == Integer.parseInt(value)
        || paymentModel.getAmount() == Integer.parseInt(value)
        || paymentModel.getPaymentMethod().toString().equalsIgnoreCase(value)
        || paymentModel.getPaymentMethodId() == Integer.parseInt(value)
        || paymentModel.getStatus().toString().equalsIgnoreCase(value)
        || paymentModel.getCreatedAt().toString().contains(value)
        || paymentModel.getUpdatedAt().toString().contains(value);
  }

  @Override
  protected int insertModel(PaymentModel paymentModel) throws SQLException, ClassNotFoundException {
    if (paymentModel.getOrderId() <= 0) {
      throw new IllegalArgumentException("Order ID must be greater than 0!");
    }
    if (paymentModel.getUserId() <= 0) {
      throw new IllegalArgumentException("User ID must be greater than 0!");
    }
    if (paymentModel.getAmount() <= 0) {
      throw new IllegalArgumentException("Amount must be greater than 0!");
    }
    if (paymentModel.getPaymentMethod() == null) {
      throw new IllegalArgumentException("Payment method cannot be null or empty!");
    }
    return add(paymentModel);
  }

  @Override
  protected int updateModel(PaymentModel paymentModel) throws SQLException, ClassNotFoundException {
    return update(paymentModel);
  }

  @Override
  protected int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<PaymentModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

  public PaymentModel getPaymentModel(int id) {
    return getModel(id);
  }

  public List<PaymentModel> getPaymentList() {
    return Collections.unmodifiableList(paymentList);
  }
}