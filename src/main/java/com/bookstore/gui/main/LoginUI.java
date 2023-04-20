package com.bookstore.gui.main;

import com.bookstore.models.CurrentUserModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import com.bookstore.bus.CurrentUserBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.input.GroupInput;
import com.bookstore.gui.form.salesman.view.SalesmanFrame;
import com.bookstore.models.UserModel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class LoginUI extends JFrame {
  private static LoginUI instance;
  private JPanel groupAccount;
  private JPanel groupContent;
  private JPanel groupLogo;
  private GroupInput groupUsername;
  private GroupInput groupPassword;

  private JLabel titleLogin;
  private JPanel groupForgotPassword;
  private Button forgetButton;

  private JPanel groupButton;
  private Button loginButton;
  private Button cancelButton;
  private Button registerButton;
  private JLabel iconLabel;
  private JLabel nameStoreLabel;
  

  public LoginUI() {
    initComponent();
    handleEvent();
    initFrame();
  }

  private void updateCurrentUser() {
    UserBUS userBus = UserBUS.getInstance();
    List<UserModel> userList = userBus.getAllModels();
    String username = groupUsername.getTextField().getText();
    UserModel userModel = userBus.getModelByUsername(username);

    CurrentUserBUS currentUserBus = CurrentUserBUS.getInstance();
    List<CurrentUserModel> currentUser = currentUserBus.getAllModels();
    int idPrev = currentUser.get(0).getCurrentUserId();
    System.out.println(idPrev);
    CurrentUserModel currentUserModel = new CurrentUserModel(userModel.getId());
    int i = currentUserBus.updateModel(currentUserModel);
    System.out.println(i);
    int idNext = currentUser.get(0).getCurrentUserId();
    System.out.println("id sau: " + idNext);
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

    groupForgotPassword = new JPanel();
    forgetButton = new Button("Forgot password");
    groupButton = new JPanel();
    loginButton = new Button("Login");
    cancelButton = new Button("Cancel");
    registerButton = new Button("Register");
    icon = new ImageIcon("../../../../resources/book_logo.png");
    iconLabel = new JLabel(icon);
    nameStoreLabel = new JLabel("Bookstore Management Application");
    // nameStoreLabel.setForeground(Color.BLUE);

    getContentPane().setLayout(new FlowLayout());

    setBackground();
    initGroupContent();
    initGroupLogo();
  }

  private void initGroupLogo() {
    groupLogo.setLayout(new BorderLayout());

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

    titleLogin.setHorizontalAlignment(SwingConstants.CENTER);
    titleLogin.setText("Login");
    titleLogin.setFont(new ThemeFont().getLargeFont());
    titleLogin.setForeground(Color.BLUE);
    titleLogin.setPreferredSize(new Dimension(100, 100));
    groupContent.add(titleLogin, BorderLayout.PAGE_START);

    groupAccount.setLayout(new BoxLayout(groupAccount, BoxLayout.Y_AXIS));

    // group username
    groupAccount.add(groupUsername);

    // group password
    groupAccount.add(groupPassword);

    // group forget password
    groupForgotPassword.setLayout(new FlowLayout(FlowLayout.RIGHT));

    forgetButton.setPreferredSize(new Dimension(200, 20));
    forgetButton.setForeground(new Color(180, 0, 0));
    forgetButton.setBackground(Color.white);
    forgetButton.hoverBackground(Color.WHITE, Color.WHITE, new Color(180, 0, 0), new Color(255, 0, 0));
    groupForgotPassword.setBorder(BorderFactory.createEmptyBorder());

    groupForgotPassword.add(forgetButton);

    groupAccount.add(groupForgotPassword, BorderLayout.EAST);

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
    groupForgotPassword.setBackground(Color.white);
    groupButton.setBackground(Color.white);
  }

  private void handleEvent() {
    loginButton.addActionListener(e -> {
      String username = groupUsername.getTextField().getText();
      char[] password = groupPassword.getPasswordField().getPassword();
      String passwordFld = new String(password);
      if (username.isEmpty() || passwordFld.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please enter username and password");
        return;
      }
      try {
        UserModel userModel = UserBUS.getInstance().getModelByUsername(username);
        if (passwordFld.equals(userModel.getPassword())) {
          JOptionPane.showMessageDialog(null, "Logged in successfully");
          updateCurrentUser();
          switch (userModel.getRole()) {
            case CUSTOMER -> {
            }
            case EMPLOYEE -> {
              dispose();
              new SalesmanFrame();
            }
            case ADMIN -> {
            }
            default -> {
              break;
            }
          }
        } else {
          JOptionPane.showMessageDialog(null,
              "Invalid username or password. Please check the username or password and try again.");
        }
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "An error occurred while logging in. Please try again." + ex);
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

          nameStoreLabel.setFont(new ThemeFont().getSmallFont());
          titleLogin.setFont(new ThemeFont().getLargeFont());
          nameStoreLabel.setPreferredSize(new Dimension(100, 20));
          iconLabel.setIcon(new ImageIcon(getClass().getResource("../../../../resources/book_logo_responsive.png")));

          groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupUsername.getLabel().setFont(new ThemeFont().getSmallFont());
          groupUsername.getLabel().setPreferredSize(new Dimension(100, 50));

          groupUsername.getTextField().setFont(new ThemeFont().getSmallFont());
          groupUsername.getTextField().setPreferredSize(new Dimension(150, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));

          groupPassword.getLabel().setFont(new ThemeFont().getSmallFont());
          groupPassword.getLabel().setPreferredSize(new Dimension(100, 50));

          groupPassword.getPasswordField().setFont(new ThemeFont().getSmallFont());
          groupPassword.getPasswordField().setPreferredSize(new Dimension(150, 50));

          cancelButton.setPreferredSize(new Dimension(100, 35));
          registerButton.setPreferredSize(new Dimension(100, 35));
          loginButton.setPreferredSize(new Dimension(100, 35));

        } else {
          groupUsername.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

          groupUsername.getLabel().setFont(new ThemeFont().getMediumFont());
          groupUsername.getLabel().setPreferredSize(new Dimension(120, 50));

          groupUsername.getTextField().setFont(new ThemeFont().getMediumFont());
          groupUsername.getTextField().setPreferredSize(new Dimension(300, 50));

          groupPassword.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 50));

          groupPassword.getLabel().setFont(new ThemeFont().getMediumFont());
          groupPassword.getLabel().setPreferredSize(new Dimension(120, 50));

          groupPassword.getPasswordField().setFont(new ThemeFont().getMediumFont());
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

  public static void main(String[] args) {
    try {
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
      UIManager.put("Button.background", Color.BLUE);
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
        | UnsupportedLookAndFeelException e) {
      e.printStackTrace();
    }
    new LoginUI();
  }
}
