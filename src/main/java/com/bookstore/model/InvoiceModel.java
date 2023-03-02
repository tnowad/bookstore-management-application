package com.bookstore.model;

import java.sql.Date;

public class InvoiceModel {
    private OrderModel order;
    private Date InvoiceDate;
    private Boolean PaymentStatus;
    private int TotalAmountDue;
    private String invoiceID;

    public InvoiceModel() {
    }

    public InvoiceModel(OrderModel order, Date InvoiceDate, Boolean PaymentStatus, int TotalAmountDue,
            String invoiceID) {
        this.order = order;
        this.InvoiceDate = InvoiceDate;
        this.PaymentStatus = PaymentStatus;
        this.TotalAmountDue = TotalAmountDue;
        this.invoiceID = invoiceID;
    }

    public OrderModel getOrder() {
        return this.order;
    }

    public void setOrder(OrderModel order) {
        this.order = order;
    }

    public Date getInvoiceDate() {
        return this.InvoiceDate;
    }

    public void setInvoiceDate(Date InvoiceDate) {
        this.InvoiceDate = InvoiceDate;
    }

    public Boolean isPaymentStatus() {
        return this.PaymentStatus;
    }

    public Boolean getPaymentStatus() {
        return this.PaymentStatus;
    }

    public void setPaymentStatus(Boolean PaymentStatus) {
        this.PaymentStatus = PaymentStatus;
    }

    public int getTotalAmountDue() {
        return this.TotalAmountDue;
    }

    public void setTotalAmountDue(int TotalAmountDue) {
        this.TotalAmountDue = TotalAmountDue;
    }

    public String getInvoiceID() {
        return this.invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public InvoiceModel order(OrderModel order) {
        setOrder(order);
        return this;
    }

    public InvoiceModel InvoiceDate(Date InvoiceDate) {
        setInvoiceDate(InvoiceDate);
        return this;
    }

    public InvoiceModel PaymentStatus(Boolean PaymentStatus) {
        setPaymentStatus(PaymentStatus);
        return this;
    }

    public InvoiceModel TotalAmountDue(int TotalAmountDue) {
        setTotalAmountDue(TotalAmountDue);
        return this;
    }

    public InvoiceModel invoiceID(String invoiceID) {
        setInvoiceID(invoiceID);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " order='" + getOrder() + "'" +
                ", InvoiceDate='" + getInvoiceDate() + "'" +
                ", PaymentStatus='" + isPaymentStatus() + "'" +
                ", TotalAmountDue='" + getTotalAmountDue() + "'" +
                ", invoiceID='" + getInvoiceID() + "'" +
                "}";
    }
}