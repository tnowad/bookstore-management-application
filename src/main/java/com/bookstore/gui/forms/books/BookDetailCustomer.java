package com.bookstore.gui.forms.books;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.AuthorModel;
import com.bookstore.models.BookModel;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BookDetailCustomer extends JPanel {

  private JButton backPreviousButton;

  private JButton addToCartButton;
  private Label authorLabel;
  private JTextField bookAuthorTextField;
  private Label bookAvailableQuantity;
  private JPanel bookDescriptionPanel;
  private JPanel bookDetailsPanel;
  private JPanel bookImagePanel;
  private JPanel bookInformationPanel;
  private JTextField bookIsbnTextField;
  private JTextField bookPriceTextField;
  private JTextField bookQuantityTextField;
  private JTextField bookStatusTextField;
  private JPanel bookTitleHeaderPanel;
  private JTextField bookTitleTextField;
  private JPanel buttonPanel;
  private Label descriptionLabel;
  private JTextArea descriptionTextArea;
  private JPanel isbnAndAuthorAndQuantityPanel;
  private Label isbnLabel;
  private JScrollPane jScrollPane1;
  private JPanel priceAndStatusField;
  private Label priceLabel;
  private JSpinner quantitySpinner;
  private Label statusLabel;

  private BookModel bookModel;
  private AuthorModel authorModel;

  public BookDetailCustomer(BookModel bookModel) {
    this.bookModel = bookModel;
    initComponents();
    updateDate();
  }

  private void updateDate() {
    authorModel = AuthorBUS.getInstance().getModelById(bookModel.getAuthorId());
    bookTitleTextField.setText(bookModel.getTitle());
    bookPriceTextField.setText("" + bookModel.getPrice());
    bookStatusTextField.setText(bookModel.getStatus().toString());
    bookIsbnTextField.setText(bookModel.getIsbn());
    bookQuantityTextField.setText("" + bookModel.getQuantity());
    descriptionTextArea.setText(bookModel.getDescription());
    bookAuthorTextField.setText(authorModel.getName());
  }

  private void initComponents() {
    backPreviousButton = new JButton("Back Previous");
    backPreviousButton.addActionListener(backPreviousButtonActionListener);

    bookTitleHeaderPanel = new JPanel(new BorderLayout());
    bookTitleTextField = new JTextField();
    bookDescriptionPanel = new JPanel();
    descriptionTextArea = new JTextArea();
    descriptionLabel = new Label("Description");
    jScrollPane1 = new JScrollPane();
    bookDetailsPanel = new JPanel();
    bookImagePanel = new JPanel();
    bookInformationPanel = new JPanel();
    priceAndStatusField = new JPanel();
    priceLabel = new Label("Price");
    bookPriceTextField = new JTextField();
    statusLabel = new Label("Status");
    bookStatusTextField = new JTextField();
    isbnAndAuthorAndQuantityPanel = new JPanel();
    isbnLabel = new Label("Isbn");
    bookIsbnTextField = new JTextField();
    authorLabel = new Label("Author");
    bookAuthorTextField = new JTextField();
    bookAvailableQuantity = new Label("Quantity");
    bookQuantityTextField = new JTextField();
    buttonPanel = new JPanel();
    quantitySpinner = new JSpinner();
    addToCartButton = new JButton();

    setMaximumSize(new Dimension(750, 530));
    setMinimumSize(new Dimension(750, 530));
    setPreferredSize(new Dimension(800, 530));
    setLayout(new BorderLayout());

    bookTitleTextField.setEditable(false);
    bookTitleTextField.setFont(new Font("Arial", 0, 14));
    bookTitleTextField.setHorizontalAlignment(JTextField.CENTER);
    bookTitleTextField.setMaximumSize(new Dimension(830, 30));
    bookTitleTextField.setMinimumSize(new Dimension(830, 30));
    bookTitleTextField.setPreferredSize(new Dimension(830, 30));

    bookTitleHeaderPanel.add(backPreviousButton, BorderLayout.LINE_START);
    bookTitleHeaderPanel.add(bookTitleTextField, BorderLayout.CENTER);

    add(bookTitleHeaderPanel, BorderLayout.PAGE_START);

    bookDescriptionPanel.setPreferredSize(new Dimension(830, 200));
    bookDescriptionPanel.setLayout(new BorderLayout());

    descriptionTextArea.setEditable(false);
    descriptionTextArea.setColumns(20);
    descriptionTextArea.setRows(5);
    descriptionTextArea.setMaximumSize(new Dimension(232, 160));
    descriptionTextArea.setMinimumSize(new Dimension(232, 160));
    descriptionTextArea.setPreferredSize(new Dimension(232, 160));
    bookDescriptionPanel.add(descriptionTextArea, BorderLayout.PAGE_END);

    bookDescriptionPanel.add(descriptionLabel, BorderLayout.CENTER);
    bookDescriptionPanel.add(jScrollPane1, BorderLayout.PAGE_START);

    add(bookDescriptionPanel, BorderLayout.PAGE_END);

    bookDetailsPanel.setLayout(new BorderLayout());

    bookImagePanel.setPreferredSize(new Dimension(300, 290));
    bookImagePanel.setLayout(new BorderLayout());
    bookDetailsPanel.add(bookImagePanel, BorderLayout.LINE_START);

    bookInformationPanel.setLayout(new BorderLayout());

    priceAndStatusField.setLayout(new FlowLayout(FlowLayout.LEFT));

    priceLabel.setHorizontalTextPosition(SwingConstants.LEADING);
    priceLabel.setPreferredSize(new Dimension(50, 30));
    priceAndStatusField.add(priceLabel);

    bookPriceTextField.setEditable(false);
    bookPriceTextField.setFont(new Font("Arial", 0, 14));
    bookPriceTextField.setPreferredSize(new Dimension(150, 30));

    priceAndStatusField.add(bookPriceTextField);

    statusLabel.setPreferredSize(new Dimension(50, 30));
    priceAndStatusField.add(statusLabel);

    bookStatusTextField.setEditable(false);
    bookStatusTextField.setFont(new Font("Arial", 0, 14));
    bookStatusTextField.setPreferredSize(new Dimension(150, 30));

    priceAndStatusField.add(bookStatusTextField);

    bookInformationPanel.add(priceAndStatusField, BorderLayout.PAGE_START);

    isbnAndAuthorAndQuantityPanel.setLayout(new GridLayout(3, 2, 5, 5));

    isbnLabel.setHorizontalAlignment(SwingConstants.CENTER);
    isbnAndAuthorAndQuantityPanel.add(isbnLabel);

    bookIsbnTextField.setEditable(false);
    bookIsbnTextField.setFont(new Font("Arial", 0, 14));
    bookIsbnTextField.setPreferredSize(new Dimension(200, 23));

    isbnAndAuthorAndQuantityPanel.add(bookIsbnTextField);

    authorLabel.setFont(new Font("Arial", 0, 14));
    authorLabel.setHorizontalAlignment(SwingConstants.CENTER);
    authorLabel.setText("Author:");
    isbnAndAuthorAndQuantityPanel.add(authorLabel);

    bookAuthorTextField.setEditable(false);
    bookAuthorTextField.setFont(new Font("Arial", 0, 14));
    bookAuthorTextField.setPreferredSize(new Dimension(100, 23));

    isbnAndAuthorAndQuantityPanel.add(bookAuthorTextField);

    bookAvailableQuantity.setFont(new Font("Arial", 0, 14));
    bookAvailableQuantity.setHorizontalAlignment(SwingConstants.CENTER);
    bookAvailableQuantity.setText("Available Quantity:");
    bookAvailableQuantity.setHorizontalTextPosition(SwingConstants.CENTER);
    bookAvailableQuantity.setPreferredSize(new Dimension(100, 30));
    isbnAndAuthorAndQuantityPanel.add(bookAvailableQuantity);

    bookQuantityTextField.setEditable(false);
    bookQuantityTextField.setFont(new Font("Arial", 0, 14));
    bookQuantityTextField.setPreferredSize(new Dimension(100, 23));
    isbnAndAuthorAndQuantityPanel.add(bookQuantityTextField);

    bookInformationPanel.add(
      isbnAndAuthorAndQuantityPanel,
      BorderLayout.CENTER
    );

    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    quantitySpinner.setFont(new Font("Arial", 0, 14));
    quantitySpinner.setPreferredSize(new Dimension(70, 30));

    buttonPanel.add(quantitySpinner);

    addToCartButton.setFont(new Font("Arial", 0, 14));

    addToCartButton.setText("Add to cart");
    addToCartButton.setPreferredSize(new Dimension(100, 30));

    buttonPanel.add(addToCartButton);

    bookInformationPanel.add(buttonPanel, BorderLayout.PAGE_END);

    bookDetailsPanel.add(bookInformationPanel, BorderLayout.CENTER);

    add(bookDetailsPanel, BorderLayout.CENTER);
  }

  private ActionListener backPreviousButtonActionListener = e -> {
    MainPanel.getInstance().backToPreviousForm();
  };
}
