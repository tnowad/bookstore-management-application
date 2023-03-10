package com.bookstore.model;

public class BookModel {
  private String isbn;
  private String title;
  private String description;
  private String image;
  private int price;
  private int quantity;
  private Status status;
  private int publisherId;
  private int authorId;

  public enum Status {
    AVAILABLE,
    UNAVAILABLE,
    DELETED;
  }

  public BookModel(String isbn, String title, String description, String image, int price, int quantity,
      Status status, int publisherId, int authorId) {
    this.isbn = isbn;
    this.title = title;
    this.description = description;
    this.image = image;
    this.price = price;
    this.quantity = quantity;
    this.status = status;
    this.publisherId = publisherId;
    this.authorId = authorId;
  }

  // Getters & Setters
  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public int getPublisherId() {
    return publisherId;
  }

  public void setPublisherId(int publisherId) {
    this.publisherId = publisherId;
  }

  public int getAuthorId() {
    return authorId;
  }

  public void setAuthorId(int authorId) {
    this.authorId = authorId;
  }
}
