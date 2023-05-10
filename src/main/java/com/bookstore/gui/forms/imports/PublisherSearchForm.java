package com.bookstore.gui.forms.imports;

import com.bookstore.bus.PublisherBUS;
import com.bookstore.interfaces.IFindModelForm;
import com.bookstore.models.PublisherModel;
import com.bookstore.models.tables.PublisherTableModel;
import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

public class PublisherSearchForm
  extends JPanel
  implements IFindModelForm<PublisherModel> {

  private List<PublisherModel> publisherList = PublisherBUS
    .getInstance()
    .getAllModels();
  private PublisherModel publisherModel;
  private JTable publisherListTable;
  private PublisherTableModel publisherTableModel;

  private JTextField publisherNameTextField;
  private JButton findPublisherButton;
  private JButton addPublisherButton;

  public PublisherSearchForm() {
    initComponents();
    updateList(publisherList);
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    publisherNameTextField = new JTextField();
    findPublisherButton = new JButton("Find");
    findPublisherButton.addActionListener(findPublisherButtonClickListener);
    addPublisherButton = new JButton("Add");
    addPublisherButton.addActionListener(addPublisherButtonClickListener);

    JPanel publisherSearchPanel = new JPanel(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    publisherSearchPanel.add(publisherNameTextField, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.weightx = 0.1;
    publisherSearchPanel.add(findPublisherButton, gridBagConstraints);
    gridBagConstraints.gridx = 2;
    gridBagConstraints.weightx = 0.1;
    publisherSearchPanel.add(addPublisherButton, gridBagConstraints);
    add(publisherSearchPanel, BorderLayout.NORTH);

    publisherTableModel = new PublisherTableModel();
    publisherListTable = new JTable(publisherTableModel);

    JPanel publisherListPanel = new JPanel(new BorderLayout());
    publisherListPanel.add(
      publisherListTable.getTableHeader(),
      BorderLayout.NORTH
    );
    publisherListPanel.add(publisherListTable, BorderLayout.CENTER);
    add(publisherListPanel, BorderLayout.CENTER);
  }

  @Override
  public PublisherModel find() {
    return publisherModel;
  }

  private void updateList(List<PublisherModel> publisherList) {
    publisherTableModel.publisherList(publisherList);
    publisherTableModel.fireTableDataChanged();
  }

  private ActionListener addPublisherButtonClickListener = e -> {
    int selectedRow = publisherListTable.getSelectedRow();

    if (selectedRow == -1) {
      return;
    }
    publisherModel = publisherTableModel.getPublisherAt(selectedRow);
    System.out.println(publisherModel);
  };

  private ActionListener findPublisherButtonClickListener = e -> {
    String providerName = publisherNameTextField.getText();
    List<PublisherModel> publisherList = PublisherBUS
      .getInstance()
      .findByName(providerName);
    updateList(publisherList);
  };
}
