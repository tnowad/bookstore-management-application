package com.bookstore.runnable;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.factories.UIFactory;
import com.bookstore.gui.main.LoginUI;
import com.bookstore.services.Authentication;

public class LoadGuiRunnable implements Runnable {

  @Override
  public void run() {
    // new LoginUI().setVisible(true);
    try {
      UIFactory.showForm(UserBUS.getInstance().login("admin", "password"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
