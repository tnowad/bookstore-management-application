package com.bookstore.gui.form.salesman.view;

import java.awt.Color;
// import java.awt.Dimension;
// import javax.swing.*;

// import com.bookstore.gui.component.button.Button;
// import com.bookstore.gui.form.salesman.view.Account.AccountPanel;
// import com.bookstore.gui.main.LoginUI;
// import com.bookstore.services.CheckCurrentUser;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

// import java.awt.event.*;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// public class SalesmanFrame extends JFrame {

//   private Button logoutButton;
//   private Button customerListButton;
//   private Button pendingOrderButton;
//   private Button importButton;
//   private Button aboutUsButton;
//   private Button contactButton;
//   private Button accountButton;
//   private Button bookListButton;

//   private JPanel container;
//   private JPanel contentCustomerList;
//   private JScrollPane jScrollPane1;

//   public SalesmanFrame() {
//     initFrame();
//     if (new CheckCurrentUser().checkStatus()) {
//       initComponents();
//       handleEvent();
//     } else {
//       dispose();
//     }
//   }

//   private void initFrame() {
//     setPreferredSize(new Dimension(1200, 700));
//     setVisible(true);
//     pack();
//     setLocationRelativeTo(null);
//   }

//   private void initComponents() {

//     container = new JPanel();
//     logoutButton = new Button("Logout");
//     customerListButton = new Button("Customer List");
//     pendingOrderButton = new Button("Pending Order List");
//     importButton = new Button("Import");
//     aboutUsButton = new Button("About Us");
//     contactButton = new Button("Contact Us");
//     accountButton = new Button("Account");
//     bookListButton = new Button("Book List");
//     jScrollPane1 = new JScrollPane();
//     contentCustomerList = new JPanel();

//     addWindowListener(new WindowAdapter() {
//       public void windowClosing(WindowEvent evt) {
//         exitForm(evt);
//       }
//     });

//     container.setLayout(null);

//     container.add(logoutButton);
//     logoutButton.setBounds(0, 540, 160, 50);
//     customerListButton.addActionListener(new ActionListener() {
//       public void actionPerformed(ActionEvent evt) {
//         customerListButtonActionPerformed(evt);
//       }
//     });
//     container.add(customerListButton);
//     customerListButton.setBounds(0, 30, 160, 50);
//     // customerListButton.getAccessibleContext().setAccessibleName("Customer list");
//     // customerListButton.getAccessibleContext().setAccessibleDescription("");

//     container.add(pendingOrderButton);
//     pendingOrderButton.setBounds(0, 80, 160, 50);

//     container.add(importButton);
//     importButton.setBounds(0, 180, 160, 50);

//     container.add(aboutUsButton);
//     aboutUsButton.setBounds(0, 440, 160, 50);

//     container.add(contactButton);
//     contactButton.setBounds(0, 490, 160, 50);

//     container.add(accountButton);
//     accountButton.setBounds(0, 390, 160, 50);

//     container.add(bookListButton);
//     bookListButton.setBounds(0, 130, 160, 50);

//     contentCustomerList.add(new CustomerListPanel());

//     jScrollPane1.setViewportView(contentCustomerList);
//     contentCustomerList.setPreferredSize(new Dimension(900, 0));

//     container.add(jScrollPane1);
//     jScrollPane1.setBounds(160, 70, 980, 580);

//     add(container, java.awt.BorderLayout.CENTER);

//   }

//   private void handleEvent() {
//     customerListButton.addActionListener(new ActionListener() {

//       @Override
//       public void actionPerformed(ActionEvent e) {
//         contentCustomerList.removeAll();
//         contentCustomerList.add(new CustomerListPanel());
//         contentCustomerList.revalidate();
//         contentCustomerList.repaint();
//       }

//     });

//     pendingOrderButton.addActionListener(new ActionListener() {

//       @Override
//       public void actionPerformed(ActionEvent e) {
//         contentCustomerList.removeAll();
//         contentCustomerList.add(new PendingOrderPanel());
//         contentCustomerList.revalidate();
//         contentCustomerList.repaint();
//       }
//     });

//     bookListButton.addActionListener(new ActionListener() {

//       @Override
//       public void actionPerformed(ActionEvent e) {
//         contentCustomerList.removeAll();
//         contentCustomerList.add(new BookListPanel());
//         contentCustomerList.revalidate();
//         contentCustomerList.repaint();
//       }
//     });

//     importButton.addActionListener(new ActionListener() {

//       @Override
//       public void actionPerformed(ActionEvent e) {
//         contentCustomerList.removeAll();
//         contentCustomerList.add(new ImportListPanel());
//         contentCustomerList.revalidate();
//         contentCustomerList.repaint();
//       }
//     });

//     accountButton.addActionListener(new ActionListener() {

//       @Override
//       public void actionPerformed(ActionEvent e) {
//         contentCustomerList.removeAll();
//         contentCustomerList.add(new AccountPanel());
//         contentCustomerList.revalidate();
//         contentCustomerList.repaint();
//       }
//     });

//     logoutButton.addActionListener(new ActionListener() {

//       @Override
//       public void actionPerformed(ActionEvent e) {
//         int option = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn đăng xuất?", "Đăng xuất",
//             JOptionPane.OK_OPTION);

//         if (option == 0) {
//           dispose();
//           new CheckCurrentUser().setCurrentUserId(0);
//           LoginUI loginFrame = new LoginUI();
//           loginFrame.setVisible(true);
//         }
//       }
//     });
//   }

//   private void exitForm(WindowEvent evt) {
//     System.exit(0);
//   }

//   private void customerListButtonActionPerformed(ActionEvent evt) {

//   }

//   private void jTextField1ActionPerformed(ActionEvent evt) {

//   }

//   public static void main(String args[]) {
//     try {
//       UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//       UIManager.put("Panel.background", new Color(250, 250, 250));
//     } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
//         | UnsupportedLookAndFeelException ignored) {
//     }
//     FlatMacLightLaf.setup();
//     java.awt.EventQueue.invokeLater(new Runnable() {
//       public void run() {
//         new SalesmanFrame();
//       }
//     });
//   }

// }

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Bao
 */
public class SalesmanFrame extends javax.swing.JFrame {

  /**
   * Creates new form MainCustomer
   */
  public SalesmanFrame() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    headerPanel = new javax.swing.JPanel();
    welcomeLabel = new javax.swing.JLabel();
    statusLabel = new javax.swing.JLabel();
    cartButton = new javax.swing.JButton();
    menuPanel = new javax.swing.JPanel();
    menuTopPanel = new javax.swing.JPanel();
    homeButton = new javax.swing.JButton();
    discoverButton = new javax.swing.JButton();
    myOrderButton = new javax.swing.JButton();
    menuBottomPanel = new javax.swing.JPanel();
    accountSettingButton = new javax.swing.JButton();
    contactSupportButton = new javax.swing.JButton();
    aboutUsButton = new javax.swing.JButton();
    logoutButton = new javax.swing.JButton();
    contentPanel = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setMinimumSize(new java.awt.Dimension(1280, 720));
    setPreferredSize(new java.awt.Dimension(1280, 720));

    headerPanel.setAlignmentX(1.0F);
    headerPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
    headerPanel.setDoubleBuffered(false);
    headerPanel.setMaximumSize(new java.awt.Dimension(832, 40));
    headerPanel.setMinimumSize(new java.awt.Dimension(413, 40));
    headerPanel.setPreferredSize(new java.awt.Dimension(672, 40));

    welcomeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    welcomeLabel.setText("Welcome, ");
    welcomeLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
    welcomeLabel.setMaximumSize(new java.awt.Dimension(380, 16));
    welcomeLabel.setMinimumSize(new java.awt.Dimension(300, 16));
    welcomeLabel.setPreferredSize(new java.awt.Dimension(300, 30));
    welcomeLabel.addKeyListener(new java.awt.event.KeyAdapter() {
      public void keyPressed(java.awt.event.KeyEvent evt) {
        welcomeLabelKeyPressed(evt);
      }
    });
    headerPanel.add(welcomeLabel);

    statusLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    statusLabel.setText("Status : ");
    statusLabel.setMaximumSize(new java.awt.Dimension(500, 16));
    statusLabel.setPreferredSize(new java.awt.Dimension(300, 16));
    headerPanel.add(statusLabel);

    cartButton.setText("Cart");
    cartButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cartButtonActionPerformed(evt);
      }
    });
    headerPanel.add(cartButton);

    getContentPane().add(headerPanel, java.awt.BorderLayout.PAGE_START);

    menuPanel.setPreferredSize(new java.awt.Dimension(120, 399));
    menuPanel.setLayout(new java.awt.GridLayout(2, 1, 0, 150));

    menuTopPanel.setLayout(new java.awt.GridLayout(3, 1, 0, 5));

    homeButton.setText("Home");
    homeButton.setMaximumSize(new java.awt.Dimension(120, 30));
    homeButton.setMinimumSize(new java.awt.Dimension(80, 30));
    homeButton.setPreferredSize(new java.awt.Dimension(120, 30));
    homeButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        homeButtonActionPerformed(evt);
      }
    });
    menuTopPanel.add(homeButton);

    discoverButton.setText("Discover");
    discoverButton.setMaximumSize(new java.awt.Dimension(120, 30));
    discoverButton.setMinimumSize(new java.awt.Dimension(120, 30));
    discoverButton.setPreferredSize(new java.awt.Dimension(120, 30));
    menuTopPanel.add(discoverButton);

    myOrderButton.setText("My order");
    myOrderButton.setMaximumSize(new java.awt.Dimension(120, 30));
    myOrderButton.setMinimumSize(new java.awt.Dimension(120, 30));
    myOrderButton.setPreferredSize(new java.awt.Dimension(120, 30));
    myOrderButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        myOrderButtonActionPerformed(evt);
      }
    });
    menuTopPanel.add(myOrderButton);

    menuPanel.add(menuTopPanel);

    menuBottomPanel.setLayout(new java.awt.GridLayout(4, 1, 0, 5));

    accountSettingButton.setText("Account Setting");
    accountSettingButton.setMaximumSize(new java.awt.Dimension(120, 30));
    accountSettingButton.setMinimumSize(new java.awt.Dimension(120, 30));
    accountSettingButton.setPreferredSize(new java.awt.Dimension(120, 30));
    accountSettingButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        accountSettingButtonActionPerformed(evt);
      }
    });
    menuBottomPanel.add(accountSettingButton);

    contactSupportButton.setText("Contact Support");
    contactSupportButton.setMaximumSize(new java.awt.Dimension(120, 30));
    contactSupportButton.setMinimumSize(new java.awt.Dimension(120, 30));
    contactSupportButton.setPreferredSize(new java.awt.Dimension(120, 30));
    contactSupportButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        contactSupportButtonActionPerformed(evt);
      }
    });
    menuBottomPanel.add(contactSupportButton);

    aboutUsButton.setText("About Us");
    aboutUsButton.setMaximumSize(new java.awt.Dimension(120, 30));
    aboutUsButton.setMinimumSize(new java.awt.Dimension(120, 30));
    aboutUsButton.setPreferredSize(new java.awt.Dimension(120, 30));
    aboutUsButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        aboutUsButtonActionPerformed(evt);
      }
    });
    menuBottomPanel.add(aboutUsButton);

    logoutButton.setText("Logout");
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

    pack();
  }// </editor-fold>

  private void cartButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void homeButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void myOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void accountSettingButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void contactSupportButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void aboutUsButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void welcomeLabelKeyPressed(java.awt.event.KeyEvent evt) {
    // TODO add your handling code here:
  }

  /**
   * @param args the command line arguments
   */
  public static void main(String args[]) {
    /* Set the Nimbus look and feel */
    // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
    // (optional) ">
    /*
     * If Nimbus (introduced in Java SE 6) is not available, stay with the default
     * look and feel.
     * For details see
     * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
     */
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      UIManager.put("Panel.background", new Color(250, 250, 250));
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
        | UnsupportedLookAndFeelException ignored) {
    }
    FlatMacLightLaf.setup();
    // try {
    //   for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
    //     if ("Nimbus".equals(info.getName())) {
    //       javax.swing.UIManager.setLookAndFeel(info.getClassName());
    //       break;
    //     }
    //   }
    // } catch (ClassNotFoundException ex) {
    //   java.util.logging.Logger.getLogger(SalesmanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
    //       ex);
    // } catch (InstantiationException ex) {
    //   java.util.logging.Logger.getLogger(SalesmanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
    //       ex);
    // } catch (IllegalAccessException ex) {
    //   java.util.logging.Logger.getLogger(SalesmanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
    //       ex);
    // } catch (javax.swing.UnsupportedLookAndFeelException ex) {
    //   java.util.logging.Logger.getLogger(SalesmanFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
    //       ex);
    // }
    // </editor-fold>
    // </editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new SalesmanFrame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify
  private javax.swing.JButton aboutUsButton;
  private javax.swing.JButton accountSettingButton;
  private javax.swing.JButton cartButton;
  private javax.swing.JButton contactSupportButton;
  private javax.swing.JPanel contentPanel;
  private javax.swing.JButton discoverButton;
  private javax.swing.JPanel headerPanel;
  private javax.swing.JButton homeButton;
  private javax.swing.JButton logoutButton;
  private javax.swing.JPanel menuBottomPanel;
  private javax.swing.JPanel menuPanel;
  private javax.swing.JPanel menuTopPanel;
  private javax.swing.JButton myOrderButton;
  private javax.swing.JLabel statusLabel;
  private javax.swing.JLabel welcomeLabel;
  // End of variables declaration
}
