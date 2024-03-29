package com.bookstore.gui.forms.books;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.bus.PublisherBUS;
import com.bookstore.enums.BookStatus;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.BookModel;
import com.bookstore.util.image.ImageUtils;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public class BookDetailFrame extends JFrame {

  private String stringImage = null;
  private JLabel authorText;
  private JButton buttonBack;
  private JPanel buttonPanel;
  private JButton buttonSave;
  private JPanel contendPanel;
  private JPanel descriptionContend;
  private JPanel descriptionPanel;
  private JLabel descriptionText;
  private JLabel setImage;
  private JPanel informationPanel;
  private JLabel isbnText;
  private JLabel priceText;
  private JLabel publisherText;
  private JLabel quantityText;
  private JScrollPane scrollPane;
  private JTextField setAuthorName;
  private JTextField setAvailableQuantity;
  private JTextArea setDescription;
  private JTextField setIsbn;
  private JTextField setPrice;
  private JTextField setPublisherName;
  private JComboBox<String> setStatus;
  private JTextField setTitle;
  private JLabel statusText;
  private JButton chooseLink;
  private String base64;

  private BookModel bookModel;

  public BookDetailFrame(BookModel book) {
    initComponents(book);
    setStatus(book.getStatus());
    setImage(book.getImage());
    setTitle("Book");
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    this.bookModel = book;
  }

  private void initComponents(BookModel book) {
    setBackground(Color.WHITE);
    contendPanel = new JPanel();
    contendPanel.setBackground(Color.WHITE);
    setImage = new JLabel();
    informationPanel = new JPanel();
    informationPanel.setBackground(Color.WHITE);
    setTitle = new JTextField();
    priceText = new JLabel();
    setPrice = new JTextField();
    statusText = new JLabel();
    setStatus = new JComboBox<>();
    isbnText = new JLabel();
    setIsbn = new JTextField();
    quantityText = new JLabel();
    setAvailableQuantity = new JTextField();
    authorText = new JLabel();
    setAuthorName = new JTextField();
    publisherText = new JLabel();
    setPublisherName = new JTextField();
    descriptionPanel = new JPanel();
    descriptionPanel.setBackground(Color.WHITE);
    descriptionText = new JLabel();
    descriptionContend = new JPanel();
    descriptionContend.setBackground(Color.WHITE);
    buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.WHITE);
    buttonBack = new JButton();
    buttonSave = new JButton();
    chooseLink = new JButton();
    scrollPane = new JScrollPane();
    setDescription = new JTextArea();

    setPreferredSize(new Dimension(900, 539));
    getContentPane().setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

    contendPanel.setPreferredSize(new Dimension(858, 280));
    contendPanel.setLayout(new BorderLayout());

    setImage.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
    setImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    setImage.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    setImage.setPreferredSize(new Dimension(200, 280));
    contendPanel.add(setImage, BorderLayout.WEST);

    informationPanel.setPreferredSize(new Dimension(655, 300));
    informationPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    chooseLink.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/categories.png"))
    );
    chooseLink.setActionCommand("+");
    chooseLink.setPreferredSize(new Dimension(40, 23));
    chooseLink.addActionListener(actionAddLinkImage);
    informationPanel.add(chooseLink);

    setTitle.setEditable(false);
    setTitle.setFont(new Font("Segoe UI", 1, 14));
    setTitle.setText(book.getTitle());
    setTitle.setPreferredSize(new Dimension(550, 26));
    informationPanel.add(setTitle);

    priceText.setFont(new Font("Segoe UI", 0, 18));
    priceText.setHorizontalAlignment(SwingConstants.RIGHT);
    priceText.setText("Price:");
    priceText.setPreferredSize(new Dimension(100, 25));
    informationPanel.add(priceText);

    setPrice.setFont(new Font("Segoe UI", 1, 18));
    setPrice.setForeground(new Color(255, 51, 51));
    setPrice.setText("" + book.getPrice());
    informationPanel.add(setPrice);

    statusText.setFont(new Font("Segoe UI", 0, 18));
    statusText.setHorizontalAlignment(SwingConstants.RIGHT);
    statusText.setText("Status:");
    statusText.setPreferredSize(new Dimension(290, 25));
    informationPanel.add(statusText);

    setStatus.setFont(new Font("Segoe UI", 1, 12));
    setStatus.setModel(
      new DefaultComboBoxModel<>(
        new String[] { "AVAILABLE", "UNAVAILABLE", "DELETED" }
      )
    );
    informationPanel.add(setStatus);

    isbnText.setFont(new Font("Segoe UI", 0, 18));
    isbnText.setHorizontalAlignment(SwingConstants.RIGHT);
    isbnText.setText("ISBN");
    isbnText.setPreferredSize(new Dimension(150, 25));
    informationPanel.add(isbnText);

    setIsbn.setEditable(false);
    setIsbn.setFont(new Font("Segoe UI", 1, 18));
    setIsbn.setText("" + book.getIsbn());
    setIsbn.setPreferredSize(new Dimension(290, 31));
    informationPanel.add(setIsbn);

    quantityText.setFont(new Font("Segoe UI", 0, 18));
    quantityText.setHorizontalAlignment(SwingConstants.RIGHT);
    quantityText.setText("Available Quantity");
    quantityText.setPreferredSize(new Dimension(230, 25));
    informationPanel.add(quantityText);

    setAvailableQuantity.setFont(new Font("Segoe UI", 1, 18));
    setAvailableQuantity.setText("" + book.getQuantity());
    setAvailableQuantity.setPreferredSize(new Dimension(290, 31));
    informationPanel.add(setAvailableQuantity);

    authorText.setFont(new Font("Segoe UI", 0, 18));
    authorText.setHorizontalAlignment(SwingConstants.RIGHT);
    authorText.setText("Author");
    authorText.setPreferredSize(new Dimension(230, 25));
    informationPanel.add(authorText);

    setAuthorName.setEditable(false);
    setAuthorName.setFont(new Font("Segoe UI", 1, 18));
    setAuthorName.setText(
      AuthorBUS.getInstance().getModelById(book.getAuthorId()).getName()
    );
    setAuthorName.setPreferredSize(new Dimension(290, 31));
    informationPanel.add(setAuthorName);

    publisherText.setFont(new Font("Segoe UI", 0, 18));
    publisherText.setHorizontalAlignment(SwingConstants.RIGHT);
    publisherText.setText("Publisher");
    publisherText.setPreferredSize(new Dimension(230, 25));
    informationPanel.add(publisherText);

    setPublisherName.setEditable(false);
    setPublisherName.setFont(new Font("Segoe UI", 1, 18));
    setPublisherName.setText(
      PublisherBUS.getInstance().getModelById(book.getPublisherId()).getName()
    );
    setPublisherName.setPreferredSize(new Dimension(290, 31));
    informationPanel.add(setPublisherName);

    contendPanel.add(informationPanel, BorderLayout.CENTER);

    getContentPane().add(contendPanel);

    descriptionPanel.setPreferredSize(new Dimension(863, 220));
    descriptionPanel.setLayout(new BorderLayout());

    descriptionText.setFont(new Font("Segoe UI", 1, 24));
    descriptionText.setText("Description:");
    descriptionPanel.add(descriptionText, BorderLayout.PAGE_START);

    descriptionContend.setLayout(new BorderLayout());

    buttonPanel.setPreferredSize(new Dimension(863, 50));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

    buttonBack.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/back.png"))
    );
    buttonBack.setPreferredSize(new Dimension(80, 30));
    buttonBack.addActionListener(actionBack);
    buttonPanel.add(buttonBack);

    buttonSave.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/save.png"))
    );
    buttonSave.setPreferredSize(new Dimension(80, 30));
    buttonSave.addActionListener(actionSave);
    buttonPanel.add(buttonSave);

    descriptionContend.add(buttonPanel, BorderLayout.PAGE_END);

    setDescription.setColumns(5);
    setDescription.setFont(new Font("Segoe UI", 3, 15));
    setDescription.setLineWrap(true);
    setDescription.setRows(5);
    setDescription.setText(book.getDescription());
    scrollPane.setViewportView(setDescription);

    scrollPane.getVerticalScrollBar().setUnitIncrement(16);

    descriptionContend.add(scrollPane, BorderLayout.CENTER);

    descriptionPanel.add(descriptionContend, BorderLayout.CENTER);

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

  public void setImage(String image) {
    try {
      Image imageBase = ImageUtils.decodeFromBase64(image);
      setImage.setIcon(new ImageIcon(imageBase));
    } catch (Exception ex) {
      setImage.setIcon(
        new ImageIcon("src/main/java/resources/images/product-placeholder.png")
      );
    }
  }

  public ActionListener actionSave = new ActionListener() {
    final String INVALID_PRICE_ERROR = "Price is not valid!";
    final String INVALID_QUANTITY_ERROR = "Quantity is not valid!";

    @Override
    public void actionPerformed(ActionEvent evt) {
      if (setDescription.getText().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Description is cannot be empty!");
        return;
      }

      try {
        int price = Integer.parseInt(setPrice.getText().trim());
        if (price <= 0) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, INVALID_PRICE_ERROR);
        return;
      }

      try {
        int quantity = Integer.parseInt(setAvailableQuantity.getText().trim());
        if (quantity <= 0) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, INVALID_QUANTITY_ERROR);
        return;
      }

      Object selectedStatusItem = setStatus.getSelectedItem();
      String statusString = selectedStatusItem.toString().toUpperCase();
      BookStatus newStatus = BookStatus.valueOf(statusString);
      int newPrice = Integer.valueOf(setPrice.getText());
      int newQuantity = Integer.valueOf(setAvailableQuantity.getText());
      String newDescription = setDescription.getText();
      BookModel newBook = new BookModel();
      newBook.setIsbn(bookModel.getIsbn());
      newBook.setTitle(bookModel.getTitle());
      newBook.setDescription(newDescription);
      if (stringImage != null) {
        newBook.setImage(stringImage);
      } else {
        newBook.setImage(newBook.getImage());
      }
      newBook.setPrice(newPrice);
      newBook.setQuantity(newQuantity);
      newBook.setStatus(newStatus);
      newBook.setAuthorId(bookModel.getAuthorId());
      newBook.setPublisherId(bookModel.getPublisherId());
      int confirm = JOptionPane.showConfirmDialog(
        null,
        "Do you want to continue?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION
      );
      if (confirm == JOptionPane.YES_OPTION) {
        BookBUS.getInstance().updateModel(newBook);
        BookBUS.getInstance().refreshData();
        JOptionPane.showMessageDialog(null, "Complete!");
        MainPanel.getInstance().showForm(BrowseProductPanel.getInstance());
      }
    }
  };

  public ActionListener actionBack = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonBack);
      frame.dispose();
    }
  };

  public ActionListener actionAddLinkImage = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      JFileChooser fileChooser = new JFileChooser();
      int returnValue = fileChooser.showOpenDialog(null);
      if (returnValue == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        String filePath = selectedFile.getAbsolutePath();

        // get image from file path
        base64 = null;
        try {
          base64 = ImageUtils.toBase64(ImageUtils.loadImage(filePath));
          Toolkit
            .getDefaultToolkit()
            .getSystemClipboard()
            .setContents(new StringSelection(base64), null);
          stringImage = base64;
          setImage.removeAll();
          setImage(base64);
        } catch (IOException ex) {
          ex.printStackTrace();
        }
      }
    }
  };
}
