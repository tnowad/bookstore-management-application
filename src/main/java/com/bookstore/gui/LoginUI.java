package com.bookstore.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.Arrays;

import javax.swing.border.*;

import com.bookstore.bus.UserBUS;
import com.bookstore.dao.UserDAO;
import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.Role;

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

    frame.getContentPane().setLayout(new FlowLayout());

    setBackground();
    initGroupContent();
    initGroupLogo();
  }

  private void initGroupLogo() {
    groupLogo.setLayout(new BorderLayout());

    iconLabel.setIcon(new ImageIcon(getClass().getResource("/resources/book_logo.png")));
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

    // titleLogin.setFont(new Font("sansserif", 0, 48));
    titleLogin.setHorizontalAlignment(SwingConstants.CENTER);
    titleLogin.setText("Login");
    titleLogin.setForeground(Color.BLUE);
    titleLogin.setPreferredSize(new Dimension(100, 100));
    groupContent.add(titleLogin, BorderLayout.PAGE_START);

    groupAccount.setLayout(new BoxLayout(groupAccount, BoxLayout.Y_AXIS));

    groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

    // usernameLabel.setFont(new Font("sansserif", 0, 24));
    usernameLabel.setText("Username");
    usernameLabel.setPreferredSize(new Dimension(120, 50));
    groupUsername.add(usernameLabel);

    // usernameTextField.setFont(new Font("sansserif", 0, 24));
    usernameTextField.setPreferredSize(new Dimension(300, 50));
    Border borderUsernameTextField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    usernameTextField.setBorder(borderUsernameTextField);
    groupUsername.add(usernameTextField);

    groupAccount.add(groupUsername);

    groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

    // passwordLabel.setFont(new Font("sansserif", 0, 24));
    passwordLabel.setText("Password");
    passwordLabel.setPreferredSize(new Dimension(120, 50));
    groupPassword.add(passwordLabel);

    // passwordField.setFont(new Font("sansserif", 0, 24));
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
    usernameTextField.addActionListener(evt -> {

    });

    passwordField.addActionListener(evt -> {

    });

    loginButton.addActionListener(e -> {
      String username = usernameTextField.getText();
      char[] password = passwordField.getPassword();
      String passwordtext = new String(password);
      try {
        UserBUS userBUS = new UserBUS();
        UserModel user = userBUS.login(username, passwordtext);
        if (user != null) {
          // successful login - do something (e.g. show main application window)
          System.out.println("Logged in successfully");
          UserModel userModel = UserDAO.getInstance().getUserByUsername(username);
          Role role = userModel.getRole();
          switch (role) {
            case CUSTOMER -> {

            }
            case EMPLOYEE -> {

            }
            case ADMIN -> {

            }
          }
        } else {
          // login failed - do something (e.g. display error message)
          JOptionPane.showMessageDialog(null, "Invalid username or password", "Login Failed",
              JOptionPane.ERROR_MESSAGE);
          System.out.println("Login Failed");
        }
        Arrays.fill(password, '0');
      } catch (SQLException ex) {
        // handle database error (e.g. display error message)
        ex.printStackTrace();
      } catch (ClassNotFoundException ex) {
        // handle missing class error (e.g. display error message)
        ex.printStackTrace();
      }
    });

    cancelButton.addActionListener(e -> frame.dispose());

    registerButton.addActionListener(e -> {
      new RegisterUI();
    });

    frame.addComponentListener(new ComponentAdapter() {
      public void componentResized(ComponentEvent e) {
        int width = frame.getContentPane().getWidth();
        if (width < 1020) {
          groupLogo.setPreferredSize(new Dimension(500, 200));

          nameStoreLabel.setFont(new Font("sansserif", 0, 16));
          titleLogin.setFont(new Font("sansserif", 0, 24));
          nameStoreLabel.setPreferredSize(new Dimension(100, 20));
          iconLabel.setIcon(new ImageIcon(getClass().getResource("/resources/book_logo_responsive.png")));

          groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          usernameLabel.setFont(new Font("sansserif", 0, 16));
          usernameLabel.setPreferredSize(new Dimension(100, 50));

          usernameTextField.setFont(new Font("sansserif", 0, 16));
          usernameTextField.setPreferredSize(new Dimension(150, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          passwordLabel.setFont(new Font("sansserif", 0, 16));
          passwordLabel.setPreferredSize(new Dimension(100, 50));

          passwordField.setFont(new Font("sansserif", 0, 16));
          passwordField.setPreferredSize(new Dimension(150, 50));

          cancelButton.setPreferredSize(new Dimension(100, 35));
          registerButton.setPreferredSize(new Dimension(100, 35));
          loginButton.setPreferredSize(new Dimension(300, 35));

        } else {
          initGroupContent();
          initGroupLogo();
        }
        frame.revalidate();
        frame.repaint();
      }
    });

  }

  private void initFrame() {
    frame.setPreferredSize(new Dimension(1100, 550));
    frame.setMinimumSize(new Dimension(700, 600));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public static void main(String[] args) {
    new LoginUI();
  }
}
