package com.bookstore.gui.factories;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.gui.components.dashboards.DashboardPanel;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.events.general.LogoutActionListener;
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
          add(
            new MenuItemModel(
              "Home",
              new ImageIcon(""),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Shop",
                      new ImageIcon(""),
                      showHomeAdmin
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Cart",
              new ImageIcon(""),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "View Cart",
                      new ImageIcon(""),
                      showHomeAdmin
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Checkout",
                      new ImageIcon(""),
                      showHomeAdmin
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Account",
              new ImageIcon(""),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "View Account",
                      new ImageIcon(""),
                      showHomeAdmin
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Edit Account",
                      new ImageIcon(""),
                      showHomeAdmin
                    )
                  );

                  add(new SubMenuItemModel("Address", new ImageIcon(""), null));
                  add(new SubMenuItemModel("Orders", new ImageIcon(""), null));
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Other",
              new ImageIcon(""),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(new SubMenuItemModel("About", new ImageIcon(""), null));
                  add(new SubMenuItemModel("Contact", new ImageIcon(""), null));
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Logout",
              new ImageIcon(""),
              new LogoutActionListener(),
              new ArrayList<SubMenuItemModel>() {
                {
                  add(new SubMenuItemModel("Logout", new ImageIcon(""), null));
                }
              }
            )
          );
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
}
