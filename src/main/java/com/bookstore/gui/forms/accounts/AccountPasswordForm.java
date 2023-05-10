package com.bookstore.gui.forms.accounts;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.InputValidator;
import com.bookstore.util.PasswordUtils;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class AccountPasswordForm extends JPanel {

  private JLabel currentPasswordLabel;
  private JLabel newPasswordLabel;
  private JLabel confirmNewPasswordLabel;
  private JPasswordField confirmNewPasswordField;
  private JPasswordField currentPasswordField;
  private JPasswordField newPasswordField;
  private Button updateButton;

  UserModel currentUser = Authentication.getCurrentUser();

  public AccountPasswordForm() {
    initComponents();
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    currentPasswordLabel = new JLabel("Current Password");
    currentPasswordField = new JPasswordField();
    newPasswordLabel = new JLabel("New Password");
    newPasswordField = new JPasswordField();
    confirmNewPasswordLabel = new JLabel("Confirm New Password");
    confirmNewPasswordField = new JPasswordField();
    updateButton = new Button("Update");
    updateButton.addActionListener(updateButtonActionListener);

    JPanel formPanel = new JPanel(new GridLayout(0, 2));
    formPanel.setBackground(Color.WHITE);
    formPanel.add(currentPasswordLabel);
    formPanel.add(currentPasswordField);
    formPanel.add(newPasswordLabel);
    formPanel.add(newPasswordField);
    formPanel.add(confirmNewPasswordLabel);
    formPanel.add(confirmNewPasswordField);

    JScrollPane scrollPane = new JScrollPane(
      new JPanel() {
        {
          setLayout(new BorderLayout());
          setBackground(Color.WHITE);
          add(formPanel, BorderLayout.NORTH);
        }
      },
      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    scrollPane.setBorder(null);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
    add(scrollPane, BorderLayout.CENTER);

    JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    actionPanel.setBackground(Color.WHITE);
    actionPanel.add(updateButton);
    add(actionPanel, BorderLayout.SOUTH);
  }

  private ActionListener updateButtonActionListener = e -> {
    try {
      String currentPassword = InputValidator.validatePassword(
        String.valueOf(currentPasswordField.getPassword())
      );
      String newPassword = InputValidator.validatePassword(
        String.valueOf(newPasswordField.getPassword())
      );
      InputValidator.validateConfirmPassword(
        String.valueOf(confirmNewPasswordField.getPassword()),
        newPassword
      );
      if (
        PasswordUtils.checkPassword(currentPassword, currentUser.getPassword())
      ) {
        currentUser.setPassword(PasswordUtils.hashPassword(newPassword));
        UserBUS.getInstance().updateModel(currentUser);
        JOptionPane.showMessageDialog(
          null,
          "Password has been updated successfully",
          "Success",
          JOptionPane.INFORMATION_MESSAGE
        );
      } else {
        JOptionPane.showMessageDialog(
          null,
          "Current password is incorrect",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      }
    } catch (Exception exception) {
      JOptionPane.showMessageDialog(
        null,
        exception.getMessage(),
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
    }
  };
}
