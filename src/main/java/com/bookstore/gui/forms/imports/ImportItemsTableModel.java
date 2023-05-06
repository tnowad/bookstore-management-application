package com.bookstore.gui.forms.imports;

import com.bookstore.models.ImportItemsModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ImportItemsTableModel extends AbstractTableModel {

  private static final String[] COLUMN_NAMES = {
    "ID",
    "Quantity",
    "ISBN",
    "Price",
  };
  private List<ImportItemsModel> importItemsList;

  public ImportItemsTableModel(List<ImportItemsModel> importItemsList) {
    this.importItemsList = importItemsList;
  }

  @Override
  public int getRowCount() {
    return importItemsList.size();
  }

  @Override
  public int getColumnCount() {
    return COLUMN_NAMES.length;
  }

  @Override
  public String getColumnName(int columnIndex) {
    return COLUMN_NAMES[columnIndex];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    switch (columnIndex) {
      case 0:
        return Integer.class;
      case 1:
        return Integer.class;
      case 2:
        return String.class;
      case 3:
        return Double.class;
      default:
        throw new IllegalArgumentException("Invalid column index");
    }
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    ImportItemsModel importItem = importItemsList.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return importItem.getImportId();
      case 1:
        return importItem.getQuantity();
      case 2:
        return importItem.getBookIsbn();
      case 3:
        return importItem.getPrice();
      default:
        throw new IllegalArgumentException("Invalid column index");
    }
  }
}
