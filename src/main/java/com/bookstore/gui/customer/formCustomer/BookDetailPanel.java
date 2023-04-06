
package com.bookstore.gui.customer.formCustomer;

public class BookDetailPanel extends javax.swing.JPanel {

  public BookDetailPanel() {
    initComponents();
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {

    getBookImagePanel = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    jLabel3 = new javax.swing.JLabel();
    jLabel4 = new javax.swing.JLabel();
    jSeparator1 = new javax.swing.JSeparator();
    jLabel5 = new javax.swing.JLabel();
    getBookTitleTxtFld = new javax.swing.JTextField();
    jSeparator2 = new javax.swing.JSeparator();
    getAvailableQuantityTxtFld = new javax.swing.JTextField();
    getBookPriceTxtFld = new javax.swing.JTextField();
    getBookIsbnTxtFld = new javax.swing.JTextField();
    getStatusTxtFld = new javax.swing.JTextField();
    quantitySpinner = new javax.swing.JSpinner();
    jLabel1 = new javax.swing.JLabel();
    getAuthorNameTxtFld = new javax.swing.JTextField();
    jLabel7 = new javax.swing.JLabel();
    addtoCartBtn = new javax.swing.JButton();
    buyNowBtn = new javax.swing.JButton();
    getDescriptionPanel = new javax.swing.JPanel();
    jLabel6 = new javax.swing.JLabel();

    javax.swing.GroupLayout getBookImagePanelLayout = new javax.swing.GroupLayout(getBookImagePanel);
    getBookImagePanel.setLayout(getBookImagePanelLayout);
    getBookImagePanelLayout.setHorizontalGroup(
        getBookImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE));
    getBookImagePanelLayout.setVerticalGroup(
        getBookImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 272, Short.MAX_VALUE));

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18));
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel2.setText("ISBN");

    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18));
    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel3.setText("Available Quantity");

    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18));
    jLabel4.setText("Status:");

    jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18));
    jLabel5.setText("Price:");

    getBookTitleTxtFld.setEditable(false);
    getBookTitleTxtFld.setFont(new java.awt.Font("Segoe UI", 1, 14));
    getBookTitleTxtFld.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getBookTitleTxtFldActionPerformed(evt);
      }
    });

    getAvailableQuantityTxtFld.setEditable(false);
    getAvailableQuantityTxtFld.setFont(new java.awt.Font("Segoe UI", 1, 18));

    getBookPriceTxtFld.setEditable(false);
    getBookPriceTxtFld.setFont(new java.awt.Font("Segoe UI", 1, 18));
    getBookPriceTxtFld.setForeground(new java.awt.Color(255, 51, 51));

    getBookIsbnTxtFld.setEditable(false);
    getBookIsbnTxtFld.setFont(new java.awt.Font("Segoe UI", 1, 18));

    getStatusTxtFld.setEditable(false);
    getStatusTxtFld.setFont(new java.awt.Font("Segoe UI", 1, 18));

    quantitySpinner.setFont(new java.awt.Font("Segoe UI", 0, 18));

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18));
    jLabel1.setText("Quanity");

    getAuthorNameTxtFld.setEditable(false);
    getAuthorNameTxtFld.setFont(new java.awt.Font("Segoe UI", 1, 18));

    jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 18));
    jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
    jLabel7.setText("Author");

    addtoCartBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
    // addtoCartBtn.setIcon(new
    // javax.swing.ImageIcon(getClass().getResource("/icon/cart (Custom).png"))); //

    addtoCartBtn.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/cart (Custom).png")));
    addtoCartBtn.setText("Add to cart");
    addtoCartBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        addtoCartBtnActionPerformed(evt);
      }
    });

    buyNowBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
    buyNowBtn.setText("Buy now");
    buyNowBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        buyNowBtnActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(getBookTitleTxtFld)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(getBookPriceTxtFld,
                            javax.swing.GroupLayout.PREFERRED_SIZE, 150,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(153, 153, 153)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getStatusTxtFld))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                false)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2,
                                javax.swing.GroupLayout.Alignment.LEADING,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getAuthorNameTxtFld,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 240,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(getAvailableQuantityTxtFld,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 120,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 142, Short.MAX_VALUE)
                                .addComponent(addtoCartBtn))
                            .addComponent(getBookIsbnTxtFld,
                                javax.swing.GroupLayout.PREFERRED_SIZE, 240,
                                javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buyNowBtn)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(
                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(quantitySpinner,
                                        javax.swing.GroupLayout.PREFERRED_SIZE,
                                        80,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap()))));
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(getBookTitleTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout
                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(getBookPriceTxtFld,
                            javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4))
                    .addComponent(getStatusTxtFld))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81,
                            Short.MAX_VALUE)
                        .addGroup(jPanel2Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(quantitySpinner,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(getBookIsbnTxtFld,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(getAvailableQuantityTxtFld,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(getAuthorNameTxtFld,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addtoCartBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buyNowBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 32,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)));

    javax.swing.GroupLayout getDescriptionPanelLayout = new javax.swing.GroupLayout(getDescriptionPanel);
    getDescriptionPanel.setLayout(getDescriptionPanelLayout);
    getDescriptionPanelLayout.setHorizontalGroup(
        getDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 809, Short.MAX_VALUE));
    getDescriptionPanelLayout.setVerticalGroup(
        getDescriptionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE));

    jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 24));
    jLabel6.setText("Description:");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(getDescriptionPanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(getBookImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getBookImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 16,
                    Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getDescriptionPanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));
  }// </editor-fold>//GEN-END:initComponents

  private void getBookTitleTxtFldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_getBookTitleTxtFldActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_getBookTitleTxtFldActionPerformed

  private void addtoCartBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_addtoCartBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_addtoCartBtnActionPerformed

  private void buyNowBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_buyNowBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_buyNowBtnActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton addtoCartBtn;
  private javax.swing.JButton buyNowBtn;
  private javax.swing.JTextField getAuthorNameTxtFld;
  private javax.swing.JTextField getAvailableQuantityTxtFld;
  private javax.swing.JPanel getBookImagePanel;
  private javax.swing.JTextField getBookIsbnTxtFld;
  private javax.swing.JTextField getBookPriceTxtFld;
  private javax.swing.JTextField getBookTitleTxtFld;
  private javax.swing.JPanel getDescriptionPanel;
  private javax.swing.JTextField getStatusTxtFld;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JSeparator jSeparator2;
  private javax.swing.JSpinner quantitySpinner;
  // End of variables declaration//GEN-END:variables
}
