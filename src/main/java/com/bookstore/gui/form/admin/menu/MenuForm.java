/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bookstore.gui.form.admin.menu;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.bookstore.gui.form.admin.RunForm;
import com.bookstore.gui.form.admin.component.bookListComponent.BrowseProductPanel;

/**
 *
 * @author yanti
 */
public class MenuForm extends javax.swing.JPanel {
    private static MenuForm instance;

    /**
     * Creates new form MenuForm
     */
    public MenuForm() {
        initComponents();
    }
    public static MenuForm getInstance()  {
        if (instance == null) {
          instance = new MenuForm();
        }
        return instance;
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
        ButtonDashboard = new javax.swing.JButton();
        ButtonRepository = new javax.swing.JButton();
        ButtonListBook = new javax.swing.JButton();
        ButtonListUser = new javax.swing.JButton();
        ButtonOrder = new javax.swing.JButton();

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextField6.setText("DashBoard");

        setPreferredSize(new java.awt.Dimension(180, 500));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/images/open-book.png"))); // NOI18N
        jLabel1.setText("BOOK STORE");

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);

        jPanel1.setPreferredSize(new java.awt.Dimension(166, 800));
        jPanel1.setLayout(new java.awt.GridLayout(15, 1, 10, 10));

        ButtonDashboard.setText("Dashboard");
        jPanel1.add(ButtonDashboard);
        ButtonDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            }
        });

        ButtonRepository.setText("Repository");
        jPanel1.add(ButtonRepository);

        ButtonListBook.setText("List Book");
        ButtonListBook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                // RunForm.getInstance().action("BookList");
                System.out.println("Xóa panel cũ và thêm panel mới ở đây");
                RunForm.getConstruct().removeAll();
                RunForm.getConstruct().add(new JLabel("News"));
            }
        });
        jPanel1.add(ButtonListBook);

        ButtonListUser.setText("List User");
        ButtonListUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RunForm.getInstance().action("UserList");
            }
        });
        jPanel1.add(ButtonListUser);

        ButtonOrder.setText("List Order");
        jPanel1.add(ButtonOrder);

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
                .addGap(24, 24, 24))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ButtonDashboard;
    private javax.swing.JButton ButtonListBook;
    private javax.swing.JButton ButtonListUser;
    private javax.swing.JButton ButtonOrder;
    private javax.swing.JButton ButtonRepository;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
