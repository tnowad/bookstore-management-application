package com.bookstore.gui.factory;

import javax.swing.Icon;

public class IconFactory {

  public static Icon createIcon(String id) {
    switch (id) {
      case "product.add":
        return null;
    }
    return new Icon() {
      @Override
      public int getIconWidth() {
        return 21;
      }

      @Override
      public int getIconHeight() {
        return 21;
      }

      @Override
      public void paintIcon(java.awt.Component c, java.awt.Graphics g, int x, int y) {
        g.setColor(java.awt.Color.BLACK);
        g.drawLine(x, y, x + getIconWidth(), y + getIconHeight());
        g.drawLine(x, y + getIconHeight(), x + getIconWidth(), y);
      }
    };
  }

}
