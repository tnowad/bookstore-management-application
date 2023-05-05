package com.bookstore.gui.factories;

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.enums.UserRole;
import com.bookstore.gui.components.menus.MenuPanel;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.main.MainFrame;
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

    MenuPanel.destroyInstance();
    MainFrame.destroyInstance();
    MainPanel.destroyInstance();

    MenuPanel.updateInstance(new MenuPanel(MenuFactory.getMenu(user)));

    MainFrame.getInstance().revalidate();
    MainFrame.getInstance().setVisible(true);
  }
}
