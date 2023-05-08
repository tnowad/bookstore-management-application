package com.bookstore.gui.forms.imports;

import com.bookstore.interfaces.IFindModelForm;
import com.bookstore.models.BookModel;
import javax.swing.JPanel;

public class BookSearchForm
  extends JPanel
  implements IFindModelForm<BookModel> {

  public BookSearchForm() {
    initComponents();
  }

  private void initComponents() {}

  @Override
  public BookModel find() {
    return null;
  }
}
