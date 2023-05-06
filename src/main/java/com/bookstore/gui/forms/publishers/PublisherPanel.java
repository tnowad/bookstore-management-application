package com.bookstore.gui.forms.publishers;

import com.bookstore.models.PublisherModel;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class PublisherPanel extends JPanel implements MouseListener{

  private JCheckBox checkBox;
  private JPanel panel;
  private JLabel setDescription;
  private JLabel setId;
  private JLabel setName;
  private JLabel setSerial;

  public PublisherPanel(int serial, PublisherModel publisher) {
    initComponents(serial, publisher);
    addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          PublisherDetail publisherDetail = new PublisherDetail(publisher);
          publisherDetail.setVisible(true);
        }
      }
    );
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
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
  }
}