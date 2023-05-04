package com.bookstore.gui.components.publishers;

import com.bookstore.bus.PublisherBUS;
import com.bookstore.models.PublisherModel;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PublisherListPanel extends javax.swing.JPanel {

  private static PublisherListPanel instance;
  PublisherBUS publisherBUS = PublisherBUS.getInstance();
  List<PublisherModel> listPublisher = publisherBUS.getAllModels();

  public PublisherListPanel() {
    initComponents();
    addTable();
  }

  public static PublisherListPanel getInstance() {
    if (instance == null) {
      instance = new PublisherListPanel();
    }
    return instance;
  }

  private void initComponents() {
    title = new JLabel();
    contend = new JPanel();
    buttonsPanel = new JPanel();
    buttonAdd = new JButton();
    buttonDelete = new JButton();
    table = new JPanel();
    headerTable = new JPanel();
    panel = new JPanel();
    label = new JLabel();
    serialText = new JLabel();
    idText = new JLabel();
    nameText = new JLabel();
    descriptionText = new JLabel();
    scrollPane = new JScrollPane();
    contendTable = new JPanel();

    setPreferredSize(new java.awt.Dimension(720, 444));
    setLayout(new java.awt.BorderLayout());

    title.setFont(new java.awt.Font("Segoe UI", 1, 18));
    title.setText("List Publisher");
    add(title, java.awt.BorderLayout.PAGE_START);

    contend.setLayout(new java.awt.BorderLayout());

    buttonsPanel.setPreferredSize(new java.awt.Dimension(720, 50));
    buttonsPanel.setLayout(
      new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 10, 10)
    );

    buttonAdd.setText("Add");
    buttonAdd.setPreferredSize(new java.awt.Dimension(75, 30));
    buttonsPanel.add(buttonAdd);

    buttonDelete.setText("Delete");
    buttonDelete.setPreferredSize(new java.awt.Dimension(75, 30));
    buttonsPanel.add(buttonDelete);

    contend.add(buttonsPanel, java.awt.BorderLayout.PAGE_START);

    table.setLayout(new java.awt.BorderLayout());

    headerTable.setLayout(new java.awt.GridLayout(1, 3));

    panel.setLayout(new java.awt.GridLayout(1, 3));
    panel.add(label);

    serialText.setFont(new java.awt.Font("Segoe UI", 0, 16));
    serialText.setText("Serial");
    panel.add(serialText);

    idText.setFont(new java.awt.Font("Segoe UI", 0, 16));
    idText.setText("Id");
    panel.add(idText);

    headerTable.add(panel);

    nameText.setFont(new java.awt.Font("Segoe UI", 0, 16));
    nameText.setText("Name");
    headerTable.add(nameText);

    descriptionText.setFont(new java.awt.Font("Segoe UI", 0, 16));
    descriptionText.setText("Description");
    headerTable.add(descriptionText);

    table.add(headerTable, java.awt.BorderLayout.NORTH);

    scrollPane.setViewportView(contendTable);

    table.add(scrollPane, java.awt.BorderLayout.CENTER);

    contend.add(table, java.awt.BorderLayout.CENTER);

    add(contend, java.awt.BorderLayout.CENTER);
  }

  public void addTable() {
    contendTable.removeAll();
    contendTable.setLayout(new java.awt.GridLayout(0, 1, 0, 15));
    int serial = 0;
    for (PublisherModel publisher : listPublisher) {
      PublisherPanel publisherPanel = new PublisherPanel(serial, publisher);
      contendTable.add(publisherPanel);
      serial = serial + 1;
    }
    contendTable.revalidate();
    contendTable.repaint();
  }

  private JButton buttonAdd;
  private JButton buttonDelete;
  private JPanel buttonsPanel;
  private JPanel contend;
  private JPanel contendTable;
  private JLabel descriptionText;
  private JPanel headerTable;
  private JLabel idText;
  private JLabel label;
  private JLabel nameText;
  private JPanel panel;
  private JScrollPane scrollPane;
  private JLabel serialText;
  private JPanel table;
  private JLabel title;
}
