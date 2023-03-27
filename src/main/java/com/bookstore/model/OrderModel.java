package com.bookstore.model;

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

  public void setCartId(int cart_id) {
    this.cartId = cart_id;
  }

  public int getCustomerId() {
    return customerId;
  }

  public void setCustomerId(int customer_id) {
    this.customerId = customer_id;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employee_id) {
    this.employeeId = employee_id;
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

  public void setCreatedAt(Timestamp created_at) {
    this.createdAt = created_at;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Timestamp updated_at) {
    this.updatedAt = updated_at;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

}
