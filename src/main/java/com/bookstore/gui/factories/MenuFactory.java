package com.bookstore.gui.factories;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.gui.components.authors.AuthorListPanel;
import com.bookstore.gui.components.books.BrowseProductPanel;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.components.providers.ProviderListPanel;
import com.bookstore.gui.components.publishers.PublisherListPanel;
import com.bookstore.gui.components.salary.SalaryListPanel;
import com.bookstore.gui.components.users.UserListPanel;
import com.bookstore.gui.events.general.ExitActionListener;
import com.bookstore.gui.events.general.LogoutActionListener;
import com.bookstore.gui.forms.accounts.AccountPasswordForm;
import com.bookstore.gui.forms.accounts.AccountProfileForm;
import com.bookstore.gui.forms.books.BookListForm;
import com.bookstore.gui.forms.carts.CartCustomerForm;
import com.bookstore.gui.forms.dashboards.DashboardPanel;
import com.bookstore.gui.forms.general.AboutUsForm;
import com.bookstore.gui.forms.general.ContactUs;
import com.bookstore.gui.forms.imports.ImportList;
import com.bookstore.gui.forms.orders.OrderHistory;
import com.bookstore.gui.forms.orders.OrderList;
import com.bookstore.gui.forms.reports.SalesReportForm;
import com.bookstore.gui.forms.shop.ShopCustomerForm;
import com.bookstore.gui.forms.users.CustomerListForm;
import com.bookstore.gui.forms.users.EmployeeListForm;
import com.bookstore.models.EmployeeModel;
import com.bookstore.models.UserModel;
import com.bookstore.models.gui.MenuItemModel;
import com.bookstore.models.gui.MenuModel;
import com.bookstore.models.gui.SubMenuItemModel;
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
              "Sales Report",
              new ImageIcon("src/main/java/resources/icons/report.png"),
              showSalesReport,
              null
            )
          );

          add(
            new MenuItemModel(
              "Book Management",
              new ImageIcon("src/main/java/resources/icons/book.png"),
              showBookListAdmin,
              null
            )
          );

          add(
            new MenuItemModel(
              "Import Management",
              new ImageIcon("src/main/java/resources/icons/import.png"),
              showImportList,
              null
            )
          );

          add(
            new MenuItemModel(
              "Order Management",
              new ImageIcon("src/main/java/resources/icons/cart.png"),
              showOrderListAdmin,
              null
            )
          );

          add(
            new MenuItemModel(
              "User Management",
              new ImageIcon("src/main/java/resources/icons/user.png"),
              showUserListAdmin,
              null
            )
          );
          add(
            new MenuItemModel(
              "Contact Management",
              new ImageIcon("src/main/java/resources/icons/user.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Publisher Management",
                      new ImageIcon(
                        "src/main/java/resources/icons/publisher.png"
                      ),
                      showPublisherListAdmin
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Author Management",
                      new ImageIcon("src/main/java/resources/icons/author.png"),
                      showAuthorListAdmin
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Provider Management",
                      new ImageIcon(
                        "src/main/java/resources/icons/provider.png"
                      ),
                      showProviderListAdmin
                    )
                  );
                }
              }
            )
          );
          add(
            new MenuItemModel(
              "Salary Management",
              new ImageIcon("src/main/java/resources/icons/user.png"),
              showSalaryListAdmin,
              null
            )
          );

          add(
            new MenuItemModel(
              "Profile",
              new ImageIcon("src/main/java/resources/icons/profile.jpg"),
              showAccountProfile,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Update Profile",
                      new ImageIcon(
                        "src//main/java/resources/icons/profile.png"
                      ),
                      showAccountProfile
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Update Password",
                      new ImageIcon(
                        "src/main/java/resources/icons/password-reset.png"
                      ),
                      showAccountPassword
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
                      new ImageIcon(
                        "src/main/java/resources/icons/contact_us.png"
                      ),
                      showContact
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "About us",
                      new ImageIcon(
                        "src/main/java/resources/icons/about_us.png"
                      ),
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
              showShop,
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
                      new ImageIcon("src/main/java/resources/icons/card.png"),
                      showCartCustomer
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Order History",
                      new ImageIcon(
                        "src/main/java/resources/icons/order_icon.png"
                      ),
                      showMyOrderCustomer
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Profile",
              new ImageIcon("src/main/java/resources/icons/profile.jpg"),
              showAccountProfile,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Update Profile",
                      new ImageIcon(
                        "src//main/java/resources/icons/profile.png"
                      ),
                      showAccountProfile
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Update Password",
                      new ImageIcon(
                        "src/main/java/resources/icons/password-reset.png"
                      ),
                      showAccountPassword
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
                      new ImageIcon(
                        "src/main/java/resources/icons/contact_us.png"
                      ),
                      showContact
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "About us",
                      new ImageIcon(
                        "src/main/java/resources/icons/about_us.png"
                      ),
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
              showShop,
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
                      new ImageIcon("src/main/java/resources/icons/card.png"),
                      showCartCustomer
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Order History",
                      new ImageIcon(
                        "src/main/java/resources/icons/order_icon.png"
                      ),
                      showMyOrderCustomer
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Management",
              new ImageIcon("src/main/java/resources/icons/management.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Customer Management",
                      new ImageIcon(
                        "src/main/java/resources/icons/customer_icon.png"
                      ),
                      showCustomerList
                    )
                  );

                  add(
                    new SubMenuItemModel(
                      "Order Management",
                      new ImageIcon(
                        "src/main/java/resources/icons/order_icon.png"
                      ),
                      showOrderList
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Import Management",
                      new ImageIcon(
                        "src/main/java/resources/icons/import_icon.png"
                      ),
                      showImportList
                    )
                  );
                }
              }
            )
          );

          add(
            new MenuItemModel(
              "Profile",
              new ImageIcon("src/main/java/resources/icons/profile.jpg"),
              showAccountProfile,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Update Profile",
                      new ImageIcon(
                        "src//main/java/resources/icons/profile.png"
                      ),
                      showAccountProfile
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Change Password",
                      new ImageIcon(
                        "src/main/java/resources/icons/password-reset.png"
                      ),
                      showAccountPassword
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
                      new ImageIcon(
                        "src/main/java/resources/icons/contact_us.png"
                      ),
                      showContact
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "About us",
                      new ImageIcon(
                        "src/main/java/resources/icons/about_us.png"
                      ),
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
              "Book Management",
              new ImageIcon("src/main/java/resources/icons/book.png"),
              showBookListAdmin,
              null
            )
          );

          add(
            new MenuItemModel(
              "Import Management",
              new ImageIcon("src/main/java/resources/icons/import.png"),
              showImportList,
              null
            )
          );

          add(
            new MenuItemModel(
              "Order Management",
              new ImageIcon("src/main/java/resources/icons/cart.png"),
              showOrderListAdmin,
              null
            )
          );

          add(
            new MenuItemModel(
              "User Management",
              new ImageIcon("src/main/java/resources/icons/user.png"),
              showUserListAdmin,
              null
            )
          );
          add(
            new MenuItemModel(
              "Contact Management",
              new ImageIcon("src/main/java/resources/icons/user.png"),
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Publisher Management",
                      new ImageIcon(
                        "src/main/java/resources/icons/publisher.png"
                      ),
                      showPublisherListAdmin
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Author Management",
                      new ImageIcon("src/main/java/resources/icons/author.png"),
                      showAuthorListAdmin
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Provider Management",
                      new ImageIcon(
                        "src/main/java/resources/icons/provider.png"
                      ),
                      showProviderListAdmin
                    )
                  );
                }
              }
            )
          );
          add(
            new MenuItemModel(
              "Salary Management",
              new ImageIcon("src/main/java/resources/icons/user.png"),
              showSalaryListAdmin,
              null
            )
          );

          add(
            new MenuItemModel(
              "Profile",
              new ImageIcon("src/main/java/resources/icons/profile.jpg"),
              showAccountProfile,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Update Profile",
                      new ImageIcon(
                        "src//main/java/resources/icons/profile.png"
                      ),
                      showAccountProfile
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "Update Password",
                      new ImageIcon(
                        "src/main/java/resources/icons/password-reset.png"
                      ),
                      showAccountPassword
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
                      new ImageIcon(
                        "src/main/java/resources/icons/contact_us.png"
                      ),
                      showContact
                    )
                  );
                  add(
                    new SubMenuItemModel(
                      "About us",
                      new ImageIcon(
                        "src/main/java/resources/icons/about_us.png"
                      ),
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
    MainPanel.getInstance().showForm(new BrowseProductPanel());
  };
  private static ActionListener showUserListAdmin = e -> {
    MainPanel.getInstance().showForm(new UserListPanel());
  };
  private static ActionListener showOrderListAdmin = e -> {
    MainPanel.getInstance().showForm(new OrderList());
  };
  private static ActionListener showPublisherListAdmin = e -> {
    MainPanel.getInstance().showForm(new PublisherListPanel());
  };
  private static ActionListener showAuthorListAdmin = e -> {
    MainPanel.getInstance().showForm(new AuthorListPanel());
  };
  private static ActionListener showProviderListAdmin = e -> {
    MainPanel.getInstance().showForm(new ProviderListPanel());
  };
  private static ActionListener showSalaryListAdmin = e -> {
    MainPanel.getInstance().showForm(new SalaryListPanel());
  };

  // employee and manager
  private static ActionListener showCustomerList = e -> {
    MainPanel.getInstance().showForm(new CustomerListForm());
  };
  private static ActionListener showBookList = e -> {
    MainPanel.getInstance().showForm(new BookListForm());
  };
  private static ActionListener showImportList = e -> {
    MainPanel.getInstance().showForm(new ImportList());
  };
  private static ActionListener showOrderList = e -> {
    MainPanel.getInstance().showForm(new OrderList());
  };

  // manager
  private static ActionListener showEmployeeList = e -> {
    MainPanel.getInstance().showForm(new EmployeeListForm());
  };

  private static ActionListener showSalaryList = e -> {
    MainPanel.getInstance().showForm(new SalaryListPanel());
  };

  // customer
  private static ActionListener showShop = e -> {
    MainPanel.getInstance().showForm(new ShopCustomerForm());
  };
  private static ActionListener showCartCustomer = e -> {
    MainPanel.getInstance().showForm(new CartCustomerForm());
  };

  private static ActionListener showMyOrderCustomer = e -> {
    MainPanel.getInstance().showForm(new OrderHistory());
  };
  // general
  private static ActionListener showAccountProfile = e -> {
    MainPanel.getInstance().showForm(new AccountProfileForm());
  };
  private static ActionListener showAccountPassword = e -> {
    MainPanel.getInstance().showForm(new AccountPasswordForm());
  };
  private static ActionListener showContact = e -> {
    MainPanel.getInstance().showForm(new ContactUs());
  };
  private static ActionListener showAboutUs = e -> {
    MainPanel.getInstance().showForm(new AboutUsForm());
  };
  private static ActionListener showSalesReport = e -> {
    MainPanel.getInstance().showForm(new SalesReportForm());
  };
}
