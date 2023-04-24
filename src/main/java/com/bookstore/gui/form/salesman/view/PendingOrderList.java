package com.bookstore.gui.form.salesman.view;

import javax.swing.*;
import javax.swing.JTextField;

import com.bookstore.bus.OrderBUS;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.button.Label;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.bookstore.models.OrderModel;

public class PendingOrderList extends JPanel {

    public PendingOrderList() {
        initComponents();
    }

    private void initComponents() {

        headerPanel = new JPanel();
        headerTopPanel = new JPanel();
        customerNameLabel = new JLabel();
        customerNameTextField = new JTextField();
        headerBottomPanel = new JPanel();
        phoneNumberLabel = new JLabel();
        phoneNumberTextField = new JTextField();
        emailLabel = new JLabel();
        emailTextField = new JTextField();
        scrollPaneParrent = new JScrollPane();
        orderTableList = new JPanel();
        scrollPaneChild = new JScrollPane();
        jTable1 = new JTable();
        footerPanel = new JPanel();
        topFooterPanel = new JPanel();
        totalCostLabel = new JLabel();
        totalCostMoneyTextfield = new JTextField();
        bottomFooterPanel = new JPanel();
        accpetButton = new JButton();
        rejectButton = new JButton();

        setMinimumSize(new java.awt.Dimension(1180, 620));
        setLayout(new java.awt.BorderLayout());

        headerPanel.setLayout(new java.awt.GridLayout(2, 1));

        headerTopPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        customerNameLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        customerNameLabel.setText("Customer name:");
        customerNameLabel.setPreferredSize(new java.awt.Dimension(150, 30));
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

        phoneNumberLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        phoneNumberLabel.setText("Phone number:");
        phoneNumberLabel.setMinimumSize(new java.awt.Dimension(82, 30));
        phoneNumberLabel.setPreferredSize(new java.awt.Dimension(150, 30));
        headerBottomPanel.add(phoneNumberLabel);

        phoneNumberTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        phoneNumberTextField.setPreferredSize(new java.awt.Dimension(150, 30));
        phoneNumberTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                phoneNumberTextFieldActionPerformed(evt);
            }
        });
        headerBottomPanel.add(phoneNumberTextField);

        emailLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        emailLabel.setText("Email:");
        emailLabel.setPreferredSize(new java.awt.Dimension(50, 30));
        headerBottomPanel.add(emailLabel);

        emailTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        emailTextField.setPreferredSize(new java.awt.Dimension(350, 30));
        headerBottomPanel.add(emailTextField);

        headerPanel.add(headerBottomPanel);

        add(headerPanel, java.awt.BorderLayout.PAGE_START);

        orderTableList.setLayout(new java.awt.BorderLayout());

        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        scrollPaneChild.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        orderTableList.add(scrollPaneChild, java.awt.BorderLayout.CENTER);

        scrollPaneParrent.setViewportView(orderTableList);

        add(scrollPaneParrent, java.awt.BorderLayout.CENTER);

        footerPanel.setLayout(new java.awt.GridLayout(2, 1));

        topFooterPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        totalCostLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        totalCostLabel.setText("Total cost:");
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

        accpetButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        accpetButton.setText("Accept");
        accpetButton.setPreferredSize(new java.awt.Dimension(85, 30));
        accpetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accpetButtonActionPerformed(evt);
            }
        });
        bottomFooterPanel.add(accpetButton);

        rejectButton.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        rejectButton.setText("Reject");
        rejectButton.setPreferredSize(new java.awt.Dimension(81, 30));
        rejectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rejectButtonActionPerformed(evt);
            }
        });
        bottomFooterPanel.add(rejectButton);

        footerPanel.add(bottomFooterPanel);

        add(footerPanel, java.awt.BorderLayout.PAGE_END);
    }// </editor-fold>

    private void customerNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void phoneNumberTextFieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void totalCostMoneyTextfieldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    private void accpetButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void rejectButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private JButton accpetButton;
    private JPanel bottomFooterPanel;
    private JLabel customerNameLabel;
    private JTextField customerNameTextField;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JPanel footerPanel;
    private JPanel headerBottomPanel;
    private JPanel headerPanel;
    private JPanel headerTopPanel;
    private JTable jTable1;
    private JPanel orderTableList;
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberTextField;
    private JButton rejectButton;
    private JScrollPane scrollPaneChild;
    private JScrollPane scrollPaneParrent;
    private JPanel topFooterPanel;
    private JLabel totalCostLabel;
    private JTextField totalCostMoneyTextfield;
}
