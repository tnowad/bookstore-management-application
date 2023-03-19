package com.bookstore.gui.swing;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class Button extends JButton {

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

  public Button() {
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
    Graphics2D graphics2d = img.createGraphics();
    graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2d.setColor(getBackground());
    graphics2d.fillRoundRect(0, 0, width, height, height, height);
    if (pressedPoint != null) {
      graphics2d.setColor(effectColor);
      graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, alpha));
      graphics2d.fillOval((int) (pressedPoint.x - animatorSize / 2), (int) (pressedPoint.y - animatorSize / 2),
          (int) animatorSize,
          (int) animatorSize);
    }
    graphics2d.dispose();
    graphics.drawImage(img, 0, 0, null);
    super.paintComponent(graphics);
  }
}
