package com.bookstore.gui.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Optional;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

import com.bookstore.bus.UserBUS;
import com.bookstore.model.UserModel;

public class ForgotPasswordUI extends JFrame {
  private static ForgotPasswordUI instance;
  private JPanel groupAccount;
  private JPanel groupContent;
  private JPanel groupLogo;
  private JPanel groupPhone;
  private JPanel groupEmail;

  private JLabel titleResetPassword;
  private JLabel emailLabel;
  private JTextField emailTextField;
  private JTextField phoneTextField;
  private JLabel phoneLabel;
  private JPanel groupForgetPassword;

  private JPanel groupButton;
  private JButton resetButton;
  private JButton cancelButton;
  private JButton registerButton;
  private JLabel iconLabel;
  private JLabel nameStoreLabel;

  private ForgotPasswordUI() {
    initComponent();
    handleEvent();
    initFrame();
  }

  public static ForgotPasswordUI getInstance() {
    if (instance == null) {
      instance = new ForgotPasswordUI();
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
    emailLabel = new JLabel();
    emailTextField = new JTextField();
    groupPhone = new JPanel();
    phoneLabel = new JLabel();
    phoneTextField = new JTextField();
    groupForgetPassword = new JPanel();
    groupButton = new JPanel();
    resetButton = new JButton();
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

    // group Email
    groupEmail.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

    emailLabel.setText("Email");
    emailLabel.setFont(new Font("sansserif", 0, 24));
    emailLabel.setPreferredSize(new Dimension(120, 50));
    groupEmail.add(emailLabel);

    emailTextField.setPreferredSize(new Dimension(300, 50));
    Border borderemailTextField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    emailTextField.setBorder(borderemailTextField);
    groupEmail.add(emailTextField);

    groupAccount.add(groupEmail);

    // group Phone
    groupPhone.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));

    phoneLabel.setText("Phone");
    phoneLabel.setFont(new Font("sansserif", 0, 24));
    phoneLabel.setPreferredSize(new Dimension(120, 50));
    groupPhone.add(phoneLabel);

    phoneTextField.setPreferredSize(new Dimension(300, 50));
    Border borderPasswordField = BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLUE);
    phoneTextField.setBorder(borderPasswordField);
    groupPhone.add(phoneTextField);

    groupAccount.add(groupPhone);

    groupAccount.add(groupForgetPassword, BorderLayout.EAST);

    groupContent.add(groupAccount, BorderLayout.CENTER);

    groupButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

    resetButton.setText("Reset");
    resetButton.setPreferredSize(new Dimension(100, 50));
    groupButton.add(resetButton);

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
    resetButton.setBackground(Color.white);
    cancelButton.setBackground(Color.white);
    registerButton.setBackground(Color.white);
  }

  private void handleEvent() {
    UserBUS userBUS = UserBUS.getInstance();

    resetButton.addActionListener(e -> {
      String email = emailTextField.getText().trim();
      String phone = phoneTextField.getText().trim();

      if (email.isEmpty() && phone.isEmpty()) {
        showError("Email or phone cannot be empty, please check the input and try again.");
      } else if (!email.isEmpty() && !userBUS.isValidEmailAddress(email)) {
        showError("Invalid email format, please check the input and try again.");
      } else {
        Optional<UserModel> optionalUser = userBUS.getAllModels().stream()
            .filter(user -> user.getEmail().equals(email) || user.getPhone().equals(phone))
            .findFirst();
        if (optionalUser.isPresent()) {
          UserModel userModel = optionalUser.get();
          int userId = userModel.getId();
          RetypePasswordUI.getInstance().setInformations(userId, email, phone);

          int option = JOptionPane.showOptionDialog(null,
              "Based on your information, we found your account, you can now reset your password.",
              "Reset Password", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
              null, new Object[] { "OK" }, "OK");

          if (option == 0) {
            ForgotPasswordUI.getInstance().setVisible(true);
            setVisible(false);
          }
        } else {
          showError("User not found, please check the input and try again.");
        }
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

          emailLabel.setFont(new Font("sansserif", 0, 16));
          emailLabel.setPreferredSize(new Dimension(100, 50));

          emailTextField.setFont(new Font("sansserif", 0, 16));
          emailTextField.setPreferredSize(new Dimension(150, 50));

          groupPhone.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          phoneLabel.setFont(new Font("sansserif", 0, 16));
          phoneLabel.setPreferredSize(new Dimension(100, 50));

          phoneTextField.setFont(new Font("sansserif", 0, 16));
          phoneTextField.setPreferredSize(new Dimension(150, 50));

          cancelButton.setPreferredSize(new Dimension(100, 35));
          registerButton.setPreferredSize(new Dimension(100, 35));
          resetButton.setPreferredSize(new Dimension(300, 35));

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

  private void initFrame() {
    setPreferredSize(new Dimension(1100, 550));
    setMinimumSize(new Dimension(700, 600));
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public static void main(String[] args) {
    new ForgotPasswordUI();
  }

}
