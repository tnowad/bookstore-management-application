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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public void setActionListener(ActionListener actionListener) {
        this.actionListener = actionListener;
    }

}
