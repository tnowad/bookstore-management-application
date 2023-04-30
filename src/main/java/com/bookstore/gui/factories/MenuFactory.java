package com.bookstore.gui.factories;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.MenuModel;
import com.bookstore.models.SubMenuItemModel;
import com.bookstore.models.UserModel.Role;
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

  public static MenuModel getMenu(Role role) {
    switch (role) {
      case ADMIN:
        return getAdminMenu();
      case CUSTOMER:
        return getCustomerMenu();
      case SALESMAN:
        return getSalesmanMenu();
      default:
        return null;
    }
  }
}
