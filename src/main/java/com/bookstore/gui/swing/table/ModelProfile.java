package com.bookstore.gui.swing.table;

import javax.swing.Icon;

public class ModelProfile {

  private Icon icon;
  private String name;

  public ModelProfile() {
  }

  public ModelProfile(Icon icon, String name) {
    this.icon = icon;
    this.name = name;
  }

  public Icon getIcon() {
    return icon;
  }

  public void setIcon(Icon icon) {
    this.icon = icon;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
