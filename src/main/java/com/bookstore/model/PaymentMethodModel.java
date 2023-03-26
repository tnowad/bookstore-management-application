package com.bookstore.model;

import java.util.Date;

public class PaymentMethodModel {
  private int id;
  private String paymentId;
  private String cardNumber;
  private String cardHolder;
  private Date expirationDate;
  private int customerId;

  public PaymentMethodModel() {
  }

  public PaymentMethodModel(String paymentId, String cardNumber, String cardHolder,
      Date expirationDate, int customerId) {
    this.paymentId = paymentId;
    this.cardNumber = cardNumber;
    this.cardHolder = cardHolder;
    this.expirationDate = expirationDate;
    this.customerId = customerId;
  }

  public PaymentMethodModel(int id, String paymentId, String cardNumber, String cardHolder,
      Date expirationDate,
      int customerId) {
    this.id = id;
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

  public Date getExpirationDate() {
    return expirationDate;
  }

  public void setExpirationDate(Date expirationDate) {
    this.expirationDate = expirationDate;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }
}
