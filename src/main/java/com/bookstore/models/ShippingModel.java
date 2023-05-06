package com.bookstore.models;

import com.bookstore.enums.ShippingStatus;

public class ShippingModel {
  private int id, orderId, addressId;
  private String shippingMethod;
  private ShippingStatus status;

  public ShippingModel() {
  }

  public ShippingModel(int id, int orderId, int addressId, String shippingMethod, ShippingStatus status) {
    this.id = id;
    this.orderId = orderId;
    this.addressId = addressId;
    this.shippingMethod = shippingMethod;
    this.status = status;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getOrderId() {
    return this.orderId;
  }

  public void setOrderId(int orderId) {
    this.orderId = orderId;
  }

  public int getAddressId() {
    return this.addressId;
  }

  public void setAddressId(int addressId) {
    this.addressId = addressId;
  }

  public String getShippingMethod() {
    return this.shippingMethod;
  }

  public void setShippingMethod(String shippingMethod) {
    this.shippingMethod = shippingMethod;
  }

  public ShippingStatus getStatus() {
    return this.status;
  }

  public void setStatus(ShippingStatus status) {
    this.status = status;
  }

  public ShippingModel id(int id) {
    setId(id);
    return this;
  }

  public ShippingModel orderId(int orderId) {
    setOrderId(orderId);
    return this;
  }

  public ShippingModel addressId(int addressId) {
    setAddressId(addressId);
    return this;
  }

  public ShippingModel shippingMethod(String shippingMethod) {
    setShippingMethod(shippingMethod);
    return this;
  }

  public ShippingModel status(ShippingStatus status) {
    setStatus(status);
    return this;
  }

}
