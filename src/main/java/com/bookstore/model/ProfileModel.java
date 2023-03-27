package com.bookstore.model;

public class ProfileModel {
  private static ProfileModel instance;
  private UserModel user;

  private ProfileModel() {
  }

  public static ProfileModel getInstance() {
    if (instance == null) {
      instance = new ProfileModel();
    }
    return instance;
  }

  public UserModel getUser() {
    return user;
  }

  public void setUser(UserModel user) {
    this.user = user;
  }

}
