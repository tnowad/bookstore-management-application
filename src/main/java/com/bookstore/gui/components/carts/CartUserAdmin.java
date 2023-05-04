package com.bookstore.gui.components.carts;

import javax.swing.JPanel;

public class CartUserAdmin extends JPanel {

    private javax.swing.JLabel icon;
    private javax.swing.JLabel text;
    private javax.swing.JLabel newUser;
    private javax.swing.JLabel quantity;


  public CartUserAdmin(String linkIcon,String title,int valueNewUser,int quantityUser) {
    initComponents(linkIcon, title, valueNewUser, quantityUser);
  }

  public void initComponents(String linkIcon,String title,int valueNewUser,int quantityUser) {
    icon = new javax.swing.JLabel();
    icon = new javax.swing.JLabel();
    text = new javax.swing.JLabel();
    newUser = new javax.swing.JLabel();
    quantity = new javax.swing.JLabel();

    setBackground(new java.awt.Color(255, 204, 255));
    setBorder(javax.swing.BorderFactory.createEtchedBorder());
    setLayout(new java.awt.BorderLayout());

    icon.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource(linkIcon)
      )
    );
    add(icon, java.awt.BorderLayout.WEST);

    text.setFont( new java.awt.Font("Segoe UI", 3, 14));
    text.setForeground(new java.awt.Color(255, 51, 51));
    text.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    text.setText(title);
    add(text, java.awt.BorderLayout.PAGE_START);

    newUser.setFont(new java.awt.Font("Segoe UI", 3, 12));
    newUser.setText("New User: "+valueNewUser);
    add(newUser, java.awt.BorderLayout.PAGE_END);

    quantity.setFont(new java.awt.Font("Segoe UI", 0, 16));
    quantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    quantity.setText(""+quantityUser);
    add(quantity, java.awt.BorderLayout.CENTER);
  }

}
