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

public class MainFrame extends JFrame {

  private static MainFrame instance = null;

  private LayoutManager layout;

  private MenuPanel menuPanel;
  private MainPanel mainPanel;
  private JPanel mainPanelWrapper;
  private HeaderPanel headerPanel;
  private JSplitPane splitPane;

  public static MainFrame getInstance() {
    if (instance == null) {
      instance =
        new MainFrame(
          MenuPanel.getInstance(),
          MainPanel.getInstance(),
          HeaderPanel.getInstance()
        );
    }
    return instance;
  }

  public MainFrame(
    MenuPanel menuPanel,
    MainPanel mainPanel,
    HeaderPanel headerPanel
  ) {
    this.menuPanel = menuPanel;
    this.mainPanel = mainPanel;
    this.headerPanel = headerPanel;
    setMinimumSize(new Dimension(900, 700));

    initComponents();
    pack();
    setLocationRelativeTo(null);
  }

  public void toggleMenu() {
    menuPanel.closeAllSubMenuItems();
    int dividerLocation = splitPane.getDividerLocation();
    int minSize = menuPanel.getMinimumSize().width;
    int maxSize = menuPanel.getMaximumSize().width;

    int targetSize = (dividerLocation - minSize < maxSize - dividerLocation)
      ? maxSize
      : minSize;

    int currentSize = dividerLocation;
    int step = (targetSize - currentSize) / 10;

    Timer timer = new Timer(
      10,
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

    menuPanel.setMinimumSize(new Dimension(50, Integer.MAX_VALUE));
    menuPanel.setMaximumSize(new Dimension(250, Integer.MAX_VALUE));

    mainPanelWrapper = new JPanel(new BorderLayout());
    mainPanelWrapper.add(headerPanel, BorderLayout.PAGE_START);
    mainPanelWrapper.add(mainPanel, BorderLayout.CENTER);

    splitPane =
      new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, menuPanel, mainPanelWrapper);
    splitPane.setResizeWeight(0);
    splitPane.setEnabled(false);
    splitPane.setOneTouchExpandable(false);
    splitPane.setDividerLocation(250);
    splitPane.setDividerSize(0);

    add(splitPane, BorderLayout.CENTER);

    ActionListener actionListener = menuPanel
      .getMenuModel()
      .getFirstActionListener();
    if (actionListener != null) {
      actionListener.actionPerformed(null);
    }

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  public static void destroyInstance() {
    MainFrame.instance = null;
  }
}
