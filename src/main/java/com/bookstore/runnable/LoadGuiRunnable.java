package com.bookstore.runnable;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.factories.UIFactory;
import com.bookstore.gui.main.LoginUI;

public class LoadGuiRunnable implements Runnable {

  @Override
  public void run() {
    LoginUI.getInstance().setVisible(true);
    try {
      UIFactory.showForm(UserBUS.getInstance().login("customer1", "$password"));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
