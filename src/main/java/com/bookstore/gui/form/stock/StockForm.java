package com.bookstore.gui.form.stock;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class StockForm extends JPanel {

  private static final long serialVersionUID = 1L;
  private LayoutManager layout;

  public StockForm() {
    initComponents();
  }

  private void initComponents() {
    layout = new BorderLayout();
    setLayout(layout);
    add(new StockTable(), BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1200, 800));
    frame.setTitle("Stock Form");
    frame.setLocationRelativeTo(null);
    frame.getContentPane().add(new StockForm());
    frame.pack();
    frame.setVisible(true);
  }
}
