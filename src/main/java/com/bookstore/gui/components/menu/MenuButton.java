package com.bookstore.gui.components.menu;

import java.awt.Color;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class MenuButton extends JButton {

  public MenuButton(Icon icon, String text) {
    super(text);
    setIcon(icon);
    init();
    setBorder(new EmptyBorder(1, 20, 1, 1));
  }

  public MenuButton(String text) {
    super(text);
    init();
    setBorder(new EmptyBorder(1, 50, 1, 1));
  }

  public MenuButton(String text, boolean subMenu) {
    super(text);
    init();
  }

  private void init() {
    setContentAreaFilled(false);
    setForeground(new Color(30, 30, 30));
    setHorizontalAlignment(JButton.LEFT);
  }
}
