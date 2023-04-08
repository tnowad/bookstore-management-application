
package com.bookstore.gui.salesman.formSalesman;

public class OrderComponent extends javax.swing.JPanel {

  public OrderComponent() {
    initComponents();
  }

  @SuppressWarnings("unchecked")

  private void initComponents() {

    jScrollPane2 = new javax.swing.JScrollPane();
    bookListTable = new javax.swing.JTable();
    jPanel1 = new javax.swing.JPanel();
    acceptOrderBtn1 = new javax.swing.JButton();
    jTextField1 = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    acceptOrderBtn = new javax.swing.JButton();
    jPanel2 = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();
    getCustomerNameTxtFld = new javax.swing.JTextField();
    jLabel3 = new javax.swing.JLabel();
    getCustomerPhoneTxtFld = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    getCustomerEmailTxtFld = new javax.swing.JTextField();

    setName("");
    setPreferredSize(new java.awt.Dimension(821, 548));

    bookListTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null },
            { null, null, null, null, null }
        },
        new String[] {
            "Serial", "Book Title", "Quantity", "Price", "Total Price"
        }) {
      Class[] types = new Class[] {
          java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class,
          java.lang.Double.class
      };
      boolean[] canEdit = new boolean[] {
          false, false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    });
    jScrollPane2.setViewportView(bookListTable);
    if (bookListTable.getColumnModel().getColumnCount() > 0) {
      bookListTable.getColumnModel().getColumn(0).setResizable(false);
      bookListTable.getColumnModel().getColumn(1).setResizable(false);
      bookListTable.getColumnModel().getColumn(2).setResizable(false);
      bookListTable.getColumnModel().getColumn(3).setResizable(false);
      bookListTable.getColumnModel().getColumn(4).setResizable(false);
    }

    acceptOrderBtn1.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
    acceptOrderBtn1
        .setIcon(new javax.swing.ImageIcon(getClass().getResource("/employee/icons/cross (Custom).png"))); // NOI18N
    acceptOrderBtn1.setText("Reject");

    jTextField1.setEditable(false);

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/employee/icons/budget (Custom).png"))); // NOI18N
    jLabel1.setText("Total cost:");

    acceptOrderBtn.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
    acceptOrderBtn.setIcon(
        new javax.swing.ImageIcon(getClass().getResource("/employee/icons/shopping-bag (Custom).png"))); // NOI18N
    acceptOrderBtn.setText("Accept");

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout
                    .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(acceptOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE,
                            100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(acceptOrderBtn1, javax.swing.GroupLayout.PREFERRED_SIZE,
                            100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 206,
                        javax.swing.GroupLayout.PREFERRED_SIZE))));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(acceptOrderBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(acceptOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40,
                        javax.swing.GroupLayout.PREFERRED_SIZE))));

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel2.setText("Customer name:");

    getCustomerNameTxtFld.setEditable(false);
    getCustomerNameTxtFld.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

    jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel3.setText("Phone number:");

    getCustomerPhoneTxtFld.setEditable(false);
    getCustomerPhoneTxtFld.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

    jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    jLabel4.setText("Email:");

    getCustomerEmailTxtFld.setEditable(false);
    getCustomerEmailTxtFld.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N

    javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout
                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 130,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(getCustomerPhoneTxtFld,
                            javax.swing.GroupLayout.PREFERRED_SIZE, 200,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(getCustomerEmailTxtFld))
                    .addComponent(getCustomerNameTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                        360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout
                    .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(getCustomerNameTxtFld)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout
                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(getCustomerPhoneTxtFld)
                            .addComponent(jLabel4))
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 36,
                            javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(getCustomerEmailTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE,
                        37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(20, Short.MAX_VALUE)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(307, Short.MAX_VALUE))
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE)));
  }

  private javax.swing.JButton acceptOrderBtn;
  private javax.swing.JButton acceptOrderBtn1;
  private javax.swing.JTable bookListTable;
  private javax.swing.JTextField getCustomerEmailTxtFld;
  private javax.swing.JTextField getCustomerNameTxtFld;
  private javax.swing.JTextField getCustomerPhoneTxtFld;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JTextField jTextField1;
}
