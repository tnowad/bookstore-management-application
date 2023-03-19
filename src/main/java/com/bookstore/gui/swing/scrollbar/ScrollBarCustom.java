package com.bookstore.gui.swing.scrollbar;

import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.Dimension;

public class ScrollBarCustom extends JScrollBar {

  public ScrollBarCustom() {
    setUI(new ModernScrollBarUI());
    setPreferredSize(new Dimension(5, 5));
    setForeground(new Color(94, 139, 231));
    setUnitIncrement(20);
    setOpaque(false);
  }
}
