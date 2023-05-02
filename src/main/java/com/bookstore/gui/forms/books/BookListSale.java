package com.bookstore.gui.forms.books;

import com.bookstore.bus.BookBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.theme.ThemeFont;
import com.bookstore.models.BookModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookListSale extends JPanel {

  private Button addBookButton;
  private Label bookListLabel;
  private JTable bookListTable;
  private JPanel excelButtonPanel;
  private Button exportToExcelButton;
  private Button importFromExcelButton;
  private JPanel jPanel1;
  private JPanel jPanel3;
  private JPanel jPanel4;
  private JPanel jPanel5;
  private JPanel jPanel6;
  private JScrollPane jScrollPane1;
  private JScrollPane jscrollPane;
  private Button searchButton;
  private JTextField searchTextField;

  BookBUS bookBus = BookBUS.getInstance();
  List<BookModel> bookList = bookBus.getAllModels();

  public BookListSale() {
    initComponents();
    listBooks();
    search();
  }

  private void initComponents() {
    jPanel1 = new JPanel();
    jPanel3 = new JPanel();
    bookListLabel = new Label("Book List");
    jPanel5 = new JPanel();
    addBookButton = new Button("Add Book");
    jPanel4 = new JPanel();
    searchTextField = new JTextField();
    searchButton = new Button("Search");
    excelButtonPanel = new JPanel();
    importFromExcelButton = new Button("Import from Excel file");
    exportToExcelButton = new Button("Export to Excel file");
    jScrollPane1 = new JScrollPane();
    jPanel6 = new JPanel();
    jscrollPane = new JScrollPane();
    bookListTable = new JTable();

    setPreferredSize(new java.awt.Dimension(1180, 620));
    setLayout(new java.awt.BorderLayout());

    jPanel1.setLayout(new java.awt.GridLayout(2, 1));

    jPanel3.setLayout(new java.awt.GridLayout(1, 2, 0, 10));

    bookListLabel.setHorizontalAlignment(SwingConstants.LEFT);
    jPanel3.add(bookListLabel);

    jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

    jPanel5.add(addBookButton);

    jPanel3.add(jPanel5);

    jPanel1.add(jPanel3);

    jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

    searchTextField.setFont(new java.awt.Font("Arial", 0, 14));
    searchTextField.setPreferredSize(new java.awt.Dimension(450, 30));
    jPanel4.add(searchTextField);

    jPanel4.add(searchButton);

    excelButtonPanel.setPreferredSize(new java.awt.Dimension(340, 30));
    excelButtonPanel.setLayout(
      new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING, 5, 0)
    );

    importFromExcelButton.setPreferredSize(new java.awt.Dimension(160, 30));

    excelButtonPanel.add(importFromExcelButton);

    exportToExcelButton.setPreferredSize(new java.awt.Dimension(150, 30));

    excelButtonPanel.add(exportToExcelButton);

    jPanel4.add(excelButtonPanel);

    jPanel1.add(jPanel4);

    add(jPanel1, java.awt.BorderLayout.PAGE_START);

    jPanel6.setLayout(new java.awt.BorderLayout());

    bookListTable.setFont(new ThemeFont().getSmallFont());

    jscrollPane.setViewportView(bookListTable);
    if (bookListTable.getColumnModel().getColumnCount() > 0) {
      bookListTable.getColumnModel().getColumn(0).setResizable(false);
      bookListTable.getColumnModel().getColumn(1).setResizable(false);
      bookListTable.getColumnModel().getColumn(2).setResizable(false);
      bookListTable.getColumnModel().getColumn(3).setResizable(false);
      bookListTable.getColumnModel().getColumn(4).setResizable(false);
    }

    jPanel6.add(jscrollPane, java.awt.BorderLayout.CENTER);

    jScrollPane1.setViewportView(jPanel6);

    add(jScrollPane1, java.awt.BorderLayout.CENTER);
  }

  private void search() {
    searchButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String text = searchTextField.getText();
          if (text == null || text.isBlank()) {
            JOptionPane.showMessageDialog(
              null,
              "Vui lòng nhập thông tin tìm kiếm !"
            );
            showTable();
          } else {
            DefaultTableModel model = new DefaultTableModel();
            // "ISBN", "Title", "Quantity", "Price", "Status"
            model.addColumn("ISBN");
            model.addColumn("Title");
            model.addColumn("Description");
            model.addColumn("Price");
            model.addColumn("Quantity");
            model.addColumn("Status");
            model.addColumn("Publisher");
            model.addColumn("Author");
            for (BookModel bookModel : bookList) {
              if (
                bookModel.getTitle().toLowerCase().contains(text.toLowerCase())
              ) {
                model.addRow(
                  new Object[] {
                    bookModel.getIsbn(),
                    bookModel.getTitle(),
                    bookModel.getDescription(),
                    bookModel.getPrice(),
                    bookModel.getQuantity(),
                    bookModel.getStatus(),
                    bookModel.getPublisherId(),
                    bookModel.getAuthorId(),
                  }
                );
                bookListTable.setModel(model);
              }
            }
          }
        }
      }
    );
  }

  private void listBooks() {
    showTable();
  }

  private void showTable() {
    DefaultTableModel model = new DefaultTableModel();
    // "ISBN", "Title", "Quantity", "Price", "Status"
    model.addColumn("ISBN");
    model.addColumn("Title");
    model.addColumn("Description");
    model.addColumn("Price");
    model.addColumn("Quantity");
    model.addColumn("Status");
    model.addColumn("Publisher");
    model.addColumn("Author");
    for (BookModel book : bookList) {
      model.addRow(
        new Object[] {
          book.getIsbn(),
          book.getTitle(),
          book.getDescription(),
          book.getPrice(),
          book.getQuantity(),
          book.getStatus(),
          book.getPublisherId(),
          book.getAuthorId(),
        }
      );
      bookListTable.setModel(model);
    }
  }
}