package com.bookstore.gui.model;

import java.awt.event.ActionListener;

public class MenuItemModel {
  private String name;
  private ActionListener actionListener;

  public MenuItemModel() {
  }

  public MenuItemModel(String name) {
    this.name = name;
  }

  public MenuItemModel(String name, ActionListener actionListener) {
    this.name = name;
    this.actionListener = actionListener;
  }

  public String getName() {
    return name;
  }

  public ActionListener getActionListener() {
    return actionListener;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setActionListener(ActionListener actionListener) {
    this.actionListener = actionListener;
  }
}