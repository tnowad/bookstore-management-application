package com.bookstore.model;

import java.sql.Date;

public class CustomerModel {
  private Date purchaseHistory;
  private String customerId;

  public CustomerModel() {
  }

  public CustomerModel(Date purchaseHistory, String customerID) {
    this.purchaseHistory = purchaseHistory;
    this.customerId = customerId;
  }

  public Date getPurchaseHistory() {
    return this.purchaseHistory;
  }

  public void setPurchaseHistory(Date PurchaseHistory) {
    this.purchaseHistory = purchaseHistory;
  }

  public String getCustomerID() {
    return this.customerId;
  }

  public void setCustomerID(String customerId) {
    this.customerId = customerId;
  }

  public CustomerModel purchaseHistory(Date purchaseHistory) {
    setPurchaseHistory(purchaseHistory);
    return this;
  }

  public CustomerModel customerID(String customerID) {
    setCustomerID(customerID);
    return this;
  }
}