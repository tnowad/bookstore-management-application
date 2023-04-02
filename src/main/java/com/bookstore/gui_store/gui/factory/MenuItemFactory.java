package com.bookstore.gui.factory;

import java.awt.event.ActionListener;

import com.bookstore.gui.model.MenuItemModel;

public class MenuItemFactory {

  private MenuItemFactory() {
  }

  public static String getMenuItemName(String id) {
    // Switch case
    return switch (id) {
      // Case for general
      case "general.logout" -> "Logout";
      case "general.exit" -> "Exit";
      // Case for customer
      case "customer.product" -> "Products";
      case "customer.cart" -> "Cart";
      case "customer.order" -> "Order Now";
      case "customer.orderHistory" -> "Order History";
      case "customer.payment" -> "Payment";
      case "customer.paymentHistory" -> "Payment History";
      case "customer.profile" -> "Profile";
      // Case for employee
      // Case for admin
      case "admin.dashboard" -> "Dashboard";
      case "admin.order" -> "Order";
      case "admin.product" -> "Product";
      case "admin.import" -> "Import";
      case "admin.employee" -> "Employee";
      case "admin.customer" -> "Customer";
      case "admin.promotionList" -> "Promotion List";
      case "admin.promotionHistory" -> "Promotion History";
      case "admin.employeeList" -> "Employee List";
      case "admin.employeeHistory" -> "Employee History";
      case "admin.customerList" -> "Customer List";
      case "admin.customerHistory" -> "Customer History";
      case "admin.importList" -> "Import List";
      case "admin.importHistory" -> "Import History";
      case "admin.orderList" -> "Order List";
      case "admin.orderHistory" -> "Order History";
      case "admin.about" -> "About";
      case "admin.settings" -> "Settings";
      default -> id;
    };
  }

  private static ActionListener getMenuItemActionListener(String name) {
    return switch (name) {
      case "general.logout" -> e -> System.out.println("Logout is clicked");
      case "general.exit" -> e -> System.out.println("Exit is clicked");
      case "customer.product" -> e -> System.out.println("Products is clicked");
      default -> e -> System.out.println(name + " is clicked");
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
