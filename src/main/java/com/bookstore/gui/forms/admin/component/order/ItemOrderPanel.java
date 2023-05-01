/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bookstore.gui.forms.admin.component.order;

import com.bookstore.bus.UserBUS;
import com.bookstore.enums.OrderStatus;
import com.bookstore.models.UserModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.time.LocalDateTime;

/**
 *
 * @author yanti
 */
public class ItemOrderPanel extends javax.swing.JPanel {

  /**
   * Creates new form OrderForm
   */
  public ItemOrderPanel(
    int serial,
    int idOrder,
    int idCart,
    int idCustomer,
    int idEmployee,
    int total,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    OrderStatus status
  ) {
    initComponents(
      serial,
      idOrder,
      idCart,
      idCustomer,
      idEmployee,
      total,
      dateCreated,
      dateUpdated,
      status
    );
    addMouseListener(
      (MouseListener) new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          PopupOrderFrame popupOrderFrame = new PopupOrderFrame(
            serial,
            idOrder,
            idCart,
            idCustomer,
            idEmployee,
            total,
            dateCreated,
            dateUpdated,
            status
          );
          popupOrderFrame.setVisible(true);
        }
      }
    );
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents(
    int serial,
    int idOrder,
    int idCart,
    int idCustomer,
    int idEmployee,
    int total,
    LocalDateTime dateCreated,
    LocalDateTime dateUpdated,
    OrderStatus status
  ) {
    setSerial = new javax.swing.JLabel();
    setIdOrder = new javax.swing.JLabel();
    setNameCustomer = new javax.swing.JLabel();
    setIdEmployee = new javax.swing.JLabel();
    setStatus = new javax.swing.JTextField();
    jCheckBox1 = new javax.swing.JCheckBox();
    setTotal = new javax.swing.JLabel();

    setPreferredSize(new java.awt.Dimension(611, 53));

    setSerial.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    setSerial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    setSerial.setText("" + serial);
    setSerial.setMaximumSize(new java.awt.Dimension(12, 18));
    setSerial.setMinimumSize(new java.awt.Dimension(12, 18));
    setSerial.setName(""); // NOI18N
    setSerial.setPreferredSize(new java.awt.Dimension(12, 19));

    setIdOrder.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    setIdOrder.setText("" + idOrder);

    setNameCustomer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    UserModel customer = UserBUS.getInstance().getModelById(idCustomer);
    setNameCustomer.setText(customer.getName());

    setIdEmployee.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    setIdEmployee.setText("" + idEmployee);

    setStatus.setEditable(false);
    setStatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    setStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    setStatus.setText("" + status);
    setStatus.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          setStatusActionPerformed(evt);
        }
      }
    );

    jCheckBox1.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          jCheckBox1ActionPerformed(evt);
        }
      }
    );

    setTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    setTotal.setText("" + total);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addComponent(jCheckBox1)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              setSerial,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              30,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              29,
              Short.MAX_VALUE
            )
            .addComponent(
              setIdOrder,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              57,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              27,
              Short.MAX_VALUE
            )
            .addComponent(
              setNameCustomer,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              131,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              32,
              Short.MAX_VALUE
            )
            .addComponent(
              setIdEmployee,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              75,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              18,
              Short.MAX_VALUE
            )
            .addComponent(
              setTotal,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              80,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              setStatus,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              89,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(
          setSerial,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          Short.MAX_VALUE
        )
        .addComponent(
          setIdOrder,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          Short.MAX_VALUE
        )
        .addComponent(
          setNameCustomer,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          Short.MAX_VALUE
        )
        .addComponent(
          setIdEmployee,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          Short.MAX_VALUE
        )
        .addComponent(
          setTotal,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          javax.swing.GroupLayout.DEFAULT_SIZE,
          Short.MAX_VALUE
        )
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap(
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(
                  setStatus,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  36,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  jCheckBox1,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  39,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addContainerGap(8, Short.MAX_VALUE)
        )
    );
  } // </editor-fold>//GEN-END:initComponents

  private void setStatusActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_setStatusActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_setStatusActionPerformed

  private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_jCheckBox1ActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_jCheckBox1ActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JCheckBox jCheckBox1;
  private javax.swing.JLabel setIdEmployee;
  private javax.swing.JLabel setIdOrder;
  private javax.swing.JLabel setNameCustomer;
  private javax.swing.JLabel setSerial;
  private javax.swing.JTextField setStatus;
  private javax.swing.JLabel setTotal;
  // End of variables declaration//GEN-END:variables
}
