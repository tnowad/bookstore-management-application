package com.bookstore.gui.swing;

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
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
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
  private float animationSize;
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
        animationSize = 0;
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
        animationSize = fraction * targetSize;
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
      g2.fillOval((int) (pressedPoint.x - animationSize / 2), (int) (pressedPoint.y - animationSize / 2),
          (int) animationSize,
          (int) animationSize);
    }
    g2.dispose();
    graphics.drawImage(img, 0, 0, null);
    super.paintComponent(graphics);
  }
}
