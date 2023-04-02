package com.bookstore.gui.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.GroupInput;
import com.bookstore.model.ProfileModel;
import com.bookstore.model.UserModel;

public class RegisterUI extends JFrame {
  private static RegisterUI instance;
  private JPanel groupAccount;
  private JPanel groupContent;
  private JPanel groupLogo;
  private JLabel titleRegister;
  private GroupInput groupUsername;
  private GroupInput groupPassword;
  private GroupInput groupPasswordAgain;

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
    groupUsername = new GroupInput("Username", "show");
    groupPassword = new GroupInput("Password", "hide");
    groupPasswordAgain = new GroupInput("Password Again", "hide");
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
    groupAccount.add(groupUsername);
    groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
    // group password
    groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
    groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 30));
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
      String username = groupUsername.getTextField().getText();
      String name = nameTextField.getText();
      String email = emailTextField.getText();
      String phone = phoneTextField.getText();
      char[] password = groupPassword.getPasswordField().getPassword();
      String passwordText = new String(password);

      // Check if the required fields are empty or contain only whitespace
      if (username.trim().isEmpty() || name.trim().isEmpty() || passwordText.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Username, name and password cannot be empty. Please try again.");
        return;
      }

      if (!email.isEmpty() && UserBUS.getInstance().isValidEmailAddress(email)) {
        JOptionPane.showMessageDialog(null, "Please enter a valid email address.");
        return;
      }

      // Check if the username is already taken
      if (UserBUS.getInstance().checkDuplicateUsername(username)) {
        JOptionPane.showMessageDialog(null, "The username is already taken. Please try another username.");
        return;
      }

      UserModel newUser = new UserModel(0, username, passwordText, null, name, email, phone, null, null, null);
      int added = UserBUS.getInstance().addModel(newUser);
      if (added == 1) {
        ProfileModel.getInstance().setUser(newUser);
        JOptionPane.showMessageDialog(null, "You've successfully registered! You can log in now.");
        dispose(); // close the registration UI
      } else {
        JOptionPane.showMessageDialog(null, "Registration failed. Please try again!");
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

          groupUsername.getLabel().setFont(new Font("sansserif", 0, 16));
          groupUsername.getLabel().setPreferredSize(new Dimension(200, 50));

          groupUsername.getTextField().setFont(new Font("sansserif", 0, 16));
          groupUsername.getTextField().setPreferredSize(new Dimension(150, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupPassword.getLabel().setFont(new Font("sansserif", 0, 16));
          groupPassword.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPassword.getPasswordField().setFont(new Font("sansserif", 0, 16));
          groupPassword.getPasswordField().setPreferredSize(new Dimension(150, 50));

          groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupPasswordAgain.getLabel().setFont(new Font("sansserif", 0, 16));
          groupPasswordAgain.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPasswordAgain.getPasswordField().setFont(new Font("sansserif", 0, 16));
          groupPasswordAgain.getPasswordField().setPreferredSize(new Dimension(150, 50));

          cancelButton.setPreferredSize(new Dimension(100, 35));
          registerButton.setPreferredSize(new Dimension(100, 35));
          loginButton.setPreferredSize(new Dimension(300, 35));

        } else {
          groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

          groupUsername.getLabel().setFont(new Font("sansserif", 0, 24));
          groupUsername.getLabel().setPreferredSize(new Dimension(200, 50));

          groupUsername.getTextField().setFont(new Font("sansserif", 0, 24));
          groupUsername.getTextField().setPreferredSize(new Dimension(300, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

          groupPassword.getLabel().setFont(new Font("sansserif", 0, 24));
          groupPassword.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPassword.getPasswordField().setFont(new Font("sansserif", 0, 24));
          groupPassword.getPasswordField().setPreferredSize(new Dimension(300, 50));

          groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));

          groupPasswordAgain.getLabel().setFont(new Font("sansserif", 0, 24));
          groupPasswordAgain.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPasswordAgain.getPasswordField().setFont(new Font("sansserif", 0, 24));
          groupPasswordAgain.getPasswordField().setPreferredSize(new Dimension(300, 50));
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
