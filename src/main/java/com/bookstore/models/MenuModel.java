package com.bookstore.models;

import javax.swing.Icon;

public class MenuModel {

  private Icon icon;
  private String menuName;
  private MenuItemModel[] subMenu;

  public Icon getIcon() {
    return icon;
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
  }

  public String getMenuName() {
    return menuName;
  }

  public void setMenuName(String menuName) {
    this.menuName = menuName;
  }

  public MenuItemModel[] getSubMenu() {
    return subMenu;
  }

  public void setSubMenu(MenuItemModel[] subMenu) {
    this.subMenu = subMenu;
  }

  public MenuModel(Icon icon, String menuName, MenuItemModel... subMenu) {
    this.icon = icon;
    this.menuName = menuName;
    this.subMenu = subMenu;
  }

  public MenuModel() {
  }
}
