package com.bookstore.models.tables;

import com.bookstore.models.PublisherModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PublisherTableModel extends AbstractTableModel {

  private static final String[] columnNames = { "ID", "Name", "Description" };

  private List<PublisherModel> publisherList;

  @Override
  public int getRowCount() {
    return publisherList == null ? 0 : publisherList.size();
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
    PublisherModel PublisherModel = publisherList.get(rowIndex);
    switch (columnIndex) {
      case 0:
        return PublisherModel.getId();
      case 1:
        return PublisherModel.getName();
      case 2:
        return PublisherModel.getDescription();
      default:
        return null;
    }
  }

  public PublisherModel getPublisherAt(int rowIndex) {
    return publisherList.get(rowIndex);
  }

  public void publisherList(List<PublisherModel> publisherList) {
    this.publisherList = publisherList;
  }
}
