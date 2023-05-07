package com.bookstore.gui.forms.orders;

import com.bookstore.bus.OrderBUS;
import com.bookstore.enums.UserRole;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.interfaces.ISearchable;
import com.bookstore.models.OrderModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;

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
    orderTableScrollPane = new JScrollPane(orderTable);
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

  private class OrderTableModel extends AbstractTableModel {

    private final String[] columnNames = {
      "Order ID",
      "Customer ID",
      "Employee ID",
      "Total",
      "Status",
      "Created At",
      "Updated At",
    };
    private List<OrderModel> orderList;

    public void setOrderList(List<OrderModel> orderList) {
      this.orderList = orderList;
    }

    @Override
    public int getRowCount() {
      return orderList == null ? 0 : orderList.size();
    }

    @Override
    public int getColumnCount() {
      return columnNames.length;
    }

    @Override
    public String getColumnName(int column) {
      return columnNames[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      OrderModel order = orderList.get(rowIndex);
      switch (columnIndex) {
        case 0:
          return order.getId();
        case 1:
          return order.getCustomerId();
        case 2:
          return order.getEmployeeId();
        case 3:
          return order.getTotal();
        case 4:
          return order.getStatus().name();
        case 5:
          return order
            .getCreatedAt()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        case 6:
          return order
            .getUpdatedAt()
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        default:
          return null;
      }
    }
  }
}
