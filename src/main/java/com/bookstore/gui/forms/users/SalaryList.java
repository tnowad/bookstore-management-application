package com.bookstore.gui.forms.users;

import javax.swing.JPanel;

public class SalaryList extends JPanel {

  private static SalaryList instance;

  public static SalaryList getInstance() {
    if (instance == null) {
      instance = new SalaryList();
    }
    return instance;
  }
}
