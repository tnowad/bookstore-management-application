package com.bookstore.gui.components.publishers;

import com.bookstore.models.PublisherModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PublisherPanel extends JPanel {

  public PublisherPanel(int serial, PublisherModel publisher) {
    initComponents(serial, publisher);
  }

  private void initComponents(int serial, PublisherModel publisher) {
    panel = new JPanel();
    checkBox = new JCheckBox();
    setSerial = new JLabel();
    setId = new JLabel();
    setName = new JLabel();
    setDescription = new JLabel();

    setLayout(new GridLayout(1, 3));

    panel.setLayout(new GridLayout(1, 3));
    panel.add(checkBox);

    setSerial.setText("" + serial);
    panel.add(setSerial);

    setId.setText("" + publisher.getId());
    panel.add(setId);

    add(panel);

    setName.setText(publisher.getName());
    setName.setPreferredSize(new Dimension(100, 16));
    add(setName);

    setDescription.setText(publisher.getDescription());
    setDescription.setPreferredSize(new Dimension(300, 16));
    add(setDescription);
  }

  private JCheckBox checkBox;
  private JPanel panel;
  private JLabel setDescription;
  private JLabel setId;
  private JLabel setName;
  private JLabel setSerial;
}
