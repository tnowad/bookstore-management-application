package com.bookstore.models.gui;

import java.util.ArrayList;
import java.util.List;

public class MenuModel {
    private ArrayList<MenuItemModel> menuItems;

    public MenuModel(ArrayList<MenuItemModel> menuItems) {
        this.menuItems = menuItems;
    }

    public List<MenuItemModel> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItemModel> menuItems) {
        this.menuItems = menuItems;
    }
}
