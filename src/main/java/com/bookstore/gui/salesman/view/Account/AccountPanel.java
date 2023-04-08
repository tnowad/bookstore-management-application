package com.bookstore.gui.salesman.view.Account;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccountPanel extends JPanel {
  public AccountPanel() {
    JButton accountSettingButton = new JButton("Account settings");
    JButton profileSettingButton = new JButton("Profile settings");
    setLayout(new FlowLayout());
    add(profileSettingButton);
    add(accountSettingButton);
  }
}
