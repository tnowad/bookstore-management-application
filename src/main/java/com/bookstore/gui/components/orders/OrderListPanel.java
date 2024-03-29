package com.bookstore.gui.components.orders;

import com.bookstore.bus.OrderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.OrderStatus;
import com.bookstore.models.OrderModel;
import com.bookstore.models.UserModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class OrderListPanel extends JPanel {

  private static OrderListPanel instance;
  java.util.List<OrderModel> orderList = OrderBUS.getInstance().getAllModels();

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
              // int customerId = Integer.parseInt(
              //   table.getValueAt(selectedRowIndex, 2).toString()
              // );
              // MainPanel
              // .getInstance()
              // .showFormStack(new OrderDetail(customerId));
              addTable();
            }
            //  else {
            //   JOptionPane.showMessageDialog(
            //     null,
            //     "No row selected!",
            //     "Warning",
            //     JOptionPane.WARNING_MESSAGE
            //   );
            // }
          }
        }
      );
  }

  private void initComponents() {
    titlePanel = new JLabel();
    contentPanel = new JPanel();
    actionPanel = new JPanel();
    jPanel3 = new JPanel();
    exportButton = new JButton();
    importButton = new JButton();
    createButton = new JButton();
    deleteButton = new JButton();
    tableContent = new JPanel();
    scrollPane = new JScrollPane();
    table = new JTable();

    setPreferredSize(new Dimension(702, 444));
    setLayout(new BorderLayout());

    titlePanel.setFont(new Font("Segoe UI", 3, 18));
    titlePanel.setForeground(new Color(255, 0, 51));
    titlePanel.setText("List Order");
    add(titlePanel, BorderLayout.NORTH);

    contentPanel.setLayout(new BorderLayout());

    actionPanel.setLayout(new BorderLayout());

    jPanel3.setLayout(new GridLayout(1, 0, 5, 0));

    exportButton.setText("Export");
    exportButton.setToolTipText("");
    jPanel3.add(exportButton);

    importButton.setText("Import");
    jPanel3.add(importButton);

    createButton.setText("Create");
    jPanel3.add(createButton);

    deleteButton.setText("Delete");
    jPanel3.add(deleteButton);

    actionPanel.add(jPanel3, BorderLayout.LINE_END);

    contentPanel.add(actionPanel, BorderLayout.PAGE_START);

    tableContent.setLayout(new BorderLayout());

    tableContent.add(scrollPane, BorderLayout.CENTER);

    contentPanel.add(tableContent, BorderLayout.CENTER);

    add(contentPanel, BorderLayout.CENTER);
  }

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
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);
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

  private JButton createButton;
  private JButton deleteButton;
  private JButton exportButton;
  private JButton importButton;
  private JPanel actionPanel;
  private JPanel contentPanel;
  private JPanel jPanel3;
  private JScrollPane scrollPane;
  private JTable table;
  private JPanel tableContent;
  private JLabel titlePanel;
}
