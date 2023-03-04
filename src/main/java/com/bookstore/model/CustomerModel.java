package com.bookstore.model;

import java.sql.Date;

public class CustomerModel {
    private Date PurchaseHistory;

    public CustomerModel() {
    }

    public CustomerModel(Date PurchaseHistory) {
        this.PurchaseHistory = PurchaseHistory;
    }

    public Date getPurchaseHistory() {
        return this.PurchaseHistory;
    }

    public void setPurchaseHistory(Date PurchaseHistory) {
        this.PurchaseHistory = PurchaseHistory;
    }

    public CustomerModel PurchaseHistory(Date PurchaseHistory) {
        setPurchaseHistory(PurchaseHistory);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " PurchaseHistory='" + getPurchaseHistory() + "'" +
                "}";
    }

}
