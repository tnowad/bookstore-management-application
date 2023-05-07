package com.bookstore.gui.forms.orders;

import com.bookstore.bus.OrderBUS;
import com.bookstore.enums.OrderStatus;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.OrderModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class OrderHistory extends JPanel{
  private JPanel headerPanel;
  private Label orderLabel;
  private JTable orderHistoryList;
  private JScrollPane scrollPaneParent;
  private Button searchButton;
  private TextField searchTextField;

  private static OrderList instance;

  OrderBUS orderBus = OrderBUS.getInstance();
  java.util.List<OrderModel> orderList = orderBus.getAllModels();
  UserModel userModel = Authentication.getCurrentUser();

  public OrderHistory() {
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
   orderHistoryList
      .getSelectionModel()
      .addListSelectionListener( event -> {
        int selectedRowIndex = orderHistoryList.getSelectedRow();
        if (selectedRowIndex != -1) {
          int orderId = Integer.parseInt(
            orderHistoryList.getValueAt(selectedRowIndex, 1).toString()
          );
          MainPanel
              .getInstance()
              .showFormStack(new OrderDetail(userModel.getId(), orderId));
            OrderBUS.getInstance().refreshData();
            listOrder();
        }
      }
      );
  }

  private void listOrder() {

    DefaultTableModel model = new DefaultTableModel();
    int stt = 1;
    model.addColumn("STT");
    model.addColumn("ID");
    model.addColumn("purchase date");
    model.addColumn("Total price");
    for (OrderModel orderModel : orderList) {
      if (orderModel.getStatus() == OrderStatus.SOLVED && userModel.getId() == orderModel.getCustomerId()) {
        model.addRow(
          new Object[] {
            stt++,
            orderModel.getId(),
            orderModel.getCreatedAt(),
            orderModel.getTotal(),
          }
        );
      }
      orderHistoryList.setModel(model);
    }

    scrollPaneParent.setViewportView(orderHistoryList);

    add(scrollPaneParent, BorderLayout.CENTER);
  }

  private void initComponents() {
    headerPanel = new JPanel();
    orderLabel = new Label("Order");
    searchTextField = new TextField();
    searchButton = new Button("Search");
    scrollPaneParent = new JScrollPane();
    orderHistoryList = new JTable();

    setMinimumSize(new Dimension(1180, 620));
    setLayout(new BorderLayout());

    headerPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));

    orderLabel.setPreferredSize(new Dimension(300, 50));
    headerPanel.add(orderLabel);

    searchTextField.setPreferredSize(new Dimension(500, 30));

    headerPanel.add(searchTextField);

    headerPanel.add(searchButton);

    add(headerPanel, BorderLayout.PAGE_START);
  }
}


