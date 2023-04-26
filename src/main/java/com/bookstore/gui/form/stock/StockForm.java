package com.bookstore.gui.form.stock;

import com.bookstore.bus.ImportBUS;
import com.bookstore.dao.ImportDAO;
import com.bookstore.gui.component.table.Table;
import com.bookstore.models.ImportModel;
import com.bookstore.models.ImportTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.List;
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
    List<ImportModel> importModelList = ImportBUS.getInstance().getAllModels();

    importModelList.add(new ImportModel(0, 1, 1, 12.2, null, null));

    ImportModel[] importModelArray = new ImportModel[importModelList.size()];

    importModelList.toArray(importModelArray);
    table.setModel(new ImportTableModel(importModelArray));
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
