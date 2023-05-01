package com.bookstore.gui.form.customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CartPanel extends JPanel {

  public CartPanel() {
    initComponents();
  }

  private void initComponents() {
    listCartPanel = new JPanel();
    listCartScrollPane = new JScrollPane();
    listCartTable = new JTable();
    groupBottomPanel = new JPanel();
    groupTotalCostPanel = new JPanel();
    totalCostLabel = new JLabel();
    totalPriceTextField = new JTextField();
    groupActionPanel = new JPanel();
    chooseAllCheckBox = new JCheckBox();
    deleteAllProductsButton = new JButton();
    proceedToCheckoutButton = new JButton();

    setLayout(new BorderLayout());

    listCartPanel.setLayout(new BorderLayout());

    listCartTable.setModel(
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
    listCartTable.setPreferredSize(new Dimension(350, 80));
    listCartScrollPane.setViewportView(listCartTable);

    listCartPanel.add(listCartScrollPane, BorderLayout.CENTER);

    add(listCartPanel, BorderLayout.CENTER);

    groupBottomPanel.setLayout(new GridLayout(2, 1));

    groupTotalCostPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    totalCostLabel.setFont(new Font("Arial", 0, 14)); // NOI18N
    totalCostLabel.setText("Total cost:");
    totalCostLabel.setPreferredSize(new Dimension(75, 30));
    groupTotalCostPanel.add(totalCostLabel);

    totalPriceTextField.setEditable(false);
    totalPriceTextField.setPreferredSize(new Dimension(200, 30));
    totalPriceTextField.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          totalPriceTextFieldActionPerformed(evt);
        }
      }
    );
    groupTotalCostPanel.add(totalPriceTextField);

    groupBottomPanel.add(groupTotalCostPanel);

    groupActionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    chooseAllCheckBox.setFont(new Font("Arial", 0, 14)); // NOI18N
    chooseAllCheckBox.setText("Choose all");
    chooseAllCheckBox.setPreferredSize(new Dimension(100, 30));
    chooseAllCheckBox.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          chooseAllCheckBoxActionPerformed(evt);
        }
      }
    );
    groupActionPanel.add(chooseAllCheckBox);

    deleteAllProductsButton.setFont(new Font("Arial", 0, 18)); // NOI18N
    deleteAllProductsButton.setText("Delete all products");
    deleteAllProductsButton.setMinimumSize(new Dimension(179, 30));
    deleteAllProductsButton.setPreferredSize(new Dimension(190, 30));
    deleteAllProductsButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          deleteAllProductsButtonActionPerformed(evt);
        }
      }
    );
    groupActionPanel.add(deleteAllProductsButton);

    proceedToCheckoutButton.setFont(new Font("Arial", 0, 18)); // NOI18N
    proceedToCheckoutButton.setText("Proceed to checkout");
    proceedToCheckoutButton.setMaximumSize(new Dimension(200, 30));
    proceedToCheckoutButton.setMinimumSize(new Dimension(200, 30));
    proceedToCheckoutButton.setPreferredSize(new Dimension(200, 30));
    proceedToCheckoutButton.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          proceedToCheckoutButtonActionPerformed(evt);
        }
      }
    );
    groupActionPanel.add(proceedToCheckoutButton);

    groupBottomPanel.add(groupActionPanel);

    add(groupBottomPanel, BorderLayout.SOUTH);
  }

  private void totalPriceTextFieldActionPerformed(ActionEvent evt) {}

  private void deleteAllProductsButtonActionPerformed(ActionEvent evt) {}

  private void proceedToCheckoutButtonActionPerformed(ActionEvent evt) {}

  private void chooseAllCheckBoxActionPerformed(ActionEvent evt) {}

  private JCheckBox chooseAllCheckBox;
  private JButton deleteAllProductsButton;
  private JPanel groupActionPanel;
  private JPanel groupBottomPanel;
  private JPanel groupTotalCostPanel;
  private JPanel listCartPanel;
  private JScrollPane listCartScrollPane;
  private JTable listCartTable;
  private JButton proceedToCheckoutButton;
  private JLabel totalCostLabel;
  private JTextField totalPriceTextField;
}
