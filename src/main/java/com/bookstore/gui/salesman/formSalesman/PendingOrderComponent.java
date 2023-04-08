package com.bookstore.gui.salesman.formSalesman;

public class PendingOrderComponent extends javax.swing.JPanel {

  public PendingOrderComponent() {
    initComponents();
  }

  @SuppressWarnings("unchecked")
  private void initComponents() {

    jScrollPane1 = new javax.swing.JScrollPane();
    orderTable = new javax.swing.JTable();
    jPanel1 = new javax.swing.JPanel();
    employeeInformationTable = new javax.swing.JScrollPane();
    jTable2 = new javax.swing.JTable();
    jPanel2 = new javax.swing.JPanel();
    searchEmployeeTxtFld = new javax.swing.JTextField();
    searchCartIdTxtFld = new javax.swing.JTextField();
    jLabel1 = new javax.swing.JLabel();
    jLabel2 = new javax.swing.JLabel();
    searchEmployeeBtn = new javax.swing.JButton();
    searchOrderBtn = new javax.swing.JButton();

    orderTable.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null }
        },
        new String[] {
            "Cart id", "Employee id", "Total Price", "Status"
        }) {
      Class[] types = new Class[] {
          java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class
      };

      public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
      }
    });
    jScrollPane1.setViewportView(orderTable);

    jTable2.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null },
            { null, null, null, null }
        },
        new String[] {
            "Employee id", "Name", "Phone number", "Employee Role"
        }) {
      Class[] types = new Class[] {
          java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class, java.lang.Object.class
      };
      boolean[] canEdit = new boolean[] {
          false, false, false, false
      };

      public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
      }

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    });
    employeeInformationTable.setViewportView(jTable2);
    if (jTable2.getColumnModel().getColumnCount() > 0) {
      jTable2.getColumnModel().getColumn(0).setResizable(false);
      jTable2.getColumnModel().getColumn(1).setResizable(false);
      jTable2.getColumnModel().getColumn(2).setResizable(false);
      jTable2.getColumnModel().getColumn(3).setResizable(false);
    }

    javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(employeeInformationTable, javax.swing.GroupLayout.PREFERRED_SIZE, 420,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)));
    jPanel1Layout.setVerticalGroup(
        jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(employeeInformationTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0,
                Short.MAX_VALUE));

    searchEmployeeTxtFld.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        searchEmployeeTxtFldActionPerformed(evt);
      }
    });

    jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel1.setText("Search order:");

    jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
    jLabel2.setText("Search Employee:");

    searchEmployeeBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search (Custom).png"))); // NOI18N

    searchOrderBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search (Custom).png"))); // NOI18N

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
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchEmployeeTxtFld, javax.swing.GroupLayout.DEFAULT_SIZE, 220,
                        Short.MAX_VALUE)
                    .addComponent(searchCartIdTxtFld))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchEmployeeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
    jPanel2Layout.setVerticalGroup(
        jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchEmployeeTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchEmployeeBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(searchCartIdTxtFld, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addComponent(searchOrderBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(181, 181, 181)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))));
  }

  private void searchEmployeeTxtFldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_searchEmployeeTxtFldActionPerformed
    // TODO add your handling code here:
  }

  private javax.swing.JScrollPane employeeInformationTable;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JTable jTable2;
  private javax.swing.JTable orderTable;
  private javax.swing.JTextField searchCartIdTxtFld;
  private javax.swing.JButton searchEmployeeBtn;
  private javax.swing.JTextField searchEmployeeTxtFld;
  private javax.swing.JButton searchOrderBtn;
}
