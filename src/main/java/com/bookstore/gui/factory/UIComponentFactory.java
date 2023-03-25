package com.bookstore.gui.factory;

import com.bookstore.gui.component.Header;
import com.bookstore.gui.component.Menu;
import com.bookstore.gui.form.MainForm;

public class UIComponentFactory {
  public static Menu createMenu(String name) {

    return new Menu();
  }

  public static Header createHeader(String name) {
    return new Header();
  }

  public static MainForm createMainForm(String name) {
    return new MainForm();
  }

}
