package com.bookstore.gui.form.salesman.view;

import javax.swing.JFrame;
import com.bookstore.bus.ImportBUS;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.bookstore.models.ImportModel;

public class ImportListPanel extends javax.swing.JPanel {

    ImportBUS importBus = ImportBUS.getInstance();
    List<ImportModel> importList = importBus.getAllModels();

    public ImportListPanel() {
        initComponents();
        listImport();
        for (ImportModel importModel : importList) {
            System.out.println(importModel.getId());
        }
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
                    imports.getId(), imports.getProviderId(), imports.getEmployeeId(), imports.getTotalPrice(),
                    imports.getCreatedAt(), imports.getUpdatedAt()
            });
            importTableList.setModel(model);
        }
        importTableList.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(importTableList);
    }

    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        importTableList = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        CustomerListUtility = new javax.swing.JPanel();
        exportCustomerBtn = new javax.swing.JButton();
        importCustomerListBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        searchCustomerTxtFld = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        addCustomerBtn = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();

        exportCustomerBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
        exportCustomerBtn
                .setIcon(new javax.swing.ImageIcon(
                        getClass().getResource("../../../../../../resources/images/export.png")));
        exportCustomerBtn.setText("Export");

        importCustomerListBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
        importCustomerListBtn
                .setIcon(new javax.swing.ImageIcon(
                        getClass().getResource("../../../../../../resources/images/import.png")));
        importCustomerListBtn.setText("Import");
        importCustomerListBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importCustomerListBtnActionPerformed(evt);
            }
        });

        searchBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
        searchBtn
                .setIcon(new javax.swing.ImageIcon(
                        getClass().getResource("../../../../../../resources/images/search.png")));
        searchBtn.setText("Search");

        searchCustomerTxtFld.setFont(new java.awt.Font("Segoe UI", 0, 18));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel1.setText("Imports");

        addCustomerBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
        addCustomerBtn
                .setIcon(new javax.swing.ImageIcon(
                        getClass().getResource("../../../../../../resources/images/addBook.png")));
        addCustomerBtn.setText("Add receipt");
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
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1)
                                        .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                                .addComponent(searchCustomerTxtFld,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE, 430,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(searchBtn)))
                                .addGroup(CustomerListUtilityLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(importCustomerListBtn)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(exportCustomerBtn))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                CustomerListUtilityLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(addCustomerBtn)))
                                .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                CustomerListUtilityLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 911,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)));
        CustomerListUtilityLayout.setVerticalGroup(
                CustomerListUtilityLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(CustomerListUtilityLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(addCustomerBtn))
                                .addGap(23, 23, 23)
                                .addGroup(CustomerListUtilityLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                                .addGroup(
                                                        CustomerListUtilityLayout
                                                                .createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.BASELINE)
                                                                .addComponent(importCustomerListBtn)
                                                                .addComponent(exportCustomerBtn)
                                                                .addComponent(searchBtn))
                                                .addGap(0, 0, Short.MAX_VALUE))
                                        .addComponent(searchCustomerTxtFld))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2)
                        .addComponent(CustomerListUtility, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator2));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(CustomerListUtility, javax.swing.GroupLayout.PREFERRED_SIZE, 100,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 442,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));
    }

    private void importCustomerListBtnActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private void addCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {
    }

    private javax.swing.JPanel CustomerListUtility;
    private javax.swing.JButton addCustomerBtn;
    private javax.swing.JTable importTableList;
    private javax.swing.JButton exportCustomerBtn;
    private javax.swing.JButton importCustomerListBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField searchCustomerTxtFld;

    public static void main(String[] args) {
        JFrame jFrame = new JFrame();
        jFrame.add(new ImportListPanel());
        jFrame.setVisible(true);
    }
}
