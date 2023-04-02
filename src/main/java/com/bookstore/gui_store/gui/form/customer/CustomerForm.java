package com.bookstore.gui.form.customer;

import java.awt.Graphics;

import javax.swing.JPanel;

public class CustomerForm extends JPanel {

  public CustomerForm() {
    initComponents();
  }

  private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);

    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE));
  }

  @Override
  public void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    graphics.setColor(getBackground().darker());
    graphics.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 20, 20);

  }
}
