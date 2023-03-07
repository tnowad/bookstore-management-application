package com.bookstore.model;

public class CustomerModel {
  private String accountId, address;

  public CustomerModel() {
  }

  public CustomerModel(String accountId, String address) {
    this.accountId = accountId;
    this.address = address;
  }

  public String getAccountId() {
    return this.accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getAddress() {
    return this.address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public CustomerModel accountId(String accountId) {
    setAccountId(accountId);
    return this;
  }

  public CustomerModel address(String address) {
    setAddress(address);
    return this;
  }

}