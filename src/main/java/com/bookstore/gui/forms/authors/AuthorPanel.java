package com.bookstore.gui.forms.authors;

import com.bookstore.models.AuthorModel;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class AuthorPanel extends JPanel implements MouseListener {

  private JPanel panel;
  private JLabel setDescription;
  private JLabel setId;
  private JLabel setName;
  private JLabel setSerial;

  public AuthorPanel(int serial, AuthorModel author) {
    initComponents(serial, author);
    addMouseListener(
      new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          AuthorDetail authorDetail = new AuthorDetail(author);
          authorDetail.setVisible(true);
        }
      }
    );
  }

  private void initComponents(int serial, AuthorModel author) {
    setSerial = new JLabel();
    setId = new JLabel();
    setName = new JLabel();
    setDescription = new JLabel();
    panel = new JPanel();

    setLayout(new GridLayout(1, 3));
    panel.setLayout(new GridLayout());

    setSerial.setText("" + serial);
    panel.add(setSerial);

    setId.setText("" + author.getId());
    panel.add(setId);

    add(panel);

    setName.setText(author.getName());
    setName.setPreferredSize(new Dimension(80, 16));
    add(setName);

    setDescription.setText(author.getDescription());
    setDescription.setPreferredSize(new Dimension(100, 16));
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
