package com.bookstore.model;

import java.sql.Date;

public class BookModel {
  private String title;
  private String ISBN;
  private String description;
  private boolean status;
  private Date publicationDate;
  private Integer quantity, quantityInStock;
  private float price;

  public BookModel() {
  }

  public BookModel(String title, String iSBN, String description, boolean status, Date publicationDate,
      Integer quantity, Integer quantityInStock, float price) {
    this.title = title;
    ISBN = iSBN;
    this.description = description;
    this.status = status;
    this.publicationDate = publicationDate;
    this.quantity = quantity;
    this.quantityInStock = quantityInStock;
    this.price = price;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getISBN() {
    return ISBN;
  }

  public void setISBN(String iSBN) {
    ISBN = iSBN;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isStatus() {
    return status;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public Date getPublicationDate() {
    return publicationDate;
  }

  public void setPublicationDate(Date publicationDate) {
    this.publicationDate = publicationDate;
  }

  public Integer getQuantity() {
    return quantity;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Integer getQuantityInStock() {
    return quantityInStock;
  }

  public void setQuantityInStock(Integer quantityInStock) {
    this.quantityInStock = quantityInStock;
  }

  public float getPrice() {
    return price;
  }

  public void setPrice(float price) {
    this.price = price;
  }

}