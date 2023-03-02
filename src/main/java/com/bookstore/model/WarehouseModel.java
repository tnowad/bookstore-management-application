package com.bookstore.model;

import java.sql.Date;

public class WarehouseModel {
    private String ID;
    private Date importDate;
    private float bookPrices;
    private int quantityinStock, quantity;

    public WarehouseModel() {
    }

    public WarehouseModel(String ID, Date importDate, float bookPrices, int quantityinStock, int quantity) {
        this.ID = ID;
        this.importDate = importDate;
        this.bookPrices = bookPrices;
        this.quantityinStock = quantityinStock;
        this.quantity = quantity;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Date getImportDate() {
        return this.importDate;
    }

    public void setImportDate(Date importDate) {
        this.importDate = importDate;
    }

    public float getBookPrices() {
        return this.bookPrices;
    }

    public void setBookPrices(float bookPrices) {
        this.bookPrices = bookPrices;
    }

    public int getQuantityinStock() {
        return this.quantityinStock;
    }

    public void setQuantityinStock(int quantityinStock) {
        this.quantityinStock = quantityinStock;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public WarehouseModel ID(String ID) {
        setID(ID);
        return this;
    }

    public WarehouseModel importDate(Date importDate) {
        setImportDate(importDate);
        return this;
    }

    public WarehouseModel bookPrices(float bookPrices) {
        setBookPrices(bookPrices);
        return this;
    }

    public WarehouseModel quantityinStock(int quantityinStock) {
        setQuantityinStock(quantityinStock);
        return this;
    }

    public WarehouseModel quantity(int quantity) {
        setQuantity(quantity);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " ID='" + getID() + "'" +
                ", importDate='" + getImportDate() + "'" +
                ", bookPrices='" + getBookPrices() + "'" +
                ", quantityinStock='" + getQuantityinStock() + "'" +
                ", quantity='" + getQuantity() + "'" +
                "}";
    }

}
