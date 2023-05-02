package com.bookstore.gui.forms.accounts;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.passwordfields.PasswordField;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AccountSettings extends JPanel {

  private Button resetButton;
  private PasswordField confirmNewPasswordField;
  private Label confirmNewPasswordLabel;
  private PasswordField currentPasswordField;
  private Label currentPasswordLabel;
  private JPanel groupButton;
  private Label newPassordLabel;
  private PasswordField newPasswordField;
  private Button updateButton;
  private JToggleButton toggleCurrentPasswordButton;
  private ImageIcon showPassword;
  private ImageIcon hidePassword;
  private JToggleButton toggleNewPasswordButton;
  private JToggleButton toggleConfirmNewPasswordButton;
  private static AccountSettings instance;

  UserBUS userBus = UserBUS.getInstance();
  UserModel currentUser = Authentication.getCurrentUser();

  private AccountSettings() {
    initComponents();
  }

  public static AccountSettings getInstance() {
    if (instance == null) {
      instance = new AccountSettings();
    }
    return instance;
  }

  private void initComponents() {
    currentPasswordLabel = new Label("Your current password");
    currentPasswordField = new PasswordField();
    newPassordLabel = new Label("New password");
    newPasswordField = new PasswordField();
    confirmNewPasswordLabel = new Label("Confirm new password");
    confirmNewPasswordField = new PasswordField();
    groupButton = new JPanel();
    updateButton = new Button("Update");
    resetButton = new Button("Reset");
    showPassword = new ImageIcon("src/main/java/resources/icons/show_password.png");
    hidePassword = new ImageIcon("src/main/java/resources/icons/hide_password.png");
    toggleCurrentPasswordButton = new JToggleButton(hidePassword);
    toggleNewPasswordButton = new JToggleButton(hidePassword);
    toggleConfirmNewPasswordButton = new JToggleButton(hidePassword);

    updateButton.addActionListener(updateButtonActionListener);
    resetButton.addActionListener(resetButtonActionListener);
    toggleCurrentPasswordButton.addActionListener(
        toggleCurrentPasswordActionListener);
    toggleNewPasswordButton.addActionListener(toggleNewPasswordActionListener);
    toggleConfirmNewPasswordButton.addActionListener(
        toggleConfirmNewPasswordActionListener);

    setMaximumSize(new Dimension(300, 200));
    setMinimumSize(new Dimension(300, 200));
    setPreferredSize(new Dimension(300, 250));
    setLayout(new GridLayout(7, 1));

    currentPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
    currentPasswordLabel.setVerticalAlignment(SwingConstants.BOTTOM);

    newPassordLabel.setHorizontalAlignment(SwingConstants.LEFT);
    newPassordLabel.setVerticalAlignment(SwingConstants.BOTTOM);

    confirmNewPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
    confirmNewPasswordLabel.setVerticalAlignment(SwingConstants.BOTTOM);

    JPanel currentPasswordFieldPanel = new JPanel(new BorderLayout());
    currentPasswordFieldPanel.add(currentPasswordField, BorderLayout.CENTER);
    currentPasswordFieldPanel.add(
        toggleCurrentPasswordButton,
        BorderLayout.EAST);
    add(currentPasswordLabel);
    add(currentPasswordFieldPanel);

    JPanel newPasswordFieldPanel = new JPanel(new BorderLayout());
    newPasswordFieldPanel.add(newPasswordField, BorderLayout.CENTER);
    newPasswordFieldPanel.add(toggleNewPasswordButton, BorderLayout.EAST);
    add(newPassordLabel);
    add(newPasswordFieldPanel);

    JPanel confirmNewPasswordFieldPanel = new JPanel(new BorderLayout());
    confirmNewPasswordFieldPanel.add(
        confirmNewPasswordField,
        BorderLayout.CENTER);
    confirmNewPasswordFieldPanel.add(
        toggleConfirmNewPasswordButton,
        BorderLayout.EAST);
    add(confirmNewPasswordLabel);
    add(confirmNewPasswordFieldPanel);
    groupButton.add(updateButton);

    groupButton.add(resetButton);

    add(groupButton);
  }

  private ActionListener updateButtonActionListener = e -> {
    String currentPassword = new String(currentPasswordField.getPassword());
    if (currentPassword.equals(currentUser.getPassword())) {
      String newPassword = new String(newPasswordField.getPassword());
      String confirmPassword = new String(
          confirmNewPasswordField.getPassword());
      if (newPassword.equals(confirmPassword)) {
        currentUser.setPassword(newPassword);
        userBus.updateModel(currentUser);
        JOptionPane.showMessageDialog(null, "Password updated successfully");
        resetButton.setEnabled(false);
        updateButton.setEnabled(false);
      } else {
        JOptionPane.showMessageDialog(
            null,
            "New password and confirm password do not match");
      }
    } else {
      JOptionPane.showMessageDialog(null, "Current password does not match");
    }
  };

  private ActionListener resetButtonActionListener = e -> {
    currentPasswordField.setText("");
    confirmNewPasswordField.setText("");
    newPasswordField.setText("");
    // resetButton.setEnabled(false);
    // updateButton.setEnabled(false);
  };

  private ActionListener toggleCurrentPasswordActionListener = e -> {
    if (toggleCurrentPasswordButton.isSelected()) {
      currentPasswordField.setEchoChar((char) 0);
      toggleCurrentPasswordButton.setIcon(showPassword);
      System.out.println(1);
    } else {
      currentPasswordField.setEchoChar('\u25cf');
      toggleCurrentPasswordButton.setIcon(hidePassword);
    }
  };

  private ActionListener toggleNewPasswordActionListener = e -> {
    if (toggleNewPasswordButton.isSelected()) {
      newPasswordField.setEchoChar((char) 0);
      toggleConfirmNewPasswordButton.setIcon(showPassword);
    } else {
      newPasswordField.setEchoChar('\u25cf');
      toggleNewPasswordButton.setIcon(hidePassword);
    }
  };

  private ActionListener toggleConfirmNewPasswordActionListener = e -> {
    if (toggleConfirmNewPasswordButton.isSelected()) {
      confirmNewPasswordField.setEchoChar((char) 0);
      toggleConfirmNewPasswordButton.setIcon(showPassword);
    } else {
      confirmNewPasswordField.setEchoChar('\u25cf');
      toggleConfirmNewPasswordButton.setIcon(hidePassword);
    }
  };
}
