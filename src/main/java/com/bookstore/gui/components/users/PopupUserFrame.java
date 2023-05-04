package com.bookstore.gui.components.users;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import com.bookstore.models.AddressModel;
import com.bookstore.models.UserModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import javax.swing.*;

public class PopupUserFrame extends javax.swing.JFrame {

  public PopupUserFrame(UserModel user) {
    initComponents(user);
    setStatus(user.getStatus());
    setRole(user.getRole());
    setAddress(user.getId());
    setTitle("User");
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents(UserModel user) {
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
    setPreferredSize(new java.awt.Dimension(610, 410));
    getContentPane()
      .setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 10));

    setName.setFont(new java.awt.Font("Segoe UI", 1, 14));
    setName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    setName.setText(user.getName());
    setName.setMaximumSize(new java.awt.Dimension(642, 26));
    setName.setMinimumSize(new java.awt.Dimension(642, 26));
    setName.setPreferredSize(new java.awt.Dimension(578, 26));
    getContentPane().add(setName);

    idText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    idText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    idText.setText("ID");
    idText.setPreferredSize(new java.awt.Dimension(86, 25));
    getContentPane().add(idText);

    setId.setEditable(false);
    setId.setFont(new java.awt.Font("Segoe UI", 1, 18));
    setId.setForeground(new java.awt.Color(255, 51, 51));
    setId.setText("" + user.getId());
    setId.setPreferredSize(new java.awt.Dimension(180, 31));
    getContentPane().add(setId);

    userNameText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    userNameText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    userNameText.setText("UserName");
    userNameText.setPreferredSize(new java.awt.Dimension(125, 25));
    getContentPane().add(userNameText);

    setUserName.setEditable(false);
    setUserName.setFont(new java.awt.Font("Segoe UI", 1, 18));
    setUserName.setForeground(new java.awt.Color(51, 204, 255));
    setUserName.setText("" + user.getUsername());
    setUserName.setPreferredSize(new java.awt.Dimension(140, 31));
    getContentPane().add(setUserName);

    addressText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    addressText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    addressText.setText("Address");
    addressText.setPreferredSize(new java.awt.Dimension(200, 25));
    getContentPane().add(addressText);

    setAddress.setEditable(false);
    setAddress.setFont(new java.awt.Font("Segoe UI", 1, 16));
    setAddress.setPreferredSize(new java.awt.Dimension(280, 31));
    getContentPane().add(setAddress);

    phoneText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    phoneText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    phoneText.setText("Phone");
    phoneText.setPreferredSize(new java.awt.Dimension(200, 25));
    getContentPane().add(phoneText);

    setPhone.setEditable(false);
    setPhone.setFont(new java.awt.Font("Segoe UI", 1, 16));
    setPhone.setText(""+user.getPhone());
    setPhone.setPreferredSize(new java.awt.Dimension(200, 31));
    getContentPane().add(setPhone);

    emailText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    emailText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    emailText.setText("Email");
    emailText.setPreferredSize(new java.awt.Dimension(200, 25));
    getContentPane().add(emailText);

    setEmail.setEditable(false);
    setEmail.setFont(new java.awt.Font("Segoe UI", 1, 16));
    setEmail.setText(user.getEmail());
    setEmail.setPreferredSize(new java.awt.Dimension(200, 31));
    getContentPane().add(setEmail);

    statusText.setFont(new java.awt.Font("Segoe UI", 0, 18));
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

    roleText.setFont(new java.awt.Font("Segoe UI", 0, 18));
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

    dateCreatedText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    dateCreatedText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    dateCreatedText.setText("Date Created");
    dateCreatedText.setPreferredSize(new java.awt.Dimension(350, 25));
    getContentPane().add(dateCreatedText);

    getDateCreated.setEditable(false);
    getDateCreated.setFont(new java.awt.Font("Segoe UI", 1, 18));
    getDateCreated.setText("" + user.getCreatedAt());
    getDateCreated.setPreferredSize(new java.awt.Dimension(200, 31));
    getContentPane().add(getDateCreated);

    dateUpdatedText.setFont(new java.awt.Font("Segoe UI", 0, 18));
    dateUpdatedText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    dateUpdatedText.setText("Date Updated");
    dateUpdatedText.setPreferredSize(new java.awt.Dimension(350, 25));
    getContentPane().add(dateUpdatedText);

    setDateUpdated.setEditable(false);
    setDateUpdated.setFont(new java.awt.Font("Segoe UI", 1, 18));
    setDateUpdated.setText("" + user.getUpdatedAt());
    setDateUpdated.setPreferredSize(new java.awt.Dimension(200, 31));
    getContentPane().add(setDateUpdated);

    buttonPanel.setPreferredSize(new java.awt.Dimension(570, 30));
    buttonPanel.setLayout(
      new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 25, 5)
    );

    buttonBack.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/back.png")
      )
    );
    buttonBack.setPreferredSize(new java.awt.Dimension(70, 23));
    buttonBack.addActionListener(actionBack);
    buttonPanel.add(buttonBack);

    buttonSave.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/save.png")
      )
    );
    buttonSave.setPreferredSize(new java.awt.Dimension(70, 23));
    buttonSave.addActionListener(actionSave);
    buttonPanel.add(buttonSave);

    getContentPane().add(buttonPanel);

    pack();
  }

  public void setAddress(int idUser) {
    AddressModel addressModel = AddressBUS.getInstance().getModelById(idUser);
    if (addressModel != null) {
      setAddress.setText(
        addressModel.getStreet() + " " + addressModel.getCity()
      );
      return;
    }
    setAddress.setText("address is null");
  }

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

  public void setRole(UserRole role) {
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

  public void actionSave() {}

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
  // End of variables declaration//GEN-END:variables\

  public ActionListener actionBack = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonBack);
      frame.dispose();
    }
  };
  public ActionListener actionSave = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      Object selectedStatusItem = setStatus.getSelectedItem();
      Object selectedRoleItem = setRole.getSelectedItem();
      if (selectedStatusItem != null && selectedRoleItem != null) {
        String statusString = selectedStatusItem.toString().toUpperCase();
        UserStatus newStatus = UserStatus.valueOf(statusString);
        String roleString = selectedRoleItem.toString().toUpperCase();
        UserRole newRole = UserRole.valueOf(roleString);

        LocalDateTime timeNow = LocalDateTime.now();
        UserModel newUser = new UserModel(
          Integer.valueOf(setId.getText().trim()),
          setUserName.getText().trim(),
          UserBUS
            .getInstance()
            .getModelByUsername(setUserName.getText().trim())
            .getPassword(),
          newStatus,
          setName.getText(),
          setEmail.getText(),
          setPhone.getText(),
          UserBUS
            .getInstance()
            .getModelByUsername(setUserName.getText().trim())
            .getUpdatedAt(),
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
  };
}
