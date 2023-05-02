package com.bookstore.gui.components.users;

import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import java.awt.event.*;
import java.time.LocalDateTime;
import javax.swing.JTextField;

public class UserPanel extends javax.swing.JPanel implements MouseListener {

  private JTextField IdUser;

  /**
   * Creates new form UserForm
   */
  public UserPanel(
    int serial,
    int idUser,
    String userName,
    String password,
    UserStatus status,
    String name,
    String email,
    String phone,
    UserRole role,
    LocalDateTime dateCreate,
    LocalDateTime dateUpdate
  ) {
    initComponents(
      serial,
      idUser,
      userName,
      password,
      status,
      name,
      email,
      phone,
      role,
      dateCreate,
      dateUpdate
    );
    addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          PopupUserFrame userFullForm = new PopupUserFrame(
            idUser,
            userName,
            password,
            status,
            name,
            email,
            phone,
            role,
            dateCreate,
            dateUpdate
          );
          userFullForm.setVisible(true);
        }
      }
    );
  }

  private void initComponents(
    int serial,
    int idUser,
    String userName,
    String password,
    UserStatus status,
    String name,
    String email,
    String phone,
    UserRole role,
    LocalDateTime dateCreate,
    LocalDateTime dateUpdate
  ) {
    java.awt.GridBagConstraints gridBagConstraints;

    checkBox = new javax.swing.JCheckBox();
    setName = new javax.swing.JLabel();
    setPhone = new javax.swing.JLabel();
    setEmail = new javax.swing.JLabel();
    setSerial = new javax.swing.JLabel();
    setRole = new javax.swing.JTextField();
    setStatus = new javax.swing.JTextField();

    java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
    layout.columnWeights = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
    setLayout(layout);

    checkBox.setMaximumSize(new java.awt.Dimension(21, 19));
    checkBox.setMinimumSize(new java.awt.Dimension(21, 19));
    checkBox.setPreferredSize(new java.awt.Dimension(20, 19));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipady = 34;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    add(checkBox, gridBagConstraints);

    setName.setFont(new java.awt.Font("Segoe UI", 0, 14));
    setName.setText(name);
    setName.setMaximumSize(new java.awt.Dimension(50, 20));
    setName.setMinimumSize(new java.awt.Dimension(50, 20));
    setName.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipadx = 99;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
    add(setName, gridBagConstraints);

    setPhone.setFont(new java.awt.Font("Segoe UI", 0, 14));
    setPhone.setText(phone);
    setPhone.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipadx = 85;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 12, 0, 0);
    add(setPhone, gridBagConstraints);

    setEmail.setFont(new java.awt.Font("Segoe UI", 0, 14));
    setEmail.setText(email);
    setEmail.setPreferredSize(new java.awt.Dimension(45, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipadx = 125;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
    add(setEmail, gridBagConstraints);

    setSerial.setFont(new java.awt.Font("Segoe UI", 0, 14));
    setSerial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    setSerial.setText("" + serial);
    setSerial.setMaximumSize(new java.awt.Dimension(12, 18));
    setSerial.setMinimumSize(new java.awt.Dimension(12, 18));
    setSerial.setPreferredSize(new java.awt.Dimension(12, 19));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipadx = 18;
    gridBagConstraints.ipady = 35;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
    add(setSerial, gridBagConstraints);

    setRole.setEditable(false);
    setRole.setFont(new java.awt.Font("Segoe UI", 0, 14));
    setRole.setHorizontalAlignment(javax.swing.JTextField.LEFT);
    setRole.setText("" + role);
    setRole.setMinimumSize(new java.awt.Dimension(90, 26));
    setRole.setPreferredSize(new java.awt.Dimension(90, 26));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 5;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(6, 6, 0, 0);
    add(setRole, gridBagConstraints);

    setStatus.setEditable(false);
    setStatus.setFont(new java.awt.Font("Segoe UI", 0, 14));
    setStatus.setHorizontalAlignment(javax.swing.JTextField.LEFT);
    setStatus.setText("" + status);
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 6;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.ipadx = 7;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(6, 18, 0, 6);
    add(setStatus, gridBagConstraints);
  } // </editor-fold>//GEN-END:initComponents

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox checkBox;
  private javax.swing.JLabel setEmail;
  private javax.swing.JLabel setName;
  private javax.swing.JLabel setPhone;
  private javax.swing.JTextField setRole;
  private javax.swing.JLabel setSerial;
  private javax.swing.JTextField setStatus;

  // End of variables declaration//GEN-END:variables
  @Override
  public void mouseClicked(MouseEvent e) {}

  @Override
  public void mousePressed(MouseEvent e) {}

  @Override
  public void mouseReleased(MouseEvent e) {}

  @Override
  public void mouseEntered(MouseEvent e) {}

  @Override
  public void mouseExited(MouseEvent e) {}
}
