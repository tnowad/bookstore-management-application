package com.bookstore.gui.form.salesman.view;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.form.salesman.view.Account.AccountPanel;
import com.bookstore.gui.main.LoginUI;
import com.bookstore.services.CheckCurrentUser;

import java.awt.event.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class SalesmanFrame extends JFrame {

  private Button logoutButton;
  private Button customerListButton;
  private Button pendingOrderButton;
  private Button importButton;
  private Button aboutUsButton;
  private Button contactButton;
  private Button accountButton;
  private Button bookListButton;

  private JPanel container;
  private JPanel contentCustomerList;
  private JScrollPane jScrollPane1;

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

    container = new JPanel();
    logoutButton = new Button("Logout");
    customerListButton = new Button("Customer List");
    pendingOrderButton = new Button("Pending Order List");
    importButton = new Button("Import");
    aboutUsButton = new Button("About Us");
    contactButton = new Button("Contact Us");
    accountButton = new Button("Account");
    bookListButton = new Button("Book List");
    jScrollPane1 = new JScrollPane();
    contentCustomerList = new JPanel();

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        exitForm(evt);
      }
    });

    container.setLayout(null);

    container.add(logoutButton);
    logoutButton.setBounds(0, 540, 160, 50);
    customerListButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        customerListButtonActionPerformed(evt);
      }
    });
    container.add(customerListButton);
    customerListButton.setBounds(0, 30, 160, 50);
    // customerListButton.getAccessibleContext().setAccessibleName("Customer list");
    // customerListButton.getAccessibleContext().setAccessibleDescription("");

    container.add(pendingOrderButton);
    pendingOrderButton.setBounds(0, 80, 160, 50);

    container.add(importButton);
    importButton.setBounds(0, 180, 160, 50);

    container.add(aboutUsButton);
    aboutUsButton.setBounds(0, 440, 160, 50);

    container.add(contactButton);
    contactButton.setBounds(0, 490, 160, 50);

    container.add(accountButton);
    accountButton.setBounds(0, 390, 160, 50);

    container.add(bookListButton);
    bookListButton.setBounds(0, 130, 160, 50);

    contentCustomerList.add(new CustomerListPanel());

    jScrollPane1.setViewportView(contentCustomerList);
    contentCustomerList.setPreferredSize(new Dimension(900, 0));

    container.add(jScrollPane1);
    jScrollPane1.setBounds(160, 70, 980, 580);

    add(container, java.awt.BorderLayout.CENTER);

  }

  private void handleEvent() {
    customerListButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentCustomerList.removeAll();
        contentCustomerList.add(new CustomerListPanel());
        contentCustomerList.revalidate();
        contentCustomerList.repaint();
      }

    });

    pendingOrderButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentCustomerList.removeAll();
        contentCustomerList.add(new PendingOrderPanel());
        contentCustomerList.revalidate();
        contentCustomerList.repaint();
      }
    });

    bookListButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentCustomerList.removeAll();
        contentCustomerList.add(new BookListPanel());
        contentCustomerList.revalidate();
        contentCustomerList.repaint();
      }
    });

    importButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentCustomerList.removeAll();
        contentCustomerList.add(new ImportListPanel());
        contentCustomerList.revalidate();
        contentCustomerList.repaint();
      }
    });

    accountButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentCustomerList.removeAll();
        contentCustomerList.add(new AccountPanel());
        contentCustomerList.revalidate();
        contentCustomerList.repaint();
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

  private void exitForm(WindowEvent evt) {
    System.exit(0);
  }

  private void customerListButtonActionPerformed(ActionEvent evt) {

  }

  private void jTextField1ActionPerformed(ActionEvent evt) {

  }

  public static void main(String args[]) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      UIManager.put("Panel.background", new Color(250, 250, 250));
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
        | UnsupportedLookAndFeelException ignored) {
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new SalesmanFrame();
      }
    });
  }

}
