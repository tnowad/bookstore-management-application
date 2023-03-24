package com.bookstore.gui.component;

import javax.swing.Icon;

import com.bookstore.gui.model.MenuModel;
import com.bookstore.model.UserModel;

public class Layout {

  private MenuModel[] menuModel;
  private UserModel userModel;

  public Layout(UserModel userModel) {
    menuModel = new MenuModel[] {
        new MenuModel(null, "Home", "Home"),
        new MenuModel(null, "Book", "Book", "Book List", "Book Category"),
        new MenuModel(null, "User", "User", "User List", "User Role"),
        new MenuModel(null, "Order", "Order", "Order List", "Order Detail"),
        new MenuModel(null, "Report", "Report", "Report List", "Report Detail"),
        new MenuModel(null, "Setting", "Setting", "Setting List", "Setting Detail")
    };
    this.userModel = userModel;
  }

  public MenuModel[] getMenuModel() {
    return menuModel;
  }

  public void setMenuModel(MenuModel[] menuModel) {
    this.menuModel = menuModel;
  }

  public UserModel getUserModel() {
    return userModel;
  }

  public void setUserModel(UserModel userModel) {
    this.userModel = userModel;
  }

}
