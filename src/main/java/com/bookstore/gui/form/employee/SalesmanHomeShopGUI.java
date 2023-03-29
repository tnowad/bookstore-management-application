package com.bookstore.gui.form.employee;

import javax.swing.*;
import java.awt.*;

public class SalesmanHomeShopGUI extends JFrame {
  private JPanel productListPanel, productDetailsPanel, salesRecordsPanel;
  private JTable productTable, salesTable;
  private JTextField searchField;
  private JButton checkoutButton;
  private JMenuBar menuBar;
  private JMenu fileMenu, helpMenu;
  private JMenuItem exitMenuItem, aboutMenuItem;

  public SalesmanHomeShopGUI() {
    // Set up the main window
    setTitle("Bookstore");
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setSize(800, 600);

    // Set up the menu bar
    menuBar = new JMenuBar();
    fileMenu = new JMenu("File");
    exitMenuItem = new JMenuItem("Exit");
    fileMenu.add(exitMenuItem);
    helpMenu = new JMenu("Help");
    aboutMenuItem = new JMenuItem("About");
    helpMenu.add(aboutMenuItem);
    menuBar.add(fileMenu);
    menuBar.add(helpMenu);
    setJMenuBar(menuBar);

    // Set up the product list panel
    productListPanel = new JPanel(new BorderLayout());
    productListPanel.setBorder(BorderFactory.createTitledBorder("Available Books"));
    searchField = new JTextField();
    JPanel searchPanel = new JPanel(new BorderLayout());
    searchPanel.add(new JLabel("Filter books by title or author: "), BorderLayout.WEST);
    searchPanel.add(searchField, BorderLayout.CENTER);
    productListPanel.add(searchPanel, BorderLayout.NORTH);
    String[] columnNames = { "ISBN", "Title", "Author", "Price", "Quantity", "Status" };
    Object[][] data = { { "1234567890", "Book 1", "Author 1", "$10.00", "100", "AVAILABLE" },
        { "0987654321", "Book 2", "Author 2", "$20.00", "50", "UNAVAILABLE" },
        { "2468101214", "Book 3", "Author 3", "$15.00", "75", "AVAILABLE" } };
    productTable = new JTable(data, columnNames);
    JScrollPane scrollPane = new JScrollPane(productTable);
    productListPanel.add(scrollPane, BorderLayout.CENTER);

    // Set up the product details panel
    productDetailsPanel = new JPanel();
    productDetailsPanel.setBorder(BorderFactory.createTitledBorder("Book Details"));
    productDetailsPanel.setLayout(new BoxLayout(productDetailsPanel, BoxLayout.Y_AXIS));

    JTextField isbnField = new JTextField();
    isbnField.setEditable(false);
    isbnField.setFocusable(false);
    JTextField titleField = new JTextField();
    titleField.setEditable(false);
    JTextField descriptionField = new JTextField();
    descriptionField.setEditable(false);
    JTextField imageField = new JTextField();
    imageField.setEditable(false);
    JTextField priceField = new JTextField();
    priceField.setEditable(false);
    JTextField quantityField = new JTextField();
    quantityField.setEditable(false);

    JLabel isbnLabel = new JLabel("ISBN: ");
    isbnLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    productDetailsPanel.add(isbnLabel);
    productDetailsPanel.add(isbnField);
    productDetailsPanel.add(Box.createVerticalStrut(10));

    JLabel titleLabel = new JLabel("Title: ");
    titleLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    productDetailsPanel.add(titleLabel);
    productDetailsPanel.add(titleField);
    productDetailsPanel.add(Box.createVerticalStrut(10));

    JLabel descriptionLabel = new JLabel("Description: ");
    descriptionLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    productDetailsPanel.add(descriptionLabel);
    productDetailsPanel.add(descriptionField);
    productDetailsPanel.add(Box.createVerticalStrut(10));

    JLabel imageLabel = new JLabel("Image: ");
    imageLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    productDetailsPanel.add(imageLabel);
    productDetailsPanel.add(imageField);
    productDetailsPanel.add(Box.createVerticalStrut(10));

    JLabel priceLabel = new JLabel("Price: ");
    priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    productDetailsPanel.add(priceLabel);
    productDetailsPanel.add(priceField);
    productDetailsPanel.add(Box.createVerticalStrut(10));

    JLabel quantityLabel = new JLabel("Quantity: ");
    quantityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    productDetailsPanel.add(quantityLabel);
    productDetailsPanel.add(quantityField);
    productDetailsPanel.add(Box.createVerticalStrut(10));

    JLabel statusLabel = new JLabel("Status: ");
    statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
    productDetailsPanel.add(statusLabel);
    JComboBox<String> statusComboBox = new JComboBox<>(new String[] { "AVAILABLE", "UNAVAILABLE", "DELETED" });
    productDetailsPanel.add(statusComboBox);

    // Set up the checkout button
    checkoutButton = new JButton("Checkout");
    checkoutButton.addActionListener(e -> {
      // Get the selected book from the product table
      int selectedRow = productTable.getSelectedRow();
      if (selectedRow == -1) {
        JOptionPane.showMessageDialog(SalesmanHomeShopGUI.this, "Please select a book to checkout.");
        return;
      }
      String isbn = (String) productTable.getValueAt(selectedRow, 0);
      String title = (String) productTable.getValueAt(selectedRow, 1);
      String price = (String) productTable.getValueAt(selectedRow, 3);

      // Display a dialog box asking for customer information
      JPanel customerInfoPanel = new JPanel(new GridLayout(3, 2));
      JTextField nameField = new JTextField();
      JTextField addressField = new JTextField();
      JTextField phoneField = new JTextField();
      customerInfoPanel.add(new JLabel("Name: "));
      customerInfoPanel.add(nameField);
      customerInfoPanel.add(new JLabel("Address: "));
      customerInfoPanel.add(addressField);
      customerInfoPanel.add(new JLabel("Phone: "));
      customerInfoPanel.add(phoneField);
      int result = JOptionPane.showConfirmDialog(SalesmanHomeShopGUI.this, customerInfoPanel,
          "Enter Customer Information", JOptionPane.OK_CANCEL_OPTION);
      if (result == JOptionPane.OK_OPTION) {
        // Update the sales records panel with the new sale
        String date = "1/1/2020"; // TODO: Get the current date
        String quantity = quantityField.getText();
        String totalPrice = "$" + Integer.parseInt(quantity) * Double.parseDouble(price.substring(1));
        Object[] newRow = { date, isbn, title, quantity, totalPrice };
        ((javax.swing.table.DefaultTableModel) salesTable.getModel()).addRow(newRow);
        JOptionPane.showMessageDialog(SalesmanHomeShopGUI.this, "Sale completed successfully!");
      }
    });
    productDetailsPanel.add(checkoutButton);

    // Set up the sales records panel
    salesRecordsPanel = new JPanel(new BorderLayout());
    salesRecordsPanel.setBorder(BorderFactory.createTitledBorder("Sales Records"));
    JPanel dateRangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    dateRangePanel.add(new JLabel("Date Range: "));
    dateRangePanel.add(new JSpinner(new SpinnerDateModel()));
    dateRangePanel.add(new JLabel("to"));
    dateRangePanel.add(new JSpinner(new SpinnerDateModel()));
    salesRecordsPanel.add(dateRangePanel, BorderLayout.NORTH);
    String[] salesColumnNames = { "Date", "ISBN", "Title", "Quantity", "Total Price" };
    Object[][] salesData = { { "1/1/2020", "1234567890", "Book 1", "10", "$100.00" },
        { "1/2/2020", "0987654321", "Book 2", "5", "$100.00" },
        { "1/3/2020", "2468101214", "Book 3", "7", "$105.00" } };
    salesTable = new JTable(new javax.swing.table.DefaultTableModel(salesData, salesColumnNames) {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    });
    JScrollPane salesScrollPane = new JScrollPane(salesTable);
    salesRecordsPanel.add(salesScrollPane, BorderLayout.CENTER);

    // Add all panels to the main window
    JPanel mainPanel = new JPanel(new BorderLayout());
    mainPanel.add(productListPanel, BorderLayout.WEST);
    mainPanel.add(productDetailsPanel, BorderLayout.CENTER);
    mainPanel.add(salesRecordsPanel, BorderLayout.SOUTH);
    add(mainPanel);

    // Show the window
    setVisible(true);

    // Add a listener to the product table to update the product details panel when
    // a book is selected
    productTable.getSelectionModel().addListSelectionListener(e -> {
      int selectedRow = productTable.getSelectedRow();
      if (selectedRow == -1) {
        isbnField.setText("");
        titleField.setText("");
        descriptionField.setText("");
        imageField.setText("");
        priceField.setText("");
        quantityField.setText("");
        statusComboBox.setSelectedIndex(0);
      } else {
        isbnField.setText((String) productTable.getValueAt(selectedRow, 0));
        titleField.setText((String) productTable.getValueAt(selectedRow, 1));
        descriptionField.setText(""); // TODO: Get the book's description from a database
        imageField.setText(""); // TODO: Get the book's image URL from a database
        priceField.setText((String) productTable.getValueAt(selectedRow, 3));
        quantityField.setText("1");
        statusComboBox.setSelectedItem(productTable.getValueAt(selectedRow, 5));
      }
    });
  }

}
