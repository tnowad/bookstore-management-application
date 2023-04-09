/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bookstore.gui.customer.formCustomer;

import java.awt.Dimension;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;

import com.bookstore.bus.BookBUS;
import com.bookstore.model.BookModel;

/**
 *
 * @author Danh
 */
public class BrowseProductPanel extends javax.swing.JPanel {

  /**
   * Creates new form BrowswProductUI
   * 
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public BrowseProductPanel() throws ClassNotFoundException, SQLException {
    initComponents();

  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   * 
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() throws ClassNotFoundException, SQLException {

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    sortByComboBox = new javax.swing.JComboBox<>();
    searchBookTxtFld = new javax.swing.JTextField();
    searchBtn = new javax.swing.JButton();
    jPanel3 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    table = new javax.swing.JPanel();

    jLabel1.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    jLabel1.setText("Sort by:");

    sortByComboBox.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    sortByComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(
        new String[] { "Recommendation", "price: low -> high", "price: high -> low" }));
    sortByComboBox.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        sortByComboBoxActionPerformed(evt);
      }
    });

    searchBookTxtFld.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    searchBookTxtFld.setText("Search anything here..");
    searchBookTxtFld.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        searchBookTxtFldActionPerformed(evt);
      }
    });

    searchBtn.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
    searchBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        searchBtnActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sortByComboBox, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBookTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 450,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addContainerGap()));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchBookTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
            .addComponent(sortByComboBox)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
            .addComponent(searchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE));

    jPanel3.setLayout(new java.awt.GridLayout(1, 0));

    table.setLayout(new java.awt.GridLayout(50, 50));

    BookBUS bookBUS = BookBUS.getInstance();
    List<BookModel> ListBook = bookBUS.getAllModels();

    for (BookModel book : ListBook) {
      BookProductPanel bookProductPanel = new BookProductPanel(book.getIsbn(), book.getTitle(), book.getDescription(),
          book.getImage(), book.getPrice(), book.getQuantity(), book.getStatus(), book.getPublisherId(),
          book.getAuthorId());
      table.add(bookProductPanel);
    }

    jScrollPane2.setViewportView(table);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE))
            .addComponent(jScrollPane2));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));
  }// </editor-fold>//GEN-END:initComponents

  private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_searchBtnActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_searchBtnActionPerformed

  private void searchBookTxtFldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_searchBookTxtFldActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_searchBookTxtFldActionPerformed

  private void sortByComboBoxActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_sortByComboBoxActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_sortByComboBoxActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTextField searchBookTxtFld;
  private javax.swing.JButton searchBtn;
  private javax.swing.JComboBox<String> sortByComboBox;
  private javax.swing.JPanel table;

  // End of variables declaration//GEN-END:variables
  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    JFrame frame = new JFrame();
    frame.setSize(new Dimension(1080, 600));
    frame.add(new BrowseProductPanel());
    frame.setVisible(true);
    ;
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
  }

}
