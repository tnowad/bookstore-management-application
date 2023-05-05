package com.bookstore.gui.forms.books;

import com.bookstore.bus.BookBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.theme.ThemeFont;
import com.bookstore.models.BookModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class BookList extends JPanel {

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
  private JScrollPane jScrollPane;
  private Button searchButton;
  private JTextField searchTextField;
  private static BookList instance;

  BookBUS bookBus = BookBUS.getInstance();
  List<BookModel> bookList = bookBus.getAllModels();

  private BookList() {
    initComponents();
    listBooks();
    search();
  }

  public static BookList getInstance() {
    if (instance == null) {
      instance = new BookList();
    }
    return instance;
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
    jScrollPane = new JScrollPane();
    bookListTable = new JTable();

    setPreferredSize(new Dimension(1180, 620));
    setLayout(new BorderLayout());

    jPanel1.setLayout(new GridLayout(2, 1));

    jPanel3.setLayout(new GridLayout(1, 2, 0, 10));

    bookListLabel.setHorizontalAlignment(SwingConstants.LEFT);
    jPanel3.add(bookListLabel);

    jPanel5.setLayout(new FlowLayout(FlowLayout.RIGHT));

    addBookButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          addBookButtonActionPerformed(evt);
        }
      }
    );
    jPanel5.add(addBookButton);

    jPanel3.add(jPanel5);

    jPanel1.add(jPanel3);

    jPanel4.setLayout(new FlowLayout(FlowLayout.LEFT));

    searchTextField.setFont(new Font("Arial", 0, 14));
    searchTextField.setPreferredSize(new Dimension(450, 30));
    jPanel4.add(searchTextField);

    searchButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          searchButtonActionPerformed(evt);
        }
      }
    );
    jPanel4.add(searchButton);

    excelButtonPanel.setPreferredSize(new Dimension(640, 30));
    excelButtonPanel.setLayout(new FlowLayout(FlowLayout.TRAILING, 5, 0));

    importFromExcelButton.setPreferredSize(new Dimension(160, 30));
    importFromExcelButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          importFromExcelButtonActionPerformed(evt);
        }
      }
    );
    excelButtonPanel.add(importFromExcelButton);

    exportToExcelButton.setPreferredSize(new Dimension(150, 30));
    exportToExcelButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          exportToExcelButtonActionPerformed(evt);
        }
      }
    );
    excelButtonPanel.add(exportToExcelButton);

    jPanel4.add(excelButtonPanel);

    jPanel1.add(jPanel4);

    add(jPanel1, BorderLayout.PAGE_START);

    jPanel6.setLayout(new BorderLayout());

    bookListTable.setFont(new ThemeFont().getSmallFont());

    bookListTable.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
          bookListTableMouseClicked(evt);
        }
      }
    );
    jScrollPane.setViewportView(bookListTable);
    if (bookListTable.getColumnModel().getColumnCount() > 0) {
      bookListTable.getColumnModel().getColumn(0).setResizable(false);
      bookListTable.getColumnModel().getColumn(1).setResizable(false);
      bookListTable.getColumnModel().getColumn(2).setResizable(false);
      bookListTable.getColumnModel().getColumn(3).setResizable(false);
      bookListTable.getColumnModel().getColumn(4).setResizable(false);
    }

    jPanel6.add(jScrollPane, BorderLayout.CENTER);

    jScrollPane1.setViewportView(jPanel6);

    add(jScrollPane1, BorderLayout.CENTER);
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

  private void searchButtonActionPerformed(ActionEvent evt) {}

  private void importFromExcelButtonActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private void exportToExcelButtonActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private void addBookButtonActionPerformed(ActionEvent evt) {}

  private void bookListTableMouseClicked(MouseEvent evt) {}
}
