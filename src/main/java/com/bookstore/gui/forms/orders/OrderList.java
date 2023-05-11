package com.bookstore.gui.forms.orders;

import com.bookstore.bus.OrderBUS;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.interfaces.ISearchable;
import com.bookstore.models.OrderModel;
import java.awt.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class OrderList extends JPanel implements ISearchable {

  private JTable orderTableList;

  private static OrderList instance;

  OrderBUS orderBus = OrderBUS.getInstance();
  List<OrderModel> orderList = orderBus.getAllModels();

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
          int orderId = Integer.parseInt(
            orderTableList.getValueAt(selectedRowIndex, 0).toString()
          );
          int customerId = Integer.parseInt(
            orderTableList.getValueAt(selectedRowIndex, 2).toString()
          );
          if (customerId != 0) {
            MainPanel
              .getInstance()
              .showFormStack(
                new OrderDetail(OrderBUS.getInstance().getModelById(orderId))
              );
            OrderBUS.getInstance().refreshData();
            listOrder();
          }
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
      orderTableList.setModel(model);
    }
    orderTableList.getTableHeader().setReorderingAllowed(false);
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(JLabel.CENTER);
    for (int i = 0; i < orderTableList.getColumnCount(); i++) {
      orderTableList
        .getColumnModel()
        .getColumn(i)
        .setCellRenderer(centerRenderer);
    }

    add(new JScrollPane(orderTableList), BorderLayout.CENTER);
  }

  private void initComponents() {
    JPanel headerPanel = new JPanel();
    Label orderLabel = new Label("Order");
    orderTableList = new JTable();

    setMinimumSize(new Dimension(1180, 620));
    setLayout(new BorderLayout());

    headerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5));
    orderLabel.setPreferredSize(new Dimension(50, 50));
    headerPanel.add(orderLabel);

    add(headerPanel, BorderLayout.PAGE_START);
  }

  @Override
  public void search(String keyword) {}
}
