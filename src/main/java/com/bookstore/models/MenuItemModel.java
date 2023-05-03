package com.bookstore.models;

import java.awt.Image;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class MenuItemModel {

  private String title;
  private Icon icon;
  private ActionListener actionListener;
  private ArrayList<SubMenuItemModel> subMenuItems;

  public MenuItemModel(String title, Icon icon, ActionListener actionListener) {
    this.title = title;
    this.icon = icon;
    this.actionListener = actionListener;
  }

  public MenuItemModel(
    String title,
    Icon icon,
    ActionListener actionListener,
    ArrayList<SubMenuItemModel> subMenuItems
  ) {
    this.title = title;
    this.icon = icon;
    this.actionListener = actionListener;
    this.subMenuItems = subMenuItems;
  }

  public String getTitle() {
    return title;
  }

  public Icon getIcon() {
    if (icon == null) {
      icon = new ImageIcon("");
    }
    Image iconImage = ((ImageIcon) icon).getImage();
    return new ImageIcon(
      iconImage.getScaledInstance(24, 24, java.awt.Image.SCALE_SMOOTH)
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

  public ActionListener getActionListener() {
    return actionListener;
  }

  public ArrayList<SubMenuItemModel> getSubMenuItems() {
    return subMenuItems;
  }
}
