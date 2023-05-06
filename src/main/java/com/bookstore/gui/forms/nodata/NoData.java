package com.bookstore.gui.forms.nodata;

import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.theme.ThemeFont;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Panel;
import javax.swing.*;


public class NoData extends Panel {

  private Label noDataLabel;
  private JLabel noDataIconLabel;

  public NoData(String name) {
    initComponents(name);
  }

  private void initComponents(String name) {

    noDataIconLabel = new JLabel();
    noDataIconLabel.setIcon(new ImageIcon("src/main/java/resources/images/logo.png"));
    noDataIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(noDataIconLabel);

    noDataLabel = new Label();
    noDataLabel.setFont(new ThemeFont().getLargeFont());
    noDataLabel.setText(name);
    noDataLabel.setPreferredSize(new Dimension(600, 200));
    noDataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(noDataLabel);

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  public static void main(String[] args) {
    new JFrame() {
      {
        NoData noData = new NoData("test");
        add(noData);
        setSize(1000, 1000);
        setLocationRelativeTo(null);
        setVisible(true);
      }
    };
  }
}