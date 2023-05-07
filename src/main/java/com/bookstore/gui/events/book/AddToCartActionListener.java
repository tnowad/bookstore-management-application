package com.bookstore.gui.events.book;

import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class AddToCartActionListener implements ActionListener {

  private final UserModel userModel;
  private final BookModel bookModel;

  public AddToCartActionListener(BookModel bookModel) {
    this.userModel = Authentication.getCurrentUser();
    this.bookModel = bookModel;
    if (userModel == null) {
      JOptionPane.showMessageDialog(
        null,
        "You must login to add book to cart!",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
      return;
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    CartModel cartModel = CartBUS
      .getInstance()
      .getShoppingCartByUserId(userModel.getId());
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
  }
}
