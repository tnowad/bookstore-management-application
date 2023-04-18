package com.bookstore.gui.component.menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel profilePanel = new JPanel();
        profilePanel.setPreferredSize(new Dimension(200, 100));
        add(profilePanel, BorderLayout.NORTH);
        menuItemsPanel = new JPanel();
        menuItemsPanel.setLayout(new BoxLayout(menuItemsPanel, BoxLayout.Y_AXIS));
        scrollPane = new JScrollPane(menuItemsPanel);
        add(scrollPane, BorderLayout.CENTER);
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

        menuItemsPanel.revalidate();
        menuItemsPanel.repaint();
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);
        
        
        MenuModel menuModel = new MenuModel(new ArrayList<MenuItemModel>(){
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
