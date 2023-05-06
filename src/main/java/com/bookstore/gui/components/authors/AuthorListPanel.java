package com.bookstore.gui.components.authors;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.gui.forms.authors.AddAuthor;
import com.bookstore.gui.forms.authors.AuthorPanel;
import com.bookstore.models.AuthorModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AuthorListPanel extends JPanel {

  private static AuthorListPanel instance;

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

    setPreferredSize(new Dimension(720, 444));
    setLayout(new BorderLayout());

    title.setFont(new Font("Segoe UI", 1, 18));
    title.setText("List Author");
    add(title, BorderLayout.PAGE_START);

    contend.setLayout(new BorderLayout());

    buttonsPanel.setPreferredSize(new Dimension(720, 50));
    buttonsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 10, 10));

    buttonAdd.setText("Add");
    buttonAdd.setPreferredSize(new Dimension(75, 30));
    buttonAdd.addActionListener(actionAdd);
    buttonsPanel.add(buttonAdd);

    buttonDelete.setText("Delete");
    buttonDelete.setPreferredSize(new Dimension(75, 30));
    buttonsPanel.add(buttonDelete);

    contend.add(buttonsPanel, BorderLayout.PAGE_START);

    table.setLayout(new BorderLayout());

    headerTable.setLayout(new GridLayout(1, 3));

    panel.setLayout(new GridLayout(1, 3));
    panel.add(label);

    serialText.setFont(new Font("Segoe UI", 0, 16));
    serialText.setText("Serial");
    panel.add(serialText);

    idText.setFont(new Font("Segoe UI", 0, 16));
    idText.setText("Id");
    panel.add(idText);

    headerTable.add(panel);

    nameText.setFont(new Font("Segoe UI", 0, 16));
    nameText.setText("Name");
    headerTable.add(nameText);

    descriptionText.setFont(new Font("Segoe UI", 0, 16));
    descriptionText.setText("Description");
    headerTable.add(descriptionText);

    table.add(headerTable, BorderLayout.NORTH);

    scrollPane.setViewportView(contendTable);

    table.add(scrollPane, BorderLayout.CENTER);

    contend.add(table, BorderLayout.CENTER);

    add(contend, BorderLayout.CENTER);
  }

  public void addTable() {
    contendTable.removeAll();
    contendTable.setLayout(new GridLayout(0, 1, 0, 15));
    int serial = 0;
    for (AuthorModel author : listAuthor) {
      AuthorPanel publisherPanel = new AuthorPanel(serial, author);
      contendTable.add(publisherPanel);
      serial = serial + 1;
    }
    contendTable.revalidate();
    contendTable.repaint();
  }

  public ActionListener actionAdd = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent e) {
      AddAuthor addAuthor = new AddAuthor();
      addAuthor.setVisible(true);
    }
    
  };
}
