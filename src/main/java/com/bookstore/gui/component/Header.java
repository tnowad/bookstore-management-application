package com.bookstore.gui.component;

import java.awt.EventQueue;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

public class Header extends javax.swing.JPanel {

  public Header() {
    initComponents();
  }

  public void addMenuEvent(ActionListener actionListener) {
    cmdMenu.addActionListener(actionListener);
  }

  private void initComponents() {

    cmdMenu = new com.bookstore.gui.swing.Button();
    pic = new com.bookstore.gui.swing.ImageAvatar();
    lbUserName = new javax.swing.JLabel();
    lbRole = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();
    buttonBadges1 = new com.bookstore.gui.swing.ButtonBadges();
    buttonBadges2 = new com.bookstore.gui.swing.ButtonBadges();

    setBackground(new java.awt.Color(255, 255, 255));

    cmdMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/gui/icon/menu.png"))); // NOI18N

    pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/gui/icon/profile.jpg"))); // NOI18N

    lbUserName.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
    lbUserName.setForeground(new java.awt.Color(127, 127, 127));
    lbUserName.setText("User Name");

    lbRole.setForeground(new java.awt.Color(127, 127, 127));
    lbRole.setText("Admin");

    jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

    buttonBadges1.setForeground(new java.awt.Color(250, 49, 49));
    buttonBadges1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/gui/icon/notification.png"))); // NOI18N
    buttonBadges1.setBadges(12);

    buttonBadges2.setForeground(new java.awt.Color(63, 178, 232));
    buttonBadges2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/gui/icon/message.png"))); // NOI18N
    buttonBadges2.setBadges(5);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cmdMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 362, Short.MAX_VALUE)
                .addComponent(buttonBadges2, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(buttonBadges1, javax.swing.GroupLayout.PREFERRED_SIZE, 38,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 8,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbUserName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbRole, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbUserName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbRole))
                    .addComponent(cmdMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(buttonBadges1, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonBadges2, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));
  }

  private com.bookstore.gui.swing.ButtonBadges buttonBadges1;
  private com.bookstore.gui.swing.ButtonBadges buttonBadges2;
  private com.bookstore.gui.swing.Button cmdMenu;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JLabel lbRole;
  private javax.swing.JLabel lbUserName;
  private com.bookstore.gui.swing.ImageAvatar pic;

  public static void main(String[] args) {
    EventQueue.invokeLater(() -> {
      new JFrame() {
        {
          add(new Header());
          pack();
          setLocationRelativeTo(null);
          setVisible(true);
        }
      };
    });
  }

}
