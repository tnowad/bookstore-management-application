package com.bookstore.gui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Arrays;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import com.bookstore.bus.UserBUS;
import com.bookstore.model.ProfileModel;
import com.bookstore.model.UserModel;

public class LoginUI extends JFrame {
  private static LoginUI instance;
  private JPanel groupAccount;
  private JPanel groupContent;
  private JPanel groupLogo;
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

  private LoginUI() {
    initComponent();
    handleEvent();
    initFrame();
  }

  public static LoginUI getInstance() {
    if (instance == null) {
      instance = new LoginUI();
    }
    return instance;
  }

  private void initComponent() {
    ImageIcon icon;
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

    getContentPane().setLayout(new FlowLayout());

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

    getContentPane().add(groupLogo, BoxLayout.X_AXIS);
  }

  private void initGroupContent() {
    groupContent.setLayout(new BorderLayout());

    titleLogin.setHorizontalAlignment(SwingConstants.CENTER);
    titleLogin.setText("Login");
    titleLogin.setForeground(Color.BLUE);
    titleLogin.setPreferredSize(new Dimension(100, 100));
    groupContent.add(titleLogin, BorderLayout.PAGE_START);

    groupAccount.setLayout(new BoxLayout(groupAccount, BoxLayout.Y_AXIS));

    groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

    usernameLabel.setText("Username");
    usernameLabel.setPreferredSize(new Dimension(120, 50));
    groupUsername.add(usernameLabel);

    usernameTextField.setPreferredSize(new Dimension(300, 50));
    Border borderUsernameTextField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    usernameTextField.setBorder(borderUsernameTextField);
    groupUsername.add(usernameTextField);

    groupAccount.add(groupUsername);

    groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

    passwordLabel.setText("Password");
    passwordLabel.setPreferredSize(new Dimension(120, 50));
    groupPassword.add(passwordLabel);

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

    getContentPane().add(groupContent, BoxLayout.X_AXIS);
  }

  private void setBackground() {
    getContentPane().setBackground(Color.white);
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
    loginButton.addActionListener(e -> {

      System.out.println("Debug: Login button clicked.");
      String username = usernameTextField.getText();
      char[] password = passwordField.getPassword();
      if (username == null || password == null) {
        JOptionPane.showMessageDialog(null, "Please enter username and password");
        return;
      }
      try {
        UserModel user = UserBUS.getInstance().login(username, Arrays.toString(password));
        if (user != null) {
          System.out.println("Debug: User found, login successful.");
          ProfileModel.getInstance().setUser(user);
          dispose();
          JOptionPane.showMessageDialog(null, "Login successful");
        } else {
          System.out.println("Debug: Invalid username or password.");
          JOptionPane.showMessageDialog(null, "Invalid username or password");
        }
      } catch (Exception ex) {
        System.out.println("Debug: An error occurred while logging in: " + ex.getMessage());
        JOptionPane.showMessageDialog(null, "An error occurred while logging in: " + ex.getMessage());
      }
    });

    cancelButton.addActionListener(e -> dispose());
    registerButton.addActionListener(e -> {
      RegisterUI.getInstance().setVisible(true);
      setVisible(false);
    });

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        int width = getContentPane().getWidth();
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
        revalidate();
        repaint();
      }
    });
  }

  private void initFrame() {
    setPreferredSize(new Dimension(1100, 550));
    setMinimumSize(new Dimension(700, 600));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
