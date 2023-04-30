/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bookstore.gui.form.admin.component.user;

import com.bookstore.bus.UserBUS;
import com.bookstore.models.UserModel;
import com.bookstore.models.UserModel.Role;
import com.bookstore.models.UserModel.Status;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

/**
 *
 * @author yanti
 */
public class PopupUser extends javax.swing.JFrame {

  /**
   * Creates new form UserFullForm
   */
  public PopupUser(
    int idUser,
    String userName,
    String password,
    Status status,
    String name,
    String email,
    String phone,
    Role role,
    LocalDateTime dateCreate,
    LocalDateTime dateUpdate
  ) {
    initComponents(
      idUser,
      userName,
      password,
      status,
      name,
      email,
      phone,
      role,
      dateCreate,
      dateUpdate
    );
    setStatus(status);
    setRole(role);
    setTitle("User");
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents(
    int idUser,
    String userName,
    String password,
    Status status,
    String name,
    String email,
    String phone,
    Role role,
    LocalDateTime dateCreate,
    LocalDateTime dateUpdate
  ) {
    jPanel2 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();
    jLabel5 = new javax.swing.JLabel();
    SetName = new javax.swing.JTextField();
    jSeparator2 = new javax.swing.JSeparator();
    SetPhone = new javax.swing.JTextField();
    SetIdUser = new javax.swing.JTextField();
    SetUserName = new javax.swing.JTextField();
    SetEmail = new javax.swing.JTextField();
    jLabel7 = new javax.swing.JLabel();
    jLabel6 = new javax.swing.JLabel();
    jLabel8 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    GetdateCreate = new javax.swing.JTextField();
    jLabel10 = new javax.swing.JLabel();
    jLabel11 = new javax.swing.JLabel();
    SetdateUpdate = new javax.swing.JTextField();
    Back = new javax.swing.JButton();
    Save = new javax.swing.JButton();
    SetStatus = new javax.swing.JComboBox<>();
    SetRole = new javax.swing.JComboBox<>();
    SetPassword = new javax.swing.JPasswordField();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel3.setText("Phone");

    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel4.setText("UserName");

    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel5.setText("ID");

    SetName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    SetName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    SetName.setText(name);
    SetName.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          SetNameActionPerformed(evt);
        }
      }
    );

    SetPhone.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    SetPhone.setText(phone);
    SetPhone.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          SetPhoneActionPerformed(evt);
        }
      }
    );

    SetIdUser.setEditable(false);
    SetIdUser.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    SetIdUser.setForeground(new java.awt.Color(255, 51, 51));
    SetIdUser.setText("" + idUser);

    SetUserName.setEditable(false);
    SetUserName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    SetUserName.setForeground(new java.awt.Color(51, 204, 255));
    SetUserName.setText(userName);

    SetEmail.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    SetEmail.setText(email);
    SetEmail.setToolTipText("");

    jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel7.setText("Status");

    jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel6.setText("Password");

    jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel8.setText("Email");

    jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel9.setText("Date Created");

    GetdateCreate.setEditable(false);
    GetdateCreate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    GetdateCreate.setText("" + dateCreate);

    jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel10.setText("Date Updated");

    jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel11.setText("Role");

    SetdateUpdate.setEditable(false);
    SetdateUpdate.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    SetdateUpdate.setText("" + dateUpdate);

    Back.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/back.png")
      )
    ); // NOI18N
    Back.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(Back);
          frame.dispose();
        }
      }
    );
    Save.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/save.png")
      )
    ); // NOI18N
    Save.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {}
      }
    );

    SetStatus.setModel(
      new javax.swing.DefaultComboBoxModel<>(
        new String[] { "INACTIVE", "ACTIVE" }
      )
    );

    SetRole.setModel(
      new javax.swing.DefaultComboBoxModel<>(
        new String[] { "ADMIN", "CUSTOMER", "EMPLOYEE" }
      )
    );

    SetPassword.setText("jPasswordField1");

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
      jPanel2
    );
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
        .addGroup(
          jPanel2Layout
            .createSequentialGroup()
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addGroup(
                      jPanel2Layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.TRAILING
                        )
                        .addComponent(
                          jLabel3,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          72,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(jLabel8)
                        .addComponent(jLabel11)
                        .addComponent(jLabel7)
                    )
                )
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addContainerGap()
                    .addComponent(
                      jLabel6,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      108,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addGap(333, 333, 333)
                    .addComponent(
                      jSeparator2,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      4,
                      Short.MAX_VALUE
                    )
                    .addGap(177, 177, 177)
                )
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addGroup(
                      jPanel2Layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addGroup(
                          jPanel2Layout
                            .createSequentialGroup()
                            .addComponent(
                              SetRole,
                              javax.swing.GroupLayout.PREFERRED_SIZE,
                              156,
                              javax.swing.GroupLayout.PREFERRED_SIZE
                            )
                            .addGap(9, 9, 9)
                            .addGroup(
                              jPanel2Layout
                                .createParallelGroup(
                                  javax.swing.GroupLayout.Alignment.TRAILING
                                )
                                .addComponent(jLabel10)
                                .addComponent(jLabel9)
                            )
                            .addPreferredGap(
                              javax.swing.LayoutStyle.ComponentPlacement.RELATED
                            )
                            .addGroup(
                              jPanel2Layout
                                .createParallelGroup(
                                  javax.swing.GroupLayout.Alignment.LEADING,
                                  false
                                )
                                .addComponent(
                                  GetdateCreate,
                                  javax.swing.GroupLayout.DEFAULT_SIZE,
                                  216,
                                  Short.MAX_VALUE
                                )
                                .addComponent(SetdateUpdate)
                            )
                        )
                        .addGroup(
                          jPanel2Layout
                            .createSequentialGroup()
                            .addGap(105, 105, 105)
                            .addComponent(
                              Back,
                              javax.swing.GroupLayout.PREFERRED_SIZE,
                              70,
                              javax.swing.GroupLayout.PREFERRED_SIZE
                            )
                            .addGap(48, 48, 48)
                            .addComponent(
                              Save,
                              javax.swing.GroupLayout.PREFERRED_SIZE,
                              73,
                              javax.swing.GroupLayout.PREFERRED_SIZE
                            )
                        )
                        .addComponent(
                          SetPhone,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          175,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          SetEmail,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          295,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                    .addContainerGap(
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      Short.MAX_VALUE
                    )
                )
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addGroup(
                      jPanel2Layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addComponent(
                          SetStatus,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          156,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                        .addComponent(
                          SetPassword,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          226,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                    .addGap(0, 0, Short.MAX_VALUE)
                )
            )
        )
        .addGroup(
          jPanel2Layout
            .createSequentialGroup()
            .addGap(25, 25, 25)
            .addComponent(
              SetName,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              588,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(0, 0, Short.MAX_VALUE)
        )
        .addGroup(
          jPanel2Layout
            .createSequentialGroup()
            .addGap(12, 12, 12)
            .addComponent(jLabel5)
            .addGap(18, 18, 18)
            .addComponent(
              SetIdUser,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              150,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(jLabel4)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addComponent(
              SetUserName,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              178,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(25, 25, 25)
        )
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          jPanel2Layout
            .createSequentialGroup()
            .addComponent(
              SetName,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              35,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              jSeparator1,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              10,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              jPanel2Layout
                .createParallelGroup(
                  javax.swing.GroupLayout.Alignment.LEADING,
                  false
                )
                .addGroup(
                  jPanel2Layout
                    .createParallelGroup(
                      javax.swing.GroupLayout.Alignment.BASELINE
                    )
                    .addComponent(
                      SetIdUser,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      javax.swing.GroupLayout.DEFAULT_SIZE,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                    .addComponent(jLabel5)
                )
                .addGroup(
                  jPanel2Layout
                    .createParallelGroup(
                      javax.swing.GroupLayout.Alignment.BASELINE
                    )
                    .addComponent(SetUserName)
                    .addComponent(jLabel4)
                )
            )
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addGap(9, 9, 9)
                    .addComponent(
                      jSeparator2,
                      javax.swing.GroupLayout.PREFERRED_SIZE,
                      10,
                      javax.swing.GroupLayout.PREFERRED_SIZE
                    )
                )
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addGap(18, 18, 18)
                    .addGroup(
                      jPanel2Layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(jLabel6)
                        .addComponent(
                          SetPassword,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          33,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                )
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  SetPhone,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  jLabel3,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  31,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  SetEmail,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(jLabel8)
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addComponent(jLabel7)
                    .addGap(0, 8, Short.MAX_VALUE)
                )
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addGap(2, 2, 2)
                    .addComponent(SetStatus)
                )
            )
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel11)
                .addGroup(
                  jPanel2Layout
                    .createSequentialGroup()
                    .addGroup(
                      jPanel2Layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.LEADING
                        )
                        .addGroup(
                          jPanel2Layout
                            .createSequentialGroup()
                            .addGap(23, 23, 23)
                            .addGroup(
                              jPanel2Layout
                                .createParallelGroup(
                                  javax.swing.GroupLayout.Alignment.BASELINE
                                )
                                .addComponent(jLabel9)
                                .addComponent(
                                  GetdateCreate,
                                  javax.swing.GroupLayout.PREFERRED_SIZE,
                                  javax.swing.GroupLayout.DEFAULT_SIZE,
                                  javax.swing.GroupLayout.PREFERRED_SIZE
                                )
                            )
                        )
                        .addComponent(
                          SetRole,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          34,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                    .addPreferredGap(
                      javax.swing.LayoutStyle.ComponentPlacement.RELATED
                    )
                    .addGroup(
                      jPanel2Layout
                        .createParallelGroup(
                          javax.swing.GroupLayout.Alignment.BASELINE
                        )
                        .addComponent(jLabel10)
                        .addComponent(
                          SetdateUpdate,
                          javax.swing.GroupLayout.PREFERRED_SIZE,
                          javax.swing.GroupLayout.DEFAULT_SIZE,
                          javax.swing.GroupLayout.PREFERRED_SIZE
                        )
                    )
                )
            )
            .addGap(24, 24, 24)
            .addGroup(
              jPanel2Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(
                  Save,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  25,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  Back,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  25,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addGap(23, 23, 23)
        )
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
      getContentPane()
    );
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addComponent(
              jPanel2,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addComponent(
              jPanel2,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(0, 0, Short.MAX_VALUE)
        )
    );

    pack();
  } // </editor-fold>//GEN-END:initComponents

  public void setStatus(Status status) {
    int index = -1;
    switch (status.toString()) {
      case "INACTIVE" -> {
        index = 0;
      }
      case "ACTIVE" -> {
        index = 1;
      }
    }
    SetStatus.setSelectedIndex(index);
  }

  public void setRole(Enum role) {
    int index = -1;
    switch (role.toString()) {
      case "ADMIN" -> {
        index = 0;
      }
      case "CUSTOMER" -> {
        index = 1;
      }
      case "EMPLOYEE" -> {
        index = 2;
      }
    }
    SetRole.setSelectedIndex(index);
  }

  public void actionSave(
    int idUser,
    String userName,
    LocalDateTime dateCreate
  ) {
    Object selectedStatusItem = SetStatus.getSelectedItem();
    Object selectedRoleItem = SetRole.getSelectedItem();
    if (selectedStatusItem != null && selectedRoleItem != null) {
      String statusString = selectedStatusItem.toString().toUpperCase();
      Status newStatus = Status.valueOf(statusString);
      String roleString = selectedRoleItem.toString().toUpperCase();
      Role newRole = Role.valueOf(roleString);
      char[] password = SetPassword.getPassword();
      String passwordString = new String(password);

      LocalDateTime timeNow = LocalDateTime.now();
      UserModel newUser = new UserModel(
        idUser,
        userName,
        passwordString,
        newStatus,
        SetName.getText(),
        SetEmail.getText(),
        SetPhone.getText(),
        dateCreate,
        timeNow,
        newRole
      );
      int confirm = JOptionPane.showConfirmDialog(
        null,
        "Do you want to continue?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION
      );
      if (confirm == JOptionPane.YES_OPTION) {
        UserBUS.getInstance().updateModel(newUser);
      }
    }
  }

  private void SetNameActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_SetNameActionPerformed
    // TODO add your handling code here:
  } // GEN-LAST:event_SetNameActionPerformed

  private void SetPhoneActionPerformed(java.awt.event.ActionEvent evt) { // GEN-FIRST:event_SetPhoneActionPerformed
    // TODO add your handling code here:
  } // GEN-LAST:event_SetPhoneActionPerformed

  private void SaveActionPerformed(java.awt.event.ActionEvent evt) {}

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton Back;
  private javax.swing.JTextField GetdateCreate;
  private javax.swing.JButton Save;
  private javax.swing.JTextField SetEmail;
  private javax.swing.JTextField SetIdUser;
  private javax.swing.JTextField SetName;
  private javax.swing.JPasswordField SetPassword;
  private javax.swing.JTextField SetPhone;
  private javax.swing.JComboBox<String> SetRole;
  private javax.swing.JComboBox<String> SetStatus;
  private javax.swing.JTextField SetUserName;
  private javax.swing.JTextField SetdateUpdate;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel11;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JSeparator jSeparator2;
  // End of variables declaration//GEN-END:variables
}
