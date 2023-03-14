package com.bookstore.model;

import java.sql.Timestamp;

public class PaymentModel {
  private int id;
  private int orderId;
  private int userId;
  private int amount;
  private PaymentMethod paymentMethod;
  private int paymentMethodId;
  private PaymentStatus status;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  public PaymentModel() {
  }

  public PaymentModel(int id, int orderId, int userId, int amount, PaymentMethod paymentMethod, int paymentMethodId,
      PaymentStatus status, Timestamp createdAt, Timestamp updatedAt) {
    super();
    this.id = id;
    this.orderId = orderId;
    this.userId = userId;
    this.amount = amount;
    this.paymentMethod = paymentMethod;
    this.paymentMethodId = paymentMethodId;
    this.status = status;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public PaymentMethod getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public int getPaymentMethodId() {
    return paymentMethodId;
  }

  public void setPaymentMethodId(int paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  public PaymentStatus getStatus() {
    return status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public enum PaymentMethod {
    CASH,
    CREDIT
  }

  public enum PaymentStatus {
    PENDING,
    SUCCESS,
    FAILED
  }

}
