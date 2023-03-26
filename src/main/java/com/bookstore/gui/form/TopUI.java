package com.bookstore.gui.form;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

public class TopUI extends JFrame implements MouseListener {

  private JPanel topUI;
  JFrame frame = new JFrame();
  private JLabel actionMenu;
  private JPanel contend;
  private JLabel button;

  public TopUI() {
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }
    topUI(900, 50);
    initFrame();

  }

  private void initFrame() {
    frame.setPreferredSize(new Dimension(1160, 550));
    frame.setBackground(Color.BLACK);
    frame.setLayout(new FlowLayout());
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

  public JPanel topUI(int width, int height) {
    topUI = new JPanel();
    topUI.setPreferredSize(new Dimension(width, height));
    topUI.setLayout(new BorderLayout());

    actionMenu = new JLabel();
    actionMenu.setIcon(new ImageIcon(getClass().getResource("/resources/image/menu.png")));
    actionMenu.setPreferredSize(new Dimension(35, 20));
    topUI.add(actionMenu, BorderLayout.WEST);

    contend = new JPanel();
    contend.setLayout(new FlowLayout(15, 15, 15));

    button = new JLabel();
    button.setIcon(new ImageIcon(getClass().getResource("/resources/image/search.png")));
    contend.add(button);

    button = new JLabel();
    button.setIcon(new ImageIcon(getClass().getResource("/resources/image/announcement.png")));
    contend.add(button);

    button = new JLabel();
    button.setIcon(new ImageIcon(getClass().getResource("/resources/image/account.png")));
    contend.add(button);

    topUI.add(contend, BorderLayout.EAST);

    // frame.add(topUI);
    return topUI;
  }

  public static void main(String[] args) {
    new TopUI();
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
