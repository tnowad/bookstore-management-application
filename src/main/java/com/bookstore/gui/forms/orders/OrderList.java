package com.bookstore.gui.forms.orders;

import com.bookstore.bus.OrderBUS;
import com.bookstore.enums.OrderStatus;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.OrderModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrderList extends JPanel {

  private JTable orderTableList;

  private static OrderList instance;

  OrderBUS orderBus = OrderBUS.getInstance();
  java.util.List<OrderModel> orderList = orderBus.getAllModels();

  public OrderList() {
    initComponents();
    listOrder();
    handleEvent();
  }

  public static OrderList getInstance() {
    if (instance == null) {
      instance = new OrderList();
    }
    return instance;
  }

  private void handleEvent() {
    orderTableList
      .getSelectionModel()
      .addListSelectionListener(event -> {
        int selectedRowIndex = orderTableList.getSelectedRow();
        if (selectedRowIndex != -1) {
          int customerId = Integer.parseInt(
            orderTableList.getValueAt(selectedRowIndex, 2).toString()
          );
          MainPanel.getInstance().showFormStack(new OrderDetail(customerId));
          listOrder();
        }
      });
  }

  private void listOrder() {
    DefaultTableModel model = new DefaultTableModel(
      new String[] {
        "Id",
        "Cart ID",
        "Customer ID",
        "Employee ID",
        "Total price",
        "Paid",
        "Status",
      },
      0
    );

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
            orderModel.getStatus().toString(),
          }
        );
      }
    }

    orderTableList.setModel(model);

    add(new JScrollPane(orderTableList), BorderLayout.CENTER);
  }

  private void initComponents() {
    JPanel headerPanel = new JPanel();
    Label orderLabel = new Label("Order");
    TextField searchTextField = new TextField();
    Button searchButton = new Button("Search");
    JTable orderTableList = new JTable();

    setMinimumSize(new Dimension(1180, 620));
    setLayout(new BorderLayout());

    headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
    orderLabel.setPreferredSize(new Dimension(300, 50));
    searchTextField.setPreferredSize(new Dimension(500, 30));
    headerPanel.add(orderLabel);
    headerPanel.add(searchTextField);
    headerPanel.add(searchButton);

    add(headerPanel, BorderLayout.PAGE_START);
    add(new JScrollPane(orderTableList), BorderLayout.CENTER);
  }
}
