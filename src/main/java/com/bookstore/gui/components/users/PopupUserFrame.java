/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bookstore.gui.components.users;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import com.bookstore.models.AddressModel;
import com.bookstore.models.UserModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.time.*;
import javax.lang.model.element.Name;
import javax.swing.*;

/**
 *
 * @author yanti
 */
public class PopupUserFrame extends javax.swing.JFrame {

  /**
   * Creates new form UserFullForm
   */
  public PopupUserFrame(
    int idUser,
    String userName,
    String password,
    UserStatus status,
    String name,
    String email,
    String phone,
    UserRole role,
    LocalDateTime dateCreate,
    LocalDateTime dateUpdate
  ) {
    initComponents();
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
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    setName = new javax.swing.JTextField();
    idText = new javax.swing.JLabel();
    setId = new javax.swing.JTextField();
    userNameText = new javax.swing.JLabel();
    setUserName = new javax.swing.JTextField();
    addressText = new javax.swing.JLabel();
    setAddress = new javax.swing.JTextField();
    phoneText = new javax.swing.JLabel();
    setPhone = new javax.swing.JTextField();
    emailText = new javax.swing.JLabel();
    setEmail = new javax.swing.JTextField();
    statusText = new javax.swing.JLabel();
    setStatus = new javax.swing.JComboBox<>();
    roleText = new javax.swing.JLabel();
    setRole = new javax.swing.JComboBox<>();
    dateCreatedText = new javax.swing.JLabel();
    getDateCreated = new javax.swing.JTextField();
    dateUpdatedText = new javax.swing.JLabel();
    setDateUpdated = new javax.swing.JTextField();
    buttonPanel = new javax.swing.JPanel();
    buttonBack = new javax.swing.JButton();
    buttonSave = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setMinimumSize(new java.awt.Dimension(610, 26));
    setSize(new java.awt.Dimension(0, 0));
    getContentPane()
      .setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

    setName.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    setName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    setName.setText("name");
    setName.setMaximumSize(new java.awt.Dimension(642, 26));
    setName.setMinimumSize(new java.awt.Dimension(642, 26));
    setName.setPreferredSize(new java.awt.Dimension(578, 26));
    getContentPane().add(setName);

    idText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    idText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    idText.setText("ID");
    idText.setPreferredSize(new java.awt.Dimension(86, 25));
    getContentPane().add(idText);

    setId.setEditable(false);
    setId.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    setId.setForeground(new java.awt.Color(255, 51, 51));
    setId.setText("id");
    setId.setPreferredSize(new java.awt.Dimension(180, 31));
    getContentPane().add(setId);

    userNameText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    userNameText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    userNameText.setText("UserName");
    userNameText.setPreferredSize(new java.awt.Dimension(125, 25));
    getContentPane().add(userNameText);

    setUserName.setEditable(false);
    setUserName.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    setUserName.setForeground(new java.awt.Color(51, 204, 255));
    setUserName.setText("id");
    setUserName.setPreferredSize(new java.awt.Dimension(140, 31));
    getContentPane().add(setUserName);

    addressText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    addressText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    addressText.setText("Address");
    addressText.setPreferredSize(new java.awt.Dimension(200, 25));
    getContentPane().add(addressText);

    setAddress.setEditable(false);
    setAddress.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
    setAddress.setText("256 An duong vuong");
    setAddress.setPreferredSize(new java.awt.Dimension(280, 31));
    getContentPane().add(setAddress);

    phoneText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    phoneText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    phoneText.setText("Phone");
    phoneText.setPreferredSize(new java.awt.Dimension(200, 25));
    getContentPane().add(phoneText);

    setPhone.setEditable(false);
    setPhone.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
    setPhone.setText("phone");
    setPhone.setPreferredSize(new java.awt.Dimension(200, 31));
    getContentPane().add(setPhone);

    emailText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    emailText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    emailText.setText("Email");
    emailText.setPreferredSize(new java.awt.Dimension(200, 25));
    getContentPane().add(emailText);

    setEmail.setEditable(false);
    setEmail.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
    setEmail.setText("email");
    setEmail.setToolTipText("");
    setEmail.setPreferredSize(new java.awt.Dimension(200, 31));
    getContentPane().add(setEmail);

    statusText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    statusText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    statusText.setText("Status");
    statusText.setPreferredSize(new java.awt.Dimension(190, 25));
    getContentPane().add(statusText);

    setStatus.setModel(
      new javax.swing.DefaultComboBoxModel<>(
        new String[] { "INACTIVE", "ACTIVE" }
      )
    );
    setStatus.setPreferredSize(new java.awt.Dimension(90, 25));
    getContentPane().add(setStatus);

    roleText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    roleText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    roleText.setText("Role");
    roleText.setPreferredSize(new java.awt.Dimension(100, 25));
    getContentPane().add(roleText);

    setRole.setModel(
      new javax.swing.DefaultComboBoxModel<>(
        new String[] { "ADMIN", "CUSTOMER", "EMPLOYEE" }
      )
    );
    setRole.setPreferredSize(new java.awt.Dimension(95, 25));
    getContentPane().add(setRole);

    dateCreatedText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    dateCreatedText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    dateCreatedText.setText("Date Created");
    dateCreatedText.setPreferredSize(new java.awt.Dimension(350, 25));
    getContentPane().add(dateCreatedText);

    getDateCreated.setEditable(false);
    getDateCreated.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    getDateCreated.setText("ok");
    getDateCreated.setPreferredSize(new java.awt.Dimension(200, 31));
    getContentPane().add(getDateCreated);

    dateUpdatedText.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    dateUpdatedText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    dateUpdatedText.setText("Date Updated");
    dateUpdatedText.setPreferredSize(new java.awt.Dimension(350, 25));
    getContentPane().add(dateUpdatedText);

    setDateUpdated.setEditable(false);
    setDateUpdated.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    setDateUpdated.setText("ok");
    setDateUpdated.setPreferredSize(new java.awt.Dimension(200, 31));
    getContentPane().add(setDateUpdated);

    buttonPanel.setPreferredSize(new java.awt.Dimension(570, 30));
    buttonPanel.setLayout(
      new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5)
    );

    buttonBack.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/images/back.png")
      )
    ); // NOI18N
    buttonBack.setPreferredSize(new java.awt.Dimension(70, 23));
    buttonPanel.add(buttonBack);

    buttonSave.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/images/save.png")
      )
    ); // NOI18N
    buttonSave.setPreferredSize(new java.awt.Dimension(70, 23));
    buttonSave.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {}
      }
    );
    buttonPanel.add(buttonSave);

    getContentPane().add(buttonPanel);

    pack();
  } // </editor-fold>//GEN-END:initComponents

  public void setStatus(UserStatus status) {
    int index = -1;
    switch (status.toString()) {
      case "INACTIVE" -> {
        index = 0;
      }
      case "ACTIVE" -> {
        index = 1;
      }
    }
    setStatus.setSelectedIndex(index);
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
    setRole.setSelectedIndex(index);
  }

  public void actionSave(
    int idUser,
    String userName,
    String password,
    LocalDateTime dateCreate
  ) {
    Object selectedStatusItem = setStatus.getSelectedItem();
    Object selectedRoleItem = setRole.getSelectedItem();
    if (selectedStatusItem != null && selectedRoleItem != null) {
      String statusString = selectedStatusItem.toString().toUpperCase();
      UserStatus newStatus = UserStatus.valueOf(statusString);
      String roleString = selectedRoleItem.toString().toUpperCase();
      UserRole newRole = UserRole.valueOf(roleString);

      LocalDateTime timeNow = LocalDateTime.now();
      UserModel newUser = new UserModel(
        idUser,
        userName,
        password,
        newStatus,
        setName.getText(),
        setEmail.getText(),
        setPhone.getText(),
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

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel addressText;
  private javax.swing.JButton buttonBack;
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JButton buttonSave;
  private javax.swing.JLabel dateCreatedText;
  private javax.swing.JLabel dateUpdatedText;
  private javax.swing.JLabel emailText;
  private javax.swing.JTextField getDateCreated;
  private javax.swing.JLabel idText;
  private javax.swing.JLabel phoneText;
  private javax.swing.JLabel roleText;
  private javax.swing.JTextField setAddress;
  private javax.swing.JTextField setDateUpdated;
  private javax.swing.JTextField setEmail;
  private javax.swing.JTextField setId;
  private javax.swing.JTextField setName;
  private javax.swing.JTextField setPhone;
  private javax.swing.JComboBox<String> setRole;
  private javax.swing.JComboBox<String> setStatus;
  private javax.swing.JTextField setUserName;
  private javax.swing.JLabel statusText;
  private javax.swing.JLabel userNameText;
  // End of variables declaration//GEN-END:variables
}