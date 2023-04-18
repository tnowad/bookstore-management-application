package com.bookstore.gui.form.salesman.view.Account;

import javax.swing.*;

import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;

public class AccountSettings extends JPanel {

  public AccountSettings() {
    initComponents();
  }

  private void initComponents() {

    confirmNewPasswordTxtFld = new javax.swing.JPasswordField();
    jLabel3 = new javax.swing.JLabel();
    newPasswordTxtFld = new javax.swing.JPasswordField();
    jLabel2 = new javax.swing.JLabel();
    currentPasswordTxtFld = new javax.swing.JPasswordField();
    jLabel1 = new javax.swing.JLabel();
    Button1 = new Button("Update");
    Button2 = new Button("Reset");

    jLabel3.setFont(new ThemeFont().getSmallFont());
    jLabel3.setText("Confirm new password");

    jLabel2.setFont(new ThemeFont().getSmallFont());
    jLabel2.setText("New password");

    jLabel1.setFont(new ThemeFont().getSmallFont());
    jLabel1.setText("Your current password");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(confirmNewPasswordTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 240,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(newPasswordTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 240,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(currentPasswordTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 240,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Button1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Button2)
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(currentPasswordTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newPasswordTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(confirmNewPasswordTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Button1)
                    .addComponent(Button2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
  }

  private JPasswordField confirmNewPasswordTxtFld;
  private JPasswordField currentPasswordTxtFld;
  private Button Button1;
  private Button Button2;
  private JLabel jLabel1;
  private JLabel jLabel2;
  private JLabel jLabel3;
  private JPasswordField newPasswordTxtFld;
}
