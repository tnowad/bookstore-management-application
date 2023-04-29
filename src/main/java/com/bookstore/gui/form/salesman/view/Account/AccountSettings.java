package com.bookstore.gui.form.salesman.view.Account;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import com.bookstore.bus.CurrentUserBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.label.Label;
import com.bookstore.models.CurrentUserModel;
import com.bookstore.models.UserModel;

public class AccountSettings extends JPanel {

        private JPasswordField currentPasswordField;
        private JPasswordField confirmNewPasswordField;
        private JPasswordField newPasswordTxtField;
        private Button updateButton;
        private Button resetButton;
        private Label yourCurrentPasswordLabel;
        private Label newPasswordLabel;
        private Label confirmPasswordLabel;

        UserBUS userBus = UserBUS.getInstance();
        CurrentUserBUS currentUserBus = CurrentUserBUS.getInstance();
        List<CurrentUserModel> currentUser = currentUserBus.getAllModels();
        int idCurrent = currentUser.get(0).getCurrentUserId();
        UserModel userModel = userBus.getModelById(idCurrent);

        public AccountSettings() {
                initComponents();
                handleEvent();
        }

        private void handleEvent() {
                updateButton.addActionListener((ActionListener) new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                updatePasswordBtnActionPerformed(evt);
                        }
                });
                resetButton.addActionListener((ActionListener) new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                resetPasswordBtnActionPerformed(evt);
                        }
                });
        }

        private void resetPasswordBtnActionPerformed(ActionEvent evt) {
               currentPasswordField.setText("");
               confirmNewPasswordField.setText("");
               newPasswordTxtField.setText("");

               resetButton.setEnabled(false);
               updateButton.setEnabled(false);
        }

        private void updatePasswordBtnActionPerformed(ActionEvent evt) {
                String currentPassword = new String(currentPasswordField.getPassword());
                if (currentPassword.equals(userModel.getPassword())) {
                        String newPassword = new String(newPasswordTxtField.getPassword());
                        String confirmPassword = new String(confirmNewPasswordField.getPassword());
                        if (newPassword.equals(confirmPassword)) {
                                userModel.setPassword(newPassword);
                                userBus.updateModel(userModel);
                                JOptionPane.showMessageDialog(null, "Password updated successfully");
                                resetButton.setEnabled(false);
                                updateButton.setEnabled(false);
                        }
                        else {
                                JOptionPane.showMessageDialog(null, "New password and confirm password do not match");
                        }
                }
                else {
                        JOptionPane.showMessageDialog(null, "Current password does not match");
                }
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
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(confirmPasswordLabel)
                                                                                .addComponent(confirmNewPasswordField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                240,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(newPasswordLabel)
                                                                                .addComponent(newPasswordTxtField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                240,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(yourCurrentPasswordLabel)
                                                                                .addComponent(currentPasswordField,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                240,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE))
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)
                                                                .addComponent(updateButton)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(resetButton)
                                                                .addContainerGap()));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addComponent(yourCurrentPasswordLabel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(currentPasswordField,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addComponent(newPasswordLabel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(newPasswordTxtField,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(confirmPasswordLabel)
                                                                .addPreferredGap(
                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(confirmNewPasswordField,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(updateButton)
                                                                                .addComponent(resetButton))
                                                                .addContainerGap(GroupLayout.DEFAULT_SIZE,
                                                                                Short.MAX_VALUE)));
        }

}
