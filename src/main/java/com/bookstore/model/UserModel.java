package com.bookstore.model;

public class UserModel {
    private String Address, Email, PhoneNumber, ID, Name, Role, AccountType;

    public UserModel() {
    }

    public UserModel(String Address, String Email, String PhoneNumber, String ID, String Name, String Role,
            String AccountType) {
        this.Address = Address;
        this.Email = Email;
        this.PhoneNumber = PhoneNumber;
        this.ID = ID;
        this.Name = Name;
        this.Role = Role;
        this.AccountType = AccountType;
    }

    public String getAddress() {
        return this.Address;
    }

    public void setAddress(String Address) {
        this.Address = Address;
    }

    public String getEmail() {
        return this.Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPhoneNumber() {
        return this.PhoneNumber;
    }

    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    public String getID() {
        return this.ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return this.Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getRole() {
        return this.Role;
    }

    public void setRole(String Role) {
        this.Role = Role;
    }

    public String getAccountType() {
        return this.AccountType;
    }

    public void setAccountType(String AccountType) {
        this.AccountType = AccountType;
    }

    public UserModel Address(String Address) {
        setAddress(Address);
        return this;
    }

    public UserModel Email(String Email) {
        setEmail(Email);
        return this;
    }

    public UserModel PhoneNumber(String PhoneNumber) {
        setPhoneNumber(PhoneNumber);
        return this;
    }

    public UserModel ID(String ID) {
        setID(ID);
        return this;
    }

    public UserModel Name(String Name) {
        setName(Name);
        return this;
    }

    public UserModel Role(String Role) {
        setRole(Role);
        return this;
    }

    public UserModel AccountType(String AccountType) {
        setAccountType(AccountType);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " Address='" + getAddress() + "'" +
                ", Email='" + getEmail() + "'" +
                ", PhoneNumber='" + getPhoneNumber() + "'" +
                ", ID='" + getID() + "'" +
                ", Name='" + getName() + "'" +
                ", Role='" + getRole() + "'" +
                ", AccountType='" + getAccountType() + "'" +
                "}";
    }

}
