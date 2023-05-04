package com.bookstore.gui.forms.customer;

import java.awt.*;
import javax.swing.*;

public class CheckoutUI extends javax.swing.JFrame {

  public CheckoutUI() {
    initComponents();
  }

  private void initComponents() {
    jTextField6 = new javax.swing.JTextField();
    groupHeaderPanel = new javax.swing.JPanel();
    nameLabel = new javax.swing.JLabel();
    nameTextField = new javax.swing.JTextField();
    emailLabel = new javax.swing.JLabel();
    emailTextField = new javax.swing.JTextField();
    addressLabel = new javax.swing.JLabel();
    addressTextField = new javax.swing.JTextField();
    phoneLabel = new javax.swing.JLabel();
    phoneTextField = new javax.swing.JTextField();
    groupContentPanel = new javax.swing.JPanel();
    groupPaymentMethodPanel = new javax.swing.JPanel();
    paymentMethodLabel = new javax.swing.JLabel();
    jRadioButton1 = new javax.swing.JRadioButton();
    jRadioButton2 = new javax.swing.JRadioButton();
    shippingMethodLabel = new javax.swing.JLabel();
    internationalShippingRadioButton = new javax.swing.JRadioButton();
    standardShippingRadioButton = new javax.swing.JRadioButton();
    expressShippingRadioButton = new javax.swing.JRadioButton();
    nextDayShippingRadioButton = new javax.swing.JRadioButton();
    groupCreditCardPanel = new javax.swing.JPanel();
    cardNumberLabel = new javax.swing.JLabel();
    cardNumberTextField = new javax.swing.JTextField();
    expirationDateLabel = new javax.swing.JLabel();
    expirationDateTextField = new javax.swing.JTextField();
    cvvLabel = new javax.swing.JLabel();
    cvvTextField = new javax.swing.JTextField();
    groupTableProductPanel = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    productListTable = new javax.swing.JTable();
    groupButtonPanel = new javax.swing.JPanel();
    checkoutButton = new javax.swing.JButton();
    exitButton = new javax.swing.JButton();

    jTextField6.setText("jTextField6");

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setPreferredSize(new java.awt.Dimension(500, 409));

    groupHeaderPanel.setLayout(new java.awt.GridLayout(4, 2));

    nameLabel.setText("jLabel1");
    groupHeaderPanel.add(nameLabel);

    nameTextField.setPreferredSize(new java.awt.Dimension(50, 22));

    groupHeaderPanel.add(nameTextField);

    emailLabel.setText("jLabel2");
    groupHeaderPanel.add(emailLabel);
    groupHeaderPanel.add(emailTextField);

    addressLabel.setText("jLabel3");
    groupHeaderPanel.add(addressLabel);
    groupHeaderPanel.add(addressTextField);

    phoneLabel.setText("jLabel4");
    groupHeaderPanel.add(phoneLabel);

    groupHeaderPanel.add(phoneTextField);

    getContentPane().add(groupHeaderPanel, java.awt.BorderLayout.PAGE_START);

    groupContentPanel.setPreferredSize(new java.awt.Dimension(100, 277));
    groupContentPanel.setLayout(
      new javax.swing.BoxLayout(
        groupContentPanel,
        javax.swing.BoxLayout.LINE_AXIS
      )
    );

    groupPaymentMethodPanel.setLayout(
      new javax.swing.BoxLayout(
        groupPaymentMethodPanel,
        javax.swing.BoxLayout.Y_AXIS
      )
    );

    paymentMethodLabel.setText("Payment Method");
    groupPaymentMethodPanel.add(paymentMethodLabel);

    jRadioButton1.setText("Cash");
    groupPaymentMethodPanel.add(jRadioButton1);

    jRadioButton2.setText("Credit");
    groupPaymentMethodPanel.add(jRadioButton2);

    shippingMethodLabel.setText("Shipping Method");
    groupPaymentMethodPanel.add(shippingMethodLabel);

    internationalShippingRadioButton.setText("International Shipping  ");

    groupPaymentMethodPanel.add(internationalShippingRadioButton);

    standardShippingRadioButton.setText("Standard Shipping");
    groupPaymentMethodPanel.add(standardShippingRadioButton);

    expressShippingRadioButton.setText("Express Shipping");
    groupPaymentMethodPanel.add(expressShippingRadioButton);

    nextDayShippingRadioButton.setText(" Next Day Shipping");
    groupPaymentMethodPanel.add(nextDayShippingRadioButton);

    groupContentPanel.add(groupPaymentMethodPanel);

    groupCreditCardPanel.setMaximumSize(new java.awt.Dimension(500, 200));
    groupCreditCardPanel.setLayout(
      new javax.swing.BoxLayout(
        groupCreditCardPanel,
        javax.swing.BoxLayout.Y_AXIS
      )
    );

    cardNumberLabel.setText("jLabel6");
    groupCreditCardPanel.add(cardNumberLabel);

    cardNumberTextField.setText("jTextField7");
    cardNumberTextField.setPreferredSize(new java.awt.Dimension(73, 10));

    groupCreditCardPanel.add(cardNumberTextField);

    expirationDateLabel.setText("jLabel7");
    groupCreditCardPanel.add(expirationDateLabel);

    expirationDateTextField.setText("jTextField8");
    expirationDateTextField.setPreferredSize(new java.awt.Dimension(73, 10));
    groupCreditCardPanel.add(expirationDateTextField);

    cvvLabel.setText("jLabel8");
    groupCreditCardPanel.add(cvvLabel);

    cvvTextField.setText("jTextField9");
    cvvTextField.setPreferredSize(new java.awt.Dimension(73, 10));
    groupCreditCardPanel.add(cvvTextField);

    // groupContentPanel.add(groupInforCreditCardPanel);

    getContentPane().add(groupContentPanel, java.awt.BorderLayout.CENTER);

    productListTable.setModel(
      new javax.swing.table.DefaultTableModel(
        new Object[][] {
          { null, null, null, null },
          { null, null, null, null },
          { null, null, null, null },
          { null, null, null, null },
        },
        new String[] { "Title 1", "Title 2", "Title 3", "Title 4" }
      )
    );
    productListTable.setPreferredSize(new java.awt.Dimension(250, 50));
    jScrollPane1.setViewportView(productListTable);

    groupTableProductPanel.add(jScrollPane1);

    getContentPane()
      .add(groupTableProductPanel, java.awt.BorderLayout.LINE_END);

    checkoutButton.setText("Checkout");
    groupButtonPanel.add(checkoutButton);

    exitButton.setText("Exit");
    groupButtonPanel.add(exitButton);

    getContentPane().add(groupButtonPanel, java.awt.BorderLayout.PAGE_END);

    pack();
  }

  public static void main(String args[]) {
    try {
      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          javax.swing.UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger
        .getLogger(CheckoutUI.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger
        .getLogger(CheckoutUI.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger
        .getLogger(CheckoutUI.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger
        .getLogger(CheckoutUI.class.getName())
        .log(java.util.logging.Level.SEVERE, null, ex);
    }

    java.awt.EventQueue.invokeLater(
      new Runnable() {
        public void run() {
          new CheckoutUI().setVisible(true);
        }
      }
    );
  }

  private javax.swing.JLabel addressLabel;
  private javax.swing.JTextField addressTextField;
  private javax.swing.JLabel cardNumberLabel;
  private javax.swing.JTextField cardNumberTextField;
  private javax.swing.JButton checkoutButton;
  private javax.swing.JLabel cvvLabel;
  private javax.swing.JTextField cvvTextField;
  private javax.swing.JLabel emailLabel;
  private javax.swing.JTextField emailTextField;
  private javax.swing.JButton exitButton;
  private javax.swing.JLabel expirationDateLabel;
  private javax.swing.JTextField expirationDateTextField;
  private javax.swing.JRadioButton expressShippingRadioButton;
  private javax.swing.JPanel groupButtonPanel;
  private javax.swing.JPanel groupContentPanel;
  private javax.swing.JPanel groupHeaderPanel;
  private javax.swing.JPanel groupCreditCardPanel;
  private javax.swing.JPanel groupPaymentMethodPanel;
  private javax.swing.JPanel groupTableProductPanel;
  private javax.swing.JRadioButton internationalShippingRadioButton;
  private javax.swing.JLabel paymentMethodLabel;
  private javax.swing.JRadioButton jRadioButton1;
  private javax.swing.JRadioButton jRadioButton2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable productListTable;
  private javax.swing.JTextField jTextField6;
  private javax.swing.JLabel nameLabel;
  private javax.swing.JTextField nameTextField;
  private javax.swing.JRadioButton nextDayShippingRadioButton;
  private javax.swing.JLabel phoneLabel;
  private javax.swing.JTextField phoneTextField;
  private javax.swing.JLabel shippingMethodLabel;
  private javax.swing.JRadioButton standardShippingRadioButton;
}
