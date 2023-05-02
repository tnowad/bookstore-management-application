package com.bookstore.gui.main;

import com.bookstore.gui.components.headers.HeaderPanel;
import com.bookstore.gui.components.menus.MenuPanel;
import com.bookstore.gui.components.panels.MainPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.Timer;

public class MainUI extends JFrame {

  private static MainUI instance = null;

  private LayoutManager layout;

  private MenuPanel drawerMenu;
  private MainPanel mainPanel;
  private JPanel mainPanelWrapper;
  private HeaderPanel headerDashboard;
  private JSplitPane splitPane;

  public static MainUI getInstance() {
    if (instance == null) {
      instance =
        new MainUI(
          MenuPanel.getInstance(),
          MainPanel.getInstance(),
          HeaderPanel.getInstance()
        );
    }
    return instance;
  }

  public MainUI(
    MenuPanel drawerMenu,
    MainPanel mainPanel,
    HeaderPanel headerDashboard
  ) {
    this.drawerMenu = drawerMenu;
    this.mainPanel = mainPanel;
    this.headerDashboard = headerDashboard;
    initComponents();
    pack();
    setLocationRelativeTo(null);
  }

  public void toggleMenu() {
    int dividerLocation = splitPane.getDividerLocation();
    int minSize = drawerMenu.getMinimumSize().width;
    int maxSize = drawerMenu.getMaximumSize().width;

    int targetSize = (dividerLocation == minSize) ? maxSize : minSize;
    int currentSize = dividerLocation;
    int step = (targetSize - currentSize) / 10;

    Timer timer = new Timer(
      20,
      new ActionListener() {
        int newSize = currentSize;
        int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
          if (count < 10) {
            newSize += step;
            splitPane.setDividerLocation(newSize);
            count++;
          } else {
            ((Timer) e.getSource()).stop();
          }
        }
      }
    );
    timer.start();
  }

  private void initComponents() {
    layout = new BorderLayout();
    setLayout(layout);

    drawerMenu.setMinimumSize(new Dimension(50, Integer.MAX_VALUE));
    drawerMenu.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));
    mainPanelWrapper = new JPanel(new BorderLayout());
    mainPanelWrapper.add(headerDashboard, BorderLayout.PAGE_START);
    mainPanelWrapper.add(mainPanel, BorderLayout.CENTER);

    splitPane =
      new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, drawerMenu, mainPanelWrapper);
    splitPane.setResizeWeight(0);
    splitPane.setEnabled(false);
    splitPane.setOneTouchExpandable(false);
    splitPane.setDividerLocation(250);

    add(splitPane, BorderLayout.CENTER);
  }

  public static void destroyInstance() {
    MainUI.instance = null;
  }
}
