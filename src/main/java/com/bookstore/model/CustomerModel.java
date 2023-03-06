package com.bookstore.model;

import java.sql.Date;

public class CustomerModel {
  private Date purchaseHistory;
  private String customerID, invoiceID, paymentID;

  public CustomerModel() {
  }

  public CustomerModel(Date purchaseHistory, String customerID, String invoiceID, String paymentID) {
    this.purchaseHistory = purchaseHistory;
    this.customerID = customerID;
    this.invoiceID = invoiceID;
    this.paymentID = paymentID;
  }

  public Date getPurchaseHistory() {
    return this.purchaseHistory;
  }

  public void setPurchaseHistory(Date purchaseHistory) {
    this.purchaseHistory = purchaseHistory;
  }

  public String getCustomerID() {
    return this.customerID;
  }

  public void setCustomerID(String customerID) {
    this.customerID = customerID;
  }

  public String getInvoiceID() {
    return this.invoiceID;
  }

  public void setInvoiceID(String invoiceID) {
    this.invoiceID = invoiceID;
  }

  public String getPaymentID() {
    return this.paymentID;
  }

  public void setPaymentID(String paymentID) {
    this.paymentID = paymentID;
  }

  public CustomerModel purchaseHistory(Date purchaseHistory) {
    setPurchaseHistory(purchaseHistory);
    return this;
  }

  public CustomerModel customerID(String customerID) {
    setCustomerID(customerID);
    return this;
  }

  public CustomerModel invoiceID(String invoiceID) {
    setInvoiceID(invoiceID);
    return this;
  }

  public CustomerModel paymentID(String paymentID) {
    setPaymentID(paymentID);
    return this;
  }
}