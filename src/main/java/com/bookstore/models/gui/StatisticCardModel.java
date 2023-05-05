package com.bookstore.models.gui;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class StatisticCardModel {

  private Icon icon;
  private String title;
  private String values;
  private String description;

  public StatisticCardModel() {}

  public StatisticCardModel(
    Icon icon,
    String title,
    String values,
    String description
  ) {
    this.icon = icon;
    this.title = title;
    this.values = values;
    this.description = description;
  }

  public Icon getIcon() {
    if (icon == null) {
      icon = new ImageIcon("");
    }
    Image iconImage = ((ImageIcon) icon).getImage();
    return new ImageIcon(
      iconImage.getScaledInstance(24, 24, Image.SCALE_SMOOTH)
    ) {
      @Override
      public int getIconHeight() {
        return 24;
      }

      @Override
      public int getIconWidth() {
        return 24;
      }
    };
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getValues() {
    return values;
  }

  public void setValues(String values) {
    this.values = values;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
