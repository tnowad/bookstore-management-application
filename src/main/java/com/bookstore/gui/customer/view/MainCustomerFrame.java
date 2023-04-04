/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bookstore.gui.customer.view;

import javax.swing.*;

import com.bookstore.gui.customer.formCustomer.BrowseProductPanel;
import com.bookstore.gui.customer.formCustomer.CartPanel;
import com.bookstore.gui.customer.formCustomer.CategoryPanel;
import com.bookstore.gui.main.LoginUI;

import java.awt.*;

/**
 *
 * @author Danh
 */
public class MainCustomerFrame extends javax.swing.JFrame {

  /**
   * Creates new form MainCustomerUI
   */
  public MainCustomerFrame() {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    logoPanel = new javax.swing.JPanel();
    searchPanel = new javax.swing.JPanel();
    displayingItemsPanel = new javax.swing.JPanel();
    menuPanel = new javax.swing.JPanel();
    categoriesBtn = new javax.swing.JButton();
    homeBtn = new javax.swing.JButton();
    discoverBtn = new javax.swing.JButton();
    logOutBtn = new javax.swing.JButton();
    accountBtn = new javax.swing.JButton();
    aboutUsBtn = new javax.swing.JButton();
    contactSupportBtn = new javax.swing.JButton();
    jLabel1 = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    welcomeUserTxtFld = new javax.swing.JTextField();
    getUserStatusTxtFld = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    cartButton = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Home");
    setBackground(new java.awt.Color(255, 255, 255));

    javax.swing.GroupLayout logoPanelLayout = new javax.swing.GroupLayout(logoPanel);
    logoPanel.setLayout(logoPanelLayout);
    logoPanelLayout.setHorizontalGroup(
        logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
    logoPanelLayout.setVerticalGroup(
        logoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));

    javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
    searchPanel.setLayout(searchPanelLayout);
    searchPanelLayout.setHorizontalGroup(
        searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
    searchPanelLayout.setVerticalGroup(
        searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 582, Short.MAX_VALUE));

    displayingItemsPanel.setLayout(new java.awt.CardLayout());

    categoriesBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    categoriesBtn.setIcon(new javax.swing.ImageIcon("/icon/categories.png")); // NOI18N
    categoriesBtn.setText("Categories");
    categoriesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    categoriesBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        categoriesBtnActionPerformed(evt);
      }
    });

    homeBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    homeBtn.setIcon(new javax.swing.ImageIcon("/icon/home.png")); // NOI18N
    homeBtn.setText("Home");
    homeBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    homeBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        homeBtnActionPerformed(evt);
      }
    });

    discoverBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    discoverBtn.setIcon(new javax.swing.ImageIcon("/icon/search.png")); // NOI18N
    discoverBtn.setText("Discover");
    discoverBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    discoverBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        discoverBtnActionPerformed(evt);
      }
    });

    logOutBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    logOutBtn.setIcon(new javax.swing.ImageIcon("/icon/logout.png")); // NOI18N
    logOutBtn.setText("Log out");
    logOutBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    logOutBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        logOutBtnActionPerformed(evt);
      }
    });

    accountBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    accountBtn.setIcon(new javax.swing.ImageIcon("/icon/user.png")); // NOI18N
    accountBtn.setText("Account");
    accountBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    accountBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        accountBtnActionPerformed(evt);
      }
    });

    aboutUsBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    aboutUsBtn.setIcon(new javax.swing.ImageIcon("/icon/info.png")); // NOI18N
    aboutUsBtn.setText("About Us");
    aboutUsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

    contactSupportBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    contactSupportBtn.setIcon(new javax.swing.ImageIcon("/icon/chat.png")); // NOI18N
    contactSupportBtn.setText("Contact Support");
    contactSupportBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

    javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
    menuPanel.setLayout(menuPanelLayout);
    menuPanelLayout.setHorizontalGroup(
        menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(aboutUsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
            .addComponent(logOutBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(categoriesBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(discoverBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(homeBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(contactSupportBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
            .addComponent(accountBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE));
    menuPanelLayout.setVerticalGroup(
        menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(homeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(discoverBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(categoriesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 177, Short.MAX_VALUE)
                .addComponent(accountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(contactSupportBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(aboutUsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));

    jLabel1.setIcon(new javax.swing.ImageIcon("/icon/books-piled-.png")); // NOI18N

    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel3.setText("Welcome,");

    welcomeUserTxtFld.setEditable(false);
    welcomeUserTxtFld.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

    getUserStatusTxtFld.setEditable(false);

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel2.setText("Status:");

    cartButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    cartButton.setIcon(new javax.swing.ImageIcon("/icon/shopping-cart.png")); // NOI18N
    cartButton.setText("Cart");
    cartButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        cartButtonActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(welcomeUserTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 445,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 53,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getUserStatusTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cartButton)
                .addContainerGap(37, Short.MAX_VALUE)));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(getUserStatusTxtFld)
                .addComponent(cartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
            .addComponent(welcomeUserTxtFld)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addGap(70, 70, 70)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(displayingItemsPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(logoPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(searchPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(displayingItemsPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap()));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void categoriesBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_categoriesBtnActionPerformed
    System.out.println("Button " + evt.getActionCommand() + " has been clicked.");
    displayingItemsPanel.removeAll();
    CategoryPanel categoryPanel = new CategoryPanel();
    displayingItemsPanel.add(categoryPanel);
    categoryPanel.setSize(displayingItemsPanel.getSize());
    displayingItemsPanel.setLayout(new CardLayout());
    displayingItemsPanel.setVisible(true);
    displayingItemsPanel.revalidate();
    displayingItemsPanel.repaint();
    // CardLayout cardLayout = (CardLayout) displayingItemsPanel.getLayout();
    // cardLayout.show(displayingItemsPanel, "categoryPanel");
  }// GEN-LAST:event_categoriesBtnActionPerformed

  private void homeBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_homeBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_homeBtnActionPerformed

  private void discoverBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_discoverBtnActionPerformed
    System.out.println("Button " + evt.getActionCommand() + " has been clicked.");
    displayingItemsPanel.removeAll();
    BrowseProductPanel browseProductUI = new BrowseProductPanel();
    displayingItemsPanel.add(browseProductUI);
    browseProductUI.setSize(displayingItemsPanel.getSize());
    displayingItemsPanel.setLayout(new CardLayout());
    displayingItemsPanel.setVisible(true);
    displayingItemsPanel.revalidate();
    displayingItemsPanel.repaint();
  }// GEN-LAST:event_discoverBtnActionPerformed

  private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logOutBtnActionPerformed
    int choice = JOptionPane.showConfirmDialog(null, "Do you want to log out?");
    if (choice == JOptionPane.YES_OPTION) {
      this.dispose();
      // LoginUI loginUI = new LoginUI();
      // loginUI.setVisible(true);
    }
  }// GEN-LAST:event_logOutBtnActionPerformed

  private void accountBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_accountBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_accountBtnActionPerformed

  private void cartButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cartButtonActionPerformed
    System.out.println("Button " + evt.getActionCommand() + " has been clicked.");
    displayingItemsPanel.removeAll();
    CartPanel cartPanel = new CartPanel();
    displayingItemsPanel.add(cartPanel);
    cartPanel.setSize(displayingItemsPanel.getSize());
    displayingItemsPanel.setLayout(new CardLayout());
    displayingItemsPanel.setVisible(true);
    displayingItemsPanel.revalidate();
    displayingItemsPanel.repaint();
  }// GEN-LAST:event_cartButtonActionPerformed

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
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
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(MainCustomerFrame.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    }
    // </editor-fold>
    // </editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(() -> new MainCustomerFrame().setVisible(true));
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton aboutUsBtn;
  private javax.swing.JButton accountBtn;
  private javax.swing.JButton cartButton;
  private javax.swing.JButton categoriesBtn;
  private javax.swing.JButton contactSupportBtn;
  private javax.swing.JButton discoverBtn;
  private javax.swing.JPanel displayingItemsPanel;
  private javax.swing.JTextField getUserStatusTxtFld;
  private javax.swing.JButton homeBtn;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JButton logOutBtn;
  private javax.swing.JPanel logoPanel;
  private javax.swing.JPanel menuPanel;
  private javax.swing.JPanel searchPanel;
  private javax.swing.JTextField welcomeUserTxtFld;
  // End of variables declaration//GEN-END:variables
}
