package com.bookstore.gui.test;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridLayoutExample {

  public static void main(String[] args) {
    JFrame frame = new JFrame("Grid Layout Example");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel(new GridLayout(5, 5, 10, 10));

    for (int i = 1; i <= 25; i++) {
      JButton button = new JButton("Nút " + i);
      panel.add(button);
    }

    frame.add(panel);
    frame.pack();
    frame.setVisible(true);
  }
}
