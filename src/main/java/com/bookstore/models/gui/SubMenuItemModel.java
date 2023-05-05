package com.bookstore.models.gui;

import java.awt.Image;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class SubMenuItemModel {

  private String title;
  private Icon icon;
  private ActionListener actionListener;

  public SubMenuItemModel(
    String title,
    Icon icon,
    ActionListener actionListener
  ) {
    this.title = title;
    this.icon = icon;
    this.actionListener = actionListener;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
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

  public ActionListener getActionListener() {
    return actionListener;
  }

  public void setActionListener(ActionListener actionListener) {
    this.actionListener = actionListener;
  }
}
