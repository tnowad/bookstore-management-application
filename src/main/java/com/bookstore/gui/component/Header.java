package com.bookstore.gui.component;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

import com.bookstore.gui.swing.Button;
import com.bookstore.gui.swing.ButtonBadges;
import com.bookstore.gui.swing.ImageAvatar;
import com.bookstore.model.ProfileModel;

public class Header extends JPanel {

  private static Header instance;

  private Button menuButton;

  private Header() {
    initComponents();
  }

  public static Header getInstance() {
    if (ProfileModel.getInstance().getUser() == null) {
      instance = null;
      // !TODO: remove this line to show header when user is not logged in
      // return null;
    }
    if (instance == null) {
      instance = new Header();
    }
    return instance;
  }

  public void addMenuEvent(ActionListener actionListener) {
    menuButton.addActionListener(actionListener);
  }

  private void initComponents() {
    ImageAvatar picture;
    JLabel userNameLabel;
    JLabel roleLabel;
    JSeparator jSeparator1;
    ButtonBadges messageButton;
    ButtonBadges notificationButton;

    menuButton = new Button();
    picture = new ImageAvatar();
    userNameLabel = new JLabel();
    roleLabel = new JLabel();
    jSeparator1 = new JSeparator();
    notificationButton = new ButtonBadges();
    messageButton = new ButtonBadges();

    setBackground(new java.awt.Color(255, 255, 255));

    menuButton.setIcon(new ImageIcon(getClass().getResource("/com/bookstore/gui/icon/menu.png"))); // NOI18N

    picture.setIcon(new ImageIcon(getClass().getResource("/com/bookstore/gui/icon/profile.jpg"))); // NOI18N

    userNameLabel.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
    userNameLabel.setForeground(new java.awt.Color(127, 127, 127));
    userNameLabel.setText("User Name");

    roleLabel.setForeground(new java.awt.Color(127, 127, 127));
    roleLabel.setText("Admin");

    jSeparator1.setOrientation(SwingConstants.VERTICAL);

    notificationButton.setForeground(new java.awt.Color(250, 49, 49));
    notificationButton
        .setIcon(new ImageIcon(getClass().getResource("/com/bookstore/gui/icon/notification.png"))); // NOI18N
    notificationButton.setBadges(12);

    messageButton.setForeground(new java.awt.Color(63, 178, 232));
    messageButton.setIcon(new ImageIcon(getClass().getResource("/com/bookstore/gui/icon/message.png"))); // NOI18N
    messageButton.setBadges(5);

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuButton, GroupLayout.PREFERRED_SIZE, 38,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                .addComponent(messageButton, GroupLayout.PREFERRED_SIZE, 38,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(notificationButton, GroupLayout.PREFERRED_SIZE, 38,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 8,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(userNameLabel, GroupLayout.Alignment.TRAILING)
                    .addComponent(roleLabel, GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(picture, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(userNameLabel)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                            GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(roleLabel))
                    .addComponent(menuButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(picture, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(notificationButton, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(messageButton, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));
  }

}
