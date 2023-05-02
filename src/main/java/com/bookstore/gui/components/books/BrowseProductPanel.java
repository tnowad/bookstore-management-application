package com.bookstore.gui.components.books;

import com.bookstore.bus.BookBUS;
import com.bookstore.models.BookModel;
import com.bookstore.util.Excel.BookExcelUtil;
/**
 *
 * @author yanti
 */
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.List;
import javax.swing.*;

public class BrowseProductPanel extends JPanel {

  private static BrowseProductPanel instance;
  private BookBUS bookBUS;
  private List<BookModel> listBook;

  public BrowseProductPanel() {
    bookBUS = BookBUS.getInstance();
    listBook = bookBUS.getAllModels();
    initComponents();
    addTable();
  }

  public static BrowseProductPanel getInstance() {
    if (instance == null) {
      instance = new BrowseProductPanel();
    }
    return instance;
  }

  private void initComponents() {
    title = new javax.swing.JLabel();
    contendPanel = new javax.swing.JPanel();
    panel = new javax.swing.JPanel();
    panelButton = new javax.swing.JPanel();
    buttonExport = new javax.swing.JButton();
    buttonImport = new javax.swing.JButton();
    buttonCreate = new javax.swing.JButton();
    buttonDelete = new javax.swing.JButton();
    scrollPane = new javax.swing.JScrollPane();
    table = new javax.swing.JPanel();

    setPreferredSize(new java.awt.Dimension(702, 444));
    setLayout(new java.awt.BorderLayout());

    title.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    title.setForeground(new java.awt.Color(255, 0, 51));
    title.setText("List Book");
    add(title, java.awt.BorderLayout.NORTH);

    contendPanel.setLayout(new java.awt.BorderLayout());

    panel.setLayout(new java.awt.BorderLayout(5, 5));

    panelButton.setLayout(new java.awt.GridLayout(1, 0, 10, 10));

    buttonExport.setText("Export");
    buttonExport.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          actionExport();
        }
      }
    );
    panelButton.add(buttonExport);

    buttonImport.setText("Import");
    buttonImport.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          actionImport();
        }
      }
    );
    panelButton.add(buttonImport);

    buttonCreate.setText("Add");
    buttonCreate.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          actionAdd();
        }
      }
    );
    panelButton.add(buttonCreate);

    buttonDelete.setText("Delete");
    buttonDelete.addActionListener(
      new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
          actionDelete();
        }
      }
    );
    panelButton.add(buttonDelete);

    panel.add(panelButton, java.awt.BorderLayout.LINE_END);

    contendPanel.add(panel, java.awt.BorderLayout.PAGE_START);

    scrollPane.setViewportView(table);

    contendPanel.add(scrollPane, java.awt.BorderLayout.CENTER);

    add(contendPanel, java.awt.BorderLayout.CENTER);
  }

  public void addTable() {
    table.setLayout(new GridLayout(0, 3, 10, 10));
    for (BookModel book : listBook) {
      if (!book.getStatus().toString().equals("DELETED")) {
        BookProductPanel bookProductPanel = new BookProductPanel(
          book.getIsbn(),
          book.getTitle(),
          book.getDescription(),
          book.getImage(),
          book.getPrice(),
          book.getQuantity(),
          book.getStatus(),
          book.getPublisherId(),
          book.getAuthorId()
        );
        table.add(bookProductPanel);
      }
    }
  }

  public void actionDelete() {
    int choice = JOptionPane.showConfirmDialog(
      null,
      "Do you want to banned products?",
      "Confirmation",
      JOptionPane.YES_NO_OPTION
    );
    if (choice == JOptionPane.YES_OPTION) {
      for (Component component : table.getComponents()) {
        JPanel subPanel = (JPanel) component;
        for (Component subComponent : subPanel.getComponents()) {
          if (
            subComponent instanceof JCheckBox &&
            ((JCheckBox) subComponent).isSelected()
          ) {
            Component[] components = subPanel.getComponents();
            for (Component c : components) {
              if (c instanceof JTextField) {
                String id = ((JTextField) c).getText();
                System.out.println(id);
                String status = "DELETED";
                int updateStatusRows = BookBUS
                  .getInstance()
                  .updateStatus(id, status);
                if (updateStatusRows == 1) {
                  JOptionPane.showMessageDialog(
                    null,
                    "You've successfully locked an products!"
                  );
                  table.revalidate();
                  table.repaint();
                }
              }
            }
          }
        }
      }
    }
  }

  public void actionAdd() {
    AddProductFrame addProductFrame = new AddProductFrame();
    addProductFrame.setVisible(true);
    
  }

  public void actionExport() {
    List<BookModel> listBooks = BookBUS.getInstance().getAllModels();
    try {
      BookExcelUtil.writeBooksToExcel(listBooks);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void actionImport() {
    try {
      List<BookModel> listBooks = BookExcelUtil.readBooksFromExcel();
      for (BookModel book : listBooks) {
        if (book.getStatus().toString().equals("deleted")) {
          listBooks.remove(book);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void receiveValue(String value) {
    String[] columns = new String[] { "title" };
    List<BookModel> list = BookBUS.getInstance().searchModel(value, columns);
    table.removeAll();
    table.setLayout(new GridLayout(0, 3, 10, 10));
    for (BookModel book : list) {
      if (!book.getStatus().toString().equals("DELETED")) {
        BookProductPanel bookProductPanel = new BookProductPanel(
          book.getIsbn(),
          book.getTitle(),
          book.getDescription(),
          book.getImage(),
          book.getPrice(),
          book.getQuantity(),
          book.getStatus(),
          book.getPublisherId(),
          book.getAuthorId()
        );
        table.add(bookProductPanel);
      }
    }
    table.revalidate();
    table.repaint();
  }

  public void actionResize() {
    double width = contendPanel.getPreferredSize().getWidth();
    System.out.println(width);
  }

  private javax.swing.JButton buttonCreate;
  private javax.swing.JButton buttonDelete;
  private javax.swing.JButton buttonExport;
  private javax.swing.JButton buttonImport;
  private javax.swing.JPanel contendPanel;
  private javax.swing.JPanel panel;
  private javax.swing.JPanel panelButton;
  private javax.swing.JScrollPane scrollPane;
  private javax.swing.JPanel table;
  private javax.swing.JLabel title;
}
