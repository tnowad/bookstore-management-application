package com.bookstore.gui.forms.shop;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.BooksCategoryBUS;
import com.bookstore.bus.CategoryBUS;
import com.bookstore.gui.forms.customer.Book;
import com.bookstore.models.BookModel;
import com.bookstore.models.BooksCategoryModel;
import com.bookstore.models.CategoryModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.*;

public class ShopCustomer extends JPanel {

  private JPanel bookListPanel;
  private JScrollPane bookListScrollPane;
  private JButton cartButtonTextField;
  private JLabel categoryLabel;
  private JComboBox<String> categoryListComboBox;
  private JPanel headerPanel;
  private JTextField searchTextField;
  private JComboBox<String> sortByConditionComboBox;
  private JLabel sortByLabel;

  private static ShopCustomer instance;
  private final List<BookModel> bookList = BookBUS.getInstance().getAllModels();
  private List<BookModel> modifiableBookList = new ArrayList<>(bookList);
  private final List<BooksCategoryModel> booksCategoryModels = BooksCategoryBUS
    .getInstance()
    .getAllModels();
  private List<BooksCategoryModel> modifiableBooksCategoryModelList = new ArrayList<>(
    booksCategoryModels
  );
  private final List<CategoryModel> categoryList = CategoryBUS
    .getInstance()
    .getAllModels();
  private List<CategoryModel> modifiableCategoryList = new ArrayList<>(
    categoryList
  );

  private ShopCustomer() {
    initComponents();
    renderListProduct(bookList);
    handleEvent();
  }

  private void handleEvent() {
    sortByConditionComboBox.addActionListener(e -> {
      bookListPanel.removeAll();
      String selectedValue = (String) sortByConditionComboBox.getSelectedItem();
      System.out.println("Selected value is " + selectedValue);
      if (selectedValue.equals("Price: low -> high")) {
        // for (int i = 0; i < books.size() - 1; i++) {
        // for (int j = i + 1; j < books.size(); j++) {
        // if (books.get(i).getPrice() > books.get(j).getPrice()) {
        // // Swap the positions of the two books in the list
        // BookModel temp = books.get(i);
        // books.set(i, books.get(j));
        // books.set(j, temp);
        // }
        // }
        // }

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
        System.out.println(selectedCategory);
        System.out.println(categoryModel.getId());
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

  private void renderListProduct(List<BookModel> bookListRender) {
    for (BookModel bookModel : bookListRender) {
      bookListPanel.add(new Book(bookModel));
    }
  }

  public static ShopCustomer getInstance() {
    if (instance == null) {
      instance = new ShopCustomer();
    }
    return instance;
  }

  private void initComponents() {
    headerPanel = new JPanel();
    sortByLabel = new JLabel();
    sortByConditionComboBox = new JComboBox<>();
    categoryLabel = new JLabel();
    categoryListComboBox = new JComboBox<>();
    searchTextField = new JTextField();
    cartButtonTextField = new JButton();
    bookListScrollPane = new JScrollPane();
    bookListPanel = new JPanel();

    setMaximumSize(new Dimension(800, 530));
    setMinimumSize(new Dimension(800, 530));
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

    searchTextField.setFont(new Font("Segoe UI", 0, 14));
    searchTextField.setText("Search anything here..");
    searchTextField.setMaximumSize(new Dimension(300, 30));
    searchTextField.setMinimumSize(new Dimension(300, 30));
    searchTextField.setPreferredSize(new Dimension(300, 30));

    headerPanel.add(searchTextField);

    cartButtonTextField.setText("Cart");
    cartButtonTextField.setPreferredSize(new Dimension(80, 30));

    headerPanel.add(cartButtonTextField);

    add(headerPanel, BorderLayout.PAGE_START);

    bookListPanel.setLayout(new GridLayout(0, 3));
    bookListScrollPane.setViewportView(bookListPanel);
    bookListScrollPane.setHorizontalScrollBarPolicy(
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    bookListScrollPane.getVerticalScrollBar().setUnitIncrement(50);

    add(bookListScrollPane, BorderLayout.CENTER);
  }
}
