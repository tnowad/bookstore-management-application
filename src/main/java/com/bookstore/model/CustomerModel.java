package com.bookstore.model;

import java.sql.Date;

public class CustomerModel {
    private Date PurchaseHistory;
    private String customerID;

    public CustomerModel() {
    }

    public CustomerModel(Date PurchaseHistory, String customerID) {
        this.PurchaseHistory = PurchaseHistory;
        this.customerID = customerID;
    }

    public Date getPurchaseHistory() {
        return this.PurchaseHistory;
    }

    public void setPurchaseHistory(Date PurchaseHistory) {
        this.PurchaseHistory = PurchaseHistory;
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public CustomerModel PurchaseHistory(Date PurchaseHistory) {
        setPurchaseHistory(PurchaseHistory);
        return this;
    }

    public CustomerModel customerID(String customerID) {
        setCustomerID(customerID);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " PurchaseHistory='" + getPurchaseHistory() + "'" +
                ", customerID='" + getCustomerID() + "'" +
                "}";
    }
}
