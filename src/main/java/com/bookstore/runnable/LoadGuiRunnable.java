package com.bookstore.runnable;

import com.bookstore.gui.main.LoginUI;

public class LoadGuiRunnable implements Runnable {

  @Override
  public void run() {
    new LoginUI().setVisible(true);
  }
}
