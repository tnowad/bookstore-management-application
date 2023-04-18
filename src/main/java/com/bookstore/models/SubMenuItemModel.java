package com.bookstore.models;

import java.awt.event.ActionListener;

import javax.swing.Icon;

public class SubMenuItemModel {
    private String title;
    private Icon icon;
    private ActionListener actionListener;

    public SubMenuItemModel(String title, Icon icon, ActionListener actionListener) {
        this.title = title;
        this.icon = icon;
        this.actionListener = actionListener;
    }
}
