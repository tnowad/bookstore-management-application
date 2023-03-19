package com.bookstore.gui.swing.table;

import javax.swing.JLabel;
import javax.swing.GroupLayout;

import com.bookstore.gui.swing.ImageAvatar;

public class Profile extends javax.swing.JPanel {

  private JLabel jLabelName;
  private ImageAvatar imageAvatar;

  public Profile(ModelProfile data) {
    initComponents();
    imageAvatar.setIcon(data.getIcon());
    jLabelName.setText(data.getName());
  }

  private void initComponents() {

    imageAvatar = new ImageAvatar();
    jLabelName = new JLabel();

    jLabelName.setForeground(new java.awt.Color(102, 102, 102));
    jLabelName.setText("Name");

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(imageAvatar, GroupLayout.PREFERRED_SIZE, 28,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jLabelName, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(imageAvatar, GroupLayout.DEFAULT_SIZE,
                        GroupLayout.DEFAULT_SIZE,
                        Short.MAX_VALUE)
                    .addComponent(jLabelName, GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE))
                .addContainerGap()));
  }

}
