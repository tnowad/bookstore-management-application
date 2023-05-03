package com.bookstore.gui.forms.customer;

import java.awt.*;
import javax.swing.*;

public class Discovery extends JPanel {

  private static Discovery instance;

  private Discovery() {
    initComponents();
  }

  public static Discovery getInstance() {
    if (instance == null) {
      instance = new Discovery();
    }
    return instance;
  }

  private void initComponents() {
    headerPanel = new JPanel();
    sortByLabel = new JLabel();
    sortByConditionComboBox = new JComboBox<>();
    categoryLabel = new JLabel();
    categoryListComboBox = new JComboBox<>();
    searchTextField = new JTextField();
    cartButtonTextField = new JButton();
    bookListScrollPane = new JScrollPane();
    bookListPanel = new JPanel();

    setMaximumSize(new Dimension(800, 530));
    setMinimumSize(new Dimension(800, 530));
    setLayout(new BorderLayout());

    sortByLabel.setFont(new Font("Arial", 0, 14));
    sortByLabel.setText("Sort By:");
    sortByLabel.setHorizontalTextPosition(SwingConstants.CENTER);
    sortByLabel.setMaximumSize(new Dimension(50, 30));
    sortByLabel.setMinimumSize(new Dimension(50, 30));
    sortByLabel.setPreferredSize(new Dimension(50, 30));
    headerPanel.add(sortByLabel);

    sortByConditionComboBox.setFont(new Font("Arial", 0, 14));
    sortByConditionComboBox.setModel(
      new DefaultComboBoxModel<>(
        new String[] {
          "Recommendation",
          "Price: low -> high",
          "Price: high -> low",
        }
      )
    );
    sortByConditionComboBox.setMaximumSize(new Dimension(150, 30));
    sortByConditionComboBox.setMinimumSize(new Dimension(150, 30));
    sortByConditionComboBox.setPreferredSize(new Dimension(150, 30));

    headerPanel.add(sortByConditionComboBox);

    categoryLabel.setFont(new Font("Arial", 0, 14));
    categoryLabel.setText("Category:");
    categoryLabel.setPreferredSize(new Dimension(62, 30));
    headerPanel.add(categoryLabel);

    categoryListComboBox.setModel(
      new DefaultComboBoxModel<>(new String[] { "All" })
    );
    categoryListComboBox.setMaximumSize(new Dimension(100, 30));
    categoryListComboBox.setMinimumSize(new Dimension(100, 30));
    categoryListComboBox.setPreferredSize(new Dimension(120, 30));

    headerPanel.add(categoryListComboBox);

    searchTextField.setFont(new Font("Segoe UI", 0, 14));
    searchTextField.setText("Search anything here..");
    searchTextField.setMaximumSize(new Dimension(300, 30));
    searchTextField.setMinimumSize(new Dimension(300, 30));
    searchTextField.setPreferredSize(new Dimension(300, 30));

    headerPanel.add(searchTextField);

    cartButtonTextField.setText("Cart");
    cartButtonTextField.setPreferredSize(new Dimension(80, 30));

    headerPanel.add(cartButtonTextField);

    add(headerPanel, BorderLayout.PAGE_START);

    bookListPanel.setLayout(new GridLayout(3, 5));
    bookListScrollPane.setViewportView(bookListPanel);

    add(bookListScrollPane, BorderLayout.CENTER);
  }

  private JPanel bookListPanel;
  private JScrollPane bookListScrollPane;
  private JButton cartButtonTextField;
  private JLabel categoryLabel;
  private JComboBox<String> categoryListComboBox;
  private JPanel headerPanel;
  private JTextField searchTextField;
  private JComboBox<String> sortByConditionComboBox;
  private JLabel sortByLabel;
}
