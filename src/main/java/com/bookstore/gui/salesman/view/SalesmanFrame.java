package com.bookstore.gui.salesman.view;

import java.awt.Dimension;
import javax.swing.*;

import com.bookstore.gui.main.LoginUI;
import com.bookstore.gui.salesman.view.Account.AccountPanel;

import java.awt.event.*;
import java.sql.SQLException;

public class SalesmanFrame extends JFrame {

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

    container = new JPanel();
    logoutButton = new JButton();
    customerListButton = new JButton();
    pendingOrderButton = new JButton();
    importButton = new JButton();
    aboutUsButton = new JButton();
    contactButton = new JButton();
    accountButton = new JButton();
    bookListButton = new JButton();
    jTextField1 = new JTextField();
    // searchButton = new JButton();
    jScrollPane1 = new JScrollPane();
    contentCustomerList = new JPanel();

    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        exitForm(evt);
      }
    });

    container.setLayout(null);

    logoutButton.setText("Logout");
    container.add(logoutButton);
    logoutButton.setBounds(0, 540, 160, 50);

    customerListButton.setText("Customer list");
    customerListButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        customerListButtonActionPerformed(evt);
      }
    });
    container.add(customerListButton);
    customerListButton.setBounds(0, 30, 160, 50);
    customerListButton.getAccessibleContext().setAccessibleName("Customer list");
    customerListButton.getAccessibleContext().setAccessibleDescription("");

    pendingOrderButton.setText("Pending order");
    container.add(pendingOrderButton);
    pendingOrderButton.setBounds(0, 80, 160, 50);

    importButton.setText("Import");
    container.add(importButton);
    importButton.setBounds(0, 180, 160, 50);

    aboutUsButton.setText("About us");
    container.add(aboutUsButton);
    aboutUsButton.setBounds(0, 440, 160, 50);

    contactButton.setText("Contact");
    container.add(contactButton);
    contactButton.setBounds(0, 490, 160, 50);

    accountButton.setText("Account");
    container.add(accountButton);
    accountButton.setBounds(0, 390, 160, 50);

    bookListButton.setText("Book list");
    container.add(bookListButton);
    bookListButton.setBounds(0, 130, 160, 50);

    // jTextField1.addActionListener(new ActionListener() {
    // public void actionPerformed(ActionEvent evt) {
    // jTextField1ActionPerformed(evt);
    // }
    // });
    // container.add(jTextField1);
    // jTextField1.setBounds(370, 10, 340, 50);

    // searchButton.setText("Search");
    // container.add(searchButton);
    // searchButton.setBounds(727, 10, 90, 50);
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
        dispose();
        LoginUI loginFrame = new LoginUI();
        loginFrame.setVisible(true);
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
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new SalesmanFrame();
      }
    });
  }

  private JButton logoutButton;
  private JButton customerListButton;
  private JButton pendingOrderButton;
  private JButton importButton;
  private JButton aboutUsButton;
  private JButton contactButton;
  private JButton accountButton;
  private JButton bookListButton;
  // private JButton searchButton;
  private JPanel container;
  private JPanel contentCustomerList;
  private JScrollPane jScrollPane1;
  private JTextField jTextField1;
}
