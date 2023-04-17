package com.bookstore.models;

import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.Icon;

public class MenuItemModel {
    private String title;
    private Icon icon;
    private ActionListener actionListener;
    private List<SubMenuItemModel> subMenuItems;

    public MenuItemModel(String title, Icon icon, ActionListener actionListener) {
        this.title = title;
        this.icon = icon;
        this.actionListener = actionListener;
    }

    public MenuItemModel(String title, Icon icon, ActionListener actionListener, List<SubMenuItemModel> subMenuItems) {
        this.title = title;
        this.icon = icon;
        this.actionListener = actionListener;
        this.subMenuItems = subMenuItems;
    }

    public String getTitle() {
        return title;
    }

    public Icon getIcon() {
        return icon;
    }

    public ActionListener getActionListener() {
        return actionListener;
    }

    public List<SubMenuItemModel> getSubMenuItems() {
        return subMenuItems;
    }
}
