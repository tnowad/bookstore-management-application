package com.bookstore.gui.components.dashboards;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.OrderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.cards.CardPanel;
import com.bookstore.gui.components.tables.Table;
import com.bookstore.models.OrderRecentTableModel;
import com.bookstore.models.StatisticCardModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DashboardPanel extends JPanel {

  private JPanel headerPanel;
  private JPanel contentPanel;

  private CardPanel totalRevenuePanel;
  private CardPanel totalOrderPanel;
  private CardPanel totalProductPanel;
  private CardPanel totalUserPanel;

  private Table table;

  public DashboardPanel() {
    initComponents();
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    headerPanel = new JPanel(new GridBagLayout());
    totalRevenuePanel = new CardPanel();
    totalOrderPanel = new CardPanel();
    totalProductPanel = new CardPanel();
    totalUserPanel = new CardPanel();

    totalRevenuePanel.setColorBottom(Color.decode("#FFC107"));
    totalOrderPanel.setColorBottom(Color.decode("#FFC107"));
    totalProductPanel.setColorBottom(Color.decode("#4CAF50"));
    totalUserPanel.setColorBottom(Color.decode("#2196F3"));

    totalRevenuePanel.setColorTop(Color.decode("#FFC107"));
    totalOrderPanel.setColorTop(Color.decode("#FFC107"));
    totalProductPanel.setColorTop(Color.decode("#4CAF50"));
    totalUserPanel.setColorTop(Color.decode("#2196F3"));

    totalRevenuePanel.setData(
      new StatisticCardModel(
        new ImageIcon("src/main/resources/icons/revenue.png"),
        "Total Revenue",
        "Rp. " + OrderBUS.getInstance().calculateTotalRevenue(),
        "Total revenue from all orders"
      )
    );

    totalOrderPanel.setData(
      new StatisticCardModel(
        new ImageIcon("src/main/resources/icons/order.png"),
        "Total Order",
        OrderBUS.getInstance().getAllModels().size() + "",
        "Total order from all users"
      )
    );

    totalProductPanel.setData(
      new StatisticCardModel(
        new ImageIcon("src/main/resources/icons/product.png"),
        "Total Product",
        BookBUS.getInstance().getAllModels().size() + "",
        "Total product in the store"
      )
    );

    totalUserPanel.setData(
      new StatisticCardModel(
        new ImageIcon("src/main/resources/icons/user.png"),
        "Total User",
        UserBUS.getInstance().getAllModels().size() + "",
        "Total user in the store"
      )
    );

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

    table = new Table();

    JLabel label = new JLabel("Recent Orders");

    contentPanel.add(table, BorderLayout.CENTER);
    contentPanel.add(label, BorderLayout.NORTH);

    JScrollPane scrollPane = new JScrollPane(table);
    contentPanel.add(scrollPane, BorderLayout.CENTER);

    add(contentPanel, BorderLayout.CENTER);
  }
}
