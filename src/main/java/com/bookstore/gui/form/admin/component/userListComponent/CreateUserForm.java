package com.bookstore.gui.form.admin.component.userListComponent;

import java.time.LocalDateTime;

import javax.swing.*;

import com.bookstore.bus.UserBUS;
import com.bookstore.models.UserModel;
import com.bookstore.models.UserModel.Role;
import com.bookstore.models.UserModel.Status;

public class CreateUserForm extends javax.swing.JFrame {

  public CreateUserForm() {
    initComponents();
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents() {

    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    ButtonCancer = new javax.swing.JButton();
    ButtonSave = new javax.swing.JButton();
    jLabel6 = new javax.swing.JLabel();
    SetUserName = new javax.swing.JTextField();
    SetPassword = new javax.swing.JPasswordField();
    SetName = new javax.swing.JTextField();
    SetPhone = new javax.swing.JTextField();
    SetEmail = new javax.swing.JTextField();
    jLabel7 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    SetRole = new javax.swing.JComboBox<>();
    SetStatus = new javax.swing.JTextField();
    Errol = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel1.setText("UserName:");

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel2.setText("Password");

    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel3.setText("Phone");

    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel4.setText("Email");

    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel5.setText("Name");

    // ButtonCancer.setIcon(new
    // javax.swing.ImageIcon(getClass().getResource("/com/bookstore/gui/form/admin/icon/back.png")));
    // // NOI18N
    ButtonCancer.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        ButtonCancerActionPerformed(evt);
      }
    });

    // ButtonSave.setIcon(new
    // javax.swing.ImageIcon(getClass().getResource("/com/bookstore/gui/admin/form/icon/save.png")));
    // // NOI18N
    ButtonSave.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        Status newstatus = Status.ACTIVE;
        Object selectedRoleItem = SetRole.getSelectedItem();
        if (selectedRoleItem != null) {
          String roleString = selectedRoleItem.toString().toUpperCase();
          Role newRole = Role.valueOf(roleString);
          char[] password = SetPassword.getPassword();
          String passwordString = new String(password);

          LocalDateTime now = LocalDateTime.now();
          UserModel newUser = new UserModel(
              UserBUS.getInstance().getAllModels().size() + 1,
              SetUserName.getText(),
              passwordString,
              newstatus,
              SetName.getText(),
              SetEmail.getText(),
              SetPhone.getText(),
              now,
              now,
              newRole);

          int confirm = JOptionPane.showConfirmDialog(null, "Do you want to continue?", "Confirmation",
              JOptionPane.YES_NO_OPTION);
          if (confirm == JOptionPane.YES_OPTION) {
            UserBUS.getInstance().addModel(newUser);
          }
        }
      }

    });

    jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    jLabel6.setForeground(new java.awt.Color(255, 51, 0));
    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel6.setText("New User");

    jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel7.setText("Role");

    jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel8.setText("Status");

    SetRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "CUSTOMER", "EMPLOYEE" }));

    SetStatus.setText("INACTIVE");

    Errol.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
    Errol.setForeground(new java.awt.Color(255, 0, 51));
    Errol.setText("! Ten tai khoan da duoc su dung");
    Errol.setVisible(false);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addGroup(layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE,
                                92,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE,
                                92,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                92,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                92,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE,
                                92,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SetPassword,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 186,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SetPhone, javax.swing.GroupLayout.PREFERRED_SIZE,
                                186,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SetEmail, javax.swing.GroupLayout.PREFERRED_SIZE,
                                260,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SetName, javax.swing.GroupLayout.PREFERRED_SIZE,
                                260,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(jLabel6,
                                    javax.swing.GroupLayout.PREFERRED_SIZE, 140,
                                    javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(SetUserName,
                                    javax.swing.GroupLayout.PREFERRED_SIZE, 197,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(
                                    javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Errol,
                                    javax.swing.GroupLayout.PREFERRED_SIZE, 193,
                                    javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(ButtonCancer, javax.swing.GroupLayout.PREFERRED_SIZE, 56,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(ButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 55,
                            javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 51,
                        Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(SetRole, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(SetStatus))
                .addGap(128, 128, 128)));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SetUserName)
                            .addComponent(Errol))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(SetPassword)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(SetName)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(SetPhone)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(SetEmail)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SetRole, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ButtonCancer,
                                javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(ButtonSave,
                                javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(SetStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
                            javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(19, 19, 19)));

    pack();
  }// </editor-fold>//GEN-END:initComponents

  private void ButtonCancerActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ButtonCancerActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_ButtonCancerActionPerformed

  /**
   * @param args the command line arguments
   */

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton ButtonCancer;
  private javax.swing.JButton ButtonSave;
  private javax.swing.JLabel Errol;
  private javax.swing.JTextField SetEmail;
  private javax.swing.JTextField SetName;
  private javax.swing.JPasswordField SetPassword;
  private javax.swing.JTextField SetPhone;
  private javax.swing.JComboBox<String> SetRole;
  private javax.swing.JTextField SetStatus;
  private javax.swing.JTextField SetUserName;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  // End of variables declaration//GEN-END:variables
}
