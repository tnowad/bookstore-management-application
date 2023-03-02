package com.bookstore.model;

public class CustomerModel {
    private String ID, Email, Address, Name, PhoneNumber;

    public CustomerModel() {
    }

    public CustomerModel(String ID, String Email, String Address, String Name, String PhoneNumber) {
        this.ID = ID;
        this.Email = Email;
        this.Address = Address;
        this.Name = Name;
        this.PhoneNumber = PhoneNumber;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPhoneNumber() {
        return this.PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

}
