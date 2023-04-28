package com.bookstore.gui.component.menu;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.SubMenuItemModel;
import java.awt.Color;
import java.awt.Dimension;
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
    // addSubMenuItems();
  }

  private void addFirstButton() {
    MenuButton firstButton = new MenuButton("  " + menuItemModel.getTitle());
    firstButton.addActionListener(toggleSubMenuItems);
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
          "  " + subMenuItemModel.getTitle()
        );
        subMenuItemButton.addActionListener(
          subMenuItemModel.getActionListener()
        );

        subMenuItemButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        subMenuItemButton.setMinimumSize(new Dimension(Integer.MAX_VALUE, 40));
        subMenuItemButton.setPreferredSize(
          new Dimension(Integer.MAX_VALUE, 40)
        );

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
