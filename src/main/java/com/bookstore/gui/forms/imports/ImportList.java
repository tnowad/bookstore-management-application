package com.bookstore.gui.forms.imports;

import com.bookstore.bus.ImportBUS;
import com.bookstore.bus.ProviderBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.interfaces.ISearchable;
import com.bookstore.models.ImportModel;
import com.bookstore.models.ProviderModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ImportList extends JPanel implements ISearchable {

  private JPanel addPanel;
  private Button addReceiptButton;
  private Button exportToExcelButton;
  private JPanel groupExcel;
  private JPanel groupListImport;
  private JPanel groupSearchPanel;
  private JPanel headerPanel;
  private Button importFromExcelButton;
  private Label importLabel;
  private JTable importTableList;
  private JScrollPane jScrollPane1;
  private JScrollPane scrollPaneTableList;

  private static ImportList instance;

  ImportBUS importBus = ImportBUS.getInstance();
  ProviderBUS providerBus = ProviderBUS.getInstance();
  List<ImportModel> importList = importBus.getAllModels();
  List<ProviderModel> providerList = providerBus.getAllModels();

  public ImportList() {
    initComponents();
    listImport();
  }

  public static ImportList getInstance() {
    if (instance == null) {
      instance = new ImportList();
    }
    return instance;
  }

  private void initComponents() {
    headerPanel = new JPanel();
    importLabel = new Label("Import list");
    addPanel = new JPanel();
    addReceiptButton = new Button("Add import");
    groupSearchPanel = new JPanel();
    groupExcel = new JPanel();
    importFromExcelButton = new Button("Import excel");
    exportToExcelButton = new Button("Export excel");
    jScrollPane1 = new JScrollPane();
    groupListImport = new JPanel();
    scrollPaneTableList = new JScrollPane();
    importTableList = new JTable();

    setMinimumSize(new Dimension(1180, 620));
    setPreferredSize(new Dimension(1180, 620));
    setLayout(new BorderLayout());

    headerPanel.setLayout(new GridLayout(2, 2));

    headerPanel.add(importLabel);

    addPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    addReceiptButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          addReceiptButtonActionPerformed(evt);
        }
      }
    );
    addPanel.add(addReceiptButton);

    headerPanel.add(addPanel);

    groupSearchPanel.setPreferredSize(new Dimension(1000, 30));
    groupSearchPanel.setLayout(
      new BoxLayout(groupSearchPanel, BoxLayout.X_AXIS)
    );

    headerPanel.add(groupSearchPanel);

    groupExcel.setPreferredSize(new Dimension(590, 30));
    groupExcel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    importFromExcelButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          importFromExcelButtonActionPerformed(evt);
        }
      }
    );
    groupExcel.add(importFromExcelButton);

    exportToExcelButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          exportToExcelButtonActionPerformed(evt);
        }
      }
    );
    groupExcel.add(exportToExcelButton);

    headerPanel.add(groupExcel);

    add(headerPanel, BorderLayout.PAGE_START);

    groupListImport.setLayout(new BorderLayout());

    importTableList.getTableHeader().setReorderingAllowed(false);
    importTableList.addMouseListener(
      new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
          importTableListMouseClicked(evt);
        }
      }
    );
    scrollPaneTableList.setViewportView(importTableList);
    if (importTableList.getColumnModel().getColumnCount() > 0) {
      importTableList.getColumnModel().getColumn(0).setResizable(false);
      importTableList.getColumnModel().getColumn(1).setResizable(false);
      importTableList.getColumnModel().getColumn(2).setResizable(false);
      importTableList.getColumnModel().getColumn(3).setResizable(false);
      importTableList.getColumnModel().getColumn(4).setResizable(false);
    }

    groupListImport.add(scrollPaneTableList, BorderLayout.CENTER);

    jScrollPane1.setViewportView(groupListImport);

    add(jScrollPane1, BorderLayout.CENTER);
  }

  private void listImport() {
    showTable();
  }

  private void showTable() {
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Id");
    model.addColumn("Provider");
    model.addColumn("Employee ID");
    model.addColumn("Price");
    model.addColumn("Created at");
    model.addColumn("Updated at");
    for (ImportModel imports : importList) {
      for (ProviderModel providerModel : providerList) {
        if (providerModel.getId() == imports.getProviderId()) {
          model.addRow(
            new Object[] {
              imports.getId(),
              providerModel.getName(),
              imports.getEmployeeId(),
              imports.getTotalPrice(),
              imports.getCreatedAt(),
              imports.getUpdatedAt(),
            }
          );
          importTableList.setModel(model);
          break;
        }
      }
    }
    importTableList.getTableHeader().setReorderingAllowed(false);
    scrollPaneTableList.setViewportView(importTableList);
  }

  private void addReceiptButtonActionPerformed(ActionEvent evt) {}

  private void exportToExcelButtonActionPerformed(ActionEvent evt) {}

  private void importFromExcelButtonActionPerformed(ActionEvent evt) {}

  private void importTableListMouseClicked(MouseEvent evt) {
    int row = importTableList.getSelectedRow();
    int id = (int) importTableList.getValueAt(row, 0);
    ImportDetailList importDetailList = new ImportDetailList(id);
    MainPanel.getInstance().showFormStack(importDetailList);
  }

  @Override
  public void search(String keyword) {
    System.out.println(keyword);
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("Id");
    model.addColumn("Provider");
    model.addColumn("Employee ID");
    model.addColumn("Price");
    model.addColumn("Created at");
    model.addColumn("Updated at");
    for (ImportModel imports : importList) {
      for (ProviderModel providerModel : providerList) {
        if (providerModel.getId() == imports.getProviderId()) {
          if (
            providerModel
              .getName()
              .toLowerCase()
              .contains(keyword.toLowerCase())
          ) {
            model.addRow(
              new Object[] {
                imports.getId(),
                providerModel.getName(),
                imports.getEmployeeId(),
                imports.getTotalPrice(),
                imports.getCreatedAt(),
                imports.getUpdatedAt(),
              }
            );
            importTableList.setModel(model);
            break;
          }
        }
      }
    }
  }
}
