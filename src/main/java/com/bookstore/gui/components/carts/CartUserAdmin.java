package com.bookstore.gui.components.carts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CartUserAdmin extends JPanel {

  private JLabel icon;
  private JLabel text;
  private JLabel newUser;
  private JLabel quantity;

  public CartUserAdmin(
    String linkIcon,
    String title,
    int valueNewUser,
    int quantityUser
  ) {
    initComponents(linkIcon, title, valueNewUser, quantityUser);
  }

  public void initComponents(
    String linkIcon,
    String title,
    int valueNewUser,
    int quantityUser
  ) {
    icon = new JLabel();
    icon = new JLabel();
    text = new JLabel();
    newUser = new JLabel();
    quantity = new JLabel();

    setBackground(new Color(255, 204, 255));
    setBorder(javax.swing.BorderFactory.createEtchedBorder());
    setLayout(new BorderLayout());

    icon.setIcon(new ImageIcon(linkIcon));
    add(icon, BorderLayout.WEST);

    text.setFont(new Font("Segoe UI", 3, 14));
    text.setForeground(new Color(255, 51, 51));
    text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    text.setText(title);
    add(text, BorderLayout.PAGE_START);

    newUser.setFont(new Font("Segoe UI", 3, 12));
    newUser.setText("New User: " + valueNewUser);
    add(newUser, BorderLayout.PAGE_END);

    quantity.setFont(new Font("Segoe UI", 0, 16));
    quantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    quantity.setText("" + quantityUser);
    add(quantity, BorderLayout.CENTER);
  }
}
