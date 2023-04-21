package com.bookstore.gui.form.manager.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.form.salesman.view.*;
import com.bookstore.gui.form.salesman.view.Account.AccountPanel;
import com.bookstore.gui.main.LoginUI;
import com.bookstore.services.CheckCurrentUser;

public class managerFrame extends JFrame {
  private JPanel grHeader;
  private JPanel grMenu;
  private JPanel grMenu1;
  private JPanel grMenu2;
  private JPanel grContent;

  private Button menuButton;
  private Button logoutButton;
  private Button customerListButton;
  private Button pendingOrderButton;
  private Button importButton;
  private Button aboutUsButton;
  private Button contactButton;
  private Button accountButton;
  private Button bookListButton;
  private Button salesmanListButton;
  private JScrollPane jScrollPane1;

  public TimerTask timerTask;

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
    grHeader = new JPanel();
    grContent = new JPanel();

    grMenu1 = new JPanel();
    grMenu2 = new JPanel();

    menuButton = new Button("|||");
    grHeader.setLayout(new FlowLayout(FlowLayout.LEFT));
    grHeader.add(menuButton);
    grHeader.setBackground(Color.BLUE);

    logoutButton = new Button("Logout");
    customerListButton = new Button("Customer List");
    pendingOrderButton = new Button("Pending Order List");
    pendingOrderButton.setPreferredSize(new Dimension(150, 50));
    importButton = new Button("Import");
    aboutUsButton = new Button("About Us");
    contactButton = new Button("Contact Us");
    accountButton = new Button("Account");
    bookListButton = new Button("Book List");
    salesmanListButton = new Button("Salesman List");
    jScrollPane1 = new JScrollPane();

    bookListButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        customerListButtonActionPerformed(evt);
      }
    });

    grMenu1.add(bookListButton);
    grMenu1.add(customerListButton);
    grMenu1.add(salesmanListButton);
    grMenu1.add(pendingOrderButton);
    grMenu1.add(importButton);
    grMenu1.setLayout(new GridLayout(5, 1));

    grMenu2.add(accountButton);
    grMenu2.add(aboutUsButton);
    grMenu2.add(contactButton);
    grMenu2.add(logoutButton);
    grMenu2.setLayout(new GridLayout(4, 1));

    grMenu.setLayout(new BorderLayout());
    grMenu.add(grMenu1, BorderLayout.NORTH);
    grMenu.add(grMenu2, BorderLayout.SOUTH);

    grContent.add(new BookListPanel());
    jScrollPane1.setViewportView(grContent);
    grContent.setPreferredSize(new Dimension(900, 0));

    add(grMenu, java.awt.BorderLayout.WEST);
    grMenu.setVisible(false);
    add(grHeader, java.awt.BorderLayout.NORTH);
    add(grContent, java.awt.BorderLayout.CENTER);
  }

  private void handleEvent() {

    grMenu.addComponentListener(new ComponentListener() {

      @Override
      public void componentResized(ComponentEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'componentResized'");
      }

      @Override
      public void componentMoved(ComponentEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'componentMoved'");
      }

      @Override
      public void componentShown(ComponentEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'componentShown'");
      }

      @Override
      public void componentHidden(ComponentEvent e) {
      }
      
    });

    menuButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
    if (grMenu.isVisible() == false)
    grMenu.setVisible(true);
    else
    grMenu.setVisible(false);
    }
    });
    bookListButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        grContent.removeAll();
        grContent.add(new BookListPanel());
        grContent.revalidate();
        grContent.repaint();
      }
    });

    customerListButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        grContent.removeAll();
        grContent.add(new CustomerListPanel());
        grContent.revalidate();
        grContent.repaint();
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
        grContent.removeAll();
        grContent.add(new PendingOrderPanel());
        grContent.revalidate();
        grContent.repaint();
      }
    });

    importButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        grContent.removeAll();
        grContent.add(new ImportListPanel());
        grContent.revalidate();
        grContent.repaint();
      }
    });

    accountButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        grContent.removeAll();
        grContent.add(new AccountPanel());
        grContent.revalidate();
        grContent.repaint();
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

}
