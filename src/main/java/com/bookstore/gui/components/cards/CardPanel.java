package com.bookstore.gui.components.cards;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import com.bookstore.models.gui.StatisticCardModel;

public class CardPanel extends JPanel {

  private JLabel labelDescription;
  private JLabel labelIcon;
  private JLabel labelTitle;
  private JLabel labelValues;

  private Color colorTop;
  private Color colorBottom;

  public Color getColorTop() {
    return colorTop;
  }

  public void setColorTop(Color color1) {
    this.colorTop = color1;
  }

  public Color getColorBottom() {
    return colorBottom;
  }

  public void setColorBottom(Color color2) {
    this.colorBottom = color2;
  }

  public CardPanel() {
    initComponents();
    setOpaque(false);
    colorTop = Color.BLACK;
    colorBottom = Color.WHITE;
  }

  public void setData(StatisticCardModel data) {
    labelIcon.setIcon(data.getIcon());
    labelTitle.setText(data.getTitle());
    labelValues.setText(data.getValues());
    labelDescription.setText(data.getDescription());
  }

  private void initComponents() {
    labelIcon = new JLabel();
    labelTitle = new JLabel();
    labelValues = new JLabel();
    labelDescription = new JLabel();

    labelIcon.setIcon(
      new Icon() {
        @Override
        public void paintIcon(Component c, Graphics g, int x, int y) {
          Graphics2D g2d = (Graphics2D) g;
          g2d.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
          );
          GradientPaint gp = new GradientPaint(
            0,
            0,
            colorTop,
            0,
            50,
            colorBottom,
            true
          );
          g2d.setPaint(gp);
          g2d.fillOval(0, 0, 50, 50);
        }

        @Override
        public int getIconWidth() {
          return 50;
        }

        @Override
        public int getIconHeight() {
          return 50;
        }
      }
    );

    labelTitle.setFont(new Font("sansserif", 1, 14));
    labelTitle.setForeground(new Color(255, 255, 255));
    labelTitle.setText("Title");

    labelValues.setFont(new Font("sansserif", 1, 18));
    labelValues.setForeground(new Color(255, 255, 255));
    labelValues.setText("Values");

    labelDescription.setFont(new Font("sansserif", 0, 14));
    labelDescription.setForeground(new Color(255, 255, 255));
    labelDescription.setText("Description");

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addGap(25, 25, 25)
            .addGroup(
              layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(labelDescription)
                .addComponent(labelValues)
                .addComponent(labelTitle)
                .addComponent(labelIcon)
            )
            .addContainerGap(283, Short.MAX_VALUE)
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addGap(32, 32, 32)
            .addComponent(labelIcon)
            .addGap(18, 18, 18)
            .addComponent(labelTitle)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labelValues)
            .addGap(18, 18, 18)
            .addComponent(labelDescription)
            .addContainerGap(25, Short.MAX_VALUE)
        )
    );
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON
    );
    GradientPaint g = new GradientPaint(
      0,
      0,
      colorTop,
      0,
      getHeight(),
      colorBottom
    );
    graphics2d.setPaint(g);
    graphics2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    graphics2d.setColor(new Color(255, 255, 255, 50));
    graphics2d.fillOval(
      getWidth() - (getHeight() / 2),
      10,
      getHeight(),
      getHeight()
    );
    graphics2d.fillOval(
      getWidth() - (getHeight() / 2) - 20,
      getHeight() / 2 + 20,
      getHeight(),
      getHeight()
    );
    super.paintComponent(graphics);
  }
}
