package com.bookstore.bus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.PaymentDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.models.PaymentModel;
import com.bookstore.models.PaymentModel.PaymentMethod;
import com.bookstore.models.PaymentModel.PaymentStatus;

public class PaymentBUS implements IBUS<PaymentModel> {

  private final List<PaymentModel> paymentList = new ArrayList<>();
  private static PaymentBUS instance;

  public static PaymentBUS getInstance() {
    if (instance == null) {
      instance = new PaymentBUS();
    }
    return instance;
  }

  private PaymentBUS() {
    this.paymentList.addAll(PaymentDAO.getInstance().readDatabase());
  }

  @Override
  public List<PaymentModel> getAllModels() {
    return Collections.unmodifiableList(paymentList);
  }

  @Override
  public PaymentModel getModelById(int id) {
    for (PaymentModel paymentModel : paymentList) {
      if (paymentModel.getId() == id) {
        return paymentModel;
      }
    }
    return null;
  }

  private PaymentModel mapToEntity(PaymentModel from) {
    PaymentModel to = new PaymentModel();
    updateEntityFields(from, to);
    return to;
  }

  private void updateEntityFields(PaymentModel from, PaymentModel to) {
    to.setId(from.getId());
    to.setOrderId(from.getOrderId());
    to.setUserId(from.getUserId());
    to.setAmount(from.getAmount());
    to.setPaymentMethod(from.getPaymentMethod());
    to.setPaymentMethodId(from.getPaymentMethodId());
    to.setStatus(from.getStatus());
    to.setCreatedAt(from.getCreatedAt());
    to.setUpdatedAt(from.getUpdatedAt());
  }

  private boolean checkFilter(PaymentModel paymentModel, String value, String[] columns) {
    for (String column : columns) {
      switch (column.toLowerCase()) {
        case "id" -> {
          if (paymentModel.getId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "order_id" -> {
          if (paymentModel.getOrderId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "user_id" -> {
          if (paymentModel.getUserId() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "amount" -> {
          if (paymentModel.getAmount() == Integer.parseInt(value)) {
            return true;
          }
        }
        case "payment_method" -> {
          if (paymentModel.getPaymentMethod().toString().equals(value)) {
            return true;
          }
        }
        case "payment_method_id" -> {
          if (paymentModel.getPaymentMethodId() == Integer.parseInt(value)) {
            return true;
          }
        }
        default -> {
          if (checkAllColumns(paymentModel, value)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private boolean checkAllColumns(PaymentModel paymentModel, String value) {
    return paymentModel.getId() == Integer.parseInt(value)
        || paymentModel.getUserId() == Integer.parseInt(value)
        || paymentModel.getAmount() == Integer.parseInt(value)
        || paymentModel.getPaymentMethod().toString().equals(value)
        || paymentModel.getPaymentMethodId() == Integer.parseInt(value)
        || paymentModel.getStatus().toString().equals(value);
  }

  @Override
  public int addModel(PaymentModel paymentModel) {
    if (paymentModel.getOrderId() <= 0) {
      throw new IllegalArgumentException("Order ID must be greater than 0!");
    }
    if (paymentModel.getUserId() <= 0) {
      throw new IllegalArgumentException("User ID must be greater than 0!");
    }
    if (paymentModel.getAmount() <= 0) {
      throw new IllegalArgumentException("Amount must be greater than 0!");
    }
    if (paymentModel.getPaymentMethodId() <= 0) {
      throw new IllegalArgumentException("Payment method ID must be greater than 0!");
    }

    paymentModel.setPaymentMethod(
        paymentModel.getPaymentMethod() != null ? paymentModel.getPaymentMethod() : PaymentMethod.CASH);
    paymentModel.setStatus(paymentModel.getStatus() != null ? paymentModel.getStatus() : PaymentStatus.PENDING);

    int id = PaymentDAO.getInstance().insert(mapToEntity(paymentModel));
    paymentModel.setId(id);
    paymentList.add(paymentModel);
    return id;
  }

  @Override
  public int updateModel(PaymentModel paymentModel) {
    int updatedRows = PaymentDAO.getInstance().update(paymentModel);
    if (updatedRows > 0) {
      for (int i = 0; i < paymentList.size(); i++) {
        if (paymentList.get(i).getOrderId() == paymentModel.getOrderId()) {
          paymentList.set(i, paymentModel);
          break;
        }
      }
    }
    return updatedRows;
  }

  public int updateStatus(int orderId, String status) {
    int success = PaymentDAO.getInstance().updateStatus(orderId, status);
    int numUpdated = 0;
    if (success == 1) {
      for (PaymentModel payment : paymentList) {
        if (payment.getOrderId() == orderId) {
          PaymentStatus roleEnum = PaymentStatus.valueOf(status.toUpperCase());
          payment.setStatus(roleEnum);
          numUpdated++;
        }
      }
    }
    return numUpdated;
  }

  @Override
  public int deleteModel(int id) {
    PaymentModel paymentModel = getModelById(id);
    if (paymentModel == null) {
      throw new IllegalArgumentException("Payment with ID " + id + " does not exist.");
    }
    int deletedRows = PaymentDAO.getInstance().delete(id);
    if (deletedRows > 0) {
      paymentList.remove(paymentModel);
    }
    return deletedRows;
  }

  @Override
  public List<PaymentModel> searchModel(String value, String[] columns) {
    List<PaymentModel> results = new ArrayList<>();
    List<PaymentModel> entities = PaymentDAO.getInstance().search(value, columns);
    for (PaymentModel entity : entities) {
      PaymentModel model = mapToEntity(entity);
      if (checkFilter(model, value, columns)) {
        results.add(model);
      }
    }

    return results;
  }
}
