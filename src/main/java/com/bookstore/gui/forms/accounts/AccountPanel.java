package com.bookstore.gui.forms.accounts;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.bookstore.gui.components.buttons.Button;

public class AccountPanel extends JPanel {

  private JPanel titleButton;
  private JPanel contentSetting;
  private Button accountSettingButton;
  private Button profileSettingButton;

  public AccountPanel() {
    initComponents();
    handleEvents();
  }

  private void handleEvents() {
    profileSettingButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            contentSetting.removeAll();
            contentSetting.add(new ProfileSettings());
            contentSetting.revalidate();
            contentSetting.repaint();
          }
        });
    accountSettingButton.addActionListener(
        new ActionListener() {
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
    accountSettingButton = new Button("Account settings");
    profileSettingButton = new Button("Profile settings");
    accountSettingButton.setPreferredSize(new Dimension(200, 30));
    profileSettingButton.setPreferredSize(new Dimension(200, 30));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    titleButton.setLayout(new FlowLayout());
    add(titleButton);
    add(contentSetting);
    titleButton.add(profileSettingButton);
    titleButton.add(accountSettingButton);
    contentSetting.add(new ProfileSettings());
  }
}
