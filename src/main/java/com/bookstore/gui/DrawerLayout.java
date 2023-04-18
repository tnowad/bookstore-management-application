package com.bookstore.gui.component.button;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;
import java.awt.*;

public class DrawerLayout extends JFrame {

    public DrawerLayout() {
        initUI();
    }

    private void initUI() {

        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        setTitle("Drawer Layout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(Color.WHITE);

        JToggleButton toggleButton = new JToggleButton();
        toggleButton.setOpaque(false);
        toggleButton.setPreferredSize(new Dimension(50, 50));
        toggleButton.setIcon(new ImageIcon(getClass().getResource("/resources/icons/menu.png")));

        JLabel toggleButtonLabel = new JLabel();
        toggleButtonLabel.setOpaque(false);
        toggleButtonLabel.setPreferredSize(new Dimension(50, 50));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setPreferredSize(new Dimension(getWidth(), 50));
        headerPanel.setBackground(Color.LIGHT_GRAY);
        headerPanel.add(toggleButtonLabel, BorderLayout.WEST);
        headerPanel.add(new JLabel("Header"), BorderLayout.CENTER);

        JPanel drawerPanel = new JPanel(new BorderLayout());
        drawerPanel.setPreferredSize(new Dimension(300, getHeight()));
        drawerPanel.setBackground(Color.DARK_GRAY);

        JList<String> menuList = new JList<>(new String[]{"Menu Item", "Menu Item", "Menu Item", "Menu Item"});
        menuList.setBackground(Color.DARK_GRAY);
        menuList.setForeground(Color.WHITE);
        menuList.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));

        drawerPanel.add(menuList, BorderLayout.CENTER);

        JPanel pageContentPanel = new JPanel(new BorderLayout());
        pageContentPanel.setBackground(Color.WHITE);
        pageContentPanel.add(new JLabel("Page Content"), BorderLayout.CENTER);

        contentPanel.add(headerPanel, BorderLayout.NORTH);
        contentPanel.add(pageContentPanel, BorderLayout.CENTER);
        contentPanel.add(toggleButton, BorderLayout.WEST);
        add(contentPanel, BorderLayout.CENTER);
        add(drawerPanel, BorderLayout.WEST);

        toggleButton.addActionListener(e -> {
            if (toggleButton.isSelected()) {
                toggleButtonLabel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/menu.png")));
                contentPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.LIGHT_GRAY));
                contentPanel.setBackground(new Color(237, 244, 255));
                drawerPanel.setPreferredSize(new Dimension(300, getHeight()));
            } else {
                toggleButtonLabel.setIcon(new ImageIcon(getClass().getResource("/resources/icons/menu.png")));
                contentPanel.setBorder(BorderFactory.createMatteBorder(0, 1, 0, 0, Color.LIGHT_GRAY));
                contentPanel.setBackground(Color.WHITE);
                drawerPanel.setPreferredSize(new Dimension(0, getHeight()));
            }
            revalidate();
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        DrawerLayout drawerLayout = new DrawerLayout();
        drawerLayout.setVisible(true);
    }
}
