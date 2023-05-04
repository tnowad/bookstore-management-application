package com.bookstore.gui.forms.books;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class BookDetailPanel extends javax.swing.JPanel {

  public BookDetailPanel() {
    initComponents();
  }

  private void initComponents() {
    bookTitleHeaderPanel = new JPanel();
    bookTitleTextField = new JTextField();
    bookDescriptionPanel = new JPanel();
    descriptionTextArea = new JTextArea();
    descriptionLabel = new JLabel();
    bookDetailsPanel = new JPanel();
    bookImagePanel = new JPanel();
    bookInformationPanel = new JPanel();
    priceAndStatusField = new JPanel();
    priceLabel = new JLabel();
    bookPriceTextField = new JTextField();
    statusLabel = new JLabel();
    bookStatusTextField = new JTextField();
    isbnAndAuthorAndQuantityPanel = new JPanel();
    isbnLabel = new JLabel();
    bookIsbnTextField = new JTextField();
    authorLabel = new JLabel();
    bookAuthorTextField = new JTextField();
    bookAvailableQuantity = new JLabel();
    bookQuantityTextField = new JTextField();
    buttonPanel = new JPanel();
    quantitySpinner = new JSpinner();
    addToCartButton = new JButton();
    buyNowButton = new JButton();

    setMaximumSize(new java.awt.Dimension(750, 530));
    setMinimumSize(new java.awt.Dimension(750, 530));
    setPreferredSize(new java.awt.Dimension(800, 530));
    setLayout(new java.awt.BorderLayout());

    bookTitleTextField.setEditable(false);
    bookTitleTextField.setFont(new java.awt.Font("Arial", 0, 14));
    bookTitleTextField.setMaximumSize(new java.awt.Dimension(830, 30));
    bookTitleTextField.setMinimumSize(new java.awt.Dimension(830, 30));
    bookTitleTextField.setPreferredSize(new java.awt.Dimension(830, 30));
    bookTitleTextField.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          bookTitleTextFieldActionPerformed(evt);
        }
      }
    );
    bookTitleHeaderPanel.add(bookTitleTextField);

    add(bookTitleHeaderPanel, java.awt.BorderLayout.PAGE_START);

    bookDescriptionPanel.setPreferredSize(new java.awt.Dimension(830, 200));
    bookDescriptionPanel.setLayout(new java.awt.BorderLayout());

    descriptionTextArea.setEditable(false);
    descriptionTextArea.setColumns(20);
    descriptionTextArea.setRows(5);
    descriptionTextArea.setMaximumSize(new java.awt.Dimension(232, 160));
    descriptionTextArea.setMinimumSize(new java.awt.Dimension(232, 160));
    descriptionTextArea.setPreferredSize(new java.awt.Dimension(232, 160));
    bookDescriptionPanel.add(
      descriptionTextArea,
      java.awt.BorderLayout.PAGE_END
    );

    descriptionLabel.setFont(new java.awt.Font("Arial", 1, 18));
    descriptionLabel.setText("Desciption:");
    bookDescriptionPanel.add(descriptionLabel, java.awt.BorderLayout.CENTER);

    add(bookDescriptionPanel, java.awt.BorderLayout.PAGE_END);

    bookDetailsPanel.setLayout(new java.awt.BorderLayout());

    bookImagePanel.setPreferredSize(new java.awt.Dimension(300, 290));
    bookImagePanel.setLayout(new java.awt.BorderLayout());
    bookDetailsPanel.add(bookImagePanel, java.awt.BorderLayout.LINE_START);

    bookInformationPanel.setLayout(new java.awt.BorderLayout());

    priceAndStatusField.setLayout(
      new java.awt.FlowLayout(java.awt.FlowLayout.LEFT)
    );

    priceLabel.setFont(new java.awt.Font("Arial", 0, 14));
    priceLabel.setText("Price:");
    priceLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
    priceLabel.setPreferredSize(new java.awt.Dimension(50, 30));
    priceAndStatusField.add(priceLabel);

    bookPriceTextField.setEditable(false);
    bookPriceTextField.setFont(new java.awt.Font("Arial", 0, 14));
    bookPriceTextField.setPreferredSize(new java.awt.Dimension(150, 30));
    bookPriceTextField.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          bookPriceTextFieldActionPerformed(evt);
        }
      }
    );
    priceAndStatusField.add(bookPriceTextField);

    statusLabel.setFont(new java.awt.Font("Arial", 0, 14));
    statusLabel.setText("Status:");
    statusLabel.setPreferredSize(new java.awt.Dimension(50, 30));
    priceAndStatusField.add(statusLabel);

    bookStatusTextField.setEditable(false);
    bookStatusTextField.setFont(new java.awt.Font("Arial", 0, 14));
    bookStatusTextField.setPreferredSize(new java.awt.Dimension(150, 30));
    bookStatusTextField.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          bookStatusTextFieldActionPerformed(evt);
        }
      }
    );
    priceAndStatusField.add(bookStatusTextField);

    bookInformationPanel.add(
      priceAndStatusField,
      java.awt.BorderLayout.PAGE_START
    );

    isbnAndAuthorAndQuantityPanel.setLayout(
      new java.awt.GridLayout(3, 2, 5, 5)
    );

    isbnLabel.setFont(new java.awt.Font("Arial", 0, 14));
    isbnLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    isbnLabel.setText("ISBN:");
    isbnAndAuthorAndQuantityPanel.add(isbnLabel);

    bookIsbnTextField.setEditable(false);
    bookIsbnTextField.setFont(new java.awt.Font("Arial", 0, 14));
    bookIsbnTextField.setPreferredSize(new java.awt.Dimension(200, 23));
    bookIsbnTextField.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          bookIsbnTextFieldActionPerformed(evt);
        }
      }
    );
    isbnAndAuthorAndQuantityPanel.add(bookIsbnTextField);

    authorLabel.setFont(new java.awt.Font("Arial", 0, 14));
    authorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    authorLabel.setText("Author:");
    isbnAndAuthorAndQuantityPanel.add(authorLabel);

    bookAuthorTextField.setEditable(false);
    bookAuthorTextField.setFont(new java.awt.Font("Arial", 0, 14));
    bookAuthorTextField.setPreferredSize(new java.awt.Dimension(100, 23));
    bookAuthorTextField.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          bookAuthorTextFieldActionPerformed(evt);
        }
      }
    );
    isbnAndAuthorAndQuantityPanel.add(bookAuthorTextField);

    bookAvailableQuantity.setFont(new java.awt.Font("Arial", 0, 14));
    bookAvailableQuantity.setHorizontalAlignment(
      javax.swing.SwingConstants.CENTER
    );
    bookAvailableQuantity.setText("Available Quantity:");
    bookAvailableQuantity.setHorizontalTextPosition(
      javax.swing.SwingConstants.CENTER
    );
    bookAvailableQuantity.setPreferredSize(new java.awt.Dimension(100, 30));
    isbnAndAuthorAndQuantityPanel.add(bookAvailableQuantity);

    bookQuantityTextField.setEditable(false);
    bookQuantityTextField.setFont(new java.awt.Font("Arial", 0, 14));
    bookQuantityTextField.setPreferredSize(new java.awt.Dimension(100, 23));
    isbnAndAuthorAndQuantityPanel.add(bookQuantityTextField);

    bookInformationPanel.add(
      isbnAndAuthorAndQuantityPanel,
      java.awt.BorderLayout.CENTER
    );

    quantitySpinner.setFont(new java.awt.Font("Arial", 0, 14));
    quantitySpinner.setPreferredSize(new java.awt.Dimension(70, 30));
    quantitySpinner.addPropertyChangeListener(
      new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
          quantitySpinnerPropertyChange(evt);
        }
      }
    );
    buttonPanel.add(quantitySpinner);

    addToCartButton.setFont(new java.awt.Font("Arial", 0, 14));
    addToCartButton.setText("Add to cart");
    addToCartButton.setPreferredSize(new java.awt.Dimension(100, 30));
    addToCartButton.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          addToCartButtonActionPerformed(evt);
        }
      }
    );
    buttonPanel.add(addToCartButton);

    buyNowButton.setFont(new java.awt.Font("Arial", 0, 14));
    buyNowButton.setText("Buy now");
    buyNowButton.setPreferredSize(new java.awt.Dimension(90, 30));
    buttonPanel.add(buyNowButton);

    bookInformationPanel.add(buttonPanel, java.awt.BorderLayout.PAGE_END);

    bookDetailsPanel.add(bookInformationPanel, java.awt.BorderLayout.CENTER);

    add(bookDetailsPanel, java.awt.BorderLayout.CENTER);
  } // </editor-fold>

  private void bookTitleTextFieldActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private void bookPriceTextFieldActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private void bookStatusTextFieldActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private void bookIsbnTextFieldActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private void bookAuthorTextFieldActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private void addToCartButtonActionPerformed(java.awt.event.ActionEvent evt) {}

  private void quantitySpinnerPropertyChange(
    java.beans.PropertyChangeEvent evt
  ) {}

  // Variables declaration - do not modify
  private JButton addToCartButton;
  private JLabel authorLabel;
  private JTextField bookAuthorTextField;
  private JLabel bookAvailableQuantity;
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
  private JButton buyNowButton;
  private JLabel descriptionLabel;
  private JTextArea descriptionTextArea;
  private JPanel isbnAndAuthorAndQuantityPanel;
  private JLabel isbnLabel;
  private JPanel priceAndStatusField;
  private JLabel priceLabel;
  private JSpinner quantitySpinner;
  private JLabel statusLabel;
}
