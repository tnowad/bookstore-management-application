/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bookstore.gui.form.admin.component.bookListComponent;

/**
 *
 * @author yanti
 */
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

import javax.swing.*;

import com.bookstore.bus.BookBUS;
import com.bookstore.models.BookModel;

public class BrowseProductPanel extends JPanel {
  private static BrowseProductPanel instance;
  private BookBUS bookBUS;
  private List<BookModel> listBook;

  /**
   * Creates new form BrowseProductPanel
   */
  public BrowseProductPanel() {
    bookBUS = BookBUS.getInstance();
    listBook = bookBUS.getAllModels();

    initComponents();
  }

  public static BrowseProductPanel getInstance() {
    if (instance == null) {
      instance = new BrowseProductPanel();
    }
    return instance;
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

    jLabel1 = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jPanel2 = new javax.swing.JPanel();
    jPanel3 = new javax.swing.JPanel();
    ButtonExport = new javax.swing.JButton();
    ButtonInport = new javax.swing.JButton();
    ButtonCreate = new javax.swing.JButton();
    ButtonDelete = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    table = new javax.swing.JPanel();

    setPreferredSize(new java.awt.Dimension(702, 444));

    jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 0, 51));
    jLabel1.setText("List Book");

    jPanel1.setLayout(new java.awt.BorderLayout());

    jPanel2.setLayout(new java.awt.BorderLayout());

    ButtonExport.setText("Xuất File");
    ButtonExport.setToolTipText("");

    ButtonInport.setText("Nhập File");

    ButtonCreate.setText("Create");

    ButtonDelete.setText("Delete");
    ButtonDelete.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        try {
          actionDelete();
        } catch (ClassNotFoundException | SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    });

    javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(ButtonExport)
                .addGap(18, 18, 18)
                .addComponent(ButtonInport)
                .addGap(18, 18, 18)
                .addComponent(ButtonCreate)
                .addGap(18, 18, 18)
                .addComponent(ButtonDelete)
                .addGap(0, 39, Short.MAX_VALUE)));
    jPanel3Layout.setVerticalGroup(
        jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ButtonCreate, javax.swing.GroupLayout.DEFAULT_SIZE, 31,
                        Short.MAX_VALUE)
                    .addComponent(ButtonExport, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonInport, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ButtonDelete, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap()));

    jPanel2.add(jPanel3, java.awt.BorderLayout.LINE_END);

    jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

    table.setLayout(new GridLayout(0, 3, 5, 5));
    
    for (BookModel book : listBook) {
      if (!book.getStatus().toString().equals("DELETED")) {
        BookProductPanel bookProductPanel = new BookProductPanel(book.getIsbn(), book.getTitle(),
            book.getDescription(), book.getImage(), book.getPrice(), book.getQuantity(), book.getStatus(),
            book.getPublisherId(), book.getAuthorId());
        table.add(bookProductPanel);
      }
    }

    jScrollPane1.setViewportView(table);
    jScrollPane1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

    jPanel1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 89,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE,
                            Short.MAX_VALUE)))));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
  }// </editor-fold>//GEN-END:initComponents

  public void actionDelete() throws ClassNotFoundException, SQLException {
    for (Component component : table.getComponents()) {
      JPanel subPanel = (JPanel) component;
      for (Component subComponent : subPanel.getComponents()) {
        if (subComponent instanceof JCheckBox && ((JCheckBox) subComponent).isSelected()) {
          Component[] components = subPanel.getComponents();
          boolean foundTextField = false;
          for (Component c : components) {
            if (c instanceof JTextField && !foundTextField) {
              foundTextField = true;
              String id = ((JTextField) c).getText();
              String status = "DELETED";
              int updateStatusRows = BookBUS.getInstance().updateStatus(id, status);
              if (updateStatusRows == 1) {
                JOptionPane.showMessageDialog(null, " KhĂ³a TĂ i Khoáº£n ThĂ nh CĂ´ng !");
              }
            }
          }
        }
      }
    }
    table.revalidate();
    table.repaint();
  }

  // public void actionSearch(){
  // String[] columns = new String[]
  // {"isbn","title","description","price","quantity","status","publisher_id","author_id"};
  // List<BookModel> list =
  // BookBUS.getInstance().searchModel(valueSearch.getText(),columns);
  // table.removeAll();
  // table.setLayout(new GridLayout(0,0,10,10));
  // for(BookModel book : list){
  // if(!book.getStatus().toString().equals("DELETED")){
  // BookProductPanel bookProductPanel = new
  // BookProductPanel(book.getIsbn(),book.getTitle(),book.getDescription(),book.getImage(),book.getPrice(),book.getQuantity(),book.getStatus(),
  // book.getPublisherId(),book.getAuthorId());
  // table.add(bookProductPanel);
  // }
  // }
  // table.revalidate();
  // table.repaint();
  // }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton ButtonCreate;
  private javax.swing.JButton ButtonDelete;
  private javax.swing.JButton ButtonExport;
  private javax.swing.JButton ButtonInport;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JPanel table;
  // End of variables declaration//GEN-END:variables
}
