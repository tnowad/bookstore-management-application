package com.bookstore.gui.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.component.GroupInput;
//import com.bookstore.model.ProfileModel;
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
  private GroupInput groupFullname;
  private GroupInput groupEmail;
  private GroupInput groupPhone;

  private JPanel groupButton;
  private JButton loginButton;
  private JButton cancelButton;
  private JButton registerButton;
  private JLabel iconLabel;
  private JLabel nameStoreLabel;

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
    groupFullname = new GroupInput("Fullname", "show");
    groupEmail = new GroupInput("Email", "show");
    groupPhone = new GroupInput("Phone", "show");
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
    iconLabel.setIcon(new ImageIcon(getClass().getResource("../../../../resources/book_logo.png")));
    groupLogo.setPreferredSize(new Dimension(400, 450));

    nameStoreLabel.setFont(new Font("Arial", 0, 24));
    nameStoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
    nameStoreLabel.setPreferredSize(new Dimension(100, 50));
    groupLogo.add(nameStoreLabel, BorderLayout.CENTER);
    groupLogo.add(iconLabel, BorderLayout.PAGE_START);

    getContentPane().add(groupLogo, BoxLayout.X_AXIS);
  }

  private void initGroupContent() {
    groupContent.setLayout(new BorderLayout());

    titleRegister.setFont(new Font("Arial", 0, 48));
    titleRegister.setHorizontalAlignment(SwingConstants.CENTER);
    titleRegister.setText("Register");
    titleRegister.setFont(new Font("Arial", 0, 50));
    titleRegister.setForeground(Color.BLUE);
    titleRegister.setPreferredSize(new Dimension(100, 100));
    groupContent.add(titleRegister, BorderLayout.PAGE_START);

    groupAccount.setLayout(new BoxLayout(groupAccount, BoxLayout.Y_AXIS));

    // group fullname
    groupFullname.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    groupAccount.add(groupFullname);

    // group email
    groupEmail.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    groupAccount.add(groupEmail);

    // group phone
    groupPhone.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    groupAccount.add(groupPhone);

    // group username
    groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    groupAccount.add(groupUsername);

    // group password
    groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
    groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
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
    groupFullname.setBackground(Color.white);
    groupEmail.setBackground(Color.white);
    groupPhone.setBackground(Color.white);
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
      String name = groupFullname.getTextField().getText();
      String email = groupEmail.getTextField().getText();
      String phone = groupPhone.getTextField().getText();
      char[] password = groupPassword.getPasswordField().getPassword();
      String passwordText = new String(password);

      UserModel newUser = new UserModel(0, username, passwordText, null, name, email, phone, null, null, null);
      // Check if the username, email, phone is already taken
      ArrayList<String> checkDuplicate = new ArrayList<>();
      checkDuplicate.add(username);
      checkDuplicate.add(email);
      checkDuplicate.add(phone);

      for (int i = 0; i < checkDuplicate.size(); i++) {
        String field = "";
        switch (i) {
          case 0:
            field = "username";
            break;
          case 1:
            field = "email";
            break;
          case 2:
            field = "phone";
            break;
        }
        try {
          if (UserBUS.getInstance().checkForDuplicate(checkDuplicate, new String[] { field })) {
            JOptionPane.showMessageDialog(null,
                "The " + field + " is already taken. Please try another " + field + ".");
            return;
          }
        } catch (HeadlessException | ClassNotFoundException | SQLException e1) {
          e1.printStackTrace();
        }
      }

      try {
        int added = UserBUS.getInstance().addModel(newUser);
        if (added == 1) {
          // ProfileModel.getInstance().setUser(newUser);
          JOptionPane.showMessageDialog(null, "You've successfully registered! You can log in now.");
          dispose(); // close the registration UI
        } else {
          JOptionPane.showMessageDialog(null, "Registration failed. Please try again!");
        }
      } catch (ClassNotFoundException | SQLException e1) {
        e1.printStackTrace();
      }
    });

    cancelButton.addActionListener(e -> System.exit(0));

    addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        int width = getContentPane().getWidth();
        if (width < 1020) {
          setPreferredSize(new Dimension(1150, 800));
          groupLogo.setPreferredSize(new Dimension(500, 200));

          nameStoreLabel.setFont(new Font("Arial", 0, 16));
          titleRegister.setFont(new Font("Arial", 0, 24));
          nameStoreLabel.setPreferredSize(new Dimension(100, 20));
          iconLabel.setIcon(new ImageIcon(getClass().getResource("../../../../resources/book_logo_responsive.png")));

          groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupUsername.getLabel().setFont(new Font("Arial", 0, 16));
          groupUsername.getLabel().setPreferredSize(new Dimension(200, 50));

          groupUsername.getTextField().setFont(new Font("Arial", 0, 16));
          groupUsername.getTextField().setPreferredSize(new Dimension(150, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupPassword.getLabel().setFont(new Font("Arial", 0, 16));
          groupPassword.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPassword.getPasswordField().setFont(new Font("Arial", 0, 16));
          groupPassword.getPasswordField().setPreferredSize(new Dimension(150, 50));

          groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupPasswordAgain.getLabel().setFont(new Font("Arial", 0, 16));
          groupPasswordAgain.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPasswordAgain.getPasswordField().setFont(new Font("Arial", 0, 16));
          groupPasswordAgain.getPasswordField().setPreferredSize(new Dimension(150, 50));

          groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
          groupPasswordAgain.getLabel().setFont(new Font("Arial", 0, 16));
          groupPasswordAgain.getLabel().setPreferredSize(new Dimension(200, 50));
          groupPasswordAgain.getPasswordField().setFont(new Font("Arial", 0, 16));
          groupPasswordAgain.getPasswordField().setPreferredSize(new Dimension(150, 50));

          groupFullname.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
          groupFullname.getLabel().setFont(new Font("Arial", 0, 16));
          groupFullname.getLabel().setPreferredSize(new Dimension(200, 50));
          groupFullname.getTextField().setFont(new Font("Arial", 0, 16));
          groupFullname.getTextField().setPreferredSize(new Dimension(150, 50));

          groupEmail.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
          groupEmail.getLabel().setFont(new Font("Arial", 0, 16));
          groupEmail.getLabel().setPreferredSize(new Dimension(200, 50));
          groupEmail.getTextField().setFont(new Font("Arial", 0, 16));
          groupEmail.getTextField().setPreferredSize(new Dimension(150, 50));

          groupPhone.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
          groupPhone.getLabel().setFont(new Font("Arial", 0, 16));
          groupPhone.getLabel().setPreferredSize(new Dimension(200, 50));
          groupPhone.getTextField().setFont(new Font("Arial", 0, 16));
          groupPhone.getTextField().setPreferredSize(new Dimension(150, 50));

          cancelButton.setPreferredSize(new Dimension(100, 35));
          registerButton.setPreferredSize(new Dimension(100, 35));
          loginButton.setPreferredSize(new Dimension(300, 35));

        } else {
          setPreferredSize(new Dimension(1150, 625));

          groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
          groupUsername.getLabel().setFont(new Font("Arial", 0, 24));
          groupUsername.getLabel().setPreferredSize(new Dimension(200, 50));
          groupUsername.getTextField().setFont(new Font("Arial", 0, 24));
          groupUsername.getTextField().setPreferredSize(new Dimension(300, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

          groupPassword.getLabel().setFont(new Font("Arial", 0, 24));
          groupPassword.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPassword.getPasswordField().setFont(new Font("Arial", 0, 24));
          groupPassword.getPasswordField().setPreferredSize(new Dimension(300, 50));

          groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

          groupPasswordAgain.getLabel().setFont(new Font("Arial", 0, 24));
          groupPasswordAgain.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPasswordAgain.getPasswordField().setFont(new Font("Arial", 0, 24));
          groupPasswordAgain.getPasswordField().setPreferredSize(new Dimension(300, 50));

          groupFullname.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
          groupFullname.getLabel().setFont(new Font("Arial", 0, 24));
          groupFullname.getLabel().setPreferredSize(new Dimension(200, 50));
          groupFullname.getTextField().setFont(new Font("Arial", 0, 24));
          groupFullname.getTextField().setPreferredSize(new Dimension(300, 50));

          groupEmail.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
          groupEmail.getLabel().setFont(new Font("Arial", 0, 24));
          groupEmail.getLabel().setPreferredSize(new Dimension(200, 50));
          groupEmail.getTextField().setFont(new Font("Arial", 0, 24));
          groupEmail.getTextField().setPreferredSize(new Dimension(300, 50));

          groupPhone.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
          groupPhone.getLabel().setFont(new Font("Arial", 0, 24));
          groupPhone.getLabel().setPreferredSize(new Dimension(200, 50));
          groupPhone.getTextField().setFont(new Font("Arial", 0, 24));
          groupPhone.getTextField().setPreferredSize(new Dimension(300, 50));

          initGroupContent();
          initGroupLogo();
        }
        revalidate();
        repaint();
      }
    });
  }

  private void initFrame() {
    setPreferredSize(new Dimension(1150, 800));
    setMinimumSize(new Dimension(700, 600));
    setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args) {
    new RegisterUI();
  }

}
