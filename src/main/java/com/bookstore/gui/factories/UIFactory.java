package com.bookstore.gui.factories;

import com.bookstore.gui.component.menu.DrawerMenu;
import com.bookstore.gui.form.customer.views.CustomerFrame;
import com.bookstore.gui.form.salesman.view.SalesmanFrame;
import com.bookstore.models.MenuItemModel;
import com.bookstore.models.MenuModel;
import com.bookstore.models.SubMenuItemModel;
import com.bookstore.models.UserModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

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

    MenuModel menuModel = MenuFactory.getMenu(user.getRole());
    DrawerMenu drawerMenu = null;
  }
}
