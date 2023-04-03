package com.bookstore.model;

public class ShippingModel {
  private int id;
  private int orderId;
  private String shippingMethod;
  private int addressId;
  private Status status;

  public enum Status {
    PENDING,
    SHIPPED,
    DELIVERED
  }

  public ShippingModel() {
  }

  public ShippingModel(int id, int orderId, String shippingMethod, int addressId, Status status) {
    this.id = id;
    this.orderId = orderId;
    this.shippingMethod = shippingMethod;
    this.addressId = addressId;
    this.status = Status.PENDING;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOrderId() {
    return orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public String getShippingMethod() {
    return shippingMethod;
  }

  public void setShippingMethod(String shippingMethod) {
    this.shippingMethod = shippingMethod;
  }

  public int getAddressId() {
    return addressId;
  }

  public void setAddressId(int addressId) {
    this.addressId = addressId;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

}
