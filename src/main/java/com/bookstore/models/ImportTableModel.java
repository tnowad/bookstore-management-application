package com.bookstore.models;

import javax.swing.table.DefaultTableModel;

public class ImportTableModel extends DefaultTableModel {

  private static final long serialVersionUID = 1L;

  private ImportModel[] data;

  public ImportTableModel(ImportModel[] data) {
    this.data = data;
    setColumnIdentifiers(
      new Object[] {
        "ID",
        "Provider ID",
        "Employee ID",
        "Total Price",
        "Created At",
        "Updated At",
      }
    );
  }

  @Override
  public int getRowCount() {
    return data.length;
  }

  @Override
  public int getColumnCount() {
    return 6;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    switch (columnIndex) {
      case 0:
        return data[rowIndex].getId();
      case 1:
        return data[rowIndex].getProviderId();
      case 2:
        return data[rowIndex].getEmployeeId();
      case 3:
        return data[rowIndex].getTotalPrice();
      case 4:
        return data[rowIndex].getCreatedAt();
      case 5:
        return data[rowIndex].getUpdatedAt();
      default:
        return null;
    }
  }
}
