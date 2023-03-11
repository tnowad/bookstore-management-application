package com.bookstore.model;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ImportModel {
  private int id;
  private int providerId;
  private int employeeId;
  private BigDecimal totalPrice;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  public ImportModel(int id, int providerId, int employeeId, BigDecimal totalPrice, Timestamp createdAt,
      Timestamp updatedAt) {
    this.id = id;
    this.providerId = providerId;
    this.employeeId = employeeId;
    this.totalPrice = totalPrice;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public int getId() {
    return id;
  }

  public int getProviderId() {
    return providerId;
  }

  public void setProviderId(int providerId) {
    this.providerId = providerId;
  }

  public int getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public BigDecimal getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(BigDecimal totalPrice) {
    this.totalPrice = totalPrice;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public Timestamp getUpdatedAt() {
    return updatedAt;
  }
}
