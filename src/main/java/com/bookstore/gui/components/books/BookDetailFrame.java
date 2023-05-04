package com.bookstore.gui.components.books;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.bus.PublisherBUS;
import com.bookstore.enums.BookStatus;
import com.bookstore.models.BookModel;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BookDetailFrame extends javax.swing.JFrame {

  public BookDetailFrame(BookModel book) {
    initComponents(book);
    setStatus(book.getStatus());
    setImage(book.getImage());
    setTitle("Book");
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents(BookModel book) {
    contendPanel = new javax.swing.JPanel();
    getImageBook = new javax.swing.JLabel();
    informationPanel = new javax.swing.JPanel();
    setTitle = new javax.swing.JTextField();
    priceText = new javax.swing.JLabel();
    setPrice = new javax.swing.JTextField();
    statusText = new javax.swing.JLabel();
    setStatus = new javax.swing.JComboBox<>();
    isbnText = new javax.swing.JLabel();
    setIsbn = new javax.swing.JTextField();
    quantityText = new javax.swing.JLabel();
    setAvailableQuantity = new javax.swing.JTextField();
    authorText = new javax.swing.JLabel();
    setAuthorName = new javax.swing.JTextField();
    publisherText = new javax.swing.JLabel();
    setPublisherName = new javax.swing.JTextField();
    descriptionPanel = new javax.swing.JPanel();
    descriptionText = new javax.swing.JLabel();
    descriptionContend = new javax.swing.JPanel();
    buttonPanel = new javax.swing.JPanel();
    buttonBack = new javax.swing.JButton();
    buttonSave = new javax.swing.JButton();
    scrollPane = new javax.swing.JScrollPane();
    setDescription = new javax.swing.JTextArea();

    setPreferredSize(new java.awt.Dimension(900, 539));
    getContentPane()
        .setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

    contendPanel.setPreferredSize(new java.awt.Dimension(858, 280));
    contendPanel.setLayout(new java.awt.BorderLayout());

    getImageBook.setBorder(
        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
    getImageBook.setEnabled(false);
    getImageBook.setPreferredSize(new java.awt.Dimension(200, 16));
    contendPanel.add(getImageBook, java.awt.BorderLayout.LINE_START);

    informationPanel.setPreferredSize(new java.awt.Dimension(655, 300));
    informationPanel.setLayout(
        new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

    setTitle.setEditable(false);
    setTitle.setFont(new java.awt.Font("Segoe UI", 1, 14));
    setTitle.setText(book.getTitle());
    setTitle.setPreferredSize(new java.awt.Dimension(600, 26));
    informationPanel.add(setTitle);

    priceText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    priceText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    priceText.setText("Price:");
    priceText.setPreferredSize(new java.awt.Dimension(100, 25));
    informationPanel.add(priceText);

    setPrice.setFont(new java.awt.Font("Segoe UI", 1, 18));
    setPrice.setForeground(new java.awt.Color(255, 51, 51));
    setPrice.setText("" + book.getPrice());
    informationPanel.add(setPrice);

    statusText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    statusText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    statusText.setText("Status:");
    statusText.setPreferredSize(new java.awt.Dimension(290, 25));
    informationPanel.add(statusText);

    setStatus.setFont(new java.awt.Font("Segoe UI", 1, 12));
    setStatus.setModel(
        new javax.swing.DefaultComboBoxModel<>(
            new String[] { "AVAILABLE", "UNAVAILABLE", "DELETED" }));
    informationPanel.add(setStatus);

    isbnText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    isbnText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    isbnText.setText("ISBN");
    isbnText.setPreferredSize(new java.awt.Dimension(150, 25));
    informationPanel.add(isbnText);

    setIsbn.setEditable(false);
    setIsbn.setFont(new java.awt.Font("Segoe UI", 1, 18));
    setIsbn.setText("" + book.getIsbn());
    setIsbn.setPreferredSize(new java.awt.Dimension(290, 31));
    informationPanel.add(setIsbn);

    quantityText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    quantityText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    quantityText.setText("Available Quantity");
    quantityText.setPreferredSize(new java.awt.Dimension(230, 25));
    informationPanel.add(quantityText);

    setAvailableQuantity.setFont(new java.awt.Font("Segoe UI", 1, 18));
    setAvailableQuantity.setText("" + book.getQuantity());
    setAvailableQuantity.setPreferredSize(new java.awt.Dimension(290, 31));
    informationPanel.add(setAvailableQuantity);

    authorText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    authorText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    authorText.setText("Author");
    authorText.setPreferredSize(new java.awt.Dimension(230, 25));
    informationPanel.add(authorText);

    setAuthorName.setEditable(false);
    setAuthorName.setFont(new java.awt.Font("Segoe UI", 1, 18));
    setAuthorName.setText(
        AuthorBUS.getInstance().getModelById(book.getAuthorId()).getName());
    setAuthorName.setPreferredSize(new java.awt.Dimension(290, 31));
    informationPanel.add(setAuthorName);

    publisherText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    publisherText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    publisherText.setText("Publisher");
    publisherText.setPreferredSize(new java.awt.Dimension(230, 25));
    informationPanel.add(publisherText);

    setPublisherName.setEditable(false);
    setPublisherName.setFont(new java.awt.Font("Segoe UI", 1, 18));
    setPublisherName.setText(
        PublisherBUS.getInstance().getModelById(book.getPublisherId()).getName());
    setPublisherName.setPreferredSize(new java.awt.Dimension(290, 31));
    informationPanel.add(setPublisherName);

    contendPanel.add(informationPanel, java.awt.BorderLayout.CENTER);

    getContentPane().add(contendPanel);

    descriptionPanel.setPreferredSize(new java.awt.Dimension(863, 220));
    descriptionPanel.setLayout(new java.awt.BorderLayout());

    descriptionText.setFont(new java.awt.Font("Segoe UI", 1, 24));
    descriptionText.setText("Description:");
    descriptionPanel.add(descriptionText, java.awt.BorderLayout.PAGE_START);

    descriptionContend.setLayout(new java.awt.BorderLayout());

    buttonPanel.setPreferredSize(new java.awt.Dimension(863, 50));
    buttonPanel.setLayout(
        new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5));

    buttonBack.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/resources/icons/back.png")));
    buttonBack.setPreferredSize(new java.awt.Dimension(80, 30));
    buttonBack.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonBack);
            frame.dispose();
          }
        });
    buttonPanel.add(buttonBack);

    buttonSave.setIcon(
        new javax.swing.ImageIcon(
            getClass().getResource("/resources/icons/save.png")));
    buttonSave.setPreferredSize(new java.awt.Dimension(80, 30));
    buttonSave.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            actionSave(book);
          }
        });
    buttonPanel.add(buttonSave);

    descriptionContend.add(buttonPanel, java.awt.BorderLayout.PAGE_END);

    setDescription.setColumns(5);
    setDescription.setFont(new java.awt.Font("Segoe UI", 3, 15));
    setDescription.setLineWrap(true);
    setDescription.setRows(5);
    setDescription.setText(book.getDescription());
    scrollPane.setViewportView(setDescription);

    descriptionContend.add(scrollPane, java.awt.BorderLayout.CENTER);

    descriptionPanel.add(descriptionContend, java.awt.BorderLayout.CENTER);

    getContentPane().add(descriptionPanel);

    pack();
  }

  public void setStatus(BookStatus status) {
    int index = -1;
    switch (status.toString()) {
      case "AVAILABLE" -> {
        index = 0;
      }
      case "UNAVAILABLE" -> {
        index = 1;
      }
      case "DELETED" -> {
        index = 2;
      }
    }
    setStatus.setSelectedIndex(index);
  }

  public void actionSave(BookModel book) {
    Object selectedStatusItem = setStatus.getSelectedItem();
    if (selectedStatusItem != null) {
      String statusString = selectedStatusItem.toString().toUpperCase();
      BookStatus newStatus = BookStatus.valueOf(statusString);
      int newPrice = Integer.valueOf(setPrice.getText());
      int newQuantity = Integer.valueOf(setAvailableQuantity.getText());
      String newDescription = setDescription.getText();
      BookModel newBook = new BookModel();
      newBook.setIsbn(book.getIsbn());
      newBook.setTitle(book.getTitle());
      newBook.setDescription(newDescription);
      newBook.setImage(book.getImage());
      newBook.setPrice(newPrice);
      newBook.setQuantity(newQuantity);
      newBook.setStatus(newStatus);
      newBook.setAuthorId(book.getAuthorId());
      newBook.setPublisherId(book.getPublisherId());
      int confirm = JOptionPane.showConfirmDialog(
          null,
          "Do you want to continue?",
          "Confirmation",
          JOptionPane.YES_NO_OPTION);
      if (confirm == JOptionPane.YES_OPTION) {
        BookBUS.getInstance().updateModel(newBook);
      }
    }
    for (Frame window : JFrame.getFrames()) {
      if (window instanceof JFrame) {
        JFrame frame = (JFrame) window;
        frame.setVisible(false);
        frame.dispose();
        frame.setVisible(true);
      }
    }
  }

  public void setImage(String image) {
    getImageBook.setIcon(new javax.swing.ImageIcon(image));
    ImageIcon icon = (ImageIcon) getImageBook.getIcon();
    int imageLoadStatus = icon.getImageLoadStatus();
    if (imageLoadStatus != 8) {
      getImageBook.setIcon(
          new javax.swing.ImageIcon(
              getClass().getResource("/resources/images/product-placeholder.png")));
    }
  }

  private javax.swing.JLabel authorText;
  private javax.swing.JButton buttonBack;
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JButton buttonSave;
  private javax.swing.JPanel contendPanel;
  private javax.swing.JPanel descriptionContend;
  private javax.swing.JPanel descriptionPanel;
  private javax.swing.JLabel descriptionText;
  private javax.swing.JLabel getImageBook;
  private javax.swing.JPanel informationPanel;
  private javax.swing.JLabel isbnText;
  private javax.swing.JLabel priceText;
  private javax.swing.JLabel publisherText;
  private javax.swing.JLabel quantityText;
  private javax.swing.JScrollPane scrollPane;
  private javax.swing.JTextField setAuthorName;
  private javax.swing.JTextField setAvailableQuantity;
  private javax.swing.JTextArea setDescription;
  private javax.swing.JTextField setIsbn;
  private javax.swing.JTextField setPrice;
  private javax.swing.JTextField setPublisherName;
  private javax.swing.JComboBox<String> setStatus;
  private javax.swing.JTextField setTitle;
  private javax.swing.JLabel statusText;
}
