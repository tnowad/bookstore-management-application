package com.bookstore.gui.factories;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.gui.components.authors.AuthorListPanel;
import com.bookstore.gui.components.books.BrowseProductPanel;
import com.bookstore.gui.components.dashboards.DashboardPanel;
import com.bookstore.gui.components.orders.OrderListPanel;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.components.providers.ProviderListPanel;
import com.bookstore.gui.components.publishers.PublisherListPanel;
import com.bookstore.gui.components.salary.SalaryListPanel;
import com.bookstore.gui.components.users.UserListPanel;
import com.bookstore.gui.events.general.ExitActionListener;
import com.bookstore.gui.events.general.LogoutActionListener;
import com.bookstore.gui.forms.accounts.AccountSettings;
import com.bookstore.gui.forms.accounts.ProfileSettings;
import com.bookstore.gui.forms.books.BookList;
import com.bookstore.gui.forms.carts.Cart;
import com.bookstore.gui.forms.customer.Checkout;
import com.bookstore.gui.forms.customer.Order;
import com.bookstore.gui.forms.customer.Shop;
import com.bookstore.gui.forms.general.AboutUs;
import com.bookstore.gui.forms.general.ContactUs;
import com.bookstore.gui.forms.imports.ImportList;
import com.bookstore.gui.forms.orders.OrderList;
import com.bookstore.gui.forms.users.CustomerList;
import com.bookstore.gui.forms.users.EmployeeList;
import com.bookstore.gui.forms.users.SalaryList;
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
              new ImageIcon("src/main/java/resources/icons/menu-white.png"),
              showHomeAdmin,
              null
            )
          );

          add(
            new MenuItemModel(
              "Report",
              new ImageIcon("src/main/java/resources/icons/report.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Sales report",
                      new ImageIcon(""),
                      null
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Import report",
                      new ImageIcon(""),
                      null
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Export report",
                      new ImageIcon(""),
                      null
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Book management",
              new ImageIcon("src/main/java/resources/icons/book.png"),
              showBookListAdmin,
              null
            )
          );

          add(
            new MenuItemModel(
              "Import management",
              new ImageIcon("src/main/java/resources/icons/import.png"),
              showImportList,
              null
            )
          );

          add(
            new MenuItemModel(
              "Order management",
              new ImageIcon("src/main/java/resources/icons/cart.png"),
              showOrderListAdmin,
              null
            )
          );

          add(
            new MenuItemModel(
              "User management",
              new ImageIcon("src/main/java/resources/icons/user.png"),
              showUserListAdmin,
              null
            )
          );
          add(
            new MenuItemModel(
              "Contact management",
              new ImageIcon("src/main/java/resources/icons/user.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Publisher management",
                      new ImageIcon(""),
                      showPublisherListAdmin
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Author management",
                      new ImageIcon(""),
                      showAuthorListAdmin
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Provider management",
                      new ImageIcon(""),
                      showProviderListAdmin
                    )
                  );
                }
              }
            )
          );
          add(
            new MenuItemModel(
              "Salary management",
              new ImageIcon("src/main/java/resources/icons/user.png"),
              showSalaryListAdmin,
              null
            )
          );

          add(
            new MenuItemModel(
              "My account",
              new ImageIcon("src/main/java/resources/icons/profile.jpg"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "My profile",
                      new ImageIcon(""),
                      showProfile
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "My account",
                      new ImageIcon(""),
                      showAccount
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Others",
              new ImageIcon("src/main/java/resources/icons/others.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Contact us",
                      new ImageIcon(""),
                      showContact
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "About us",
                      new ImageIcon(""),
                      showAboutUs
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Logout",
              new ImageIcon("src/main/java/resources/icons/logout.png"),
              new LogoutActionListener(),
              null
            )
          );

          add(
            new MenuItemModel(
              "Exit",
              new ImageIcon("src/main/java/resources/icons/exit.png"),
              new ExitActionListener(),
              null
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
              "Shop",
              new ImageIcon("src/main/java/resources/icons/shop.png"),
              showHomeCustomer,
              null
            )
          );

          add(
            new MenuItemModel(
              "Cart",
              new ImageIcon("src/main/java/resources/icons/cart.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "View Cart",
                      new ImageIcon(""),
                      showCartCustomer
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Order History",
                      new ImageIcon(""),
                      showMyOrderCustomer
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "My account",
              new ImageIcon("src/main/java/resources/icons/profile.jpg"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "My profile",
                      new ImageIcon(""),
                      showProfile
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "My account",
                      new ImageIcon(""),
                      showAccount
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Others",
              new ImageIcon("src/main/java/resources/icons/others.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Contact us",
                      new ImageIcon(""),
                      showContact
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "About us",
                      new ImageIcon(""),
                      showAboutUs
                    )
                  );
                }
              }
            )
          );
          add(
            new MenuItemModel(
              "Logout",
              new ImageIcon("src/main/java/resources/icons/logout.png"),
              new LogoutActionListener(),
              null
            )
          );
          add(
            new MenuItemModel(
              "Exit",
              new ImageIcon("src/main/java/resources/icons/exit.png"),
              new ExitActionListener(),
              null
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
          add(
            new MenuItemModel(
              "Shop",
              new ImageIcon("src/main/java/resources/icons/shop.png"),
              null,
              null
            )
          );

          add(
            new MenuItemModel(
              "Cart",
              new ImageIcon("src/main/java/resources/icons/cart.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "View Cart",
                      new ImageIcon(""),
                      showCartCustomer
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Order History",
                      new ImageIcon(""),
                      showMyOrderCustomer
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Customer management",
              new ImageIcon(""),
              showCustomerList,
              null
            )
          );

          add(
            new MenuItemModel(
              "Order management",
              new ImageIcon(""),
              showOrderList,
              null
            )
          );

          add(
            new MenuItemModel(
              "My account",
              new ImageIcon("src/main/java/resources/icons/profile.jpg"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "My profile",
                      new ImageIcon(""),
                      showProfile
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "My account",
                      new ImageIcon(""),
                      showAccount
                    )
                  );
                }
              }
            )
          );
          add(
            new MenuItemModel(
              "Others",
              new ImageIcon("src/main/java/resources/icons/others.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Contact us",
                      new ImageIcon(""),
                      showContact
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "About us",
                      new ImageIcon(""),
                      showAboutUs
                    )
                  );
                }
              }
            )
          );
          add(
            new MenuItemModel(
              "Logout",
              new ImageIcon("src/main/java/resources/icons/logout.png"),
              new LogoutActionListener(),
              null
            )
          );
          add(
            new MenuItemModel(
              "Exit",
              new ImageIcon("src/main/java/resources/icons/exit.png"),
              new ExitActionListener(),
              null
            )
          );
        }
      }
    );
  }

  public static MenuModel getMenuEmployeeManager() {
    return new MenuModel(
      new ArrayList<MenuItemModel>() {
        {
          add(
            new MenuItemModel(
              "Employee management",
              new ImageIcon(""),
              showEmployeeList,
              null
            )
          );
          add(
            new MenuItemModel(
              "Salary management",
              new ImageIcon(""),
              showSalaryList,
              null
            )
          );
          add(
            new MenuItemModel(
              "Customer manager",
              new ImageIcon(""),
              showCustomerList,
              null
            )
          );
          add(
            new MenuItemModel(
              "Book management",
              new ImageIcon(""),
              showBookList,
              null
            )
          );
          add(
            new MenuItemModel(
              "Import management",
              new ImageIcon(""),
              showImportList,
              null
            )
          );
          add(
            new MenuItemModel(
              "Order management",
              new ImageIcon(""),
              showOrderList,
              null
            )
          );

          add(
            new MenuItemModel(
              "My account",
              new ImageIcon("src/main/java/resources/icons/profile.jpg"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "My profile",
                      new ImageIcon(""),
                      showProfile
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "My account",
                      new ImageIcon(""),
                      showAccount
                    )
                  );
                }
              }
            )
          );
          add(
            new MenuItemModel(
              "Others",
              new ImageIcon("src/main/java/resources/icons/others.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Contact us",
                      new ImageIcon(""),
                      showContact
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "About us",
                      new ImageIcon(""),
                      showAboutUs
                    )
                  );
                }
              }
            )
          );
          add(
            new MenuItemModel(
              "Logout",
              new ImageIcon("src/main/java/resources/icons/logout.png"),
              new LogoutActionListener(),
              null
            )
          );
          add(
            new MenuItemModel(
              "Exit",
              new ImageIcon("src/main/java/resources/icons/exit.png"),
              new ExitActionListener(),
              null
            )
          );
        }
      }
    );
  }

  // admin
  private static ActionListener showHomeAdmin = e -> {
    MainPanel.getInstance().showForm(new DashboardPanel());
  };
  private static ActionListener showBookListAdmin = e -> {
    MainPanel.getInstance().showForm(BrowseProductPanel.getInstance());
  };
  private static ActionListener showUserListAdmin = e -> {
    MainPanel.getInstance().showForm(UserListPanel.getInstance());
  };
  private static ActionListener showOrderListAdmin = e -> {
    MainPanel.getInstance().showForm(OrderListPanel.getInstance());
  };
  private static ActionListener showPublisherListAdmin = e -> {
    MainPanel.getInstance().showForm(PublisherListPanel.getInstance());
  };
  private static ActionListener showAuthorListAdmin = e -> {
    MainPanel.getInstance().showForm(AuthorListPanel.getInstance());
  };
  private static ActionListener showProviderListAdmin = e -> {
    MainPanel.getInstance().showForm(ProviderListPanel.getInstance());
  };
  private static ActionListener showSalaryListAdmin = e -> {
    MainPanel.getInstance().showForm(SalaryListPanel.getInstance());
  };

  // employee and manager
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

  // manager
  private static ActionListener showEmployeeList = e -> {
    MainPanel.getInstance().showForm(EmployeeList.getInstance());
  };

  private static ActionListener showSalaryList = e -> {
    MainPanel.getInstance().showForm(SalaryList.getInstance());
  };

  // customer
  private static ActionListener showHomeCustomer = e -> {
    MainPanel.getInstance().showForm(Shop.getInstance());
  };
  private static ActionListener showDiscoveryCustomer = e -> {
    MainPanel.getInstance().showForm(Shop.getInstance());
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
  // general
  private static ActionListener showProfile = e -> {
    MainPanel.getInstance().showForm(ProfileSettings.getInstance());
  };
  private static ActionListener showAccount = e -> {
    MainPanel.getInstance().showForm(AccountSettings.getInstance());
  };
  private static ActionListener showContact = e -> {
    MainPanel.getInstance().showForm(ContactUs.getInstance());
  };
  private static ActionListener showAboutUs = e -> {
    MainPanel.getInstance().showForm(AboutUs.getInstance());
  };
}
