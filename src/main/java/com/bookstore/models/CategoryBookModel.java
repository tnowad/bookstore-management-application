package com.bookstore.models;

public class BooksCategoryModel {

  private int categoryId;
  private String bookIsbn;

  public BooksCategoryModel() {}

  public BooksCategoryModel(int categoryId, String bookIsbn) {
    this.categoryId = categoryId;
    this.bookIsbn = bookIsbn;
  }

  public int getCategoryId() {
    return categoryId;
  }

  public void setCategoryId(int categoryId) {
    this.categoryId = categoryId;
  }

  public String getBookIsbn() {
    return bookIsbn;
  }

  public void setBookIsbn(String bookIsbn) {
    this.bookIsbn = bookIsbn;
  }
}
