
package com.bookstore.gui.form.salesman.view;

public class MainEmployeeFrame extends javax.swing.JFrame {

  public MainEmployeeFrame() {
    initComponents();
  }

  private void initComponents() {

    menuPanel = new javax.swing.JPanel();
    createOrderBtn = new javax.swing.JButton();
    pendingOrdersBtn = new javax.swing.JButton();
    customerListBtn = new javax.swing.JButton();
    aboutUsBtn = new javax.swing.JButton();
    bookListBtn = new javax.swing.JButton();
    importListBtn = new javax.swing.JButton();
    logOutBtn = new javax.swing.JButton();
    accountBtn = new javax.swing.JButton();
    jLabel2 = new javax.swing.JLabel();
    getEmployeeRoleTxtFld = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    welcomeUserTxtFld = new javax.swing.JTextField();
    getPanels = new javax.swing.JPanel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Employee - Home");
    setPreferredSize(new java.awt.Dimension(1024, 600));

    createOrderBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    createOrderBtn
        .setIcon(
            new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/create.png"))); // NOI18N
    createOrderBtn.setText("Confirm Order");
    createOrderBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    createOrderBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        createOrderBtnActionPerformed(evt);
      }
    });

    pendingOrdersBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    pendingOrdersBtn
        .setIcon(
            new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/file.png"))); // NOI18N
    pendingOrdersBtn.setText("Pending Orders");
    pendingOrdersBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    pendingOrdersBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        pendingOrdersBtnActionPerformed(evt);
      }
    });

    customerListBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    customerListBtn
        .setIcon(
            new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/customer.png"))); // NOI18N
    customerListBtn.setText("Customer List");
    customerListBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    customerListBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        customerListBtnActionPerformed(evt);
      }
    });

    aboutUsBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    aboutUsBtn.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/info.png"))); // NOI18N
    aboutUsBtn.setText("About Us");
    aboutUsBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);

    bookListBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    bookListBtn
        .setIcon(new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/add.png"))); // NOI18N
    bookListBtn.setText("Book List");
    bookListBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    bookListBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        bookListBtnActionPerformed(evt);
      }
    });

    importListBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    importListBtn
        .setIcon(new javax.swing.ImageIcon(
            getClass().getResource("../../../../../resources/images/clipboard.png"))); // NOI18N
    importListBtn.setText("Import List");
    importListBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    importListBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        importListBtnActionPerformed(evt);
      }
    });

    logOutBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    logOutBtn.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/logout.png"))); // NOI18N
    logOutBtn.setText("Log out");
    logOutBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    logOutBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        logOutBtnActionPerformed(evt);
      }
    });

    accountBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    accountBtn.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/user.png"))); // NOI18N
    accountBtn.setText("Account");
    accountBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    accountBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        accountBtnActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout menuPanelLayout = new javax.swing.GroupLayout(menuPanel);
    menuPanel.setLayout(menuPanelLayout);
    menuPanelLayout.setHorizontalGroup(
        menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(aboutUsBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(customerListBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pendingOrdersBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(createOrderBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bookListBtn, javax.swing.GroupLayout.Alignment.TRAILING,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
            .addComponent(importListBtn, javax.swing.GroupLayout.Alignment.TRAILING,
                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
            .addComponent(logOutBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(accountBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
    menuPanelLayout.setVerticalGroup(
        menuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(customerListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pendingOrdersBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(createOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(importListBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 130,
                    Short.MAX_VALUE)
                .addComponent(accountBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(aboutUsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logOutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel2.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/identity.png"))); // NOI18N
    jLabel2.setText("Role:");

    getEmployeeRoleTxtFld.setEditable(false);
    getEmployeeRoleTxtFld.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    getEmployeeRoleTxtFld.setHorizontalAlignment(javax.swing.JTextField.CENTER);

    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel3.setText("Welcome,");

    welcomeUserTxtFld.setEditable(false);
    welcomeUserTxtFld.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

    javax.swing.GroupLayout getPanelsLayout = new javax.swing.GroupLayout(getPanels);
    getPanels.setLayout(getPanelsLayout);
    getPanelsLayout.setHorizontalGroup(
        getPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 831, Short.MAX_VALUE));
    getPanelsLayout.setVerticalGroup(
        getPanelsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 530, Short.MAX_VALUE));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 175,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 78,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(welcomeUserTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                            474, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getEmployeeRoleTxtFld))
                    .addComponent(getPanels, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(welcomeUserTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 40,
                        Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2)
                    .addComponent(getEmployeeRoleTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(menuPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getPanels, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void createOrderBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_createOrderBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_createOrderBtnActionPerformed

  private void pendingOrdersBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_pendingOrdersBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_pendingOrdersBtnActionPerformed

  private void customerListBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_customerListBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_customerListBtnActionPerformed

  private void logOutBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logOutBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_logOutBtnActionPerformed

  private void bookListBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_bookListBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_bookListBtnActionPerformed

  private void accountBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_accountBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_accountBtnActionPerformed

  private void importListBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_importListBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_importListBtnActionPerformed

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
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(MainEmployeeFrame.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(MainEmployeeFrame.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(MainEmployeeFrame.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(MainEmployeeFrame.class.getName()).log(java.util.logging.Level.SEVERE,
          null, ex);
    }
    // </editor-fold>

    /* Create and display the form */
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new MainEmployeeFrame().setVisible(true);
      }
    });
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton aboutUsBtn;
  private javax.swing.JButton accountBtn;
  private javax.swing.JButton bookListBtn;
  private javax.swing.JButton createOrderBtn;
  private javax.swing.JButton customerListBtn;
  private javax.swing.JTextField getEmployeeRoleTxtFld;
  private javax.swing.JPanel getPanels;
  private javax.swing.JButton importListBtn;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JButton logOutBtn;
  private javax.swing.JPanel menuPanel;
  private javax.swing.JButton pendingOrdersBtn;
  private javax.swing.JTextField welcomeUserTxtFld;
  // End of variables declaration//GEN-END:variables
}
