package com.bookstore.gui.form.salesman.view;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;

import com.bookstore.bus.UserBUS;
import com.bookstore.model.UserModel;

public class CustomerListPanel extends javax.swing.JPanel {

  public CustomerListPanel() {
    initComponents();
    listCustomer();
  }

  private void listCustomer() {
    UserBUS userBus = UserBUS.getInstance();
    List<UserModel> customersList = userBus.getAllModels();
    for (UserModel user : customersList) {
      System.out.println(user.getName());
    }
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {

    jSeparator1 = new javax.swing.JSeparator();
    CustomerListUtility = new javax.swing.JPanel();
    exportCustomerBtn = new javax.swing.JButton();
    importCustomerListBtn = new javax.swing.JButton();
    searchBtn = new javax.swing.JButton();
    searchCustomerTxtFld = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    addCustomerBtn = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane2 = new javax.swing.JScrollPane();
    customerTableList = new javax.swing.JTable();

    exportCustomerBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    exportCustomerBtn.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/export.png"))); // NOI18N
    exportCustomerBtn.setText("Export");

    importCustomerListBtn.setFont(new java.awt.Font("Segoe UI", 0, 18));
    importCustomerListBtn
        .setIcon(new javax.swing.ImageIcon(
            getClass().getResource("../../../../../resources/images/import.png"))); // NOI18N
    importCustomerListBtn.setText("Import");
    importCustomerListBtn.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        importCustomerListBtnActionPerformed(evt);
      }
    });

    searchBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    searchBtn.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/search.png"))); // NOI18N
    searchBtn.setText("Search");

    searchCustomerTxtFld.setFont(new java.awt.Font("Segoe UI", 0, 18));

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24));
    jLabel1.setText("Customers");

    addCustomerBtn.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    addCustomerBtn.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("../../../../../resources/images/addCustomer.png"))); // NOI18N
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
                .addContainerGap()));
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
                .addContainerGap()));

    customerTableList.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null }
        },
        new String[] {
            "ID", "Name", "Email", "Phone number", "Status"
        }) {
      Class[] types = new Class[] {
          java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class,
          java.lang.Object.class
      };
      boolean[] canEdit = new boolean[] {
          false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    });
    customerTableList.getTableHeader().setReorderingAllowed(false);
    jScrollPane2.setViewportView(customerTableList);

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
        jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 903, Short.MAX_VALUE));
    jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE));

    jScrollPane1.setViewportView(jPanel2);

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
        this);
    this.setLayout(layout);
    layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup().addContainerGap()
            .addComponent(CustomerListUtility, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
        .addComponent(jScrollPane1).addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING));
    layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
        .createSequentialGroup()
        .addComponent(CustomerListUtility, javax.swing.GroupLayout.PREFERRED_SIZE,
            javax.swing.GroupLayout.DEFAULT_SIZE,
            javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10,
            javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jScrollPane1)));
  }

  private void addCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void importCustomerListBtnActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private javax.swing.JPanel CustomerListUtility;
  private javax.swing.JButton addCustomerBtn;
  private javax.swing.JTable customerTableList;
  private javax.swing.JButton exportCustomerBtn;
  private javax.swing.JButton importCustomerListBtn;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JSeparator jSeparator1;
  private javax.swing.JButton searchBtn;
  private javax.swing.JTextField searchCustomerTxtFld;

  public static void main(String[] args) {
    JFrame jFrame = new JFrame();
    jFrame.add(new CustomerListPanel());
    jFrame.setVisible(true);
  }
}
