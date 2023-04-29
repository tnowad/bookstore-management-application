package com.bookstore.gui.form.salesman.view;

import javax.swing.*;
import javax.swing.JTextField;

import com.bookstore.bus.OrderBUS;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.label.Label;

import java.util.List;
import javax.swing.table.DefaultTableModel;
import com.bookstore.models.OrderModel;

public class PendingOrderPanel extends JPanel {

  OrderBUS orderBus = OrderBUS.getInstance();
  List<OrderModel> orderList = orderBus.getAllModels();

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
    orderTableList.getTableHeader().setReorderingAllowed(false);
    jScrollPane1.setViewportView(orderTableList);
  }

  public PendingOrderPanel() {
    initComponents();
    listOrder();
  }

  private void initComponents() {

    pendingOrderTitle = new Label("Pending Order List");
    jPanel1 = new JPanel();
    jTextField1 = new JTextField();
    searchButton = new Button("Search");
    jPanel4 = new JPanel();
    jScrollPane1 = new JScrollPane();
    orderTableList = new JTable();

    setLayout(new java.awt.BorderLayout());

    jTextField1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1ActionPerformed(evt);
      }
    });

    searchButton.setText("Search");

    GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pendingOrderTitle)
                .addGap(183, 183, 183)
                .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 248,
                    GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 99,
                    GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE)));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pendingOrderTitle)
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 40,
                        GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 40,
                        GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE)));

    add(jPanel1, java.awt.BorderLayout.PAGE_START);

    jPanel4.setLayout(new BoxLayout(jPanel4, BoxLayout.LINE_AXIS));

    jScrollPane1.setViewportView(orderTableList);
    jPanel4.add(jScrollPane1);

    add(jPanel4, java.awt.BorderLayout.CENTER);
  }

  private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private Label pendingOrderTitle;
  private Button searchButton;
  private JPanel jPanel1;
  private JPanel jPanel4;
  private JScrollPane jScrollPane1;
  private JTable orderTableList;
  private JTextField jTextField1;
}
