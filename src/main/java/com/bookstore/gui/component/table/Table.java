package com.bookstore.gui.component.table;

import com.bookstore.enums.StatusType;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

  public Table() {
    setShowHorizontalLines(true);
    setGridColor(new Color(230, 230, 230));
    setRowHeight(40);
    getTableHeader().setReorderingAllowed(false);
  }

  public void addRow(Object[] row) {
    DefaultTableModel model = (DefaultTableModel) getModel();
    model.addRow(row);
  }
}
