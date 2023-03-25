package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.bookstore.dao.PaymentMethodDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.PaymentMethodModel;

public class PaymentMethodBUS implements IBUS<PaymentMethodModel> {

  private final List<PaymentMethodModel> paymentMethodList = new ArrayList<>();

  public PaymentMethodBUS() throws SQLException, ClassNotFoundException {
    this.paymentMethodList.addAll(PaymentMethodDAO.getInstance().readDatabase());
  }

  @Override
  public List<PaymentMethodModel> getAllModels() {
    return Collections.unmodifiableList(paymentMethodList);
  }

  @Override
  public PaymentMethodModel getModelById(int id) throws SQLException, ClassNotFoundException {
    for (PaymentMethodModel paymentMethodModel : paymentMethodList) {
      if (paymentMethodModel.getId() == id) {
        return paymentMethodModel;
      }
    }
    return null;
  }

  public List<PaymentMethodModel> getPaymentMethodList() throws NullPointerException {
    return Collections.unmodifiableList(paymentMethodList);
  }

  private PaymentMethodModel mapToEntity(PaymentMethodModel from) {
    PaymentMethodModel to = new PaymentMethodModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(PaymentMethodModel from, PaymentMethodModel to) {
    to.setPaymentId(from.getPaymentId());
    to.setCardNumber(from.getCardNumber());
    to.setCardHolder(from.getCardHolder());
    to.setExpirationDate(from.getExpirationDate());
    to.setCustomerId(from.getCustomerId());
  }

  private boolean checkFilter(PaymentMethodModel paymentMethodModel, String value, String column) {
    return switch (column.toLowerCase()) {
      case "id" -> paymentMethodModel.getId() == Integer.parseInt(value);
      case "payment_id" -> paymentMethodModel.getPaymentId().toLowerCase().contains(value.toLowerCase());
      case "card_number" -> paymentMethodModel.getCardNumber().toLowerCase().contains(value.toLowerCase());
      case "card_holder" -> paymentMethodModel.getCardHolder().toLowerCase().contains(value.toLowerCase());
      case "expiration_date" -> paymentMethodModel.getExpirationDate().equals(Date.parse(value));
      case "customer_id" -> paymentMethodModel.getCustomerId() == Integer.parseInt(value);
      default -> checkAllColumns(paymentMethodModel, value);
    };
  }

  private boolean checkAllColumns(PaymentMethodModel paymentMethodModel, String value) {
    return paymentMethodModel.getId() == Integer.parseInt(value)
        || paymentMethodModel.getPaymentId().toLowerCase().contains(value.toLowerCase())
        || paymentMethodModel.getCardNumber().toLowerCase().contains(value.toLowerCase())
        || paymentMethodModel.getCardHolder().toLowerCase().contains(value.toLowerCase())
        || paymentMethodModel.getExpirationDate().equals(Date.parse(value))
        || paymentMethodModel.getCustomerId() == Integer.parseInt(value);
  }

  @Override
  public int addModel(PaymentMethodModel paymentMethodModel) throws SQLException, ClassNotFoundException {
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

    int id = PaymentMethodDAO.getInstance().insert(mapToEntity(paymentMethodModel));
    paymentMethodModel.setId(id);
    paymentMethodList.add(paymentMethodModel);
    return id;
  }

  @Override
  public int updateModel(PaymentMethodModel paymentMethodModel) throws SQLException, ClassNotFoundException {
    int updatedRows = PaymentMethodDAO.getInstance().update(paymentMethodModel);
    if (updatedRows > 0) {
      for (int i = 0; i < paymentMethodList.size(); i++) {
        if (paymentMethodList.get(i).getId() == paymentMethodModel.getId()) {
          paymentMethodList.set(i, paymentMethodModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
    PaymentMethodModel paymentMethodModel = getModelById(id);
    if (paymentMethodModel == null) {
      throw new IllegalArgumentException("Payment method with ID " + id + " does not exist.");
    }
    int deletedRows = PaymentMethodDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      paymentMethodList.remove(paymentMethodModel);
    }
    return deletedRows;
  }

  @Override
  public List<PaymentMethodModel> searchModel(String value, String columns)
      throws SQLException, ClassNotFoundException {
    List<PaymentMethodModel> results = new ArrayList<>();
    try {
      List<PaymentMethodModel> entities = PaymentMethodDAO.getInstance().search(value, columns);
      for (PaymentMethodModel entity : entities) {
        PaymentMethodModel model = mapToEntity(entity);
        if (checkFilter(model, value, columns)) {
          results.add(model);
        }
      }
    } catch (SQLException e) {
      throw new SQLException("Failed to search for payment method: " + e.getMessage());
    } catch (ClassNotFoundException e) {
      throw new ClassNotFoundException("Failed to search for payment method: " + e.getMessage());
    }

    if (results.isEmpty()) {
      throw new IllegalArgumentException("No payment method found with the specified search criteria.");
    }

    return results;
  }
}
