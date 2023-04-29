package com.bookstore.gui.form.account.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.component.passwordfield.PasswordField;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.label.Label;

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

    UserBUS userBus = UserBUS.getInstance();
    UserModel currentUser = Authentication.getCurrentUser();

    public AccountSettings() {
        initComponents();
        handleEvent();
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

        setMaximumSize(new Dimension(300, 200));
        setMinimumSize(new Dimension(300, 200));
        setPreferredSize(new Dimension(300, 250));
        setLayout(new GridLayout(7, 1));

        currentPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        currentPasswordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        add(currentPasswordLabel);
        add(currentPasswordField);

        newPassordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        newPassordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        add(newPassordLabel);
        add(newPasswordField);

        confirmNewPasswordLabel.setHorizontalAlignment(SwingConstants.LEFT);
        confirmNewPasswordLabel.setVerticalAlignment(SwingConstants.BOTTOM);
        add(confirmNewPasswordLabel);
        add(confirmNewPasswordField);

        groupButton.add(updateButton);

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
