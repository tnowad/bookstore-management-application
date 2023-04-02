package com.bookstore.gui.form.cart;

import java.sql.SQLException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CartForm extends JFrame {

  public CartForm() throws ClassNotFoundException, SQLException {
    JPanel container = new JPanel();
    container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    // Menu menu = new Menu();
    CartUI formHome = new CartUI();
    CartHeader cartHeader = new CartHeader();
    container.add(cartHeader);
    // container.add(menu);
    container.add(formHome);
    add(container);
    initComponent();
  }

  private void initComponent() {
    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Cart");
    setResizable(true);
    setSize(new java.awt.Dimension(800, 600));
    setLocationRelativeTo(null);
  }

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    new CartForm().setVisible(true);
  }
}
