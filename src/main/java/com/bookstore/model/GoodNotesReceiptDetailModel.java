package com.bookstore.model;

public class GoodNotesReceiptDetailModel {
    private String dgnrId, productId;
    private int amount;
    private float price;

    public GoodNotesReceiptDetailModel() {
    }

    public GoodNotesReceiptDetailModel(String dgnrId, String productId, int amount, float price) {
        this.dgnrId = dgnrId;
        this.productId = productId;
        this.amount = amount;
        this.price = price;
    }

    public String getDgnrId() {
        return this.dgnrId;
    }

    public void setDgnrId(String dgnrId) {
        this.dgnrId = dgnrId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public GoodNotesReceiptDetailModel dgnrId(String dgnrId) {
        setDgnrId(dgnrId);
        return this;
    }

    public GoodNotesReceiptDetailModel productId(String productId) {
        setProductId(productId);
        return this;
    }

    public GoodNotesReceiptDetailModel amount(int amount) {
        setAmount(amount);
        return this;
    }

    public GoodNotesReceiptDetailModel price(float price) {
        setPrice(price);
        return this;
    }

}
