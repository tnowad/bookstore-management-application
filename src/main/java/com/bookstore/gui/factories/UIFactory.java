package com.bookstore.gui.factories;

import com.bookstore.dao.EmployeeDAO;
import com.bookstore.enums.UserRole;
import com.bookstore.gui.components.header.HeaderDashboard;
import com.bookstore.gui.components.menu.DrawerMenu;
import com.bookstore.gui.components.panel.MainPanel;
import com.bookstore.gui.forms.customer.CustomerFrame;
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
import javax.swing.JFrame;

public class UIFactory {

  public static void showForm(UserModel user) {
    if (user == null) {
      System.out.println("User is null");
      return;
    }

    System.out.println(user);
    if (user.getRole() == UserRole.EMPLOYEE) {
      System.out.println(
        EmployeeDAO.getInstance().getEmployeeById(user.getId())
      );
    }

    MenuModel menuModel = MenuFactory.getMenu(user);

    DrawerMenu drawerMenu = new DrawerMenu(menuModel);

    MainPanel mainPanel = MainPanel.getInstance();

    HeaderDashboard headerDashboard = new HeaderDashboard();
    MainUI mainUI = new MainUI(drawerMenu, mainPanel, headerDashboard);

    mainUI.setVisible(true);
  }
}
