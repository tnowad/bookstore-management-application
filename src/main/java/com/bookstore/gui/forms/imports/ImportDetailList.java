package com.bookstore.gui.forms.imports;

import com.bookstore.bus.ImportBUS;
import com.bookstore.bus.ProviderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.models.ImportItemsModel;
import com.bookstore.models.ImportModel;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ImportDetailList extends JPanel {

  private ImportModel importModel;
  private List<ImportItemsModel> importItemsList;

  private JLabel providerLabel = new JLabel("Provider:");
  private JLabel providerValueLabel = new JLabel();
  private JLabel employeeLabel = new JLabel("Employee:");
  private JLabel employeeValueLabel = new JLabel();
  private JLabel createdAtLabel = new JLabel("Created At:");
  private JLabel createdAtValueLabel = new JLabel();
  private JLabel updatedAtLabel = new JLabel("Updated At:");
  private JLabel updatedAtValueLabel = new JLabel();
  private JLabel totalPriceLabel = new JLabel("Total Price:");
  private JLabel totalPriceValueLabel = new JLabel();

  private JTable importItemsTable;
  private ImportItemsTableModel importItemsTableModel;

  private JButton editButton = new JButton("Edit");
  private JButton deleteButton = new JButton("Delete");

  public ImportDetailList(int importId) {
    this.importModel = ImportBUS.getInstance().getModelById(importId);
    this.importItemsList = ImportBUS.getInstance().getImportItems(importId);

    initComponents();
    loadData();
  }

  private void initComponents() {
    JPanel contentPane = new JPanel(new GridBagLayout());

    GridBagConstraints constraints = new GridBagConstraints();
    constraints.gridx = 0;
    constraints.gridy = 0;
    constraints.anchor = GridBagConstraints.WEST;
    constraints.insets = new Insets(10, 10, 10, 10);

    // Provider
    contentPane.add(providerLabel, constraints);

    constraints.gridx++;
    contentPane.add(providerValueLabel, constraints);

    // Employee
    constraints.gridx = 0;
    constraints.gridy++;
    contentPane.add(employeeLabel, constraints);

    constraints.gridx++;
    contentPane.add(employeeValueLabel, constraints);

    // Created At
    constraints.gridx = 0;
    constraints.gridy++;
    contentPane.add(createdAtLabel, constraints);

    constraints.gridx++;
    contentPane.add(createdAtValueLabel, constraints);

    // Updated At
    constraints.gridx = 0;
    constraints.gridy++;
    contentPane.add(updatedAtLabel, constraints);

    constraints.gridx++;
    contentPane.add(updatedAtValueLabel, constraints);

    // Total Price
    constraints.gridx = 0;
    constraints.gridy++;
    contentPane.add(totalPriceLabel, constraints);

    constraints.gridx++;
    contentPane.add(totalPriceValueLabel, constraints);

    // Import Items Table
    importItemsTableModel = new ImportItemsTableModel(importItemsList);
    importItemsTable = new JTable(importItemsTableModel);
    JScrollPane scrollPane = new JScrollPane(importItemsTable);

    constraints.gridx = 0;
    constraints.gridy++;
    constraints.gridwidth = 2;
    constraints.fill = GridBagConstraints.BOTH;
    constraints.weightx = 1;
    constraints.weighty = 1;
    contentPane.add(scrollPane, constraints);

    // Buttons
    constraints.gridy++;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.weightx = 0;
    constraints.weighty = 0;
    constraints.anchor = GridBagConstraints.CENTER;
    contentPane.add(editButton, constraints);

    constraints.gridx++;
    contentPane.add(deleteButton, constraints);

    add(contentPane);
  }

  private void loadData() {
    // Load import model data
    providerValueLabel.setText(
      ProviderBUS
        .getInstance()
        .getModelById(importModel.getProviderId())
        .getName()
    );
    employeeValueLabel.setText(
      UserBUS
        .getInstance()
        .getModelById(importModel.getEmployeeId())
        .getUsername()
    );
    createdAtValueLabel.setText(
      importModel.getCreatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    );
    updatedAtValueLabel.setText(
      importModel.getUpdatedAt().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
    );
    totalPriceValueLabel.setText(importModel.getTotalPrice().toString());
  }
}
