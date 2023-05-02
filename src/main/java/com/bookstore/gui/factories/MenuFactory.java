package com.bookstore.gui.factories;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.gui.components.dashboards.DashboardPanel;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.events.general.LogoutActionListener;
import com.bookstore.gui.forms.accounts.AccountSettings;
import com.bookstore.gui.forms.accounts.ProfileSettings;
import com.bookstore.gui.forms.books.BookList;
import com.bookstore.gui.forms.carts.Cart;
import com.bookstore.gui.forms.customer.Checkout;
import com.bookstore.gui.forms.customer.Discovery;
import com.bookstore.gui.forms.customer.HomeCustomer;
import com.bookstore.gui.forms.customer.Order;
import com.bookstore.gui.forms.imports.ImportList;
import com.bookstore.gui.forms.orders.OrderList;
import com.bookstore.gui.forms.users.CustomerList;
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
            EmployeeDAO.getInstance().getEmployeeById(user.getId()));
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
                    null));
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
                                showHomeAdmin));
                        add(
                            new SubMenuItemModel(
                                "View Customer",
                                new ImageIcon(""),
                                showHomeAdmin));
                        add(
                            new SubMenuItemModel(
                                "Edit Customer",
                                new ImageIcon(""),
                                showHomeAdmin));
                      }
                    }));
          }
        });
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
                                showHomeCustomer));
                      }
                    }));
            add(
                new MenuItemModel(
                    "Discovery",
                    new ImageIcon(""),
                    null,
                    new ArrayList<SubMenuItemModel>() {
                      {
                        add(
                            new SubMenuItemModel(
                                "Discovery",
                                new ImageIcon(""),
                                showDiscoveryCustomer));
                      }
                    }));

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
                                showCartCustomer));
                        add(
                            new SubMenuItemModel(
                                "Checkout",
                                new ImageIcon(""),
                                showCheckoutCustomer));
                        // my order
                        add(
                            new SubMenuItemModel(
                                "My Order",
                                new ImageIcon(""),
                                showMyOrderCustomer));
                      }
                    }));

            add(
                new MenuItemModel(
                    "Account",
                    new ImageIcon(""),
                    null,
                    new ArrayList<SubMenuItemModel>() {
                      {
                        add(
                            new SubMenuItemModel(
                                "My profile",
                                new ImageIcon(""),
                                showProfile));
                        add(
                            new SubMenuItemModel(
                                "My account",
                                new ImageIcon(""),
                                showAccount));
                      }
                    }));

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
                    }));

            add(
                new MenuItemModel(
                    "Logout",
                    new ImageIcon(""),
                    new LogoutActionListener(),
                    null));
          }
        });
  }

  public static MenuModel getMenuEmployeeSalesman() {
    return new MenuModel(
        new ArrayList<MenuItemModel>() {
          {
            add(new MenuItemModel("Customer managerment", new ImageIcon(""), showCustomerList, null));
            add(new MenuItemModel("Book management", new ImageIcon(""), showBookList, null));
            add(
                new MenuItemModel(
                    "Import management",
                    new ImageIcon(""),
                    showImportList, null));
            add(
                new MenuItemModel(
                    "Order management",
                    new ImageIcon(""),
                    showOrderList, null));
            // my account
            add(new MenuItemModel("My account", new ImageIcon(""), null,
                new ArrayList<SubMenuItemModel>() {
                  {
                    add(new SubMenuItemModel("My profile", new ImageIcon(""), showProfile));
                    add(new SubMenuItemModel("My account", new ImageIcon(""), showAccount));
                  }
                }));
          }
        });
  }

  public static MenuModel getMenuEmployeeManager() {
    return new MenuModel(
        new ArrayList<MenuItemModel>() {
          {
            add(new MenuItemModel("Home", new ImageIcon(""), null, null));
          }
        });
  }

  // admin
  private static ActionListener showHomeAdmin = e -> {
    MainPanel.getInstance().showForm(DashboardPanel.getInstance());
  };

  // manager

  // employee
  private static ActionListener showCustomerList = e -> {
    MainPanel.getInstance().showForm(CustomerList.getInstance());
  };
  private static ActionListener showBookList = e -> {
    MainPanel.getInstance().showForm(BookList.getInstance());
  };
  private static ActionListener showImportList = e -> {
    MainPanel.getInstance().showForm(ImportList.getInstance());
  };
  private static ActionListener showOrderList = e -> {
    MainPanel.getInstance().showForm(OrderList.getInstance());
  };

  // customer
  private static ActionListener showHomeCustomer = e -> {
    MainPanel.getInstance().showForm(HomeCustomer.getInstance());
  };
  private static ActionListener showDiscoveryCustomer = e -> {
    MainPanel.getInstance().showForm(Discovery.getInstance());
  };

  private static ActionListener showCartCustomer = e -> {
    MainPanel.getInstance().showForm(Cart.getInstance());
  };

  private static ActionListener showCheckoutCustomer = e -> {
    MainPanel.getInstance().showForm(Checkout.getInstance());
  };
  private static ActionListener showMyOrderCustomer = e -> {
    MainPanel.getInstance().showForm(Order.getInstance());
  };
  private static ActionListener showProfile = e -> {
    MainPanel.getInstance().showForm(ProfileSettings.getInstance());
  };
  private static ActionListener showAccount = e -> {
    MainPanel.getInstance().showForm(AccountSettings.getInstance());
  };

}
