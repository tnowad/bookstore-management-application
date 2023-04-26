package com.bookstore.gui.form.stock;

import com.bookstore.bus.ImportBUS;
import com.bookstore.dao.ImportDAO;
import com.bookstore.gui.component.table.Table;
import com.bookstore.models.ImportModel;
import com.bookstore.models.ImportTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.awt.ScrollPane;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class StockForm extends JPanel {

  private static final long serialVersionUID = 1L;
  private LayoutManager layout;
  private Table table;
  ScrollPane scrollPane;

  public StockForm() {
    initComponents();
    List<ImportModel> importList = ImportBUS.getInstance().getAllModels();
    DefaultTableModel tableModel = new ImportTableModel(importList);
    table.setModel(tableModel);
  }

  private void initComponents() {
    layout = new BorderLayout();
    scrollPane = new ScrollPane();

    setLayout(layout);
    setPreferredSize(new Dimension(1200, 800));
    table = new Table();
    scrollPane.add(table);
    add(scrollPane, BorderLayout.CENTER);

    table.setFillsViewportHeight(true);
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
