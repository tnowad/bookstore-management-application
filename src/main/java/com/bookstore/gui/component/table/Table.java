package com.bookstore.gui.component.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.bookstore.enums.StatusType;

public class Table extends JTable {

  public Table() {
    setShowHorizontalLines(true);
    setGridColor(new Color(230, 230, 230));
    setRowHeight(40);
    getTableHeader().setReorderingAllowed(false);
    getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable jTable, Object o, boolean bln, boolean bln1, int i,
          int i1) {
        TableHeader header = new TableHeader(o + "");
        if (i1 == 4) {
          header.setHorizontalAlignment(SwingConstants.CENTER);
        }
        return header;
      }
    });
    setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable jTable, Object o, boolean selected, boolean bln1, int i,
          int i1) {
        if (i1 != 4) {
          Component com = super.getTableCellRendererComponent(jTable, o, selected, bln1, i, i1);
          com.setBackground(Color.WHITE);
          setBorder(noFocusBorder);
          if (selected) {
            com.setForeground(new Color(15, 89, 140));
          } else {
            com.setForeground(new Color(102, 102, 102));
          }
          return com;
        } else {
          StatusType type = (StatusType) o;
          return new CellStatus(type);
        }
      }
    });
  }

  public void addRow(Object[] row) {
    DefaultTableModel model = (DefaultTableModel) getModel();
    model.addRow(row);
  }
}
