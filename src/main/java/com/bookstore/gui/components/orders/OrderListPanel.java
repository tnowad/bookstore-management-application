/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bookstore.gui.components.orders;

import com.bookstore.bus.OrderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.OrderStatus;
import com.bookstore.gui.forms.orders.OrderDetail;
import com.bookstore.models.OrderModel;
import com.bookstore.models.UserModel;

import java.awt.Dialog;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yanti
 */
public class OrderListPanel extends javax.swing.JPanel {

  private static OrderListPanel instance;
  java.util.List<OrderModel> orderList = OrderBUS.getInstance().getAllModels();

  /**
   * Creates new form OrderPanel
   */
  public OrderListPanel() {
    initComponents();
    addTable();
    handleEvent();
  }

  public static OrderListPanel getInstance() {
    if (instance == null) {
      instance = new OrderListPanel();
    }
    return instance;
  }

  private void handleEvent() {
    table
      .getSelectionModel()
      .addListSelectionListener(
        new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent event) {
            int selectedRowIndex = table.getSelectedRow();
            if (selectedRowIndex != -1) {
              int customerId = Integer.parseInt(
                table.getValueAt(selectedRowIndex, 2).toString()
              );
              new Dialog(new OrderDetail(customerId));
              addTable();
            } else {
              System.out.println("Don't know how to handle this order");
            }
          }
        }
      );
  }

  private void initComponents() {

    titlePanel = new javax.swing.JLabel();
    contend = new javax.swing.JPanel();
    buttonPanel = new javax.swing.JPanel();
    jPanel3 = new javax.swing.JPanel();
    ButtonExport = new javax.swing.JButton();
    ButtonImport = new javax.swing.JButton();
    ButtonCreate = new javax.swing.JButton();
    ButtonDelete = new javax.swing.JButton();
    tableContend = new javax.swing.JPanel();
    scrollPane = new javax.swing.JScrollPane();
    table = new javax.swing.JTable();

    setPreferredSize(new java.awt.Dimension(702, 444));
    setLayout(new java.awt.BorderLayout());

    titlePanel.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
    titlePanel.setForeground(new java.awt.Color(255, 0, 51));
    titlePanel.setText("List Order");
    add(titlePanel, java.awt.BorderLayout.NORTH);

    contend.setLayout(new java.awt.BorderLayout());

    buttonPanel.setLayout(new java.awt.BorderLayout());

    jPanel3.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

    ButtonExport.setText("Xuất File");
    ButtonExport.setToolTipText("");
    jPanel3.add(ButtonExport);

    ButtonImport.setText("Nhập File");
    jPanel3.add(ButtonImport);

    ButtonCreate.setText("Create");
    jPanel3.add(ButtonCreate);

    ButtonDelete.setText("Delete");
    jPanel3.add(ButtonDelete);

    buttonPanel.add(jPanel3, java.awt.BorderLayout.LINE_END);

    contend.add(buttonPanel, java.awt.BorderLayout.PAGE_START);

    tableContend.setLayout(new java.awt.BorderLayout());


    tableContend.add(scrollPane, java.awt.BorderLayout.CENTER);

    contend.add(tableContend, java.awt.BorderLayout.CENTER);

    add(contend, java.awt.BorderLayout.CENTER);
  } // </editor-fold>//GEN-END:initComponents

  public void addTable() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Id");
    model.addColumn("Cart ID");
    model.addColumn("Customer ID");
    model.addColumn("Employee ID");
    model.addColumn("Total price");
    model.addColumn("Paid");
    model.addColumn("Status");
    for (OrderModel orderModel : orderList) {
      if (orderModel.getStatus() == OrderStatus.PENDING) {
        model.addRow(
          new Object[] {
            orderModel.getId(),
            orderModel.getCartId(),
            orderModel.getCustomerId(),
            orderModel.getEmployeeId(),
            orderModel.getTotal(),
            orderModel.getPaid(),
            orderModel.getStatus(),
          }
        );
      }
      table.setModel(model);
    }
    scrollPane.setViewportView(table);
  }

  public void receiveValue(String value) {
    int Serial = 0;
    List<UserModel> customerList = UserBUS
      .getInstance()
      .searchModel(value, new String[] { "name" });
    table.removeAll();
    table.setLayout(new GridLayout(0, 1, 10, 10));
    for (UserModel customer : customerList) {
      List<OrderModel> listOrder = OrderBUS
        .getInstance()
        .searchModel(
          String.valueOf(customer.getId()),
          new String[] { "customer_id" }
        );
      for (OrderModel order : listOrder) {
        if (!order.getStatus().toString().equals("BANNED")) {
          ItemOrderPanel orderForm = new ItemOrderPanel(Serial, order);
          table.add(orderForm);
          Serial = Serial + 1;
        }
      }
    }
    table.revalidate();
    table.repaint();
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton ButtonCreate;
  private javax.swing.JButton ButtonDelete;
  private javax.swing.JButton ButtonExport;
  private javax.swing.JButton ButtonImport;
  private javax.swing.JPanel buttonPanel;
  private javax.swing.JPanel contend;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JScrollPane scrollPane;
  private javax.swing.JTable table;
  private javax.swing.JPanel tableContend;
  private javax.swing.JLabel titlePanel;
  // End of variables declaration//GEN-END:variables
}
