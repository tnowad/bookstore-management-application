package com.bookstore.gui.forms.publishers;

import com.bookstore.bus.PublisherBUS;
import com.bookstore.gui.components.publishers.PublisherItemPanel;
import com.bookstore.interfaces.ISearchable;
import com.bookstore.models.PublisherModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;

public class PublisherListPanel extends JPanel implements ISearchable {

  private JButton buttonAdd;
  private JPanel buttonsPanel;
  private JPanel contend;
  private JPanel contendTable;
  private JLabel descriptionText;
  private JPanel headerTable;
  private JLabel idText;
  private JLabel nameText;
  private JPanel panel;
  private JScrollPane scrollPane;
  private JLabel serialText;
  private JPanel table;
  private JLabel title;
  private static PublisherListPanel instance;
  PublisherBUS publisherBUS = PublisherBUS.getInstance();
  List<PublisherModel> listPublisher = publisherBUS.getAllModels();

  public PublisherListPanel() {
    initComponents();
    addTable(listPublisher);
  }

  public static PublisherListPanel getInstance() {
    if (instance == null) {
      instance = new PublisherListPanel();
    }
    return instance;
  }

  private void initComponents() {
    title = new JLabel();
    setBackground(Color.WHITE);
    contend = new JPanel();
    contend.setBackground(Color.WHITE);
    buttonsPanel = new JPanel();
    buttonsPanel.setBackground(Color.WHITE);
    buttonAdd = new JButton();
    table = new JPanel();
    table.setBackground(Color.WHITE);
    headerTable = new JPanel();
    serialText = new JLabel();
    idText = new JLabel();
    nameText = new JLabel();
    descriptionText = new JLabel();
    scrollPane = new JScrollPane();
    contendTable = new JPanel();
    contendTable.setBackground(Color.WHITE);
    panel = new JPanel();
    panel.setBackground(Color.WHITE);

    setPreferredSize(new Dimension(720, 444));
    setLayout(new BorderLayout());

    title.setFont(new Font("Segoe UI", 3, 18));
    title.setForeground(new Color(255, 0, 51));
    title.setText("List Publisher");
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

  public void addTable(List<PublisherModel> listPublisher) {
    contendTable.removeAll();
    contendTable.setLayout(new GridLayout(0, 1, 0, 15));
    int serial = 1;
    for (PublisherModel publisher : listPublisher) {
      PublisherItemPanel publisherPanel = new PublisherItemPanel(
        serial,
        publisher
      );
      contendTable.add(publisherPanel);
      serial = serial + 1;
    }
    contendTable.revalidate();
    contendTable.repaint();
  }

  public ActionListener actionAdd = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      AddPublisherForm addPublisher = new AddPublisherForm();
      addPublisher.setVisible(true);
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
      addTable(listPublisher);
      this.revalidate();
      this.repaint();
    } else {
      List<PublisherModel> newList = PublisherBUS
        .getInstance()
        .searchModel(keyword, new String[] { "name" });
      if (newList.isEmpty()) {
        JOptionPane.showMessageDialog(
          null,
          "The information you entered could not be found!"
        );
        addTable(listPublisher);
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
