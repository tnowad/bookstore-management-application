package com.bookstore.model;

import java.sql.Date;

public class CustomerModel {
  private Date purchaseHistory;
  private String userID, invoiceID, paymentID;

  public CustomerModel() {
  }

  public CustomerModel(Date purchaseHistory, String userID, String invoiceID, String paymentID) {
    this.purchaseHistory = purchaseHistory;
    this.userID = userID;
    this.invoiceID = invoiceID;
    this.paymentID = paymentID;
  }

  public Date getPurchaseHistory() {
    return this.purchaseHistory;
  }

  public void setPurchaseHistory(Date purchaseHistory) {
    this.purchaseHistory = purchaseHistory;
  }

  public String getUserID() {
    return this.userID;
  }

  public void setUserID(String userID) {
    this.userID = userID;
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

  public CustomerModel userID(String userID) {
    setUserID(userID);
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

  @Override
  public String toString() {
    return "{" +
        " purchaseHistory='" + getPurchaseHistory() + "'" +
        ", userID='" + getUserID() + "'" +
        ", invoiceID='" + getInvoiceID() + "'" +
        ", paymentID='" + getPaymentID() + "'" +
        "}";
  }
}