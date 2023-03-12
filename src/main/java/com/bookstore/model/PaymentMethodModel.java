package com.bookstore.model;

import java.time.LocalDate;

public class PaymentMethodModel {
  private int id;
  private String paymentId; // using string since it has NVARCHAR datatype in DB
  private String cardNumber;
  private String cardHolder;
  private LocalDate expirationDate;
  private int customerId;

  public PaymentMethodModel() {
  }

  public PaymentMethodModel(String paymentId, String cardNumber, String cardHolder,
      LocalDate expirationDate, int customerId) {
    this.paymentId = paymentId;
    this.cardNumber = cardNumber;
    this.cardHolder = cardHolder;
    this.expirationDate = expirationDate;
    this.customerId = customerId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getCardNumber() {
    return cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardHolder() {
    return cardHolder;
  }

  public void setCardHolder(String cardHolder) {
    this.cardHolder = cardHolder;
  }

  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }
}
