package com.bookstore.runnable;

import com.bookstore.gui.main.LoginFrame;

public class LoadGuiRunnable implements Runnable {

  @Override
  public void run() {
    LoginFrame.getInstance().setVisible(true);
  }
}
