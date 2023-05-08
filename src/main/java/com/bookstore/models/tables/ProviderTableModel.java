package com.bookstore.models.tables;

import com.bookstore.models.ProviderModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ProviderTableModel extends AbstractTableModel {

  private static final String[] columnNames = { "ID", "Name", "Description" };

  private List<ProviderModel> providerList;

  @Override
  public int getRowCount() {
    return providerList == null ? 0 : providerList.size();
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
    ProviderModel providerModel = providerList.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return providerModel.getId();
      case 1:
        return providerModel.getName();
      case 2:
        return providerModel.getDescription();
      default:
        return null;
    }
  }

  public ProviderModel getProviderAt(int rowIndex) {
    return providerList.get(rowIndex);
  }

  public void setProviderList(List<ProviderModel> providerList) {
    this.providerList = providerList;
  }
}
