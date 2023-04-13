package com.bookstore.runnable;

import com.bookstore.gui.UIManagement;
import com.bookstore.gui.main.LoginUI;
import com.bookstore.gui.main.RegisterUI;

public class LoadGuiRunnable implements Runnable {
  @Override
  public void run() {
    UIManagement.setUI("loginUI", new LoginUI());
    UIManagement.setUI("registerUI", new RegisterUI());

    UIManagement.getUI("loginUI").setVisible(true);
    UIManagement.getUI("registerUI").setVisible(false);
  }
}