package com.bookstore.gui.component.button;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ActionButton extends JButton {

  public ActionButton() {
    initComponents();
  }

  public ActionButton(String text) {
    super(text);
    initComponents();
  }

  private void initComponents() {
    setContentAreaFilled(false);
    setBorder(new EmptyBorder(3, 3, 3, 3));
  }

  protected void paintComponent(Graphics graphics) {
    Graphics2D graphics2d = (Graphics2D) graphics.create();
    graphics2d.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON
    );
    int width = getWidth();
    int height = getHeight();
    int size = Math.min(width, height);
    int x = (width - size) / 2;
    int y = (height - size) / 2;

    if (getModel().isArmed()) {
      graphics2d.setColor(getBackground().darker());
    } else if (getModel().isRollover()) {
      graphics2d.setColor(getBackground().brighter());
    } else {
      graphics2d.setColor(getBackground());
    }

    graphics2d.fill(new Ellipse2D.Double(x, y, size, size));
    graphics2d.dispose();
    super.paintComponent(graphics);
  }
}
