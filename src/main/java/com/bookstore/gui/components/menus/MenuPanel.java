package com.bookstore.gui.components.menus;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.MenuModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MenuPanel extends JPanel {

  private static MenuPanel instance = null;

  public static MenuPanel getInstance() {
    if (instance == null) {
      instance = new MenuPanel(new MenuModel(new ArrayList<MenuItemModel>()));
    }
    return instance;
  }

  private MenuModel menuModel;

  private JScrollPane scrollPane;
  private JPanel menuItemsPanel;

  public MenuPanel(MenuModel menuModel) {
    this.menuModel = menuModel;
    initComponents();
    if (menuModel.getMenuItems() != null) {
      for (MenuItemModel menuItemModel : menuModel.getMenuItems()) {
        addMenuItem(menuItemModel);
      }
    }
  }

  private void initComponents() {
    scrollPane = new JScrollPane();
    menuItemsPanel = new JPanel();

    // color of the background of the menu
    menuItemsPanel.setBackground(Color.decode("#007aff"));

    menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));

    scrollPane.setBorder(null);
    scrollPane.setHorizontalScrollBarPolicy(
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    scrollPane.setViewport(null);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    scrollPane.setViewportView(menuItemsPanel);
    setLayout(new BorderLayout());
    add(scrollPane, BorderLayout.CENTER);
    setPreferredSize(new Dimension(400, 400));
  }

  public MenuModel getMenuModel() {
    return menuModel;
  }

  public void setMenuModel(MenuModel menuModel) {
    this.menuModel = menuModel;
  }

  public void addMenuItem(MenuItemModel menuItemModel) {
    MenuItemPanel menuItemPanel = new MenuItemPanel(menuItemModel);

    // color of background of the menu
    menuItemPanel.setBackground(Color.decode("#4a90d9"));

    menuItemsPanel.add(menuItemPanel);
  }

  public static void updateInstance(MenuPanel menuPanel) {
    MenuPanel.instance = menuPanel;
  }

  public void closeAllSubMenuItems() {
    for (int i = 0; i < menuItemsPanel.getComponentCount(); i++) {
      if (!(menuItemsPanel.getComponent(i) instanceof MenuItemPanel)) continue;
      ((MenuItemPanel) menuItemsPanel.getComponent(i)).closeSubMenuItems();
    }
  }
}
