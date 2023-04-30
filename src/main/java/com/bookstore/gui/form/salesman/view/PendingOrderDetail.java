package com.bookstore.gui.form.salesman.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.label.Label;

public class PendingOrderDetail extends JFrame {

  public PendingOrderDetail() {
    initComponents();
    handleEvent();
    // listOrder();
  }

  private void initComponents() {

    jPanel1 = new JPanel();
    customerInforLabel = new Label("Customer Information");
    nameLabel = new Label("Fullname");
    nameTextField = new JTextField();
    addressLabel = new Label("Address");
    addressTextField = new JTextField();
    phoneLabel = new Label("Phone Number");
    phoneTextField = new JTextField();
    jPanel2 = new JPanel();
    tableListScrollPane = new JScrollPane();
    orderTableList = new JTable();
    jPanel3 = new JPanel();
    totalPriceLabel = new Label("Total price");
    totalPriceTextField = new JTextField();
    confirmButton = new Button();
    rejectButton = new Button();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    jPanel1.setPreferredSize(new Dimension(471, 200));
    jPanel1.setLayout(null);

    jPanel1.add(customerInforLabel);

    nameLabel.setText("Full name:");
    jPanel1.add(nameLabel);

    nameTextField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        nameTextFieldActionPerformed(evt);
      }
    });
    jPanel1.add(nameTextField);
    nameTextField.setBounds(130, 70, 220, 28);

    addressLabel.setText("Address:");
    jPanel1.add(addressLabel);

    addressTextField.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        addressTextField2ActionPerforme(evt);
      }
    });
    jPanel1.add(addressTextField);
    addressTextField.setBounds(130, 110, 220, 28);

    phoneLabel.setText("Phone number:");
    jPanel1.add(phoneLabel);
    jPanel1.add(phoneTextField);
    phoneTextField.setBounds(130, 150, 220, 28);

    getContentPane().add(jPanel1, BorderLayout.PAGE_START);

    jPanel2.setLayout(new BoxLayout(jPanel2, BoxLayout.LINE_AXIS));

    tableListScrollPane.setViewportView(orderTableList);

    jPanel2.add(tableListScrollPane);

    getContentPane().add(jPanel2, BorderLayout.CENTER);

    jPanel3.setLayout(new BoxLayout(jPanel3, BoxLayout.LINE_AXIS));

    jPanel3.add(totalPriceLabel);
    jPanel3.add(totalPriceTextField);

    confirmButton.setText("Confirm");
    jPanel3.add(confirmButton);

    rejectButton.setText("Reject");
    jPanel3.add(rejectButton);

    getContentPane().add(jPanel3, BorderLayout.PAGE_END);

    pack();
  }

  private void handleEvent() {
  }

  private void nameTextFieldActionPerformed(ActionEvent evt) {
  }

  private void addressTextField2ActionPerforme(ActionEvent evt) {
  }

  public static void main(String args[]) {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (ClassNotFoundException ex) {
      java.util.logging.Logger.getLogger(PendingOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(PendingOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(PendingOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    } catch (UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(PendingOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    }

    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new PendingOrderDetail().setVisible(true);
      }
    });
  }

  private Button confirmButton;
  private Button rejectButton;
  private Label customerInforLabel;
  private Label nameLabel;
  private Label addressLabel;
  private Label phoneLabel;
  private Label totalPriceLabel;
  private JPanel jPanel1;
  private JPanel jPanel2;
  private JPanel jPanel3;
  private JScrollPane tableListScrollPane;
  private JTable orderTableList;
  private JTextField nameTextField;
  private JTextField addressTextField;
  private JTextField phoneTextField;
  private JTextField totalPriceTextField;
}
