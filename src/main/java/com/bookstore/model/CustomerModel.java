package com.bookstore.model;

import java.sql.Date;

public class CustomerModel {
  private Date purchaseHistory;
  private String customerId, invoiceId, paymentId;

  public CustomerModel() {
  }

  public CustomerModel(Date purchaseHistory, String customerId, String invoiceId, String paymentId) {
    this.purchaseHistory = purchaseHistory;
    this.customerId = customerId;
    this.invoiceId = invoiceId;
    this.paymentId = paymentId;
  }

  public Date getPurchaseHistory() {
    return purchaseHistory;
  }

  public void setPurchaseHistory(Date purchaseHistory) {
    this.purchaseHistory = purchaseHistory;
  }

  public String getCustomerId() {
    return customerId;
  }

  public void setCustomerId(String customerId) {
    this.customerId = customerId;
  }

  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }

  public String getPaymentId() {
    return paymentId;
  }

  public void setPaymentId(String paymentId) {
    this.paymentId = paymentId;
  }

}