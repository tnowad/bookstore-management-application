package com.bookstore.model;

import java.sql.Date;

public class OrderModel {
    private String ShippingInformation;
    private Date orderDate;
    private String invoiceID, ISBN, userID;

    public OrderModel() {
    }

    public OrderModel(String ShippingInformation, Date orderDate, String invoiceID, String ISBN, String userID) {
        this.ShippingInformation = ShippingInformation;
        this.orderDate = orderDate;
        this.invoiceID = invoiceID;
        this.ISBN = ISBN;
        this.userID = userID;
    }

    public String getShippingInformation() {
        return this.ShippingInformation;
    }

    public void setShippingInformation(String ShippingInformation) {
        this.ShippingInformation = ShippingInformation;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getInvoiceID() {
        return this.invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getUserID() {
        return this.userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public OrderModel ShippingInformation(String ShippingInformation) {
        setShippingInformation(ShippingInformation);
        return this;
    }

    public OrderModel orderDate(Date orderDate) {
        setOrderDate(orderDate);
        return this;
    }

    public OrderModel invoiceID(String invoiceID) {
        setInvoiceID(invoiceID);
        return this;
    }

    public OrderModel ISBN(String ISBN) {
        setISBN(ISBN);
        return this;
    }

    public OrderModel userID(String userID) {
        setUserID(userID);
        return this;
    }

}
