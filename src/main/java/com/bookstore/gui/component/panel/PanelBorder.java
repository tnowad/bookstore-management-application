package com.bookstore.gui.component.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class PanelBorder extends javax.swing.JPanel {

  public PanelBorder() {
    initComponents();
    setOpaque(false);
  }

  private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE));
  }// </editor-fold>//GEN-END:initComponents

  @Override
  protected void paintComponent(Graphics graphics) {
    Graphics2D g2 = (Graphics2D) graphics;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(getBackground());
    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    super.paintComponent(graphics);
  }
}
