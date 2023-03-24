package com.bookstore.gui.model;

import java.awt.Component;

public class MenuItemModel {
  private String name;
  private Component component;

  public MenuItemModel(String name, Component component) {
    this.name = name;
    this.component = component;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Component getComponent() {
    return component;
  }

  public void setComponent(Component component) {
    this.component = component;
  }
}