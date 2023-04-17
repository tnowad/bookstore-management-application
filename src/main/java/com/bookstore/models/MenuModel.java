package com.bookstore.models;

import java.util.List;

public class MenuModel {
    private List<MenuItemModel> menuItems;

    public MenuModel(List<MenuItemModel> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItemModel> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItemModel> menuItems) {
        this.menuItems = menuItems;
    }
}
