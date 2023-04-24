package com.bookstore.gui.form.salesman.view;

import javax.swing.JFrame;
import com.bookstore.bus.ImportBUS;
import com.bookstore.bus.ProviderBUS;
import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.button.Label;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import com.bookstore.models.ImportModel;
import com.bookstore.models.ProviderModel;

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
        importLabel = new JLabel();
        addPanel = new JPanel();
        addReceiptButton = new JButton();
        groupSearchPanel = new JPanel();
        searchBarTextField = new JTextField();
        searchButton = new JButton();
        groupExcel = new JPanel();
        importFromExcelButton = new JButton();
        exportToExcelButton = new JButton();
        jScrollPane1 = new JScrollPane();
        groupListImport = new JPanel();
        scrollPaneTableList = new JScrollPane();
        importTableList = new JTable();

        setMinimumSize(new java.awt.Dimension(1180, 620));
        setPreferredSize(new java.awt.Dimension(1180, 620));
        setLayout(new java.awt.BorderLayout());

        headerPanel.setLayout(new java.awt.GridLayout(2, 2));

        importLabel.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        importLabel.setText("Imports List");
        headerPanel.add(importLabel);

        addPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        addReceiptButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        addReceiptButton.setText("Add receipt");
        addReceiptButton.setPreferredSize(new java.awt.Dimension(110, 30));
        addReceiptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addReceiptButtonActionPerformed(evt);
            }
        });
        addPanel.add(addReceiptButton);

        headerPanel.add(addPanel);

        groupSearchPanel.setPreferredSize(new java.awt.Dimension(590, 30));
        groupSearchPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        searchBarTextField.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchBarTextField.setPreferredSize(new java.awt.Dimension(450, 30));
        groupSearchPanel.add(searchBarTextField);

        searchButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        searchButton.setText("Search");
        searchButton.setPreferredSize(new java.awt.Dimension(80, 30));
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });
        groupSearchPanel.add(searchButton);

        headerPanel.add(groupSearchPanel);

        groupExcel.setPreferredSize(new java.awt.Dimension(590, 30));
        groupExcel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        importFromExcelButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        importFromExcelButton.setText("Import from Excel file");
        importFromExcelButton.setPreferredSize(new java.awt.Dimension(160, 30));
        importFromExcelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importFromExcelButtonActionPerformed(evt);
            }
        });
        groupExcel.add(importFromExcelButton);

        exportToExcelButton.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        exportToExcelButton.setText("Export to Excel file");
        exportToExcelButton.setPreferredSize(new java.awt.Dimension(145, 30));
        exportToExcelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportToExcelButtonActionPerformed(evt);
            }
        });
        groupExcel.add(exportToExcelButton);

        headerPanel.add(groupExcel);

        add(headerPanel, java.awt.BorderLayout.PAGE_START);

        groupListImport.setLayout(new java.awt.BorderLayout());

        importTableList.setModel(new DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null }
                },
                new String[] {
                        "ID", "Provider ID", "Employee ID", "Price", "Status"
                }) {
            Class[] types = new Class[] {
                    java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class,
                    java.lang.String.class
            };
            boolean[] canEdit = new boolean[] {
                    false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        importTableList.getTableHeader().setReorderingAllowed(false);
        importTableList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importTableListMouseClicked(evt);
            }
        });
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
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = searchBarTextField.getText();
                if (text == null || text.isBlank()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập thông tin tìm kiếm !");
                    showTable();
                } else {
                    ProviderModel provider = new ProviderModel();
                    DefaultTableModel model = new DefaultTableModel();
                    model.addColumn("Id");
                    model.addColumn("Provider");
                    model.addColumn("Employee ID");
                    model.addColumn("Price");
                    model.addColumn("Created at");
                    model.addColumn("Updated at");
                    for (ImportModel imports : importList) {
                        for (ProviderModel providerModel : providerList) {
                            if (providerModel.getName().toLowerCase()
                                    .contains(text.toLowerCase())) {
                                provider = providerModel;
                                break;
                            }
                        }
                        if (provider.getId() == imports.getProviderId()) {
                            model.addRow(new Object[] {
                                    imports.getId(), provider.getName(),
                                    imports.getEmployeeId(),
                                    imports.getTotalPrice(),
                                    imports.getCreatedAt(), imports.getUpdatedAt()
                            });
                            importTableList.setModel(model);
                        }

                    }
                }
            }

        });
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
                    model.addRow(new Object[] {
                            imports.getId(), providerModel.getName(),
                            imports.getEmployeeId(),
                            imports.getTotalPrice(),
                            imports.getCreatedAt(), imports.getUpdatedAt()
                    });
                    importTableList.setModel(model);
                    break;
                }
            }

        }
        importTableList.getTableHeader().setReorderingAllowed(false);
        scrollPaneTableList.setViewportView(importTableList);
    }

    private void addReceiptButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void exportToExcelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void importFromExcelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void importTableListMouseClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
    private JPanel addPanel;
    private JButton addReceiptButton;
    private JButton exportToExcelButton;
    private JPanel groupExcel;
    private JPanel groupListImport;
    private JPanel groupSearchPanel;
    private JPanel headerPanel;
    private JButton importFromExcelButton;
    private JLabel importLabel;
    private JTable importTableList;
    private JScrollPane jScrollPane1;
    private JScrollPane scrollPaneTableList;
    private JTextField searchBarTextField;
    private JButton searchButton;
    // End of variables declaration
}
