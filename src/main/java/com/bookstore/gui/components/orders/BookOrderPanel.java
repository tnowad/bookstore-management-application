/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bookstore.gui.components.orders;

/**
 *
 * @author yanti
 */
public class BookOrderPanel extends javax.swing.JPanel {

  /**
   * Creates new form BookOrderPanel
   */
  public BookOrderPanel(
    int Serial,
    String title,
    String isbn,
    int price,
    int quantity,
    int total,
    String status
  ) {
    initComponents();
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {
    getSerial1 = new javax.swing.JLabel();
    getIsbn = new javax.swing.JLabel();
    getTitle = new javax.swing.JLabel();
    getQuantity = new javax.swing.JLabel();
    getPrice = new javax.swing.JLabel();
    getTotal = new javax.swing.JLabel();
    getStatus = new javax.swing.JTextField();

    getSerial1.setText("01");

    getIsbn.setFont(new java.awt.Font("Segoe UI", 3, 12));
    getIsbn.setText("huynh trieu den");

    getTitle.setText("0941045139");

    getQuantity.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    getQuantity.setText("60");

    getPrice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    getPrice.setText("60");

    getTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    getTotal.setText("1000");

    getStatus.setEditable(false);
    getStatus.setForeground(new java.awt.Color(255, 0, 51));
    getStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    getStatus.setText("ok");
    getStatus.setBorder(
      new javax.swing.border.SoftBevelBorder(
        javax.swing.border.BevelBorder.RAISED,
        new java.awt.Color(0, 255, 0),
        new java.awt.Color(255, 51, 153),
        new java.awt.Color(51, 51, 255),
        new java.awt.Color(255, 102, 102)
      )
    );
    getStatus.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          getStatusActionPerformed(evt);
        }
      }
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addComponent(getSerial1)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getIsbn,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              136,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getTitle,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              132,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getPrice,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              83,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getQuantity,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              83,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getTotal,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              113,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              Short.MAX_VALUE
            )
            .addComponent(
              getStatus,
              javax.swing.GroupLayout.DEFAULT_SIZE,
              73,
              Short.MAX_VALUE
            )
            .addContainerGap()
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          javax.swing.GroupLayout.Alignment.TRAILING,
          layout
            .createSequentialGroup()
            .addGap(16, 16, 16)
            .addGroup(
              layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(
                  getSerial1,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getIsbn,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getTitle,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getQuantity,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getPrice,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getTotal,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  24,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
                .addComponent(
                  getStatus,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
            .addGap(14, 14, 14)
        )
    );
  } // </editor-fold>//GEN-END:initComponents

  private void getStatusActionPerformed(java.awt.event.ActionEvent evt) { //GEN-FIRST:event_getStatusActionPerformed
    // TODO add your handling code here:
  } //GEN-LAST:event_getStatusActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel getIsbn;
  private javax.swing.JLabel getPrice;
  private javax.swing.JLabel getQuantity;
  private javax.swing.JLabel getSerial1;
  private javax.swing.JTextField getStatus;
  private javax.swing.JLabel getTitle;
  private javax.swing.JLabel getTotal;
  // End of variables declaration//GEN-END:variables
}
