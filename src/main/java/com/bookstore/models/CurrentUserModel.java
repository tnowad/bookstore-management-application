package com.bookstore.models;

public class CurrentUserModel {
    private int currentUserId;

    public CurrentUserModel() {
    }

    public CurrentUserModel(int currentUserId) {
        this.currentUserId = currentUserId;
    }

    public int getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(int currentUserId) {
        this.currentUserId = currentUserId;
    }

}
