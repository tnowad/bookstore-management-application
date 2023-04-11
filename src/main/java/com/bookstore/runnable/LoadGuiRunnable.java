package com.bookstore.runnable;

import com.bookstore.gui.main.LoginUI;

public class LoadGuiRunnable implements Runnable {
  @Override
  public void run() {
    LoginUI loginUI = new LoginUI();
    loginUI.setVisible(true);
  }
}