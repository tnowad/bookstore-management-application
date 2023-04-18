package com.bookstore.models;

import java.time.LocalDateTime;

public class CartModel {
  private int id;
  private int userId;
  private LocalDateTime createdAt;
  private Status status;
  private LocalDateTime expires;
  private int promotionId;

  public enum Status {
    SHOPPING,
    PENDING,
    REJECTED,
    ACCEPTED;
  }

  public CartModel() {
  }

  public CartModel(int id, int userId, LocalDateTime createdAt, Status status, LocalDateTime expires, int promotionId) {
    this.id = id;
    this.userId = userId;
    this.createdAt = createdAt;
    this.status = status;
    this.expires = expires;
    this.promotionId = promotionId;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public Status getStatus() {
    return this.status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public LocalDateTime getExpires() {
    return this.expires;
  }

  public void setExpires(LocalDateTime expires) {
    this.expires = expires;
  }

  public int getPromotionId() {
    return this.promotionId;
  }

  public void setPromotionId(int promotionId) {
    this.promotionId = promotionId;
  }

  public CartModel id(int id) {
    setId(id);
    return this;
  }

  public CartModel userId(int userId) {
    setUserId(userId);
    return this;
  }

  public CartModel createdAt(LocalDateTime createdAt) {
    setCreatedAt(createdAt);
    return this;
  }

  public CartModel status(Status status) {
    setStatus(status);
    return this;
  }

  public CartModel expires(LocalDateTime expires) {
    setExpires(expires);
    return this;
  }

  public CartModel promotionId(int promotionId) {
    setPromotionId(promotionId);
    return this;
  }

}
