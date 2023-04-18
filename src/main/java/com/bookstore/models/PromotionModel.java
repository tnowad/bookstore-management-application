package com.bookstore.models;

import java.time.LocalDate;

public class PromotionModel {
  private int id;
  private String description;
  private int quantity;
  private LocalDate startDate;
  private LocalDate endDate;
  private String conditionApply;
  private int discountPercent;
  private int discountAmount;

  public PromotionModel() {
  }

  public PromotionModel(int id, String description, int quantity, LocalDate startDate, LocalDate endDate,
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
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public LocalDate getStartDate() {
    return this.startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return this.endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public String getConditionApply() {
    return this.conditionApply;
  }

  public void setConditionApply(String conditionApply) {
    this.conditionApply = conditionApply;
  }

  public int getDiscountPercent() {
    return this.discountPercent;
  }

  public void setDiscountPercent(int discountPercent) {
    this.discountPercent = discountPercent;
  }

  public int getDiscountAmount() {
    return this.discountAmount;
  }

  public void setDiscountAmount(int discountAmount) {
    this.discountAmount = discountAmount;
  }

  public PromotionModel id(int id) {
    setId(id);
    return this;
  }

  public PromotionModel description(String description) {
    setDescription(description);
    return this;
  }

  public PromotionModel quantity(int quantity) {
    setQuantity(quantity);
    return this;
  }

  public PromotionModel startDate(LocalDate startDate) {
    setStartDate(startDate);
    return this;
  }

  public PromotionModel endDate(LocalDate endDate) {
    setEndDate(endDate);
    return this;
  }

  public PromotionModel conditionApply(String conditionApply) {
    setConditionApply(conditionApply);
    return this;
  }

  public PromotionModel discountPercent(int discountPercent) {
    setDiscountPercent(discountPercent);
    return this;
  }

  public PromotionModel discountAmount(int discountAmount) {
    setDiscountAmount(discountAmount);
    return this;
  }

}
