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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.component.Button;
import com.bookstore.gui.component.GroupInput;
import com.bookstore.model.ProfileModel;
import com.bookstore.model.UserModel;

public class LoginUI extends JFrame {
  private static LoginUI instance;
  private JPanel groupAccount;
  private JPanel groupContent;
  private JPanel groupLogo;
  private GroupInput groupUsername;
  private GroupInput groupPassword;

  private JLabel titleLogin;
  private JPanel groupForgetPassword;
  private Button forgetButton;

  private JPanel groupButton;
  private Button loginButton;
  private Button cancelButton;
  private Button registerButton;
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
    groupUsername = new GroupInput("Username", "show");
    groupPassword = new GroupInput("Password", "hide");

    groupForgetPassword = new JPanel();
    forgetButton = new Button("Forget password");
    groupButton = new JPanel();
    loginButton = new Button("Login");
    cancelButton = new Button("Cancel");
    registerButton = new Button("Register");
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
    titleLogin.setFont(new Font("sansserif", 0, 50));
    titleLogin.setForeground(Color.BLUE);
    titleLogin.setPreferredSize(new Dimension(100, 100));
    groupContent.add(titleLogin, BorderLayout.PAGE_START);

    groupAccount.setLayout(new BoxLayout(groupAccount, BoxLayout.Y_AXIS));

    // group username
    groupAccount.add(groupUsername);

    // group password
    groupAccount.add(groupPassword);

    // group forget password
    groupForgetPassword.setLayout(new FlowLayout(FlowLayout.RIGHT));

    forgetButton.setPreferredSize(new Dimension(200, 20));
    forgetButton.setForeground(Color.RED);
    forgetButton.setBorder(null);
    groupForgetPassword.setBorder(BorderFactory.createEmptyBorder());

    groupForgetPassword.add(forgetButton);

    groupAccount.add(groupForgetPassword, BorderLayout.EAST);

    groupContent.add(groupAccount, BorderLayout.CENTER);

    // group button
    groupButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));
    groupButton.add(loginButton);
    groupButton.add(cancelButton);
    groupButton.add(registerButton);
    groupContent.add(groupButton, BorderLayout.PAGE_END);

    getContentPane().add(groupContent, BoxLayout.X_AXIS);
  }

  private void setBackground() {
    getContentPane().setBackground(Color.white);
    groupLogo.setBackground(Color.white);
    groupContent.setBackground(Color.white);
    groupAccount.setBackground(Color.white);
    groupForgetPassword.setBackground(Color.WHITE);
    groupButton.setBackground(Color.white);
  }

  private void handleEvent() {
    loginButton.addActionListener(e -> {
      String username = groupUsername.getTextField().getText();
      char[] password = groupPassword.getPasswordField().getPassword();
      if (username == null || password == null) {
        JOptionPane.showMessageDialog(null, "Please enter username and password");
        return;
      }
      try {
        UserModel user = UserBUS.getInstance().login(username, Arrays.toString(password));
        if (user != null) {
          ProfileModel.getInstance().setUser(user);
          dispose();
          System.out.println("Logged in successfully");
        } else {
          JOptionPane.showMessageDialog(null,
              "Invalid username or password. Please check the username or password and try again.");
        }
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "An error occurred while logging in: " + ex.getMessage());
      }
    });

    cancelButton.addActionListener(e -> dispose());
    registerButton.addActionListener(e -> {
      RegisterUI.getInstance().setVisible(true);
      setVisible(false);
    });

    forgetButton.addActionListener(e -> {
      ForgotPasswordUI.getInstance().setVisible(true);
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

          groupUsername.getLabel().setFont(new Font("sansserif", 0, 16));
          groupUsername.getLabel().setPreferredSize(new Dimension(100, 50));

          groupUsername.getTextField().setFont(new Font("sansserif", 0, 16));
          groupUsername.getTextField().setPreferredSize(new Dimension(150, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupPassword.getLabel().setFont(new Font("sansserif", 0, 16));
          groupPassword.getLabel().setPreferredSize(new Dimension(100, 50));

          groupPassword.getPasswordField().setFont(new Font("sansserif", 0, 16));
          groupPassword.getPasswordField().setPreferredSize(new Dimension(150, 50));

          cancelButton.setPreferredSize(new Dimension(100, 35));
          registerButton.setPreferredSize(new Dimension(100, 35));
          loginButton.setPreferredSize(new Dimension(100, 35));

        } else {
          groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

          groupUsername.getLabel().setFont(new Font("sansserif", 0, 24));
          groupUsername.getLabel().setPreferredSize(new Dimension(120, 50));

          groupUsername.getTextField().setFont(new Font("sansserif", 0, 24));
          groupUsername.getTextField().setPreferredSize(new Dimension(300, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

          groupPassword.getLabel().setFont(new Font("sansserif", 0, 24));
          groupPassword.getLabel().setPreferredSize(new Dimension(120, 50));

          groupPassword.getPasswordField().setFont(new Font("sansserif", 0, 24));
          groupPassword.getPasswordField().setPreferredSize(new Dimension(300, 50));
          loginButton.setButtonSize(100, 50);
          cancelButton.setButtonSize(100, 50);
          registerButton.setButtonSize(100, 50);
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
