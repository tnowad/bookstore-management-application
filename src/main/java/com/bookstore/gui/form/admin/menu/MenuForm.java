/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bookstore.gui.form.admin.menu;

import javax.swing.JScrollPane;

/**
 *
 * @author yanti
 */
public class MenuForm extends javax.swing.JPanel {

    /**
     * Creates new form MenuForm
     */
    public MenuForm() {
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

        jTextField6 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        MenuDashBoard = new javax.swing.JLabel();
        MenuRepository = new javax.swing.JLabel();
        MenuUserList = new javax.swing.JLabel();
        MenuBookList = new javax.swing.JLabel();
        MenuOderList = new javax.swing.JLabel();

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("DashBoard");

        setPreferredSize(new java.awt.Dimension(180, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        // jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/book_logo.png"))); // NOI18N
        jLabel1.setText("BOOK STORE");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        jPanel1.setPreferredSize(new java.awt.Dimension(166, 800));
        jPanel1.setLayout(new java.awt.GridLayout(15, 1));

        MenuDashBoard.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuDashBoard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuDashBoard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/clipboard.png"))); // NOI18N
        MenuDashBoard.setText("DashBoard");
        MenuDashBoard.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(MenuDashBoard);

        MenuRepository.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuRepository.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        // MenuRepository.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/reposir.png"))); // NOI18N
        MenuRepository.setText("Repository");
        MenuRepository.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(MenuRepository);

        MenuUserList.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuUserList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuUserList.setText("User List");
        MenuUserList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(MenuUserList);

        MenuBookList.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuBookList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuBookList.setText("Book List");
        MenuBookList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(MenuBookList);

        MenuOderList.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        MenuOderList.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        MenuOderList.setText("Order List");
        MenuOderList.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.add(MenuOderList);

        jScrollPane1.setViewportView(jPanel1);
        jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE)
                .addGap(15, 15, 15))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel MenuBookList;
    private javax.swing.JLabel MenuDashBoard;
    private javax.swing.JLabel MenuOderList;
    private javax.swing.JLabel MenuRepository;
    private javax.swing.JLabel MenuUserList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
