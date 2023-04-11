package com.bookstore.services;

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
    return Authentication.getInstance().currentUser;
  }

  public static void setCurrentUser(UserModel currentUser) {
    Authentication.getInstance().currentUser = currentUser;
  }

  public static void logout() {
    Authentication.getInstance().currentUser = null;
  }

  public static boolean isLoggedIn() {
    return Authentication.getInstance().currentUser != null;
  }
}
