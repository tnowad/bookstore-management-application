
package com.bookstore.gui.customer.formCustomer;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;

public class BookProductPanel extends javax.swing.JPanel implements MouseListener {

  public BookProductPanel(String isbn, String title, String description,String image, int price, int quantity, Enum status,int publisher_id, int author_id) {
    initComponents( isbn,  title,  description, image,  price,  quantity,  status, publisher_id,  author_id);
    

  }

  @SuppressWarnings("unchecked")

  private void initComponents(String isbn, String title, String description,String image, int price, int quantity, Enum status,int publisher_id, int author_id) {

    getBookImagePanel = new javax.swing.JPanel();
    getBookTitleTxtFld = new javax.swing.JTextField();
    bookDetailBtn = new javax.swing.JToggleButton();

    javax.swing.GroupLayout getBookImagePanelLayout = new javax.swing.GroupLayout(getBookImagePanel);
    getBookImagePanel.setLayout(getBookImagePanelLayout);
    getBookImagePanelLayout.setHorizontalGroup(
        getBookImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE));
    getBookImagePanelLayout.setVerticalGroup(
        getBookImagePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE));

    getBookTitleTxtFld.setEditable(false);
    getBookTitleTxtFld.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    getBookTitleTxtFld.setText(title);
    getBookTitleTxtFld.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        getBookTitleTxtFldActionPerformed(evt);
      }
    });

    // bookDetailBtn.setIcon(new
    // javax.swing.ImageIcon(getClass().getResource("/icon/open-book
    // (Custom).png"))); // NOI18N
    bookDetailBtn.setIcon(new javax.swing.ImageIcon(
        getClass().getResource("../../../../../resources/images/open-book (Custom).png"))); // NOI18N
    bookDetailBtn.setText("Book Detail");
    bookDetailBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        BookDetailPanel bookDetailPanel = new BookDetailPanel(isbn,  title,  description, image,  price,  quantity,  status, publisher_id,  author_id);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(new Dimension(1000,600));
        frame.add(bookDetailPanel);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
      }
    });

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(getBookImagePanel, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(getBookTitleTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 188,
                        Short.MAX_VALUE)
                    .addComponent(bookDetailBtn, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(getBookTitleTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 28,
                    Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getBookImagePanel, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookDetailBtn)
                .addContainerGap()));
  }// </editor-fold>//GEN-END:initComponents

  private void getBookTitleTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }// GEN-LAST:event_getBookTitleTxtFldActionPerformed

  private void bookDetailBtnActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }// GEN-LAST:event_bookDetailBtnActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JToggleButton bookDetailBtn;
  private javax.swing.JPanel getBookImagePanel;
  private javax.swing.JTextField getBookTitleTxtFld;
  // End of variables declaration//GEN-END:variables
  @Override
  public void mouseClicked(MouseEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
  }

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
  }
}
