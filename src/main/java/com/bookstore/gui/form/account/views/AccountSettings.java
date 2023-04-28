package com.bookstore.gui.form.account.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import com.bookstore.bus.UserBUS;
import com.bookstore.models.CurrentUserModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;

public class AccountSettings extends JPanel {

    private JButton resetButton;
    private JPasswordField confirmNewPasswordField;
    private JLabel confirmNewPasswordLabel;
    private JPasswordField currentPasswordField;
    private JLabel currentPasswordLabel;
    private JPanel groupButton;
    private JLabel newPassordLabel;
    private JPasswordField newPasswordField;
    private JButton updateButton;

    UserBUS userBus = UserBUS.getInstance();
    UserModel currentUser = Authentication.getCurrentUser();
  


    public AccountSettings() {
        initComponents();
        handleEvent();
    }

    private void initComponents() {

        currentPasswordLabel = new JLabel();
        currentPasswordField = new JPasswordField();
        newPassordLabel = new JLabel();
        newPasswordField = new JPasswordField();
        confirmNewPasswordLabel = new JLabel();
        confirmNewPasswordField = new JPasswordField();
        groupButton = new JPanel();
        updateButton = new JButton();
        resetButton = new JButton();

        setMaximumSize(new Dimension(300, 200));
        setMinimumSize(new Dimension(300, 200));
        setPreferredSize(new Dimension(300, 200));
        setLayout(new GridLayout(7, 1));

        currentPasswordLabel.setFont(new Font("Arial", 0, 18));
        currentPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentPasswordLabel.setText("Your current password");
        currentPasswordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        add(currentPasswordLabel);
        add(currentPasswordField);

        newPassordLabel.setFont(new Font("Arial", 0, 18));
        newPassordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        newPassordLabel.setText("New password");
        newPassordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        add(newPassordLabel);
        add(newPasswordField);

        confirmNewPasswordLabel.setFont(new Font("Arial", 0, 18));
        confirmNewPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        confirmNewPasswordLabel.setText("Confirm new password");
        confirmNewPasswordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        add(confirmNewPasswordLabel);
        add(confirmNewPasswordField);

        updateButton.setText("Update");

        groupButton.add(updateButton);

        resetButton.setText("Cancel");

        groupButton.add(resetButton);

        add(groupButton);
    }

    private void handleEvent() {
        updateButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updatePasswordButtonActionPerformed(evt);
            }
        });
        resetButton.addActionListener((ActionListener) new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetPasswordButtonActionPerformed(evt);
            }
        });
    }

    private void resetPasswordButtonActionPerformed(ActionEvent evt) {
        currentPasswordField.setText("");
        confirmNewPasswordField.setText("");
        newPasswordField.setText("");

        resetButton.setEnabled(false);
        updateButton.setEnabled(false);
    }

    protected void updatePasswordButtonActionPerformed(ActionEvent evt) {
        String currentPassword = new String(currentPasswordField.getPassword());
        if (currentPassword.equals(currentUser.getPassword())) {
            String newPassword = new String(newPasswordField.getPassword());
            String confirmPassword = new String(confirmNewPasswordField.getPassword());
            if (newPassword.equals(confirmPassword)) {
                currentUser.setPassword(newPassword);
                userBus.updateModel(currentUser);
                JOptionPane.showMessageDialog(null, "Password updated successfully");
                resetButton.setEnabled(false);
                updateButton.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "New password and confirm password do not match");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Current password does not match");
        }
    }

}
