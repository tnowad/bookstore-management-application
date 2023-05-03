package com.bookstore.gui.events.general;

import com.bookstore.gui.main.LoginFrame;
import com.bookstore.gui.main.MainFrame;
import com.bookstore.services.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutActionListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    Authentication.logout();
    MainFrame.getInstance().setVisible(false);
    LoginFrame.getInstance().setVisible(true);
  }
}
