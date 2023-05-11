package com.bookstore.gui.forms.authors;

import com.bookstore.bus.AuthorBUS;
import com.bookstore.interfaces.ISearchable;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class AuthorListPanel extends JPanel implements ISearchable {

  private static AuthorListPanel instance;

  private JButton buttonAdd;
  private JPanel buttonsPanel;
  private JPanel contend;
  private JPanel contendTable;
  private JLabel descriptionText;
  private JPanel headerTable;
  private JLabel idText;
  private JLabel nameText;
  private JScrollPane scrollPane;
  private JLabel serialText;
  private JPanel table;
  private JLabel title;
  private JPanel panel;

  AuthorBUS authorBUS = AuthorBUS.getInstance();
  List<AuthorModel> listAuthor = authorBUS.getAllModels();

  public AuthorListPanel() {
    initComponents();
    addTable(listAuthor);
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
    table = new JPanel();
    headerTable = new JPanel();
    serialText = new JLabel();
    idText = new JLabel();
    nameText = new JLabel();
    descriptionText = new JLabel();
    scrollPane = new JScrollPane();
    contendTable = new JPanel();
    panel = new JPanel();

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

    contend.add(buttonsPanel, BorderLayout.PAGE_START);

    table.setLayout(new BorderLayout());

    headerTable.setLayout(new GridLayout(1, 3));

    panel.setLayout(new GridLayout());

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
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);

    table.add(scrollPane, BorderLayout.CENTER);

    contend.add(table, BorderLayout.CENTER);

    add(contend, BorderLayout.CENTER);
  }

  public void addTable(List<AuthorModel> listAuthor) {
    contendTable.removeAll();
    contendTable.setLayout(new GridLayout(0, 1, 0, 15));
    int serial = 1;
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
      AddAuthorForm addAuthor = new AddAuthorForm();
      addAuthor.setVisible(true);
    }
  };

  @Override
  public void search(String keyword) {
    contendTable.removeAll();
    if (keyword == null || keyword.isBlank()) {
      JOptionPane.showMessageDialog(
        null,
        "Please enter your search information!"
      );
      addTable(listAuthor);
      this.revalidate();
      this.repaint();
    } else {
      List<AuthorModel> newList = AuthorBUS
        .getInstance()
        .searchModel(keyword, new String[] { "name" });
      if (newList.isEmpty()) {
        JOptionPane.showMessageDialog(
          null,
          "The information you entered could not be found!"
        );
        addTable(listAuthor);
        this.revalidate();
        this.repaint();
      } else {
        addTable(newList);
        this.revalidate();
        this.repaint();
      }
    }
  }
}
