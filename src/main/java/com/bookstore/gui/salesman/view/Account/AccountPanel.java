package com.bookstore.gui.salesman.view.Account;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AccountPanel extends JPanel {
  private JPanel titleButton;
  private JPanel contentSetting;
  private JButton accountSettingButton;
  private JButton profileSettingButton;

  public AccountPanel() {
    initComponents();
    handleEvents();
  }

  private void handleEvents() {
    profileSettingButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentSetting.removeAll();
        contentSetting.add(new ProfileSettings());
        contentSetting.revalidate();
        contentSetting.repaint();
      }
    });
    accountSettingButton.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        contentSetting.removeAll();
        contentSetting.add(new AccountSettings());
        contentSetting.revalidate();
        contentSetting.repaint();
      }
    });
  }

  private void initComponents() {
    titleButton = new JPanel();
    contentSetting = new JPanel();
    accountSettingButton = new JButton("Account settings");
    profileSettingButton = new JButton("Profile settings");
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    titleButton.setLayout(new FlowLayout());
    add(titleButton);
    add(contentSetting);
    titleButton.add(profileSettingButton);
    titleButton.add(accountSettingButton);
    contentSetting.add(new ProfileSettings());
  }
}
