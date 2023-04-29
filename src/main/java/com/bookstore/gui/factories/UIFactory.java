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

    MenuModel menuModel = null;
    DrawerMenu drawerMenu = null;

    switch (user.getRole()) {
      case ADMIN:
        System.out.println("Admin form");
        menuModel =
          new MenuModel(
            new ArrayList<MenuItemModel>() {
              {
                add(
                  new MenuItemModel("Home", new ImageIcon(""), showHome, null)
                );
                add(
                  new MenuItemModel(
                    "Customer",
                    new ImageIcon(""),
                    showHome,
                    new ArrayList<SubMenuItemModel>() {
                      {
                        add(
                          new SubMenuItemModel(
                            "Add Customer",
                            new ImageIcon(""),
                            showHome
                          )
                        );
                        add(
                          new SubMenuItemModel(
                            "View Customer",
                            new ImageIcon(""),
                            showHome
                          )
                        );
                        add(
                          new SubMenuItemModel(
                            "Edit Customer",
                            new ImageIcon(""),
                            showHome
                          )
                        );
                      }
                    }
                  )
                );
              }
            }
          );
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

    if (menuModel != null) {
      drawerMenu = new DrawerMenu(menuModel);
    }

    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(800, 600);
    frame.setLocationRelativeTo(null);
    frame.add(drawerMenu);
    frame.setVisible(true);
  }

  private static ActionListener showHome = e -> {
    System.out.println("Show home");
  };
}
