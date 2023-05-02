
package com.bookstore.gui.components.users;

import com.bookstore.bus.UserBUS;
import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import com.bookstore.models.UserModel;

import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.*;

public class CreateUserFrame extends javax.swing.JFrame {

  public CreateUserFrame() {
    initComponents();
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents() {

    titlePanel = new javax.swing.JLabel();
    nameText = new javax.swing.JLabel();
    setName = new javax.swing.JTextField();
    usernameText = new javax.swing.JLabel();
    setUserName = new javax.swing.JTextField();
    passwordText = new javax.swing.JLabel();
    setPassword = new javax.swing.JPasswordField();
    phoneText = new javax.swing.JLabel();
    setPhone = new javax.swing.JTextField();
    emailText = new javax.swing.JLabel();
    setEmail = new javax.swing.JTextField();
    setRole = new javax.swing.JComboBox<>();
    statusText = new javax.swing.JLabel();
    roleText = new javax.swing.JLabel();
    setStatus = new javax.swing.JComboBox<>();
    buttonPanel = new javax.swing.JPanel();
    buttonBack = new javax.swing.JButton();
    buttonSave = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setMinimumSize(new java.awt.Dimension(590, 394));
    setPreferredSize(new java.awt.Dimension(590, 394));
    getContentPane().setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 25, 15));

    titlePanel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    titlePanel.setForeground(new java.awt.Color(255, 51, 0));
    titlePanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    titlePanel.setText("New User");
    titlePanel.setPreferredSize(new java.awt.Dimension(530, 25));
    getContentPane().add(titlePanel);

    nameText.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
    nameText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    nameText.setText("Name");
    nameText.setPreferredSize(new java.awt.Dimension(180, 20));
    getContentPane().add(nameText);

    setName.setPreferredSize(new java.awt.Dimension(250, 22));
    getContentPane().add(setName);

    usernameText.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
    usernameText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    usernameText.setText("UserName:");
    usernameText.setPreferredSize(new java.awt.Dimension(180, 20));
    getContentPane().add(usernameText);

    setUserName.setPreferredSize(new java.awt.Dimension(240, 22));
    getContentPane().add(setUserName);

    passwordText.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
    passwordText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    passwordText.setText("Password");
    passwordText.setPreferredSize(new java.awt.Dimension(180, 20));
    getContentPane().add(passwordText);

    setPassword.setPreferredSize(new java.awt.Dimension(200, 22));
    getContentPane().add(setPassword);

    phoneText.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
    phoneText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    phoneText.setText("Phone");
    phoneText.setPreferredSize(new java.awt.Dimension(180, 20));
    getContentPane().add(phoneText);

    setPhone.setPreferredSize(new java.awt.Dimension(230, 22));
    getContentPane().add(setPhone);

    emailText.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
    emailText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    emailText.setText("Email");
    emailText.setPreferredSize(new java.awt.Dimension(180, 20));
    getContentPane().add(emailText);

    setEmail.setPreferredSize(new java.awt.Dimension(280, 22));
    getContentPane().add(setEmail);

    roleText.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
    roleText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    roleText.setText("Role");
    roleText.setPreferredSize(new java.awt.Dimension(170, 20));
    getContentPane().add(roleText);

    setRole.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ADMIN", "CUSTOMER", "EMPLOYEE" }));
    getContentPane().add(setRole);

    statusText.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
    statusText.setText("Status");
    getContentPane().add(statusText);

    setStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ACTIVE", "INACTIVE" }));
    getContentPane().add(setStatus);

    buttonPanel.setPreferredSize(new java.awt.Dimension(530, 30));
    buttonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 30, 5));

    buttonBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/back.png"))); // NOI18N
    buttonBack.setPreferredSize(new java.awt.Dimension(70, 23));
    buttonBack.addActionListener(actionBack);
    buttonPanel.add(buttonBack);

    buttonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/icons/save.png"))); // NOI18N
    buttonSave.setPreferredSize(new java.awt.Dimension(70, 23));
    buttonPanel.add(buttonSave);

    getContentPane().add(buttonPanel);

    pack();
  }

  private boolean isValidEmailAddress(String email) {
    // pattern to validate email
    Pattern pattern = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\." +
            "[a-zA-Z0-9_+&*-]+)*@" +
            "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
            "A-Z]{2,7}$",
        Pattern.CASE_INSENSITIVE);
    return pattern.matcher(email).matches();
  }

  private boolean isValidPhoneNumber(String number) {
    Pattern pattern = Pattern.compile("^\\d{10}$");
    return pattern.matcher(number).matches();
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton buttonBack;
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JButton buttonSave;
  private javax.swing.JLabel emailText;
  private javax.swing.JLabel nameText;
  private javax.swing.JLabel passwordText;
  private javax.swing.JLabel phoneText;
  private javax.swing.JLabel roleText;
  private javax.swing.JTextField setEmail;
  private javax.swing.JTextField setName;
  private javax.swing.JPasswordField setPassword;
  private javax.swing.JTextField setPhone;
  private javax.swing.JComboBox<String> setRole;
  private javax.swing.JComboBox<String> setStatus;
  private javax.swing.JTextField setUserName;
  private javax.swing.JLabel statusText;
  private javax.swing.JLabel titlePanel;
  private javax.swing.JLabel usernameText;
  // End of variables declaration//GEN-END:variables

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
      final String EMPTY_FIELD_ERROR = " cannot be empty!";
      final String INVALID_ERROR = " is not valid!";
      final String DUPLICATE_ERROR = " already exists!";

      if (setName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Name" + EMPTY_FIELD_ERROR);
        return;
      }

      if (setUserName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "User Name" + EMPTY_FIELD_ERROR);
        return;
      }

      if (String.valueOf(setPassword.getPassword()).isEmpty()) {
        JOptionPane.showMessageDialog(null, "Password" + EMPTY_FIELD_ERROR);
        return;
      }

      if (setPhone.getText().trim().isEmpty() ||
          !isValidPhoneNumber(setPhone.getText().trim())) {
        JOptionPane.showMessageDialog(null, "Phone" + INVALID_ERROR);
        return;
      }

      if (setEmail.getText().trim().isEmpty() ||
          !isValidEmailAddress(setEmail.getText().trim())) {
        JOptionPane.showMessageDialog(null, "Email" + INVALID_ERROR);
        return;
      }

      if (UserBUS
          .getInstance()
          .checkForDuplicate(
              Arrays.asList(setUserName.getText()),
              new String[] { "username" })) {
        JOptionPane.showMessageDialog(null, "User Name" + DUPLICATE_ERROR);
        return;
      }

      if (UserBUS
          .getInstance()
          .checkForDuplicate(
              Arrays.asList(setPhone.getText()),
              new String[] { "phone" })) {
        JOptionPane.showMessageDialog(null, "Phone" + DUPLICATE_ERROR);
        return;
      }
      if (UserBUS
          .getInstance()
          .checkForDuplicate(
              Arrays.asList(setEmail.getText()),
              new String[] { "email" })) {
        JOptionPane.showMessageDialog(null, "Email" + DUPLICATE_ERROR);
        return;
      }

      int confirm = JOptionPane.showConfirmDialog(
          null,
          "Do you want to create?",
          "Confirmation",
          JOptionPane.YES_NO_OPTION);
      if (confirm == JOptionPane.YES_OPTION) {
        Object selectedRoleItem = setRole.getSelectedItem();
        UserRole role = UserRole.valueOf(
            selectedRoleItem.toString().toUpperCase());

        Object selectedStatusItem = setStatus.getSelectedItem();
        UserStatus status = UserStatus.valueOf(
            selectedStatusItem.toString().toUpperCase());

        String password = new String(setPassword.getPassword());

        LocalDateTime timeNow = LocalDateTime.now();

        UserModel newUser = new UserModel(
            0,
            setUserName.getText().trim(),
            password,
            status,
            setName.getText().trim(),
            setEmail.getText().trim(),
            setPhone.getText().trim(),
            timeNow,
            timeNow,
            role);

        UserBUS.getInstance().addModel(newUser);

      }
    }

  };
}
