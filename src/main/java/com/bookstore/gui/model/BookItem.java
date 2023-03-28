package com.bookstore.gui.model;

import javax.swing.Icon;

public class BookItem {
  private String isbn;
  private String title;
  private String description;
  private int price;
  private Icon image;

  public BookItem() {
  }

  public BookItem(String isbn, String title, String description, int price, Icon image) {
    this.isbn = isbn;
    this.title = title;
    this.description = description;
    this.price = price;
    this.image = image;
  }

  public String getIsbn() {
    return this.isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public String getTitle() {
    return this.title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return this.description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public Icon getImage() {
    return this.image;
  }

  public void setImage(Icon image) {
    this.image = image;
  }

  public BookItem isbn(String isbn) {
    setIsbn(isbn);
    return this;
  }

  public BookItem title(String title) {
    setTitle(title);
    return this;
  }

  public BookItem description(String description) {
    setDescription(description);
    return this;
  }

  public BookItem price(int price) {
    setPrice(price);
    return this;
  }

  public BookItem image(Icon image) {
    setImage(image);
    return this;
  }

}
