package com.bookstore.models;

import java.time.LocalDate;

public class PaymentMethodModel {

  private int id;
  private String paymentId;
  private String cardNumber;
  private String cardHolder;
  private LocalDate expirationDate;
  private int customerId;

  public PaymentMethodModel() {}

  public PaymentMethodModel(
    int id,
    String paymentId,
    String cardNumber,
    String cardHolder,
    LocalDate expirationDate,
    int customerId
  ) {
    this.id = id;
    this.paymentId = paymentId;
    this.cardNumber = cardNumber;
    this.cardHolder = cardHolder;
    this.expirationDate = expirationDate;
    this.customerId = customerId;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getPaymentId() {
    return this.paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

  public String getCardNumber() {
    return this.cardNumber;
  }

  public void setCardNumber(String cardNumber) {
    this.cardNumber = cardNumber;
  }

  public String getCardHolder() {
    return this.cardHolder;
  }

  public void setCardHolder(String cardHolder) {
    this.cardHolder = cardHolder;
  }

  public LocalDate getExpirationDate() {
    return this.expirationDate;
  }

  public void setExpirationDate(LocalDate expirationDate) {
    this.expirationDate = expirationDate;
  }

  public int getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public PaymentMethodModel id(int id) {
    setId(id);
    return this;
  }

  public PaymentMethodModel paymentId(String paymentId) {
    setPaymentId(paymentId);
    return this;
  }

  public PaymentMethodModel cardNumber(String cardNumber) {
    setCardNumber(cardNumber);
    return this;
  }

  public PaymentMethodModel cardHolder(String cardHolder) {
    setCardHolder(cardHolder);
    return this;
  }

  public PaymentMethodModel expirationDate(LocalDate expirationDate) {
    setExpirationDate(expirationDate);
    return this;
  }

  public PaymentMethodModel customerId(int customerId) {
    setCustomerId(customerId);
    return this;
  }
}
