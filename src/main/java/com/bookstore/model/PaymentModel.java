package com.bookstore.model;

import java.sql.Date;

public class PaymentModel {
    private String paymentId, orderId;
    private Date paymentDate;
    private float paymentAmount;

    public PaymentModel() {
    }

    public PaymentModel(String paymentId, String orderId, Date paymentDate, float paymentAmount) {
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentId() {
        return this.paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getPaymentAmount() {
        return this.paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public PaymentModel paymentId(String paymentId) {
        setPaymentId(paymentId);
        return this;
    }

    public PaymentModel orderId(String orderId) {
        setOrderId(orderId);
        return this;
    }

    public PaymentModel paymentDate(Date paymentDate) {
        setPaymentDate(paymentDate);
        return this;
    }

    public PaymentModel paymentAmount(float paymentAmount) {
        setPaymentAmount(paymentAmount);
        return this;
    }

}
