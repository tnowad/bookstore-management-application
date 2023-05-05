package com.bookstore.models.gui;

import java.awt.event.ActionListener;
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

  public ActionListener getFirstActionListener() {
    if (menuItems.size() > 0) {
      if (menuItems.get(0).getActionListener() != null) {
        return menuItems.get(0).getActionListener();
      } else if (menuItems.get(0).getSubMenuItems().size() > 0) {
        return menuItems.get(0).getSubMenuItems().get(0).getActionListener();
      }
    }
    return null;
  }
}
