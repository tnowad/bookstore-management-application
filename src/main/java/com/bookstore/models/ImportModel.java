package com.bookstore.models;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class ImportModel {
  private int id;
  private int providerId;
  private int employeeId;
  private BigDecimal totalPrice;
  private Timestamp createdAt;
  private Timestamp updatedAt;

  public ImportModel() {
  }

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

  public void setId(int id) {
    this.id = id;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public void setUpdatedAt(Timestamp updatedAt) {
    this.updatedAt = updatedAt;
  }

  public ImportModel id(int id) {
    setId(id);
    return this;
  }

  public ImportModel providerId(int providerId) {
    setProviderId(providerId);
    return this;
  }

  public ImportModel employeeId(int employeeId) {
    setEmployeeId(employeeId);
    return this;
  }

  public ImportModel totalPrice(BigDecimal totalPrice) {
    setTotalPrice(totalPrice);
    return this;
  }

  public ImportModel createdAt(Timestamp createdAt) {
    setCreatedAt(createdAt);
    return this;
  }

  public ImportModel updatedAt(Timestamp updatedAt) {
    setUpdatedAt(updatedAt);
    return this;
  }
}