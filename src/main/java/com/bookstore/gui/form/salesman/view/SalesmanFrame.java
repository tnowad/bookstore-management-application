package com.bookstore.gui.form.salesman.view;

import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.main.RegisterUI;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SalesmanFrame extends JFrame {

  private Button aboutUsButton;
  private Button accountButton;
  private Button cartButton;
  private Button contactButton;
  private JPanel contentPanel;
  private Button pendingOrderButton;
  private JPanel headerPanel;
  private Button customerListButton;
  private Button logoutButton;
  private JPanel menuBottomPanel;
  private JPanel menuPanel;
  private JPanel menuTopPanel;
  private Button bookListButton;
  private JLabel statusLabel;
  private JLabel welcomeLabel;
  private Button importListButton;

  public SalesmanFrame() {
    initFrame();
    initComponents();
    handleEvent();
  }

  private void initFrame() {
    setPreferredSize(new Dimension(1200, 700));
    setVisible(true);
    pack();
    setLocationRelativeTo(null);
  }

  private void initComponents() {
    headerPanel = new JPanel();
    welcomeLabel = new JLabel();
    statusLabel = new JLabel();
    cartButton = new Button("Cart");
    menuPanel = new JPanel();
    menuTopPanel = new JPanel();
    customerListButton = new Button("Customer List");
    pendingOrderButton = new Button("Pending Order");
    bookListButton = new Button("Book List");
    importListButton = new Button("Import List");
    menuBottomPanel = new JPanel();
    accountButton = new Button("Account settings");
    contactButton = new Button("Contact us");
    aboutUsButton = new Button("About us");
    logoutButton = new Button("Logout");
    contentPanel = new JPanel();

    contentPanel.add(new CustomerList());

    headerPanel.setAlignmentX(1.0F);
    headerPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    headerPanel.setDoubleBuffered(false);
    headerPanel.setMaximumSize(new java.awt.Dimension(832, 80));
    headerPanel.setMinimumSize(new java.awt.Dimension(413, 40));
    headerPanel.setPreferredSize(new java.awt.Dimension(672, 80));

    welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    welcomeLabel.setText("Welcome, ");
    welcomeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    welcomeLabel.setMaximumSize(new java.awt.Dimension(380, 16));
    welcomeLabel.setMinimumSize(new java.awt.Dimension(300, 16));
    welcomeLabel.setPreferredSize(new java.awt.Dimension(300, 30));
    
    headerPanel.add(welcomeLabel);

    statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    statusLabel.setText("Status : ");
    statusLabel.setMaximumSize(new java.awt.Dimension(500, 16));
    statusLabel.setPreferredSize(new java.awt.Dimension(300, 16));
    headerPanel.add(statusLabel);

    
    headerPanel.add(cartButton);

    getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

    menuPanel.setPreferredSize(new java.awt.Dimension(200, 399));
    menuPanel.setLayout(new java.awt.GridLayout(2, 1, 0, 150));

    menuTopPanel.setLayout(new java.awt.GridLayout(4, 1, 0, 5));

    
    menuTopPanel.add(customerListButton);

    menuTopPanel.add(pendingOrderButton);

    
    menuTopPanel.add(bookListButton);

   
    menuTopPanel.add(importListButton);

    menuPanel.add(menuTopPanel);

    menuBottomPanel.setLayout(new java.awt.GridLayout(4, 1, 0, 5));

   
    menuBottomPanel.add(accountButton);

  
    menuBottomPanel.add(contactButton);

   
    menuBottomPanel.add(aboutUsButton);

    menuBottomPanel.add(logoutButton);

    menuPanel.add(menuBottomPanel);

    getContentPane().add(menuPanel, java.awt.BorderLayout.LINE_START);

    contentPanel.setLayout(new java.awt.CardLayout());
    getContentPane().add(contentPanel, java.awt.BorderLayout.CENTER);
  }

  private void handleEvent() {
    customerListButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            contentPanel.removeAll();
            contentPanel.add(new CustomerList());
            contentPanel.revalidate();
            contentPanel.repaint();
          }
        });

    pendingOrderButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            contentPanel.removeAll();
            contentPanel.add(new OrderList());
            contentPanel.revalidate();
            contentPanel.repaint();
          }
        });

    bookListButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            contentPanel.removeAll();
            contentPanel.add(new BookList());
            contentPanel.revalidate();
            contentPanel.repaint();
          }
        });

    importListButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            contentPanel.removeAll();
            contentPanel.add(new ImportList());
            contentPanel.revalidate();
            contentPanel.repaint();
          }
        });

    accountButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            contentPanel.removeAll();
            contentPanel.add(new com.bookstore.gui.form.account.views.AccountPanel());
            contentPanel.revalidate();
            contentPanel.repaint();
          }
        });

    logoutButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int option = JOptionPane.showConfirmDialog(
                null,
                "Bạn chắc chắn muốn đăng xuất?",
                "Đăng xuất",
                JOptionPane.OK_OPTION);
            if (option == 0) {
              dispose();
              RegisterUI loginFrame = new RegisterUI();
              loginFrame.setVisible(true);
            }
          }
        });
  }

  public static void main(String args[]) {
    try {
      UIManager.setLookAndFeel(
          "com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      UIManager.put("Panel.background", new Color(250, 250, 250));
    } catch (
        ClassNotFoundException
        | IllegalAccessException
        | InstantiationException
        | UnsupportedLookAndFeelException ignored) {
    }
    FlatMacLightLaf.setup();
    java.awt.EventQueue.invokeLater(
        new Runnable() {
          public void run() {
            new SalesmanFrame();
          }
        });
  }
}
