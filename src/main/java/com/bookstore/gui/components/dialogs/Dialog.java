package com.bookstore.gui.components.dialogs;

import javax.swing.JDialog;
import javax.swing.JFrame;

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
}
