package com.bookstore.gui.forms.imports;

import com.bookstore.bus.ImportBUS;
import com.bookstore.bus.ProviderBUS;
import com.bookstore.gui.components.button.Button;
import com.bookstore.gui.components.label.Label;
import com.bookstore.models.ImportModel;
import com.bookstore.models.ProviderModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ImportList extends JPanel {

  ImportBUS importBus = ImportBUS.getInstance();
  ProviderBUS providerBus = ProviderBUS.getInstance();
  List<ImportModel> importList = importBus.getAllModels();
  List<ProviderModel> providerList = providerBus.getAllModels();

  public ImportList() {
    initComponents();
    listImport();
    searh();
  }

  private void initComponents() {
    headerPanel = new JPanel();
    importLabel = new Label("Import list");
    addPanel = new JPanel();
    addReceiptButton = new Button("Add import");
    groupSearchPanel = new JPanel();
    searchBarTextField = new JTextField();
    searchButton = new Button("Search");
    groupExcel = new JPanel();
    importFromExcelButton = new Button("Import excel");
    exportToExcelButton = new Button("Export excel");
    jScrollPane1 = new JScrollPane();
    groupListImport = new JPanel();
    scrollPaneTableList = new JScrollPane();
    importTableList = new JTable();

    setMinimumSize(new java.awt.Dimension(1180, 620));
    setPreferredSize(new java.awt.Dimension(1180, 620));
    setLayout(new java.awt.BorderLayout());

    headerPanel.setLayout(new java.awt.GridLayout(2, 2));

    headerPanel.add(importLabel);

    addPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

    addReceiptButton.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          addReceiptButtonActionPerformed(evt);
        }
      }
    );
    addPanel.add(addReceiptButton);

    headerPanel.add(addPanel);

    groupSearchPanel.setPreferredSize(new java.awt.Dimension(1000, 30));
    groupSearchPanel.setLayout(
      new BoxLayout(groupSearchPanel, BoxLayout.X_AXIS)
    );

    searchBarTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
    searchBarTextField.setPreferredSize(new java.awt.Dimension(450, 30));
    groupSearchPanel.add(searchBarTextField);

    searchButton.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          searchButtonActionPerformed(evt);
        }
      }
    );
    groupSearchPanel.add(searchButton);

    headerPanel.add(groupSearchPanel);

    groupExcel.setPreferredSize(new java.awt.Dimension(590, 30));
    groupExcel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

    importFromExcelButton.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          importFromExcelButtonActionPerformed(evt);
        }
      }
    );
    groupExcel.add(importFromExcelButton);

    exportToExcelButton.addActionListener(
      new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
          exportToExcelButtonActionPerformed(evt);
        }
      }
    );
    groupExcel.add(exportToExcelButton);

    headerPanel.add(groupExcel);

    add(headerPanel, java.awt.BorderLayout.PAGE_START);

    groupListImport.setLayout(new java.awt.BorderLayout());

    importTableList.getTableHeader().setReorderingAllowed(false);
    importTableList.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
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

    groupListImport.add(scrollPaneTableList, java.awt.BorderLayout.CENTER);

    jScrollPane1.setViewportView(groupListImport);

    add(jScrollPane1, java.awt.BorderLayout.CENTER);
  }

  private void searh() {
    searchButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          String text = searchBarTextField.getText();
          if (text == null || text.isBlank()) {
            JOptionPane.showMessageDialog(
              null,
              "Search field cannot be empty. Please try again!"
            );
            showTable();
          } else {
            ProviderModel provider = new ProviderModel();
            DefaultTableModel model = new DefaultTableModel();
            System.out.println(text);
            model.addColumn("Id");
            model.addColumn("Provider");
            model.addColumn("Employee ID");
            model.addColumn("Price");
            model.addColumn("Created at");
            model.addColumn("Updated at");
            for (ProviderModel providerModel : providerList) {
              if (
                providerModel
                  .getName()
                  .toLowerCase()
                  .contains(text.toLowerCase())
              ) {
                provider = providerModel;
                System.out.println(providerModel.getName());
                break;
              }
            }
            for (ImportModel imports : importList) {
              if (provider.getId() == imports.getProviderId()) {
                System.out.println(
                  provider.getId() + " and " + imports.getProviderId()
                );
                model.addRow(
                  new Object[] {
                    imports.getId(),
                    provider.getName(),
                    imports.getEmployeeId(),
                    imports.getTotalPrice(),
                    imports.getCreatedAt(),
                    imports.getUpdatedAt(),
                  }
                );
                importTableList.setModel(model);
              }
            }
          }
        }
      }
    );
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

  private void addReceiptButtonActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private void exportToExcelButtonActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private void importFromExcelButtonActionPerformed(
    java.awt.event.ActionEvent evt
  ) {}

  private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {}

  private void importTableListMouseClicked(java.awt.event.MouseEvent evt) {}

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
  private JTextField searchBarTextField;
  private Button searchButton;
}
