package com.bookstore.gui.factories;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.gui.component.menu.DrawerMenu;
import com.bookstore.gui.form.customer.views.CustomerFrame;
import com.bookstore.gui.form.salesman.view.SalesmanFrame;
import com.bookstore.models.EmployeeModel;
import com.bookstore.models.MenuItemModel;
import com.bookstore.models.MenuModel;
import com.bookstore.models.SubMenuItemModel;
import com.bookstore.models.UserModel;
import com.bookstore.models.UserModel.Role;
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

    System.out.println(user);
    if (user.getRole() == Role.EMPLOYEE) {
      System.out.println(
        EmployeeDAO.getInstance().getEmployeeById(user.getId())
      );
    }

    MenuModel menuModel = MenuFactory.getMenu(user);
    DrawerMenu drawerMenu = null;
  }
}
