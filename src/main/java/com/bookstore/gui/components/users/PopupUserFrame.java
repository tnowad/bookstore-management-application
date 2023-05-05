package com.bookstore.gui.components.users;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import com.bookstore.models.AddressModel;
import com.bookstore.models.UserModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.*;
import java.time.format.DateTimeFormatter;

import javax.swing.*;

public class PopupUserFrame extends JFrame {
  private UserModel user;

  public PopupUserFrame(UserModel user) {
    initComponents(user);
    this.user=user;
    setStatus(user.getStatus());
    setRole(user.getRole());
    setAddress(user.getId());
    setTitle("User");
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents(UserModel user) {
    setName = new JTextField();
    idText = new JLabel();
    setId = new JTextField();
    userNameText = new JLabel();
    setUserName = new JTextField();
    addressText = new JLabel();
    setAddress = new JTextField();
    phoneText = new JLabel();
    setPhone = new JTextField();
    emailText = new JLabel();
    setEmail = new JTextField();
    statusText = new JLabel();
    setStatus = new JComboBox<>();
    roleText = new JLabel();
    setRole = new JComboBox<>();
    dateCreatedText = new JLabel();
    getDateCreated = new JTextField();
    dateUpdatedText = new JLabel();
    setDateUpdated = new JTextField();
    buttonPanel = new JPanel();
    buttonBack = new JButton();
    buttonSave = new JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(610, 26));
    setPreferredSize(new Dimension(610, 410));
    getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 5, 10));

    setName.setFont(new Font("Segoe UI", 1, 14));
    setName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    setName.setText(user.getName());
    setName.setMaximumSize(new Dimension(642, 26));
    setName.setMinimumSize(new Dimension(642, 26));
    setName.setPreferredSize(new Dimension(578, 26));
    getContentPane().add(setName);

    idText.setFont(new Font("Segoe UI", 0, 18));
    idText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    idText.setText("ID");
    idText.setPreferredSize(new Dimension(86, 25));
    getContentPane().add(idText);

    setId.setEditable(false);
    setId.setFont(new Font("Segoe UI", 1, 18));
    setId.setForeground(new Color(255, 51, 51));
    setId.setText("" + user.getId());
    setId.setPreferredSize(new Dimension(180, 31));
    getContentPane().add(setId);

    userNameText.setFont(new Font("Segoe UI", 0, 18));
    userNameText.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    userNameText.setText("UserName");
    userNameText.setPreferredSize(new Dimension(125, 25));
    getContentPane().add(userNameText);

    setUserName.setEditable(false);
    setUserName.setFont(new Font("Segoe UI", 1, 18));
    setUserName.setForeground(new Color(51, 204, 255));
    setUserName.setText("" + user.getUsername());
    setUserName.setPreferredSize(new Dimension(140, 31));
    getContentPane().add(setUserName);

    addressText.setFont(new Font("Segoe UI", 0, 18));
    addressText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    addressText.setText("Address");
    addressText.setPreferredSize(new Dimension(200, 25));
    getContentPane().add(addressText);

    setAddress.setEditable(false);
    setAddress.setFont(new Font("Segoe UI", 1, 16));
    setAddress.setPreferredSize(new Dimension(280, 31));
    getContentPane().add(setAddress);

    phoneText.setFont(new Font("Segoe UI", 0, 18));
    phoneText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    phoneText.setText("Phone");
    phoneText.setPreferredSize(new Dimension(200, 25));
    getContentPane().add(phoneText);

    setPhone.setEditable(false);
    setPhone.setFont(new Font("Segoe UI", 1, 16));
    setPhone.setText("" + user.getPhone());
    setPhone.setPreferredSize(new Dimension(200, 31));
    getContentPane().add(setPhone);

    emailText.setFont(new Font("Segoe UI", 0, 18));
    emailText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    emailText.setText("Email");
    emailText.setPreferredSize(new Dimension(200, 25));
    getContentPane().add(emailText);

    setEmail.setEditable(false);
    setEmail.setFont(new Font("Segoe UI", 1, 16));
    setEmail.setText(user.getEmail());
    setEmail.setPreferredSize(new Dimension(200, 31));
    getContentPane().add(setEmail);

    statusText.setFont(new Font("Segoe UI", 0, 18));
    statusText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    statusText.setText("Status");
    statusText.setPreferredSize(new Dimension(190, 25));
    getContentPane().add(statusText);

    setStatus.setModel(
      new DefaultComboBoxModel<>(new String[] { "INACTIVE", "ACTIVE" })
    );
    setStatus.setPreferredSize(new Dimension(90, 25));
    getContentPane().add(setStatus);

    roleText.setFont(new Font("Segoe UI", 0, 18));
    roleText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    roleText.setText("Role");
    roleText.setPreferredSize(new Dimension(100, 25));
    getContentPane().add(roleText);

    setRole.setModel(
      new DefaultComboBoxModel<>(
        new String[] { "ADMIN", "CUSTOMER", "EMPLOYEE" }
      )
    );
    setRole.setPreferredSize(new Dimension(95, 25));
    getContentPane().add(setRole);

    dateCreatedText.setFont(new Font("Segoe UI", 0, 18));
    dateCreatedText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    dateCreatedText.setText("Date Created");
    dateCreatedText.setPreferredSize(new Dimension(350, 25));
    getContentPane().add(dateCreatedText);

    getDateCreated.setEditable(false);
    getDateCreated.setFont(new Font("Segoe UI", 1, 18));
    getDateCreated.setText("" + user.getCreatedAt());
    getDateCreated.setPreferredSize(new Dimension(200, 31));
    getContentPane().add(getDateCreated);

    dateUpdatedText.setFont(new Font("Segoe UI", 0, 18));
    dateUpdatedText.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    dateUpdatedText.setText("Date Updated");
    dateUpdatedText.setPreferredSize(new Dimension(350, 25));
    getContentPane().add(dateUpdatedText);

    setDateUpdated.setEditable(false);
    setDateUpdated.setFont(new Font("Segoe UI", 1, 18));
    setDateUpdated.setText("" + user.getUpdatedAt());
    setDateUpdated.setPreferredSize(new Dimension(200, 31));
    getContentPane().add(setDateUpdated);

    buttonPanel.setPreferredSize(new Dimension(570, 30));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 25, 5));

    buttonBack.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/back.png"))
    );
    buttonBack.setPreferredSize(new Dimension(70, 23));
    buttonBack.addActionListener(actionBack);
    buttonPanel.add(buttonBack);

    buttonSave.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/save.png"))
    );
    buttonSave.setPreferredSize(new Dimension(70, 23));
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

  private JLabel addressText;
  private JButton buttonBack;
  private JPanel buttonPanel;
  private JButton buttonSave;
  private JLabel dateCreatedText;
  private JLabel dateUpdatedText;
  private JLabel emailText;
  private JTextField getDateCreated;
  private JLabel idText;
  private JLabel phoneText;
  private JLabel roleText;
  private JTextField setAddress;
  private JTextField setDateUpdated;
  private JTextField setEmail;
  private JTextField setId;
  private JTextField setName;
  private JTextField setPhone;
  private JComboBox<String> setRole;
  private JComboBox<String> setStatus;
  private JTextField setUserName;
  private JLabel statusText;
  private JLabel userNameText;

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

      UserStatus newStatus = UserStatus.valueOf(selectedStatusItem.toString().toUpperCase());

      UserRole newRole = UserRole.valueOf(selectedRoleItem.toString().toUpperCase());

      LocalDateTime timeNow = LocalDateTime.now();
      timeNow.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));

      UserModel newUser = new UserModel();
      newUser.setId(user.getId());
      newUser.setUsername(user.getUsername());
      newUser.setPassword(user.getPassword());
      newUser.setStatus(newStatus);
      newUser.setName(setName.getText().trim());
      newUser.setEmail(user.getEmail());
      newUser.setPhone(user.getPhone());
      newUser.setCreatedAt(user.getCreatedAt());
      newUser.setUpdatedAt(timeNow);
      newUser.setRole(newRole);

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
  };
}
