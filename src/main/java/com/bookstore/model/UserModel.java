package com.bookstore.model;

import java.sql.Date;

public class UserModel {
  private String accountId, firstName, lastName, email, phone, role;
  private Date createdAt, updatedAt;

  public UserModel() {
  }

  public UserModel(String accountId, String firstName, String lastName, String email, String phone, String role,
      Date createdAt, Date updatedAt) {
    this.accountId = accountId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.phone = phone;
    this.role = role;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public String getAccountId() {
    return this.accountId;
  }

  public void setAccountId(String accountId) {
    this.accountId = accountId;
  }

  public String getFirstName() {
    return this.firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return this.lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return this.email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return this.phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getRole() {
    return this.role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public Date getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  public UserModel accountId(String accountId) {
    setAccountId(accountId);
    return this;
  }

  public UserModel firstName(String firstName) {
    setFirstName(firstName);
    return this;
  }

  public UserModel lastName(String lastName) {
    setLastName(lastName);
    return this;
  }

  public UserModel email(String email) {
    setEmail(email);
    return this;
  }

  public UserModel phone(String phone) {
    setPhone(phone);
    return this;
  }

  public UserModel role(String role) {
    setRole(role);
    return this;
  }

  public UserModel createdAt(Date createdAt) {
    setCreatedAt(createdAt);
    return this;
  }

  public UserModel updatedAt(Date updatedAt) {
    setUpdatedAt(updatedAt);
    return this;
  }
}
