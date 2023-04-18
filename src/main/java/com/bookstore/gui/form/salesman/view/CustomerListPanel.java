package com.bookstore.gui.form.salesman.view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;
import com.bookstore.models.UserModel;

public class CustomerListPanel extends javax.swing.JPanel {
        UserBUS userBus = UserBUS.getInstance();
        List<UserModel> customersList = userBus.getAllModels();

        public CustomerListPanel() {
                initComponents();
                listCustomer();
        }

        private void listCustomer() {
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Id");
                model.addColumn("Name");
                model.addColumn("Email");
                model.addColumn("Phone");
                model.addColumn("Status");
                for (UserModel customer : customersList) {
                        model.addRow(new Object[] { customer.getId(), customer.getName(), customer.getEmail(),
                                        customer.getPhone(),
                                        customer.getStatus() });
                        customerTableList.setModel(model);
                }
        }

        private void initComponents() {

                jSeparator1 = new javax.swing.JSeparator();
                CustomerListUtility = new javax.swing.JPanel();
                exportCustomerBtn = new Button("Export");
                importCustomerListBtn = new Button("Import");
                searchBtn = new Button("Search");
                searchCustomerTxtFld = new javax.swing.JTextField();
                jLabel1 = new javax.swing.JLabel();
                addCustomerBtn = new Button("Add");
                jScrollPane1 = new javax.swing.JScrollPane();
                jPanel2 = new javax.swing.JPanel();
                jScrollPane2 = new javax.swing.JScrollPane();
                customerTableList = new javax.swing.JTable();

                exportCustomerBtn.setFont(new java.awt.Font("Arial", 0, 18));
                exportCustomerBtn.setIcon(
                                new ImageIcon(getClass().getResource("../../../../../../resources/images/export.png")));

                importCustomerListBtn.setFont(new ThemeFont().getSmallFont()    );
                importCustomerListBtn
                                .setIcon(new ImageIcon(
                                                getClass().getResource(
                                                                "../../../../../../resources/images/import.png")));
                importCustomerListBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                importCustomerListBtnActionPerformed(evt);
                        }
                });

                searchBtn.setIcon(
                                new ImageIcon(getClass()
                                                .getResource("../../../../../../resources/images/search.png")));

                searchCustomerTxtFld.setFont(new ThemeFont().getSmallFont()     );

                jLabel1.setFont(new ThemeFont().getSmallFont()  );

                addCustomerBtn.setFont(new ThemeFont().getSmallFont()   );
                addCustomerBtn.setIcon(
                                new ImageIcon(
                                                getClass().getResource(
                                                                "../../../../../../resources/images/addCustomer.png")));
                addCustomerBtn.setText("Add customer");
                addCustomerBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                addCustomerBtnActionPerformed(evt);
                        }
                });

                javax.swing.GroupLayout CustomerListUtilityLayout = new javax.swing.GroupLayout(CustomerListUtility);
                CustomerListUtility.setLayout(CustomerListUtilityLayout);
                CustomerListUtilityLayout.setHorizontalGroup(
                                CustomerListUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel1)
                                                                                .addGroup(CustomerListUtilityLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(searchCustomerTxtFld,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                                                                430,
                                                                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(searchBtn)))
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(CustomerListUtilityLayout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(importCustomerListBtn)
                                                                                                .addPreferredGap(
                                                                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(exportCustomerBtn))
                                                                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                CustomerListUtilityLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                .addComponent(addCustomerBtn)))
                                                                .addContainerGap()));
                CustomerListUtilityLayout.setVerticalGroup(
                                CustomerListUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(addCustomerBtn))
                                                                .addGap(23, 23, 23)
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(CustomerListUtilityLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(
                                                                                                                CustomerListUtilityLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                javax.swing.GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(importCustomerListBtn)
                                                                                                                                .addComponent(exportCustomerBtn)
                                                                                                                                .addComponent(searchBtn))
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addComponent(searchCustomerTxtFld))
                                                                .addContainerGap()));

                customerTableList.getTableHeader().setReorderingAllowed(false);
                jScrollPane2.setViewportView(customerTableList);

                javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
                                jPanel2);
                jPanel2.setLayout(jPanel2Layout);
                jPanel2Layout.setHorizontalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 903,
                                                                Short.MAX_VALUE));
                jPanel2Layout.setVerticalGroup(
                                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 440,
                                                                Short.MAX_VALUE));

                jScrollPane1.setViewportView(jPanel2);

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
                                this);
                this.setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addContainerGap()
                                                .addComponent(CustomerListUtility, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                .addComponent(jScrollPane1)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING));
                layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout
                                                .createSequentialGroup()
                                                .addComponent(CustomerListUtility,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1)));
        }

        private void addCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void importCustomerListBtnActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private JPanel CustomerListUtility;
        private Button addCustomerBtn;
        private JTable customerTableList;
        private Button exportCustomerBtn;
        private Button importCustomerListBtn;
        private JLabel jLabel1;
        private JPanel jPanel2;
        private JScrollPane jScrollPane1;
        private JScrollPane jScrollPane2;
        private JSeparator jSeparator1;
        private Button searchBtn;
        private JTextField searchCustomerTxtFld;

        public static void main(String[] args) {
                JFrame jFrame = new JFrame();
                jFrame.add(new CustomerListPanel());
                jFrame.setVisible(true);
        }
}
