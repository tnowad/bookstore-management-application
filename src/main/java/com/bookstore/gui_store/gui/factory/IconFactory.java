package com.bookstore.gui.factory;

import javax.swing.Icon;

public class IconFactory {
  private IconFactory() {
    throw new IllegalStateException("Utility class");
  }

  public static Icon createIcon(String id) {
    return switch (id) {
      case "product.add", "product.edit" -> null;
      default -> new Icon() {
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
          g.setColor(new java.awt.Color(255, 255, 255));
          g.fillRect(x, y, getIconWidth(), getIconHeight());
          g.setColor(new java.awt.Color(0, 0, 0));
          g.drawRect(x, y, getIconWidth(), getIconHeight());
        }
      };
    };
  }
}