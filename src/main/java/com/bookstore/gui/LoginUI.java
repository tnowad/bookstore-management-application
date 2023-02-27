package com.bookstore.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;

import com.formdev.flatlaf.FlatLightLaf;

public class LoginUI {

  JFrame frame = new JFrame();
  private JPanel groupAccount;
  private JPanel groupContent;
  private JPanel groupLogo;
  private ImageIcon icon;
  private JPanel groupPassword;
  private JPanel groupUsername;
  private JPanel groupButton;
  private JButton loginButton;
  private JButton cancelButton;
  private JButton registerButton;
  private JPasswordField passwordField;
  private JLabel passwordLabel;
  private JLabel titleLogin;
  private JLabel usernameLabel;
  private JTextField usernameTextField;
  private JLabel iconLabel;
  private JLabel nameStoreLabel;

  public LoginUI() {
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }
    initComponent();
    handleEvent();
    initFrame();
  }

  private void initComponent() {
    groupLogo = new JPanel();
    groupContent = new JPanel();
    titleLogin = new JLabel();
    groupAccount = new JPanel();
    groupUsername = new JPanel();
    usernameLabel = new JLabel();
    usernameTextField = new JTextField();
    groupPassword = new JPanel();
    passwordLabel = new JLabel();
    passwordField = new JPasswordField();
    groupButton = new JPanel();
    loginButton = new JButton();
    cancelButton = new JButton();
    registerButton = new JButton();
    icon = new ImageIcon("icon/book.png");
    iconLabel = new JLabel(icon);
    nameStoreLabel = new JLabel("Bookstore Management Application");
    nameStoreLabel.setForeground(Color.BLUE);
    // iconLabel.setIcon(new ImageIcon(getClass().getResource("/resources/book-logo.png")));

    frame.getContentPane().setLayout(new FlowLayout());

    setBackground();
    initGroupContent();
    initGroupLogo();

  }

  private void initGroupLogo() {
    groupLogo.setLayout(new BorderLayout());

    groupLogo.setPreferredSize(new Dimension(400, 450));

    nameStoreLabel.setFont(new Font("sansserif", 0, 24));
    nameStoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
    nameStoreLabel.setPreferredSize(new Dimension(100, 50));
    groupLogo.add(nameStoreLabel, BorderLayout.CENTER);
    groupLogo.add(iconLabel, BorderLayout.PAGE_START);

    frame.getContentPane().add(groupLogo, BoxLayout.X_AXIS);
  }

  private void initGroupContent() {
    groupContent.setLayout(new BorderLayout());

    titleLogin.setFont(new Font("sansserif", 0, 48));
    titleLogin.setHorizontalAlignment(SwingConstants.CENTER);
    titleLogin.setText("Login");
    titleLogin.setForeground(Color.BLUE);
    titleLogin.setPreferredSize(new Dimension(100, 100));
    groupContent.add(titleLogin, BorderLayout.PAGE_START);

    groupAccount.setLayout(new BoxLayout(groupAccount, BoxLayout.Y_AXIS));

    groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

    usernameLabel.setFont(new Font("sansserif", 0, 24));
    usernameLabel.setText("Username");
    usernameLabel.setPreferredSize(new Dimension(120, 50));
    groupUsername.add(usernameLabel);

    usernameTextField.setFont(new Font("sansserif", 0, 24));
    usernameTextField.setPreferredSize(new Dimension(300, 50));
    Border borderUsernameTextField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    usernameTextField.setBorder(borderUsernameTextField);
    groupUsername.add(usernameTextField);

    groupAccount.add(groupUsername);

    groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

    passwordLabel.setFont(new Font("sansserif", 0, 24));
    passwordLabel.setText("Password");
    passwordLabel.setPreferredSize(new Dimension(120, 50));
    groupPassword.add(passwordLabel);

    passwordField.setFont(new Font("sansserif", 0, 24));
    passwordField.setPreferredSize(new Dimension(300, 50));
    Border borderPasswordField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    passwordField.setBorder(borderPasswordField);
    groupPassword.add(passwordField);

    groupAccount.add(groupPassword);

    groupContent.add(groupAccount, BorderLayout.CENTER);

    groupButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

    loginButton.setText("Login");
    loginButton.setPreferredSize(new Dimension(100, 50));
    groupButton.add(loginButton);

    cancelButton.setText("Cancel");
    cancelButton.setPreferredSize(new Dimension(100, 50));
    groupButton.add(cancelButton);

    registerButton.setText("Don't have account? Register");
    registerButton.setPreferredSize(new Dimension(300, 50));
    groupButton.add(registerButton);

    groupContent.add(groupButton, BorderLayout.PAGE_END);

    frame.getContentPane().add(groupContent, BoxLayout.X_AXIS);
  }

  private void setBackground() {
    frame.getContentPane().setBackground(Color.white);
    groupLogo.setBackground(Color.white);
    groupContent.setBackground(Color.white);
    groupAccount.setBackground(Color.white);
    groupUsername.setBackground(Color.white);
    groupPassword.setBackground(Color.white);
    groupButton.setBackground(Color.white);
    loginButton.setBackground(Color.white);
    cancelButton.setBackground(Color.white);
    registerButton.setBackground(Color.white);
  }

  private void handleEvent() {
    usernameTextField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {

      }
    });

    passwordField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {

      }
    });

    loginButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

      }
    });

    cancelButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
      }
    });
  }

  private void initFrame() {
    frame.setPreferredSize(new Dimension(1100, 550));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    new LoginUI();
  }
}
