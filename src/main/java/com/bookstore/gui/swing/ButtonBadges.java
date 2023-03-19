package com.bookstore.gui.swing;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class ButtonBadges extends JButton {

  public int getBadges() {
    return badges;
  }

  public void setBadges(int badges) {
    this.badges = badges;
  }

  public Color getEffectColor() {
    return effectColor;
  }

  public void setEffectColor(Color effectColor) {
    this.effectColor = effectColor;
  }

  private Animator animator;
  private int targetSize;
  private float animatorSize;
  private Point pressedPoint;
  private float alpha;
  private Color effectColor = new Color(173, 173, 173);
  private int badges;

  public ButtonBadges() {
    setContentAreaFilled(false);
    setBorder(new EmptyBorder(5, 5, 5, 5));
    setBackground(Color.WHITE);
    setCursor(new Cursor(Cursor.HAND_CURSOR));
    addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent me) {
        targetSize = Math.max(getWidth(), getHeight()) * 2;
        animatorSize = 0;
        pressedPoint = me.getPoint();
        alpha = 0.5f;
        if (animator.isRunning()) {
          animator.stop();
        }
        animator.start();
      }
    });
    TimingTarget target = new TimingTargetAdapter() {
      @Override
      public void timingEvent(float fraction) {
        if (fraction > 0.5f) {
          alpha = 1 - fraction;
        }
        animatorSize = fraction * targetSize;
        repaint();
      }
    };
    animator = new Animator(400, target);
    animator.setResolution(0);
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    int width = getWidth();
    int height = getHeight();
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    Graphics2D g2 = img.createGraphics();
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    g2.setColor(getBackground());
    g2.fillRoundRect(0, 0, width, height, height, height);
    if (pressedPoint != null) {
      g2.setColor(effectColor);
      g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
      g2.fillOval((int) (pressedPoint.x - animatorSize / 2), (int) (pressedPoint.y - animatorSize / 2),
          (int) animatorSize, (int) animatorSize);
    }
    g2.dispose();
    graphics.drawImage(img, 0, 0, null);
    super.paintComponent(graphics);
  }

  @Override
  public void paint(Graphics graphics) {
    super.paint(graphics);
    if (badges > 0) {
      String value = badges > 9 ? "+9" : badges + "";
      int width = getWidth();
      Graphics2D graphics2d = (Graphics2D) graphics;
      graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      FontMetrics fontMetrics = graphics2d.getFontMetrics();
      Rectangle2D rectangle2d = fontMetrics.getStringBounds(value, graphics2d);
      int fontWidth = (int) rectangle2d.getWidth();
      int fontHeight = (int) rectangle2d.getHeight();
      graphics2d.setColor(getForeground());
      int size = Math.max(fontWidth, fontHeight) + 4;
      graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.8f));
      graphics2d.fillOval(width - size, 0, size, size);
      int x = (size - fontWidth) / 2;
      graphics2d.setColor(Color.WHITE);
      graphics2d.setComposite(AlphaComposite.SrcOver);
      graphics2d.drawString(value, width - size + x, fontMetrics.getAscent() + 1);
      graphics2d.dispose();
    }
  }
}
