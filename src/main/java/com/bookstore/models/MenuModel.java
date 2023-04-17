package com.bookstore.models;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.Icon;

public class MenuModel {
    private Icon icon;
    private String title;
    private ActionEvent actionEvent;
    private List<MenuItemModel> menuItems;

    public MenuModel(Icon icon, String title, ActionEvent actionEvent, List<MenuItemModel> menuItems) {
        this.icon = icon;
        this.title = title;
        this.actionEvent = actionEvent;
        this.menuItems = menuItems;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ActionEvent getActionEvent() {
        return actionEvent;
    }

    public void setActionEvent(ActionEvent actionEvent) {
        this.actionEvent = actionEvent;
    }

    public List<MenuItemModel> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemModel> menuItems) {
        this.menuItems = menuItems;
    }
}
