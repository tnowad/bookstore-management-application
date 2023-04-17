package com.bookstore.models;

public class MenuItemModel {
    private String title;
    private String actionEvent;

    public MenuItemModel(String title, String actionEvent) {
        this.title = title;
        this.actionEvent = actionEvent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(String actionEvent) {
        this.actionEvent = actionEvent;
    }
}
