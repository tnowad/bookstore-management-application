package com.bookstore.models;

import java.time.LocalDateTime;

public class ImportModel {

  private int id;
  private int providerId;
  private int employeeId;
  private Double totalPrice;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  public ImportModel() {}

  public ImportModel(
    int id,
    int providerId,
    int employeeId,
    Double totalPrice,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
  ) {
    this.id = id;
    this.providerId = providerId;
    this.employeeId = employeeId;
    this.totalPrice = totalPrice;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getProviderId() {
    return this.providerId;
  }

  public void setProviderId(int providerId) {
    this.providerId = providerId;
  }

  public int getEmployeeId() {
    return this.employeeId;
  }

  public void setEmployeeId(int employeeId) {
    this.employeeId = employeeId;
  }

  public Double getTotalPrice() {
    return this.totalPrice;
  }

  public void setTotalPrice(Double totalPrice) {
    this.totalPrice = totalPrice;
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

  public ImportModel totalPrice(Double totalPrice) {
    setTotalPrice(totalPrice);
    return this;
  }

  public ImportModel createdAt(LocalDateTime createdAt) {
    setCreatedAt(createdAt);
    return this;
  }

  public ImportModel updatedAt(LocalDateTime updatedAt) {
    setUpdatedAt(updatedAt);
    return this;
  }
}
