package com.bookstore.gui.form.cart;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.gui.model.CounterModel;
import com.bookstore.model.BookModel;
import com.bookstore.model.CartItemsModel;

public class CartSection extends javax.swing.JPanel {

  private javax.swing.JTextArea DescriptionTextArea;
  private javax.swing.JCheckBox checkBoxChooseBookButton;
  private javax.swing.JButton deleteProductBtn;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JLabel lbPrice;
  private javax.swing.JLabel lblBookName;
  private javax.swing.JButton minusBtn;
  private javax.swing.JButton plusBtn;
  private javax.swing.JTextField txtQuantity;

  public CartSection(String Title, int Price, int Quantity, String bookIsbn, int cartId)
      throws ClassNotFoundException, SQLException {
    initComponents(Title, Price, Quantity, bookIsbn, cartId);
    setName(Title);
  }

  private void initComponents(String Title, int Price, int Quantity, String bookIsbn, int cartId)
      throws ClassNotFoundException, SQLException {

    lblBookName = new javax.swing.JLabel();
    lbPrice = new javax.swing.JLabel();
    txtQuantity = new javax.swing.JTextField();
    minusBtn = new javax.swing.JButton();
    plusBtn = new javax.swing.JButton();
    deleteProductBtn = new javax.swing.JButton();
    jPanel1 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    DescriptionTextArea = new javax.swing.JTextArea();
    checkBoxChooseBookButton = new javax.swing.JCheckBox();
    checkBoxChooseBookButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
          checkTickBtnActionPerformed(evt);
        } catch (ClassNotFoundException | SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });
    lblBookName.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
    lblBookName.setForeground(new java.awt.Color(76, 76, 76));
    lblBookName.setText(Title);

    lbPrice.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
    lbPrice.setForeground(new java.awt.Color(76, 76, 76));
    lbPrice.setText("" + Integer.valueOf(Price) * Quantity);

    txtQuantity.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    txtQuantity.setText("" + Quantity);

    minusBtn.setText("-");
    minusBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
          minusBtnActionPerformed(evt, Price, Quantity, bookIsbn, cartId);
        } catch (ClassNotFoundException e) {
          e.printStackTrace();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    });
    plusBtn.setText("+");
    plusBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
          plusBtnActionPerformed(evt, Price, Quantity, bookIsbn, cartId);
        } catch (ClassNotFoundException | SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });

    deleteProductBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
          deleteProductBtnActionPerformed(evt);
        } catch (ClassNotFoundException | SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });

    DescriptionTextArea.setColumns(20);
    DescriptionTextArea.setRows(5);
    jScrollPane1.setViewportView(DescriptionTextArea);

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(checkBoxChooseBookButton)
                .addGap(167, 167, 167)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblBookName, javax.swing.GroupLayout.Alignment.TRAILING,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 60,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(minusBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(plusBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 42,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(checkBoxChooseBookButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBookName)
                        .addGap(19, 19, 19)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbPrice)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(minusBtn)
                                    .addComponent(plusBtn))
                                .addComponent(deleteProductBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(28, 28, 28)))));
  }// </editor-fold>//GEN-END:initComponents

  private void minusBtnActionPerformed(java.awt.event.ActionEvent evt, int Price, int Quantity, String bookIsbn,
      int cartId) throws ClassNotFoundException, SQLException {
    // Get the CartItemsBUS instance
    CartItemsBUS cartItemsBUS = CartItemsBUS.getInstance();

    // Set the price label
    lbPrice.setText(Integer.toString(Price));
    lbPrice.revalidate();
    lbPrice.repaint();

    // Get the matching cart item
    CartItemsModel item = cartItemsBUS.getModelById(cartId);

    if (item != null && item.getBookIsbn().equals(bookIsbn)) {
      // Increment the quantity using the CounterModel
      CounterModel counter = new CounterModel();
      counter.setValue(item.getQuantity());
      counter.DecreaseValue();
      int newQuantity = counter.getValue();

      if (newQuantity >= 1) {
        // Update the cart item quantity and price
        item.setQuantity(newQuantity);
        int basedPrice = BookBUS.getInstance().getModelByIsbn(bookIsbn).getPrice();
        int updatedPrice = basedPrice * newQuantity;
        item.setPrice(updatedPrice);
        lbPrice.setText(String.valueOf(updatedPrice));

        try {
          int success = cartItemsBUS.updateModel(item);
          if (success == 0) {
            System.err.println("Update failed for CartItemsModel: " + item.toString());
          } else {
            item = cartItemsBUS.getModelById(item.getCartId());
            txtQuantity.setText(newQuantity + "");
          }
        } catch (Exception e) {
          System.err.println("Error while updating CartItemsModel: " + e.getMessage());
          e.printStackTrace();
        }

        // Check if new quantity is less than or equal to 1
        if (newQuantity < 1) {
          // Display confirmation dialog to delete item
          int confirmed = JOptionPane.showConfirmDialog(null,
              "You are about to delete this item, do you want to do that?", "Confirm Delete",
              JOptionPane.YES_NO_OPTION);
          if (confirmed == JOptionPane.YES_OPTION) {
            try {
              cartItemsBUS.deleteModel(item.getCartId());
              txtQuantity.setText("0");
              lbPrice.setText("0");
            } catch (Exception e) {
              System.err.println("Error while deleting CartItemsModel: " + e.getMessage());
              e.printStackTrace();
            }
          } else {
            // User clicked No, set quantity back to 1
            counter.setValue(1);// Set quantity back to 1
            item.setQuantity(counter.getValue());
            int updatedPriceBackTo1 = basedPrice * counter.getValue();
            item.setPrice(updatedPriceBackTo1);

            try {
              cartItemsBUS.updateModel(item);
              lbPrice.setText(String.valueOf(updatedPriceBackTo1));
              txtQuantity.setText(String.valueOf(item.getQuantity()));
            } catch (Exception e) {
              System.err.println("Error while updating CartItemsModel: " + e.getMessage());
              e.printStackTrace();
            }
          }
        }
      }
    }
  }

  private void plusBtnActionPerformed(java.awt.event.ActionEvent evt, int Price, int Quantity, String bookIsbn,
      int cartId) throws ClassNotFoundException, SQLException {
    // Get the CartItemsBUS instance
    CartItemsBUS cartItemsBUS = CartItemsBUS.getInstance();

    // Set the price label
    lbPrice.setText(Integer.toString(Price));
    lbPrice.revalidate();
    lbPrice.repaint();

    // Get the matching cart item
    CartItemsModel item = cartItemsBUS.getModelById(cartId);
    BookModel bookItem = BookBUS.getInstance().getModelByIsbn(bookIsbn);
    if (item != null && item.getBookIsbn().equals(bookIsbn)) {
      // Increment the quantity using the CounterModel
      CounterModel counter = new CounterModel();
      counter.setValue(item.getQuantity());
      counter.IncreaseValue();
      int newQuantity = counter.getValue();

      if (newQuantity >= 1) {
        // Update the cart item quantity and price
        item.setQuantity(newQuantity);
        int basedPrice = BookBUS.getInstance().getModelByIsbn(bookIsbn).getPrice();
        int updatedPrice = basedPrice * newQuantity;
        item.setPrice(updatedPrice);
        lbPrice.setText(String.valueOf(updatedPrice));

        try {
          int success = cartItemsBUS.updateModel(item);
          if (success == 0) {
            System.err.println("Update failed for CartItemsModel: " + item.toString());
          } else {
            item = cartItemsBUS.getModelById(item.getCartId());
            txtQuantity.setText(newQuantity + "");
          }
        } catch (Exception e) {
          System.err.println("Error while updating CartItemsModel: " + e.getMessage());
          e.printStackTrace();
        }

        // Check if new quantity is larger than original quantity
        if (newQuantity > bookItem.getQuantity()) {
          JOptionPane.showMessageDialog(null, "You cannot go beyond book's quantity!");

          // Reset the item to its previous state
          item.setQuantity(newQuantity - 1);
          item.setPrice(basedPrice * (newQuantity - 1));
          lbPrice.setText(String.valueOf(item.getPrice()));
          try {
            int success = cartItemsBUS.updateModel(item);
            if (success == 0) {
              System.err.println("Update failed for CartItemsModel: " + item.toString());
            } else {
              item = cartItemsBUS.getModelById(item.getCartId());
              txtQuantity.setText((newQuantity - 1) + "");
            }
          } catch (Exception e) {
            System.err.println("Error while updating CartItemsModel: " + e.getMessage());
            e.printStackTrace();
          }
        }
      }
    }
  }

  private void deleteProductBtnActionPerformed(java.awt.event.ActionEvent evt)
      throws ClassNotFoundException, SQLException {
      
  }

  private void checkTickBtnActionPerformed(java.awt.event.ActionEvent evt) throws ClassNotFoundException, SQLException {

  }

}
