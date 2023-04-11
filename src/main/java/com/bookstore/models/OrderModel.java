package com.bookstore.models;

import java.sql.Timestamp;

public class OrderModel {
  private int id;
  private int cartId;
  private int customerId;
  private int employeeId;
  private int total;
  private int paid;
  private Timestamp createdAt;
  private Timestamp updatedAt;
  private Status status;

  public enum Status {
    PENDING,
    SOLVED;
  }

  public OrderModel() {
  }

  public OrderModel(int cartId, int customerId, int employeeId, int total, int paid, Timestamp createdAt,
      Timestamp updatedAt, Status status) {
    this.cartId = cartId;
    this.customerId = customerId;
    this.employeeId = employeeId;
    this.total = total;
    this.paid = paid;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.status = status;
  }

  public OrderModel(int id, int cartId, int customerId, int employeeId, int total, int paid, Timestamp createdAt,
      Timestamp updatedAt, Status status) {
    this.id = id;
    this.cartId = cartId;
    this.customerId = customerId;
    this.employeeId = employeeId;
    this.total = total;
    this.paid = paid;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCartId() {
    return cartId;
  }

  public void setCartId(int cartId) {
    this.cartId = cartId;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getPaid() {
    return paid;
  }

  public void setPaid(int paid) {
    this.paid = paid;
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

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
