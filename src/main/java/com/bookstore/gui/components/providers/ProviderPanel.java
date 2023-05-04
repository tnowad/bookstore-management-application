package com.bookstore.gui.components.providers;

import com.bookstore.models.ProviderModel;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ProviderPanel extends javax.swing.JPanel {

  public ProviderPanel(int serial, ProviderModel providerModel) {
    initComponents(serial, providerModel);
  }

  private void initComponents(int serial, ProviderModel providerModel) {
    panel = new JPanel();
    checkBox = new JCheckBox();
    setSerial = new JLabel();
    setId = new JLabel();
    setName = new JLabel();
    setDescription = new JLabel();

    setLayout(new java.awt.GridLayout(1, 3));

    panel.setLayout(new java.awt.GridLayout(1, 3));
    panel.add(checkBox);

    setSerial.setText("" + serial);
    panel.add(setSerial);

    setId.setText("" + providerModel.getId());
    panel.add(setId);

    add(panel);

    setName.setText(providerModel.getName());
    setName.setPreferredSize(new java.awt.Dimension(100, 16));
    add(setName);

    setDescription.setText(providerModel.getDescription());
    setDescription.setPreferredSize(new java.awt.Dimension(300, 16));
    add(setDescription);
  }

  private JCheckBox checkBox;
  private JPanel panel;
  private JLabel setDescription;
  private JLabel setId;
  private JLabel setName;
  private JLabel setSerial;
}
