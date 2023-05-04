package com.bookstore.gui.components.users;

import com.bookstore.enums.UserRole;
import com.bookstore.enums.UserStatus;
import com.bookstore.models.UserModel;

import java.awt.Container;
import java.awt.event.*;
import java.time.LocalDateTime;

public class UserPanel extends javax.swing.JPanel implements MouseListener {


  /**
   * Creates new form UserForm
   */
  public UserPanel(int serial,UserModel user) {
    initComponents(serial,user);
    addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          PopupUserFrame userFullForm = new PopupUserFrame(user);
          userFullForm.setVisible(true);
        }
      }
    );
  }

  private void initComponents(int serial,UserModel user) {

    checkBox = new javax.swing.JCheckBox();
    setName = new javax.swing.JLabel();
    setPhone = new javax.swing.JLabel();
    setEmail = new javax.swing.JLabel();
    setSerial = new javax.swing.JLabel();
    setRole = new javax.swing.JLabel();
    setStatus = new javax.swing.JLabel();
    panelItemHeader_1 = new javax.swing.JPanel();
    panelItemHeader_2 = new javax.swing.JPanel();

    setLayout(new java.awt.GridLayout());

    panelItemHeader_1.setLayout(new java.awt.GridLayout(1,2));

    panelItemHeader_1.add(checkBox);

    setSerial.setText(""+serial);
    panelItemHeader_1.add(setSerial);

    add(panelItemHeader_1);

    setName.setText(user.getName());
    add(setName);

    setPhone.setText(user.getPhone());
    add(setPhone);

    setEmail.setText(user.getEmail());
    add(setEmail);

    panelItemHeader_2.setLayout(new java.awt.GridLayout(1,2));

    
    setRole.setText(""+user.getRole());
    panelItemHeader_2.add(setRole);

    setStatus.setText(""+user.getStatus());
    panelItemHeader_2.add(setStatus);

    add(panelItemHeader_2);


  } 

  private javax.swing.JCheckBox checkBox;
  private javax.swing.JLabel setEmail;
  private javax.swing.JLabel setName;
  private javax.swing.JLabel setPhone;
  private javax.swing.JLabel setRole;
  private javax.swing.JLabel setSerial;
  private javax.swing.JLabel setStatus;
  private javax.swing.JPanel panelItemHeader_1;
  private javax.swing.JPanel panelItemHeader_2;

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
