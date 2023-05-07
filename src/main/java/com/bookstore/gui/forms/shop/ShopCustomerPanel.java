package com.bookstore.gui.forms.shop;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.BooksCategoryBUS;
import com.bookstore.bus.CategoryBUS;
import com.bookstore.gui.components.books.BookItemPanel;
import com.bookstore.gui.components.dialogs.Dialog;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.forms.carts.CartCustomerPanel;
import com.bookstore.gui.forms.general.NoDataPanel;
import com.bookstore.interfaces.IFilterAble;
import com.bookstore.interfaces.ISearchable;
import com.bookstore.models.BookModel;
import com.bookstore.models.BooksCategoryModel;
import com.bookstore.models.CategoryModel;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;

public class ShopCustomerPanel
  extends JPanel
  implements ISearchable, IFilterAble {

  private JPanel bookListPanel;
  private JScrollPane bookListScrollPane;
  private JButton cartButtonTextField;
  private JLabel categoryLabel;
  private JComboBox<String> categoryListComboBox;
  private JPanel headerPanel;
  private JComboBox<String> sortByConditionComboBox;
  private JLabel sortByLabel;

  private final List<BookModel> bookList = BookBUS.getInstance().getAllModels();
  private List<BookModel> modifiableBookList = new ArrayList<>(bookList);
  private final List<CategoryModel> categoryList = CategoryBUS
    .getInstance()
    .getAllModels();

  public ShopCustomerPanel() {
    initComponents();
    renderListProduct(bookList);
    handleEvent();
  }

  private void handleEvent() {
    cartButtonTextField.addActionListener(arg0 -> {
      MainPanel.getInstance().showForm(new CartCustomerPanel());
    });
    sortByConditionComboBox.addActionListener(e -> {
      bookListPanel.removeAll();
      String selectedValue = (String) sortByConditionComboBox.getSelectedItem();
      if (selectedValue.equals("Price: low -> high")) {
        Collections.sort(
          modifiableBookList,
          Comparator.comparingInt(BookModel::getPrice)
        );
      } else if (selectedValue.equals("Price: high -> low")) {
        Collections.sort(
          modifiableBookList,
          Comparator.comparingInt(BookModel::getPrice).reversed()
        );
      }
      renderListProduct(modifiableBookList);
      this.revalidate();
      this.repaint();
    });

    categoryListComboBox.addActionListener(e -> {
      bookListPanel.removeAll();
      String selectedCategory = (String) categoryListComboBox.getSelectedItem();
      List<BookModel> books = new ArrayList<BookModel>();
      if (selectedCategory.equals("All")) {
        renderListProduct(bookList);
        this.revalidate();
        this.repaint();
      } else {
        BooksCategoryBUS booksCategoryBUS = BooksCategoryBUS.getInstance();
        CategoryBUS categoryBUS = CategoryBUS.getInstance();
        CategoryModel categoryModel = categoryBUS.getModelByName(
          selectedCategory
        );
        if (categoryModel != null) {
          List<BooksCategoryModel> booksCategoryModels = booksCategoryBUS.getAllModels();
          for (BooksCategoryModel booksCategoryModel : booksCategoryModels) {
            if (booksCategoryModel.getCategoryId() == categoryModel.getId()) {
              BookModel bookModel = BookBUS
                .getInstance()
                .getBookByIsbn(booksCategoryModel.getBookIsbn());
              if (bookModel != null && !books.contains(bookModel)) {
                books.add(bookModel);
              }
            }
          }
        }

        renderListProduct(books);
        this.revalidate();
        this.repaint();
      }
    });
  }

  void renderListProduct(List<BookModel> bookListRender) {
    bookListPanel.removeAll();
    if (bookListRender.size() <= 0) {
      bookListPanel.setLayout(new GridLayout(0, 1));
      bookListPanel.add(new NoDataPanel("Don't have data for product"));
    } else {
      int width = bookListPanel.getParent().getParent().getWidth();
      int column = width / 250 > 0 ? width / 250 : 1;
      bookListPanel.setLayout(new GridLayout(0, column, 10, 10));
      for (BookModel bookModel : bookListRender) {
        BookItemPanel bookItemPanel = new BookItemPanel(bookModel);
        bookItemPanel.setPreferredSize(new Dimension(250, 400));
        bookListPanel.add(bookItemPanel);
      }
    }
    this.revalidate();
    this.repaint();
  }

  private void initComponents() {
    headerPanel = new JPanel();
    sortByLabel = new JLabel();
    sortByConditionComboBox = new JComboBox<>();
    categoryLabel = new JLabel();
    categoryListComboBox = new JComboBox<>();
    cartButtonTextField = new JButton();
    bookListScrollPane = new JScrollPane();
    bookListPanel = new JPanel();

    setLayout(new BorderLayout());

    sortByLabel.setFont(new Font("Arial", 0, 14));
    sortByLabel.setText("Sort By:");
    sortByLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    sortByLabel.setMaximumSize(new Dimension(50, 30));
    sortByLabel.setMinimumSize(new Dimension(50, 30));
    sortByLabel.setPreferredSize(new Dimension(50, 30));
    headerPanel.add(sortByLabel);

    sortByConditionComboBox.setFont(new Font("Arial", 0, 14));
    sortByConditionComboBox.setModel(
      new DefaultComboBoxModel<>(
        new String[] { "Price: low -> high", "Price: high -> low" }
      )
    );
    sortByConditionComboBox.setMaximumSize(new Dimension(150, 30));
    sortByConditionComboBox.setMinimumSize(new Dimension(150, 30));
    sortByConditionComboBox.setPreferredSize(new Dimension(150, 30));

    headerPanel.add(sortByConditionComboBox);

    categoryLabel.setFont(new Font("Arial", 0, 14));
    categoryLabel.setText("Category:");
    categoryLabel.setPreferredSize(new Dimension(62, 30));

    categoryListComboBox.setMaximumSize(new Dimension(100, 30));
    categoryListComboBox.setMinimumSize(new Dimension(100, 30));
    categoryListComboBox.setPreferredSize(new Dimension(120, 30));
    categoryListComboBox.addItem("All");
    for (int i = 0; i < categoryList.size(); i++) {
      categoryListComboBox.addItem(categoryList.get(i).getName());
    }

    headerPanel.add(categoryLabel);
    headerPanel.add(categoryListComboBox);

    cartButtonTextField.setText("Cart");
    cartButtonTextField.setPreferredSize(new Dimension(80, 30));

    headerPanel.add(cartButtonTextField);

    add(headerPanel, BorderLayout.PAGE_START);

    bookListPanel.setLayout(new GridLayout(0, 2));

    bookListScrollPane.addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          int width = bookListScrollPane.getWidth();
          int column = width / 250 > 0 ? width / 250 : 1;
          bookListPanel.setLayout(new GridLayout(0, column, 10, 10));
        }
      }
    );

    bookListScrollPane.setHorizontalScrollBarPolicy(
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    JPanel bookListWrapper = new JPanel();
    bookListWrapper.add(bookListPanel);
    bookListScrollPane.setViewportView(bookListWrapper);
    bookListScrollPane.getVerticalScrollBar().setUnitIncrement(50);

    add(bookListScrollPane, BorderLayout.CENTER);
  }

  @Override
  public void search(String keyword) {
    bookListPanel.removeAll();
    if (keyword == null || keyword.isBlank()) {
      renderListProduct(bookList);
      this.revalidate();
      this.repaint();
    } else {
      List<BookModel> books = new ArrayList<BookModel>();
      for (BookModel bookModel : bookList) {
        if (
          bookModel.getTitle().toLowerCase().contains(keyword.toLowerCase())
        ) {
          books.add(bookModel);
        }
      }

      renderListProduct(books);
      this.revalidate();
      this.repaint();
    }
  }

  @Override
  public void filter() {
    new Dialog(new BookFilterPanel(this));
  }
}