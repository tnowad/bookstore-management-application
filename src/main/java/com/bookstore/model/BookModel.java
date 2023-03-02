package com.bookstore.model;

import java.sql.Date;

public class BookModel {
    private String Title, ISBN, Description;
    private boolean Status;
    private Date PublicationDate;
    private Integer Quantity;

    public BookModel() {
    }

    public BookModel(String Title, String ISBN, String Description, boolean Status, Date PublicationDate,
            Integer Quantity) {
        this.Title = Title;
        this.ISBN = ISBN;
        this.Description = Description;
        this.Status = Status;
        this.PublicationDate = PublicationDate;
        this.Quantity = Quantity;
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

    public String getDescription() {
        return this.Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
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

    public BookModel Title(String Title) {
        setTitle(Title);
        return this;
    }

    public BookModel ISBN(String ISBN) {
        setISBN(ISBN);
        return this;
    }

    public BookModel Description(String Description) {
        setDescription(Description);
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

    @Override
    public String toString() {
        return "{" +
                " Title='" + getTitle() + "'" +
                ", ISBN='" + getISBN() + "'" +
                ", Description='" + getDescription() + "'" +
                ", Status='" + isStatus() + "'" +
                ", PublicationDate='" + getPublicationDate() + "'" +
                ", Quantity='" + getQuantity() + "'" +
                "}";
    }

}
