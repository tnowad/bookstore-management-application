package com.bookstore.gui.components.menus;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.MenuModel;
import com.bookstore.models.SubMenuItemModel;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DrawerMenu extends JPanel {

  private static DrawerMenu instance = null;

  public static DrawerMenu getInstance() {
    if (instance == null) {
      instance = new DrawerMenu(new MenuModel(new ArrayList<MenuItemModel>()));
    }
    return instance;
  }

  private MenuModel menuModel;

  private JScrollPane scrollPane;
  private JPanel menuItemsPanel;

  public DrawerMenu(MenuModel menuModel) {
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
    menuItemsPanel.setBackground(Color.decode("#273853"));

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
    DrawerMenuItem drawerMenuItem = new DrawerMenuItem(menuItemModel);
    menuItemsPanel.add(drawerMenuItem);
  }

  public static void main(String[] args) {
    FlatMacLightLaf.setup();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    frame.setLocationRelativeTo(null);
    ActionListener showClickedActionListener = e -> {
      System.out.println("Clicked");
    };
    // new icon from image
    ImageIcon icon = new ImageIcon("src/main/java/resources/icons/menu.png");
    MenuModel menuModel = new MenuModel(
      new ArrayList<MenuItemModel>() {
        {
          add(
            new MenuItemModel(
              "Home",
              icon,
              showClickedActionListener,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(
                    new SubMenuItemModel(
                      "Home 1",
                      icon,
                      showClickedActionListener
                    )
                  );
                  add(new SubMenuItemModel("Home 2", null, null));
                  add(new SubMenuItemModel("Home 3", null, null));
                }
              }
            )
          );
          add(
            new MenuItemModel(
              "Home 2",
              null,
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(new SubMenuItemModel("Home 1", null, null));
                  add(new SubMenuItemModel("Home 2", null, null));
                  add(new SubMenuItemModel("Home 3", null, null));
                }
              }
            )
          );
          add(
            new MenuItemModel(
              "Home 3",
              null,
              null,
              new ArrayList<SubMenuItemModel>() {
                {
                  add(new SubMenuItemModel("Home 1", null, null));
                  add(new SubMenuItemModel("Home 2", null, null));
                  add(new SubMenuItemModel("Home 3", null, null));
                }
              }
            )
          );
          add(new MenuItemModel("Home 4", null, null));
        }
      }
    );
    System.out.println(menuModel.getMenuItems().size());

    DrawerMenu drawerMenu = new DrawerMenu(menuModel);

    frame.add(drawerMenu);
    frame.setVisible(true);
  }
}
