package com.bookstore.gui.forms.shop;

import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.theme.ThemeFont;

import java.awt.Dimension;
import java.awt.Panel;

public class NoData extends Panel {

  private Label noDataLabel;

  public NoData(String name) {
    initComponents(name);
  }

  private void initComponents(String name) {
    noDataLabel = new Label();
    System.out.println(5);
    noDataLabel.setFont(new ThemeFont().getLargeFont());
    noDataLabel.setText(name);
    noDataLabel.setPreferredSize(new Dimension(600, 200));
    add(noDataLabel);
  }
}
