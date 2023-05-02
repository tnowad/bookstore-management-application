package com.bookstore.gui.factories;

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.enums.UserRole;
import com.bookstore.gui.components.headers.Header;
import com.bookstore.gui.components.menus.MenuPanel;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.main.MainUI;
import com.bookstore.models.MenuModel;
import com.bookstore.models.UserModel;

public class UIFactory {

  public static void showForm(UserModel user) {
    if (user == null) {
      System.out.println("User is null");
      return;
    }

    System.out.println(user);
    if (user.getRole() == UserRole.EMPLOYEE) {
      System.out.println(EmployeeBUS.getInstance().getModelById(user.getId()));
    }

    MenuModel menuModel = MenuFactory.getMenu(user);

    MenuPanel drawerMenu = new MenuPanel(menuModel);

    MainPanel mainPanel = MainPanel.getInstance();

    Header headerDashboard = new Header();
    MainUI mainUI = new MainUI(drawerMenu, mainPanel, headerDashboard);

    mainUI.setVisible(true);
  }
}
