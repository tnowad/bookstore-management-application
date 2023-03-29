package com.bookstore.model;

public class CartItemsModel {
  private int cartId;
  private String bookIsbn;
  private int price;
  private int quantity;
  private int discount;

  public CartItemsModel() {
  }

  public CartItemsModel(int cartId, String bookIsbn, int price, int quantity, int discount) {
    this.cartId = cartId;
    this.bookIsbn = bookIsbn;
    this.price = price;
    this.quantity = quantity;
    this.discount = discount;
  }

  public int getCartId() {
    return this.cartId;
  }

  public void setCartId(int cartId) {
    this.cartId = cartId;
  }

  public String getBookIsbn() {
    return this.bookIsbn;
  }

  public void setBookIsbn(String bookIsbn) {
    this.bookIsbn = bookIsbn;
  }

  public int getPrice() {
    return this.price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getQuantity() {
    return this.quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public int getDiscount() {
    return this.discount;
  }

  public void setDiscount(int discount) {
    this.discount = discount;
  }

  public CartItemsModel cartId(int cartId) {
    setCartId(cartId);
    return this;
  }

  public CartItemsModel bookIsbn(String bookIsbn) {
    setBookIsbn(bookIsbn);
    return this;
  }

  public CartItemsModel price(int price) {
    setPrice(price);
    return this;
  }

  public CartItemsModel quantity(int quantity) {
    setQuantity(quantity);
    return this;
  }

  public CartItemsModel discount(int discount) {
    setDiscount(discount);
    return this;
  }

}
