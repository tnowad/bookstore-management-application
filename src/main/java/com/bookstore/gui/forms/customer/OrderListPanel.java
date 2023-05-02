package com.bookstore.gui.forms.customer;

import java.awt.*;
import javax.swing.*;

public class OrderListPanel extends JPanel {
  private static OrderListPanel instance;

  private OrderListPanel() {
    initComponents();
  }

  public static OrderListPanel getInstance() {
    if (instance == null) {
      instance = new OrderListPanel();
    }
    return instance;
  }

  private void initComponents() {
    viewBooksJScrollPane = new JScrollPane();
    viewbooksPanel = new JPanel();
    deliveryStatusPanel = new JPanel();
    deliveryStatusField = new JTextField();
    buttonPanel = new JPanel();
    viewOrderDetailsButton = new JButton();
    getcheckoutReceiptButton = new JButton();

    setMaximumSize(new Dimension(530, 170));
    setMinimumSize(new Dimension(530, 170));
    setLayout(new BorderLayout());

    viewbooksPanel.setLayout(new GridLayout(3, 1, 0, 5));
    viewBooksJScrollPane.setViewportView(viewbooksPanel);

    add(viewBooksJScrollPane, BorderLayout.CENTER);

    deliveryStatusPanel.setLayout(new CardLayout());

    deliveryStatusField.setEditable(false);
    deliveryStatusField.setFont(new Font("Arial", 0, 14)); // NOI18N
    deliveryStatusField.setText("Delivery Status:");

    deliveryStatusPanel.add(deliveryStatusField, "card2");

    add(deliveryStatusPanel, BorderLayout.PAGE_START);

    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 2));

    viewOrderDetailsButton.setText("View Order Details");

    buttonPanel.add(viewOrderDetailsButton);

    getcheckoutReceiptButton.setText("Get Checkout Receipt");

    buttonPanel.add(getcheckoutReceiptButton);

    add(buttonPanel, BorderLayout.PAGE_END);
  }

  private JPanel buttonPanel;
  private JTextField deliveryStatusField;
  private JPanel deliveryStatusPanel;
  private JButton getcheckoutReceiptButton;
  private JScrollPane viewBooksJScrollPane;
  private JButton viewOrderDetailsButton;
  private JPanel viewbooksPanel;
}
