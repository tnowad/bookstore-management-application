package com.bookstore.gui.form.stock;

import com.bookstore.gui.component.table.Table;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class StockForm extends JPanel {

  private static final long serialVersionUID = 1L;
  private LayoutManager layout;
  private Table table;

  public StockForm() {
    initComponents();
    table.setModel(
      new DefaultTableModel(
        new Object[][] {},
        new String[] { "Name", "Email", "User Type", "Joined", "Status" }
      ) {
        private static final long serialVersionUID = 1L;
        Class<?>[] types = new Class[] {
          java.lang.String.class,
          java.lang.String.class,
          java.lang.String.class,
          java.lang.String.class,
          java.lang.Boolean.class,
        };
        boolean[] canEdit = new boolean[] { false, false, false, false, false };

        public Class<?> getColumnClass(int columnIndex) {
          return types[columnIndex];
        }

        public boolean isCellEditable(int rowIndex, int columnIndex) {
          return canEdit[columnIndex];
        }
      }
    );
    table.addRow(
      new Object[] {
        "Mike Bhand",
        "mikebhand@gmail.com",
        "Admin",
        "25 Apr,2018",
        true,
      }
    );
  }

  private void initComponents() {
    layout = new BorderLayout();
    setLayout(layout);
    table = new Table();
    add(table, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1200, 800));
    frame.setTitle("Stock Form");
    frame.setLocationRelativeTo(null);
    frame.getContentPane().add(new StockForm());
    frame.pack();
    frame.setVisible(true);
  }
}
