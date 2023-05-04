package com.bookstore.gui.events.general;

import com.bookstore.gui.main.LoginFrame;
import com.bookstore.gui.main.MainFrame;
import com.bookstore.services.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class LogoutActionListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    int option = JOptionPane.showConfirmDialog(
      null,
      "Are you sure you want to logout?",
      "Logout",
      JOptionPane.YES_NO_OPTION
    );
    if (option == JOptionPane.YES_OPTION) {
      Authentication.logout();

      MainFrame.getInstance().dispose();
      MainFrame.getInstance().setVisible(false);
      LoginFrame.getInstance().setVisible(true);
    }
  }
}
