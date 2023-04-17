package com.bookstore.gui.form.salesman.view;

import javax.swing.JPanel;
import com.bookstore.bus.OrderBUS;
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

    jPanel1 = new javax.swing.JPanel();
    jTextField1 = new javax.swing.JTextField();
    jButton1 = new javax.swing.JButton();
    jPanel4 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    orderTableList = new javax.swing.JTable();

    setLayout(new java.awt.BorderLayout());

    jTextField1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1ActionPerformed(evt);
      }
    });

    jButton1.setText("Search");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 248,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 99,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(273, Short.MAX_VALUE)));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE)));

    add(jPanel1, java.awt.BorderLayout.PAGE_START);

    jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

    orderTableList.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null }
        },
        new String[] {
            "Cart ID", "Employee ID", "Total Price", "Status"
        }));
    jScrollPane1.setViewportView(orderTableList);

    jPanel4.add(jScrollPane1);

    add(jPanel4, java.awt.BorderLayout.CENTER);
  }

  private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private javax.swing.JButton jButton1;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable orderTableList;
  private javax.swing.JTextField jTextField1;
}
