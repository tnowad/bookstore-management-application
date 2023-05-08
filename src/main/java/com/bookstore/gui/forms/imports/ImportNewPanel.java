package com.bookstore.gui.forms.imports;

import com.bookstore.gui.components.panels.MainPanel;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

public class ImportNewPanel extends JPanel {

  private JPanel actionPanel;
  private JButton addBookButton;
  private JLabel authorLabel;
  private JTextField authorTextField;
  private JPanel bookFormPanel;
  private JPanel bookInformationPanel;
  private JLabel bookIsbnLabel;
  private JTextField bookIsbnTextField;
  private JPanel bookListPanel;
  private JScrollPane bookListScrollPane;
  private JTable bookListTable;
  private JLabel categoriesLabel;
  private JTextField categoriesTextField;
  private JButton exitButton;
  private JPanel importFormPanel;
  private JLabel importIdLabel;
  private JTextField importIdTextField;
  private JLabel priceLabel;
  private JTextField priceTextField;
  private JLabel providerLabel;
  private JTextField providerTextField;
  private JLabel quantityLabel;
  private JTextField quantityTextField;
  private JLabel titleBookLabel;
  private JTextField titleBookTextfield;
  private JLabel titleLabel;
  private JLabel totalPriceLabel;
  private JButton backToPreviousButton;

  public ImportNewPanel() {
    initComponents();
    handleEvent();
  }

  private void handleEvent() {
    backToPreviousButton.addActionListener(e -> {
      MainPanel.getInstance().backToPreviousForm();
    });
    bookIsbnTextField
      .getDocument()
      .addDocumentListener(
        new DocumentListener() {
          @Override
          public void insertUpdate(DocumentEvent e) {
            System.out.println("Insert");
          }

          @Override
          public void removeUpdate(DocumentEvent e) {
           System.out.println("Remove");
          }

          @Override
          public void changedUpdate(DocumentEvent e) {
            System.out.println("Change");
          }
        }
      );
  }

  private void initComponents() {
    GridBagConstraints gridBagConstraints;

    titleLabel = new JLabel();
    importFormPanel = new JPanel();
    importIdLabel = new JLabel();
    importIdTextField = new JTextField();
    providerLabel = new JLabel();
    providerTextField = new JTextField();
    bookFormPanel = new JPanel();
    bookInformationPanel = new JPanel();
    bookIsbnLabel = new JLabel();
    bookIsbnTextField = new JTextField();
    titleBookLabel = new JLabel();
    titleBookTextfield = new JTextField();
    quantityLabel = new JLabel();
    quantityTextField = new JTextField();
    categoriesLabel = new JLabel();
    categoriesTextField = new JTextField();
    priceLabel = new JLabel();
    priceTextField = new JTextField();
    authorLabel = new JLabel();
    authorTextField = new JTextField();
    addBookButton = new JButton();
    bookListPanel = new JPanel();
    bookListScrollPane = new JScrollPane();
    bookListTable = new JTable();
    actionPanel = new JPanel();
    exitButton = new JButton();
    totalPriceLabel = new JLabel();
    backToPreviousButton = new JButton("Back to previous");

    setLayout(new GridBagLayout());

    gridBagConstraints = new java.awt.GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
    add(backToPreviousButton, gridBagConstraints);

    titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
    titleLabel.setFont(new Font("Segoe UI", 0, 36));
    titleLabel.setText("Book list import");
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(titleLabel, gridBagConstraints);

    importFormPanel.setBorder(
      BorderFactory.createTitledBorder("Book Information")
    );
    importFormPanel.setLayout(new GridLayout(2, 2, 2, 0));

    importIdLabel.setText("Import ID");
    importFormPanel.add(importIdLabel);
    importFormPanel.add(importIdTextField);

    providerLabel.setText("Provider ");
    importFormPanel.add(providerLabel);

    importFormPanel.add(providerTextField);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(importFormPanel, gridBagConstraints);

    bookFormPanel.setBorder(
      BorderFactory.createTitledBorder("Book Information")
    );
    bookFormPanel.setLayout(new BorderLayout());

    bookInformationPanel.setLayout(new GridLayout(6, 2));

    bookIsbnLabel.setText("Book ISBN");
    bookInformationPanel.add(bookIsbnLabel);
    bookInformationPanel.add(bookIsbnTextField);

    titleBookLabel.setText("Name book");
    bookInformationPanel.add(titleBookLabel);

    bookInformationPanel.add(titleBookTextfield);

    quantityLabel.setText("Quantity");
    bookInformationPanel.add(quantityLabel);
    bookInformationPanel.add(quantityTextField);

    categoriesLabel.setText("Category");
    bookInformationPanel.add(categoriesLabel);
    bookInformationPanel.add(categoriesTextField);

    priceLabel.setText("Price");
    bookInformationPanel.add(priceLabel);

    bookInformationPanel.add(priceTextField);

    authorLabel.setText("Author");
    bookInformationPanel.add(authorLabel);

    bookInformationPanel.add(authorTextField);

    bookFormPanel.add(bookInformationPanel, BorderLayout.CENTER);

    addBookButton.setText("Add book");
    bookFormPanel.add(addBookButton, BorderLayout.PAGE_END);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 3;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(bookFormPanel, gridBagConstraints);

    bookListPanel.setBorder(BorderFactory.createTitledBorder("Import detail"));
    bookListPanel.setLayout(new BoxLayout(bookListPanel, BoxLayout.LINE_AXIS));

    bookListTable.setModel(
      new DefaultTableModel(
        new Object[][] {
          { null, null, null, null },
          { null, null, null, null },
          { null, null, null, null },
          { null, null, null, null },
        },
        new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }
      )
    );
    bookListScrollPane.setViewportView(bookListTable);

    bookListPanel.add(bookListScrollPane);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 4;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(bookListPanel, gridBagConstraints);

    actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 5));

    exitButton.setText("Exit");
    actionPanel.add(exitButton);

    totalPriceLabel.setText("Total price");
    actionPanel.add(totalPriceLabel);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 5;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.weightx = 1.0;
    add(actionPanel, gridBagConstraints);
  }
}
