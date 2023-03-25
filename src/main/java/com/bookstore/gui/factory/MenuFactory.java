package com.bookstore.gui.factory;

import javax.swing.Icon;
import javax.swing.JFrame;

import com.bookstore.gui.component.Menu;
import com.bookstore.gui.model.MenuItemModel;
import com.bookstore.gui.model.MenuModel;

public class MenuFactory {
  public static MenuItemModel[] createMenuItemModel(String... menuItems) {
    MenuItemModel[] menuItemModel = new MenuItemModel[menuItems.length];
    for (int i = 0; i < menuItems.length; i++) {
      menuItemModel[i] = new MenuItemModel(menuItems[i]);
    }
    return menuItemModel;
  }

  public static MenuModel createMenuModel(String icon, String name, MenuItemModel[] menuItemModel) {
    MenuModel menuModel = new MenuModel(
        IconFactory.createIcon(icon),
        name,
        menuItemModel);
    return menuModel;
  }

  public static Menu createMenu(String name) {
    Menu menu = new Menu();
    if (name.equals("customer")) {
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("product"),
              "Product",
              MenuItemFactory.createMenuItemModel(
                  "customer.search",
                  "customer.cart")));
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("order"),
              "Order",
              MenuItemFactory.createMenuItemModel(
                  "customer.order",
                  "customer.orderHistory")));
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("payment"),
              "Payment",
              MenuItemFactory.createMenuItemModel(
                  "customer.payment",
                  "customer.paymentHistory")));
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("profile"),
              "Profile",
              MenuItemFactory.createMenuItemModel(
                  "customer.profile",
                  "customer.changePassword")));
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("other"),
              "Other",
              MenuItemFactory.createMenuItemModel(
                  "customer.logout")));
    } else if (name.equals("admin")) {

    }
    return menu;
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(MenuFactory.createMenu("customer"));
    frame.pack();
    frame.setVisible(true);
  }
}
