package com.bookstore.bus;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.dao.PaymentDAO;
import com.bookstore.interfaces.IBUS;
import com.bookstore.model.PaymentModel;
import com.bookstore.model.PaymentModel.PaymentMethod;
import com.bookstore.model.PaymentModel.PaymentStatus;

public class PaymentBUS implements IBUS<PaymentModel> {

  private final List<PaymentModel> paymentList = new ArrayList<>();
  private static PaymentBUS instance;

  public static PaymentBUS getInstance() throws ClassNotFoundException, SQLException {
    if (instance == null) {
      instance = new PaymentBUS();
    }
    return instance;
  }

  private PaymentBUS() throws SQLException, ClassNotFoundException {
    this.paymentList.addAll(PaymentDAO.getInstance().readDatabase());
  }

  @Override
  public List<PaymentModel> getAllModels() {
    return Collections.unmodifiableList(paymentList);
  }

  @Override
  public PaymentModel getModelById(int id) throws SQLException, ClassNotFoundException {
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

  @Override
  public int addModel(PaymentModel paymentModel) throws SQLException, ClassNotFoundException {
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
        paymentModel.getPaymentMethod() != null ? paymentModel.getPaymentMethod() : PaymentMethod.cash);
    paymentModel.setStatus(paymentModel.getStatus() != null ? paymentModel.getStatus() : PaymentStatus.pending);

    int id = PaymentDAO.getInstance().insert(mapToEntity(paymentModel));
    paymentModel.setId(id);
    paymentList.add(paymentModel);
    return id;
  }

  @Override
  public int updateModel(PaymentModel paymentModel) throws SQLException, ClassNotFoundException {
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

  public int updateStatus(int orderId, PaymentStatus status) throws ClassNotFoundException, SQLException {
    int success = PaymentDAO.getInstance().updateStatus(orderId, status);
    if (success == 1) {
      for (PaymentModel payment : paymentList) {
        if (payment.getOrderId() == orderId) {
          payment.setStatus(status);
          payment.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
          return 1;
        }
      }
    }
    return 0;
  }

  @Override
  public int deleteModel(int id) throws SQLException, ClassNotFoundException {
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
  public List<PaymentModel> searchModel(String value, String[] columns) throws SQLException, ClassNotFoundException {
    throw new UnsupportedOperationException("Search is not supported for PaymentBUS.");
  }

}
