package com.bookstore.gui.component.button;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.border.Border;

import com.bookstore.gui.Theme.ThemeColor;
import com.bookstore.gui.Theme.ThemeFont;

public class Button extends JButton {
  public Button(String name) {
    initComponents(name);
    handleEvent();
  }

  private void handleEvent() {
    // addMouseListener((MouseListener) new MouseAdapter() {
    // private Timer timer;
    // private int alpha = 0;

    // public void mouseEntered(MouseEvent e) {
    // timer = new Timer(10, new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // alpha += 5;
    // if (alpha >= 200) {
    // // alpha = 200;
    // timer.stop();
    // }
    // setBackground(new Color(0, 119, alpha));

    // }
    // });
    // timer.start();
    // }

    // public void mouseExited(MouseEvent e) {
    // timer = new Timer(10, new ActionListener() {
    // public void actionPerformed(ActionEvent e) {
    // alpha -= 25;
    // if (alpha <= 190) {
    // // alpha = 190;
    // timer.stop();
    // }
    // setBackground(new Color(0, 119, alpha));
    // }
    // });
    // timer.start();
    // }
    // });
    hoverBackground(new ThemeColor().getButtonBackground(), new ThemeColor().getButtonHoverBackground(),
        new ThemeColor().getButtonForeground(),
        new ThemeColor().getButtonHoverForeground());
  }

  public void hoverBackground(Color originalBackgroundColor, Color laterBackgroundColor, Color originalForegroundColor,
      Color laterForegroundColor) {
    addMouseListener(new MouseAdapter() {
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
    });
  }

  private void initComponents(String name) {
    setText(name);
    setFont(new ThemeFont().getSmallFont());
    setButtonSize(100, 40);
    setBackground(new ThemeColor().getButtonBackground());
    setForeground(new ThemeColor().getButtonForeground());
    // setBorder(null);
    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
  }

  private void setButtonBorder() {
    setBorder(new Border() {
      @Override
      public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(x, y, width - 1, height - 1, 15, 15);
      }

      @Override
      public Insets getBorderInsets(Component c) {
        return new Insets(1, 1, 1, 1);
      }

      @Override
      public boolean isBorderOpaque() {
        return false;
      }
    });
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
