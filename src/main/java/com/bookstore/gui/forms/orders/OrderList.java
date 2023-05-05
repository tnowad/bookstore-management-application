package com.bookstore.gui.forms.orders;

import com.bookstore.bus.OrderBUS;
import com.bookstore.enums.OrderStatus;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.dialogs.Dialog;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.models.OrderModel;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class OrderList extends JPanel {

  private JPanel headerPanel;
  private Label orderLabel;
  // private JPanel orderListPanel;
  private JTable orderTableList;
  // private JScrollPane scrollPaneChild;
  private JScrollPane scrollPaneParent;
  private Button searchButton;
  private TextField searchTextField;

  private static OrderList instance;

  OrderBUS orderBus = OrderBUS.getInstance();
  java.util.List<OrderModel> orderList = orderBus.getAllModels();

  private OrderList() {
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
      .addListSelectionListener(
        new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent event) {
            int selectedRowIndex = orderTableList.getSelectedRow();
            if (selectedRowIndex != -1) {
              int customerId = Integer.parseInt(
                orderTableList.getValueAt(selectedRowIndex, 2).toString()
              );
              new Dialog(new OrderDetail(customerId));
              listOrder();
            } else {
              System.out.println("Don't know how to handle this order");
            }
          }
        }
      );
  }

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
      orderTableList.setModel(model);
    }
    // scrollPaneChild.setViewportView(orderTableList);

    // orderListPanel.add(scrollPaneChild, BorderLayout.CENTER);

    scrollPaneParent.setViewportView(orderTableList);

    add(scrollPaneParent, BorderLayout.CENTER);
  }

  private void initComponents() {
    headerPanel = new JPanel();
    orderLabel = new Label("Order");
    searchTextField = new TextField();
    searchButton = new Button("Search");
    scrollPaneParent = new JScrollPane();
    // orderListPanel = new JPanel();
    // scrollPaneChild = new JScrollPane();
    orderTableList = new JTable();

    setMinimumSize(new Dimension(1180, 620));
    setLayout(new BorderLayout());

    headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

    orderLabel.setPreferredSize(new Dimension(300, 50));
    headerPanel.add(orderLabel);

    searchTextField.setPreferredSize(new Dimension(500, 30));

    headerPanel.add(searchTextField);

    headerPanel.add(searchButton);

    add(headerPanel, BorderLayout.PAGE_START);
    // orderListPanel.setLayout(new BorderLayout());
    // orderListPanel.add(scrollPaneChild, BorderLayout.CENTER);

    // scrollPaneParent.setViewportView(orderListPanel);

    // add(scrollPaneParent, BorderLayout.CENTER);
  }
}
