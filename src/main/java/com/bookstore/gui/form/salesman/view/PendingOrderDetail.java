package com.bookstore.gui.form.salesman.view;

import javax.swing.JFrame;


public class PendingOrderDetail extends JFrame {

  // "ISBN", "Title ", "Quantity", "Price"

 

  public PendingOrderDetail() {
    initComponents();
    // listOrder();
  }

  private void initComponents() {

    jPanel1 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    jTextField1 = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    jTextField2 = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    jTextField3 = new javax.swing.JTextField();
    jPanel2 = new javax.swing.JPanel();
    jScrollPane1 = new javax.swing.JScrollPane();
    orderTableList = new javax.swing.JTable();
    jPanel3 = new javax.swing.JPanel();
    jLabel5 = new javax.swing.JLabel();
    jTextField4 = new javax.swing.JTextField();
    jButton1 = new javax.swing.JButton();
    jButton2 = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    jPanel1.setPreferredSize(new java.awt.Dimension(471, 200));
    jPanel1.setLayout(null);

    jLabel1.setText("Thông tin khách hàng");
    jLabel1.setPreferredSize(new java.awt.Dimension(118, 20));
    jPanel1.add(jLabel1);
    jLabel1.setBounds(180, 10, 130, 40);

    jLabel2.setText("Full name:");
    jPanel1.add(jLabel2);
    jLabel2.setBounds(20, 70, 60, 30);

    jTextField1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField1ActionPerformed(evt);
      }
    });
    jPanel1.add(jTextField1);
    jTextField1.setBounds(130, 70, 220, 28);

    jLabel3.setText("Address:");
    jPanel1.add(jLabel3);
    jLabel3.setBounds(20, 110, 60, 30);

    jTextField2.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField2ActionPerformed(evt);
      }
    });
    jPanel1.add(jTextField2);
    jTextField2.setBounds(130, 110, 220, 28);

    jLabel4.setText("Phone number:");
    jPanel1.add(jLabel4);
    jLabel4.setBounds(20, 150, 90, 30);
    jPanel1.add(jTextField3);
    jTextField3.setBounds(130, 150, 220, 28);

    getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_START);

    jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.LINE_AXIS));

    // orderTableList.setModel(new javax.swing.table.DefaultTableModel(
    //     new Object[][] {
    //         { null, null, null, null },
    //         { null, null, null, null },
    //         { null, null, null, null },
    //         { null, null, null, null },
    //         { null, null, null, null },
    //         { null, null, null, null },
    //         { null, null, null, null },
    //         { null, null, null, null },
    //         { null, null, null, null },
    //         { null, null, null, null }
    //     },
    //     new String[] {
    //         "ISBN", "Title ", "Quantity", "Price"
    //     }));
    // jScrollPane1.setViewportView(orderTableList);

    // jPanel2.add(jScrollPane1);

    getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

    jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.LINE_AXIS));

    jLabel5.setText("Total price");
    jPanel3.add(jLabel5);
    jPanel3.add(jTextField4);

    jButton1.setText("Confirm");
    jPanel3.add(jButton1);

    jButton2.setText("Reject");
    jPanel3.add(jButton2);

    getContentPane().add(jPanel3, java.awt.BorderLayout.PAGE_END);

    pack();
  }// </editor-fold>

  private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
  }

  private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
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
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
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
