package com.bookstore.gui.form.salesman.view;

import javax.swing.*;

import com.bookstore.gui.component.label.Label;

public class PendingOrderDetail extends JFrame {

  // "ISBN", "Title ", "Quantity", "Price"

  public PendingOrderDetail() {
    initComponents();
    // listOrder();
  }

  private void initComponents() {

    jPanel1 = new JPanel();
    jLabel1 = new Label("Customer Information");
    jLabel2 = new Label("Fullname");
    jTextField1 = new JTextField();
    jLabel3 = new Label("Address");
    jTextField2 = new JTextField();
    jLabel4 = new Label("Phone Number");
    jTextField3 = new javax.swing.JTextField();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    orderTableList = new javax.swing.JTable();
    jPanel3 = new javax.swing.JPanel();
    jLabel5 = new Label("Total price");
    jTextField4 = new javax.swing.JTextField();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanel1.setPreferredSize(new java.awt.Dimension(471, 200));
    jPanel1.setLayout(null);

    jPanel1.add(jLabel1);

    jLabel2.setText("Full name:");
    jPanel1.add(jLabel2);

    jTextField1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1ActionPerformed(evt);
      }
    });
    jPanel1.add(jTextField1);
    jTextField1.setBounds(130, 70, 220, 28);

    jLabel3.setText("Address:");
    jPanel1.add(jLabel3);

    jTextField2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField2ActionPerformed(evt);
      }
    });
    jPanel1.add(jTextField2);
    jTextField2.setBounds(130, 110, 220, 28);

    jLabel4.setText("Phone number:");
    jPanel1.add(jLabel4);
    jPanel1.add(jTextField3);
    jTextField3.setBounds(130, 150, 220, 28);

    getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

    jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

    jScrollPane1.setViewportView(orderTableList);

    jPanel2.add(jScrollPane1);

    getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

    jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

    jPanel3.add(jLabel5);
    jPanel3.add(jTextField4);

    jButton1.setText("Confirm");
    jPanel3.add(jButton1);

    jButton2.setText("Reject");
    jPanel3.add(jButton2);

    getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

    pack();
  }

  private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
  }

  private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
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
      java.util.logging.Logger.getLogger(PendingOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    } catch (InstantiationException ex) {
      java.util.logging.Logger.getLogger(PendingOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    } catch (IllegalAccessException ex) {
      java.util.logging.Logger.getLogger(PendingOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
      java.util.logging.Logger.getLogger(PendingOrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
          ex);
    }

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new PendingOrderDetail().setVisible(true);
      }
    });
  }

  private javax.swing.JButton jButton1;
  private javax.swing.JButton jButton2;
  private Label jLabel1;
  private Label jLabel2;
  private Label jLabel3;
  private Label jLabel4;
  private Label jLabel5;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable orderTableList;
  private javax.swing.JTextField jTextField1;
  private javax.swing.JTextField jTextField2;
  private javax.swing.JTextField jTextField3;
  private javax.swing.JTextField jTextField4;
}
