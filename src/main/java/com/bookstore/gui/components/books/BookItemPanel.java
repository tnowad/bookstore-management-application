package com.bookstore.gui.components.books;

import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.forms.books.BookDetailCustomer;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.image.ImageUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BookItemPanel extends JPanel {

  private JButton addToCartButton;
  private JButton detailButton;
  private JLabel titleLabel;
  private JPanel footerPanel;
  private Label priceLabel;
  private Label imageLabel;
  private JPanel jPanelFooter;

  private UserModel userModel = Authentication.getCurrentUser();

  private BookModel bookModel;

  public BookItemPanel(BookModel bookModel) {
    this.bookModel = bookModel;
    initComponents();
    updateData();
    handleEvent();
  }

  private void updateData() {
    CartItemsBUS.getInstance().refreshData();
  }

  private void handleEvent() {
    detailButton.addActionListener(e -> {
      MainPanel.getInstance().showFormStack(new BookDetailCustomer(bookModel));
    });

    addToCartButton.addActionListener(e -> {
      updateData();

      CartModel cartModel = CartBUS
        .getInstance()
        .getShoppingCartByUserId(userModel.getId());
      System.out.println(cartModel.getId());
      try {
        CartItemsBUS.getInstance().addBookToCart(cartModel, bookModel);
      } catch (Exception exception) {
        JOptionPane.showMessageDialog(
          null,
          exception.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
        return;
      }
      JOptionPane.showMessageDialog(
        null,
        "Add book to cart successfully!",
        "Success",
        JOptionPane.INFORMATION_MESSAGE
      );
      updateData();
    });
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    titleLabel = new JLabel(bookModel.getTitle());
    titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    add(titleLabel, BorderLayout.CENTER);

    imageLabel = new Label();
    Image image = null;
    try {
      image = ImageUtils.decodeFromBase64(bookModel.getImage());
    } catch (Exception ex) {
      image =
        new ImageIcon("src/main/java/resources/images/product-placeholder.png")
          .getImage();
    }
    image = image.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
    imageLabel.setIcon(new ImageIcon(image));

    imageLabel.setPreferredSize(new Dimension(200, 150));
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add(imageLabel, BorderLayout.NORTH);

    // footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    footerPanel = new JPanel(new BorderLayout());
    priceLabel = new Label();
    detailButton = new JButton();
    addToCartButton = new JButton();
    jPanelFooter = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(
      new Locale("vi", "VN")
    );

    priceLabel.setText(currencyFormatter.format(bookModel.getPrice()));
    priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
    priceLabel.setFont(new Font("Arial", Font.BOLD, 16));
    priceLabel.setLabelSize(100, 22);
    footerPanel.add(priceLabel, BorderLayout.NORTH);

    detailButton.setText("Book detail");
    jPanelFooter.add(detailButton);
    // footerPanel.add(detailButton, BorderLayout.WEST);
    addToCartButton.setText("Add to cart");
    jPanelFooter.add(addToCartButton);

    footerPanel.add(jPanelFooter, BorderLayout.CENTER);
    // footerPanel.add(addToCartButton, BorderLayout.CENTER);
    add(footerPanel, BorderLayout.SOUTH);
  }
}
