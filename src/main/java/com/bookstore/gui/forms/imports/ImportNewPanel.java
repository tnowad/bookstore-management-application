package com.bookstore.gui.forms.imports;

import com.bookstore.gui.components.dialogs.Dialog;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.BookModel;
import com.bookstore.models.ProviderModel;
import com.bookstore.models.tables.BookTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class ImportNewPanel extends JPanel {

  private JButton addBookButton;
  private JButton backToPreviousButton;
  private JButton exitButton;

  private JButton findBookButton;
  private JButton findProviderButton;

  private JLabel authorLabel;
  private JLabel bookIsbnLabel;
  private JLabel categoriesLabel;
  private JLabel importIdLabel;
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
  private JTextField importIdTextField;
  private JTextField priceTextField;
  private JTextField providerTextField;
  private JTextField quantityTextField;
  private JTextField titleBookTextfield;

  private JTable bookListTable;

  private ProviderModel providerModel;
  private BookTableModel bookTableModel;

  public ImportNewPanel() {
    initComponents();
    handleEvent();
  }

  private void initComponents() {
    GridBagConstraints gridBagConstraints;

    titleLabel = new JLabel();
    importFormPanel = new JPanel();
    importIdLabel = new JLabel();
    importIdTextField = new JTextField();
    providerLabel = new JLabel();
    providerTextField = new JTextField();
    bookFormPanel = new JPanel();
    bookInformationPanel = new JPanel();
    bookIsbnLabel = new JLabel();
    bookIsbnTextField = new JTextField();
    titleBookLabel = new JLabel();
    titleBookTextfield = new JTextField();
    quantityLabel = new JLabel();
    quantityTextField = new JTextField();
    categoriesLabel = new JLabel();
    categoriesTextField = new JTextField();
    priceLabel = new JLabel();
    priceTextField = new JTextField();
    authorLabel = new JLabel();
    authorTextField = new JTextField();
    addBookButton = new JButton();
    bookListPanel = new JPanel();
    bookListScrollPane = new JScrollPane();

    actionPanel = new JPanel();
    exitButton = new JButton();
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

    importIdLabel.setText("Import ID");
    importFormPanel.add(importIdLabel);
    importFormPanel.add(importIdTextField);

    providerLabel.setText("Provider ");
    importFormPanel.add(providerLabel);

    findProviderButton = new JButton("Find provider");
    findProviderButton.addActionListener(findProviderButtonActionListener);
    JPanel providerPanel = new JPanel(new BorderLayout());
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

    bookInformationPanel.setLayout(new GridLayout(6, 2));

    bookIsbnLabel.setText("Book ISBN");
    bookInformationPanel.add(bookIsbnLabel);
    bookInformationPanel.add(bookIsbnTextField);

    titleBookLabel.setText("Name book");
    bookInformationPanel.add(titleBookLabel);

    bookInformationPanel.add(titleBookTextfield);

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
    bookFormPanel.add(addBookButton, BorderLayout.PAGE_END);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(bookFormPanel, gridBagConstraints);

    bookListPanel.setBorder(BorderFactory.createTitledBorder("Import detail"));
    bookListPanel.setLayout(new BoxLayout(bookListPanel, BoxLayout.LINE_AXIS));

    bookTableModel = new BookTableModel();

    bookListTable = new JTable(bookTableModel);
    bookListTable.setFillsViewportHeight(true);
    bookListScrollPane.setViewportView(bookListTable);

    bookListPanel.add(bookListScrollPane);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(bookListPanel, gridBagConstraints);

    actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

    exitButton.setText("Exit");
    actionPanel.add(exitButton);

    totalPriceLabel.setText("Total price");
    actionPanel.add(totalPriceLabel);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(actionPanel, gridBagConstraints);
  }

  private void updateList(List<BookModel> bookList) {
    bookTableModel.setBookList(bookList);
    bookTableModel.fireTableDataChanged();
  }

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
  }

  private ActionListener findProviderButtonActionListener = e -> {
    ProviderSearchForm providerSearchForm = new ProviderSearchForm();
    new Dialog(providerSearchForm);

    providerModel = providerSearchForm.find();

    if (providerModel != null) {
      providerTextField.setText(providerModel.getName());
    }
  };

  private ActionListener addBookButtonActionListener = e -> {
    BookSearchForm bookSearchForm = new BookSearchForm();
    new Dialog(bookSearchForm);

    BookModel bookModel = bookSearchForm.find();

    if (bookModel != null) {
      bookIsbnTextField.setText(bookModel.getIsbn());
      titleBookTextfield.setText(bookModel.getTitle());
      quantityTextField.setText(String.valueOf(bookModel.getQuantity()));
      priceTextField.setText(String.valueOf(bookModel.getPrice()));
    }
  };
}
