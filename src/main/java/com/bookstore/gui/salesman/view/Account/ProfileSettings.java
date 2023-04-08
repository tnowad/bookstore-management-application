package com.bookstore.gui.salesman.view.Account;

import javax.swing.JPanel;

public class ProfileSettings extends JPanel {

  public ProfileSettings() {
    initComponents();
  }

  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    getInformationPanel = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    getEmailInEditTxtFld = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    getNameInEditTxtFld = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    getPhoneTxtFld = new javax.swing.JTextField();
    getUserNameTxtFld = new javax.swing.JTextField();
    jLabel14 = new javax.swing.JLabel();
    updateUserInformationBtn = new javax.swing.JButton();
    resetUserInformation = new javax.swing.JButton();
    jLabel7 = new javax.swing.JLabel();
    getCityTxtFld = new javax.swing.JTextField();
    jLabel8 = new javax.swing.JLabel();
    getStreetTxtFld = new javax.swing.JTextField();
    jLabel9 = new javax.swing.JLabel();
    getZipTxtFld = new javax.swing.JTextField();
    jLabel11 = new javax.swing.JLabel();
    jLabel12 = new javax.swing.JLabel();
    getStateTxtFld = new javax.swing.JTextField();
    jLabel15 = new javax.swing.JLabel();
    confirmPasswordFld = new javax.swing.JPasswordField();

    setPreferredSize(new java.awt.Dimension(821, 548));

    jLabel1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
    jLabel1.setText("Edit Your Personal Settings");

    jLabel2.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
    jLabel2.setText("Name");

    jLabel3.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
    jLabel3.setText("Email");

    jLabel4.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
    jLabel4.setText("Phone Number");

    getPhoneTxtFld.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getPhoneTxtFldActionPerformed(evt);
      }
    });

    getUserNameTxtFld.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getUserNameTxtFldActionPerformed(evt);
      }
    });

    jLabel14.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
    jLabel14.setText("Username");

    javax.swing.GroupLayout getInformationPanelLayout = new javax.swing.GroupLayout(getInformationPanel);
    getInformationPanel.setLayout(getInformationPanelLayout);
    getInformationPanelLayout.setHorizontalGroup(
        getInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getInformationPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(
                    getInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(getInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(getNameInEditTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getEmailInEditTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getUserNameTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 300,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(getPhoneTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(106, Short.MAX_VALUE)));
    getInformationPanelLayout.setVerticalGroup(
        getInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(getInformationPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(getInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(getNameInEditTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(getInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addComponent(getUserNameTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(getInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getEmailInEditTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(getInformationPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getPhoneTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(17, 17, 17)));

    updateUserInformationBtn.setBackground(new java.awt.Color(255, 255, 255));
    updateUserInformationBtn.setFont(new java.awt.Font("Comic Sans MS", 1, 14));
    updateUserInformationBtn.setForeground(new java.awt.Color(0, 0, 255));
    updateUserInformationBtn.setText("UPDATE");
    updateUserInformationBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        updateUserInformationBtnActionPerformed(evt);
      }
    });

    resetUserInformation.setBackground(new java.awt.Color(255, 255, 255));
    resetUserInformation.setFont(new java.awt.Font("Comic Sans MS", 1, 14));
    resetUserInformation.setForeground(new java.awt.Color(0, 0, 255));
    resetUserInformation.setText("RESET");
    resetUserInformation.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        resetUserInformationActionPerformed(evt);
      }
    });

    jLabel7.setFont(new java.awt.Font("Comic Sans MS", 0, 14));
    jLabel7.setText("Street");

    jLabel8.setFont(new java.awt.Font("Comic Sans MS", 0, 14));
    jLabel8.setText("City");

    jLabel9.setFont(new java.awt.Font("Comic Sans MS", 0, 14));
    jLabel9.setText("State");

    getZipTxtFld.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getZipTxtFldActionPerformed(evt);
      }
    });

    jLabel11.setFont(new java.awt.Font("Comic Sans MS", 0, 14));
    jLabel11.setText("Confirm Password");

    jLabel12.setFont(new java.awt.Font("Comic Sans MS", 0, 14));
    jLabel12.setText("Update Address Informations");

    getStateTxtFld.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getStateTxtFldActionPerformed(evt);
      }
    });

    jLabel15.setFont(new java.awt.Font("Comic Sans MS", 0, 14));
    jLabel15.setText("Zip");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(147, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel12)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 39,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(getCityTxtFld, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getZipTxtFld, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getStreetTxtFld, javax.swing.GroupLayout.Alignment.LEADING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(getStateTxtFld, javax.swing.GroupLayout.Alignment.LEADING,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(confirmPasswordFld, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                    javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(getInformationPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateUserInformationBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(resetUserInformation)))
                .addGap(53, 53, 53)));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getInformationPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(getStreetTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(getCityTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(8, 8, 8))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(getStateTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(getZipTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmPasswordFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateUserInformationBtn)
                    .addComponent(resetUserInformation))
                .addContainerGap(26, Short.MAX_VALUE)));
  }// </editor-fold>

  private void getPhoneTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void updateUserInformationBtnActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void getZipTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void getUserNameTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void resetUserInformationActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void getStateTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  // Variables declaration - do not modify
  private javax.swing.JPasswordField confirmPasswordFld;
  private javax.swing.JTextField getCityTxtFld;
  private javax.swing.JTextField getEmailInEditTxtFld;
  private javax.swing.JPanel getInformationPanel;
  private javax.swing.JTextField getNameInEditTxtFld;
  private javax.swing.JTextField getPhoneTxtFld;
  private javax.swing.JTextField getStateTxtFld;
  private javax.swing.JTextField getStreetTxtFld;
  private javax.swing.JTextField getUserNameTxtFld;
  private javax.swing.JTextField getZipTxtFld;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel12;
  private javax.swing.JLabel jLabel14;
  private javax.swing.JLabel jLabel15;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JButton resetUserInformation;
  private javax.swing.JButton updateUserInformationBtn;

}
