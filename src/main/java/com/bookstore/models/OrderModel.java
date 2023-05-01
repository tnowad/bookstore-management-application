package com.bookstore.models;

import java.time.LocalDateTime;

public class OrderModel {
  private int id;
  private int cartId;
  private int customerId;
  private int employeeId;
  private int total;
  private int paid;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private Status status;

  public enum Status {
    PENDING,
    REJECTED,
    SOLVED;
  }

  public OrderModel() {
  }

  public OrderModel(int id, int cartId, int customerId, int employeeId, int total, int paid, LocalDateTime createdAt,
      LocalDateTime updatedAt, Status status) {
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
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCartId() {
    return this.cartId;
  }

  public void setCartId(int cartId) {
    this.cartId = cartId;
  }

  public int getCustomerId() {
    return this.customerId;
  }

  public void setCustomerId(int customerId) {
    this.customerId = customerId;
  }

  public int getEmployeeId() {
    return this.employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public int getTotal() {
    return this.total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public int getPaid() {
    return this.paid;
  }

  public void setPaid(int paid) {
    this.paid = paid;
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

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public OrderModel id(int id) {
    setId(id);
    return this;
  }

  public OrderModel cartId(int cartId) {
    setCartId(cartId);
    return this;
  }

  public OrderModel customerId(int customerId) {
    setCustomerId(customerId);
    return this;
  }

  public OrderModel employeeId(int employeeId) {
    setEmployeeId(employeeId);
    return this;
  }

  public OrderModel total(int total) {
    setTotal(total);
    return this;
  }

  public OrderModel paid(int paid) {
    setPaid(paid);
    return this;
  }

  public OrderModel createdAt(LocalDateTime createdAt) {
    setCreatedAt(createdAt);
    return this;
  }

  public OrderModel updatedAt(LocalDateTime updatedAt) {
    setUpdatedAt(updatedAt);
    return this;
  }

  public OrderModel status(Status status) {
    setStatus(status);
    return this;
  }

}
