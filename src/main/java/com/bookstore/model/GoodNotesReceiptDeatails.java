package com.bookstore.model;

public class GoodNotesReceiptDeatails {
    private String ID, ID_Product;
    private int amount;
    private float price;

    public GoodNotesReceiptDeatails() {
    }

    public GoodNotesReceiptDeatails(String ID, String ID_Product, int amount, float price) {
        this.ID = ID;
        this.ID_Product = ID_Product;
        this.amount = amount;
        this.price = price;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID_Product() {
        return this.ID_Product;
    }

    public void setID_Product(String ID_Product) {
        this.ID_Product = ID_Product;
    }

    public int getAmount() {
        return this.amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return this.price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public GoodNotesReceiptDeatails ID(String ID) {
        setID(ID);
        return this;
    }

    public GoodNotesReceiptDeatails ID_Product(String ID_Product) {
        setID_Product(ID_Product);
        return this;
    }

    public GoodNotesReceiptDeatails amount(int amount) {
        setAmount(amount);
        return this;
    }

    public GoodNotesReceiptDeatails price(float price) {
        setPrice(price);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " ID='" + getID() + "'" +
                ", ID_Product='" + getID_Product() + "'" +
                ", amount='" + getAmount() + "'" +
                ", price='" + getPrice() + "'" +
                "}";
    }

}
