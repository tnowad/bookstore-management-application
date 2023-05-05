package com.bookstore.gui.forms.shop;

import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.theme.ThemeFont;
import java.awt.Panel;

public class NoData extends Panel {

  public NoData() {
    initComponents();
  }

  private void initComponents() {
    noDataLabel = new Label();

    noDataLabel.setFont(new ThemeFont().getLargeFont());
    noDataLabel.setText("No Data");
    noDataLabel.setPreferredSize(new java.awt.Dimension(400, 200));
    add(noDataLabel);
  }

  private Label noDataLabel;
}
