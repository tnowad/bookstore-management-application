package com.bookstore.model;

import java.sql.Date;

public class OrderModel {
  private String shippingInformation;
  private Date orderDate;
  private String invoiceId, ISBN, userId, orderId;

  public OrderModel() {
  }

  public OrderModel(String shippingInformation, Date orderDate, String invoiceId, String iSBN, String userId,
      String orderId) {
    this.shippingInformation = shippingInformation;
    this.orderDate = orderDate;
    this.invoiceId = invoiceId;
    ISBN = iSBN;
    this.userId = userId;
    this.orderId = orderId;
  }

  public String getShippingInformation() {
    return shippingInformation;
  }

  public void setShippingInformation(String shippingInformation) {
    this.shippingInformation = shippingInformation;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public String getInvoiceId() {
    return invoiceId;
  }

  public void setInvoiceId(String invoiceId) {
    this.invoiceId = invoiceId;
  }

  public String getISBN() {
    return ISBN;
  }

  public void setISBN(String iSBN) {
    ISBN = iSBN;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

}
