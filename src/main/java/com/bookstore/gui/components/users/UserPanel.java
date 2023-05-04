package com.bookstore.gui.components.users;

import com.bookstore.models.UserModel;
import java.awt.GridLayout;
import java.awt.event.*;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserPanel extends javax.swing.JPanel implements MouseListener {

  public UserPanel(int serial, UserModel user) {
    initComponents(serial, user);
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

  private void initComponents(int serial, UserModel user) {
    checkBox = new JCheckBox();
    setName = new JLabel();
    setPhone = new JLabel();
    setEmail = new JLabel();
    setSerial = new JLabel();
    setRole = new JLabel();
    setStatus = new JLabel();
    panelItemHeader_1 = new JPanel();
    panelItemHeader_2 = new JPanel();

    setLayout(new GridLayout());

    panelItemHeader_1.setLayout(new GridLayout(1, 2));

    panelItemHeader_1.add(checkBox);

    setSerial.setText("" + serial);
    panelItemHeader_1.add(setSerial);

    add(panelItemHeader_1);

    setName.setText(user.getName());
    add(setName);

    setPhone.setText(user.getPhone());
    add(setPhone);

    setEmail.setText(user.getEmail());
    add(setEmail);

    panelItemHeader_2.setLayout(new GridLayout(1, 2));

    setRole.setText("" + user.getRole());
    panelItemHeader_2.add(setRole);

    setStatus.setText("" + user.getStatus());
    panelItemHeader_2.add(setStatus);

    add(panelItemHeader_2);
  }

  private JCheckBox checkBox;
  private JLabel setEmail;
  private JLabel setName;
  private JLabel setPhone;
  private JLabel setRole;
  private JLabel setSerial;
  private JLabel setStatus;
  private JPanel panelItemHeader_1;
  private JPanel panelItemHeader_2;

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
