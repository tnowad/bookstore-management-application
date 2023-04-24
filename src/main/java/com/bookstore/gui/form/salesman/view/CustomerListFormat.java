package com.bookstore.gui.form.salesman.view;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.button.Label;
import com.bookstore.models.UserModel;
import com.bookstore.models.UserModel.Role;

public class CustomerListFormat extends javax.swing.JPanel {
    UserBUS userBus = UserBUS.getInstance();
    List<UserModel> customersList = userBus.getAllModels();

    public CustomerListFormat() {
        initComponents();
        listCustomer();
        search();
    }

    private void search() {
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = searchCustomerTxtFld.getText();
                if (text == null || text.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm !");
                    showTable();
                } else {
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Id");
                    model.addColumn("Name");
                    model.addColumn("Email");
                    model.addColumn("Phone");
                    model.addColumn("Status");
                    for (UserModel customer : customersList) {
                        if (customer.getName().toLowerCase().contains(text.toLowerCase())) {
                            model.addRow(new Object[] { customer.getId(),
                                    customer.getName(), customer.getEmail(),
                                    customer.getPhone(),
                                    customer.getStatus() });
                            customerTableList.setModel(model);
                        }
                    }
                }
            }
        });
    }

    private void listCustomer() {
        showTable();
    }

    private void showTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("Email");
        model.addColumn("Phone");
        model.addColumn("Status");
        for (UserModel customer : customersList) {
            if (customer.getRole() == Role.CUSTOMER) {
                model.addRow(new Object[] { customer.getId(), customer.getName(), customer.getEmail(),
                        customer.getPhone(),
                        customer.getStatus() });
                customerTableList.setModel(model);
            }
        }
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        customerListLabel = new javax.swing.JLabel();
        addCustomerPanel = new javax.swing.JPanel();
        addCustomerButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        searchCustomerTxtFld = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        customerTableList = new javax.swing.JTable();

        setMinimumSize(new java.awt.Dimension(1180, 620));
        setPreferredSize(new java.awt.Dimension(1180, 620));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setLayout(new java.awt.GridLayout(2, 0));

        jPanel3.setLayout(new java.awt.GridLayout(1, 2));

        customerListLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        customerListLabel.setText("Customer List");
        customerListLabel.setPreferredSize(new java.awt.Dimension(87, 30));
        jPanel3.add(customerListLabel);

        addCustomerPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        addCustomerButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addCustomerButton.setText("Add Customer");
        addCustomerButton.setPreferredSize(new java.awt.Dimension(120, 30));
        addCustomerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerButtonActionPerformed(evt);
            }
        });
        addCustomerPanel.add(addCustomerButton);

        jPanel3.add(addCustomerPanel);

        jPanel1.add(jPanel3);

        searchCustomerTxtFld.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchCustomerTxtFld.setPreferredSize(new java.awt.Dimension(450, 30));
        jPanel4.add(searchCustomerTxtFld);

        searchButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchButton.setText("Search");
        searchButton.setMaximumSize(new java.awt.Dimension(75, 30));
        searchButton.setMinimumSize(new java.awt.Dimension(75, 30));
        searchButton.setPreferredSize(new java.awt.Dimension(75, 30));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        jPanel4.add(searchButton);

        jPanel1.add(jPanel4);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        // customerTableList.setModel(new javax.swing.table.DefaultTableModel(
        //         new Object[][] {
        //                 { null, null, null, null, null },
        //                 { null, null, null, null, null },
        //                 { null, null, null, null, null },
        //                 { null, null, null, null, null }
        //         },
        //         new String[] {
        //                 "ID", "Name", "Email", "Phone number", "Status"
        //         }) {
        //     Class[] types = new Class[] {
        //             java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class,
        //             java.lang.String.class
        //     };
        //     boolean[] canEdit = new boolean[] {
        //             false, false, false, false, false
        //     };

        //     public Class getColumnClass(int columnIndex) {
        //         return types[columnIndex];
        //     }

        //     public boolean isCellEditable(int rowIndex, int columnIndex) {
        //         return canEdit[columnIndex];
        //     }
        // });
        customerTableList.getTableHeader().setReorderingAllowed(false);
        customerTableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTableListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(customerTableList);
        // if (customerTableList.getColumnModel().getColumnCount() > 0) {
        //     customerTableList.getColumnModel().getColumn(0).setResizable(false);
        //     customerTableList.getColumnModel().getColumn(1).setResizable(false);
        //     customerTableList.getColumnModel().getColumn(2).setResizable(false);
        //     customerTableList.getColumnModel().getColumn(3).setResizable(false);
        //     customerTableList.getColumnModel().getColumn(4).setResizable(false);
        // }

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel2);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void customerTableListMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private javax.swing.JButton searchButton;
    private javax.swing.JButton addCustomerButton;
    private javax.swing.JTable customerTableList;

    private javax.swing.JPanel addCustomerPanel;
    private javax.swing.JLabel customerListLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField searchCustomerTxtFld;
}
