package com.bookstore.models;

import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.Icon;


public class MenuModel {
  private Icon icon;
  private String title;
  private ActionEvent action;
  private List<MenuModel> subMenus;

  public MenuModel(Icon icon, String title, ActionEvent action, List<MenuModel> subMenus) {
    this.icon = icon;
    this.title = title;
    this.action = action;
    this.subMenus = subMenus;
  }

  public Icon getIcon() {
    return icon;
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

  public ActionEvent getAction() {
    return action;
  }

  public void setAction(ActionEvent action) {
    this.action = action;
  }

  public List<MenuModel> getSubMenus() {
    return subMenus;
  }

  public void setSubMenus(List<MenuModel> subMenus) {
    this.subMenus = subMenus;
  }
}
