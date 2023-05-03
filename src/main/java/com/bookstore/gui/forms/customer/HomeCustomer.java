package com.bookstore.gui.forms.customer;

import javax.swing.JPanel;

public class HomeCustomer extends JPanel {

  private static HomeCustomer instance;

  public static HomeCustomer getInstance() {
    if (instance == null) {
      instance = new HomeCustomer();
    }
    return instance;
  }
}
