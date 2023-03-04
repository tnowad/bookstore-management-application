package com.bookstore.model;

public class AccountModel {
    private String Username, Password, Status;

    public AccountModel() {
    }

    public AccountModel(String Username, String Password, String Status) {
        this.Username = Username;
        this.Password = Password;
        this.Status = Status;
    }

    public String getUsername() {
        return this.Username;
    }

    public void setUsername(String Username) {
        this.Username = Username;
    }

    public String getPassword() {
        return this.Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getStatus() {
        return this.Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public AccountModel Username(String Username) {
        setUsername(Username);
        return this;
    }

    public AccountModel Password(String Password) {
        setPassword(Password);
        return this;
    }

    public AccountModel Status(String Status) {
        setStatus(Status);
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                " Username='" + getUsername() + "'" +
                ", Password='" + getPassword() + "'" +
                ", Status='" + getStatus() + "'" +
                "}";
    }

}
