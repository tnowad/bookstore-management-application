package com.bookstore.gui.factory;

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
    return new MenuModel(IconFactory.createIcon(icon), name, menuItemModel);
  }

  public static Menu createMenu(String name) {
    Menu menu = new Menu();
    if (name.equals("customer")) {
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("product"),
              "Product",
              MenuItemFactory.createMenuItemModel(
                  "customer.product",
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
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("dashboard"),
              "Dashboard",
              MenuItemFactory.createMenuItemModel(
                  "admin.dashboard", "admin.order", "admin.product", "admin.import", "admin.employee",
                  "admin.customer")));
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("promotion"),
              "Promotion",
              MenuItemFactory.createMenuItemModel(
                  "admin.promotionList",
                  "admin.promotionHistory")));
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("employee"),
              "Employee",
              MenuItemFactory.createMenuItemModel(
                  "admin.employeeList",
                  "admin.employeeHistory")));
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("customer"),
              "Customer",
              MenuItemFactory.createMenuItemModel(
                  "admin.customerList",
                  "admin.customerHistory")));
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("import"),
              "Import",
              MenuItemFactory.createMenuItemModel(
                  "admin.importList",
                  "admin.importHistory")));
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("order"),
              "Order",
              MenuItemFactory.createMenuItemModel(
                  "admin.orderList",
                  "admin.orderHistory")));
      menu.addMenu(
          new MenuModel(
              IconFactory.createIcon("other"),
              "Other",
              MenuItemFactory.createMenuItemModel(
                  "admin.about", "admin.settings", "general.logout")));
    }
    return menu;
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.add(MenuFactory.createMenu("admin"));
    frame.pack();
    frame.setVisible(true);
  }
}
