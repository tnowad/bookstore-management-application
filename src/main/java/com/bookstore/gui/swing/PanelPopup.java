package com.bookstore.gui.swing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

public class PanelPopup extends JPanel {

  public PanelPopup() {
    setOpaque(false);
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2d.setColor(new Color(50, 50, 50));
    graphics2d.fillRect(8, 0, getSize().width - 8, getSize().height);
    int x[] = { 0, 10, 10 };
    int y[] = { 20, 13, 27 };
    graphics2d.fillPolygon(x, y, x.length);
    super.paintComponent(graphics);
  }
}
