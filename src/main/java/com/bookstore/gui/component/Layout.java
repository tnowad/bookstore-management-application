package com.bookstore.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

import javax.swing.Icon;

import com.bookstore.gui.model.MenuModel;
import com.bookstore.model.UserModel;
import com.bookstore.gui.form.Form;
import com.bookstore.gui.model.MenuItemModel;

public class Layout {

  private MenuModel[] menuModel;
  private UserModel userModel;

  public Layout(UserModel userModel) {
    menuModel = new MenuModel[] {
        new MenuModel(null, "Home",
            new MenuItemModel[] {
                new MenuItemModel("Home", new Form("Home")),
                new MenuItemModel("About", new Form("About")),
                new MenuItemModel("Contact", new Form("Contact")),
            }),
        new MenuModel(null, "Book", new MenuItemModel[] {
            new MenuItemModel("Book", new Form("Book")),
            new MenuItemModel("Category", new Form("Category")),
            new MenuItemModel("Author", new Form("Author")),
            new MenuItemModel("Publisher", new Form("Publisher")),
        }),
        new MenuModel(null, "User", new MenuItemModel[] {
            new MenuItemModel("User", new Form("User")),
            new MenuItemModel("Role", new Form("Role")),
        }),
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
