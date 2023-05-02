package com.bookstore.gui.components.dashboards;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import java.awt.GridLayout;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class DashboardPanel extends javax.swing.JPanel {

  private static DashboardPanel instance;

  /**
   * Creates new form DashboardPanel
   */
  public DashboardPanel() {
    initComponents();
    addTable();
    CartDashboard();
  }

  public static DashboardPanel getInstance() {
    if (instance == null) {
      instance = new DashboardPanel();
    }
    return instance;
  }

  private void initComponents() {
    jLabel2 = new javax.swing.JLabel();
    jPanel10 = new javax.swing.JPanel();
    jPanel9 = new javax.swing.JPanel();
    jPanel4 = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    jLabel9 = new javax.swing.JLabel();
    setTotalProductSold = new javax.swing.JLabel();
    valueProductSold = new javax.swing.JLabel();
    jPanel2 = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    setTotalNewUser = new javax.swing.JLabel();
    valueNewUser = new javax.swing.JLabel();
    jPanel3 = new javax.swing.JPanel();
    JLabel6 = new javax.swing.JLabel();
    setTotalRevenue = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    ValueRevenue = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    jPanel11 = new javax.swing.JPanel();
    jPanel13 = new javax.swing.JPanel();
    jPanel14 = new javax.swing.JPanel();
    jLabel4 = new javax.swing.JLabel();
    topBook = new javax.swing.JPanel();
    jPanel7 = new javax.swing.JPanel();
    jPanel8 = new javax.swing.JPanel();
    jPanel18 = new javax.swing.JPanel();
    jLabel17 = new javax.swing.JLabel();
    jLabel30 = new javax.swing.JLabel();
    jLabel31 = new javax.swing.JLabel();
    jLabel32 = new javax.swing.JLabel();
    jLabel33 = new javax.swing.JLabel();
    jLabel34 = new javax.swing.JLabel();
    jLabel35 = new javax.swing.JLabel();
    jScrollPane2 = new javax.swing.JScrollPane();
    tableUser = new javax.swing.JPanel();

    setBackground(new java.awt.Color(255, 255, 255));
    setBorder(
      javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))
    );
    setPreferredSize(new java.awt.Dimension(702, 444));
    setLayout(new java.awt.BorderLayout());

    jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18));
    jLabel2.setForeground(new java.awt.Color(255, 51, 51));
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel2.setText("DashBoard");
    jLabel2.setPreferredSize(new java.awt.Dimension(702, 20));
    add(jLabel2, java.awt.BorderLayout.PAGE_START);

    jPanel10.setLayout(new java.awt.BorderLayout());

    jPanel9.setBackground(new java.awt.Color(255, 255, 255));
    jPanel9.setPreferredSize(new java.awt.Dimension(702, 100));
    jPanel9.setLayout(new java.awt.GridLayout(1, 4, 20, 20));

    jPanel4.setBackground(new java.awt.Color(204, 204, 255));
    jPanel4.setBorder(
      new javax.swing.border.SoftBevelBorder(
        javax.swing.border.BevelBorder.RAISED,
        new java.awt.Color(204, 255, 204),
        new java.awt.Color(255, 204, 51),
        new java.awt.Color(204, 102, 255),
        new java.awt.Color(0, 51, 51)
      )
    );
    jPanel4.setLayout(new java.awt.BorderLayout());

    jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 12));
    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel3.setText("Total Product Sold");
    jLabel3.setPreferredSize(new java.awt.Dimension(158, 16));
    jPanel4.add(jLabel3, java.awt.BorderLayout.PAGE_START);

    jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel9.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/line-chart.png")
      )
    );
    jLabel9.setPreferredSize(new java.awt.Dimension(35, 24));
    jPanel4.add(jLabel9, java.awt.BorderLayout.LINE_START);

    setTotalProductSold.setFont(new java.awt.Font("Segoe UI", 2, 18));
    setTotalProductSold.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    setTotalProductSold.setText("67%");
    setTotalProductSold.setPreferredSize(new java.awt.Dimension(100, 25));
    jPanel4.add(setTotalProductSold, java.awt.BorderLayout.CENTER);

    valueProductSold.setFont(new java.awt.Font("Segoe UI", 2, 14));
    valueProductSold.setForeground(new java.awt.Color(204, 51, 255));
    valueProductSold.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    valueProductSold.setText("Actual Value: 1000");
    jPanel4.add(valueProductSold, java.awt.BorderLayout.PAGE_END);

    jPanel9.add(jPanel4);

    jPanel2.setBackground(new java.awt.Color(255, 255, 255));
    jPanel2.setBorder(
      new javax.swing.border.SoftBevelBorder(
        javax.swing.border.BevelBorder.RAISED,
        new java.awt.Color(255, 102, 102),
        new java.awt.Color(0, 204, 51),
        new java.awt.Color(0, 102, 102),
        new java.awt.Color(0, 51, 204)
      )
    );
    jPanel2.setLayout(new java.awt.BorderLayout());

    jLabel1.setFont(new java.awt.Font("Segoe UI Historic", 1, 12));
    jLabel1.setText("Total New User");
    jPanel2.add(jLabel1, java.awt.BorderLayout.PAGE_START);

    jLabel5.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/line-chart.png")
      )
    );
    jLabel5.setPreferredSize(new java.awt.Dimension(35, 24));
    jPanel2.add(jLabel5, java.awt.BorderLayout.LINE_START);

    setTotalNewUser.setFont(new java.awt.Font("Segoe UI", 2, 18));
    setTotalNewUser.setText("67%");
    jPanel2.add(setTotalNewUser, java.awt.BorderLayout.CENTER);

    valueNewUser.setFont(new java.awt.Font("Segoe UI", 2, 14));
    valueNewUser.setForeground(new java.awt.Color(204, 51, 255));
    valueNewUser.setText("Actual Value: 1000");
    jPanel2.add(valueNewUser, java.awt.BorderLayout.PAGE_END);

    jPanel9.add(jPanel2);

    jPanel3.setBackground(new java.awt.Color(204, 255, 255));
    jPanel3.setBorder(
      new javax.swing.border.SoftBevelBorder(
        javax.swing.border.BevelBorder.RAISED,
        new java.awt.Color(153, 255, 153),
        new java.awt.Color(51, 255, 51),
        new java.awt.Color(51, 255, 255),
        new java.awt.Color(153, 102, 0)
      )
    );
    jPanel3.setLayout(new java.awt.BorderLayout());

    JLabel6.setFont(new java.awt.Font("Segoe UI Historic", 1, 12));
    JLabel6.setText("Total Revenue");
    jPanel3.add(JLabel6, java.awt.BorderLayout.PAGE_START);

    setTotalRevenue.setFont(new java.awt.Font("Segoe UI", 2, 18));
    setTotalRevenue.setText("67%");
    jPanel3.add(setTotalRevenue, java.awt.BorderLayout.CENTER);

    jLabel7.setIcon(
      new javax.swing.ImageIcon(
        getClass().getResource("/resources/icons/line-chart.png")
      )
    );
    jLabel7.setPreferredSize(new java.awt.Dimension(35, 24));
    jPanel3.add(jLabel7, java.awt.BorderLayout.LINE_START);

    ValueRevenue.setFont(new java.awt.Font("Segoe UI", 2, 14));
    ValueRevenue.setForeground(new java.awt.Color(204, 51, 255));
    ValueRevenue.setText("Actual Value: 1000");
    jPanel3.add(ValueRevenue, java.awt.BorderLayout.PAGE_END);

    jPanel9.add(jPanel3);

    jPanel5.setBackground(new java.awt.Color(204, 255, 204));
    jPanel5.setBorder(
      new javax.swing.border.SoftBevelBorder(
        javax.swing.border.BevelBorder.RAISED,
        new java.awt.Color(255, 51, 204),
        new java.awt.Color(153, 153, 0),
        new java.awt.Color(0, 204, 204),
        new java.awt.Color(153, 255, 153)
      )
    );

    javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(
      jPanel5
    );
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 156, Short.MAX_VALUE)
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 94, Short.MAX_VALUE)
    );

    jPanel9.add(jPanel5);

    jPanel10.add(jPanel9, java.awt.BorderLayout.PAGE_START);

    jPanel11.setLayout(new java.awt.BorderLayout());

    jPanel13.setLayout(
      new javax.swing.BoxLayout(jPanel13, javax.swing.BoxLayout.LINE_AXIS)
    );

    jPanel14.setBackground(new java.awt.Color(255, 255, 255));
    jPanel14.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jPanel14.setPreferredSize(new java.awt.Dimension(200, 304));
    jPanel14.setLayout(new java.awt.BorderLayout());

    jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14));
    jLabel4.setText("Top Book ");
    jPanel14.add(jLabel4, java.awt.BorderLayout.PAGE_START);

    javax.swing.GroupLayout topBookLayout = new javax.swing.GroupLayout(
      topBook
    );
    topBook.setLayout(topBookLayout);
    topBookLayout.setHorizontalGroup(
      topBookLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 196, Short.MAX_VALUE)
    );
    topBookLayout.setVerticalGroup(
      topBookLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 298, Short.MAX_VALUE)
    );

    jPanel14.add(topBook, java.awt.BorderLayout.CENTER);

    jPanel13.add(jPanel14);

    jPanel11.add(jPanel13, java.awt.BorderLayout.LINE_END);

    jPanel7.setBackground(new java.awt.Color(255, 255, 255));
    jPanel7.setForeground(new java.awt.Color(51, 255, 51));
    jPanel7.setLayout(new java.awt.BorderLayout());

    jPanel8.setBackground(new java.awt.Color(255, 255, 255));
    jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    jPanel8.setPreferredSize(new java.awt.Dimension(502, 60));

    jPanel18.setBackground(new java.awt.Color(255, 255, 255));

    jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 14));
    jLabel17.setForeground(new java.awt.Color(153, 0, 153));
    jLabel17.setText("User");

    jLabel30.setFont(new java.awt.Font("Segoe UI", 1, 12));
    jLabel30.setForeground(new java.awt.Color(0, 0, 204));
    jLabel30.setText("Serial");

    jLabel31.setFont(new java.awt.Font("Segoe UI", 1, 12));
    jLabel31.setForeground(new java.awt.Color(0, 0, 204));
    jLabel31.setText("Name");

    jLabel32.setFont(new java.awt.Font("Segoe UI", 1, 12));
    jLabel32.setForeground(new java.awt.Color(0, 0, 204));
    jLabel32.setText("Phone");

    jLabel33.setFont(new java.awt.Font("Segoe UI", 1, 12));
    jLabel33.setForeground(new java.awt.Color(0, 0, 204));
    jLabel33.setText("User Age");

    jLabel34.setFont(new java.awt.Font("Segoe UI", 1, 12));
    jLabel34.setForeground(new java.awt.Color(0, 0, 204));
    jLabel34.setText("Role");

    jLabel35.setFont(new java.awt.Font("Segoe UI", 1, 12));
    jLabel35.setForeground(new java.awt.Color(0, 0, 204));
    jLabel35.setText("Status");

    javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(
      jPanel18
    );
    jPanel18.setLayout(jPanel18Layout);
    jPanel18Layout.setHorizontalGroup(
      jPanel18Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          jPanel18Layout
            .createSequentialGroup()
            .addComponent(jLabel30)
            .addGap(18, 18, Short.MAX_VALUE)
            .addComponent(jLabel31)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              66,
              Short.MAX_VALUE
            )
            .addComponent(jLabel32)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              41,
              Short.MAX_VALUE
            )
            .addComponent(
              jLabel33,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              62,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              37,
              Short.MAX_VALUE
            )
            .addComponent(jLabel35)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.RELATED,
              60,
              Short.MAX_VALUE
            )
            .addComponent(jLabel34)
            .addGap(45, 45, 45)
        )
        .addGroup(
          jPanel18Layout
            .createSequentialGroup()
            .addComponent(
              jLabel17,
              javax.swing.GroupLayout.PREFERRED_SIZE,
              77,
              javax.swing.GroupLayout.PREFERRED_SIZE
            )
            .addGap(0, 0, Short.MAX_VALUE)
        )
    );
    jPanel18Layout.setVerticalGroup(
      jPanel18Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGroup(
          jPanel18Layout
            .createSequentialGroup()
            .addComponent(jLabel17)
            .addPreferredGap(
              javax.swing.LayoutStyle.ComponentPlacement.UNRELATED
            )
            .addGroup(
              jPanel18Layout
                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(jLabel30)
                .addComponent(jLabel31)
                .addComponent(jLabel32)
                .addComponent(jLabel33)
                .addComponent(jLabel34)
                .addComponent(jLabel35)
            )
        )
    );

    javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(
      jPanel8
    );
    jPanel8.setLayout(jPanel8Layout);
    jPanel8Layout.setHorizontalGroup(
      jPanel8Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 504, Short.MAX_VALUE)
        .addGroup(
          jPanel8Layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
              jPanel8Layout
                .createSequentialGroup()
                .addContainerGap()
                .addComponent(
                  jPanel18,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  Short.MAX_VALUE
                )
                .addContainerGap()
            )
        )
    );
    jPanel8Layout.setVerticalGroup(
      jPanel8Layout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 56, Short.MAX_VALUE)
        .addGroup(
          jPanel8Layout
            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(
              javax.swing.GroupLayout.Alignment.TRAILING,
              jPanel8Layout
                .createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(
                  jPanel18,
                  javax.swing.GroupLayout.PREFERRED_SIZE,
                  javax.swing.GroupLayout.DEFAULT_SIZE,
                  javax.swing.GroupLayout.PREFERRED_SIZE
                )
            )
        )
    );

    jPanel7.add(jPanel8, java.awt.BorderLayout.PAGE_START);

    jScrollPane2.setBorder(
      javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0))
    );

    javax.swing.GroupLayout tableUserLayout = new javax.swing.GroupLayout(
      tableUser
    );
    tableUser.setLayout(tableUserLayout);
    tableUserLayout.setHorizontalGroup(
      tableUserLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 506, Short.MAX_VALUE)
    );
    tableUserLayout.setVerticalGroup(
      tableUserLayout
        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
        .addGap(0, 260, Short.MAX_VALUE)
    );

    jScrollPane2.setViewportView(tableUser);

    jPanel7.add(jScrollPane2, java.awt.BorderLayout.CENTER);

    jPanel11.add(jPanel7, java.awt.BorderLayout.CENTER);

    jPanel10.add(jPanel11, java.awt.BorderLayout.CENTER);

    add(jPanel10, java.awt.BorderLayout.CENTER);
  } // </editor-fold>//GEN-END:initComponents

  public void addTable() {
    List<BookModel> listBook = BookBUS.getInstance().getAllModels();
    TopProductPanel topProductPanel;
    int top1 = 0;
    int top2 = 0;
    int top3 = 0;
    BookModel bookTop1Model = null;
    BookModel bookTop2Model = null;
    BookModel bookTop3Model = null;
    topBook.setLayout(new GridLayout(3, 1, 5, 5));
    for (BookModel book : listBook) {
      List<CartItemsModel> listCartItems = CartItemsBUS
        .getInstance()
        .searchModel(book.getIsbn().toString(), new String[] { "book_isbn" });
      int value = 0;
      for (CartItemsModel cartItem : listCartItems) {
        value = value + cartItem.getQuantity();
      }
      if (value > top1) {
        bookTop3Model = bookTop2Model;
        bookTop2Model = bookTop1Model;
        bookTop1Model = book;
        top1 = value;
      } else if (value >= top2) {
        bookTop3Model = bookTop2Model;
        bookTop2Model = book;
        top2 = value;
      } else if (value >= top3) {
        bookTop3Model = book;
        top3 = value;
      }
    }
    topProductPanel = new TopProductPanel(1, bookTop1Model.getTitle());
    topBook.add(topProductPanel);
    topProductPanel = new TopProductPanel(2, bookTop2Model.getTitle());
    topBook.add(topProductPanel);
    topProductPanel = new TopProductPanel(3, bookTop3Model.getTitle());
    topBook.add(topProductPanel);

    List<UserModel> listUser = UserBUS.getInstance().getAllModels();
    tableUser.setLayout(new GridLayout(0, 1, 5, 5));
    for (UserModel user : listUser) {
      if (!user.getStatus().toString().equals("BANNED")) {
        LocalDateTime timeNow = LocalDateTime.now();
        Duration duration = Duration.between(user.getCreatedAt(), timeNow);
        long userAges = duration.toDays();
        if (userAges == 0) userAges = 1;
        UserPanel userPanel = new UserPanel(
          1,
          user.getName(),
          user.getPhone(),
          user.getStatus(),
          user.getRole(),
          userAges
        );
        tableUser.add(userPanel);
      }
    }
  }

  public void CartDashboard() {
    List<CartModel> listCart = CartBUS
      .getInstance()
      .searchModel("accept", new String[] { "status" });
    LocalDateTime timeNow = LocalDateTime.now();
    int month = timeNow.getMonthValue();

    int productSoldInMonth = 0;
    int productSoldLastMonth = 0;
    int revenueInMonth = 0;
    int revenueLastMonth = 0;

    for (CartModel cart : listCart) {
      if (cart.getCreatedAt().getMonthValue() == month) {
        List<CartItemsModel> listCartItem = CartItemsBUS
          .getInstance()
          .searchModel(
            String.valueOf(cart.getId()),
            new String[] { "cart_id" }
          );
        productSoldInMonth = productSoldInMonth + listCartItem.size();

        for (CartItemsModel cartItem : listCartItem) {
          revenueInMonth =
            revenueInMonth + cartItem.getPrice() * cartItem.getQuantity();
        }
      }
      if (month - cart.getCreatedAt().getMonthValue() == 1) {
        List<CartItemsModel> listCartItem = CartItemsBUS
          .getInstance()
          .searchModel(
            String.valueOf(cart.getId()),
            new String[] { "cart_id" }
          );
        productSoldLastMonth = productSoldLastMonth + listCartItem.size();

        for (CartItemsModel cartItem : listCartItem) {
          revenueLastMonth =
            revenueLastMonth + cartItem.getPrice() * cartItem.getQuantity();
        }
      }
    }
    float result = (float) 100 / productSoldLastMonth;
    setTotalProductSold.setText(
      String.format("%.02f", result * productSoldInMonth) + "%"
    );
    valueProductSold.setText("Actual Value: " + productSoldInMonth);

    float ratioRevenue = (float) 100 / revenueLastMonth;
    setTotalRevenue.setText(
      String.format("%.02f", ratioRevenue * revenueInMonth) + "%"
    );
    ValueRevenue.setText("Actual Value: " + revenueInMonth);

    List<UserModel> listUser = UserBUS.getInstance().getAllModels();
    int userNearlyInMonth = 0;
    int userNearlyLastMonth = 0;
    for (UserModel user : listUser) {
      if (
        !user.getStatus().toString().equals("BANNED") &&
        user.getCreatedAt().getMonthValue() == month
      ) {
        userNearlyInMonth = userNearlyInMonth + 1;
      }
      if (
        !user.getStatus().toString().equals("BANNED") &&
        month - user.getCreatedAt().getMonthValue() == 1
      ) {
        userNearlyLastMonth = userNearlyLastMonth + 1;
      }
    }
    float ratioNewUser = (float) 100 / userNearlyLastMonth;
    setTotalNewUser.setText(
      String.format("%.02f", ratioNewUser * userNearlyInMonth) + "%"
    );
    valueNewUser.setText("Actual Value: " + userNearlyInMonth);
    if (listCart.isEmpty()) {
      setTotalProductSold.setText("0%");
      setTotalRevenue.setText("0%");
    }
  }

  public void receiveValue(String value) {
    String[] columns = new String[] { "name" };
    tableUser.removeAll();
    List<UserModel> list = UserBUS.getInstance().searchModel(value, columns);
    int serial = 0;
    tableUser.setLayout(new GridLayout(list.size(), 0, 10, 10));
    for (UserModel user : list) {
      if (!user.getStatus().toString().equals("BANNED")) {
        LocalDateTime timeNow = LocalDateTime.now();
        Duration duration = Duration.between(user.getCreatedAt(), timeNow);
        long userAges = duration.toDays();
        UserPanel userPanel = new UserPanel(
          serial,
          user.getName(),
          user.getPhone(),
          user.getStatus(),
          user.getRole(),
          userAges
        );
        tableUser.add(userPanel);
        serial = serial + 1;
      }
    }
    tableUser.revalidate();
    tableUser.repaint();
  }

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JLabel JLabel6;
  private javax.swing.JLabel ValueRevenue;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel17;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel30;
  private javax.swing.JLabel jLabel31;
  private javax.swing.JLabel jLabel32;
  private javax.swing.JLabel jLabel33;
  private javax.swing.JLabel jLabel34;
  private javax.swing.JLabel jLabel35;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel10;
  private javax.swing.JPanel jPanel11;
  private javax.swing.JPanel jPanel13;
  private javax.swing.JPanel jPanel14;
  private javax.swing.JPanel jPanel18;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JPanel jPanel7;
  private javax.swing.JPanel jPanel8;
  private javax.swing.JPanel jPanel9;
  private javax.swing.JScrollPane jScrollPane2;
  private javax.swing.JLabel setTotalNewUser;
  private javax.swing.JLabel setTotalProductSold;
  private javax.swing.JLabel setTotalRevenue;
  private javax.swing.JPanel tableUser;
  private javax.swing.JPanel topBook;
  private javax.swing.JLabel valueNewUser;
  private javax.swing.JLabel valueProductSold;
  // End of variables declaration//GEN-END:variables
}
