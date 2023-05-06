package com.bookstore.models;

public class ImportItemsModel {

  private int importId, quantity;
  private String bookIsbn;
  private double price;

  public ImportItemsModel() {}

  public ImportItemsModel(
    int importId,
    int quantity,
    String bookIsbn,
    double price
  ) {
    this.importId = importId;
    this.quantity = quantity;
    this.bookIsbn = bookIsbn;
    this.price = price;
  }

  public int getImportId() {
    return this.importId;
  }

  public void setImportId(int importId) {
    this.importId = importId;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public String getBookIsbn() {
    return this.bookIsbn;
  }

  public void setBookIsbn(String bookIsbn) {
    this.bookIsbn = bookIsbn;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public ImportItemsModel importId(int importId) {
    setImportId(importId);
    return this;
  }

  public ImportItemsModel quantity(int quantity) {
    setQuantity(quantity);
    return this;
  }

  public ImportItemsModel bookIsbn(String bookIsbn) {
    setBookIsbn(bookIsbn);
    return this;
  }

  public ImportItemsModel price(double price) {
    setPrice(price);
    return this;
  }

  @Override
  public String toString() {
    return (
      "ImportItemsModel [importId=" +
      importId +
      ", quantity=" +
      quantity +
      ", bookIsbn=" +
      bookIsbn +
      ", price=" +
      price +
      "]"
    );
  }
}
