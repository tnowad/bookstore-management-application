package com.bookstore.gui.components.menus;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.SubMenuItemModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class DrawerMenuItem extends JPanel {

  private MenuItemModel menuItemModel;

  public DrawerMenuItem(MenuItemModel menuItemModel) {
    this.menuItemModel = menuItemModel;
    initComponents();
    addFirstButton();
    // addSubMenuItems();
  }

  private void addFirstButton() {
    MenuButton firstButton = new MenuButton("  " + menuItemModel.getTitle());
    firstButton.setIcon(menuItemModel.getIcon());
    firstButton.setForeground(Color.decode("#FFFFFF"));
    firstButton.setFont(firstButton.getFont().deriveFont(1));
    firstButton.addActionListener(e -> {
      if (menuItemModel.getActionListener() != null) {
        menuItemModel.getActionListener().actionPerformed(e);
      }
      toggleSubMenuItems.actionPerformed(e);
    });
    firstButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
    firstButton.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
    firstButton.setPreferredSize(new Dimension(Integer.MAX_VALUE, 40));

    add(firstButton);
  }

  private void addSubMenuItems() {
    if (menuItemModel.getSubMenuItems() != null) {
      JPanel subMenuItemsPanel = new JPanel();

      subMenuItemsPanel.setLayout(
        new BoxLayout(subMenuItemsPanel, BoxLayout.Y_AXIS)
      );
      subMenuItemsPanel.setBackground(Color.decode("#3B70C1"));

      for (SubMenuItemModel subMenuItemModel : menuItemModel.getSubMenuItems()) {
        MenuButton subMenuItemButton = new MenuButton(
          "   " + subMenuItemModel.getTitle()
        );
        subMenuItemButton.setIcon(subMenuItemModel.getIcon());
        subMenuItemButton.addActionListener(
          subMenuItemModel.getActionListener()
        );

        subMenuItemButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        subMenuItemButton.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
        subMenuItemButton.setPreferredSize(
          new Dimension(Integer.MAX_VALUE, 40)
        );
        subMenuItemButton.setForeground(Color.decode("#FFFFFF"));
        subMenuItemsPanel.add(subMenuItemButton);
      }
      add(subMenuItemsPanel);
    }
  }

  private void initComponents() {
    setOpaque(false);
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
}
