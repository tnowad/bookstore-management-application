package com.bookstore.model;

import java.sql.Date;

public class BookModel {
    private String Title, ISBN;
    private boolean Status;
    private Date PublicationDate;
    private Integer Quantity, QuantityinStock;
    private float price;

    public BookModel() {
    }

    public BookModel(String Title, String ISBN, boolean Status, Date PublicationDate, Integer Quantity,
            Integer QuantityinStock, float price) {
        this.Title = Title;
        this.ISBN = ISBN;
        this.Status = Status;
        this.PublicationDate = PublicationDate;
        this.Quantity = Quantity;
        this.QuantityinStock = QuantityinStock;
        this.price = price;
    }

    public String getTitle() {
        return this.Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getISBN() {
        return this.ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public boolean isStatus() {
        return this.Status;
    }

    public boolean getStatus() {
        return this.Status;
    }

    public void setStatus(boolean Status) {
        this.Status = Status;
    }

    public Date getPublicationDate() {
        return this.PublicationDate;
    }

    public void setPublicationDate(Date PublicationDate) {
        this.PublicationDate = PublicationDate;
    }

    public Integer getQuantity() {
        return this.Quantity;
    }

    public void setQuantity(Integer Quantity) {
        this.Quantity = Quantity;
    }

    public Integer getQuantityinStock() {
        return this.QuantityinStock;
    }

    public void setQuantityinStock(Integer QuantityinStock) {
        this.QuantityinStock = QuantityinStock;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public BookModel Title(String Title) {
        setTitle(Title);
        return this;
    }

    public BookModel ISBN(String ISBN) {
        setISBN(ISBN);
        return this;
    }

    public BookModel Status(boolean Status) {
        setStatus(Status);
        return this;
    }

    public BookModel PublicationDate(Date PublicationDate) {
        setPublicationDate(PublicationDate);
        return this;
    }

    public BookModel Quantity(Integer Quantity) {
        setQuantity(Quantity);
        return this;
    }

    public BookModel QuantityinStock(Integer QuantityinStock) {
        setQuantityinStock(QuantityinStock);
        return this;
    }

    public BookModel price(float price) {
        setPrice(price);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " Title='" + getTitle() + "'" +
                ", ISBN='" + getISBN() + "'" +
                ", Status='" + isStatus() + "'" +
                ", PublicationDate='" + getPublicationDate() + "'" +
                ", Quantity='" + getQuantity() + "'" +
                ", QuantityinStock='" + getQuantityinStock() + "'" +
                ", price='" + getPrice() + "'" +
                "}";
    }

}