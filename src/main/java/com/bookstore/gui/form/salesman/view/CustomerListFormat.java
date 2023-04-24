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

public class CustomerListFormat extends JPanel {
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

    private void initComponents() {

        jPanel1 = new JPanel();
        jPanel3 = new JPanel();
        customerListLabel = new JLabel();
        addCustomerPanel = new JPanel();
        addCustomerButton = new Button("Add Customer");
        jPanel4 = new JPanel();
        searchCustomerTxtFld = new JTextField();
        searchButton = new Button("Search");
        jScrollPane1 = new JScrollPane();
        jPanel2 = new JPanel();
        jScrollPane2 = new JScrollPane();
        customerTableList = new JTable();

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

        searchButton.setMaximumSize(new java.awt.Dimension(75, 30));
        searchButton.setMinimumSize(new java.awt.Dimension(75, 30));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        jPanel4.add(searchButton);

        jPanel1.add(jPanel4);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);

        jPanel2.setLayout(new java.awt.BorderLayout());

        customerTableList.getTableHeader().setReorderingAllowed(false);
        customerTableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerTableListMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(customerTableList);

        jPanel2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        jScrollPane1.setViewportView(jPanel2);

        add(jScrollPane1, java.awt.BorderLayout.CENTER);
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private void customerTableListMouseClicked(java.awt.event.MouseEvent evt) {

    }

    private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {

    }

    private Button searchButton;
    private Button addCustomerButton;
    private JTable customerTableList;

    private JPanel addCustomerPanel;
    private JLabel customerListLabel;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextField searchCustomerTxtFld;
}
