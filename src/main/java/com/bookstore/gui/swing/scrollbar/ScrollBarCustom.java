package com.bookstore.gui.swing.scrollbar;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

public class ScrollBarCustom extends JScrollBar {

  public ScrollBarCustom() {
    setUI(new ModernScrollBarUI());
    setPreferredSize(new Dimension(5, 5));
    setForeground(new Color(94, 139, 231));
    setUnitIncrement(20);
    setOpaque(false);
  }

  public static void main(String[] args) {
    new JPanel() {
      {
        setPreferredSize(new Dimension(200, 200));
        setLayout(null);
        add(new ScrollBarCustom());
      }
    }.setVisible(true);

  }
}
