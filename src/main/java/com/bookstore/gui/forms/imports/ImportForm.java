package com.bookstore.gui.forms.imports;

import com.bookstore.bus.ImportBUS;
import com.bookstore.gui.components.tables.Table;
import com.bookstore.models.ImportModel;
import com.bookstore.models.tables.ImportTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ImportForm extends JPanel {

  private LayoutManager layout;
  private JPanel topPanel;
  private Table table;
  private JScrollPane scrollPane;

  private JLabel titleLabel;
  private JTextField searchImportTextField;
  private JButton addImportButton;
  private JButton editImportButton;
  private JButton deleteImportButton;
  private JButton searchImportButton;
  private JButton refreshImportButton;
  private JButton filterImportButton;
  private JButton exportExcelButton;

  public ImportForm() {
    initComponents();
    // fill data to table model
    List<ImportModel> importList = ImportBUS.getInstance().getAllModels();

    DefaultTableModel tableModel = new ImportTableModel(importList);

    table.setModel(tableModel);
  }

  private void initComponents() {
    layout = new BorderLayout();
    setLayout(layout);
    topPanel = new JPanel();
    GridBagLayout topPanelLayout = new GridBagLayout();
    topPanel.setLayout(topPanelLayout);

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

    titleLabel = new JLabel("Stock Management");
    titleLabel.setIcon(
      new ImageIcon("src/main/java/resources/icons/stock.png")
    );
    JPanel filterPanel = new JPanel();
    filterPanel.setLayout(new BorderLayout());
    searchImportTextField = new JTextField(20);
    searchImportButton = new JButton("Search");
    filterPanel.add(searchImportTextField, BorderLayout.CENTER);
    filterPanel.add(searchImportButton, BorderLayout.EAST);

    filterImportButton = new JButton("Filter");
    addImportButton = new JButton("Create");
    editImportButton = new JButton("Edit");
    deleteImportButton = new JButton("Delete");
    refreshImportButton = new JButton("Refresh");
    exportExcelButton = new JButton("Export Excel");
    new JButton("Import Excel");

    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.insets = new Insets(0, 0, 0, 10);
    topPanel.add(titleLabel, gridBagConstraints);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    topPanel.add(filterPanel, gridBagConstraints);
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 1;
    topPanel.add(filterImportButton, gridBagConstraints);
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 1;
    topPanel.add(addImportButton, gridBagConstraints);
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 1;
    topPanel.add(editImportButton, gridBagConstraints);
    gridBagConstraints.gridx = 5;
    gridBagConstraints.gridy = 1;
    topPanel.add(deleteImportButton, gridBagConstraints);
    gridBagConstraints.gridx = 6;
    gridBagConstraints.gridy = 1;
    topPanel.add(refreshImportButton, gridBagConstraints);
    gridBagConstraints.gridx = 7;
    gridBagConstraints.gridy = 1;
    topPanel.add(exportExcelButton, gridBagConstraints);

    setPreferredSize(new Dimension(1200, 800));
    table = new Table();
    scrollPane = new JScrollPane(table);
    add(topPanel, BorderLayout.NORTH);
    add(scrollPane, BorderLayout.CENTER);
  }
}
