package com.bookstore.gui.components.inputs;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class SearchTextField extends JTextField {

  public SearchTextField() {
    setBorder(new EmptyBorder(5, 5, 5, 5));
    setSelectionColor(new Color(220, 204, 182));
  }

  private final String hint = "Search here ...";

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    if (getText().length() == 0) {
      int height = getHeight();
      ((Graphics2D) graphics).setRenderingHint(
          RenderingHints.KEY_TEXT_ANTIALIASING,
          RenderingHints.VALUE_TEXT_ANTIALIAS_ON
        );
      Insets insets = getInsets();
      FontMetrics fontMetrics = graphics.getFontMetrics();
      int colorBackground = getBackground().getRGB();
      int colorForeground = getForeground().getRGB();
      int m = 0xfefefefe;
      int color = ((colorBackground & m) >>> 1) + ((colorForeground & m) >>> 1);
      graphics.setColor(new Color(color, true));
      graphics.drawString(
        hint,
        insets.left,
        height / 2 + fontMetrics.getAscent() / 2 - 2
      );
    }
  }
}
