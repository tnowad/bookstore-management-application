package com.bookstore.gui.components.books;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.bus.PublisherBUS;
import com.bookstore.enums.BookStatus;
import com.bookstore.models.AuthorModel;
import com.bookstore.models.BookModel;
import com.bookstore.models.PublisherModel;
import com.bookstore.util.image.ImageUtils;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class AddProductFrame extends JFrame {

  private String stringImage;
  private JLabel authorText;
  private JButton buttonBack;
  private JPanel buttonPanel;
  private JButton buttonSave;
  private JButton chooseLink;
  private JLabel imageText;
  private JLabel isbnText;
  private JLabel priceText;
  private JLabel publisherText;
  private JLabel quantityText;
  private JScrollPane scrollPane;
  private JTextField setAuthorId;
  private JTextField setAuthorName;
  private JTextArea setDescription;
  private JTextField setImageLink;
  private JTextField setIsbn;
  private JTextField setPrice;
  private JTextField setPublisherId;
  private JTextField setPublisherName;
  private JTextField setQuantity;
  private JComboBox<String> setStatus;
  private JTextField setTitle;
  private JLabel statusText;
  private JLabel titleFrame;
  private JLabel titleText;
  private String base64;

  public AddProductFrame() {
    initComponents();
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents() {
    titleFrame = new JLabel();
    isbnText = new JLabel();
    setIsbn = new JTextField();
    titleText = new JLabel();
    setTitle = new JTextField();
    priceText = new JLabel();
    setPrice = new JTextField();
    quantityText = new JLabel();
    setQuantity = new JTextField();
    publisherText = new JLabel();
    setPublisherId = new JTextField();
    setPublisherName = new JTextField();
    authorText = new JLabel();
    setAuthorId = new JTextField();
    setAuthorName = new JTextField();
    imageText = new JLabel();
    setImageLink = new JTextField();
    chooseLink = new JButton();
    statusText = new JLabel();
    setStatus = new JComboBox<>();
    scrollPane = new JScrollPane();
    setDescription = new JTextArea();
    buttonPanel = new JPanel();
    buttonBack = new JButton();
    buttonSave = new JButton();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setAutoRequestFocus(false);
    setMaximumSize(new Dimension(566, 400));
    setMinimumSize(new Dimension(566, 400));
    setPreferredSize(new Dimension(566, 450));
    getContentPane().setLayout(new FlowLayout());

    titleFrame.setFont(new Font("Segoe UI", 1, 18));
    titleFrame.setHorizontalAlignment(SwingConstants.CENTER);
    titleFrame.setText("Add New Book ");
    titleFrame.setPreferredSize(new Dimension(600, 25));
    getContentPane().add(titleFrame);

    isbnText.setFont(new Font("Segoe UI", 1, 13));
    isbnText.setText("Isbn");
    isbnText.setPreferredSize(new Dimension(200, 16));
    getContentPane().add(isbnText);

    setIsbn.setPreferredSize(new Dimension(200, 22));
    getContentPane().add(setIsbn);

    titleText.setFont(new Font("Segoe UI", 1, 13));
    titleText.setText("Book Title");
    titleText.setPreferredSize(new Dimension(200, 16));
    getContentPane().add(titleText);

    setTitle.setPreferredSize(new Dimension(200, 22));
    getContentPane().add(setTitle);

    priceText.setFont(new Font("Segoe UI", 1, 13));
    priceText.setText("Price");
    priceText.setPreferredSize(new Dimension(200, 16));
    getContentPane().add(priceText);

    setPrice.setPreferredSize(new Dimension(200, 22));
    getContentPane().add(setPrice);

    quantityText.setFont(new Font("Segoe UI", 1, 13));
    quantityText.setText("Quantity");
    quantityText.setPreferredSize(new Dimension(200, 16));
    getContentPane().add(quantityText);

    setQuantity.setPreferredSize(new Dimension(200, 22));
    getContentPane().add(setQuantity);

    publisherText.setFont(new Font("Segoe UI", 1, 13));
    publisherText.setText("Publisher id");
    publisherText.setPreferredSize(new Dimension(160, 16));
    getContentPane().add(publisherText);

    setPublisherId.setPreferredSize(new Dimension(60, 22));
    setPublisherId.addFocusListener(actionCheckPublisherId);
    getContentPane().add(setPublisherId);

    setPublisherName.setPreferredSize(new Dimension(260, 22));
    setPublisherName.addFocusListener(actionCheckPublisherName);
    getContentPane().add(setPublisherName);

    authorText.setFont(new Font("Segoe UI", 1, 13));
    authorText.setText("Author id");
    authorText.setPreferredSize(new Dimension(160, 16));
    getContentPane().add(authorText);

    setAuthorId.setPreferredSize(new Dimension(60, 22));
    setAuthorId.addFocusListener(actionCheckAuthorId);
    getContentPane().add(setAuthorId);

    setAuthorName.setPreferredSize(new Dimension(260, 22));
    setAuthorName.addFocusListener(actionCheckAuthorName);
    getContentPane().add(setAuthorName);

    imageText.setFont(new Font("Segoe UI", 1, 13));
    imageText.setText("Image Link");
    imageText.setPreferredSize(new Dimension(140, 16));
    getContentPane().add(imageText);

    setImageLink.setPreferredSize(new Dimension(220, 22));
    getContentPane().add(setImageLink);

    chooseLink.setIcon(
        new ImageIcon(getClass().getResource("/resources/icons/categories.png")));
    chooseLink.setActionCommand("+");
    chooseLink.setPreferredSize(new Dimension(50, 23));
    chooseLink.addActionListener(actionAddLinkImage);
    getContentPane().add(chooseLink);

    statusText.setFont(new Font("Segoe UI", 1, 13));
    statusText.setHorizontalAlignment(SwingConstants.CENTER);
    statusText.setText("Status");
    statusText.setPreferredSize(new Dimension(180, 16));
    getContentPane().add(statusText);

    setStatus.setModel(
        new DefaultComboBoxModel<>(
            new String[] { "available", "unavailable", "deleted" }));
    getContentPane().add(setStatus);

    scrollPane.setPreferredSize(new Dimension(550, 100));

    setDescription.setColumns(20);
    setDescription.setFont(new Font("Segoe UI", 2, 14));
    setDescription.setRows(5);
    setDescription.setText("description ?");
    scrollPane.setViewportView(setDescription);

    getContentPane().add(scrollPane);

    buttonPanel.setPreferredSize(new Dimension(300, 50));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 20));

    buttonBack.setIcon(
        new ImageIcon(getClass().getResource("/resources/icons/back.png")));
    buttonBack.setPreferredSize(new Dimension(72, 23));
    buttonBack.addActionListener(actionBack);
    buttonPanel.add(buttonBack);

    buttonSave.setIcon(
        new ImageIcon(getClass().getResource("/resources/icons/save.png")));
    buttonSave.setPreferredSize(new Dimension(72, 23));
    buttonSave.addActionListener(actionSave);
    buttonPanel.add(buttonSave);

    getContentPane().add(buttonPanel);

    pack();
  }

  public Boolean checkPublisherName() {
    if (PublisherBUS
        .getInstance()
        .getModelByPublisherName(setPublisherName.getText().trim()) != null) {
      setPublisherId.setText(
          String.valueOf(
              PublisherBUS
                  .getInstance()
                  .getModelByPublisherName(setPublisherName.getText().trim())
                  .getId()));
      return true;
    } else {
      setPublisherId.setText(null);
      return false;
    }
  }

  public Boolean checkAuthorName() {
    if (AuthorBUS
        .getInstance()
        .getModelByName(setAuthorName.getText().trim()) != null) {
      setAuthorId.setText(
          String.valueOf(
              AuthorBUS
                  .getInstance()
                  .getModelByName(setAuthorName.getText().trim())
                  .getId()));
      return true;
    } else {
      setAuthorId.setText(null);
      return false;
    }
  }

  public FocusListener actionCheckPublisherId = new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
      if (PublisherBUS
          .getInstance()
          .getModelById(Integer.parseInt(setPublisherId.getText().trim())) != null) {
        setPublisherName.setText(
            PublisherBUS
                .getInstance()
                .getModelById(Integer.parseInt(setPublisherId.getText().trim()))
                .getName());
      } else {
        setPublisherName.setText(null);
      }
    }
  };

  public FocusListener actionCheckPublisherName = new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
      checkPublisherName();
    }
  };

  public FocusListener actionCheckAuthorId = new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
      if (AuthorBUS
          .getInstance()
          .getModelById(Integer.parseInt(setAuthorId.getText().trim())) != null) {
        setAuthorName.setText(
            AuthorBUS
                .getInstance()
                .getModelById(Integer.parseInt(setAuthorId.getText().trim()))
                .getName());
      } else {
        setAuthorName.setText(null);
      }
    }
  };

  public FocusListener actionCheckAuthorName = new FocusListener() {
    @Override
    public void focusGained(FocusEvent e) {
    }

    @Override
    public void focusLost(FocusEvent e) {
      checkAuthorName();
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
        setImageLink.setText(filePath);

        // get image from file path
        base64 = null;
        try {
          base64 = ImageUtils.toBase64(ImageUtils.loadImage(filePath));
          Toolkit
              .getDefaultToolkit()
              .getSystemClipboard()
              .setContents(new StringSelection(base64), null);
          stringImage = base64;
        } catch (IOException ex) {
          ex.printStackTrace();
        }
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

  public ActionListener actionSave = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent evt) {
      final String EMPTY_FIELD_ERROR = " cannot be empty!";
      final String INVALID_PRICE_ERROR = "Price is not valid!";
      final String INVALID_QUANTITY_ERROR = "Quantity is not valid!";
      final String DUPLICATE_ISBN_ERROR = "Isbn already exists!";
      final String DUPLICATE_TITLE_ERROR = "Title already exists!";

      if (setIsbn.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Isbn" + EMPTY_FIELD_ERROR);
        return;
      }

      if (setTitle.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Title" + EMPTY_FIELD_ERROR);
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
        int quantity = Integer.parseInt(setQuantity.getText().trim());
        if (quantity <= 0) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, INVALID_QUANTITY_ERROR);
        return;
      }

      if (setPublisherName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(
            null,
            "Publisher Name" + EMPTY_FIELD_ERROR);
        return;
      }

      if (setAuthorName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Author Name" + EMPTY_FIELD_ERROR);
        return;
      }

      if (BookBUS
          .getInstance()
          .checkForDuplicate(
              Arrays.asList(setIsbn.getText()),
              new String[] { "isbn" })) {
        JOptionPane.showMessageDialog(null, DUPLICATE_ISBN_ERROR);
        return;
      }
      if (BookBUS
          .getInstance()
          .checkForDuplicate(
              Arrays.asList(setTitle.getText()),
              new String[] { "title" })) {
        JOptionPane.showMessageDialog(null, DUPLICATE_TITLE_ERROR);
        return;
      }

      if (!checkPublisherName()) {
        int choice = JOptionPane.showConfirmDialog(
            null,
            "Do you want to add new publisher?");
        if (choice == JOptionPane.YES_OPTION) {
          PublisherModel publisherModel = new PublisherModel(
              0,
              setPublisherName.getText().trim(),
              "description ?");
          PublisherBUS.getInstance().addModel(publisherModel);
          PublisherBUS.getInstance().refreshData();
        } else {
          return;
        }
      }
      if (!checkAuthorName()) {
        int choice = JOptionPane.showConfirmDialog(
            null,
            "Do you want to add new author?");
        if (choice == JOptionPane.YES_OPTION) {
          AuthorModel authorModel = new AuthorModel(
              0,
              setAuthorName.getText().trim(),
              "description ?");
          AuthorBUS.getInstance().addModel(authorModel);
          AuthorBUS.getInstance().refreshData();
        } else {
          return;
        }
      }

      BookStatus newStatus = BookStatus.valueOf(
          setStatus.getSelectedItem().toString().toUpperCase());
      BookModel book = new BookModel();
      book.setIsbn(setIsbn.getText());
      book.setTitle(setTitle.getText());
      book.setDescription(setDescription.getText());
      book.setImage(stringImage);
      book.setPrice(Integer.parseInt(setPrice.getText().trim()));
      book.setQuantity(Integer.parseInt(setQuantity.getText().trim()));
      book.setStatus(newStatus);
      book.setPublisherId(
          PublisherBUS
              .getInstance()
              .getModelByPublisherName(setPublisherName.getText().trim())
              .getId());
      book.setAuthorId(
          AuthorBUS
              .getInstance()
              .getModelByName(setAuthorName.getText().trim())
              .getId());
      BookBUS.getInstance().addModel(book);
      JOptionPane.showMessageDialog(null, "Completed!");
    }
  };
}
