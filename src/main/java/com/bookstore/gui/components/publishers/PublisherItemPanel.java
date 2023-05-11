package com.bookstore.gui.components.publishers;

import com.bookstore.gui.forms.publishers.PublisherItemDetailForm;
import com.bookstore.models.PublisherModel;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class PublisherItemPanel extends JPanel {

  private JPanel panel;
  private JLabel setDescription;
  private JLabel setId;
  private JLabel setName;
  private JLabel setSerial;

  public PublisherItemPanel(int serial, PublisherModel publisher) {
    initComponents(serial, publisher);
    addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          PublisherItemDetailForm publisherDetail = new PublisherItemDetailForm(
            publisher
          );
          publisherDetail.setVisible(true);
        }
      }
    );
  }

  private void initComponents(int serial, PublisherModel publisher) {
    setBackground(Color.WHITE);
    setSerial = new JLabel();
    setId = new JLabel();
    setName = new JLabel();
    setDescription = new JLabel();
    panel = new JPanel();
    panel.setBackground(Color.WHITE);

    setLayout(new GridLayout(1, 3));
    panel.setLayout(new GridLayout());

    setSerial.setText("" + serial);
    panel.add(setSerial);

    setId.setText("" + publisher.getId());
    panel.add(setId);

    add(panel);

    setName.setText(publisher.getName());
    setName.setPreferredSize(new Dimension(80, 16));
    add(setName);

    setDescription.setText(publisher.getDescription());
    setDescription.setPreferredSize(new Dimension(100, 16));
    add(setDescription);
  }
}
