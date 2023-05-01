package com.bookstore.gui.main;

import com.bookstore.gui.component.header.HeaderDashboard;
import com.bookstore.gui.component.menu.DrawerMenu;
import com.bookstore.gui.component.panel.MainPanel;
import java.awt.LayoutManager;
import javax.swing.JFrame;

public class MainUI extends JFrame {

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
