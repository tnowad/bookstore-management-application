package com.bookstore.gui.form.customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.bookstore.gui.component.button.Button;

import javax.swing.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

public class CustomerFrame extends JFrame {
    private Button aboutUsButton;
    private Button accountSettingButton;
    private Button cartButton;
    private Button contactSupportButton;
    private Button discoverButton;
    private Button myOrderButton;
    private Button homeButton;
    private Button logoutButton;
    private JPanel contentPanel;
    private JPanel headerPanel;
    private JPanel menuBottomPanel;
    private JPanel menuPanel;
    private JPanel menuTopPanel;
    private JLabel statusLabel;
    private JLabel welcomeLabel;

    public CustomerFrame() {
        initComponents();
    }

    private void initComponents() {

        headerPanel = new JPanel();
        welcomeLabel = new JLabel();
        statusLabel = new JLabel();
        cartButton = new Button("Cart");
        menuPanel = new JPanel();
        menuTopPanel = new JPanel();
        homeButton = new Button("Home");
        discoverButton = new Button("Discover");
        myOrderButton = new Button("My order");
        menuBottomPanel = new JPanel();
        accountSettingButton = new Button("Account Settings");
        contactSupportButton = new Button("Contact Support");
        aboutUsButton = new Button("About Us");
        logoutButton = new Button("Logout");
        contentPanel = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(1280, 720));
        setPreferredSize(new Dimension(1280, 720));

        headerPanel.setAlignmentX(1.0F);
        headerPanel.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        headerPanel.setDoubleBuffered(false);
        headerPanel.setMaximumSize(new Dimension(832, 80));
        headerPanel.setMinimumSize(new Dimension(413, 40));
        headerPanel.setPreferredSize(new Dimension(672, 80));

        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setText("Welcome, ");
        welcomeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        welcomeLabel.setMaximumSize(new Dimension(380, 16));
        welcomeLabel.setMinimumSize(new Dimension(300, 16));
        welcomeLabel.setPreferredSize(new Dimension(300, 30));
        welcomeLabel.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                welcomeLabelKeyPressed(evt);
            }
        });
        headerPanel.add(welcomeLabel);

        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setText("Status : ");
        statusLabel.setMaximumSize(new Dimension(500, 16));
        statusLabel.setPreferredSize(new Dimension(300, 16));
        headerPanel.add(statusLabel);

        cartButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cartButtonActionPerformed(evt);
            }

        });
        headerPanel.add(cartButton);

        getContentPane().add(headerPanel, BorderLayout.PAGE_START);

        menuPanel.setPreferredSize(new Dimension(200, 80));
        menuPanel.setLayout(new GridLayout(2, 1, 0, 250));

        menuTopPanel.setLayout(new GridLayout(3, 1, 0, 5));
        homeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                homeButtonActionPerformed(evt);
            }
        });
        menuTopPanel.add(homeButton);

        menuTopPanel.add(discoverButton);

        myOrderButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                myOrderButtonActionPerformed(evt);
            }
        });
        menuTopPanel.add(myOrderButton);

        menuPanel.add(menuTopPanel);

        menuBottomPanel.setLayout(new GridLayout(4, 1, 0, 5));

        accountSettingButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                accountSettingButtonActionPerformed(evt);
            }
        });
        menuBottomPanel.add(accountSettingButton);

        contactSupportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                contactSupportButtonActionPerformed(evt);
            }
        });
        menuBottomPanel.add(contactSupportButton);

        aboutUsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                aboutUsButtonActionPerformed(evt);
            }
        });
        menuBottomPanel.add(aboutUsButton);

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                logoutButtonActionPerformed(evt);
            }
        });
        menuBottomPanel.add(logoutButton);

        menuPanel.add(menuBottomPanel);

        getContentPane().add(menuPanel, BorderLayout.LINE_START);

        contentPanel.setLayout(new CardLayout());
        getContentPane().add(contentPanel, BorderLayout.CENTER);

        pack();
    }// </editor-fold>

    private void cartButtonActionPerformed(ActionEvent evt) {
        System.out.println(1);
    }

    private void homeButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void myOrderButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void accountSettingButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void contactSupportButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void aboutUsButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void logoutButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void welcomeLabelKeyPressed(KeyEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(String args[]) {

        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.put("Panel.background", new Color(250, 250, 250));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
                | UnsupportedLookAndFeelException ignored) {
        }
        FlatMacLightLaf.setup();

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CustomerFrame().setVisible(true);
            }
        });
    }

}
