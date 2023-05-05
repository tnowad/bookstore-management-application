package com.bookstore.gui.components.authors;

import com.bookstore.models.AuthorModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AuthorPanel extends javax.swing.JPanel {

  private JCheckBox checkBox;
  private JPanel panel;
  private JLabel setDescription;
  private JLabel setId;
  private JLabel setName;
  private JLabel setSerial;

  public AuthorPanel(int serial, AuthorModel author) {
    initComponents(serial, author);
  }

  private void initComponents(int serial, AuthorModel author) {
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

    setId.setText("" + author.getId());
    panel.add(setId);

    add(panel);

    setName.setText(author.getName());
    setName.setPreferredSize(new Dimension(100, 16));
    add(setName);

    setDescription.setText(author.getDescription());
    setDescription.setPreferredSize(new Dimension(300, 16));
    add(setDescription);
  }
}