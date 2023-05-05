package com.bookstore.gui.forms.checkout;

import com.bookstore.bus.CartBUS;
import com.bookstore.models.CartModel;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CompletedOrderForm extends JPanel {

  private CartModel cartModel;

  public CompletedOrderForm(int cartId) {
    this.cartModel = CartBUS.getInstance().getModelById(cartId);
    initComponents();
  }

  private void initComponents() {
    JLabel label = new JLabel(
      "Completed order " + cartModel.getId() + " successfully!"
    );
    add(label);
  }
}
