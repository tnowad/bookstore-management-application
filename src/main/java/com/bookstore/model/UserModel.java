package com.bookstore.model;

public class UserModel {
  private String email, phoneNumber, id, name, role, accountType;

  public UserModel() {
  }

  public UserModel(String email, String phoneNumber, String id, String name, String role, String accountType) {
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.id = id;
    this.name = name;
    this.role = role;
    this.accountType = accountType;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

}
