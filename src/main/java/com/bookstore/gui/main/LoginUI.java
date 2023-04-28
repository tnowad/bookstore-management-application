package com.bookstore.gui.main;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.factories.UIFactory;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.PasswordUtil;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
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

public class LoginUI extends JFrame {

  private static LoginUI instance;

  private LayoutManager layout;
  private JPanel loginPanel;
  private Button exitButton;
  private Button forgotPasswordButton;
  private Button loginButton;
  private Button registerButton;
  private JLabel LogoLabel;
  private JLabel passwordLabel;
  private JLabel usernameLabel;
  private JLabel welcomeLabel;
  private JPasswordField passwordField;
  private JTextField usernameTextField;

  public LoginUI() {
    initComponents();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);
    setLocationRelativeTo(null);
    setMinimumSize(new Dimension(700, 500));
    setPreferredSize(new Dimension(1000, 500));
    pack();
  }

  public static LoginUI getInstance() {
    if (instance == null) {
      instance = new LoginUI();
    }
    return instance;
  }

  private void initComponents() {
    layout = new FlowLayout(FlowLayout.CENTER, 10, 50);
    setLayout(layout);
    LogoLabel = new JLabel();
    LogoLabel.setIcon(new ImageIcon("src/main/java/resources/book_logo.png"));

    loginPanel = new JPanel();

    loginPanel.setLayout(new GridBagLayout());
    usernameLabel = new JLabel();
    usernameLabel.setText("Username");
    passwordLabel = new JLabel();
    passwordLabel.setText("Password");
    usernameTextField = new JTextField();
    passwordField = new JPasswordField();
    loginButton = new Button("Login");
    loginButton.addActionListener(loginButtonActionListener);
    exitButton = new Button("Exit");
    exitButton.addActionListener(exitButtonActionListener);
    registerButton = new Button("Register");
    registerButton.addActionListener(registerButtonActionListener);
    forgotPasswordButton = new Button("Forgot Password");
    forgotPasswordButton.addActionListener(forgotPasswordButtonActionListener);

    loginPanel.setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 4;
    loginPanel.add(usernameLabel, gridBagConstraints);

    gridBagConstraints.gridy = 1;
    loginPanel.add(usernameTextField, gridBagConstraints);

    gridBagConstraints.gridy = 2;
    loginPanel.add(passwordLabel, gridBagConstraints);
    gridBagConstraints.gridy = 3;
    loginPanel.add(passwordField, gridBagConstraints);

    gridBagConstraints.gridwidth = 1;
    gridBagConstraints.gridy = 4;
    loginPanel.add(loginButton, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    loginPanel.add(registerButton, gridBagConstraints);
    gridBagConstraints.gridx = 2;
    loginPanel.add(forgotPasswordButton, gridBagConstraints);
    gridBagConstraints.gridx = 3;
    loginPanel.add(exitButton, gridBagConstraints);

    add(LogoLabel);
    add(loginPanel);
  }

  private ActionListener loginButtonActionListener = e -> {
    String username = usernameTextField.getText();
    String password = new String(passwordField.getPassword());
    System.out.println("Username: " + username);
    System.out.println("Password: " + password);

    try {
      if (username.isEmpty() || password.isEmpty()) {
        throw new Exception("Username and password must not be empty");
      }
      UserModel user = UserBUS.getInstance().login(username, password);
      Authentication.setCurrentUser(user);
      JOptionPane.showMessageDialog(null, "Login successfully");
      UIFactory.showForm(user);
      dispose();
    } catch (Exception exception) {
      JOptionPane.showMessageDialog(null, exception.getMessage());
    }
    passwordField.setText("");
  };

  private ActionListener exitButtonActionListener = e -> {
    System.exit(0);
  };

  private ActionListener registerButtonActionListener = e -> {
    System.out.println("Register Button Clicked");
    RegisterUI.getInstance().setVisible(true);
    dispose();
  };

  private ActionListener forgotPasswordButtonActionListener = e -> {
    System.out.println("Forgot Password Button Clicked");
    ForgotPasswordUI.getInstance().setVisible(true);
    dispose();
  };

  public static void main(String[] args) {
    System.out.println(PasswordUtil.hashPassword("password"));
    FlatMacLightLaf.setup();
    new LoginUI().setVisible(true);
  }
}
