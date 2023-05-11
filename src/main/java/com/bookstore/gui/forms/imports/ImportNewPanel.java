package com.bookstore.gui.forms.imports;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.bus.BooksCategoryBUS;
import com.bookstore.bus.CategoryBUS;
import com.bookstore.bus.ImportBUS;
import com.bookstore.bus.ImportItemsBUS;
import com.bookstore.bus.ProviderBUS;
import com.bookstore.bus.PublisherBUS;
import com.bookstore.dao.DatabaseConnection;
import com.bookstore.enums.BookStatus;
import com.bookstore.gui.components.dialogs.Dialog;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.forms.general.NoDataPanel;
import com.bookstore.models.AuthorModel;
import com.bookstore.models.BookModel;
import com.bookstore.models.BooksCategoryModel;
import com.bookstore.models.CategoryModel;
import com.bookstore.models.ImportItemsModel;
import com.bookstore.models.ImportModel;
import com.bookstore.models.ProviderModel;
import com.bookstore.models.PublisherModel;
import com.bookstore.models.UserModel;
import com.bookstore.models.tables.BookTableModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.PDF.PDFWriter;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ImportNewPanel extends JPanel {

  private JButton addBookButton;
  private JButton backToPreviousButton;
  private JButton saveButton;

  private JButton findBookButton;
  private JButton findProviderButton;
  private JButton findPublisherButton;

  private JLabel authorLabel;
  private JLabel bookIsbnLabel;
  private JLabel categoriesLabel;
  private JLabel publisherLabel;
  private JLabel priceLabel;
  private JLabel providerLabel;
  private JLabel quantityLabel;
  private JLabel titleBookLabel;
  private JLabel titleLabel;
  private JLabel totalPriceLabel;
  private JPanel actionPanel;
  private JPanel bookFormPanel;
  private JPanel bookInformationPanel;
  private JPanel bookListPanel;
  private JPanel importFormPanel;
  private JScrollPane bookListScrollPane;
  private JTextField authorTextField;
  private JTextField bookIsbnTextField;
  private JTextField categoriesTextField;
  private JTextField publisherTextField;
  private JTextField priceTextField;
  private JTextField providerTextField;
  private JTextField quantityTextField;
  private JTextField titleBookTextfield;

  private JTable bookListTable;
  private ImportModel importModel;
  private ProviderModel providerModel;
  private PublisherModel publisherModel;
  private BookTableModel bookTableModel;
  private List<ImportItemsModel> importItemsList = new ArrayList<ImportItemsModel>();
  private JLabel descriptionBookLabel;
  private JTextField descriptionBookTextfield;
  private UserModel userModel = Authentication.getCurrentUser();
  private int count;

  public ImportNewPanel() {
    initComponents();
    handleEvent();
    // if (importItemsList.size() > 0) {
    bookImportListTable();
    // }
  }

  private void bookImportListTable() {
    if (importItemsList.size() > 0) {
      bookListPanel.removeAll();
      DefaultTableModel model = new DefaultTableModel();
      model.addColumn("Isbn");
      model.addColumn("Book name");
      model.addColumn("Description");
      model.addColumn("Price");
      model.addColumn("Acthor");
      model.addColumn("Publisher");
      model.addColumn("Quantity");
      model.addColumn("Total price");
      for (ImportItemsModel importItemsModel : importItemsList) {
        BookModel bookModel = BookBUS
          .getInstance()
          .getBookByIsbn(importItemsModel.getBookIsbn());
        model.addRow(
          new Object[] {
            importItemsModel.getBookIsbn(),
            bookModel.getTitle(),
            bookModel.getDescription(),
            bookModel.getTitle(),
            bookModel.getAuthorId(),
            bookModel.getPublisherId(),
            importItemsModel.getQuantity(),
            importItemsModel.getPrice(),
          }
        );
        bookListTable.setModel(model);
      }
      bookListTable.getTableHeader().setReorderingAllowed(false);
      DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
      centerRenderer.setHorizontalAlignment(JLabel.CENTER);
      for (int i = 0; i < bookListTable.getColumnCount(); i++) {
        bookListTable
          .getColumnModel()
          .getColumn(i)
          .setCellRenderer(centerRenderer);
      }
      bookListScrollPane.setViewportView(bookListTable);
      bookListPanel.add(bookListScrollPane);
      bookListPanel.revalidate();
      bookListPanel.repaint();
    } else {
      bookListPanel.removeAll();
      bookListPanel.setLayout(new FlowLayout());
      bookListPanel.add(new NoDataPanel("Don't have import items"));
      bookListPanel.revalidate();
      bookListPanel.repaint();
    }
  }

  private void initComponents() {
    GridBagConstraints gridBagConstraints;

    titleLabel = new JLabel();
    importFormPanel = new JPanel();
    importFormPanel.setBackground(Color.WHITE);
    publisherLabel = new JLabel();
    publisherTextField = new JTextField();
    providerLabel = new JLabel();
    providerTextField = new JTextField();
    bookFormPanel = new JPanel();
    bookFormPanel.setBackground(Color.WHITE);
    bookInformationPanel = new JPanel();
    bookInformationPanel.setBackground(Color.WHITE);
    bookIsbnLabel = new JLabel();
    bookIsbnTextField = new JTextField();
    titleBookLabel = new JLabel();
    titleBookTextfield = new JTextField();
    descriptionBookLabel = new JLabel();
    descriptionBookTextfield = new JTextField();
    quantityLabel = new JLabel();
    quantityTextField = new JTextField();
    categoriesLabel = new JLabel();
    categoriesTextField = new JTextField();
    priceLabel = new JLabel();
    priceTextField = new JTextField();
    authorLabel = new JLabel();
    authorTextField = new JTextField();

    addBookButton = new JButton();
    findBookButton = new JButton();
    bookListPanel = new JPanel();
    bookListPanel.setBackground(Color.WHITE);
    bookListScrollPane = new JScrollPane();

    actionPanel = new JPanel();
    actionPanel.setBackground(Color.WHITE);
    saveButton = new JButton();
    totalPriceLabel = new JLabel();
    backToPreviousButton = new JButton("Back to previous");

    setLayout(new GridBagLayout());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    add(backToPreviousButton, gridBagConstraints);

    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setFont(new Font("Segoe UI", 0, 36));
    titleLabel.setText("Book list import");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(titleLabel, gridBagConstraints);

    importFormPanel.setBorder(
      BorderFactory.createTitledBorder("Book Information")
    );
    importFormPanel.setLayout(new GridLayout(2, 2, 2, 0));

    publisherLabel.setText("Publisher ");
    importFormPanel.add(publisherLabel);
    importFormPanel.add(publisherTextField);

    providerLabel.setText("Provider ");
    importFormPanel.add(providerLabel);

    findPublisherButton = new JButton("Find publisher");
    JPanel publisherPanel = new JPanel(new BorderLayout());
    publisherPanel.setBackground(Color.WHITE);
    publisherPanel.add(publisherTextField, BorderLayout.CENTER);
    publisherPanel.add(findPublisherButton, BorderLayout.LINE_END);
    importFormPanel.add(publisherPanel);

    findProviderButton = new JButton("Find provider");
    JPanel providerPanel = new JPanel(new BorderLayout());
    providerPanel.setBackground(Color.WHITE);
    providerPanel.add(providerTextField, BorderLayout.CENTER);
    providerPanel.add(findProviderButton, BorderLayout.LINE_END);
    importFormPanel.add(providerPanel);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(importFormPanel, gridBagConstraints);

    bookFormPanel.setBorder(
      BorderFactory.createTitledBorder("Book Information")
    );
    bookFormPanel.setLayout(new BorderLayout());

    bookInformationPanel.setLayout(new GridLayout(7, 2));

    bookIsbnLabel.setText("Book ISBN");
    bookInformationPanel.add(bookIsbnLabel);
    bookInformationPanel.add(bookIsbnTextField);

    titleBookLabel.setText("Name book");
    bookInformationPanel.add(titleBookLabel);

    bookInformationPanel.add(titleBookTextfield);

    descriptionBookLabel.setText("Description");
    bookInformationPanel.add(descriptionBookLabel);

    bookInformationPanel.add(descriptionBookTextfield);

    quantityLabel.setText("Quantity");
    bookInformationPanel.add(quantityLabel);
    bookInformationPanel.add(quantityTextField);

    categoriesLabel.setText("Category");
    bookInformationPanel.add(categoriesLabel);
    bookInformationPanel.add(categoriesTextField);

    priceLabel.setText("Price");
    bookInformationPanel.add(priceLabel);

    bookInformationPanel.add(priceTextField);

    authorLabel.setText("Author");
    bookInformationPanel.add(authorLabel);

    bookInformationPanel.add(authorTextField);

    bookFormPanel.add(bookInformationPanel, BorderLayout.CENTER);

    addBookButton.setText("Add book");
    findBookButton.setText("Find Book");
    JPanel groupButtonPanel = new JPanel();
    groupButtonPanel.setBackground(Color.WHITE);
    groupButtonPanel.setLayout(new FlowLayout());
    groupButtonPanel.add(addBookButton);
    groupButtonPanel.add(findBookButton);
    bookFormPanel.add(groupButtonPanel, BorderLayout.PAGE_END);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(bookFormPanel, gridBagConstraints);

    bookListPanel.setPreferredSize(new Dimension(1000, 500));
    bookListPanel.setBorder(BorderFactory.createTitledBorder("Import detail"));
    bookListPanel.setLayout(new BoxLayout(bookListPanel, BoxLayout.LINE_AXIS));

    bookTableModel = new BookTableModel();

    bookListTable = new JTable();
    bookListTable.setFillsViewportHeight(true);
    bookListScrollPane.setViewportView(bookListTable);
    // bookListScrollPane.getVerticalScrollBar().setUnitIncrement(16);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1.0;
    gridBagConstraints.weighty = 1.0;
    add(bookListPanel, gridBagConstraints);

    actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

    saveButton.setText("Save");
    actionPanel.add(saveButton);

    totalPriceLabel.setText("Total price");
    actionPanel.add(totalPriceLabel);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(actionPanel, gridBagConstraints);
  }

  // private void updateList(List<BookModel> bookList) {
  // bookTableModel.setBookList(bookList);
  // bookTableModel.fireTableDataChanged();
  // }

  private void handleEvent() {
    backToPreviousButton.addActionListener(e -> {
      MainPanel.getInstance().backToPreviousForm();
    });
    bookIsbnTextField
      .getDocument()
      .addDocumentListener(
        new DocumentListener() {
          @Override
          public void insertUpdate(DocumentEvent e) {
            System.out.println("Insert");
          }

          @Override
          public void removeUpdate(DocumentEvent e) {
            System.out.println("Remove");
          }

          @Override
          public void changedUpdate(DocumentEvent e) {
            System.out.println("Change");
          }
        }
      );

    findProviderButton.addActionListener(e -> {
      ProviderSearchForm providerSearchForm = new ProviderSearchForm();
      new Dialog(providerSearchForm);

      providerModel = providerSearchForm.find();

      if (providerModel != null) {
        providerTextField.setText(providerModel.getName());
      }
    });

    findPublisherButton.addActionListener(e -> {
      PublisherSearchForm publisherSearchForm = new PublisherSearchForm();
      new Dialog(publisherSearchForm);

      publisherModel = publisherSearchForm.find();

      if (publisherModel != null) {
        publisherTextField.setText(publisherModel.getName());
      }
    });

    findBookButton.addActionListener(e -> {
      BookSearchForm bookSearchForm = new BookSearchForm();
      new Dialog(bookSearchForm);
      BookModel bookModel = bookSearchForm.find();
      if (bookModel != null) {
        bookIsbnTextField.setText(bookModel.getIsbn());
        List<BooksCategoryModel> booksCategoryListFilter = BooksCategoryBUS
          .getInstance()
          .getModelsByIsbn(bookIsbnTextField.getText());
        StringBuilder categoryListFilter = new StringBuilder();
        for (BooksCategoryModel bookModelFilter : booksCategoryListFilter) {
          CategoryModel categoryModelFilter = CategoryBUS
            .getInstance()
            .getModelById(bookModelFilter.getCategoryId());
          if (categoryListFilter.length() > 0) {
            categoryListFilter.append(", ");
          }
          categoryListFilter.append(categoryModelFilter.getName());
        }
        String result = categoryListFilter.toString();
        if (categoryListFilter.length() > 0) {
          categoryListFilter.deleteCharAt(categoryListFilter.length() - 1);
        }

        titleBookTextfield.setText(bookModel.getTitle());
        descriptionBookTextfield.setText(bookModel.getDescription());
        priceTextField.setText(String.valueOf(bookModel.getPrice()));
        categoriesTextField.setText(result);
        AuthorModel authorModel = AuthorBUS
          .getInstance()
          .getModelById(bookModel.getAuthorId());
        authorTextField.setText(authorModel.getName());
        PublisherModel publisherModel = PublisherBUS
          .getInstance()
          .getModelById(bookModel.getPublisherId());
        publisherTextField.setText(publisherModel.getName());
      }
    });

    addBookButton.addActionListener(e -> {
      int option = JOptionPane.showConfirmDialog(
        null,
        "Do you want to add it ?"
      );
      if (option == JOptionPane.YES_OPTION) {
        BookModel book = BookBUS
          .getInstance()
          .getBookByIsbn(bookIsbnTextField.getText());
        int quantity = 0;
        int price = 0;
        try {
          price = Integer.parseInt(quantityTextField.getText());
          quantity = Integer.parseInt(priceTextField.getText());
        } catch (Exception e2) {
          JOptionPane.showMessageDialog(null, "Quantity or price must than 0");
        }
        AuthorModel updateAuthorModel = null;
        PublisherModel updatePublisherModel = null;
        ProviderModel updateProviderModel = null;

        try {
          // create import
          for (ProviderModel providerModel : ProviderBUS
            .getInstance()
            .getAllModels()) {
            if (providerModel.getName().equals(providerTextField.getText())) {
              updateProviderModel = providerModel;
            }
          }

          if (updateProviderModel == null) {
            updateProviderModel = new ProviderModel();
            updateProviderModel.setName(authorTextField.getText());
            updateProviderModel.setDescription("");
            ProviderBUS.getInstance().addModel(updateProviderModel);
            ProviderBUS.getInstance().refreshData();
          }

          for (ProviderModel providerModel : ProviderBUS
            .getInstance()
            .getAllModels()) {
            if (providerModel.getName().equals(authorTextField.getText())) {
              updateProviderModel = providerModel;
              break;
            }
          }
          if (importModel == null) {
            importModel = new ImportModel();
            count = 1;
            for (ImportModel importModel : ImportBUS
              .getInstance()
              .getAllModels()) {
              if (importModel.getId() == count) ++count; else break;
            }
            importModel.setId(count);
            importModel.setEmployeeId(userModel.getId());
            importModel.setProviderId(updateProviderModel.getId());
            importModel.setTotalPrice((double) 0);
          }
          importModel.setTotalPrice(
            importModel.getTotalPrice() + (double) price * quantity
          );
          // ImportBUS.getInstance().updateModel(importModel);

          DatabaseConnection.getInstance().beginTransaction();
          if (book == null) {
            for (AuthorModel authorModel : AuthorBUS
              .getInstance()
              .getAllModels()) {
              if (authorModel.getName().equals(authorTextField.getText())) {
                updateAuthorModel = authorModel;
                break;
              }
            }

            if (updateAuthorModel == null) {
              updateAuthorModel = new AuthorModel();
              updateAuthorModel.setName(authorTextField.getText());
              updateAuthorModel.setDescription("");
              AuthorBUS.getInstance().addModel(updateAuthorModel);
              AuthorBUS.getInstance().refreshData();
            }

            for (PublisherModel publisherModel : PublisherBUS
              .getInstance()
              .getAllModels()) {
              if (
                publisherModel.getName().equals(publisherTextField.getText())
              ) {
                updatePublisherModel = publisherModel;
                break;
              }
            }

            if (updatePublisherModel == null) {
              updatePublisherModel = new PublisherModel();
              updatePublisherModel.setName(publisherTextField.getText());
              updatePublisherModel.setDescription("");
              PublisherBUS.getInstance().addModel(updatePublisherModel);
              PublisherBUS.getInstance().refreshData();
            }

            // 2 dòng for này là để cập nhật lại updateAuthorModel và updatePublisherModel
            // sau khi thêm
            for (AuthorModel authorModel : AuthorBUS
              .getInstance()
              .getAllModels()) {
              if (authorModel.getName().equals(authorTextField.getText())) {
                updateAuthorModel = authorModel;
                break;
              }
            }

            for (PublisherModel publisherModel : PublisherBUS
              .getInstance()
              .getAllModels()) {
              if (
                publisherModel.getName().equals(publisherTextField.getText())
              ) {
                updatePublisherModel = publisherModel;
                break;
              }
            }

            book = new BookModel();
            book.setIsbn(bookIsbnTextField.getText());
            book.setPrice(price);
            book.setTitle(titleBookTextfield.getText());
            book.setDescription(descriptionBookTextfield.getText());
            book.setAuthorId(updateAuthorModel.getId());
            book.setImage("");
            book.setPublisherId(updatePublisherModel.getId());
            book.setQuantity(quantity);
            BookBUS.getInstance().addModel(book);
            BookBUS.getInstance().refreshData();

            ImportItemsModel importItemsModel = new ImportItemsModel();
            importItemsModel.setBookIsbn(book.getIsbn());
            importItemsModel.setPrice(price);
            importItemsModel.setQuantity(quantity);
            importItemsModel.setImportId(importModel.getId());
            importItemsList.add(importItemsModel);
            bookImportListTable();
            totalPriceLabel.setText(
              "Total price:  " + importModel.getTotalPrice()
            );
          } else {
            book.setPrice(price);
            book.setQuantity(book.getQuantity() + quantity);
            book.setStatus(BookStatus.AVAILABLE);
            BookBUS.getInstance().updateModel(book);
            BookBUS.getInstance().refreshData();
            ImportItemsModel importItemsModel = new ImportItemsModel();
            importItemsModel.setBookIsbn(book.getIsbn());
            importItemsModel.setPrice(price);
            importItemsModel.setQuantity(quantity);
            importItemsModel.setImportId(importModel.getId());
            importItemsList.add(importItemsModel);
            bookImportListTable();
            totalPriceLabel.setText(
              "Total price:  " + importModel.getTotalPrice()
            );
          }
          // create import
        } catch (Exception e1) {
          JOptionPane.showMessageDialog(null, e1);
        }
      }
      publisherTextField.setText("");
      providerTextField.setText("");
      bookIsbnTextField.setText("");
      titleBookTextfield.setText("");
      descriptionBookTextfield.setText("");
      quantityTextField.setText("");
      categoriesTextField.setText("");
      priceTextField.setText("");
      authorTextField.setText("");
    });
    saveButton.addActionListener(e -> {
      int option = JOptionPane.showConfirmDialog(
        null,
        "Do you want to save import ? "
      );
      if (option == JOptionPane.YES_OPTION) {
        ImportBUS.getInstance().addModel(importModel);
        for (ImportItemsModel importItemsModel : importItemsList) {
          ImportItemsBUS.getInstance().addModel(importItemsModel);
        }
        publisherTextField.setText("");
        providerTextField.setText("");
        bookIsbnTextField.setText("");
        titleBookTextfield.setText("");
        descriptionBookTextfield.setText("");
        quantityTextField.setText("");
        categoriesTextField.setText("");
        priceTextField.setText("");
        authorTextField.setText("");
        JOptionPane.showMessageDialog(null, "Save import successfully");
        int choice = JOptionPane.showConfirmDialog(
          null,
          "Do you want to print the import to PDF?"
        );
        if (choice == JOptionPane.YES_OPTION) {
          JFileChooser fileChooser = new JFileChooser();
          int result = fileChooser.showSaveDialog(null);
          if (result == JFileChooser.APPROVE_OPTION) {
            java.io.File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();
            PDFWriter
              .getInstance()
              .exportImportsToPDF(importModel.getId(), filePath);
          }
        }
      }
    });
  }
}
