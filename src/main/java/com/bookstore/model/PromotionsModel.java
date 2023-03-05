package com.bookstore.model;

import java.sql.Date;

public class PromotionsModel {
  private String promotionId;
  private int amount;
  private Date endDate, startDate;
  private String offerDescription;
  private String promotionType;
  private String invoiceId;
  private float discountAmount = 0;

  public PromotionsModel() {
  }

  public PromotionsModel(String promotionId, int amount, Date endDate, Date startDate, String offerDescription,
      String promotionType, String invoiceId, float discountAmount) {
    this.promotionId = promotionId;
    this.amount = amount;
    this.endDate = endDate;
    this.startDate = startDate;
    this.offerDescription = offerDescription;
    this.promotionType = promotionType;
    this.invoiceId = invoiceId;
    this.discountAmount = discountAmount;
  }

  public String getPromotionId() {
    return promotionId;
  }

  public void setPromotionId(String promotionId) {
    this.promotionId = promotionId;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public String getOfferDescription() {
    return offerDescription;
  }

  public void setOfferDescription(String offerDescription) {
    this.offerDescription = offerDescription;
  }

  public String getPromotionType() {
    return promotionType;
  }

  public void setPromotionType(String promotionType) {
    this.promotionType = promotionType;
  }

  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }

  public float getDiscountAmount() {
    return discountAmount;
  }

  public void setDiscountAmount(float discountAmount) {
    this.discountAmount = discountAmount;
  }

}
