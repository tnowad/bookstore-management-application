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

  private double width;

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
    title = new JLabel();
    contendPanel = new JPanel();
    panel = new JPanel();
    panelButton = new JPanel();
    buttonExport = new JButton();
    buttonImport = new JButton();
    buttonCreate = new JButton();
    buttonDelete = new JButton();
    scrollPane = new JScrollPane();
    table = new JPanel();

    setPreferredSize(new Dimension(702, 444));
    setLayout(new BorderLayout());

    addComponentListener(reSize);

    title.setFont(new Font("Segoe UI", 3, 18));
    title.setForeground(new Color(255, 0, 51));
    title.setText("List Book");
    add(title, java.awt.BorderLayout.NORTH);

    contendPanel.setLayout(new BorderLayout());

    panel.setLayout(new BorderLayout(5, 5));

    panelButton.setLayout(new GridLayout(1, 0, 10, 10));

    buttonExport.setText("Export");
    buttonExport.addActionListener(actionExport);
    panelButton.add(buttonExport);

    buttonImport.setText("Import");
    buttonImport.addActionListener(actionImport);
    panelButton.add(buttonImport);

    buttonCreate.setText("Add");
    buttonCreate.addActionListener(actionAdd);
    panelButton.add(buttonCreate);

    buttonDelete.setText("Delete");
    buttonDelete.addActionListener(actionBanned);
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
        BookProductPanel bookProductPanel = new BookProductPanel(book);
        table.add(bookProductPanel);
      }
    }
  }

  public void receiveValue(String value) {
    String[] columns = new String[] { "title" };
    List<BookModel> list = BookBUS.getInstance().searchModel(value, columns);
    table.removeAll();
    table.setLayout(new GridLayout(0, 3, 10, 10));
    for (BookModel book : list) {
      if (!book.getStatus().toString().equals("DELETED")) {
        BookProductPanel bookProductPanel = new BookProductPanel(book);
        table.add(bookProductPanel);
      }
    }
    table.revalidate();
    table.repaint();
  }

  private JButton buttonCreate;
  private JButton buttonDelete;
  private JButton buttonExport;
  private JButton buttonImport;
  private JPanel contendPanel;
  private JPanel panel;
  private JPanel panelButton;
  private JScrollPane scrollPane;
  private JPanel table;
  private JLabel title;

  public ActionListener actionAdd = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      AddProductFrame addProductFrame = new AddProductFrame();
      addProductFrame.setVisible(true);
    }
  };
  public ActionListener actionBanned = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
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
                  }
                }
              }
            }
          }
        }
      }
    }

  };

  public ActionListener actionImport = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
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
  };

  public ActionListener actionExport = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
      List<BookModel> listBooks = BookBUS.getInstance().getAllModels();
      try {
        BookExcelUtil.writeBooksToExcel(listBooks);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  };
  public ComponentListener reSize = new ComponentListener() {
    @Override
    public void componentResized(ComponentEvent e) {
      double width = BrowseProductPanel.getInstance().getWidth();
      int cols = (int) width / 199;
      table.setLayout(new GridLayout(0, cols, 10, 10));
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
  };
}
