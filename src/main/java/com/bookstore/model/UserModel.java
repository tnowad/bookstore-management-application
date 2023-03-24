package com.bookstore.model;

import java.sql.Timestamp;

public class UserModel {
  private int id;
  private String username;
  private String password;
  private Status status;
  private String name;
  private String email;
  private String phone;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private Role role;

  public enum Status {
    ACTIVE,
    INACTIVE,
    BANNED,
  }

  public enum Role {
    CUSTOMER,
    EMPLOYEE,
    ADMIN
  }

  public UserModel() {
  }

  public UserModel(int id, String username, String password, Status status, String name, String email, String phone,
      Timestamp createdAt, Timestamp updatedAt, Role role) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.status = status;
    this.name = name;
    this.email = email;
    this.phone = phone;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.role = role;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

}
