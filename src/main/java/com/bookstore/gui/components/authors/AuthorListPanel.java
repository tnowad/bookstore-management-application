package com.bookstore.gui.components.authors;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.models.AuthorModel;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AuthorListPanel extends javax.swing.JPanel {

  private static AuthorListPanel instance;
  AuthorBUS authorBUS = AuthorBUS.getInstance();
  List<AuthorModel> listAuthor = authorBUS.getAllModels();

  public AuthorListPanel() {
    initComponents();
    addTable();
  }

  public static AuthorListPanel getInstance() {
    if (instance == null) {
      instance = new AuthorListPanel();
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

    title.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
    title.setText("List Author");
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

    serialText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    serialText.setText("Serial");
    panel.add(serialText);

    idText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    idText.setText("Id");
    panel.add(idText);

    headerTable.add(panel);

    nameText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    nameText.setText("Name");
    headerTable.add(nameText);

    descriptionText.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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
    for (AuthorModel author : listAuthor) {
      AuthorPanel publisherPanel = new AuthorPanel(serial, author);
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
