package com.bookstore.gui.forms.providers;

import com.bookstore.models.ProviderModel;
import java.awt.*;
import java.awt.event.*;


import javax.swing.*;

public class ProviderPanel extends JPanel implements MouseListener{

  private JCheckBox checkBox;
  private JPanel panel;
  private JLabel setDescription;
  private JLabel setId;
  private JLabel setName;
  private JLabel setSerial;

  public ProviderPanel(int serial, ProviderModel providerModel) {
    initComponents(serial, providerModel);
    addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          ProviderDetail providerDetail = new ProviderDetail(providerModel);
          providerDetail.setVisible(true);
        }
      }
    );
  }

  private void initComponents(int serial, ProviderModel providerModel) {
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

    setId.setText("" + providerModel.getId());
    panel.add(setId);

    add(panel);

    setName.setText(providerModel.getName());
    setName.setPreferredSize(new Dimension(100, 16));
    add(setName);

    setDescription.setText(providerModel.getDescription());
    setDescription.setPreferredSize(new Dimension(300, 16));
    add(setDescription);
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    
  }

  @Override
  public void mousePressed(MouseEvent e) {
    
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    
  }

  @Override
  public void mouseExited(MouseEvent e) {
    
  }
}
