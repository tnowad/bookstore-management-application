package com.bookstore.models.tables;

import com.bookstore.bus.UserBUS;
import com.bookstore.models.OrderModel;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class OrderRecentTableModel extends DefaultTableModel {

  private static final String[] columnNames = {
    "ID",
    "Customer Email",
    "Employee Email",
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
            UserBUS
              .getInstance()
              .getModelById(order.getCustomerId())
              .getEmail(),
            UserBUS
              .getInstance()
              .getModelById(order.getEmployeeId())
              .getEmail(),
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
