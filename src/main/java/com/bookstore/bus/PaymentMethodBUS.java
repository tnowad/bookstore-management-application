package com.bookstore.bus;

import com.bookstore.dao.PaymentMethodDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.PaymentMethodModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PaymentMethodBUS implements IBUS<PaymentMethodModel> {

  private final List<PaymentMethodModel> paymentMethodList = new ArrayList<>();
  private static PaymentMethodBUS instance;

  public static PaymentMethodBUS getInstance() {
    if (instance == null) {
      instance = new PaymentMethodBUS();
    }
    return instance;
  }

  private PaymentMethodBUS() {
    this.paymentMethodList.addAll(
        PaymentMethodDAO.getInstance().readDatabase()
      );
  }

  @Override
  public List<PaymentMethodModel> getAllModels() {
    return Collections.unmodifiableList(paymentMethodList);
  }

  @Override
  public PaymentMethodModel getModelById(int id) {
    for (PaymentMethodModel paymentMethodModel : paymentMethodList) {
      if (paymentMethodModel.getId() == id) {
        return paymentMethodModel;
      }
    }
    return null;
  }

  private PaymentMethodModel mapToEntity(PaymentMethodModel from) {
    PaymentMethodModel to = new PaymentMethodModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(
    PaymentMethodModel from,
    PaymentMethodModel to
  ) {
    to.setId(from.getId());
    to.setPaymentId(from.getPaymentId());
    to.setCardNumber(from.getCardNumber());
    to.setCardHolder(from.getCardHolder());
    to.setExpirationDate(from.getExpirationDate());
    to.setCustomerId(from.getCustomerId());
  }

  private boolean checkFilter(
    PaymentMethodModel paymentMethodModel,
    String value,
    String[] columns
  ) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (paymentMethodModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "payment_id" -> {
          if (
            paymentMethodModel
              .getPaymentId()
              .toLowerCase()
              .contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        case "card_number" -> {
          if (
            paymentMethodModel
              .getCardNumber()
              .toLowerCase()
              .contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        case "card_holder" -> {
          if (
            paymentMethodModel
              .getCardHolder()
              .toLowerCase()
              .contains(value.toLowerCase())
          ) {
            return true;
          }
        }
        case "expiration_date" -> {
          if (
            new SimpleDateFormat()
              .format(paymentMethodModel.getExpirationDate())
              .equals(value)
          ) {
            return true;
          }
        }
        case "customer_id" -> {
          if (paymentMethodModel.getCustomerId() == Integer.parseInt(value)) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(paymentMethodModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(
    PaymentMethodModel paymentMethodModel,
    String value
  ) {
    return (
      paymentMethodModel.getId() == Integer.parseInt(value) ||
      paymentMethodModel
        .getPaymentId()
        .toLowerCase()
        .contains(value.toLowerCase()) ||
      paymentMethodModel
        .getCardNumber()
        .toLowerCase()
        .contains(value.toLowerCase()) ||
      paymentMethodModel
        .getCardHolder()
        .toLowerCase()
        .contains(value.toLowerCase()) ||
      new SimpleDateFormat()
        .format(paymentMethodModel.getExpirationDate())
        .equals(value) ||
      paymentMethodModel.getCustomerId() == Integer.parseInt(value)
    );
  }

  @Override
  public int addModel(PaymentMethodModel paymentMethodModel) {
    if (
      paymentMethodModel.getPaymentId() == null ||
      paymentMethodModel.getPaymentId().isEmpty()
    ) {
      throw new IllegalArgumentException("Payment ID cannot be null or empty!");
    }
    if (
      paymentMethodModel.getCardNumber() == null ||
      paymentMethodModel.getCardNumber().isEmpty()
    ) {
      throw new IllegalArgumentException(
        "Card number cannot be null or empty!"
      );
    }
    if (
      paymentMethodModel.getCardHolder() == null ||
      paymentMethodModel.getCardHolder().isEmpty()
    ) {
      throw new IllegalArgumentException(
        "Card holder cannot be null or empty!"
      );
    }
    if (paymentMethodModel.getExpirationDate() == null) {
      throw new IllegalArgumentException("Expiration date cannot be null!");
    }

    int id = PaymentMethodDAO
      .getInstance()
      .insert(mapToEntity(paymentMethodModel));
    paymentMethodModel.setId(id);
    paymentMethodList.add(paymentMethodModel);
    return id;
  }

  @Override
  public int updateModel(PaymentMethodModel paymentMethodModel) {
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
  public int deleteModel(int id) {
    PaymentMethodModel paymentMethodModel = getModelById(id);
    if (paymentMethodModel == null) {
      throw new IllegalArgumentException(
        "Payment method with ID " + id + " does not exist."
      );
    }
    int deletedRows = PaymentMethodDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      paymentMethodList.remove(paymentMethodModel);
    }
    return deletedRows;
  }

  @Override
  public List<PaymentMethodModel> searchModel(String value, String[] columns) {
    List<PaymentMethodModel> results = new ArrayList<>();

    List<PaymentMethodModel> entities = PaymentMethodDAO
      .getInstance()
      .search(value, columns);
    for (PaymentMethodModel entity : entities) {
      PaymentMethodModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }

  @Override
  public void refreshData() {
    throw new UnsupportedOperationException(
      "Unimplemented method 'refreshData'"
    );
  }
}
