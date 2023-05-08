package com.bookstore.models.tables;

import com.bookstore.models.ImportModel;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ImportTableModel extends AbstractTableModel {

  private static final String[] columnNames = {
    "ID",
    "Provider ID",
    "Employee ID",
    "Total Price",
    "Created At",
    "Updated At",
  };
  private List<ImportModel> importList;

  @Override
  public int getRowCount() {
    return importList == null ? 0 : importList.size();
  }

  @Override
  public String getColumnName(int column) {
    return columnNames[column];
  }

  @Override
  public int getColumnCount() {
    return columnNames.length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    ImportModel importModel = importList.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return importModel.getId();
      case 1:
        return importModel.getProviderId();
      case 2:
        return importModel.getEmployeeId();
      case 3:
        return importModel.getTotalPrice();
      case 4:
        return importModel
          .getCreatedAt()
          .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      case 5:
        return importModel
          .getUpdatedAt()
          .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
      default:
        return null;
    }
  }

  public ImportModel getImportAt(int rowIndex) {
    return importList.get(rowIndex);
  }

  public void setImportList(List<ImportModel> importList) {
    this.importList = importList;
  }
}
