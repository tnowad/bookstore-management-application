package com.bookstore.model;

public class AccountModel {
  private String username, password, status, accountId;

  public AccountModel() {
  }

  public AccountModel(String username, String password, String status, String accountId) {
    this.username = username;
    this.password = password;
    this.status = status;
    this.accountId = accountId;
  }

  public String getUsername() {
    return this.username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return this.password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getStatus() {
    return this.status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getAccountId() {
    return this.accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public AccountModel username(String username) {
    setUsername(username);
    return this;
  }

  public AccountModel password(String password) {
    setPassword(password);
    return this;
  }

  public AccountModel status(String status) {
    setStatus(status);
    return this;
  }

  public AccountModel accountId(String accountId) {
    setAccountId(accountId);
    return this;
  }

}
