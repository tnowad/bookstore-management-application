package com.bookstore.bus;

import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.PaymentMethodDAO;
import com.bookstore.model.PaymentMethodModel;

public class PaymentMethodBUS extends BUSAbstract<PaymentMethodModel> {

  private final List<PaymentMethodModel> paymentMethodList = new ArrayList<>();
  private final PaymentMethodDAO paymentMethodDAO = PaymentMethodDAO.getInstance();

  public PaymentMethodBUS() throws SQLException, ClassNotFoundException {
    this.paymentMethodList.addAll(paymentMethodDAO.readDatabase());
  }

  @Override
  protected ArrayList<PaymentMethodModel> readFromDatabase() throws SQLException, ClassNotFoundException {
    return paymentMethodDAO.readDatabase();
  }

  @Override
  public int getId(PaymentMethodModel paymentMethodModel) {
    return paymentMethodModel.getId();
  }

  public PaymentMethodModel getPaymentMethodModel(int id) {
    return getModel(id);
  }

  public List<PaymentMethodModel> getPaymentMethodList() {
    return Collections.unmodifiableList(paymentMethodList);
  }

  @Override
  protected PaymentMethodModel mapToEntity(PaymentMethodModel from) {
    PaymentMethodModel to = new PaymentMethodModel();
    updateEntityFields(from, to);
    return to;
  }

  @Override
  protected void updateEntityFields(PaymentMethodModel from, PaymentMethodModel to) {
    to.setPaymentId(from.getPaymentId());
    to.setCardNumber(from.getCardNumber());
    to.setCardHolder(from.getCardHolder());
    to.setExpirationDate(from.getExpirationDate());
    to.setCustomerId(from.getCustomerId());
  }

  @Override
  protected boolean checkFilter(PaymentMethodModel paymentMethodModel, String value, String column) {
    switch (column.toLowerCase()) {
      case "id" -> {
        return paymentMethodModel.getId() == Integer.parseInt(value);
      }
      case "payment_id" -> {
        return paymentMethodModel.getPaymentId().equalsIgnoreCase(value);
      }
      case "card_number" -> {
        return paymentMethodModel.getCardNumber().equalsIgnoreCase(value);
      }
      case "card_holder" -> {
        return paymentMethodModel.getCardHolder().equalsIgnoreCase(value);
      }
      case "expiration_date" -> {
        try {
          Date expirationDate = paymentMethodModel.getExpirationDate();
          Date inputDate = Date.valueOf(value);
          return expirationDate.compareTo(inputDate) <= 0;
        } catch (IllegalArgumentException e) {
          System.out.println("Invalid date format: " + value);
          return false;
        }
      }
      case "customer_id" -> {
        return paymentMethodModel.getCustomerId() == Integer.parseInt(value);
      }
      default -> {
        return checkAllColumns(paymentMethodModel, value);
      }
    }
  }

  private boolean checkAllColumns(PaymentMethodModel paymentMethodModel, String value) {
    return paymentMethodModel.getId() == Integer.parseInt(value)
        || paymentMethodModel.getPaymentId().equalsIgnoreCase(value)
        || paymentMethodModel.getCardNumber().equalsIgnoreCase(value)
        || paymentMethodModel.getCardHolder().equalsIgnoreCase(value)
        || paymentMethodModel.getExpirationDate().toString().contains(value)
        || paymentMethodModel.getCustomerId() == Integer.parseInt(value);
  }

  @Override
  public int insertModel(PaymentMethodModel paymentMethodModel) throws SQLException, ClassNotFoundException {
    if (paymentMethodModel.getPaymentId() == null || paymentMethodModel.getPaymentId().isEmpty()) {
      throw new IllegalArgumentException("Payment ID cannot be null or empty!");
    }
    if (paymentMethodModel.getCardNumber() == null || paymentMethodModel.getCardNumber().isEmpty()) {
      throw new IllegalArgumentException("Card number cannot be null or empty!");
    }
    if (paymentMethodModel.getCardHolder() == null || paymentMethodModel.getCardHolder().isEmpty()) {
      throw new IllegalArgumentException("Card holder cannot be null or empty!");
    }
    if (paymentMethodModel.getExpirationDate() == null) {
      throw new IllegalArgumentException("Expiration date cannot be null!");
    }

    return add(paymentMethodModel);
  }

  @Override
  public int updateModel(PaymentMethodModel paymentMethodModel) throws SQLException, ClassNotFoundException {
    return update(paymentMethodModel);
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    return delete(id);
  }

  public List<PaymentMethodModel> searchModel(String value, String columns) {
    return search(value, columns);
  }

}