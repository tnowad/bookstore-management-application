package com.bookstore.model;

import java.util.Date;

public class PromotionModel {
  private int id;
  private String description;
  private int quantity;
  private Date startDate;
  private Date endDate;
  private String conditionApply;
  private int discountPercent;
  private int discountAmount;

  public PromotionModel(int id, String description, int quantity, Date startDate, Date endDate,
      String conditionApply, int discountPercent, int discountAmount) {
    this.id = id;
    this.description = description;
    this.quantity = quantity;
    this.startDate = startDate;
    this.endDate = endDate;
    this.conditionApply = conditionApply;
    this.discountPercent = discountPercent;
    this.discountAmount = discountAmount;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public String getConditionApply() {
    return conditionApply;
  }

  public void setConditionApply(String conditionApply) {
    this.conditionApply = conditionApply;
  }

  public int getDiscountPercent() {
    return discountPercent;
  }

  public void setDiscountPercent(int discountPercent) {
    this.discountPercent = discountPercent;
  }

  public int getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(int discountAmount) {
    this.discountAmount = discountAmount;
  }
}
