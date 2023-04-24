package com.bookstore.gui.form.salesman.view;

import javax.swing.*;
import javax.swing.JTextField;

public class PendingOrderList extends JPanel {
    OrderBUS orderBus = OrderBUS.getInstance();
    List<OrderModel> orderList = orderBus.getAllModels();

    public PendingOrderList() {
        initComponents();
        listOrder();
    }

    private void initComponents() {

        headerPanel = new JPanel();
        headerTopPanel = new JPanel();
        customerNameLabel = new Label("");
        customerNameTextField = new JTextField();
        headerBottomPanel = new JPanel();
        phoneNumberLabel = new Label("Customer name");
        phoneNumberTextField = new JTextField();
        emailLabel = new Label("Email");
        emailTextField = new JTextField();
        scrollPaneParrent = new JScrollPane();
        orderListPanel = new JPanel();
        scrollPaneChild = new JScrollPane();
        orderTableList = new JTable();
        footerPanel = new JPanel();
        topFooterPanel = new JPanel();
        totalCostLabel = new Label("Total cost");
        totalCostMoneyTextfield = new JTextField();
        bottomFooterPanel = new JPanel();
        accpetButton = new Button("Accpet");
        rejectButton = new Button("Reject");

        setMinimumSize(new java.awt.Dimension(1180, 620));
        setLayout(new java.awt.BorderLayout());

        headerPanel.setLayout(new java.awt.GridLayout(2, 1));

        headerTopPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        headerTopPanel.add(customerNameLabel);

        customerNameTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        customerNameTextField.setPreferredSize(new java.awt.Dimension(500, 30));
        customerNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerNameTextFieldActionPerformed(evt);
            }
        });
        headerTopPanel.add(customerNameTextField);

        headerPanel.add(headerTopPanel);

        headerBottomPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        headerBottomPanel.add(phoneNumberLabel);

        phoneNumberTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        phoneNumberTextField.setPreferredSize(new java.awt.Dimension(150, 30));
        phoneNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberTextFieldActionPerformed(evt);
            }
        });
        headerBottomPanel.add(phoneNumberTextField);

        headerBottomPanel.add(emailLabel);

        emailTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        emailTextField.setPreferredSize(new java.awt.Dimension(350, 30));
        headerBottomPanel.add(emailTextField);

        headerPanel.add(headerBottomPanel);

        add(headerPanel, java.awt.BorderLayout.PAGE_START);

        orderListPanel.setLayout(new java.awt.BorderLayout());

        orderTableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                orderTableListMouseClicked(evt);
            }
        });

        footerPanel.setLayout(new java.awt.GridLayout(2, 1));

        topFooterPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        topFooterPanel.add(totalCostLabel);

        totalCostMoneyTextfield.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        totalCostMoneyTextfield.setPreferredSize(new java.awt.Dimension(200, 30));
        totalCostMoneyTextfield.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalCostMoneyTextfieldActionPerformed(evt);
            }
        });
        topFooterPanel.add(totalCostMoneyTextfield);

        footerPanel.add(topFooterPanel);

        bottomFooterPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        accpetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accpetButtonActionPerformed(evt);
            }
        });
        bottomFooterPanel.add(accpetButton);

        rejectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectButtonActionPerformed(evt);
            }
        });
        bottomFooterPanel.add(rejectButton);

        footerPanel.add(bottomFooterPanel);

        add(footerPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>

    private void listOrder() {
        // "ID", "Provider ID", "Employee ID", "Price", "Status"
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Cart ID");
        model.addColumn("Customer ID");
        model.addColumn("Employee ID");
        model.addColumn("Total price");
        model.addColumn("Paid");
        model.addColumn("Status");
        for (OrderModel orderModel : orderList) {
            model.addRow(new Object[] {
                    orderModel.getId(), orderModel.getCartId(), orderModel.getCustomerId(), orderModel.getEmployeeId(),
                    orderModel.getTotal(), orderModel.getPaid(), orderModel.getStatus()
            });
            orderTableList.setModel(model);
        }
        scrollPaneChild.setViewportView(orderTableList);

        orderListPanel.add(scrollPaneChild, java.awt.BorderLayout.CENTER);

        scrollPaneParrent.setViewportView(orderListPanel);

        add(scrollPaneParrent, java.awt.BorderLayout.CENTER);
    }

    private void customerNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void phoneNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void totalCostMoneyTextfieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void orderTableListMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void accpetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void rejectButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
    private Button accpetButton;
    private JPanel bottomFooterPanel;
    private Label customerNameLabel;
    private JTextField customerNameTextField;
    private Label emailLabel;
    private JTextField emailTextField;
    private JPanel footerPanel;
    private JPanel headerBottomPanel;
    private JPanel headerPanel;
    private JPanel headerTopPanel;
    private JPanel orderListPanel;
    private JTable orderTableList;
    private Label phoneNumberLabel;
    private JTextField phoneNumberTextField;
    private Button rejectButton;
    private JScrollPane scrollPaneChild;
    private JScrollPane scrollPaneParrent;
    private JPanel topFooterPanel;
    private Label totalCostLabel;
    private JTextField totalCostMoneyTextfield;
    // End of variables declaration
}
