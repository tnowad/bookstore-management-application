package com.bookstore.gui.factories;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.gui.components.dashboards.DashboardPanel;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.forms.users.SalesmanFrame;
import com.bookstore.gui.main.MainUI;
import com.bookstore.models.EmployeeModel;
import com.bookstore.models.MenuItemModel;
import com.bookstore.models.MenuModel;
import com.bookstore.models.SubMenuItemModel;
import com.bookstore.models.UserModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class MenuFactory {

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
        throw new IllegalArgumentException("User role is not supported");
    }
  }

  public static MenuModel getMenuEmployee(EmployeeModel employee) {
    switch (employee.getEmployeeType()) {
      case EMPLOYEE_MANAGER:
        return getMenuEmployeeManager();
      case EMPLOYEE_SALES:
        return getMenuEmployeeSalesman();
      default:
        throw new IllegalArgumentException("Employee type is not supported");
    }
  }

  public static MenuModel getAdminMenu() {
    return new MenuModel(
      new ArrayList<MenuItemModel>() {
        {
          add(
            new MenuItemModel(
              "Dashboard",
              new ImageIcon(""),
              showHomeAdmin,
              null
            )
          );
          add(
            new MenuItemModel(
              "Customer",
              new ImageIcon(""),
              showHomeAdmin,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Add Customer",
                      new ImageIcon(""),
                      showHomeAdmin
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "View Customer",
                      new ImageIcon(""),
                      showHomeAdmin
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Edit Customer",
                      new ImageIcon(""),
                      showHomeAdmin
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
    return new MenuModel(
      new ArrayList<MenuItemModel>() {
        {
          add(new MenuItemModel("Home", new ImageIcon(""), null, null));
        }
      }
    );
  }

  public static MenuModel getMenuEmployeeSalesman() {
    return new MenuModel(
      new ArrayList<MenuItemModel>() {
        {
          add(new MenuItemModel("Home", new ImageIcon(""), null, null));
        }
      }
    );
  }

  public static MenuModel getMenuEmployeeManager() {
    return new MenuModel(
      new ArrayList<MenuItemModel>() {
        {
          add(new MenuItemModel("Home", new ImageIcon(""), null, null));
        }
      }
    );
  }

  private static ActionListener showHomeAdmin = e -> {
    MainPanel.getInstance().showForm(DashboardPanel.getInstance());
  };

  private static ActionListener logout = e -> {
    MainUI.getInstance().dispose();
  };
}
