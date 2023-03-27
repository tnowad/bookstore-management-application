package com.bookstore.gui.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

import com.bookstore.bus.UserBUS;
import com.bookstore.model.ProfileModel;
import com.bookstore.model.UserModel;

public class RegisterUI extends JFrame {
  private static RegisterUI instance;
  private JPanel groupAccount;
  private JPanel groupContent;
  private JPanel groupLogo;
  private JLabel titleRegister;
  private JPanel groupUsername;
  private JLabel usernameLabel;
  private JTextField usernameTextField;
  private JPanel groupPassword;
  private JLabel passwordLabel;
  private JPasswordField passwordField;
  private JPanel groupPasswordAgain;
  private JLabel passwordLabelAgain;
  private JPasswordField passwordFieldAgain;
  private JPanel groupButton;
  private JButton loginButton;
  private JButton cancelButton;
  private JButton registerButton;
  private JLabel iconLabel;
  private JLabel nameStoreLabel;
  private JLabel nameTextField;
  private JLabel emailTextField;
  private JLabel phoneTextField;

  private RegisterUI() {
    initComponent();
    handleEvent();
    initFrame();
  }

  public static RegisterUI getInstance() {
    if (instance == null)
      instance = new RegisterUI();
    return instance;
  }

  private void initComponent() {
    ImageIcon icon;
    groupLogo = new JPanel();
    groupContent = new JPanel();
    titleRegister = new JLabel();
    groupAccount = new JPanel();
    groupUsername = new JPanel();
    usernameLabel = new JLabel();
    usernameTextField = new JTextField();
    groupPassword = new JPanel();
    passwordLabel = new JLabel();
    passwordField = new JPasswordField();
    groupPasswordAgain = new JPanel();
    passwordLabelAgain = new JLabel();
    passwordFieldAgain = new JPasswordField();
    groupButton = new JPanel();
    loginButton = new JButton();
    cancelButton = new JButton();
    registerButton = new JButton();
    icon = new ImageIcon("icon/book.png");
    iconLabel = new JLabel(icon);
    nameStoreLabel = new JLabel("Bookstore Management Application");

    getContentPane().setLayout(new FlowLayout());

    setBackground();
    initGroupContent();
    initGroupLogo();
  }

  private void initGroupLogo() {
    groupLogo.setLayout(new BorderLayout());

    nameStoreLabel.setForeground(Color.BLUE);
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

    titleRegister.setFont(new Font("sansserif", 0, 48));
    titleRegister.setHorizontalAlignment(SwingConstants.CENTER);
    titleRegister.setText("Register");
    titleRegister.setFont(new Font("sansserif", 0, 50));
    titleRegister.setForeground(Color.BLUE);
    titleRegister.setPreferredSize(new Dimension(100, 100));
    groupContent.add(titleRegister, BorderLayout.PAGE_START);

    groupAccount.setLayout(new BoxLayout(groupAccount, BoxLayout.Y_AXIS));

    // group username
    groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

    usernameLabel.setFont(new Font("sansserif", 0, 24));
    usernameLabel.setText("Username");
    usernameLabel.setPreferredSize(new Dimension(250, 50));
    groupUsername.add(usernameLabel);

    usernameTextField.setFont(new Font("sansserif", 0, 24));
    usernameTextField.setPreferredSize(new Dimension(300, 50));
    Border borderUsernameTextField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    usernameTextField.setBorder(borderUsernameTextField);
    groupUsername.add(usernameTextField);

    groupAccount.add(groupUsername);

    // group password
    groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

    passwordLabel.setFont(new Font("sansserif", 0, 24));
    passwordLabel.setText("Password");
    passwordLabel.setPreferredSize(new Dimension(250, 50));
    groupPassword.add(passwordLabel);

    passwordField.setFont(new Font("sansserif", 0, 24));
    passwordField.setPreferredSize(new Dimension(300, 50));
    Border borderPasswordField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    passwordField.setBorder(borderPasswordField);
    groupPassword.add(passwordField);

    groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

    passwordLabelAgain.setFont(new Font("sansserif", 0, 24));
    passwordLabelAgain.setText("Password Again");
    passwordLabelAgain.setPreferredSize(new Dimension(250, 50));
    groupPasswordAgain.add(passwordLabelAgain);

    passwordFieldAgain.setFont(new Font("sansserif", 0, 24));
    passwordFieldAgain.setPreferredSize(new Dimension(300, 50));
    Border borderPasswordFieldAgain = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    passwordFieldAgain.setBorder(borderPasswordFieldAgain);
    groupPasswordAgain.add(passwordFieldAgain);

    groupAccount.add(groupPassword);
    groupAccount.add(groupPasswordAgain);

    groupContent.add(groupAccount, BorderLayout.CENTER);

    groupButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

    registerButton.setText("Register");
    registerButton.setPreferredSize(new Dimension(100, 50));
    groupButton.add(registerButton);

    cancelButton.setText("Cancel");
    cancelButton.setPreferredSize(new Dimension(100, 50));
    groupButton.add(cancelButton);

    loginButton.setText("Do you have account? Login");
    loginButton.setPreferredSize(new Dimension(300, 50));
    groupButton.add(loginButton);

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
    groupPasswordAgain.setBackground(Color.white);
    groupButton.setBackground(Color.white);
    loginButton.setBackground(Color.white);
    cancelButton.setBackground(Color.white);
    registerButton.setBackground(Color.white);
  }

  private void handleEvent() {

    loginButton.addActionListener(e -> {
      LoginUI.getInstance().setVisible(true);
      setVisible(false);
    });

    registerButton.addActionListener(e -> {
      String username = usernameTextField.getText();
      String name = nameTextField.getText();
      String email = emailTextField.getText();
      String phone = phoneTextField.getText();
      char[] password = passwordField.getPassword();
      String passwordText = new String(password);

      if (UserBUS.getInstance().checkDuplicateUsername(username)) {
        JOptionPane.showMessageDialog(null,
            "The username is taken from different account, please try another username.");
      } else {
        UserModel newUser = new UserModel(0, username, passwordText, null, name, email, phone, null, null, null);
        int added = UserBUS.getInstance().addModel(newUser);
        if (added == 1) {
          ProfileModel.getInstance().setUser(newUser);
          JOptionPane.showMessageDialog(null, "You've successfully registered! You can log in now.");
          dispose(); // close the registration UI
        } else {
          JOptionPane.showMessageDialog(null, "Registration failed. Please try again!");
        }
      }
    });

    cancelButton.addActionListener(e -> System.exit(0));

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        int width = getContentPane().getWidth();
        if (width < 1020) {
          groupLogo.setPreferredSize(new Dimension(500, 200));

          nameStoreLabel.setFont(new Font("sansserif", 0, 16));
          titleRegister.setFont(new Font("sansserif", 0, 24));
          nameStoreLabel.setPreferredSize(new Dimension(100, 20));
          iconLabel.setIcon(new ImageIcon(getClass().getResource("/resources/book_logo_responsive.png")));

          groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          usernameLabel.setFont(new Font("sansserif", 0, 16));
          usernameLabel.setPreferredSize(new Dimension(200, 50));

          usernameTextField.setFont(new Font("sansserif", 0, 16));
          usernameTextField.setPreferredSize(new Dimension(150, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          passwordLabel.setFont(new Font("sansserif", 0, 16));
          passwordLabel.setPreferredSize(new Dimension(200, 50));

          passwordField.setFont(new Font("sansserif", 0, 16));
          passwordField.setPreferredSize(new Dimension(150, 50));

          groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          passwordLabelAgain.setFont(new Font("sansserif", 0, 16));
          passwordLabelAgain.setPreferredSize(new Dimension(200, 50));

          passwordFieldAgain.setFont(new Font("sansserif", 0, 16));
          passwordFieldAgain.setPreferredSize(new Dimension(150, 50));

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
    setPreferredSize(new Dimension(1150, 625));
    setMinimumSize(new Dimension(700, 600));
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

}
