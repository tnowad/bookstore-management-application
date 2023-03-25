package com.bookstore.gui.factory;

import com.bookstore.gui.component.Header;
import com.bookstore.gui.component.Menu;
import com.bookstore.gui.form.MainForm;
import com.bookstore.gui.main.Main;

public class UIComponentFactory {
  public static Menu createMenu(String name) {
    return MenuFactory.createMenu(name);
  }

  public static Header createHeader(String name) {
    return new Header();
  }

  public static MainForm createMainForm(String name) {
    return new MainForm();
  }

  public static Main createMain(String name) {
    return new Main(UIComponentFactory.createMenu(name),
        UIComponentFactory.createHeader(name),
        UIComponentFactory.createMainForm(name));
  }

  public static void main(String[] args) {
    UIComponentFactory.createMain("customer").setVisible(true);
  }

}
