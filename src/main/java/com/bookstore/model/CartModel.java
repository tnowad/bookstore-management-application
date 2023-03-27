package com.bookstore.model;

import java.sql.Timestamp;

public class CartModel {
  private int id;
  private int userId;
  private Timestamp createdAt;
  private Status status;
  private Timestamp expires;
  private int promotionId;

  public enum Status {
    SHOPPING,
    pending,
    reject,
    accept;
  }

  public CartModel() {
  }

  public CartModel(int id, int userId, Timestamp createdAt, Status status, Timestamp expires, Integer promotionId) {
    this.id = id;
    this.userId = userId;
    this.createdAt = createdAt;
    this.status = status;
    this.expires = expires;
    this.promotionId = promotionId;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public Timestamp getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Timestamp createdAt) {
    this.createdAt = createdAt;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public Timestamp getExpires() {
    return expires;
  }

  public void setExpires(Timestamp expires) {
    this.expires = expires;
  }

  public Integer getPromotionId() {
    return promotionId;
  }

  public void setPromotionId(Integer promotionId) {
    this.promotionId = promotionId;
  }
}
