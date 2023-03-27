package com.bookstore.gui.swing.scrollbar;

import java.awt.Adjustable;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class ModernScrollBarUI extends BasicScrollBarUI {

  private final int THUMB_SIZE = 80;

  @Override
  protected Dimension getMaximumThumbSize() {
    if (scrollbar.getOrientation() == Adjustable.VERTICAL) {
      return new Dimension(0, THUMB_SIZE);
    } else {
      return new Dimension(THUMB_SIZE, 0);
    }
  }

  @Override
  protected Dimension getMinimumThumbSize() {
    if (scrollbar.getOrientation() == Adjustable.VERTICAL) {
      return new Dimension(0, THUMB_SIZE);
    } else {
      return new Dimension(THUMB_SIZE, 0);
    }
  }

  @Override
  protected JButton createIncreaseButton(int i) {
    return new ScrollBarButton();
  }

  @Override
  protected JButton createDecreaseButton(int i) {
    return new ScrollBarButton();
  }

  @Override
  protected void paintTrack(Graphics graphics, JComponent jc, Rectangle rectangle) {
    // TODO document why this method is empty
  }

  @Override
  protected void paintThumb(Graphics graphics, JComponent jcomponent, Rectangle rectangle) {
    Graphics2D g2 = (Graphics2D) graphics;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    int x = rectangle.x;
    int y = rectangle.y;
    int width = rectangle.width;
    int height = rectangle.height;
    if (scrollbar.getOrientation() == Adjustable.VERTICAL) {
      y += 8;
      height -= 16;
    } else {
      x += 8;
      width -= 16;
    }
    g2.setColor(scrollbar.getForeground());
    g2.fillRoundRect(x, y, width, height, 1, 1);
  }

  private class ScrollBarButton extends JButton {

    public ScrollBarButton() {
      setBorder(BorderFactory.createEmptyBorder());
    }

    @Override
    public void paint(Graphics graphics) {
      // TODO document why this method is empty
    }
  }
}
