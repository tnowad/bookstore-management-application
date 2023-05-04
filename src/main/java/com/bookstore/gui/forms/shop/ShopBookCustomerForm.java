package com.bookstore.gui.forms.shop;

import com.bookstore.gui.components.tables.Table;
import com.bookstore.interfaces.ISearchable;
import javax.swing.JPanel;

public class ShopBookCustomerForm extends JPanel implements ISearchable {

  private Table bookTable;

  public ShopBookCustomerForm() {
    initComponents();
  }

  private void initComponents() {
    bookTable = new Table();
    add(bookTable);
  }

  @Override
  public void search(String keyword) {}
}
