package com.bookstore.gui.component.menu;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import com.bookstore.models.MenuItemModel;
import com.bookstore.models.MenuModel;

public class DrawerMenu extends JPanel {
    private MenuModel menuModel;

    public DrawerMenu(MenuModel menuModel) {
        this.menuModel = menuModel;

        // Create a JList to display the menu items
        JList<MenuItemModel> list = new JList<>();
        list.setModel(new DefaultListModel<>());
        list.setCellRenderer(new MenuItemRenderer());

        // Add the menu items to the list
        DefaultListModel<MenuItemModel> listModel = (DefaultListModel<MenuItemModel>) list.getModel();
        List<MenuItemModel> menuItems = menuModel.getMenuItems();
        for (MenuItemModel menuItem : menuItems) {
            listModel.addElement(menuItem);
        }

        // Add an ActionListener to the list to handle selection
        
        for (MenuItemModel menuItemModel : menuItems) {
            listModel.addElement(menuItemModel);
        }

        // Add the list to the JPanel
        setLayout(new BorderLayout());
        add(new JLabel(menuModel.getTitle(), menuModel.getIcon(), JLabel.CENTER), BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
    }

    // Custom ListCellRenderer to display the menu item titles
    private class MenuItemRenderer extends JLabel implements ListCellRenderer<MenuItemModel> {
        public MenuItemRenderer() {
            setOpaque(true);
        }

        @Override
        public java.awt.Component getListCellRendererComponent(JList<? extends MenuItemModel> list, MenuItemModel value,
                int index, boolean isSelected, boolean cellHasFocus) {
            setText(value.getTitle());
            if (isSelected) {
                setBackground(list.getSelectionBackground());
                setForeground(list.getSelectionForeground());
            } else {
                setBackground(list.getBackground());
                setForeground(list.getForeground());
            }
            return this;
        }
    }

    public static void main(String[] args) {
        MenuModel menuModel = new MenuModel(null, "Menu Title", null, List.of(
                new MenuItemModel("Item 1", "action1"),
                new MenuItemModel("Item 2", "action2"),
                new MenuItemModel("Item 3", "action3")));
        JPanel drawerMenu = new DrawerMenu(menuModel);
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(drawerMenu);
        frame.pack();
        frame.setVisible(true);
    }
}
