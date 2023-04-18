package com.bookstore.gui.component.menu;

import java.awt.MenuItem;

import javax.swing.JPanel;

import com.bookstore.models.MenuItemModel;

public class DrawerMenuItem extends JPanel {
    private MenuItem menuItem;

    public DrawerMenuItem(MenuItemModel menuItemModel) {
        this.menuItem = menuItemModel;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}
