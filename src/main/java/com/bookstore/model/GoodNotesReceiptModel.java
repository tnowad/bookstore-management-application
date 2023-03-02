package com.bookstore.model;

import java.sql.Date;

public class GoodNotesReceiptModel {
    private Date importDate;
    private String ID;

    public GoodNotesReceiptModel() {
    }

    public GoodNotesReceiptModel(Date importDate, String ID) {
        this.importDate = importDate;
        this.ID = ID;
    }

    public Date getImportDate() {
        return this.importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public GoodNotesReceiptModel importDate(Date importDate) {
        setImportDate(importDate);
        return this;
    }

    public GoodNotesReceiptModel ID(String ID) {
        setID(ID);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " importDate='" + getImportDate() + "'" +
                ", ID='" + getID() + "'" +
                "}";
    }

}
