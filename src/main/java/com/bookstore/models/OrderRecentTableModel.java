package com.bookstore.models;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class OrderRecentTableModel extends DefaultTableModel {

  private static final String[] columnNames = {
    "ID",
    "CustomerID",
    "EmployeeID",
    "TotalPrice",
    "CreatedAt",
    "UpdatedAt",
  };

  public OrderRecentTableModel(List<OrderModel> orderList) {
    super(new Object[][] {}, columnNames);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm:ss"
    );
    for (OrderModel order : orderList) {
      this.addRow(
          new Object[] {
            order.getId(),
            order.getCustomerId(),
            order.getEmployeeId(),
            order.getTotal(),
            order.getCreatedAt().format(formatter),
            order.getUpdatedAt().format(formatter),
          }
        );
    }
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }
}
