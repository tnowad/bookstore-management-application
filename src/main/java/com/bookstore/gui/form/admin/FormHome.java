package com.bookstore.gui.form.admin;

import com.bookstore.gui.model.ModelStatisticCard;
import com.bookstore.gui.component.StatusType;
import com.bookstore.model.EmployeeModel.EmployeeType;
import com.bookstore.gui.component.Table;
import com.bookstore.gui.component.Card;
import com.bookstore.gui.component.PanelBorder;
import com.bookstore.gui.component.ScrollBar;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FormHome extends javax.swing.JPanel {

  public FormHome() {
    initComponents();
    card1.setData(
        new ModelStatisticCard(new ImageIcon(getClass().getResource("/com/bookstore/gui/icon/stock.png")),
            "Stock Total",
            "$200000", "Increased by 60%"));
    card2.setData(
        new ModelStatisticCard(new ImageIcon(getClass().getResource("/com/bookstore/gui/icon/profit.png")),
            "Total Profit",
            "$15000", "Increased by 25%"));
    card3.setData(
        new ModelStatisticCard(new ImageIcon(getClass().getResource("/com/bookstore/gui/icon/flag.png")),
            "Unique Visitors",
            "$300000", "Increased by 70%"));

    spTable.setVerticalScrollBar(new ScrollBar());
    spTable.getVerticalScrollBar().setBackground(Color.WHITE);
    spTable.getViewport().setBackground(Color.WHITE);
    JPanel p = new JPanel();
    p.setBackground(Color.WHITE);
    spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    table.addRow(new Object[] { "Mike Bhand", "mikebhand@gmail.com", EmployeeType.EMPLOYEE_MANAGER, "25 Apr,2018",
        StatusType.PENDING });
    table.addRow(
        new Object[] { "Andrew Strauss", "andrewstrauss@gmail.com", EmployeeType.EMPLOYEE_MANAGER, "25 Apr,2018",
            StatusType.APPROVED });
    table.addRow(
        new Object[] { "Ross Kopelman", "rosskopelman@gmail.com", EmployeeType.EMPLOYEE_INVENTORY, "25 Apr,2018",
            StatusType.APPROVED });
    table.addRow(new Object[] { "Mike Hussy", "mikehussy@gmail.com", EmployeeType.EMPLOYEE_INVENTORY, "25 Apr,2018",
        StatusType.REJECT });
    table.addRow(
        new Object[] { "Kevin Pietersen", "kevinpietersen@gmail.com", EmployeeType.EMPLOYEE_ORDER, "25 Apr,2018",
            StatusType.PENDING });
    table.addRow(
        new Object[] { "Andrew Strauss", "andrewstrauss@gmail.com", EmployeeType.EMPLOYEE_SALES, "25 Apr,2018",
            StatusType.APPROVED });
    table.addRow(
        new Object[] { "Ross Kopelman", "rosskopelman@gmail.com", EmployeeType.EMPLOYEE_ORDER, "25 Apr,2018",
            StatusType.APPROVED });
    table.addRow(new Object[] { "Mike Hussy", "mikehussy@gmail.com", EmployeeType.EMPLOYEE_SALES, "25 Apr,2018",
        StatusType.REJECT });
    table.addRow(
        new Object[] { "Kevin Pietersen", "kevinpietersen@gmail.com", EmployeeType.EMPLOYEE_SALES, "25 Apr,2018",
            StatusType.PENDING });
    table.addRow(
        new Object[] { "Kevin Pietersen", "kevinpietersen@gmail.com", EmployeeType.EMPLOYEE_MANAGER, "25 Apr,2018",
            StatusType.PENDING });
    table.addRow(
        new Object[] { "Andrew Strauss", "andrewstrauss@gmail.com", EmployeeType.EMPLOYEE_SALES, "25 Apr,2018",
            StatusType.APPROVED });
    table.addRow(
        new Object[] { "Ross Kopelman", "rosskopelman@gmail.com", EmployeeType.EMPLOYEE_INVENTORY, "25 Apr,2018",
            StatusType.APPROVED });
    table.addRow(new Object[] { "Mike Hussy", "mikehussy@gmail.com", EmployeeType.EMPLOYEE_SALES, "25 Apr,2018",
        StatusType.REJECT });
    table.addRow(
        new Object[] { "Kevin Pietersen", "kevinpietersen@gmail.com", EmployeeType.EMPLOYEE_SALES, "25 Apr,2018",
            StatusType.PENDING });
  }

  @SuppressWarnings("unchecked")

  private void initComponents() {

    panel = new javax.swing.JLayeredPane();
    card1 = new Card();
    card2 = new Card();
    card3 = new Card();
    panelBorder1 = new PanelBorder();
    jLabel1 = new javax.swing.JLabel();
    spTable = new javax.swing.JScrollPane();
    table = new Table();

    setBackground(new java.awt.Color(242, 242, 242));

    panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

    card1.setColor1(new java.awt.Color(142, 142, 250));
    card1.setColor2(new java.awt.Color(123, 123, 245));
    panel.add(card1);

    card2.setColor1(new java.awt.Color(186, 123, 247));
    card2.setColor2(new java.awt.Color(167, 94, 236));
    panel.add(card2);

    card3.setColor1(new java.awt.Color(241, 208, 62));
    card3.setColor2(new java.awt.Color(211, 184, 61));
    panel.add(card3);

    panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

    jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(127, 127, 127));
    jLabel1.setText("Standard Table Design");

    spTable.setBorder(null);

    table.setModel(new javax.swing.table.DefaultTableModel(
        new Object[][] {

        },
        new String[] {
            "Name", "Email", "User Type", "Joined", "Status"
        }) {
      boolean[] canEdit = new boolean[] {
          false, false, false, false, false
      };

      public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
      }
    });
    spTable.setViewportView(table);

    javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
    panelBorder1.setLayout(panelBorder1Layout);
    panelBorder1Layout.setHorizontalGroup(
        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(spTable))
                .addContainerGap()));
    panelBorder1Layout.setVerticalGroup(
        panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTable, javax.swing.GroupLayout.DEFAULT_SIZE, 279, Short.MAX_VALUE)
                .addGap(20, 20, 20)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, 875, Short.MAX_VALUE))
                .addGap(20, 20, 20)));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addGap(20, 20, 20)));
  }

  private Card card1;
  private Card card2;
  private Card card3;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLayeredPane panel;
  private PanelBorder panelBorder1;
  private javax.swing.JScrollPane spTable;
  private Table table;
}
