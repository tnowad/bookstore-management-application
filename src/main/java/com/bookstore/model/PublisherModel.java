package com.bookstore.model;

public class PublisherModel {
  private String location, name, ISBN, publisherId;

  public PublisherModel() {
  }

  public PublisherModel(String location, String name, String iSBN, String publisherId) {
    this.location = location;
    this.name = name;
    this.ISBN = iSBN;
    this.publisherId = publisherId;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getISBN() {
    return ISBN;
  }

  public void setISBN(String iSBN) {
    ISBN = iSBN;
  }

  public String getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(String publisherId) {
    this.publisherId = publisherId;
  }

}
