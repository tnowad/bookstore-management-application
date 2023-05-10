package com.bookstore.gui.forms.dashboards;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.OrderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.cards.CardPanel;
import com.bookstore.gui.components.tables.Table;
import com.bookstore.interfaces.ISearchable;
import com.bookstore.models.OrderModel;
import com.bookstore.models.gui.StatisticCardModel;
import com.bookstore.models.tables.OrderRecentTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

public class DashboardPanel extends JPanel implements ISearchable {

  private JPanel headerPanel;
  private JPanel contentPanel;

  private CardPanel totalRevenuePanel;
  private CardPanel totalOrderPanel;
  private CardPanel totalProductPanel;
  private CardPanel totalUserPanel;

  private Table table;

  public DashboardPanel() {
    initComponents();
    updateTable();
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    headerPanel = new JPanel(new GridBagLayout());
    headerPanel.setBackground(Color.WHITE);

    totalRevenuePanel =
      new CardPanel(
        new StatisticCardModel(
          new ImageIcon("src/main/java/resources/icons/revenue.png"),
          "Total Revenue",
          "Rp. " + OrderBUS.getInstance().calculateTotalRevenue(),
          "Total revenue from all orders"
        )
      );
    totalOrderPanel =
      new CardPanel(
        new StatisticCardModel(
          new ImageIcon("src/main/java/resources/icons/cart.png"),
          "Total Order",
          OrderBUS.getInstance().getAllModels().size() + "",
          "Total order from all users"
        )
      );
    totalProductPanel =
      new CardPanel(
        new StatisticCardModel(
          new ImageIcon("src/main/java/resources/icons/book.png"),
          "Total Product",
          BookBUS.getInstance().getAllModels().size() + "",
          "Total product in the store"
        )
      );
    totalUserPanel =
      new CardPanel(
        new StatisticCardModel(
          new ImageIcon("src/main/java/resources/icons/user.png"),
          "Total User",
          UserBUS.getInstance().getAllModels().size() + "",
          "Total user in the store"
        )
      );

    totalOrderPanel.setColor(Color.decode("#FFC107"));
    totalRevenuePanel.setColor(Color.decode("#FFC107"));
    totalProductPanel.setColor(Color.decode("#4CAF50"));
    totalUserPanel.setColor(Color.decode("#2196F3"));

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.weightx = 1;
    gridBagConstraints.weighty = 1;
    headerPanel.add(totalRevenuePanel, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    headerPanel.add(totalOrderPanel, gridBagConstraints);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    headerPanel.add(totalProductPanel, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 1;
    headerPanel.add(totalUserPanel, gridBagConstraints);

    add(headerPanel, BorderLayout.NORTH);

    contentPanel = new JPanel(new BorderLayout());
    contentPanel.setBackground(Color.WHITE);

    table = new Table();
    JLabel label = new JLabel("Recent Orders");
    contentPanel.add(label, BorderLayout.NORTH);
    contentPanel.add(table, BorderLayout.CENTER);
    JScrollPane scrollPane = new JScrollPane(table);
    contentPanel.add(scrollPane, BorderLayout.CENTER);
    add(contentPanel, BorderLayout.CENTER);
  }

  public void updateTable() {
    List<OrderModel> orders = OrderBUS.getInstance().getAllModels();
    DefaultTableModel model = new OrderRecentTableModel(orders);
    table.setModel(model);
  }

  @Override
  public void search(String keyword) {
    List<OrderModel> orders = OrderBUS.getInstance().getAllModels();
    List<OrderModel> filteredOrders = new java.util.ArrayList<>();

    for (OrderModel order : orders) {
      if (
        (
          (UserBUS.getInstance().getModelById(order.getCustomerId()) != null)
            ? UserBUS
              .getInstance()
              .getModelById(order.getCustomerId())
              .getEmail()
              .toLowerCase()
              .contains(keyword.toLowerCase())
            : false
        ) ||
        UserBUS
          .getInstance()
          .getModelById(order.getEmployeeId())
          .getEmail()
          .toLowerCase()
          .contains(keyword.toLowerCase())
      ) {
        filteredOrders.add(order);
      }
    }

    DefaultTableModel model = new OrderRecentTableModel(filteredOrders);
    table.setModel(model);
  }
}
