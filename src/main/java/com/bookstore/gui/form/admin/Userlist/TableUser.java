package com.bookstore.gui.form.admin.Userlist;

import com.bookstore.gui.model.ModelStatisticCard;
import com.bookstore.model.UserModel;

import com.bookstore.gui.component.Table;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.component.Card;
import com.bookstore.gui.component.PanelBorder;
import com.bookstore.gui.component.ScrollBar;
import com.bookstore.gui.component.StatusType;

import java.awt.Color;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TableUser extends javax.swing.JPanel {

  public TableUser() {
    initComponents();
    adminCard.setData(
        new ModelStatisticCard(new ImageIcon(getClass().getResource("/com/bookstore/gui/icon/stock.png")),
            "Admin",
            "1000", "Increased by 60%"));
    customerCard.setData(
        new ModelStatisticCard(new ImageIcon(getClass().getResource("/com/bookstore/gui/icon/profit.png")),
            "Customer",
            "1000", "Increased by 25%"));
    employeeCard.setData(
        new ModelStatisticCard(new ImageIcon(getClass().getResource("/com/bookstore/gui/icon/flag.png")),
            "Employee",
            "500", "Increased by 70%"));

    spTable.setVerticalScrollBar(new ScrollBar());
    spTable.getVerticalScrollBar().setBackground(Color.WHITE);
    spTable.getViewport().setBackground(Color.WHITE);
    JPanel p = new JPanel();
    p.setBackground(Color.WHITE);
    spTable.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    UserBUS userBUS = UserBUS.getInstance();
    List<UserModel> userList =userBUS.getAllModels();
    System.out.println(userList);
    for(UserModel user: userList){
        table.addRow(
        new Object[] { user.getName(), user.getEmail(), user.getRole(), user.getCreatedAt(),
         user.getStatus() });
    }
  }

  private void initComponents() {

    panel = new javax.swing.JLayeredPane();
    adminCard = new Card();
    customerCard = new Card();
    employeeCard = new Card();
    panelBorder1 = new PanelBorder();
    jLabel1 = new javax.swing.JLabel();
    spTable = new javax.swing.JScrollPane();
    table = new Table();

    setBackground(new java.awt.Color(242, 242, 242));

    panel.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

    adminCard.setColor1(new java.awt.Color(142, 142, 250));
    adminCard.setColor2(new java.awt.Color(123, 123, 245));
    panel.add(adminCard);

    customerCard.setColor1(new java.awt.Color(186, 123, 247));
    customerCard.setColor2(new java.awt.Color(167, 94, 236));
    panel.add(customerCard);

    employeeCard.setColor1(new java.awt.Color(241, 208, 62));
    employeeCard.setColor2(new java.awt.Color(211, 184, 61));
    panel.add(employeeCard);

    panelBorder1.setBackground(new java.awt.Color(255, 255, 255));

    jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(127, 127, 127));
    jLabel1.setText("User List");

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

  private Card adminCard;
  private Card customerCard;
  private Card employeeCard;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLayeredPane panel;
  private PanelBorder panelBorder1;
  private javax.swing.JScrollPane spTable;
  private Table table;
}
