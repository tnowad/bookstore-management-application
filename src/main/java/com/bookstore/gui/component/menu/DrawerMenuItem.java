package com.bookstore.gui.component.menu;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.SubMenuItemModel;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.GroupLayout;
import net.miginfocom.swing.MigLayout;

public class DrawerMenuItem extends javax.swing.JPanel {

  private float alpha;
  private transient MenuItemModel menuItemModel;
  private boolean open;
  private int index;

  public DrawerMenuItem(MenuItemModel menuItemModel) {
    initComponents();

    setOpaque(false);
    setLayout(
      new MigLayout(
        "wrap, fillx, insets 0",
        "[fill]",
        "[fill, 40!]0[fill, 35!]"
      )
    );
    MenuButton firstItem = new MenuButton(
      menuItemModel.getIcon(),
      "      " + menuItemModel.getTitle()
    );

    firstItem.addActionListener(ae -> {});

    add(firstItem);
    for (SubMenuItemModel subMenuItemModel : menuItemModel.getSubMenuItems()) {
      MenuButton item = new MenuButton(subMenuItemModel.getTitle());
      item.addActionListener(subMenuItemModel.getActionListener());
      add(item);
    }
  }

  private void initComponents() {
    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 400, Short.MAX_VALUE)
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 300, Short.MAX_VALUE)
    );
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    int width = getWidth();
    int height = getPreferredSize().height;
    Graphics2D g2 = (Graphics2D) graphics;
    g2.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON
    );
    g2.setColor(new Color(50, 50, 50));
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    g2.fillRect(0, 2, width, 38);
    g2.setComposite(AlphaComposite.SrcOver);
    g2.fillRect(0, 40, width, height - 40);
    g2.setColor(new Color(100, 100, 100));
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
    g2.setColor(new Color(230, 230, 230));
    float ay = alpha * size;
    float ay1 = (1f - alpha) * size;
    g2.drawLine(x, (int) (y + ay), x + 4, (int) (y + ay1));
    g2.drawLine(x + 4, (int) (y + ay1), x + 8, (int) (y + ay));
  }
}
