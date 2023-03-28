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
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import com.bookstore.bus.UserBUS;
import com.bookstore.model.UserModel;

public class RetypePasswordUI extends JFrame {
  private static RetypePasswordUI instance;
  private JPanel groupAccount;
  private JPanel groupContent;
  private JPanel groupLogo;
  private JPanel groupPhone;
  private JPanel groupEmail;

  private JLabel titleResetPassword;
  private JLabel passwordLabel;
  private JPasswordField passwordField;
  private JPasswordField confirmPasswordField;
  private JLabel confirmPasswordLabel;
  private JPanel groupForgetPassword;

  private JPanel groupButton;
  private JButton updateButton;
  private JButton cancelButton;
  private JButton registerButton;
  private JLabel iconLabel;
  private JLabel nameStoreLabel;

  private String email;
  private String phone;
  private int userId;

  private RetypePasswordUI() {
    initComponent();
    handleEvent();
    initFrame();
  }

  public static RetypePasswordUI getInstance() {
    if (instance == null) {
      instance = new RetypePasswordUI();
    }
    return instance;
  }

  private void initComponent() {
    ImageIcon icon;
    groupLogo = new JPanel();
    groupContent = new JPanel();
    titleResetPassword = new JLabel();
    groupAccount = new JPanel();
    groupEmail = new JPanel();
    passwordLabel = new JLabel();
    passwordField = new JPasswordField();
    groupPhone = new JPanel();
    confirmPasswordLabel = new JLabel();
    confirmPasswordField = new JPasswordField();
    groupForgetPassword = new JPanel();
    groupButton = new JPanel();
    updateButton = new JButton();
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

    titleResetPassword.setHorizontalAlignment(SwingConstants.CENTER);
    titleResetPassword.setText("Reset Password");
    titleResetPassword.setFont(new Font("sansserif", 0, 50));
    titleResetPassword.setForeground(Color.BLUE);
    titleResetPassword.setPreferredSize(new Dimension(100, 100));
    groupContent.add(titleResetPassword, BorderLayout.PAGE_START);

    groupAccount.setLayout(new BoxLayout(groupAccount, BoxLayout.Y_AXIS));

    // group Password
    groupEmail.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

    passwordLabel.setText("Password");
    passwordLabel.setFont(new Font("sansserif", 0, 24));
    passwordLabel.setPreferredSize(new Dimension(120, 50));
    groupEmail.add(passwordLabel);

    passwordField.setPreferredSize(new Dimension(300, 50));
    Border borderpasswordField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    passwordField.setBorder(borderpasswordField);
    groupEmail.add(passwordField);

    groupAccount.add(groupEmail);

    // group Confirm Password
    groupPhone.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

    confirmPasswordLabel.setText("Confirm Password");
    confirmPasswordLabel.setFont(new Font("sansserif", 0, 24));
    confirmPasswordLabel.setPreferredSize(new Dimension(120, 50));
    groupPhone.add(confirmPasswordLabel);

    confirmPasswordField.setPreferredSize(new Dimension(300, 50));
    Border borderPasswordField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    confirmPasswordField.setBorder(borderPasswordField);
    groupPhone.add(confirmPasswordField);

    groupAccount.add(groupPhone);

    groupAccount.add(groupForgetPassword, BorderLayout.EAST);

    groupContent.add(groupAccount, BorderLayout.CENTER);

    groupButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

    updateButton.setText("Update");
    updateButton.setPreferredSize(new Dimension(100, 50));
    groupButton.add(updateButton);

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
    groupEmail.setBackground(Color.white);
    groupPhone.setBackground(Color.white);
    groupForgetPassword.setBackground(Color.white);
    groupButton.setBackground(Color.white);
    updateButton.setBackground(Color.white);
    cancelButton.setBackground(Color.white);
    registerButton.setBackground(Color.white);
  }

  private void handleEvent() {
    UserBUS userBUS = UserBUS.getInstance();

    updateButton.addActionListener(e -> {
      char[] password = passwordField.getPassword();
      char[] confirmPassword = confirmPasswordField.getPassword();

      if (password.length == 0 || confirmPassword.length == 0) {
        JOptionPane.showMessageDialog(null, "Please enter a password.");
        passwordField.setText("");
        confirmPasswordField.setText("");
        return;
      }

      if (Arrays.toString(password).equals(Arrays.toString(confirmPassword))) {
        int id = getUserId();
        UserModel userModel = userBUS.getModelById(id);
        if (userModel != null) {
          userModel.setPassword(new String(password));
          int success = userBUS.updateModel(userModel);
          if (success == 1) {
            JOptionPane.showMessageDialog(null, "You've successfully reset your password. You can log in now.");
            LoginUI.getInstance().setVisible(true);
            setVisible(false);
          } else {
            showError("Failed to update password. Please try again later.");
          }
        } else {
          showError("User not found. Please try again later.");
        }
      } else {
        showError("Passwords do not match. Please try again.");
      }
    });

    cancelButton.addActionListener(e -> {
      LoginUI.getInstance().setVisible(true);
      setVisible(false);
    });

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
          titleResetPassword.setFont(new Font("sansserif", 0, 24));
          nameStoreLabel.setPreferredSize(new Dimension(100, 20));
          iconLabel.setIcon(new ImageIcon(getClass().getResource("/resources/book_logo_responsive.png")));

          groupEmail.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          passwordLabel.setFont(new Font("sansserif", 0, 16));
          passwordLabel.setPreferredSize(new Dimension(100, 50));

          passwordField.setFont(new Font("sansserif", 0, 16));
          passwordField.setPreferredSize(new Dimension(150, 50));

          groupPhone.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          confirmPasswordLabel.setFont(new Font("sansserif", 0, 16));
          confirmPasswordLabel.setPreferredSize(new Dimension(100, 50));

          confirmPasswordField.setFont(new Font("sansserif", 0, 16));
          confirmPasswordField.setPreferredSize(new Dimension(150, 50));

          cancelButton.setPreferredSize(new Dimension(100, 35));
          registerButton.setPreferredSize(new Dimension(100, 35));
          updateButton.setPreferredSize(new Dimension(300, 35));

        } else {
          initGroupContent();
          initGroupLogo();
        }
        revalidate();
        repaint();
      }
    });
  }

  private void showError(String message) {
    JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
  }

  public void setInformations(int userId, String email, String phone) {
    this.email = email;
    this.phone = phone;
    this.userId = userId;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public int getUserId() {
    return userId;
  }

  private void initFrame() {
    setPreferredSize(new Dimension(1100, 550));
    setMinimumSize(new Dimension(700, 600));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args) {
    new RetypePasswordUI();
  }

}
