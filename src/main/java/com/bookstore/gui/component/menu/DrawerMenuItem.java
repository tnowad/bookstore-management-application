package com.bookstore.gui.component.menu;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.GroupLayout;
import javax.swing.JPanel;

import com.bookstore.models.MenuItemModel;

public class DrawerMenuItem extends JPanel {
    private MenuItemModel menuItem;

    private float alpha;

    public DrawerMenuItem(MenuItemModel menuItemModel) {
        this.menuItem = menuItemModel;
        initComponents();

        MenuButton firstItem = new MenuButton(menuItem.getIcon(), "     " + menuItem.getTitle());
        firstItem.addActionListener(menuItem.getActionListener());

        this.add(firstItem);
        for (int i = 0; i < menuItem.getSubMenuItems().size(); i++) {
            MenuButton subMenuItem = new MenuButton(menuItem.getSubMenuItems().get(i).getIcon(),
                    "     " + menuItem.getSubMenuItems().get(i).getTitle());
            subMenuItem.addActionListener(menuItem.getSubMenuItems().get(i).getActionListener());
            this.add(subMenuItem);
        }
    }

    private void initComponents() {
        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE));
    }

    protected void paintComponent(Graphics graphics) {
        int width = getWidth();
        int height = getPreferredSize().height;
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha * 0.5f));
        g2.fillRect(0, 0, width, 40);
        g2.setComposite(AlphaComposite.SrcOver);
        g2.setColor(new Color(60, 60, 60));
        g2.drawLine(30, 40, 30, height - 17);
        for (int i = 0; i < menuItem.getSubMenuItems().size(); i++) {
            int y = ((i + 1) * 35 + 40) - 17;
            g2.drawLine(30, y, 38, y);
        }
        if (menuItem.getSubMenuItems().size() > 0) {
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
}
