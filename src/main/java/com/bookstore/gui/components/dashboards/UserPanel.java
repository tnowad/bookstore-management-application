package com.bookstore.gui.components.dashboards;

import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.SwingConstants;

public class UserPanel extends JPanel {

  private JLabel getAgeUser;
  private JLabel getNameUser;
  private JLabel getPhone;
  private JTextField getRole;
  private JLabel getSerial;
  private JLabel getStatus;

  public UserPanel(
    int serial,
    String name,
    String phone,
    UserStatus status,
    UserRole role,
    long createdTime
  ) {
    initComponents();
  }

  private void initComponents() {
    getSerial = new JLabel();
    getNameUser = new JLabel();
    getPhone = new JLabel();
    getAgeUser = new JLabel();
    getStatus = new JLabel();
    getRole = new JTextField();

    setBackground(new java.awt.Color(255, 255, 255));
    setPreferredSize(new java.awt.Dimension(445, 42));

    getSerial.setText("01");

    getNameUser.setFont(new java.awt.Font("Segoe UI", 3, 12));
    getNameUser.setText("huynh trieu den");

    getPhone.setText("0941045139");

    getAgeUser.setHorizontalAlignment(SwingConstants.CENTER);
    getAgeUser.setText("60");

    getStatus.setHorizontalAlignment(SwingConstants.CENTER);
    getStatus.setText("BANNED");

    getRole.setEditable(false);
    getRole.setBackground(new java.awt.Color(204, 204, 255));
    getRole.setHorizontalAlignment(JTextField.CENTER);
    getRole.setText("ADMIN");
    getRole.setBorder(
      BorderFactory.createLineBorder(new java.awt.Color(204, 204, 0))
    );

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addGap(7, 7, 7)
            .addComponent(getSerial)
            .addPreferredGap(
              LayoutStyle.ComponentPlacement.RELATED,
              GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getNameUser,
              GroupLayout.PREFERRED_SIZE,
              104,
              GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              LayoutStyle.ComponentPlacement.RELATED,
              GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getPhone,
              GroupLayout.PREFERRED_SIZE,
              75,
              GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              LayoutStyle.ComponentPlacement.RELATED,
              GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getAgeUser,
              GroupLayout.PREFERRED_SIZE,
              39,
              GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              LayoutStyle.ComponentPlacement.RELATED,
              12,
              Short.MAX_VALUE
            )
            .addComponent(
              getStatus,
              GroupLayout.PREFERRED_SIZE,
              81,
              GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              LayoutStyle.ComponentPlacement.RELATED,
              GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getRole,
              GroupLayout.PREFERRED_SIZE,
              78,
              GroupLayout.PREFERRED_SIZE
            )
            .addContainerGap()
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addGroup(
              layout
                .createParallelGroup(GroupLayout.Alignment.BASELINE)
                .addComponent(
                  getSerial,
                  GroupLayout.PREFERRED_SIZE,
                  24,
                  GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getNameUser,
                  GroupLayout.PREFERRED_SIZE,
                  24,
                  GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getPhone,
                  GroupLayout.PREFERRED_SIZE,
                  24,
                  GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getAgeUser,
                  GroupLayout.PREFERRED_SIZE,
                  24,
                  GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getStatus,
                  GroupLayout.PREFERRED_SIZE,
                  24,
                  GroupLayout.PREFERRED_SIZE
                )
                .addComponent(getRole)
            )
            .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        )
    );
  }
}
