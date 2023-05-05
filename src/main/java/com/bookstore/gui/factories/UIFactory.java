package com.bookstore.gui.factories;

import com.bookstore.gui.components.menus.MenuPanel;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.main.MainFrame;
import com.bookstore.models.UserModel;
import javax.swing.JOptionPane;

public class UIFactory {

  public static void showForm(UserModel user) {
    if (user == null) {
      JOptionPane.showMessageDialog(
        null,
        "User not found. Please try again!",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
      return;
    }

    MenuPanel.destroyInstance();
    MainFrame.destroyInstance();
    MainPanel.destroyInstance();

    MenuPanel.updateInstance(new MenuPanel(MenuFactory.getMenu(user)));

    MainFrame.getInstance().revalidate();
    MainFrame.getInstance().setVisible(true);
  }
}
