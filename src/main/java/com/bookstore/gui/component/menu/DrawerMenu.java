package com.bookstore.gui.component.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.MenuModel;

public class DrawerMenu extends JPanel {
    private MenuModel menuModel;
    private Boolean isExpanded = true;

    private JScrollPane scrollPane;
    private JPanel menuItemsPanel;

    public DrawerMenu(MenuModel menuModel) {
        this.menuModel = menuModel;
        initComponents();

        for (MenuItemModel menuItemModel : menuModel.getMenuItems()) {
            addMenuItem(menuItemModel);
        }
    }

    private void initComponents() {
        scrollPane = new JScrollPane();
        menuItemsPanel = new JPanel();

        scrollPane.setBorder(null);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setViewport(null);

        GroupLayout menuItemsPanelLayout = new GroupLayout(menuItemsPanel);
        menuItemsPanel.setLayout(menuItemsPanelLayout);

        menuItemsPanelLayout.setHorizontalGroup(
                menuItemsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 312, Short.MAX_VALUE));

        menuItemsPanelLayout.setVerticalGroup(
                menuItemsPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 523, Short.MAX_VALUE));

        scrollPane.setViewportView(menuItemsPanel);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE));

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

        menuItemsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        MenuModel menuModel = new MenuModel(new ArrayList<MenuItemModel>() {
            {
                add(new MenuItemModel("Home", null, null));
                add(new MenuItemModel("Books", null, null));
                add(new MenuItemModel("Authors", null, null));
                add(new MenuItemModel("Publishers", null, null));
                add(new MenuItemModel("Categories", null, null));
                add(new MenuItemModel("Users", null, null));
                add(new MenuItemModel("Settings", null, null));
            }
        });

        DrawerMenu drawerMenu = new DrawerMenu(menuModel);

        frame.add(drawerMenu);
        frame.setVisible(true);
    }
}
