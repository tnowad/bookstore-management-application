package com.bookstore.gui.main;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class RegisterFrame extends JFrame {

  private static RegisterFrame instance;

  private LayoutManager layout;
  private JPanel registerPanel;
  private Button exitButton;
  private Button registerButton;
  private Button loginButton;
  private JLabel LogoLabel;

  private JLabel usernameLabel;
  private JLabel nameLabel;
  private JLabel emailLabel;
  private JLabel phoneLabel;
  private JLabel passwordLabel;
  private JLabel confirmPasswordLabel;

  private JTextField usernameTextField;
  private JTextField nameTextField;
  private JTextField emailTextField;
  private JTextField phoneTextField;
  private JPasswordField passwordField;
  private JPasswordField confirmPasswordField;

  public RegisterFrame() {
    initComponents();
    setPreferredSize(new Dimension(700, 800));
    setMinimumSize(new Dimension(700, 800));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
  }

  public static RegisterFrame getInstance() {
    if (instance == null) {
      instance = new RegisterFrame();
    }
    return instance;
  }

  private void initComponents() {
    layout = new FlowLayout(FlowLayout.CENTER, 10, 10);
    setLayout(layout);
    LogoLabel = new JLabel();
    LogoLabel.setIcon(new ImageIcon("src/main/java/resources/images/logo.png"));

    registerPanel = new JPanel();
    registerPanel.setLayout(new GridBagLayout());

    usernameLabel = new JLabel("Username");
    nameLabel = new JLabel("Name");
    emailLabel = new JLabel("Email");
    phoneLabel = new JLabel("Phone");
    passwordLabel = new JLabel("Password");
    confirmPasswordLabel = new JLabel("Confirm Password");
    usernameTextField = new JTextField();
    nameTextField = new JTextField();
    emailTextField = new JTextField();
    phoneTextField = new JTextField();
    passwordField = new JPasswordField();
    confirmPasswordField = new JPasswordField();
    exitButton = new Button("Exit");
    exitButton.addActionListener(exitButtonActionListener);
    registerButton = new Button("Register");
    registerButton.addActionListener(registerButtonActionListener);
    loginButton = new Button("Login");
    loginButton.addActionListener(loginButtonActionListener);
    registerPanel.setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 4;
    registerPanel.add(usernameLabel, gridBagConstraints);
    gridBagConstraints.gridy = 1;
    registerPanel.add(usernameTextField, gridBagConstraints);
    gridBagConstraints.gridy = 2;
    registerPanel.add(nameLabel, gridBagConstraints);
    gridBagConstraints.gridy = 3;
    registerPanel.add(nameTextField, gridBagConstraints);
    gridBagConstraints.gridy = 4;
    registerPanel.add(emailLabel, gridBagConstraints);
    gridBagConstraints.gridy = 5;
    registerPanel.add(emailTextField, gridBagConstraints);
    gridBagConstraints.gridy = 6;
    registerPanel.add(phoneLabel, gridBagConstraints);
    gridBagConstraints.gridy = 7;
    registerPanel.add(phoneTextField, gridBagConstraints);
    gridBagConstraints.gridy = 8;
    registerPanel.add(passwordLabel, gridBagConstraints);
    gridBagConstraints.gridy = 9;
    registerPanel.add(passwordField, gridBagConstraints);
    gridBagConstraints.gridy = 10;
    registerPanel.add(confirmPasswordLabel, gridBagConstraints);
    gridBagConstraints.gridy = 11;
    registerPanel.add(confirmPasswordField, gridBagConstraints);

    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridy = 12;
    registerPanel.add(registerButton, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    registerPanel.add(loginButton, gridBagConstraints);
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridx = 3;
    registerPanel.add(exitButton, gridBagConstraints);

    add(LogoLabel);
    add(registerPanel);
  }

  private ActionListener registerButtonActionListener = e -> {
    String username = usernameTextField.getText();
    String name = nameTextField.getText();
    String email = emailTextField.getText();
    String phone = phoneTextField.getText();
    String password = String.valueOf(passwordField.getPassword());
    String confirmPassword = String.valueOf(confirmPasswordField.getPassword());
    try {
      if (
        username.isEmpty() ||
        name.isEmpty() ||
        email.isEmpty() ||
        phone.isEmpty() ||
        password.isEmpty() ||
        confirmPassword.isEmpty()
      ) {
        throw new IllegalArgumentException("Please fill all fields");
      }
      if (!password.equals(confirmPassword)) {
        throw new IllegalArgumentException("Password not match");
      }
      UserModel user = UserBUS
        .getInstance()
        .register(username, password, name, email, phone);
      Authentication.setCurrentUser(user);
      JOptionPane.showMessageDialog(
        null,
        "Register successfully",
        "Success",
        JOptionPane.INFORMATION_MESSAGE
      );
    } catch (Exception exception) {
      JOptionPane.showMessageDialog(
        null,
        exception.getMessage(),
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
      return;
    }
  };

  private ActionListener loginButtonActionListener = e -> {
    LoginFrame.getInstance().setVisible(true);
    dispose();
  };

  private ActionListener exitButtonActionListener = e -> {
    System.exit(0);
  };
}
