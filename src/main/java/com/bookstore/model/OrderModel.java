package com.bookstore.model;

import java.sql.Date;

public class OrderModel {
    private String ShippingInformation;
    private BookModel bookInformation;
    private CustomerModel customerInformation;
    private Date orderDate;
    private Integer orderTotal;

    public OrderModel() {
    }

    public OrderModel(String ShippingInformation, BookModel bookInformation, CustomerModel customerInformation,
            Date orderDate,
            Integer orderTotal) {
        this.ShippingInformation = ShippingInformation;
        this.bookInformation = bookInformation;
        this.customerInformation = customerInformation;
        this.orderDate = orderDate;
        this.orderTotal = orderTotal;
    }

    public String getShippingInformation() {
        return this.ShippingInformation;
    }

    public void setShippingInformation(String ShippingInformation) {
        this.ShippingInformation = ShippingInformation;
    }

    public BookModel getBookInformation() {
        return this.bookInformation;
    }

    public void setBookInformation(BookModel bookInformation) {
        this.bookInformation = bookInformation;
    }

    public CustomerModel getCustomerInformation() {
        return this.customerInformation;
    }

    public void setCustomerInformation(CustomerModel customerInformation) {
        this.customerInformation = customerInformation;
    }

    public Date getOrderDate() {
        return this.orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Integer getOrderTotal() {
        return this.orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public OrderModel ShippingInformation(String ShippingInformation) {
        setShippingInformation(ShippingInformation);
        return this;
    }

    public OrderModel bookInformation(BookModel bookInformation) {
        setBookInformation(bookInformation);
        return this;
    }

    public OrderModel customerInformation(CustomerModel customerInformation) {
        setCustomerInformation(customerInformation);
        return this;
    }

    public OrderModel orderDate(Date orderDate) {
        setOrderDate(orderDate);
        return this;
    }

    public OrderModel orderTotal(Integer orderTotal) {
        setOrderTotal(orderTotal);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " ShippingInformation='" + getShippingInformation() + "'" +
                ", bookInformation='" + getBookInformation() + "'" +
                ", customerInformation='" + getCustomerInformation() + "'" +
                ", orderDate='" + getOrderDate() + "'" +
                ", orderTotal='" + getOrderTotal() + "'" +
                "}";
    }

}
