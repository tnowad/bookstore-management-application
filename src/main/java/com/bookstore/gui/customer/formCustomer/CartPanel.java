/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bookstore.gui.customer.formCustomer;

/**
 *
 * @author Danh
 */
public class CartPanel extends javax.swing.JPanel {

    /**
     * Creates new form CartUI
     */
    public CartPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        summaryLbl = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        totalMoneyTxtFld = new javax.swing.JTextField();
        proceedToCheckoutBtn = new javax.swing.JButton();
        chooseAllTxtBox = new javax.swing.JCheckBox();
        deleteAllProductBtn = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Total cost:");

        totalMoneyTxtFld.setEditable(false);
        totalMoneyTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalMoneyTxtFldActionPerformed(evt);
            }
        });

        proceedToCheckoutBtn.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        proceedToCheckoutBtn.setText("Proceed to checkout");
        proceedToCheckoutBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proceedToCheckoutBtnActionPerformed(evt);
            }
        });

        chooseAllTxtBox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        chooseAllTxtBox.setText("Choose all");

        deleteAllProductBtn.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        // deleteAllProductBtn.setIcon(new
        // javax.swing.ImageIcon(getClass().getResource("/icon/remove-from-cart
        // (Custom)_1.png"))); // NOI18N
        deleteAllProductBtn.setIcon(new javax.swing.ImageIcon(
                getClass().getResource("../../../../../resources/images/remove-from-cart (Custom)_1.png")));
        deleteAllProductBtn.setText("Delete all products");

        javax.swing.GroupLayout summaryLblLayout = new javax.swing.GroupLayout(summaryLbl);
        summaryLbl.setLayout(summaryLblLayout);
        summaryLblLayout.setHorizontalGroup(
                summaryLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, summaryLblLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(summaryLblLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(summaryLblLayout.createSequentialGroup()
                                                .addComponent(chooseAllTxtBox)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        275, Short.MAX_VALUE)
                                                .addComponent(deleteAllProductBtn,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 220,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(summaryLblLayout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(jLabel1)))
                                .addGap(6, 6, 6)
                                .addGroup(summaryLblLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(proceedToCheckoutBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(totalMoneyTxtFld, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 194,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap()));
        summaryLblLayout.setVerticalGroup(
                summaryLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(summaryLblLayout.createSequentialGroup()
                                .addGap(8, 8, 8)
                                .addGroup(
                                        summaryLblLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(totalMoneyTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        36, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(summaryLblLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(summaryLblLayout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(chooseAllTxtBox))
                                        .addGroup(summaryLblLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(summaryLblLayout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(proceedToCheckoutBtn,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(deleteAllProductBtn,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addContainerGap(10, Short.MAX_VALUE)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 466, Short.MAX_VALUE));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(summaryLbl, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(summaryLbl, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
    }// </editor-fold>//GEN-END:initComponents

    private void totalMoneyTxtFldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_totalMoneyTxtFldActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_totalMoneyTxtFldActionPerformed

    private void proceedToCheckoutBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_proceedToCheckoutBtnActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_proceedToCheckoutBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox chooseAllTxtBox;
    private javax.swing.JButton deleteAllProductBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton proceedToCheckoutBtn;
    private javax.swing.JPanel summaryLbl;
    private javax.swing.JTextField totalMoneyTxtFld;
    // End of variables declaration//GEN-END:variables
}
