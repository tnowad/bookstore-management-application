package com.bookstore.gui.components.orders;

import com.bookstore.gui.forms.orders.OrderDetail;
import com.bookstore.models.OrderModel;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.WindowConstants;

/**
 *
 * @author yanti
 */
public class ItemOrderPanel extends javax.swing.JPanel {

  /**
   * Creates new form OrderForm
   */
  public ItemOrderPanel(int Serial, OrderModel orderModel) {
    initComponents();
    addMouseListener(
      (MouseListener) new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          // OrderDetail orderDetail = new OrderDetail(orderModel.getCustomerId());
          // orderDetail.setLocationRelativeTo(null);
          // orderDetail.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
          // orderDetail.setVisible(true);
          new Dialog(new OrderDetail(orderModel.getCustomerId()));

        }
      }
    );
  }

  private void initComponents() {
    java.awt.GridBagConstraints gridBagConstraints;

    setSerial = new javax.swing.JLabel();
    setIdOrder = new javax.swing.JLabel();
    setNameCustomer = new javax.swing.JLabel();
    setIdEmployee = new javax.swing.JLabel();
    setStatus = new javax.swing.JTextField();
    checkBox = new javax.swing.JCheckBox();
    setTotal = new javax.swing.JLabel();

    setPreferredSize(new java.awt.Dimension(611, 53));
    java.awt.GridBagLayout layout = new java.awt.GridBagLayout();
    layout.columnWeights = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
    setLayout(layout);

    setSerial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    setSerial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    setSerial.setText("01");
    setSerial.setMaximumSize(new java.awt.Dimension(12, 18));
    setSerial.setMinimumSize(new java.awt.Dimension(12, 18));
    setSerial.setName(""); // NOI18N
    setSerial.setPreferredSize(new java.awt.Dimension(25, 19));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 18;
    gridBagConstraints.ipady = 35;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 6, 0, 0);
    add(setSerial, gridBagConstraints);

    setIdOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    setIdOrder.setText("id");
    setIdOrder.setMinimumSize(new java.awt.Dimension(15, 20));
    setIdOrder.setPreferredSize(new java.awt.Dimension(30, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 41;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 29, 0, 0);
    add(setIdOrder, gridBagConstraints);

    setNameCustomer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    setNameCustomer.setText("ok");
    setNameCustomer.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 105;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 27, 0, 0);
    add(setNameCustomer, gridBagConstraints);

    setIdEmployee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    setIdEmployee.setText("ok");
    setIdEmployee.setPreferredSize(new java.awt.Dimension(50, 20));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 90;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 32, 0, 0);
    add(setIdEmployee, gridBagConstraints);

    setStatus.setEditable(false);
    setStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    setStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    setStatus.setText("BANNED");

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 6;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.ipadx = 25;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(6, 12, 0, 6);
    add(setStatus, gridBagConstraints);

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipady = 20;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(6, 0, 0, 0);
    add(checkBox, gridBagConstraints);

    setTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    setTotal.setText("ok");
    setTotal.setPreferredSize(new java.awt.Dimension(20, 16));
    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 5;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 67;
    gridBagConstraints.ipady = 37;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.NORTHWEST;
    gridBagConstraints.insets = new java.awt.Insets(0, 18, 0, 0);
    add(setTotal, gridBagConstraints);
  } // </editor-fold>//GEN-END:initComponents

  private javax.swing.JCheckBox checkBox;
  private javax.swing.JLabel setIdEmployee;
  private javax.swing.JLabel setIdOrder;
  private javax.swing.JLabel setNameCustomer;
  private javax.swing.JLabel setSerial;
  private javax.swing.JTextField setStatus;
  private javax.swing.JLabel setTotal;
}
