package com.bookstore.gui.factories;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.gui.form.salesman.view.SalesmanFrame;
import com.bookstore.models.EmployeeModel;
import com.bookstore.models.MenuItemModel;
import com.bookstore.models.MenuModel;
import com.bookstore.models.SubMenuItemModel;
import com.bookstore.models.UserModel;
import com.bookstore.models.UserModel.Role;
import java.awt.MenuItem;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class MenuFactory {

  public static MenuModel getAdminMenu() {
    return new MenuModel(
      new ArrayList<MenuItemModel>() {
        {
          add(new MenuItemModel("Home", new ImageIcon(""), showHome, null));
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
  }

  public static MenuModel getCustomerMenu() {
    return new MenuModel(null);
  }

  public static MenuModel getMenu(UserModel user) {
    switch (user.getRole()) {
      case ADMIN:
        return getAdminMenu();
      case CUSTOMER:
        return getCustomerMenu();
      case EMPLOYEE:
        return getMenuEmployee(
          EmployeeDAO.getInstance().getEmployeeById(user.getId())
        );
      default:
        return new MenuModel(new ArrayList<MenuItemModel>());
    }
  }

  public static MenuModel getMenuEmployee(EmployeeModel employee) {
    return new MenuModel(null);
  }

  private static ActionListener showHome = e -> {
    System.out.println("Show home");
  };
}
