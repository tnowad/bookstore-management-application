package com.bookstore.models;

import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import java.time.LocalDateTime;

public class UserModel {

  private int id;
  private String username;
  private String password;
  private UserStatus status;
  private String name;
  private String email;
  private String phone;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private UserRole role;

  public UserModel() {}

  public UserModel(
    int id,
    String username,
    String password,
    UserStatus status,
    String name,
    String email,
    String phone,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    UserRole role
  ) {
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
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
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

  public UserStatus getStatus() {
    return this.status;
  }

  public void setStatus(UserStatus status) {
    this.status = status;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
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

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  public UserRole getRole() {
    return this.role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public UserModel id(int id) {
    setId(id);
    return this;
  }

  public UserModel username(String username) {
    setUsername(username);
    return this;
  }

  public UserModel password(String password) {
    setPassword(password);
    return this;
  }

  public UserModel status(UserStatus status) {
    setStatus(status);
    return this;
  }

  public UserModel name(String name) {
    setName(name);
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

  public UserModel createdAt(LocalDateTime createdAt) {
    setCreatedAt(createdAt);
    return this;
  }

  public UserModel updatedAt(LocalDateTime updatedAt) {
    setUpdatedAt(updatedAt);
    return this;
  }

  public UserModel role(UserRole role) {
    setRole(role);
    return this;
  }

  @Override
  public String toString() {
    return String.format(
      "id: %d\nusername: %s\npassword: %s\nstatus: %s\nname: %s\nemail: %s\nphone: %s\ncreatedAt: %s\nupdatedAt: %s\nrole: %s",
      id,
      username,
      password,
      status,
      name,
      email,
      phone,
      createdAt,
      updatedAt,
      role
    );
  }
}
