package com.bookstore.gui.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.input.GroupInput;
import com.bookstore.models.UserModel;

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
  private Button loginButton;
  private Button cancelButton;
  private Button registerButton;
  private JLabel iconLabel;
  private JLabel nameStoreLabel;

  public RegisterUI() {
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
    loginButton = new Button("Do you have account? Login");
    cancelButton = new Button("Cancel");
    registerButton = new Button("Register");
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

    nameStoreLabel.setFont(new ThemeFont().getMediumFont());
    nameStoreLabel.setHorizontalAlignment(SwingConstants.CENTER);
    nameStoreLabel.setPreferredSize(new Dimension(100, 50));
    groupLogo.add(nameStoreLabel, BorderLayout.CENTER);
    groupLogo.add(iconLabel, BorderLayout.PAGE_START);

    getContentPane().add(groupLogo, BoxLayout.X_AXIS);
  }

  private void initGroupContent() {
    groupContent.setLayout(new BorderLayout());

    titleRegister.setFont(new ThemeFont().getLargeFont());
    titleRegister.setHorizontalAlignment(SwingConstants.CENTER);
    titleRegister.setText("Register");
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

    registerButton.setPreferredSize(new Dimension(100, 50));
    groupButton.add(registerButton);

    cancelButton.setPreferredSize(new Dimension(100, 50));
    groupButton.add(cancelButton);

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
    groupButton.setBackground(Color.white);
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
      char[] passwordAgain = groupPasswordAgain.getPasswordField().getPassword();
      String passwordText = new String(password);
      String passwordAgainText = new String(passwordAgain);

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
        } catch (HeadlessException e1) {
          e1.printStackTrace();
        }
      }

      if (!passwordText.equals(passwordAgainText)) {
        JOptionPane.showMessageDialog(null, "Your password doesn't match. Please check and re-type password.");
        return;
      }

      UserModel newUser = new UserModel(0, username, passwordText, null, name, email, phone, null, null, null);
      int added = UserBUS.getInstance().addModel(newUser);
      if (added == 1) {
        // ProfileModel.getInstance().setUser(newUser);
        JOptionPane.showMessageDialog(null, "You've successfully registered! You can log in now.");
        dispose(); // close the registration UI
        new LoginUI();
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
          setPreferredSize(new Dimension(1150, 800));
          groupLogo.setPreferredSize(new Dimension(500, 200));

          nameStoreLabel.setFont(new ThemeFont().getSmallFont());
          titleRegister.setFont(new ThemeFont().getMediumFont());
          nameStoreLabel.setPreferredSize(new Dimension(100, 20));
          iconLabel.setIcon(new ImageIcon(getClass().getResource("../../../../resources/book_logo_responsive.png")));

          groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupUsername.getLabel().setFont(new ThemeFont().getSmallFont());
          groupUsername.getLabel().setPreferredSize(new Dimension(200, 50));

          groupUsername.getTextField().setFont(new ThemeFont().getSmallFont());
          groupUsername.getTextField().setPreferredSize(new Dimension(150, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupPassword.getLabel().setFont(new ThemeFont().getSmallFont());
          groupPassword.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPassword.getPasswordField().setFont(new ThemeFont().getSmallFont());
          groupPassword.getPasswordField().setPreferredSize(new Dimension(150, 50));

          groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupPasswordAgain.getLabel().setFont(new ThemeFont().getSmallFont());
          groupPasswordAgain.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPasswordAgain.getPasswordField().setFont(new ThemeFont().getSmallFont());
          groupPasswordAgain.getPasswordField().setPreferredSize(new Dimension(150, 50));

          groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
          groupPasswordAgain.getLabel().setFont(new ThemeFont().getSmallFont());
          groupPasswordAgain.getLabel().setPreferredSize(new Dimension(200, 50));
          groupPasswordAgain.getPasswordField().setFont(new ThemeFont().getSmallFont());
          groupPasswordAgain.getPasswordField().setPreferredSize(new Dimension(150, 50));

          groupFullname.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
          groupFullname.getLabel().setFont(new ThemeFont().getSmallFont());
          groupFullname.getLabel().setPreferredSize(new Dimension(200, 50));
          groupFullname.getTextField().setFont(new ThemeFont().getSmallFont());
          groupFullname.getTextField().setPreferredSize(new Dimension(150, 50));

          groupEmail.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
          groupEmail.getLabel().setFont(new ThemeFont().getSmallFont());
          groupEmail.getLabel().setPreferredSize(new Dimension(200, 50));
          groupEmail.getTextField().setFont(new ThemeFont().getSmallFont());
          groupEmail.getTextField().setPreferredSize(new Dimension(150, 50));

          groupPhone.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
          groupPhone.getLabel().setFont(new ThemeFont().getSmallFont());
          groupPhone.getLabel().setPreferredSize(new Dimension(200, 50));
          groupPhone.getTextField().setFont(new ThemeFont().getSmallFont());
          groupPhone.getTextField().setPreferredSize(new Dimension(150, 50));

          cancelButton.setPreferredSize(new Dimension(100, 35));
          registerButton.setPreferredSize(new Dimension(100, 35));
          loginButton.setPreferredSize(new Dimension(300, 35));

        } else {
          setPreferredSize(new Dimension(1150, 625));

          groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
          groupUsername.getLabel().setFont(new ThemeFont().getMediumFont());
          groupUsername.getLabel().setPreferredSize(new Dimension(200, 50));
          groupUsername.getTextField().setFont(new ThemeFont().getMediumFont());
          groupUsername.getTextField().setPreferredSize(new Dimension(300, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

          groupPassword.getLabel().setFont(new ThemeFont().getMediumFont());
          groupPassword.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPassword.getPasswordField().setFont(new ThemeFont().getMediumFont());
          groupPassword.getPasswordField().setPreferredSize(new Dimension(300, 50));

          groupPasswordAgain.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

          groupPasswordAgain.getLabel().setFont(new ThemeFont().getMediumFont());
          groupPasswordAgain.getLabel().setPreferredSize(new Dimension(200, 50));

          groupPasswordAgain.getPasswordField().setFont(new ThemeFont().getMediumFont());
          groupPasswordAgain.getPasswordField().setPreferredSize(new Dimension(300, 50));

          groupFullname.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
          groupFullname.getLabel().setFont(new ThemeFont().getMediumFont());
          groupFullname.getLabel().setPreferredSize(new Dimension(200, 50));
          groupFullname.getTextField().setFont(new ThemeFont().getMediumFont());
          groupFullname.getTextField().setPreferredSize(new Dimension(300, 50));

          groupEmail.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
          groupEmail.getLabel().setFont(new ThemeFont().getMediumFont());
          groupEmail.getLabel().setPreferredSize(new Dimension(200, 50));
          groupEmail.getTextField().setFont(new ThemeFont().getMediumFont());
          groupEmail.getTextField().setPreferredSize(new Dimension(300, 50));

          groupPhone.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
          groupPhone.getLabel().setFont(new ThemeFont().getMediumFont());
          groupPhone.getLabel().setPreferredSize(new Dimension(200, 50));
          groupPhone.getTextField().setFont(new ThemeFont().getMediumFont());
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
