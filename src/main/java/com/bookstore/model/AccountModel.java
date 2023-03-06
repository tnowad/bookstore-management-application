package com.bookstore.model;

public class AccountModel {
    private String username, password, status, accountID;

    public AccountModel() {
    }

    public AccountModel(String username, String password, String status, String accountID) {
        this.username = username;
        this.password = password;
        this.status = status;
        this.accountID = accountID;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAccountID() {
        return this.accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public AccountModel username(String username) {
        setUsername(username);
        return this;
    }

    public AccountModel password(String password) {
        setPassword(password);
        return this;
    }

    public AccountModel status(String status) {
        setStatus(status);
        return this;
    }

    public AccountModel accountID(String accountID) {
        setAccountID(accountID);
        return this;
    }

}
