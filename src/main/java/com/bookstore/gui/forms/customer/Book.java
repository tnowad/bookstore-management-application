package com.bookstore.gui.forms.customer;

import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.enums.CartStatus;
import com.bookstore.gui.components.dialogs.Dialog;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.image.ImageUtils;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Book extends javax.swing.JPanel {

  private UserModel userModel = Authentication.getCurrentUser();
  private List<CartModel> cartList = CartBUS.getInstance().getAllModels();
  private CartModel myCartModel;
  private List<CartItemsModel> cartItemList = CartItemsBUS
    .getInstance()
    .getAllModels();
  private CartItemsModel cartItemsModel;

  private BookModel bookModel;

  public Book(BookModel bookModel) {
    this.bookModel = bookModel;
    initComponents();
    updateData();
    setImage(bookModel.getImage());
    handleEvent();
  }

  private void updateData() {
    CartItemsBUS.getInstance().refreshData();
  }

  private void handleEvent() {
    bookDetailButton.addActionListener(e -> {
      new Dialog(new BookDetail(bookModel));
    });

    addToCartButton.addActionListener(e -> {
      updateData();
      boolean bookAlreadyInCart = false;
      for (CartModel cartModel : cartList) {
        if (
          cartModel.getStatus().equals(CartStatus.PENDING) &&
          cartModel.getUserId() == userModel.getId()
        ) {
          myCartModel = cartModel;
          break;
        }
      }
      for (CartItemsModel cartItemModel : cartItemList) {
        if (
          cartItemModel.getBookIsbn().equals(bookModel.getIsbn()) &&
          cartItemModel.getCartId() == myCartModel.getId()
        ) {
          JOptionPane.showMessageDialog(
            null,
            "This book is already in your cart!"
          );
          bookAlreadyInCart = true;
          break;
        }
      }
      if (!bookAlreadyInCart) {
        if (myCartModel == null) {
          myCartModel = new CartModel();
          myCartModel.setUserId(userModel.getId());
          myCartModel.setPromotionId(1);
          myCartModel.setStatus(CartStatus.PENDING);
          CartBUS.getInstance().addModel(myCartModel);
        }
        cartItemsModel = new CartItemsModel();
        cartItemsModel.setBookIsbn(bookModel.getIsbn());
        cartItemsModel.setCartId(myCartModel.getId());
        cartItemsModel.setDiscount(ABORT);
        cartItemsModel.setPrice(bookModel.getPrice());
        cartItemsModel.setQuantity(1);
        CartItemsBUS.getInstance().addModel(cartItemsModel);
      }
    });
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

  private void initComponents() {
    bookTitleLabel = new JLabel();
    bookImagePanel = new JPanel();
    groupButtonPanel = new JPanel();
    bookDetailButton = new JButton();
    addToCartButton = new JButton();
    bookPriceLabel = new Label("");
    setImage = new Label("");

    setLayout(new java.awt.BorderLayout());

    bookTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 14));
    bookTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    bookTitleLabel.setText(bookModel.getTitle());
    add(bookTitleLabel, java.awt.BorderLayout.PAGE_START);
    bookTitleLabel.getAccessibleContext().setAccessibleDescription("");

    bookImagePanel.setLayout(new FlowLayout());
    bookImagePanel.add(setImage);

    add(bookImagePanel, java.awt.BorderLayout.CENTER);

    groupButtonPanel.setLayout(
      new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 2, 0)
    );

    bookPriceLabel.setText("" + bookModel.getPrice() + " $");
    groupButtonPanel.add(bookPriceLabel);

    bookDetailButton.setText("Book detail");
    groupButtonPanel.add(bookDetailButton);

    addToCartButton.setText("Add to cart");
    groupButtonPanel.add(addToCartButton);

    add(groupButtonPanel, java.awt.BorderLayout.SOUTH);
  }

  private JButton addToCartButton;
  private JButton bookDetailButton;
  private JPanel bookImagePanel;
  private JLabel bookTitleLabel;
  private JPanel groupButtonPanel;
  private Label bookPriceLabel;
  private Label setImage;
}
