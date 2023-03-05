package com.bookstore.model;

import java.time.LocalDate;

public class InvoiceModel {
    private LocalDate InvoiceDate;
    private Boolean PaymentStatus;
    private float TotalAmountDue = 0;
    private String invoiceID;
    private String giftcardID;

    public InvoiceModel() {
        InvoiceDate = LocalDate.now();
    }

    public InvoiceModel(LocalDate InvoiceDate, Boolean PaymentStatus, float TotalAmountDue, String invoiceID,
            String giftcardID) {
        this.InvoiceDate = InvoiceDate;
        this.PaymentStatus = PaymentStatus;
        this.TotalAmountDue = TotalAmountDue;
        this.invoiceID = invoiceID;
        this.giftcardID = giftcardID;
    }

    public LocalDate getInvoiceDate() {
        return this.InvoiceDate;
    }

    public void setInvoiceDate(LocalDate InvoiceDate) {
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

    public float getTotalAmountDue() {
        return this.TotalAmountDue;
    }

    public void setTotalAmountDue(float TotalAmountDue) {
        this.TotalAmountDue = TotalAmountDue;
    }

    public String getInvoiceID() {
        return this.invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getGiftcardID() {
        return this.giftcardID;
    }

    public void setGiftcardID(String giftcardID) {
        this.giftcardID = giftcardID;
    }

    public InvoiceModel InvoiceDate(LocalDate InvoiceDate) {
        setInvoiceDate(InvoiceDate);
        return this;
    }

    public InvoiceModel PaymentStatus(Boolean PaymentStatus) {
        setPaymentStatus(PaymentStatus);
        return this;
    }

    public InvoiceModel TotalAmountDue(float TotalAmountDue) {
        setTotalAmountDue(TotalAmountDue);
        return this;
    }

    public InvoiceModel invoiceID(String invoiceID) {
        setInvoiceID(invoiceID);
        return this;
    }

    public InvoiceModel giftcardID(String giftcardID) {
        setGiftcardID(giftcardID);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " InvoiceDate='" + getInvoiceDate() + "'" +
                ", PaymentStatus='" + isPaymentStatus() + "'" +
                ", TotalAmountDue='" + getTotalAmountDue() + "'" +
                ", invoiceID='" + getInvoiceID() + "'" +
                ", giftcardID='" + getGiftcardID() + "'" +
                "}";
    }
}