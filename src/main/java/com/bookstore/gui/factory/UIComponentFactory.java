package com.bookstore.gui.factory;

import com.bookstore.gui.component.Header;
import com.bookstore.gui.component.Menu;
import com.bookstore.gui.form.MainForm;
import com.bookstore.gui.form.customer.CustomerForm;
import com.bookstore.gui.main.Main;

public class UIComponentFactory {
  public static Menu createMenu(String name) {
    return MenuFactory.createMenu(name);
  }

  public static Header createHeader() {
    return Header.getInstance();
  }

  public static MainForm createMainForm() {
    return MainForm.getInstance();
  }

  public static Main createMain(String name) {
    return new Main(UIComponentFactory.createMenu(name),
        UIComponentFactory.createHeader(),
        UIComponentFactory.createMainForm());
  }

  public static void main(String[] args) {
    UIComponentFactory.createMain("customer").setVisible(true);
    new Thread(() -> {
      try {
        Thread.sleep(5000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      MainForm.getInstance().showForm(new CustomerForm());
    }).start();
  }

}
