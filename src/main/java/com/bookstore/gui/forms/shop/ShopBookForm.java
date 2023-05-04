package com.bookstore.gui.forms.shop;

import com.bookstore.gui.components.tables.Table;
import com.bookstore.interfaces.ISearchable;
import javax.swing.JPanel;

public class ShopBookForm extends JPanel implements ISearchable {

  private Table bookTable;

  public ShopBookForm() {
    initComponents();
  }

  private void initComponents() {
    bookTable = new Table();
    add(bookTable);
  }

  @Override
  public void search(String keyword) {}
}
