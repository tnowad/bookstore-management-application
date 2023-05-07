package com.bookstore.gui.components.dialogs;

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dialog extends JDialog {

  public Dialog(JFrame parent) {
    super(parent, true);
    getContentPane().add(parent.getContentPane());
    setModal(true);
    setSize(parent.getSize());
    setResizable(parent.isResizable());
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public Dialog(JPanel panel) {
    super();
    getContentPane().add(panel);
    setModal(true);
    setSize(panel.getSize());
    setResizable(false);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
}
