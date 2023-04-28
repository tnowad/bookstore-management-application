package com.bookstore.gui.factories;

import com.bookstore.gui.form.customer.views.CustomerFrame;
import com.bookstore.gui.form.salesman.view.SalesmanFrame;
import com.bookstore.models.UserModel;

public class UIFactory {

  public static void showForm(UserModel user) {
    if (user == null) {
      System.out.println("User is null");
      return;
    }
    System.out.println("User: " + user.getUsername());
    System.out.println("Password: " + user.getPassword());
    System.out.println("Role: " + user.getRole());
    System.out.println("Status: " + user.getStatus());

    switch (user.getRole()) {
      case ADMIN:
        System.out.println("Admin form");
        break;
      case CUSTOMER:
        System.out.println("Customer form");
        new CustomerFrame();
        break;
      case EMPLOYEE:
        System.out.println("Employee form");
        new SalesmanFrame();
        break;
      default:
        System.out.println("Unknown role");
        break;
    }
  }
}
