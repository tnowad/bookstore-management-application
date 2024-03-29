package com.bookstore.services;

import com.bookstore.bus.UserBUS;
import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import com.bookstore.models.UserModel;

public class Authentication {

  private static Authentication instance;
  private UserModel currentUser;

  private Authentication() {
    currentUser = null;
  }

  public static Authentication getInstance() {
    if (instance == null) {
      instance = new Authentication();
    }
    return instance;
  }

  public static UserModel getCurrentUser() {
    if (Authentication.getInstance().currentUser == null) {
      return null;
    }
    setCurrentUser(
      UserBUS
        .getInstance()
        .getModelById(Authentication.getInstance().currentUser.getId())
    );
    return Authentication.getInstance().currentUser;
  }

  public static void setCurrentUser(UserModel currentUser) {
    if (currentUser == null) {
      throw new RuntimeException("User is null");
    }

    if (currentUser.getStatus() != UserStatus.ACTIVE) {
      throw new RuntimeException("User is not active");
    }

    Authentication.getInstance().currentUser = currentUser;
  }

  public static void logout() {
    Authentication.getInstance().currentUser = null;
  }

  public static boolean isLoggedIn() {
    return Authentication.getInstance().currentUser != null;
  }

  public static boolean isRole(UserRole role) {
    if (!Authentication.isLoggedIn()) {
      return false;
    }
    return Authentication.getInstance().currentUser.getRole().equals(role);
  }
}
