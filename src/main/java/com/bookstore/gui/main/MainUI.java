package com.bookstore.gui.main;

import com.bookstore.gui.components.headers.HeaderDashboard;
import com.bookstore.gui.components.menus.DrawerMenu;
import com.bookstore.gui.components.panels.MainPanel;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainUI extends JFrame {

  private static MainUI instance = null;

  private LayoutManager layout;

  private DrawerMenu drawerMenu;
  private MainPanel mainPanel;
  private HeaderDashboard headerDashboard;

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
    layout = new BorderLayout();
    setLayout(layout);
    add(drawerMenu, BorderLayout.LINE_START);
    JPanel mainPanelWrapper = new JPanel(new BorderLayout());
    mainPanelWrapper.add(headerDashboard, BorderLayout.PAGE_START);
    mainPanelWrapper.add(mainPanel, BorderLayout.CENTER);
    add(mainPanelWrapper, BorderLayout.CENTER);
  }
}
