package com.bookstore.gui.form.general;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * The LoginPanel class represents the login form panel of the Bookstore
 * application.
 * It extends the JPanel class and provides a visual interface for user login.
 */
public class LoginPanel extends JPanel {

  private JTextField usernameField;
  private JPasswordField passwordField;
  private JButton loginButton;

  /**
   * Initializes the login form panel by setting its layout and adding the
   * required components.
   */
  public LoginPanel() {
    initializeComponents();
  }

  /**
   * Initializes the components of the login form panel, including labels, text
   * fields, and buttons.
   */
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
    add(new JPanel()); // empty panel for spacing
    add(loginButton);
  }

  /**
   * Validates user input in the login form panel and returns true if the input is
   * valid.
   * 
   * @return True if the user input is valid, false otherwise.
   */
  public boolean validateInput() {
    String username = getUsername();
    String password = getPassword();
    return !username.isEmpty() && !password.isEmpty();
  }

  /**
   * Retrieves the username entered in the login form panel.
   * 
   * @return The username entered in the login form panel as a string.
   */
  public String getUsername() {
    return usernameField.getText();
  }

  /**
   * Retrieves the password entered in the login form panel.
   * 
   * @return The password entered in the login form panel as a string.
   */
  public String getPassword() {
    char[] passwordChars = passwordField.getPassword();
    return new String(passwordChars);
  }

  /**
   * Clears the input fields in the login form panel.
   */
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
