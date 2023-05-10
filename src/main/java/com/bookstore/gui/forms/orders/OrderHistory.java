package com.bookstore.gui.forms.orders;

import com.bookstore.bus.OrderBUS;
import com.bookstore.enums.UserRole;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.interfaces.ISearchable;
import com.bookstore.models.OrderModel;
import com.bookstore.models.UserModel;
import com.bookstore.models.tables.OrderTableModel;
import com.bookstore.services.Authentication;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class OrderHistory extends JPanel implements ISearchable {

  private JPanel headerPanel;
  private Label orderLabel;
  private JTable orderTable;
  private JScrollPane orderTableScrollPane;
  private OrderTableModel orderTableModel;

  private List<OrderModel> orderList;
  private UserModel userModel;

  public OrderHistory() {
    loadData();
    initComponents();
    loadOrderData();
  }

  private void loadData() {
    orderList = new ArrayList<>(OrderBUS.getInstance().getAllModels());
    userModel = Authentication.getCurrentUser();
    if (userModel.getRole() == UserRole.CUSTOMER) {
      orderList.removeIf(order -> order.getCustomerId() != userModel.getId());
    } else if (userModel.getRole() == UserRole.EMPLOYEE) {
      orderList.removeIf(order -> order.getEmployeeId() != userModel.getId());
    } else if (userModel.getRole() == UserRole.ADMIN) {
      // do nothing
    }
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    orderLabel = new Label("Order History");
    headerPanel.add(orderLabel);
    add(headerPanel, BorderLayout.NORTH);

    orderTableModel = new OrderTableModel();
    orderTable = new JTable(orderTableModel);
    orderTable.addMouseListener(orderTableClickListener);
    orderTableScrollPane = new JScrollPane(orderTable);
    orderTableScrollPane.getVerticalScrollBar().setUnitIncrement(16);
    add(orderTableScrollPane, BorderLayout.CENTER);
  }

  private void loadOrderData() {
    orderTableModel.setOrderList(orderList);
    orderTableModel.fireTableDataChanged();
  }

  @Override
  public void search(String keyword) {
    List<OrderModel> searchResults = new ArrayList<>();
    for (OrderModel order : orderList) {
      if (
        String.valueOf(order.getId()).contains(keyword) ||
        String.valueOf(order.getCustomerId()).contains(keyword) ||
        String.valueOf(order.getEmployeeId()).contains(keyword) ||
        String.valueOf(order.getTotal()).contains(keyword) ||
        order.getStatus().name().contains(keyword) ||
        order.getCreatedAt().toString().contains(keyword) ||
        order.getUpdatedAt().toString().contains(keyword)
      ) {
        searchResults.add(order);
      }
    }
    orderTableModel.setOrderList(searchResults);
    orderTableModel.fireTableDataChanged();
  }

  private final MouseAdapter orderTableClickListener = new MouseAdapter() {
    @Override
    public void mouseClicked(MouseEvent e) {
      if (e.getClickCount() == 1) {
        JTable source = (JTable) e.getSource();
        int row = source.rowAtPoint(e.getPoint());
        if (row >= 0) {
          OrderModel selectedOrder = orderTableModel.getOrderAt(row);
          OrderDetail orderDetail = new OrderDetail(selectedOrder);
          MainPanel.getInstance().showFormStack(orderDetail);
        }
      }
    }
  };
}
