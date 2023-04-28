package com.bookstore.gui.form.account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

public class AccountSettings extends JPanel {

    private JButton cancelButton;
    private JPasswordField confirmNewPasswordField;
    private JLabel confirmNewPasswordLabel;
    private JPasswordField currentPasswordField;
    private JLabel currentPasswordLabel;
    private JPanel groupButton;
    private JLabel newPassordLabel;
    private JPasswordField newPasswordField;
    private JButton updateButton;

    public AccountSettings() {
        initComponents();
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
        cancelButton = new JButton();

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
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        groupButton.add(updateButton);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        groupButton.add(cancelButton);

        add(groupButton);
    }

    private void cancelButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void updateButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

}
