package com.bookstore.gui.forms.imports;

import com.bookstore.bus.BookBUS;
import com.bookstore.interfaces.IFindModelForm;
import com.bookstore.models.BookModel;
import com.bookstore.models.tables.BookTableModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class BookSearchForm
  extends JPanel
  implements IFindModelForm<BookModel> {

  private List<BookModel> bookList = BookBUS.getInstance().getAllModels();
  private BookModel BookModel;
  private JTable bookListTable;
  private BookTableModel bookTableModel;

  private JTextField bookTitleTextField;
  private JButton findBookButton;
  private JButton addBookButton;

  public BookSearchForm() {
    initComponents();
    updateList(bookList);
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    bookTitleTextField = new JTextField();
    findBookButton = new JButton("Find");
    findBookButton.addActionListener(findBookButtonClickListener);
    addBookButton = new JButton("Add");
    addBookButton.addActionListener(addBookButtonClickListener);

    JPanel bookSearchPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    bookSearchPanel.add(bookTitleTextField, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.weightx = 0.1;
    bookSearchPanel.add(findBookButton, gridBagConstraints);
    gridBagConstraints.gridx = 2;
    gridBagConstraints.weightx = 0.1;
    bookSearchPanel.add(addBookButton, gridBagConstraints);
    add(bookSearchPanel, BorderLayout.NORTH);

    bookTableModel = new BookTableModel();
    bookListTable = new JTable(bookTableModel);

    JPanel bookListPanel = new JPanel(new BorderLayout());
    bookListPanel.add(bookListTable.getTableHeader(), BorderLayout.NORTH);
    bookListPanel.add(bookListTable, BorderLayout.CENTER);
    add(bookListPanel, BorderLayout.CENTER);
  }

  @Override
  public BookModel find() {
    return BookModel;
  }

  private void updateList(List<BookModel> bookList) {
    bookTableModel.setBookList(bookList);
    bookTableModel.fireTableDataChanged();
  }

  private ActionListener addBookButtonClickListener = e -> {
    int selectedRow = bookListTable.getSelectedRow();

    if (selectedRow == -1) {
      return;
    }
    BookModel = bookTableModel.getBookAt(selectedRow);
    System.out.println(BookModel);
  };

  private ActionListener findBookButtonClickListener = e -> {
    String bookTitle = bookTitleTextField.getText();
    List<BookModel> bookList = BookBUS
      .getInstance()
      .searchModel(bookTitle, new String[] { "title" });
    updateList(bookList);
  };
}
