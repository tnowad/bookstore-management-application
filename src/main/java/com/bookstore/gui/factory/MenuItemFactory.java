package com.bookstore.gui.factory;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bookstore.gui.model.MenuItemModel;

public class MenuItemFactory {
  public static String getMenuItemName(String id) {
    // Switch case
    switch (id) {
      // Case for general
      case "general.logout":
        return "Logout";
      case "general.exit":
        return "Exit";
      // Case for customer
      case "customer.product":
        return "Products";
      case "customer.cart":
        return "Cart";
      case "customer.order":
        return "Order Now";
      case "customer.orderHistory":
        return "Order History";
      case "customer.payment":
        return "Payment";
      case "customer.paymentHistory":
        return "Payment History";
      case "customer.profile":
        return "Profile";
      // Case for employee
      // Case for admin
      case "admin.dashboard":
        return "Dashboard";
      case "admin.order":
        return "Order";
      case "admin.product":
        return "Product";
      case "admin.import":
        return "Import";
      case "admin.employee":
        return "Employee";
      case "admin.customer":
        return "Customer";
      case "admin.promotionList":
        return "Promotion List";
      case "admin.promotionHistory":
        return "Promotion History";
      case "admin.employeeList":
        return "Employee List";
      case "admin.employeeHistory":
        return "Employee History";
      case "admin.customerList":
        return "Customer List";
      case "admin.customerHistory":
        return "Customer History";
      case "admin.importList":
        return "Import List";
      case "admin.importHistory":
        return "Import History";
      case "admin.orderList":
        return "Order List";
      case "admin.orderHistory":
        return "Order History";
      case "admin.about":
        return "About";
      case "admin.settings":
        return "Settings";
      case "admin.logout":
        return "Logout";
    }
    return id;
  }

  private static ActionListener getMenuItemActionListener(String name) {
    switch (name) {
      case "general.logout":
        return new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.out.println("Logout");
          }
        };
    }
    return new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println(name + " is clicked");
      }
    };
  }

  public static MenuItemModel createMenuItemModel(String name) {
    return new MenuItemModel(getMenuItemName(name), getMenuItemActionListener(name));
  }

  public static MenuItemModel[] createMenuItemModel(String... menuItems) {
    MenuItemModel[] menuItemModel = new MenuItemModel[menuItems.length];
    for (int i = 0; i < menuItems.length; i++) {
      menuItemModel[i] = createMenuItemModel(menuItems[i]);
    }
    return menuItemModel;
  }
}
