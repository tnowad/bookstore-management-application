package com.bookstore.models.tables;

import com.bookstore.models.OrderModel;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class OrderTableModel extends AbstractTableModel {

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

  public OrderModel getOrderAt(int rowIndex) {
    return orderList.get(rowIndex);
  }
}
