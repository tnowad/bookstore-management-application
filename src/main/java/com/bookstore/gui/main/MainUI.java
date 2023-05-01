package com.bookstore.gui.main;

import com.bookstore.gui.components.headers.HeaderDashboard;
import com.bookstore.gui.components.menus.DrawerMenu;
import com.bookstore.gui.components.panels.MainPanel;
import java.awt.LayoutManager;
import javax.swing.JFrame;

public class MainUI extends JFrame {

  private static MainUI instance = null;

  public static MainUI getInstance() {
    if (instance == null) {
      instance =
        new MainUI(
          DrawerMenu.getInstance(),
          MainPanel.getInstance(),
          HeaderDashboard.getInstance()
        );
    }
    return instance;
  }

  private LayoutManager layout;

  private DrawerMenu drawerMenu;
  private MainPanel mainPanel;
  private HeaderDashboard headerDashboard;

  public MainUI(
    DrawerMenu drawerMenu,
    MainPanel mainPanel,
    HeaderDashboard headerDashboard
  ) {
    this.drawerMenu = drawerMenu;
    this.mainPanel = mainPanel;
    this.headerDashboard = headerDashboard;
    initComponents();
    pack();
    setLocationRelativeTo(null);
  }

  private void initComponents() {
    layout = new java.awt.BorderLayout();
    setLayout(layout);
    add(drawerMenu, java.awt.BorderLayout.LINE_START);
    add(mainPanel, java.awt.BorderLayout.CENTER);
    add(headerDashboard, java.awt.BorderLayout.PAGE_START);
  }
}
