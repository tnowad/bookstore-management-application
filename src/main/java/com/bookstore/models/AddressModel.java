package com.bookstore.models;

public class AddressModel {
  private int id;
  private int userId;
  private String street;
  private String city;
  private String state;
  private String zip;

  public AddressModel() {
  }

  public AddressModel(int id, int userId, String street, String city, String state, String zip) {
    this.id = id;
    this.userId = userId;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUserId() {
    return this.userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public String getStreet() {
    return this.street;
  }

  public void setStreet(String street) {
    this.street = street;
  }

  public String getCity() {
    return this.city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return this.state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getZip() {
    return this.zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public AddressModel id(int id) {
    setId(id);
    return this;
  }

  public AddressModel userId(int userId) {
    setUserId(userId);
    return this;
  }

  public AddressModel street(String street) {
    setStreet(street);
    return this;
  }

  public AddressModel city(String city) {
    setCity(city);
    return this;
  }

  public AddressModel state(String state) {
    setState(state);
    return this;
  }

  public AddressModel zip(String zip) {
    setZip(zip);
    return this;
  }
}
