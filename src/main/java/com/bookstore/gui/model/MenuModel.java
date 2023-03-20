package com.bookstore.gui.model;

import javax.swing.Icon;

public class MenuModel {

  private Icon icon;
  private String menuName;
  private String subMenu[];

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

  public String[] getSubMenu() {
    return subMenu;
  }

  public void setSubMenu(String[] subMenu) {
    this.subMenu = subMenu;
  }

  public MenuModel(Icon icon, String menuName, String... subMenu) {
    this.icon = icon;
    this.menuName = menuName;
    this.subMenu = subMenu;
  }

  public MenuModel() {
  }
}
