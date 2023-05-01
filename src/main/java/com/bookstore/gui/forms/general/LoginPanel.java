package com.bookstore.gui.forms.general;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPanel extends JPanel {

  private JTextField usernameField;
  private JPasswordField passwordField;
  private JButton loginButton;

  public LoginPanel() {
    initializeComponents();
  }

  private void initializeComponents() {
    JLabel usernameLabel = new JLabel("Username:");
    usernameField = new JTextField(20);
    JLabel passwordLabel = new JLabel("Password:");
    passwordField = new JPasswordField(20);
    loginButton = new JButton("Login");

    setLayout(new GridLayout(3, 2));
    add(usernameLabel);
    add(usernameField);
    add(passwordLabel);
    add(passwordField);
    add(new JPanel());
    add(loginButton);
  }

  public boolean validateInput() {
    String username = getUsername();
    String password = getPassword();
    return !username.isEmpty() && !password.isEmpty();
  }

  public String getUsername() {
    return usernameField.getText();
  }

  public String getPassword() {
    char[] passwordChars = passwordField.getPassword();
    return new String(passwordChars);
  }

  public void clearFields() {
    usernameField.setText("");
    passwordField.setText("");
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setSize(400, 300);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new LoginPanel());
    frame.setVisible(true);
  }
}
