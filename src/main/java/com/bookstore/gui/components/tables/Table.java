package com.bookstore.gui.components.tables;

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
    // getTableHeader().setReorderingAllowed(false);
    setDefaultRenderer(
      Object.class,
      new DefaultTableCellRenderer() {
        @Override
        public Component getTableCellRendererComponent(
          JTable table,
          Object value,
          boolean isSelected,
          boolean hasFocus,
          int row,
          int column
        ) {
          super.getTableCellRendererComponent(
            table,
            value,
            isSelected,
            hasFocus,
            row,
            column
          );
          return this;
        }
      }
    );
  }

  public void addRow(Object[] row) {
    DefaultTableModel model = (DefaultTableModel) getModel();
    model.addRow(row);
  }
}
