package com.bookstore.gui.components.books;

import com.bookstore.bus.BookBUS;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.forms.filters.BookFilterForm;
import com.bookstore.interfaces.IListPanel;
import com.bookstore.interfaces.ISearchable;
import com.bookstore.models.BookModel;
import com.bookstore.util.Excel.BookExcelUtil;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;

public class BrowseProductPanel
  extends JPanel
  implements ISearchable, IListPanel<BookModel> {

  private JButton buttonSortAz;
  private JButton buttonSortZa;
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
  private int cols = 3;
  private static BrowseProductPanel instance;

  private BookBUS bookBUS = BookBUS.getInstance();
  private List<BookModel> listBook = bookBUS.getAllModels();
  private List<BookModel> modifiableBookList = new ArrayList<>(listBook);

  public BrowseProductPanel() {
    initComponents();
    updateList(modifiableBookList);
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
    buttonSortAz = new JButton();
    buttonSortZa = new JButton();

    setPreferredSize(new Dimension(702, 444));
    setLayout(new BorderLayout());

    addComponentListener(reSize);

    title.setFont(new Font("Segoe UI", 3, 18));
    title.setForeground(new Color(255, 0, 51));
    title.setText("List Book");
    add(title, BorderLayout.NORTH);

    contendPanel.setLayout(new BorderLayout());

    panel.setLayout(new BorderLayout(5, 5));

    panelButton.setLayout(new GridLayout(1, 0, 10, 10));

    buttonSortAz.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/sortAz.png"))
    );
    buttonSortAz.addActionListener(actionSortAz);
    panelButton.add(buttonSortAz);

    buttonSortZa.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/sortZa.png"))
    );
    buttonSortZa.addActionListener(actionSortZa);
    panelButton.add(buttonSortZa);

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

    panel.add(panelButton, BorderLayout.LINE_END);

    contendPanel.add(panel, BorderLayout.PAGE_START);

    table = new JPanel();
    JPanel tableWrapper = new JPanel();
    tableWrapper.setLayout(new BorderLayout());
    tableWrapper.add(table, BorderLayout.PAGE_START);
    scrollPane =
      new JScrollPane(
        tableWrapper,
        JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
        JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
      );

    scrollPane.getVerticalScrollBar().setUnitIncrement(16);

    contendPanel.add(scrollPane, BorderLayout.CENTER);

    add(contendPanel, BorderLayout.CENTER);
  }

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
      int updateStatusRows = 0;
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
                  String status = "DELETED";
                  updateStatusRows =
                    BookBUS.getInstance().updateStatus(id, status);
                  if (updateStatusRows == 1) {
                    BookBUS.getInstance().refreshData();
                    JOptionPane.showMessageDialog(
                      null,
                      "You've successfully locked an products!"
                    );
                    MainPanel.getInstance().showForm(BrowseProductPanel.getInstance());
                  }
                }
              }
            }
          }
        }
      }
      if (updateStatusRows == 0) {
        JOptionPane.showMessageDialog(
          null,
          "No products have been selected yet!"
        );
      }
    }
  };

  public ActionListener actionImport = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
      try {
        List<BookModel> listBooks = BookExcelUtil.readBooksFromExcel();
        if (listBooks == null) return;
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

  public ActionListener actionSortAz = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      table.removeAll();
      modifiableBookList.sort(Comparator.comparing(BookModel::getTitle));
      updateList(modifiableBookList);
      table.revalidate();
      table.repaint();
    }
  };
  public ActionListener actionSortZa = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      table.removeAll();
      modifiableBookList.sort(
        Comparator.comparing(BookModel::getTitle).reversed()
      );
      updateList(modifiableBookList);
      table.revalidate();
      table.repaint();
    }
  };

  public ComponentListener reSize = new ComponentListener() {
    @Override
    public void componentResized(ComponentEvent e) {
      double width = getWidth();
      cols = (int) width / 199;
      table.setLayout(new GridLayout(0, cols, 10, 10));
    }

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentShown(ComponentEvent e) {}

    @Override
    public void componentHidden(ComponentEvent e) {}
  };

  @Override
  public void search(String keyword) {
    table.removeAll();
    if (keyword == null || keyword.isBlank()) {
      updateList(listBook);
    } else {
      List<BookModel> books = new ArrayList<BookModel>();
      for (BookModel bookModel : listBook) {
        if (
          bookModel.getTitle().toLowerCase().contains(keyword.toLowerCase())
        ) {
          books.add(bookModel);
        }
      }
      updateList(books);
    }
  }

  @Override
  public void activateFilterPanel() {
    BookFilterForm bookFilterForm = new BookFilterForm(this);
    new com.bookstore.gui.components.dialogs.Dialog(bookFilterForm);
  }

  @Override
  public void updateList(List<BookModel> filteredList) {
    table.removeAll();
    table.setLayout(new GridLayout(0, cols, 10, 10));
    for (BookModel book : filteredList) {
      if (!book.getStatus().toString().equals("DELETED")) {
        BookProductPanel bookProductPanel = new BookProductPanel(book);
        table.add(bookProductPanel);
      }
    }
    this.revalidate();
    this.repaint();
  }
}
