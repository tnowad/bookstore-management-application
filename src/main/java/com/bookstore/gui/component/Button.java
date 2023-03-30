package com.bookstore.gui.component;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;

import javax.swing.JButton;
import javax.swing.border.Border;

public class Button extends JButton {
  public Button(String name) {
    initComponents(name);
  }

  private void initComponents(String name) {
    setText(name);
    setFont(new Font("Arial", Font.BOLD, 14));
    setButtonSize(100, 50);
    setBorder(new Border() {
      @Override
      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(x, y, width - 1, height - 1, 15, 15);
      }

      @Override
      public Insets getBorderInsets(Component c) {
        return new Insets(1, 1, 1, 1);
      }

      @Override
      public boolean isBorderOpaque() {
        return false;
      }
    });
  }

  public void setButtonSize(int width, int height) {
    setPreferredSize(new Dimension(width, height));
  }

}
