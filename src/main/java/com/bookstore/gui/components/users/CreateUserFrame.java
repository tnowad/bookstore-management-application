package com.bookstore.gui.components.users;

import com.bookstore.bus.EmployeeBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.dao.DatabaseConnection;
import com.bookstore.enums.EmployeeType;
import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.EmployeeModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.PasswordUtils;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import javax.swing.*;

public class CreateUserFrame extends JFrame {

  private JButton buttonBack;
  private JPanel buttonPanel;
  private JButton buttonSave;
  private JLabel emailText;
  private JLabel nameText;
  private JLabel passwordText;
  private JLabel phoneText;
  private JLabel roleText;
  private JTextField setEmail;
  private JTextField setName;
  private JPasswordField setPassword;
  private JTextField setPhone;
  private JComboBox<String> userRoleComboBox;
  private JComboBox<String> employeeTypeComboBox;
  private JComboBox<String> setStatus;
  JLabel employeeTypeLabel;
  private JTextField setUserName;
  private JLabel statusText;
  private JLabel titlePanel;
  private JLabel usernameText;

  UserModel userModel = Authentication.getCurrentUser();

  public CreateUserFrame() {
    initComponents();
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents() {
    titlePanel = new JLabel();
    nameText = new JLabel();
    setName = new JTextField();
    usernameText = new JLabel();
    setUserName = new JTextField();
    passwordText = new JLabel();
    setPassword = new JPasswordField();
    phoneText = new JLabel();
    setPhone = new JTextField();
    emailText = new JLabel();
    setEmail = new JTextField();
    userRoleComboBox = new JComboBox<>();
    employeeTypeComboBox = new JComboBox<>();
    statusText = new JLabel();
    roleText = new JLabel();
    setStatus = new JComboBox<>();
    buttonPanel = new JPanel();
    buttonBack = new JButton();
    buttonSave = new JButton();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(590, 394));
    setPreferredSize(new Dimension(590, 394));
    // getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 25, 15));
    titlePanel.setFont(new Font("Segoe UI", 1, 18));
    titlePanel.setForeground(new Color(255, 51, 0));
    titlePanel.setHorizontalAlignment(SwingConstants.CENTER);
    titlePanel.setText("New User");
    titlePanel.setPreferredSize(new Dimension(530, 25));
    getContentPane().add(titlePanel);

    nameText.setFont(new Font("Segoe UI", 1, 15));
    nameText.setHorizontalAlignment(SwingConstants.RIGHT);
    nameText.setText("Name");
    nameText.setPreferredSize(new Dimension(180, 20));
    getContentPane().add(nameText);

    setName.setPreferredSize(new Dimension(250, 22));
    getContentPane().add(setName);

    usernameText.setFont(new Font("Segoe UI", 1, 15));
    usernameText.setHorizontalAlignment(SwingConstants.RIGHT);
    usernameText.setText("UserName:");
    usernameText.setPreferredSize(new Dimension(180, 20));
    getContentPane().add(usernameText);

    setUserName.setPreferredSize(new Dimension(240, 22));
    getContentPane().add(setUserName);

    passwordText.setFont(new Font("Segoe UI", 1, 15));
    passwordText.setHorizontalAlignment(SwingConstants.RIGHT);
    passwordText.setText("Password");
    passwordText.setPreferredSize(new Dimension(180, 20));
    getContentPane().add(passwordText);

    setPassword.setPreferredSize(new Dimension(200, 22));
    getContentPane().add(setPassword);

    phoneText.setFont(new Font("Segoe UI", 1, 15));
    phoneText.setHorizontalAlignment(SwingConstants.RIGHT);
    phoneText.setText("Phone");
    phoneText.setPreferredSize(new Dimension(180, 20));
    getContentPane().add(phoneText);

    setPhone.setPreferredSize(new Dimension(230, 22));
    getContentPane().add(setPhone);

    emailText.setFont(new Font("Segoe UI", 1, 15));
    emailText.setHorizontalAlignment(SwingConstants.RIGHT);
    emailText.setText("Email");
    emailText.setPreferredSize(new Dimension(180, 20));
    getContentPane().add(emailText);

    setEmail.setPreferredSize(new Dimension(280, 22));
    getContentPane().add(setEmail);

    roleText.setFont(new Font("Segoe UI", 1, 15));
    roleText.setHorizontalAlignment(SwingConstants.RIGHT);
    roleText.setText("Role");
    roleText.setPreferredSize(new Dimension(170, 20));
    getContentPane().add(roleText);

    if (userModel.getRole() == UserRole.ADMIN) {
      userRoleComboBox.setModel(
        new DefaultComboBoxModel<>(
          new String[] { "EMPLOYEE", "ADMIN", "CUSTOMER" }
        )
      );
    } else {
      userRoleComboBox.setModel(
        new DefaultComboBoxModel<>(new String[] { "EMPLOYEE", "CUSTOMER" })
      );
    }

    employeeTypeComboBox.setModel(
      new DefaultComboBoxModel<>(
        new String[] { "EMPLOYEE_MANAGER", "EMPLOYEE_SALES" }
      )
    );
    userRoleComboBox.addActionListener(changeRoleCombo);
    getContentPane().add(userRoleComboBox);
    employeeTypeLabel = new JLabel("Employee Type");
    getContentPane().add(employeeTypeLabel);
    getContentPane().add(employeeTypeComboBox);

    statusText.setFont(new Font("Segoe UI", 1, 15));
    statusText.setText("Status");
    getContentPane().add(statusText);

    setStatus.setModel(
      new DefaultComboBoxModel<>(new String[] { "ACTIVE", "INACTIVE" })
    );
    getContentPane().add(setStatus);

    buttonPanel.setPreferredSize(new Dimension(530, 30));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

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

  private boolean isValidEmailAddress(String email) {
    // pattern to validate email
    Pattern pattern = Pattern.compile(
      "^[a-zA-Z0-9_+&*-]+(?:\\." +
      "[a-zA-Z0-9_+&*-]+)*@" +
      "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
      "A-Z]{2,7}$",
      Pattern.CASE_INSENSITIVE
    );
    return pattern.matcher(email).matches();
  }

  private boolean isValidPhoneNumber(String number) {
    Pattern pattern = Pattern.compile("^\\d{10}$");
    return pattern.matcher(number).matches();
  }

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

      if (
        setPhone.getText().trim().isEmpty() ||
        !isValidPhoneNumber(setPhone.getText().trim())
      ) {
        JOptionPane.showMessageDialog(null, "Phone" + INVALID_ERROR);
        return;
      }

      if (
        setEmail.getText().trim().isEmpty() ||
        !isValidEmailAddress(setEmail.getText().trim())
      ) {
        JOptionPane.showMessageDialog(null, "Email" + INVALID_ERROR);
        return;
      }

      if (
        UserBUS
          .getInstance()
          .checkForDuplicate(
            Arrays.asList(setUserName.getText()),
            new String[] { "username" }
          )
      ) {
        JOptionPane.showMessageDialog(null, "User Name" + DUPLICATE_ERROR);
        return;
      }

      if (
        UserBUS
          .getInstance()
          .checkForDuplicate(
            Arrays.asList(setPhone.getText()),
            new String[] { "phone" }
          )
      ) {
        JOptionPane.showMessageDialog(null, "Phone" + DUPLICATE_ERROR);
        return;
      }
      if (
        UserBUS
          .getInstance()
          .checkForDuplicate(
            Arrays.asList(setEmail.getText()),
            new String[] { "email" }
          )
      ) {
        JOptionPane.showMessageDialog(null, "Email" + DUPLICATE_ERROR);
        return;
      }

      int confirm = JOptionPane.showConfirmDialog(
        null,
        "Do you want to create?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION
      );
      if (confirm == JOptionPane.YES_OPTION) {
        Object selectedRoleItem = userRoleComboBox.getSelectedItem();
        UserRole role = UserRole.valueOf(
          selectedRoleItem.toString().toUpperCase()
        );

        Object selectedStatusItem = setStatus.getSelectedItem();
        UserStatus status = UserStatus.valueOf(
          selectedStatusItem.toString().toUpperCase()
        );

        String password = new String(setPassword.getPassword());

        LocalDateTime timeNow = LocalDateTime.now();

        UserModel newUser = new UserModel(
          0,
          setUserName.getText().trim(),
          PasswordUtils.hashPassword(password),
          status,
          setName.getText().trim(),
          setEmail.getText().trim(),
          setPhone.getText().trim(),
          timeNow,
          timeNow,
          role
        );
        //
        DatabaseConnection.getInstance().beginTransaction();
        UserBUS.getInstance().addModel(newUser);
        if (newUser.getRole() == UserRole.EMPLOYEE) {
          EmployeeType type = EmployeeType.valueOf(
            employeeTypeComboBox.getSelectedItem().toString().toUpperCase()
          );
          EmployeeModel newEmployee = new EmployeeModel(
            newUser.getId(),
            0,
            type,
            "Empty"
          );
          EmployeeBUS.getInstance().addModel(newEmployee);
        }
        if (EmployeeBUS.getInstance().getModelById(newUser.getId()) == null) {
          DatabaseConnection.getInstance().rollbackTransaction();
        }

        DatabaseConnection.getInstance().endTransaction();

        JOptionPane.showMessageDialog(null, "Complete!");
        UserBUS.getInstance().refreshData();
        MainPanel.getInstance().showForm(UserListPanel.getInstance());
      }
    }
  };

  public ActionListener changeRoleCombo = e -> {
    Object selectedRoleItem = userRoleComboBox.getSelectedItem();
    UserRole role = UserRole.valueOf(selectedRoleItem.toString().toUpperCase());

    if (role == UserRole.EMPLOYEE) {
      employeeTypeLabel.setVisible(true);
      employeeTypeComboBox.setVisible(true);
    } else {
      employeeTypeLabel.setVisible(false);
      employeeTypeComboBox.setVisible(false);
    }
  };
}
