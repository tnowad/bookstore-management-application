package com.bookstore.gui.components.dashboards;

import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;

public class UserPanel extends javax.swing.JPanel {

  /**
   * Creates new form userPanel
   */
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
    getSerial = new javax.swing.JLabel();
    getNameUser = new javax.swing.JLabel();
    getPhone = new javax.swing.JLabel();
    getAgeUser = new javax.swing.JLabel();
    getStatus = new javax.swing.JLabel();
    getRole = new javax.swing.JTextField();

    setBackground(new java.awt.Color(255, 255, 255));
    setBorder(
      new javax.swing.border.SoftBevelBorder(
        javax.swing.border.BevelBorder.RAISED,
        new java.awt.Color(102, 102, 0),
        new java.awt.Color(153, 255, 153),
        new java.awt.Color(102, 0, 204),
        new java.awt.Color(51, 255, 153)
      )
    );
    setPreferredSize(new java.awt.Dimension(445, 42));

    getSerial.setText("01");

    getNameUser.setFont(new java.awt.Font("Segoe UI", 3, 12));
    getNameUser.setText("huynh trieu den");

    getPhone.setText("0941045139");

    getAgeUser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    getAgeUser.setText("60");

    getStatus.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    getStatus.setText("BANNED");

    getRole.setEditable(false);
    getRole.setBackground(new java.awt.Color(204, 204, 255));
    getRole.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    getRole.setText("ADMIN");
    getRole.setBorder(
      javax.swing.BorderFactory.createLineBorder(
        new java.awt.Color(204, 204, 0)
      )
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addGap(7, 7, 7)
            .addComponent(getSerial)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getNameUser,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              104,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getPhone,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              75,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getAgeUser,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              39,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              12,
              Short.MAX_VALUE
            )
            .addComponent(
              getStatus,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              81,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getRole,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              78,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addContainerGap()
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  getSerial,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getNameUser,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getPhone,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getAgeUser,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getStatus,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(getRole)
            )
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
        )
    );
  } // </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel getAgeUser;
  private javax.swing.JLabel getNameUser;
  private javax.swing.JLabel getPhone;
  private javax.swing.JTextField getRole;
  private javax.swing.JLabel getSerial;
  private javax.swing.JLabel getStatus;
  // End of variables declaration//GEN-END:variables
}
