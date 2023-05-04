package com.bookstore.gui.components.authors;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.models.AuthorModel;
import java.util.List;

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
    title = new javax.swing.JLabel();
    contend = new javax.swing.JPanel();
    buttonsPanel = new javax.swing.JPanel();
    buttonAdd = new javax.swing.JButton();
    buttonDelete = new javax.swing.JButton();
    table = new javax.swing.JPanel();
    headerTable = new javax.swing.JPanel();
    panel = new javax.swing.JPanel();
    label = new javax.swing.JLabel();
    serialText = new javax.swing.JLabel();
    idText = new javax.swing.JLabel();
    nameText = new javax.swing.JLabel();
    descriptionText = new javax.swing.JLabel();
    scrollPane = new javax.swing.JScrollPane();
    contendTable = new javax.swing.JPanel();

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
    contendTable.setLayout(new java.awt.GridLayout(0, 1,0,15));
    int serial = 0;
    for (AuthorModel author : listAuthor) {
      AuthorPanel publisherPanel = new AuthorPanel(serial, author);
      contendTable.add(publisherPanel);
      serial = serial+1;
    }
    contendTable.revalidate();
    contendTable.repaint();
  }

  private javax.swing.JButton buttonAdd;
  private javax.swing.JButton buttonDelete;
  private javax.swing.JPanel buttonsPanel;
  private javax.swing.JPanel contend;
  private javax.swing.JPanel contendTable;
  private javax.swing.JLabel descriptionText;
  private javax.swing.JPanel headerTable;
  private javax.swing.JLabel idText;
  private javax.swing.JLabel label;
  private javax.swing.JLabel nameText;
  private javax.swing.JPanel panel;
  private javax.swing.JScrollPane scrollPane;
  private javax.swing.JLabel serialText;
  private javax.swing.JPanel table;
  private javax.swing.JLabel title;
}
