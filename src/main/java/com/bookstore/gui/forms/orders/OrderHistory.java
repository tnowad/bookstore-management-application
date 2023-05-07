package com.bookstore.gui.forms.orders;

import com.bookstore.bus.OrderBUS;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.interfaces.ISearchable;
import com.bookstore.models.OrderModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrderHistory extends JPanel implements ISearchable {

  private JPanel headerPanel;
  private Label orderLabel;
  private JTable orderTable;
  private JScrollPane orderTableScrollPane;

  OrderBUS orderBus = OrderBUS.getInstance();
  java.util.List<OrderModel> orderList = orderBus.getAllModels();
  UserModel userModel = Authentication.getCurrentUser();

  public OrderHistory() {
    initComponents();
    listOrder();
    handleEvent();
  }

  private void handleEvent() {
    orderTable
      .getSelectionModel()
      .addListSelectionListener(event -> {
        int selectedRowIndex = orderTable.getSelectedRow();
        if (selectedRowIndex != -1) {
          int orderId = Integer.parseInt(
            orderTable.getValueAt(selectedRowIndex, 1).toString()
          );
          MainPanel
            .getInstance()
            .showFormStack(new OrderDetail(userModel.getId(), orderId));
          OrderBUS.getInstance().refreshData();
          listOrder();
        }
      });
  }

  private void listOrder() {
    DefaultTableModel model = new DefaultTableModel();
    int stt = 1;
    model.addColumn("STT");
    model.addColumn("ID");
    model.addColumn("purchase date");
    model.addColumn("Total price");
    model.addColumn("status");
    for (OrderModel orderModel : orderList) {
      if (userModel.getId() == orderModel.getCustomerId()) {
        model.addRow(
          new Object[] {
            stt++,
            orderModel.getId(),
            orderModel.getCreatedAt(),
            orderModel.getTotal(),
            orderModel.getStatus(),
          }
        );
      }
      orderTable.setModel(model);
    }

    orderTableScrollPane.setViewportView(orderTable);

    add(orderTableScrollPane, BorderLayout.CENTER);
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    headerPanel.add(orderLabel);
    add(headerPanel, BorderLayout.NORTH);

    orderTableScrollPane = new JScrollPane();
    orderTable = new JTable();
    orderLabel = new Label("Order History");
    orderTableScrollPane.setViewportView(orderTable);

    add(orderTableScrollPane, BorderLayout.CENTER);
  }

  @Override
  public void search(String keyword) {
    // Write search method in here
  }
}
