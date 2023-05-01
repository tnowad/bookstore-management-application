package com.bookstore.gui.form.customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class OrderListPanel extends JPanel {

  public OrderListPanel() {
    initComponents();
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
    deliveryStatusField.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          deliveryStatusFieldActionPerformed(evt);
        }
      }
    );
    deliveryStatusPanel.add(deliveryStatusField, "card2");

    add(deliveryStatusPanel, BorderLayout.PAGE_START);

    buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 2));

    viewOrderDetailsButton.setText("View Order Details");
    viewOrderDetailsButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          viewOrderDetailsButtonActionPerformed(evt);
        }
      }
    );
    buttonPanel.add(viewOrderDetailsButton);

    getcheckoutReceiptButton.setText("Get Checkout Receipt");
    getcheckoutReceiptButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          getcheckoutReceiptButtonActionPerformed(evt);
        }
      }
    );
    buttonPanel.add(getcheckoutReceiptButton);

    add(buttonPanel, BorderLayout.PAGE_END);
  }

  private void viewOrderDetailsButtonActionPerformed(ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void deliveryStatusFieldActionPerformed(ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void getcheckoutReceiptButtonActionPerformed(ActionEvent evt) {
    // TODO add your handling code here:
  }

  private JPanel buttonPanel;
  private JTextField deliveryStatusField;
  private JPanel deliveryStatusPanel;
  private JButton getcheckoutReceiptButton;
  private JScrollPane viewBooksJScrollPane;
  private JButton viewOrderDetailsButton;
  private JPanel viewbooksPanel;
}
