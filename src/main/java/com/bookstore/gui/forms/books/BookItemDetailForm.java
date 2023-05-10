package com.bookstore.gui.forms.books;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.events.book.AddToCartActionListener;
import com.bookstore.models.AuthorModel;
import com.bookstore.models.BookModel;
import com.bookstore.util.image.ImageUtils;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class BookItemDetailForm extends JPanel {

  private JButton backPreviousButton;
  private JButton addToCartButton;
  private Label authorLabel;
  private JTextField bookAuthorTextField;
  private Label bookAvailableQuantity;
  private JTextField bookIsbnTextField;
  private JTextField bookPriceTextField;
  private JTextField bookQuantityTextField;
  private JTextField bookStatusTextField;
  private JTextField bookTitleTextField;
  private Label descriptionLabel;
  private JTextArea descriptionTextArea;
  private Label isbnLabel;
  private JScrollPane jScrollPane1;
  private Label priceLabel;
  private Label statusLabel;

  private BookModel bookModel;
  private AuthorModel authorModel;

  public BookItemDetailForm(BookModel bookModel) {
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
    setBackground(Color.WHITE);
    backPreviousButton = new JButton("Back Previous");
    backPreviousButton.addActionListener(backPreviousButtonActionListener);

    JPanel bookTitleHeaderPanel = new JPanel(new BorderLayout());
    bookTitleHeaderPanel.setBackground(Color.WHITE);
    JPanel bookDescriptionPanel = new JPanel();
    bookDescriptionPanel.setBackground(Color.WHITE);
    JPanel bookDetailsPanel = new JPanel();
    bookDetailsPanel.setBackground(Color.WHITE);
    JPanel bookImagePanel = new JPanel();
    bookImagePanel.setBackground(Color.WHITE);
    JPanel bookInformationPanel = new JPanel();
    bookInformationPanel.setBackground(Color.WHITE);
    JPanel priceAndStatusField = new JPanel();
    priceAndStatusField.setBackground(Color.WHITE);
    JPanel isbnAndAuthorAndQuantityPanel = new JPanel();
    isbnAndAuthorAndQuantityPanel.setBackground(Color.WHITE);
    JPanel actionPanel = new JPanel();
    actionPanel.setBackground(Color.WHITE);
    bookTitleTextField = new JTextField();
    descriptionTextArea = new JTextArea();
    descriptionLabel = new Label("Description");
    jScrollPane1 = new JScrollPane();
    priceLabel = new Label("Price");
    bookPriceTextField = new JTextField();
    statusLabel = new Label("Status");
    bookStatusTextField = new JTextField();
    isbnLabel = new Label("Isbn");
    bookIsbnTextField = new JTextField();
    authorLabel = new Label("Author");
    bookAuthorTextField = new JTextField();
    bookAvailableQuantity = new Label("Quantity");
    bookQuantityTextField = new JTextField();

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
    bookImagePanel.setLayout(new GridBagLayout());

    JLabel imageLabel = new JLabel();
    Image image = null;
    try {
      image = ImageUtils.decodeFromBase64(bookModel.getImage());
    } catch (Exception ex) {
      image =
        new ImageIcon("src/main/java/resources/images/product-placeholder.png")
          .getImage();
    }
    image = image.getScaledInstance(300, 450, Image.SCALE_SMOOTH);
    imageLabel.setIcon(new ImageIcon(image));
    imageLabel.setPreferredSize(new Dimension(300, 450));

    bookImagePanel.add(
      imageLabel,
      new GridBagConstraints() {
        {
          gridx = 0;
          gridy = 0;
          weightx = 1;
          weighty = 1;
          anchor = GridBagConstraints.CENTER;
        }
      }
    );
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

    addToCartButton = new JButton();
    actionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
    addToCartButton.setFont(new Font("Arial", 0, 14));
    addToCartButton.setText("Add to cart");
    addToCartButton.addActionListener(e -> {
      new AddToCartActionListener(bookModel).actionPerformed(e);
    });
    actionPanel.add(addToCartButton);

    bookInformationPanel.add(actionPanel, BorderLayout.PAGE_END);

    bookDetailsPanel.add(bookInformationPanel, BorderLayout.CENTER);

    add(bookDetailsPanel, BorderLayout.CENTER);
  }

  private ActionListener backPreviousButtonActionListener = e -> {
    MainPanel.getInstance().backToPreviousForm();
  };
}
