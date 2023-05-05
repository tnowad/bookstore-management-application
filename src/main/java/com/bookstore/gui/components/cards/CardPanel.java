package com.bookstore.gui.components.cards;

import com.bookstore.models.gui.StatisticCardModel;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class CardPanel extends JPanel {

  private JLabel labelDescription;
  private JLabel labelTitle;
  private JLabel labelValues;

  private Color colorTop;
  private Color colorBottom;
  private StatisticCardModel statisticCardModel;

  public Color getColorTop() {
    return colorTop;
  }

  public void setColor(Color colorTop, Color colorBottom) {
    this.colorTop = colorTop;
    this.colorBottom = colorBottom;
  }

  public void setColor(Color color) {
    this.colorTop = color;
    this.colorBottom = color;
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

  public CardPanel(StatisticCardModel statisticCardModel) {
    this.statisticCardModel = statisticCardModel;
    initComponents();
    setOpaque(false);
    colorTop = Color.BLACK;
    colorBottom = Color.WHITE;
  }

  private void initComponents() {
    labelTitle = new JLabel();
    labelValues = new JLabel();
    labelDescription = new JLabel();

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
            .addGap(18, 18, 18)
            .addComponent(labelTitle)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labelValues)
            .addGap(18, 18, 18)
            .addComponent(labelDescription)
            .addContainerGap(25, Short.MAX_VALUE)
        )
    );

    labelTitle.setIcon(statisticCardModel.getIcon());
    labelTitle.setText(statisticCardModel.getTitle());
    labelValues.setText(statisticCardModel.getValues());
    labelDescription.setText(statisticCardModel.getDescription());
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON
    );
    GradientPaint gradientPaint = new GradientPaint(
      0,
      0,
      colorTop,
      0,
      getHeight(),
      colorBottom
    );
    graphics2d.setPaint(gradientPaint);
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
