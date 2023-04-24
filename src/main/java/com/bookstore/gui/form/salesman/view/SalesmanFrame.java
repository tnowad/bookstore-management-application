package com.bookstore.gui.form.salesman.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.form.salesman.view.Account.AccountPanel;
import com.bookstore.gui.main.LoginUI;
import com.bookstore.services.CheckCurrentUser;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.event.*;

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
  // JButton[] buttonsListTop = { homeButton, discoverButton,
  // myOrderButton };

  public SalesmanFrame() {
    initFrame();
    if (new CheckCurrentUser().checkStatus()) {
      initComponents();
      handleEvent();
    } else {
      dispose();
    }
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
    headerPanel.setMaximumSize(new java.awt.Dimension(832, 40));
    headerPanel.setMinimumSize(new java.awt.Dimension(413, 40));
    headerPanel.setPreferredSize(new java.awt.Dimension(672, 40));

    welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
    welcomeLabel.setText("Welcome, ");
    welcomeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    welcomeLabel.setMaximumSize(new java.awt.Dimension(380, 16));
    welcomeLabel.setMinimumSize(new java.awt.Dimension(300, 16));
    welcomeLabel.setPreferredSize(new java.awt.Dimension(300, 30));
    welcomeLabel.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        welcomeLabelKeyPressed(evt);
      }
    });
    headerPanel.add(welcomeLabel);

    statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
    statusLabel.setText("Status : ");
    statusLabel.setMaximumSize(new java.awt.Dimension(500, 16));
    statusLabel.setPreferredSize(new java.awt.Dimension(300, 16));
    headerPanel.add(statusLabel);

    cartButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cartButtonActionPerformed(evt);
      }
    });
    headerPanel.add(cartButton);

    getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

    menuPanel.setPreferredSize(new java.awt.Dimension(120, 399));
    menuPanel.setLayout(new java.awt.GridLayout(2, 1, 0, 150));

    menuTopPanel.setLayout(new java.awt.GridLayout(4, 1, 0, 5));

    customerListButton.setMaximumSize(new java.awt.Dimension(120, 30));
    customerListButton.setMinimumSize(new java.awt.Dimension(80, 30));
    customerListButton.setPreferredSize(new java.awt.Dimension(120, 30));
    customerListButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        homeButtonActionPerformed(evt);
      }
    });
    menuTopPanel.add(customerListButton);

    pendingOrderButton.setMaximumSize(new java.awt.Dimension(120, 30));
    pendingOrderButton.setMinimumSize(new java.awt.Dimension(120, 30));
    pendingOrderButton.setPreferredSize(new java.awt.Dimension(120, 30));
    menuTopPanel.add(pendingOrderButton);

    bookListButton.setMaximumSize(new java.awt.Dimension(120, 30));
    bookListButton.setMinimumSize(new java.awt.Dimension(120, 30));
    bookListButton.setPreferredSize(new java.awt.Dimension(120, 30));
    bookListButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        myOrderButtonActionPerformed(evt);
      }
    });
    menuTopPanel.add(bookListButton);

    importListButton.setMaximumSize(new java.awt.Dimension(120, 30));
    importListButton.setMinimumSize(new java.awt.Dimension(120, 30));
    importListButton.setPreferredSize(new java.awt.Dimension(120, 30));
    importListButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        importListButtonActionPerformed(evt);
      }
    });
    menuTopPanel.add(importListButton);

    menuPanel.add(menuTopPanel);

    menuBottomPanel.setLayout(new java.awt.GridLayout(4, 1, 0, 5));

    accountButton.setMaximumSize(new java.awt.Dimension(120, 30));
    accountButton.setMinimumSize(new java.awt.Dimension(120, 30));
    accountButton.setPreferredSize(new java.awt.Dimension(120, 30));
    accountButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        accountSettingButtonActionPerformed(evt);
      }
    });
    menuBottomPanel.add(accountButton);

    contactButton.setMaximumSize(new java.awt.Dimension(120, 30));
    contactButton.setMinimumSize(new java.awt.Dimension(120, 30));
    contactButton.setPreferredSize(new java.awt.Dimension(120, 30));
    contactButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        contactSupportButtonActionPerformed(evt);
      }
    });
    menuBottomPanel.add(contactButton);

    aboutUsButton.setMaximumSize(new java.awt.Dimension(120, 30));
    aboutUsButton.setMinimumSize(new java.awt.Dimension(120, 30));
    aboutUsButton.setPreferredSize(new java.awt.Dimension(120, 30));
    aboutUsButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        aboutUsButtonActionPerformed(evt);
      }
    });
    menuBottomPanel.add(aboutUsButton);

    logoutButton.setMaximumSize(new java.awt.Dimension(120, 30));
    logoutButton.setMinimumSize(new java.awt.Dimension(120, 30));
    logoutButton.setPreferredSize(new java.awt.Dimension(120, 30));
    logoutButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        logoutButtonActionPerformed(evt);
      }
    });
    menuBottomPanel.add(logoutButton);

    menuPanel.add(menuBottomPanel);

    getContentPane().add(menuPanel, java.awt.BorderLayout.LINE_START);

    contentPanel.setLayout(new java.awt.CardLayout());
    getContentPane().add(contentPanel, java.awt.BorderLayout.CENTER);

  }

  private void handleEvent() {
    customerListButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentPanel.removeAll();
        contentPanel.add(new CustomerList());
        contentPanel.revalidate();
        contentPanel.repaint();
      }

    });

    pendingOrderButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentPanel.removeAll();
        contentPanel.add(new PendingOrderPanel());
        contentPanel.revalidate();
        contentPanel.repaint();
      }
    });

    bookListButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentPanel.removeAll();
        contentPanel.add(new BookList());
        contentPanel.revalidate();
        contentPanel.repaint();
      }
    });

    importListButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentPanel.removeAll();
        contentPanel.add(new ImportListPanel());
        contentPanel.revalidate();
        contentPanel.repaint();
      }
    });

    accountButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentPanel.removeAll();
        contentPanel.add(new AccountPanel());
        contentPanel.revalidate();
        contentPanel.repaint();
      }
    });

    logoutButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn đăng xuất?", "Đăng xuất",
            JOptionPane.OK_OPTION);
        if (option == 0) {
          dispose();
          new CheckCurrentUser().setCurrentUserId(0);
          LoginUI loginFrame = new LoginUI();
          loginFrame.setVisible(true);
        }
      }
    });
  }

  private void cartButtonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void myOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void importListButtonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void accountSettingButtonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void contactSupportButtonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void aboutUsButtonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void welcomeLabelKeyPressed(java.awt.event.KeyEvent evt) {
  }

  public static void main(String args[]) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      UIManager.put("Panel.background", new Color(250, 250, 250));
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
        | UnsupportedLookAndFeelException ignored) {
    }
    FlatMacLightLaf.setup();
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new SalesmanFrame();
      }
    });
  }

}
