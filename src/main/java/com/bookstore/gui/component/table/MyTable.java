package com.bookstore.gui.component.table;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class MyTable extends JTable {

  public MyTable() {
    super();
    this.setModel(new MyTableModel());
  }

  private static class MyTableModel extends AbstractTableModel {

    private static final String[] COLUMN_NAMES = { "Name", "Age", "Gender" };

    private Object[][] data = {
      { "John Doe", 25, "Male" },
      { "Jane Doe", 23, "Female" },
      { "Peter Smith", 30, "Male" },
      { "Mary Smith", 28, "Female" },
    };

    @Override
    public int getRowCount() {
      return data.length;
    }

    @Override
    public int getColumnCount() {
      return COLUMN_NAMES.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
      return data[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
      return COLUMN_NAMES[column];
    }
  }

  public static void main(String[] args) {
    FlatMacLightLaf.setup();
    EventQueue.invokeLater(
      new Runnable() {
        @Override
        public void run() {
          JFrame frame = new JFrame("My Table");
          frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
          frame.add(new JScrollPane(new MyTable()));
          frame.pack();
          frame.setLocationRelativeTo(null);
          frame.setVisible(true);
        }
      }
    );
  }
}
