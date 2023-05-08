package com.bookstore.gui.forms.filters;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.bus.BooksCategoryBUS;
import com.bookstore.bus.CategoryBUS;
import com.bookstore.gui.forms.shop.ShopCustomerPanel;
import com.bookstore.interfaces.IFilterForm;
import com.bookstore.interfaces.IListPanel;
import com.bookstore.models.AuthorModel;
import com.bookstore.models.BookModel;
import com.bookstore.models.BooksCategoryModel;
import com.bookstore.models.CategoryModel;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class BookFilterForm extends JPanel implements IFilterForm {

  private IListPanel<BookModel> shopCustomerPanel;

  private JPanel categoryPanel;
  private JTextField keywordField;
  private JSpinner minPriceSpinner;
  private JSpinner maxPriceSpinner;

  private ButtonGroup sortByGroup;

  private List<CategoryModel> categoryList;

  public BookFilterForm(IListPanel<BookModel> shopCustomerPanel) {
    this.shopCustomerPanel = shopCustomerPanel;
    this.categoryList = CategoryBUS.getInstance().getAllModels();

    initComponents();
  }

  private void initComponents() {
    setLayout(new GridBagLayout());

    // Category panel
    categoryPanel = new JPanel(new GridLayout(0, 4, 5, 5));
    categoryPanel.setBorder(BorderFactory.createTitledBorder("Category"));

    // Category checkboxes
    for (CategoryModel category : categoryList) {
      JCheckBox checkBox = new JCheckBox(category.getName());
      checkBox.setActionCommand(String.valueOf(category.getId()));
      checkBox.addActionListener(applyFilter);
      categoryPanel.add(checkBox);
    }

    // Keyword panel
    JPanel keywordPanel = new JPanel(new BorderLayout(5, 5));
    keywordPanel.setBorder(BorderFactory.createTitledBorder("Keyword"));

    // Keyword text field and button
    keywordField = new JTextField();
    keywordField.addActionListener(applyFilter);
    JButton searchButton = new JButton("Search");
    searchButton.addActionListener(applyFilter);

    keywordPanel.add(keywordField, BorderLayout.CENTER);
    keywordPanel.add(searchButton, BorderLayout.EAST);

    // Price range panel
    JPanel priceRangePanel = new JPanel(new BorderLayout(5, 5));
    priceRangePanel.setBorder(BorderFactory.createTitledBorder("Price Range"));

    // Price range sliders
    minPriceSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000000, 1));
    maxPriceSpinner =
      new JSpinner(new SpinnerNumberModel(1000000, 0, 1000000, 1));
    minPriceSpinner.addChangeListener(e -> {
      applyFilter.actionPerformed(null);
    });

    maxPriceSpinner.addChangeListener(e -> {
      applyFilter.actionPerformed(null);
    });

    JPanel sliderPanel = new JPanel(new GridLayout(1, 2, 5, 5));
    sliderPanel.add(minPriceSpinner);
    sliderPanel.add(maxPriceSpinner);

    JPanel labelPanel = new JPanel(new GridLayout(1, 2, 5, 5));
    labelPanel.add(new JLabel("Min"));
    labelPanel.add(new JLabel("Max"));

    priceRangePanel.add(sliderPanel, BorderLayout.CENTER);
    priceRangePanel.add(labelPanel, BorderLayout.SOUTH);

    // Sort by panel
    JPanel sortByPanel = new JPanel(new GridLayout(0, 1, 5, 5));
    sortByPanel.setBorder(BorderFactory.createTitledBorder("Sort By"));

    // Sort by radio buttons
    sortByGroup = new ButtonGroup();
    JRadioButton titleRadioButton = new JRadioButton("Title");
    JRadioButton priceRadioButton = new JRadioButton("Price");
    JRadioButton authorRadioButton = new JRadioButton("Author");

    titleRadioButton.setActionCommand("title");
    priceRadioButton.setActionCommand("price");
    authorRadioButton.setActionCommand("author");

    sortByGroup.add(titleRadioButton);
    sortByGroup.add(priceRadioButton);
    sortByGroup.add(authorRadioButton);

    titleRadioButton.addActionListener(applyFilter);
    priceRadioButton.addActionListener(applyFilter);
    authorRadioButton.addActionListener(applyFilter);

    sortByPanel.add(titleRadioButton);
    sortByPanel.add(priceRadioButton);
    sortByPanel.add(authorRadioButton);

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1;
    gridBagConstraints.weighty = 1;
    add(keywordPanel, gridBagConstraints);

    gridBagConstraints.gridy = 1;
    add(categoryPanel, gridBagConstraints);

    gridBagConstraints.gridy = 2;
    add(priceRangePanel, gridBagConstraints);

    gridBagConstraints.gridy = 3;
    add(sortByPanel, gridBagConstraints);
  }

  private ActionListener applyFilter = e -> {
    List<BookModel> filteredList = new ArrayList<>(
      BookBUS.getInstance().getAllModels()
    );

    List<CategoryModel> selectedCategories = new ArrayList<>();

    for (Component component : categoryPanel.getComponents()) {
      if (component instanceof JCheckBox) {
        JCheckBox checkBox = (JCheckBox) component;
        if (checkBox.isSelected()) {
          int categoryId = Integer.parseInt(checkBox.getActionCommand());
          selectedCategories.add(
            CategoryBUS.getInstance().getModelById(categoryId)
          );
        }
      }
    }

    String keyword = keywordField.getText().trim().toLowerCase();

    int minPrice = (int) minPriceSpinner.getValue();
    int maxPrice = (int) maxPriceSpinner.getValue();

    String sortBy = sortByGroup.getSelection() == null
      ? "title"
      : sortByGroup.getSelection().getActionCommand();
    List<BookModel> temp = new ArrayList<>();
    for (BookModel book : filteredList) {
      if (
        keyword.length() == 0 ||
        book.getTitle().toLowerCase().contains(keyword) ||
        AuthorBUS
          .getInstance()
          .getModelById(book.getAuthorId())
          .getName()
          .toLowerCase()
          .contains(keyword)
      ) {
        temp.add(book);
      }
    }
    filteredList = temp;
    if (selectedCategories.size() > 0) {
      temp = new ArrayList<>();
      for (BookModel book : filteredList) {
        boolean isMatch = true;
        List<BooksCategoryModel> booksCategories = BooksCategoryBUS
          .getInstance()
          .getModelsByBookId(book.getIsbn());
        for (BooksCategoryModel booksCategory : booksCategories) {
          boolean isMatched = false;
          for (CategoryModel category : selectedCategories) {
            if (booksCategory.getCategoryId() == category.getId()) {
              isMatched = true;
              break;
            }
          }
          if (!isMatched) {
            isMatch = false;
            break;
          }
        }
        if (isMatch) {
          temp.add(book);
        }
      }
      filteredList = temp;
    }

    temp = new ArrayList<>();
    for (BookModel book : filteredList) {
      if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
        temp.add(book);
      }
    }
    filteredList = temp;

    if (sortBy.equals("title")) {
      filteredList.sort(Comparator.comparing(BookModel::getTitle));
    } else if (sortBy.equals("price")) {
      filteredList.sort(Comparator.comparing(BookModel::getPrice));
    } else if (sortBy.equals("author")) {
      filteredList.sort((book1, book2) -> {
        AuthorModel author1 = AuthorBUS
          .getInstance()
          .getModelById(book1.getAuthorId());
        AuthorModel author2 = AuthorBUS
          .getInstance()
          .getModelById(book2.getAuthorId());
        return author1.getName().compareTo(author2.getName());
      });
    }
    shopCustomerPanel.updateList(filteredList);
  };
}
