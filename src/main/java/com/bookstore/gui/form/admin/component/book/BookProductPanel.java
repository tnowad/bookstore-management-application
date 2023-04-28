/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bookstore.gui.form.admin.component.book;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 *
 * @author yanti
 */
public class BookProductPanel extends javax.swing.JPanel {

  private JTextField isbnBook;

  /**
   * Creates new form BookProductPanel
   */
  public BookProductPanel(String isbn,String title,String description,String image,int price,int quantity,Enum status,int publisher_id,int author_id) {
    initComponents(isbn,title,description,image,price,quantity,status,publisher_id,author_id);
    setImage(image);
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents(String isbn,String title,String description,String image,int price,int quantity,Enum status,int publisher_id,int author_id) {

        checkBox = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        ButtonDetail = new javax.swing.JButton();
        getImageBook = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        getTitle = new javax.swing.JTextField();

        isbnBook = new javax.swing.JTextField();
        isbnBook.setText(""+isbn);
        add(isbnBook);

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMaximumSize(new java.awt.Dimension(199, 333));
        setMinimumSize(new java.awt.Dimension(199, 333));
        setPreferredSize(new java.awt.Dimension(199, 333));
        setLayout(new java.awt.BorderLayout());

        checkBox.setPreferredSize(new java.awt.Dimension(50, 19));
        checkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkBoxActionPerformed(evt);
            }
        });
        add(checkBox, java.awt.BorderLayout.PAGE_END);

        jPanel2.setLayout(new java.awt.BorderLayout());

        ButtonDetail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ButtonDetail.setText("Detail");
        ButtonDetail.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            BookDetailFrame bookDetailFrame = new BookDetailFrame(isbn, title, description, image, price, quantity, status, publisher_id, author_id);
            bookDetailFrame.setVisible(true);
          }
          
        });
        jPanel2.add(ButtonDetail, java.awt.BorderLayout.PAGE_END);

        getImageBook.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getImageBook.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getImageBook.setPreferredSize(new java.awt.Dimension(399, 260));
        jPanel2.add(getImageBook, java.awt.BorderLayout.CENTER);

        jPanel1.setMinimumSize(new java.awt.Dimension(30, 30));

        getTitle.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        getTitle.setText(title);
        getTitle.setPreferredSize(new java.awt.Dimension(220, 22));
        jPanel1.add(getTitle);

        jPanel2.add(jPanel1, java.awt.BorderLayout.PAGE_START);

        add(jPanel2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkBoxActionPerformed

  public void setImage(String image) {
    getImageBook.setIcon(new javax.swing.ImageIcon(image));
    ImageIcon icon = (ImageIcon) getImageBook.getIcon();
    int imageLoadStatus = icon.getImageLoadStatus();
    if (imageLoadStatus != 8) {
      getImageBook.setIcon(
        new javax.swing.ImageIcon(
          getClass().getResource("/resources/images_products/imagenull.png")
        )
      );
    }
  }
  

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonDetail;
    private javax.swing.JCheckBox checkBox;
    private javax.swing.JLabel getImageBook;
    private javax.swing.JTextField getTitle;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
