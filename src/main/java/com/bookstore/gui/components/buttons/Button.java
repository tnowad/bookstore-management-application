package com.bookstore.gui.components.buttons;

import com.bookstore.gui.theme.ThemeColor;
import com.bookstore.gui.theme.ThemeFont;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.UIManager;

public class Button extends JButton {

  public Button() {}

  public Button(String name) {
    initComponents(name);
    handleEvent();
  }

  private void handleEvent() {
    hoverBackground(
      new ThemeColor().getButtonBackground(),
      new ThemeColor().getButtonHoverBackground(),
      new ThemeColor().getButtonForeground(),
      new ThemeColor().getButtonHoverForeground()
    );
  }

  public void hoverBackground(
    Color originalBackgroundColor,
    Color laterBackgroundColor,
    Color originalForegroundColor,
    Color laterForegroundColor
  ) {
    addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent evt) {
          setBackground(laterBackgroundColor);
          setForeground(laterForegroundColor);
        }

        @Override
        public void mouseExited(MouseEvent evt) {
          setBackground(UIManager.getColor("control"));
          setBackground(originalBackgroundColor);
          setForeground(originalForegroundColor);
        }
      }
    );
  }

  private void initComponents(String name) {
    setText(name);
    setFont(new ThemeFont().getSmallFont());
    setButtonSize(100, 30);
    setBackground(new ThemeColor().getButtonBackground());
    setForeground(new ThemeColor().getButtonForeground());
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  }

  public void setButtonSize(int width, int height) {
    setPreferredSize(new Dimension(width, height));
  }

  public void setButtonMaxSize(int maxWidth, int maxHeight) {
    setMaximumSize(new Dimension(maxWidth, maxHeight));
  }

  public void setButtonMinSize(int minWidth, int minHeight) {
    setMinimumSize(new Dimension(minWidth, minHeight));
  }
}
