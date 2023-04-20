package com.bookstore.gui.form.manager.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.commons.lang3.ObjectUtils.Null;

import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.form.salesman.view.*;
import com.bookstore.gui.form.salesman.view.Account.AccountPanel;
import com.bookstore.gui.main.LoginUI;

public class managerFrame extends JFrame {
  private JPanel grHeader;
  private JPanel grMenu;
  private JPanel grContent;

  public managerFrame() {
    initFrame();
    initComponents();
    // setBackground();
    handleEvent();
  }

  private void initFrame() {
    setPreferredSize(new Dimension(1200, 700));
    setVisible(true);
    pack();
    setLocationRelativeTo(null);
  }

  private void initComponents() {

    grMenu = new JPanel();

    logoutButton = new Button("Logout");
    customerListButton = new Button("Customer List");
    pendingOrderButton = new Button("Pending Order List");
    importButton = new Button("Import");
    aboutUsButton = new Button("About Us");
    contactButton = new Button("Contact Us");
    accountButton = new Button("Account");
    bookListButton = new Button("Book List");
    salesmanListButton = new Button("Salesman List");
    jScrollPane1 = new JScrollPane();
    contentCustomerList = new JPanel();

    grMenu.setLayout(null);

    bookListButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        customerListButtonActionPerformed(evt);
      }
    });

    grMenu.add(bookListButton);
    bookListButton.setBounds(0, 30, 160, 50);

    grMenu.add(customerListButton);
    customerListButton.setBounds(0, 80, 160, 50);

    grMenu.add(customerListButton);
    pendingOrderButton.setBounds(0, 130, 160, 50);

    grMenu.add(pendingOrderButton);
    pendingOrderButton.setBounds(0, 180, 160, 50);

    grMenu.add(importButton);
    importButton.setBounds(0, 230, 160, 50);

    grMenu.add(accountButton);
    accountButton.setBounds(0, 440, 160, 50);

    grMenu.add(aboutUsButton);
    aboutUsButton.setBounds(0, 490, 160, 50);

    grMenu.add(contactButton);
    contactButton.setBounds(0, 540, 160, 50);

    grMenu.add(logoutButton);
    logoutButton.setBounds(0, 590, 160, 50);

    contentCustomerList.add(new BookListPanel());
    jScrollPane1.setViewportView(contentCustomerList);
    contentCustomerList.setPreferredSize(new Dimension(900, 0));

    grMenu.add(jScrollPane1);
    jScrollPane1.setBounds(160, 70, 980, 580);

    add(grMenu, java.awt.BorderLayout.CENTER);
  }

  private void handleEvent() {

    bookListButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentCustomerList.removeAll();
        contentCustomerList.add(new BookListPanel());
        contentCustomerList.revalidate();
        contentCustomerList.repaint();
      }
    });

    customerListButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentCustomerList.removeAll();
        contentCustomerList.add(new CustomerListPanel());
        contentCustomerList.revalidate();
        contentCustomerList.repaint();
      }

    });

    salesmanListButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        //
        //
        //
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

  // private void showConfirm(String message) {
  // JOptionPane.showConfirmDialog(rootPane, message, message, ALLBITS, ABORT)
  // }

  public static void main(String args[]) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      UIManager.put("Panel.background", new Color(250, 250, 250));
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
        | UnsupportedLookAndFeelException ignored) {
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new managerFrame();
      }
    });
  }

  private Button logoutButton;
  private Button customerListButton;
  private Button pendingOrderButton;
  private Button importButton;
  private Button aboutUsButton;
  private Button contactButton;
  private Button accountButton;
  private Button bookListButton;
  private Button salesmanListButton;
  private JPanel contentCustomerList;
  private JScrollPane jScrollPane1;
}
