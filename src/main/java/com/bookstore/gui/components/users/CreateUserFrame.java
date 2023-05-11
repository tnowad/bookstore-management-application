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
import java.awt.BorderLayout;
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
  private JPanel actionPanel;
  private JButton buttonSave;
  private JLabel emailLabel;
  private JLabel nameLabel;
  private JLabel passwordLabel;
  private JLabel phoneLabel;
  private JLabel roleLabel;
  private JTextField emailTextField;
  private JTextField nameTextField;
  private JPasswordField passwordField;
  private JTextField phoneTextField;
  private JComboBox<String> userRoleComboBox;
  private JComboBox<String> employeeTypeComboBox;
  private JComboBox<String> statusComboBox;
  JLabel employeeTypeLabel;
  private JTextField usernameTextField;
  private JLabel statusLabel;
  private JLabel titlePanel;
  private JLabel usernameLabel;

  UserModel userModel = Authentication.getCurrentUser();

  public CreateUserFrame() {
    initComponents();
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents() {
    titlePanel = new JLabel();
    nameLabel = new JLabel();
    nameTextField = new JTextField();
    usernameLabel = new JLabel();
    usernameTextField = new JTextField();
    passwordLabel = new JLabel();
    passwordField = new JPasswordField();
    phoneLabel = new JLabel();
    phoneTextField = new JTextField();
    emailLabel = new JLabel();
    emailTextField = new JTextField();
    userRoleComboBox = new JComboBox<>();
    employeeTypeComboBox = new JComboBox<>();
    statusLabel = new JLabel();
    roleLabel = new JLabel();
    statusComboBox = new JComboBox<>();
    actionPanel = new JPanel();
    buttonBack = new JButton();
    buttonSave = new JButton();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setMinimumSize(new Dimension(590, 394));
    setPreferredSize(new Dimension(590, 394));
    // getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT, 25, 15));
    getContentPane().setLayout(new BorderLayout(10, 10));

    titlePanel.setFont(new Font("Segoe UI", 1, 18));
    titlePanel.setForeground(new Color(255, 51, 0));
    titlePanel.setText("New User");
    // getContentPane().add(titlePanel);
    nameLabel.setFont(new Font("Segoe UI", 1, 15));
    nameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    nameLabel.setText("Name");

    usernameLabel.setFont(new Font("Segoe UI", 1, 15));
    usernameLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    usernameLabel.setText("UserName:");

    usernameTextField.setPreferredSize(new Dimension(240, 22));

    passwordLabel.setFont(new Font("Segoe UI", 1, 15));
    passwordLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    passwordLabel.setText("Password");

    phoneLabel.setFont(new Font("Segoe UI", 1, 15));
    phoneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    phoneLabel.setText("Phone");

    emailLabel.setFont(new Font("Segoe UI", 1, 15));
    emailLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    emailLabel.setText("Email");

    roleLabel.setFont(new Font("Segoe UI", 1, 15));
    roleLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    roleLabel.setText("Role");

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
    employeeTypeLabel = new JLabel("Employee Type");
    employeeTypeLabel.setFont(new Font("Segoe UI", 1, 15));
    employeeTypeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    employeeTypeLabel.setPreferredSize(new Dimension(120, 20));

    statusLabel.setFont(new Font("Segoe UI", 1, 15));
    statusLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    statusLabel.setPreferredSize(new Dimension(230, 20));
    statusLabel.setText("Status");
    statusComboBox.setModel(
      new DefaultComboBoxModel<>(new String[] { "ACTIVE", "INACTIVE" })
    );

    JPanel contentPanel = new JPanel(new GridLayout(0, 2));

    contentPanel.add(titlePanel);
    contentPanel.add(new JLabel());
    contentPanel.add(usernameLabel);
    contentPanel.add(usernameTextField);
    contentPanel.add(nameLabel);
    contentPanel.add(nameTextField);
    contentPanel.add(emailLabel);
    contentPanel.add(emailTextField);
    contentPanel.add(passwordLabel);
    contentPanel.add(passwordField);
    contentPanel.add(phoneLabel);
    contentPanel.add(phoneTextField);
    contentPanel.add(roleLabel);
    contentPanel.add(userRoleComboBox);
    contentPanel.add(employeeTypeLabel);
    contentPanel.add(employeeTypeComboBox);
    contentPanel.add(statusLabel);
    contentPanel.add(statusComboBox);
    getContentPane().add(contentPanel, BorderLayout.CENTER);

    actionPanel.setPreferredSize(new Dimension(530, 30));
    actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));

    buttonBack.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/back.png"))
    );
    buttonBack.setPreferredSize(new Dimension(70, 23));
    buttonBack.addActionListener(actionBack);
    actionPanel.add(buttonBack);

    buttonSave.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/save.png"))
    );
    buttonSave.setPreferredSize(new Dimension(70, 23));
    buttonSave.addActionListener(actionSave);
    actionPanel.add(buttonSave);

    getContentPane().add(actionPanel, BorderLayout.SOUTH);
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

      if (nameTextField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Name" + EMPTY_FIELD_ERROR);
        return;
      }

      if (usernameTextField.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "User Name" + EMPTY_FIELD_ERROR);
        return;
      }

      if (String.valueOf(passwordField.getPassword()).isEmpty()) {
        JOptionPane.showMessageDialog(null, "Password" + EMPTY_FIELD_ERROR);
        return;
      }

      if (
        phoneTextField.getText().trim().isEmpty() ||
        !isValidPhoneNumber(phoneTextField.getText().trim())
      ) {
        JOptionPane.showMessageDialog(null, "Phone" + INVALID_ERROR);
        return;
      }

      if (
        emailTextField.getText().trim().isEmpty() ||
        !isValidEmailAddress(emailTextField.getText().trim())
      ) {
        JOptionPane.showMessageDialog(null, "Email" + INVALID_ERROR);
        return;
      }

      if (
        UserBUS
          .getInstance()
          .checkForDuplicate(
            Arrays.asList(usernameTextField.getText()),
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
            Arrays.asList(phoneTextField.getText()),
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
            Arrays.asList(emailTextField.getText()),
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

        Object selectedStatusItem = statusComboBox.getSelectedItem();
        UserStatus status = UserStatus.valueOf(
          selectedStatusItem.toString().toUpperCase()
        );

        String password = new String(passwordField.getPassword());

        LocalDateTime timeNow = LocalDateTime.now();

        UserModel newUser = new UserModel(
          0,
          usernameTextField.getText().trim(),
          PasswordUtils.hashPassword(password),
          status,
          nameTextField.getText().trim(),
          emailTextField.getText().trim(),
          phoneTextField.getText().trim(),
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
      employeeTypeComboBox.setEnabled(true);
    } else {
      employeeTypeComboBox.setEnabled(false);
    }
  };
}
