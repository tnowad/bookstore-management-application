package com.bookstore.models;

import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class ImportTableModel extends DefaultTableModel {

  private static final String[] columnNames = {
    "ID",
    "Provider ID",
    "Employee ID",
    "Total Price",
    "Created At",
    "Updated At",
  };

  public ImportTableModel(List<ImportModel> importList) {
    super(new Object[][] {}, columnNames);
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd HH:mm:ss"
    );

    for (ImportModel anImport : importList) {
      Object[] row = new Object[] {
        anImport.getId(),
        anImport.getProviderId(),
        anImport.getEmployeeId(),
        anImport.getTotalPrice(),
        anImport.getCreatedAt() == null
          ? ""
          : anImport.getCreatedAt().format(formatter),
        anImport.getUpdatedAt() == null
          ? ""
          : anImport.getUpdatedAt().format(formatter),
      };
      addRow(row);
    }
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return false;
  }
}
