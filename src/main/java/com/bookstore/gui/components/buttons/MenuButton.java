package com.bookstore.gui.components.buttons;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class MenuButton extends JButton {

  public MenuButton(String text) {
    super(text);
    init();
    setBorder(new EmptyBorder(1, 15, 1, 1));
  }

  private void init() {
    setContentAreaFilled(false);
    setForeground(new Color(30, 30, 30));
    setHorizontalAlignment(JButton.LEFT);
  }
}
