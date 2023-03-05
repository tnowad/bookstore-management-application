package com.bookstore.model;

import java.sql.Date;

public class PromotionsModel {
    private int Amount, AmountRemaining;
    private Date endDate, startDate;
    private String offerDescription, promotionType, invoiceID, promotionID;
    private float discountAmount = 0;

    public PromotionsModel() {
    }

    public PromotionsModel(int Amount, int AmountRemaining, Date endDate, Date startDate, String offerDescription,
            String promotionType, String invoiceID, String promotionID, float discountAmount) {
        this.Amount = Amount;
        this.AmountRemaining = AmountRemaining;
        this.endDate = endDate;
        this.startDate = startDate;
        this.offerDescription = offerDescription;
        this.promotionType = promotionType;
        this.invoiceID = invoiceID;
        this.promotionID = promotionID;
        this.discountAmount = discountAmount;
    }

    public int getAmount() {
        return this.Amount;
    }

    public void setAmount(int Amount) {
        this.Amount = Amount;
    }

    public int getAmountRemaining() {
        return this.AmountRemaining;
    }

    public void setAmountRemaining(int AmountRemaining) {
        this.AmountRemaining = AmountRemaining;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getOfferDescription() {
        return this.offerDescription;
    }

    public void setOfferDescription(String offerDescription) {
        this.offerDescription = offerDescription;
    }

    public String getPromotionType() {
        return this.promotionType;
    }

    public void setPromotionType(String promotionType) {
        this.promotionType = promotionType;
    }

    public String getInvoiceID() {
        return this.invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getPromotionID() {
        return this.promotionID;
    }

    public void setPromotionID(String promotionID) {
        this.promotionID = promotionID;
    }

    public float getDiscountAmount() {
        return this.discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public PromotionsModel Amount(int Amount) {
        setAmount(Amount);
        return this;
    }

    public PromotionsModel AmountRemaining(int AmountRemaining) {
        setAmountRemaining(AmountRemaining);
        return this;
    }

    public PromotionsModel endDate(Date endDate) {
        setEndDate(endDate);
        return this;
    }

    public PromotionsModel startDate(Date startDate) {
        setStartDate(startDate);
        return this;
    }

    public PromotionsModel offerDescription(String offerDescription) {
        setOfferDescription(offerDescription);
        return this;
    }

    public PromotionsModel promotionType(String promotionType) {
        setPromotionType(promotionType);
        return this;
    }

    public PromotionsModel invoiceID(String invoiceID) {
        setInvoiceID(invoiceID);
        return this;
    }

    public PromotionsModel promotionID(String promotionID) {
        setPromotionID(promotionID);
        return this;
    }

    public PromotionsModel discountAmount(float discountAmount) {
        setDiscountAmount(discountAmount);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " Amount='" + getAmount() + "'" +
                ", AmountRemaining='" + getAmountRemaining() + "'" +
                ", endDate='" + getEndDate() + "'" +
                ", startDate='" + getStartDate() + "'" +
                ", offerDescription='" + getOfferDescription() + "'" +
                ", promotionType='" + getPromotionType() + "'" +
                ", invoiceID='" + getInvoiceID() + "'" +
                ", promotionID='" + getPromotionID() + "'" +
                ", discountAmount='" + getDiscountAmount() + "'" +
                "}";
    }

}
