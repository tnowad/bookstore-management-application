package com.bookstore.model;

import java.sql.Timestamp;

public class OrderModel {
  private int id;
  private int cart_id;
  private int customer_id;
  private int employee_id;
  private int total;
  private int paid;
  private Timestamp created_at;
  private Timestamp updated_at;
  private Status status;

  public enum Status {
    PENDING,
    SOLVED;
  }

  public OrderModel() {
  }

  public OrderModel(int cart_id, int customer_id, int employee_id, int total, int paid, Timestamp created_at,
      Timestamp updated_at, Status status) {
    this.cart_id = cart_id;
    this.customer_id = customer_id;
    this.employee_id = employee_id;
    this.total = total;
    this.paid = paid;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.status = status;
  }

  public OrderModel(int id, int cart_id, int customer_id, int employee_id, int total, int paid, Timestamp created_at,
      Timestamp updated_at, Status status) {
    this.id = id;
    this.cart_id = cart_id;
    this.customer_id = customer_id;
    this.employee_id = employee_id;
    this.total = total;
    this.paid = paid;
    this.created_at = created_at;
    this.updated_at = updated_at;
    this.status = status;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getCart_id() {
    return cart_id;
  }

  public void setCart_id(int cart_id) {
    this.cart_id = cart_id;
  }

  public int getCustomer_id() {
    return customer_id;
  }

  public void setCustomer_id(int customer_id) {
    this.customer_id = customer_id;
  }

  public int getEmployee_id() {
    return employee_id;
  }

  public void setEmployee_id(int employee_id) {
    this.employee_id = employee_id;
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

  public Timestamp getCreated_at() {
    return created_at;
  }

  public void setCreated_at(Timestamp created_at) {
    this.created_at = created_at;
  }

  public Timestamp getUpdated_at() {
    return updated_at;
  }

  public void setUpdated_at(Timestamp updated_at) {
    this.updated_at = updated_at;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }
}
