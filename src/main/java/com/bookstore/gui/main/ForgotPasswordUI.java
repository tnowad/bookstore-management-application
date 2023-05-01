package com.bookstore.gui.main;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.button.Button;
import com.bookstore.gui.components.input.GroupInput;
import com.bookstore.gui.theme.ThemeColor;
import com.bookstore.gui.theme.ThemeFont;
import com.bookstore.models.UserModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Optional;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class ForgotPasswordUI extends JFrame {

  private static ForgotPasswordUI instance;
  private JPanel groupAccount;
  private JPanel groupContent;
  private JPanel groupLogo;
  private GroupInput groupPhone;
  private GroupInput groupEmail;

  private JLabel titleResetPassword;
  private JPanel groupForgetPassword;

  private JPanel groupButton;
  private Button resetButton;
  private Button cancelButton;
  private Button registerButton;
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
    groupEmail = new GroupInput("Email", "show");
    groupPhone = new GroupInput("Phone number", "show");
    groupForgetPassword = new JPanel();
    groupButton = new JPanel();
    resetButton = new Button("Reset");
    cancelButton = new Button("Cancel");
    registerButton = new Button("Don't have account? Register");
    icon = new ImageIcon("icon/book.png");
    iconLabel = new JLabel(icon);

    getContentPane().setLayout(new FlowLayout());

    setBackground();
    initGroupContent();
    initGroupLogo();
  }

  private void initGroupLogo() {
    groupLogo.setLayout(new BorderLayout());

    iconLabel.setIcon(
      new ImageIcon(
        getClass().getResource("../../../../resources/fogotpass_icon.png")
      )
    );
    groupLogo.setPreferredSize(new Dimension(400, 450));
    nameStoreLabel = new JLabel();
    nameStoreLabel.setText("Bookstore Management Application");
    nameStoreLabel.setForeground(Color.BLACK);
    nameStoreLabel.setFont(new ThemeFont().getMediumFont());
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
    titleResetPassword.setFont(new ThemeFont().getLargeFont());
    titleResetPassword.setForeground(Color.BLACK);
    titleResetPassword.setPreferredSize(new Dimension(100, 100));
    groupContent.add(titleResetPassword, BorderLayout.PAGE_START);

    groupAccount.setLayout(new BoxLayout(groupAccount, BoxLayout.Y_AXIS));

    // group Email
    groupEmail.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));
    groupAccount.add(groupEmail);

    // group Phone
    groupPhone.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
    groupAccount.add(groupPhone);

    groupAccount.add(groupForgetPassword, BorderLayout.EAST);

    groupContent.add(groupAccount, BorderLayout.CENTER);

    groupButton.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 50));

    resetButton.setPreferredSize(new Dimension(100, 50));
    groupButton.add(resetButton);

    cancelButton.setPreferredSize(new Dimension(100, 50));
    groupButton.add(cancelButton);

    groupButton.add(registerButton);

    groupContent.add(groupButton, BorderLayout.PAGE_END);

    getContentPane().add(groupContent, BoxLayout.X_AXIS);
  }

  private void setBackground() {
    getContentPane().setBackground(new ThemeColor().getBackground());
    groupLogo.setBackground(new ThemeColor().getBackground());
    groupContent.setBackground(new ThemeColor().getBackground());
    groupAccount.setBackground(new ThemeColor().getBackground());
    groupEmail.setBackground(Color.white);
    groupPhone.setBackground(Color.white);
    groupForgetPassword.setBackground(Color.white);
    groupButton.setBackground(Color.white);
  }

  private void handleEvent() {
    resetButton.addActionListener(e -> {
      String email = groupEmail.getTextField().getText().trim();
      String phone = groupPhone.getTextField().getText().trim();

      if (email.isEmpty() && phone.isEmpty()) {
        showError(
          "Email or phone cannot be empty, please check the input and try again."
        );
      } else {
        Optional<UserModel> optionalUser = UserBUS
          .getInstance()
          .getAllModels()
          .stream()
          .filter(user ->
            user.getEmail().equals(email) || user.getPhone().equals(phone)
          )
          .findFirst();
        if (optionalUser.isPresent()) {
          UserModel userModel = optionalUser.get();
          int userId = userModel.getId();
          RetypePasswordUI.getInstance().setInformations(userId, email, phone);

          int option = JOptionPane.showOptionDialog(
            null,
            "Based on your information, we found your account, you can now reset your password.",
            "Reset Password",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            null,
            new Object[] { "OK" },
            "OK"
          );

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
      RegisterUI.getInstance().setVisible(true);
      setVisible(false);
    });

    registerButton.addActionListener(e -> {
      RegisterUI.getInstance().setVisible(true);
      setVisible(false);
    });

    addComponentListener(
      new ComponentAdapter() {
        @Override
        public void componentResized(ComponentEvent e) {
          int width = getContentPane().getWidth();
          int height = getContentPane().getHeight();
          if (width < 1020) {
            groupLogo.setPreferredSize(new Dimension(500, 300));
            nameStoreLabel.setFont(new ThemeFont().getMediumFont());
            nameStoreLabel.setText("Forgot Password?");
            titleResetPassword.setFont(new ThemeFont().getSmallFont());
            titleResetPassword.setText("You can reset your password here");
            nameStoreLabel.setPreferredSize(new Dimension(100, 20));
            iconLabel.setIcon(
              new ImageIcon(
                getClass()
                  .getResource("../../../../resources/fogotpass_icon.png")
              )
            );

            groupEmail.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

            resetButton.setPreferredSize(new Dimension(100, 35));
            cancelButton.setPreferredSize(new Dimension(100, 35));
            registerButton.setPreferredSize(new Dimension(200, 35));

            if (height < 600) {
              nameStoreLabel.setFont(new ThemeFont().getSmallFont());
              iconLabel.setIcon(
                new ImageIcon(
                  getClass()
                    .getResource(
                      "../../../../resources/fogotpass_reponsive.png"
                    )
                )
              );
              groupLogo.setPreferredSize(new Dimension(500, 200));
            }
          } else {
            nameStoreLabel.setVisible(false);
            initGroupContent();
            initGroupLogo();
          }
          revalidate();
          repaint();
        }
      }
    );
  }

  private void showError(String message) {
    JOptionPane.showMessageDialog(
      null,
      message,
      "Error",
      JOptionPane.ERROR_MESSAGE
    );
  }

  private void initFrame() {
    setPreferredSize(new Dimension(600, 700));
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
