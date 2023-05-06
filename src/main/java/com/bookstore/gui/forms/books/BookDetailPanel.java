package com.bookstore.gui.forms.books;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class BookDetailPanel extends JPanel {

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

    setMaximumSize(new Dimension(750, 530));
    setMinimumSize(new Dimension(750, 530));
    setPreferredSize(new Dimension(800, 530));
    setLayout(new BorderLayout());

    bookTitleTextField.setEditable(false);
    bookTitleTextField.setFont(new Font("Arial", 0, 14));
    bookTitleTextField.setMaximumSize(new Dimension(830, 30));
    bookTitleTextField.setMinimumSize(new Dimension(830, 30));
    bookTitleTextField.setPreferredSize(new Dimension(830, 30));
    bookTitleTextField.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          bookTitleTextFieldActionPerformed(evt);
        }
      }
    );
    bookTitleHeaderPanel.add(bookTitleTextField);

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

    descriptionLabel.setFont(new Font("Arial", 1, 18));
    descriptionLabel.setText("Description:");
    bookDescriptionPanel.add(descriptionLabel, BorderLayout.CENTER);

    add(bookDescriptionPanel, BorderLayout.PAGE_END);

    bookDetailsPanel.setLayout(new BorderLayout());

    bookImagePanel.setPreferredSize(new Dimension(300, 290));
    bookImagePanel.setLayout(new BorderLayout());
    bookDetailsPanel.add(bookImagePanel, BorderLayout.LINE_START);

    bookInformationPanel.setLayout(new BorderLayout());

    priceAndStatusField.setLayout(new FlowLayout(FlowLayout.LEFT));

    priceLabel.setFont(new Font("Arial", 0, 14));
    priceLabel.setText("Price:");
    priceLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
    priceLabel.setPreferredSize(new Dimension(50, 30));
    priceAndStatusField.add(priceLabel);

    bookPriceTextField.setEditable(false);
    bookPriceTextField.setFont(new Font("Arial", 0, 14));
    bookPriceTextField.setPreferredSize(new Dimension(150, 30));
    bookPriceTextField.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          bookPriceTextFieldActionPerformed(evt);
        }
      }
    );
    priceAndStatusField.add(bookPriceTextField);

    statusLabel.setFont(new Font("Arial", 0, 14));
    statusLabel.setText("Status:");
    statusLabel.setPreferredSize(new Dimension(50, 30));
    priceAndStatusField.add(statusLabel);

    bookStatusTextField.setEditable(false);
    bookStatusTextField.setFont(new Font("Arial", 0, 14));
    bookStatusTextField.setPreferredSize(new Dimension(150, 30));
    bookStatusTextField.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          bookStatusTextFieldActionPerformed(evt);
        }
      }
    );
    priceAndStatusField.add(bookStatusTextField);

    bookInformationPanel.add(priceAndStatusField, BorderLayout.PAGE_START);

    isbnAndAuthorAndQuantityPanel.setLayout(new GridLayout(3, 2, 5, 5));

    isbnLabel.setFont(new Font("Arial", 0, 14));
    isbnLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    isbnLabel.setText("ISBN:");
    isbnAndAuthorAndQuantityPanel.add(isbnLabel);

    bookIsbnTextField.setEditable(false);
    bookIsbnTextField.setFont(new Font("Arial", 0, 14));
    bookIsbnTextField.setPreferredSize(new Dimension(200, 23));
    bookIsbnTextField.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          bookIsbnTextFieldActionPerformed(evt);
        }
      }
    );
    isbnAndAuthorAndQuantityPanel.add(bookIsbnTextField);

    authorLabel.setFont(new Font("Arial", 0, 14));
    authorLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    authorLabel.setText("Author:");
    isbnAndAuthorAndQuantityPanel.add(authorLabel);

    bookAuthorTextField.setEditable(false);
    bookAuthorTextField.setFont(new Font("Arial", 0, 14));
    bookAuthorTextField.setPreferredSize(new Dimension(100, 23));
    bookAuthorTextField.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          bookAuthorTextFieldActionPerformed(evt);
        }
      }
    );
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

    quantitySpinner.setFont(new Font("Arial", 0, 14));
    quantitySpinner.setPreferredSize(new Dimension(70, 30));
    quantitySpinner.addPropertyChangeListener(
      new java.beans.PropertyChangeListener() {
        public void propertyChange(java.beans.PropertyChangeEvent evt) {
          quantitySpinnerPropertyChange(evt);
        }
      }
    );
    buttonPanel.add(quantitySpinner);

    addToCartButton.setFont(new Font("Arial", 0, 14));
    addToCartButton.setText("Add to cart");
    addToCartButton.setPreferredSize(new Dimension(100, 30));
    addToCartButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          addToCartButtonActionPerformed(evt);
        }
      }
    );
    buttonPanel.add(addToCartButton);

    buyNowButton.setFont(new Font("Arial", 0, 14));
    buyNowButton.setText("Buy now");
    buyNowButton.setPreferredSize(new Dimension(90, 30));
    buttonPanel.add(buyNowButton);

    bookInformationPanel.add(buttonPanel, BorderLayout.PAGE_END);

    bookDetailsPanel.add(bookInformationPanel, BorderLayout.CENTER);

    add(bookDetailsPanel, BorderLayout.CENTER);
  } 

  private void bookTitleTextFieldActionPerformed(ActionEvent evt) {}

  private void bookPriceTextFieldActionPerformed(ActionEvent evt) {}

  private void bookStatusTextFieldActionPerformed(ActionEvent evt) {}

  private void bookIsbnTextFieldActionPerformed(ActionEvent evt) {}

  private void bookAuthorTextFieldActionPerformed(ActionEvent evt) {}

  private void addToCartButtonActionPerformed(ActionEvent evt) {}

  private void quantitySpinnerPropertyChange(
    java.beans.PropertyChangeEvent evt
  ) {}
}
