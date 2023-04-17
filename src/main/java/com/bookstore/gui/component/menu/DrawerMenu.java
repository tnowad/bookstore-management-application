package com.bookstore.gui.component.menu;

import javax.swing.JPanel;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.MenuModel;

public class DrawerMenu extends JPanel {
    private MenuModel menuModel;

    public DrawerMenu(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }

    public void addMenuItem(MenuItemModel menuItemModel) {
        
    }
}
