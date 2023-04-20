package com.bookstore.gui.form.salesman.view.Account;

import javax.swing.*;

import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.button.Label;

public class AccountSettings extends JPanel {

    public AccountSettings() {
        initComponents();
    }

    private void initComponents() {

        newPasswordLabel = new Label("New Password");
        confirmPasswordLabel = new Label("Confirm New Password");
        yourCurrentPasswordLabel = new Label("Your current password");
        confirmNewPasswordField = new JPasswordField();
        currentPasswordField = new JPasswordField();
        newPasswordTxtField = new JPasswordField();
        updateButton = new Button("Update");
        resetButton = new Button("Reset");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(confirmPasswordLabel)
                                        .addComponent(confirmNewPasswordField, GroupLayout.PREFERRED_SIZE,
                                                240,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(newPasswordLabel)
                                        .addComponent(newPasswordTxtField, GroupLayout.PREFERRED_SIZE, 240,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(yourCurrentPasswordLabel)
                                        .addComponent(currentPasswordField, GroupLayout.PREFERRED_SIZE,
                                                240,
                                                GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(updateButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(resetButton)
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(yourCurrentPasswordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(currentPasswordField, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(newPasswordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(newPasswordTxtField, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(confirmPasswordLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confirmNewPasswordField, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(updateButton)
                                        .addComponent(resetButton))
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
    }

    private JPasswordField confirmNewPasswordField;
    private JPasswordField currentPasswordField;
    private Button updateButton;
    private Button resetButton;
    private Label yourCurrentPasswordLabel;
    private Label newPasswordLabel;
    private Label confirmPasswordLabel;
    private JPasswordField newPasswordTxtField;
}
