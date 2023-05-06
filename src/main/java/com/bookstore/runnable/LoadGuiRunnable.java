package com.bookstore.runnable;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.factories.UIFactory;
import com.bookstore.gui.main.LoginFrame;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import javax.security.auth.login.LoginException;

public class LoadGuiRunnable implements Runnable {

  @Override
  public void run() {
    UserModel user;
    try {
      user = UserBUS.getInstance().login("customer7", "password");
      Authentication.setCurrentUser(user);
      UIFactory.showForm(user);
    } catch (LoginException e) {
      LoginFrame.getInstance().setVisible(true);
    }
  }
}
