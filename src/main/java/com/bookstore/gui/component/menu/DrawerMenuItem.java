package com.bookstore.gui.component.menu;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.SubMenuItemModel;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.RenderingHints;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

public class DrawerMenuItem extends JPanel {

  private MenuItemModel menuItemModel;

  private float alpha;

  public DrawerMenuItem(MenuItemModel menuItemModel) {
    this.menuItemModel = menuItemModel;
    initComponents();

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 1;
    gridBagConstraints.weighty = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.NORTH;

    MenuButton firstItem = new MenuButton(
      menuItemModel.getIcon(),
      "     " + menuItemModel.getTitle()
    );
    firstItem.setBackground(Color.RED);
    firstItem.addActionListener(menuItemModel.getActionListener());

    gridBagConstraints.insets = new java.awt.Insets(0, 0, 0, 0);

    this.add(firstItem, gridBagConstraints);

    if (menuItemModel.getSubMenuItems() != null) {
      for (SubMenuItemModel subMenuItemModel : menuItemModel.getSubMenuItems()) {
        MenuButton subItem = new MenuButton(
          subMenuItemModel.getIcon(),
          "     " + subMenuItemModel.getTitle()
        );
        subItem.addActionListener(subMenuItemModel.getActionListener());
        
        gridBagConstraints.gridy++;

        this.add(subItem, gridBagConstraints);
      }
    }
  }

  private void initComponents() {
    GridBagLayout layout = new GridBagLayout();
    this.setLayout(layout);
  }

  protected void paintComponent(Graphics graphics) {
    int width = getWidth();
    int height = getPreferredSize().height;
    Graphics2D g2 = (Graphics2D) graphics;
    g2.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON
    );
    g2.setColor(new Color(255, 255, 255));
    g2.setComposite(
      AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha * 0.5f)
    );
    g2.fillRect(0, 0, width, 40);
    g2.setComposite(AlphaComposite.SrcOver);
    g2.setColor(new Color(60, 60, 60));
    g2.drawLine(30, 40, 30, height - 17);
    for (int i = 0; i < menuItemModel.getSubMenuItems().size(); i++) {
      int y = ((i + 1) * 35 + 40) - 17;
      g2.drawLine(30, y, 38, y);
    }
    if (menuItemModel.getSubMenuItems().size() > 0) {
      createArrowButton(g2);
    }
    super.paintComponent(graphics);
  }

  private void createArrowButton(Graphics2D g2) {
    int size = 4;
    int y = 19;
    int x = 205;
    g2.setColor(new Color(60, 60, 60));
    float ay = alpha * size;
    float ay1 = (1f - alpha) * size;
    g2.drawLine(x, (int) (y + ay), x + 4, (int) (y + ay1));
    g2.drawLine(x + 4, (int) (y + ay1), x + 8, (int) (y + ay));
  }

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
