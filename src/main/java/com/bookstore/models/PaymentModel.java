package com.bookstore.models;

import com.bookstore.enums.PaymentMethod;
import com.bookstore.enums.PaymentStatus;
import java.time.LocalDateTime;

public class PaymentModel {

  private int id;
  private int orderId;
  private int userId;
  private int amount;
  private PaymentMethod paymentMethod;
  private int paymentMethodId;
  private PaymentStatus status;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public PaymentModel() {}

  public PaymentModel(
    int id,
    int orderId,
    int userId,
    int amount,
    PaymentMethod paymentMethod,
    int paymentMethodId,
    PaymentStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
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
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOrderId() {
    return this.orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getAmount() {
    return this.amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public PaymentMethod getPaymentMethod() {
    return this.paymentMethod;
  }

  public void setPaymentMethod(PaymentMethod paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public int getPaymentMethodId() {
    return this.paymentMethodId;
  }

  public void setPaymentMethodId(int paymentMethodId) {
    this.paymentMethodId = paymentMethodId;
  }

  public PaymentStatus getStatus() {
    return this.status;
  }

  public void setStatus(PaymentStatus status) {
    this.status = status;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public PaymentModel id(int id) {
    setId(id);
    return this;
  }

  public PaymentModel orderId(int orderId) {
    setOrderId(orderId);
    return this;
  }

  public PaymentModel userId(int userId) {
    setUserId(userId);
    return this;
  }

  public PaymentModel amount(int amount) {
    setAmount(amount);
    return this;
  }

  public PaymentModel paymentMethod(PaymentMethod paymentMethod) {
    setPaymentMethod(paymentMethod);
    return this;
  }

  public PaymentModel paymentMethodId(int paymentMethodId) {
    setPaymentMethodId(paymentMethodId);
    return this;
  }

  public PaymentModel status(PaymentStatus status) {
    setStatus(status);
    return this;
  }

  public PaymentModel createdAt(LocalDateTime createdAt) {
    setCreatedAt(createdAt);
    return this;
  }

  public PaymentModel updatedAt(LocalDateTime updatedAt) {
    setUpdatedAt(updatedAt);
    return this;
  }
}
