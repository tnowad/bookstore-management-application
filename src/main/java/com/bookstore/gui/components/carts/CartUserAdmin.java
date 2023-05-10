package com.bookstore.gui.components.carts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class CartUserAdmin extends JPanel {

  private JLabel icon;
  private JLabel text;
  private JLabel newUser;
  private JLabel quantity;

  public CartUserAdmin(
    String linkIcon,
    String title,
    int valueNewUser,
    int quantityUser,
    String color
  ) {
    initComponents(linkIcon, title, valueNewUser, quantityUser, color);
  }

  public void initComponents(
    String linkIcon,
    String title,
    int valueNewUser,
    int quantityUser,
    String color
  ) {
    icon = new JLabel();
    text = new JLabel();
    newUser = new JLabel();
    quantity = new JLabel();

    setBackground(Color.decode(color));
    setBorder(BorderFactory.createEtchedBorder());
    setLayout(new BorderLayout());

    icon.setIcon(new ImageIcon(getClass().getResource(linkIcon)));
    add(icon, BorderLayout.WEST);

    text.setFont(new Font("Segoe UI", 3, 14));
    text.setForeground(new Color(255, 51, 51));
    text.setHorizontalAlignment(SwingConstants.CENTER);
    text.setText(title);
    add(text, BorderLayout.NORTH);

    newUser.setFont(new Font("Segoe UI", 3, 12));
    newUser.setText("New User: " + valueNewUser);
    add(newUser, BorderLayout.SOUTH);

    quantity.setFont(new Font("Segoe UI", 0, 16));
    quantity.setHorizontalAlignment(SwingConstants.CENTER);
    quantity.setText("" + quantityUser);
    add(quantity, BorderLayout.CENTER);
  }
}
