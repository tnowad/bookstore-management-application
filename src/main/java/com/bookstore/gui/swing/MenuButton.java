package com.bookstore.gui.swing;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class MenuButton extends JButton {

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  private int index;
  private Animator animator;
  private int targetSize;
  private float animatorSize;
  private Point pressedPoint;
  private float alpha;
  private Color effectColor = new Color(255, 255, 255, 150);

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
    setForeground(new Color(255, 255, 255));
    setHorizontalAlignment(JButton.LEFT);
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
    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    if (pressedPoint != null) {
      graphics2d.setColor(effectColor);
      graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
      graphics2d.fillOval((int) (pressedPoint.x - animatorSize / 2), (int) (pressedPoint.y - animatorSize / 2),
          (int) animatorSize,
          (int) animatorSize);
    }
    graphics2d.setComposite(AlphaComposite.SrcOver);
    super.paintComponent(graphics);
  }
}
