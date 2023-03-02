package com.bookstore.model;

import java.sql.Date;

public class PaymentModel {
    private OrderModel Order;
    private Date paymentDate;
    private float paymentAmount;

    public PaymentModel() {
    }

    public PaymentModel(OrderModel Order, Date paymentDate, float paymentAmount) {
        this.Order = Order;
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public OrderModel getOrder() {
        return this.Order;
    }

    public void setOrder(OrderModel Order) {
        this.Order = Order;
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

    public PaymentModel Order(OrderModel Order) {
        setOrder(Order);
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

    @Override
    public String toString() {
        return "{" +
                " Order='" + getOrder() + "'" +
                ", paymentDate='" + getPaymentDate() + "'" +
                ", paymentAmount='" + getPaymentAmount() + "'" +
                "}";
    }

}
