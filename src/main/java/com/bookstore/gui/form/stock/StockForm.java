package com.bookstore.gui.form.stock;

import com.bookstore.bus.ImportBUS;
import com.bookstore.dao.ImportDAO;
import com.bookstore.gui.component.table.Table;
import com.bookstore.gui.component.table.TableActionCellEditor;
import com.bookstore.gui.component.table.TableActionCellRender;
import com.bookstore.models.ImportModel;
import com.bookstore.models.ImportTableModel;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.LayoutManager;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class StockForm extends JPanel {

  private static final long serialVersionUID = 1L;
  private LayoutManager layout;
  private JPanel topPanel;
  private Table table;
  ScrollPane scrollPane;

  public StockForm() {
    initComponents();
    List<ImportModel> importList = ImportBUS.getInstance().getAllModels();
    DefaultTableModel tableModel = new ImportTableModel(importList);
    table.setModel(tableModel);
    table
      .getColumnModel()
      .getColumn(6)
      .setCellRenderer(new TableActionCellRender());
    table
      .getColumnModel()
      .getColumn(6)
      .setCellEditor(new TableActionCellEditor());
  }

  private void initComponents() {
    layout = new BorderLayout();
    topPanel = new JPanel();

    topPanel.setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

    ActionListener addImportActionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.out.println("Add import");
      }
    };

    JButton button = new JButton("Add");
    button.addActionListener(addImportActionListener);
    topPanel.add(button, gridBagConstraints);

    scrollPane = new ScrollPane();

    setLayout(layout);
    setPreferredSize(new Dimension(1200, 800));
    table = new Table();
    scrollPane.add(table.getTableHeader());
    scrollPane.add(table);

    add(topPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    FlatMacLightLaf.setup();
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
