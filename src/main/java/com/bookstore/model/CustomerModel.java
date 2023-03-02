package com.bookstore.model;

import java.sql.Date;

public class CustomerModel {
    private String Email;
    private Date PurchaseHistory;

    public CustomerModel() {
    }

    public CustomerModel(String Email, Date PurchaseHistory) {
        this.Email = Email;
        this.PurchaseHistory = PurchaseHistory;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public Date getPurchaseHistory() {
        return this.PurchaseHistory;
    }

    public void setPurchaseHistory(Date PurchaseHistory) {
        this.PurchaseHistory = PurchaseHistory;
    }

    public CustomerModel Email(String Email) {
        setEmail(Email);
        return this;
    }

    public CustomerModel PurchaseHistory(Date PurchaseHistory) {
        setPurchaseHistory(PurchaseHistory);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " Email='" + getEmail() + "'" +
                ", PurchaseHistory='" + getPurchaseHistory() + "'" +
                "}";
    }

}
