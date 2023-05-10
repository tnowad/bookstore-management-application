package com.bookstore.gui.forms.reports;

import com.bookstore.gui.components.cards.CardPanel;
import com.bookstore.models.CartModel;
import com.bookstore.models.gui.StatisticCardModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class SalesReportPanel extends JPanel {

  private CardPanel totalOrdersCardPanel;
  private CardPanel totalSalesCardPanel;
  private CardPanel totalProfitCardPanel;
  private CardPanel totalBooksSoldCardPanel;

  public SalesReportPanel() {
    initComponents();
  }

  private void initComponents() {
    totalOrdersCardPanel =
      new CardPanel(new StatisticCardModel(new ImageIcon(""), "", "", ""));
    totalSalesCardPanel =
      new CardPanel(new StatisticCardModel(new ImageIcon(""), "", "", ""));
    totalProfitCardPanel =
      new CardPanel(new StatisticCardModel(new ImageIcon(""), "", "", ""));
    totalBooksSoldCardPanel =
      new CardPanel(new StatisticCardModel(new ImageIcon(""), "", "", ""));

    add(totalOrdersCardPanel);
    add(totalSalesCardPanel);
    add(totalProfitCardPanel);
    add(totalBooksSoldCardPanel);
  }
}
