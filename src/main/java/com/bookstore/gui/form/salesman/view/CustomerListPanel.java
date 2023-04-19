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

public class CustomerListPanel extends JPanel {

        private JPanel CustomerListUtility;
        private Button addCustomerBtn;
        private JTable customerTableList;
        private Button exportCustomerBtn;
        private Button importCustomerListBtn;
        private Label titleLabel;
        private JPanel container;
        private JScrollPane jScrollPane1;
        private JScrollPane jScrollPane2;
        private JSeparator jSeparator1;
        private Button searchBtn;
        private JTextField searchCustomerTxtFld;

        UserBUS userBus = UserBUS.getInstance();
        List<UserModel> customersList = userBus.getAllModels();

        public CustomerListPanel() {
                initComponents();
                listCustomer();
                search();
        }

        private void search() {
                searchBtn.addActionListener(new ActionListener() {
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

                container = new JPanel();
                jSeparator1 = new JSeparator();
                CustomerListUtility = new JPanel();
                exportCustomerBtn = new Button("Export");
                importCustomerListBtn = new Button("Import");
                searchBtn = new Button("Search");
                searchCustomerTxtFld = new JTextField();
                titleLabel = new Label("Customer List");
                addCustomerBtn = new Button("Add Customer");
                jScrollPane1 = new JScrollPane();
                jScrollPane2 = new JScrollPane();
                customerTableList = new JTable();

                exportCustomerBtn.setFont(new ThemeFont().getSmallFont());
                exportCustomerBtn.setIcon(
                                new ImageIcon(getClass().getResource("../../../../../../resources/images/export.png")));

                importCustomerListBtn.setFont(new ThemeFont().getSmallFont());
                importCustomerListBtn
                                .setIcon(new ImageIcon(
                                                getClass().getResource(
                                                                "../../../../../../resources/images/import.png")));
                importCustomerListBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                importCustomerListBtnActionPerformed(evt);
                        }
                });

                searchBtn.setIcon(
                                new ImageIcon(getClass()
                                                .getResource("../../../../../../resources/images/search.png")));

                searchCustomerTxtFld.setFont(new ThemeFont().getSmallFont());

                addCustomerBtn.setFont(new ThemeFont().getSmallFont());
                addCustomerBtn.setIcon(
                                new ImageIcon(
                                                getClass().getResource(
                                                                "../../../../../../resources/images/addCustomer.png")));
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
                                                                                .addComponent(titleLabel)
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
                                                                .addContainerGap()));
                CustomerListUtilityLayout.setVerticalGroup(
                                CustomerListUtilityLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(CustomerListUtilityLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(CustomerListUtilityLayout
                                                                                .createParallelGroup(
                                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(titleLabel)
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
                                                                .addContainerGap()));

                customerTableList.getTableHeader().setReorderingAllowed(false);
                jScrollPane2.setViewportView(customerTableList);

                GroupLayout containerLayout = new GroupLayout(
                                container);
                container.setLayout(containerLayout);
                containerLayout.setHorizontalGroup(
                                containerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 903,
                                                                Short.MAX_VALUE));
                containerLayout.setVerticalGroup(
                                containerLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addComponent(jScrollPane2, GroupLayout.DEFAULT_SIZE, 440,
                                                                Short.MAX_VALUE));

                jScrollPane1.setViewportView(container);

                GroupLayout layout = new GroupLayout(
                                this);
                this.setLayout(layout);
                layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup().addContainerGap()
                                                .addComponent(CustomerListUtility, GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addContainerGap())
                                .addComponent(jScrollPane1)
                                .addComponent(jSeparator1, GroupLayout.Alignment.TRAILING));
                layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addGroup(layout
                                                .createSequentialGroup()
                                                .addComponent(CustomerListUtility,
                                                                GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE,
                                                                GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jSeparator1, GroupLayout.PREFERRED_SIZE, 10,
                                                                GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1)));
        }

        private void addCustomerBtnActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void importCustomerListBtnActionPerformed(java.awt.event.ActionEvent evt) {
                // TODO add your handling code here:
        }

        public static void main(String[] args) {
                JFrame jFrame = new JFrame();
                jFrame.add(new CustomerListPanel());
                jFrame.setVisible(true);
        }
}
