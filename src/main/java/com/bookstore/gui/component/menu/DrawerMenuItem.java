package com.bookstore.gui.component.menu;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.SubMenuItemModel;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class DrawerMenuItem extends JPanel {

  private MenuItemModel menuItemModel;

  public DrawerMenuItem(MenuItemModel menuItemModel) {
    this.menuItemModel = menuItemModel;

    initComponents();
    addFirstButton();
    addSubMenuItems();
  }

  private void addFirstButton() {
    MenuButton firstButton = new MenuButton("  " + menuItemModel.getTitle());
    firstButton.addActionListener(toggleSubMenuItems);
    add(firstButton);
  }

  private void addSubMenuItems() {
    if (menuItemModel.getSubMenuItems() != null) {
      JPanel subMenuItemsPanel = new JPanel();
      subMenuItemsPanel.setLayout(
        new BoxLayout(subMenuItemsPanel, BoxLayout.Y_AXIS)
      );
      for (SubMenuItemModel subMenuItemModel : menuItemModel.getSubMenuItems()) {
        MenuButton subMenuItemButton = new MenuButton(
          "  " + subMenuItemModel.getTitle()
        );
        subMenuItemButton.addActionListener(
          subMenuItemModel.getActionListener()
        );
        subMenuItemsPanel.add(subMenuItemButton);
      }
      add(subMenuItemsPanel);
    }
  }

  private void initComponents() {
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  private ActionListener toggleSubMenuItems = e -> {
    if (getComponents().length == 1) {
      addSubMenuItems();
    } else if (getComponents().length > 1) {
      removeAll();
      addFirstButton();
    }
    revalidate();
    repaint();
  };

  public static void main(String[] args) {
    javax.swing.JFrame frame = new javax.swing.JFrame();
    frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    frame
      .getContentPane()
      .add(
        new DrawerMenuItem(
          new MenuItemModel(
            "Menu",
            null,
            null,
            new ArrayList<SubMenuItemModel>() {
              {
                add(new SubMenuItemModel("Sub Menu", null, null));
                add(new SubMenuItemModel("Sub Menu", null, null));
                add(new SubMenuItemModel("Sub Menu", null, null));
              }
            }
          )
        )
      );

    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
