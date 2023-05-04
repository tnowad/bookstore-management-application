package com.bookstore.gui.forms.customer;

import com.bookstore.bus.CartBUS;
import com.bookstore.enums.CartStatus;
import com.bookstore.gui.components.dialogs.Dialog;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import java.awt.Frame;
import java.util.List;

public class Book extends javax.swing.JPanel {

  private UserModel userModel = Authentication.getCurrentUser();
  private List<CartModel> cartList = CartBUS.getInstance().getAllModels();
  private CartModel myCartModel;

  private BookModel bookModel;

  public Book(BookModel bookModel) {
    this.bookModel = bookModel;
    initComponents();
    handleEvent();
  }

  private void handleEvent() {
    bookDetailButton.addActionListener(e -> {
      new Dialog(new BookDetail(bookModel));
    });
    addToCartButton.addActionListener(e -> {
      for (CartModel cartModel : cartList) {
        if (cartModel.getStatus().equals(CartStatus.PENDING)) {
          myCartModel = new CartModel();
          myCartModel = cartModel;
          break;
        }
      }
      if (myCartModel == null) {
        myCartModel = new CartModel();
      }
      
    });
  }

  private void initComponents() {
    bookTitleLabel = new javax.swing.JLabel();
    bookImagePanel = new javax.swing.JPanel();
    groupButtonPanel = new javax.swing.JPanel();
    bookDetailButton = new javax.swing.JButton();
    addToCartButton = new javax.swing.JButton();

    setLayout(new java.awt.BorderLayout());

    bookTitleLabel.setFont(new java.awt.Font("Segoe UI", 0, 14));
    bookTitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    bookTitleLabel.setText(bookModel.getTitle());
    add(bookTitleLabel, java.awt.BorderLayout.PAGE_START);
    bookTitleLabel.getAccessibleContext().setAccessibleDescription("");

    javax.swing.GroupLayout bookImagePanelLayout = new javax.swing.GroupLayout(
      bookImagePanel
    );
    bookImagePanel.setLayout(bookImagePanelLayout);
    bookImagePanelLayout.setHorizontalGroup(
      bookImagePanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 220, Short.MAX_VALUE)
    );
    bookImagePanelLayout.setVerticalGroup(
      bookImagePanelLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 207, Short.MAX_VALUE)
    );

    add(bookImagePanel, java.awt.BorderLayout.CENTER);

    groupButtonPanel.setLayout(
      new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 0)
    );

    bookDetailButton.setText("Book detail");
    groupButtonPanel.add(bookDetailButton);

    addToCartButton.setText("Add to cart");
    groupButtonPanel.add(addToCartButton);

    add(groupButtonPanel, java.awt.BorderLayout.SOUTH);
  }

  private javax.swing.JButton addToCartButton;
  private javax.swing.JButton bookDetailButton;
  private javax.swing.JPanel bookImagePanel;
  private javax.swing.JLabel bookTitleLabel;
  private javax.swing.JPanel groupButtonPanel;
}