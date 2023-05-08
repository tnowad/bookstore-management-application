package com.bookstore.gui.forms.imports;

import com.bookstore.bus.ProviderBUS;
import com.bookstore.interfaces.IFindModelForm;
import com.bookstore.models.ProviderModel;
import com.bookstore.models.tables.ProviderTableModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class ProviderSearchForm
  extends JPanel
  implements IFindModelForm<ProviderModel> {

  private List<ProviderModel> providerList = ProviderBUS
    .getInstance()
    .getAllModels();
  private ProviderModel providerModel;
  private JTable providerListTable;
  private ProviderTableModel providerTableModel;

  private JTextField providerNameTextField;
  private JButton findProviderButton;
  private JButton addProviderButton;

  public ProviderSearchForm() {
    initComponents();
    updateList(providerList);
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    providerNameTextField = new JTextField();
    findProviderButton = new JButton("Find");
    findProviderButton.addActionListener(findProviderButtonClickListener);
    addProviderButton = new JButton("Add");
    addProviderButton.addActionListener(addProviderButtonClickListener);

    JPanel providerSearchPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    providerSearchPanel.add(providerNameTextField, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.weightx = 0.1;
    providerSearchPanel.add(findProviderButton, gridBagConstraints);
    gridBagConstraints.gridx = 2;
    gridBagConstraints.weightx = 0.1;
    providerSearchPanel.add(addProviderButton, gridBagConstraints);
    add(providerSearchPanel, BorderLayout.NORTH);

    providerTableModel = new ProviderTableModel();
    providerListTable = new JTable(providerTableModel);

    JPanel providerListPanel = new JPanel(new BorderLayout());
    providerListPanel.add(
      providerListTable.getTableHeader(),
      BorderLayout.NORTH
    );
    providerListPanel.add(providerListTable, BorderLayout.CENTER);
    add(providerListPanel, BorderLayout.CENTER);
  }

  @Override
  public ProviderModel find() {
    return providerModel;
  }

  private void updateList(List<ProviderModel> providerList) {
    providerTableModel.setProviderList(providerList);
    providerTableModel.fireTableDataChanged();
  }

  private ActionListener addProviderButtonClickListener = e -> {
    int selectedRow = providerListTable.getSelectedRow();

    if (selectedRow == -1) {
      return;
    }
    providerModel = providerTableModel.getProviderAt(selectedRow);
    System.out.println(providerModel);
  };

  private ActionListener findProviderButtonClickListener = e -> {
    String providerName = providerNameTextField.getText();
    List<ProviderModel> providerList = ProviderBUS
      .getInstance()
      .findByName(providerName);
    updateList(providerList);
  };
}
