package com.bookstore.gui.form.salesman.view;

import javax.swing.JFrame;
import com.bookstore.bus.ImportBUS;
import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;

import java.util.List;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import com.bookstore.models.ImportModel;

public class ImportListPanel extends JPanel {

        ImportBUS importBus = ImportBUS.getInstance();
        List<ImportModel> importList = importBus.getAllModels();

        public ImportListPanel() {
                initComponents();
                listImport();
        }

        private void listImport() {
                // "ID", "Provider ID", "Employee ID", "Price", "Status"
                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("Id");
                model.addColumn("Provider ID");
                model.addColumn("Employee ID");
                model.addColumn("Price");
                model.addColumn("Created at");
                model.addColumn("Updated at");
                for (ImportModel imports : importList) {
                        model.addRow(new Object[] {
                                        imports.getId(), imports.getProviderId(), imports.getEmployeeId(),
                                        imports.getTotalPrice(),
                                        imports.getCreatedAt(), imports.getUpdatedAt()
                        });
                        importTableList.setModel(model);
                }
                importTableList.getTableHeader().setReorderingAllowed(false);
                jScrollPane2.setViewportView(importTableList);
        }

        private void initComponents() {

                jScrollPane2 = new JScrollPane();
                importTableList = new JTable();
                jSeparator2 = new JSeparator();
                CustomerListUtility = new JPanel();
                exportCustomerBtn = new Button("Export");
                importCustomerListBtn = new Button("Import");
                searchBtn = new Button("Search");
                searchCustomerTxtFld = new JTextField();
                jLabel1 = new JLabel();
                addCustomerBtn = new Button("Add");
                jSeparator1 = new JSeparator();

                exportCustomerBtn.setFont(new ThemeFont().getSmallFont());
                exportCustomerBtn
                                .setIcon(new ImageIcon(
                                                getClass().getResource(
                                                                "../../../../../../resources/images/export.png")));
                exportCustomerBtn.setText("Export");

                importCustomerListBtn.setFont(new ThemeFont().getSmallFont());
                importCustomerListBtn
                                .setIcon(new ImageIcon(
                                                getClass().getResource(
                                                                "../../../../../../resources/images/import.png")));
                importCustomerListBtn.setText("Import");
                importCustomerListBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                importCustomerListBtnActionPerformed(evt);
                        }
                });

                searchBtn.setFont(new ThemeFont().getSmallFont());
                searchBtn
                                .setIcon(new ImageIcon(
                                                getClass().getResource(
                                                                "../../../../../../resources/images/search.png")));
                searchBtn.setText("Search");

                searchCustomerTxtFld.setFont(new ThemeFont().getSmallFont());

                jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24));
                jLabel1.setText("Imports");

                addCustomerBtn.setFont(new ThemeFont().getSmallFont());
                addCustomerBtn
                                .setIcon(new ImageIcon(
                                                getClass().getResource(
                                                                "../../../../../../resources/images/addBook.png")));
                addCustomerBtn.setText("Add receipt");
                addCustomerBtn.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                addCustomerBtnActionPerformed(evt);
                        }
                });

                GroupLayout CustomerListUtilityLayout = new GroupLayout(CustomerListUtility);
                CustomerListUtility.setLayout(CustomerListUtilityLayout);
                CustomerListUtilityLayout.setHorizontalGroup(
                                CustomerListUtilityLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(jLabel1)
                                                                                .addGroup(CustomerListUtilityLayout
                                                                                                .createSequentialGroup()
                                                                                                .addComponent(searchCustomerTxtFld,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                430,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(searchBtn)))
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(CustomerListUtilityLayout
                                                                                                .createSequentialGroup()
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(importCustomerListBtn)
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                                                .addComponent(exportCustomerBtn))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                CustomerListUtilityLayout
                                                                                                                .createSequentialGroup()
                                                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                                                .addComponent(addCustomerBtn)))
                                                                .addContainerGap())
                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                CustomerListUtilityLayout.createSequentialGroup()
                                                                                .addGap(0, 0, Short.MAX_VALUE)
                                                                                .addComponent(jSeparator1,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                911,
                                                                                                GroupLayout.PREFERRED_SIZE)));
                CustomerListUtilityLayout.setVerticalGroup(
                                CustomerListUtilityLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(jLabel1)
                                                                                .addComponent(addCustomerBtn))
                                                                .addGap(23, 23, 23)
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(CustomerListUtilityLayout
                                                                                                .createSequentialGroup()
                                                                                                .addGroup(
                                                                                                                CustomerListUtilityLayout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                                                                .addComponent(importCustomerListBtn)
                                                                                                                                .addComponent(exportCustomerBtn)
                                                                                                                                .addComponent(searchBtn))
                                                                                                .addGap(0, 0, Short.MAX_VALUE))
                                                                                .addComponent(searchCustomerTxtFld))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jSeparator1,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                10,
                                                                                GroupLayout.PREFERRED_SIZE)));

                GroupLayout layout = new GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane2)
                                                .addComponent(CustomerListUtility, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jSeparator2));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout
                                                                .createSequentialGroup()
                                                                .addComponent(CustomerListUtility,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                100,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jSeparator2,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                10,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(jScrollPane2,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                442,
                                                                                GroupLayout.PREFERRED_SIZE)));
        }

        private void importCustomerListBtnActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private void addCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {
        }

        private Button addCustomerBtn;
        private Button exportCustomerBtn;
        private Button importCustomerListBtn;
        private Button searchBtn;
        private JPanel CustomerListUtility;
        private JTable importTableList;
        private JLabel jLabel1;
        private JScrollPane jScrollPane2;
        private JSeparator jSeparator1;
        private JSeparator jSeparator2;
        private JTextField searchCustomerTxtFld;

        public static void main(String[] args) {
                JFrame jFrame = new JFrame();
                jFrame.add(new ImportListPanel());
                jFrame.setVisible(true);
        }
}
