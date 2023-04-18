package com.bookstore.gui.component.menu;

import java.awt.MenuItem;

import javax.swing.JPanel;

public class DrawerMenuItem extends JPanel {
    private MenuItem menuItem;

    public DrawerMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
}
