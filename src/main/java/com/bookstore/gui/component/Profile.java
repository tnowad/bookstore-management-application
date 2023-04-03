package com.bookstore.gui.component;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.GroupLayout;

public class Profile extends JPanel {

  public Profile() {
    initComponents();
    setOpaque(false);
  }

  private void initComponents() {

    JLabel title = new JLabel();

    title.setFont(new Font("sansserif", 1, 24));
    title.setText("Bookstore");

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(title, GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE)
                .addGap(10, 10, 10)));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(title, GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                .addContainerGap()));
  }

}
