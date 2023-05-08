package com.bookstore.models.tables;

import com.bookstore.models.ImportItemsModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ImportItemTableModel extends AbstractTableModel {

  private static final String[] columnNames = {
    "Import ID",
    "Quantity",
    "Book ISBN",
    "Price",
  };

  private List<ImportItemsModel> importItemList;

  @Override
  public int getRowCount() {
    return importItemList == null ? 0 : importItemList.size();
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
    ImportItemsModel importItemsModel = importItemList.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return importItemsModel.getImportId();
      case 1:
        return importItemsModel.getQuantity();
      case 2:
        return importItemsModel.getBookIsbn();
      case 3:
        return importItemsModel.getPrice();
      default:
        return null;
    }
  }

  public ImportItemsModel getImportItemAt(int rowIndex) {
    return importItemList.get(rowIndex);
  }

  public void setImportItemList(List<ImportItemsModel> importItemList) {
    this.importItemList = importItemList;
  }
}
