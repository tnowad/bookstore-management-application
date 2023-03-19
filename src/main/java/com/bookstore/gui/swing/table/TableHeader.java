package com.bookstore.gui.swing.table;

import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class TableHeader extends JLabel {

  public TableHeader(String text) {
    super(text);
    setOpaque(true);
    setBackground(Color.WHITE);
    setFont(new Font("sansserif", 1, 12));
    setForeground(new Color(102, 102, 102));
    setBorder(new EmptyBorder(10, 5, 10, 5));
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    super.paintComponent(graphics);
    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2d.setColor(new Color(230, 230, 230));
    graphics2d.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
  }
}
