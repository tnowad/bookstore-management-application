package com.bookstore.gui.events.general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class ExitActionListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    int option = JOptionPane.showConfirmDialog(
      null,
      "Are you sure you want to exit?",
      "Exit",
      JOptionPane.YES_NO_OPTION
    );

    if (option == JOptionPane.YES_OPTION) {
      System.exit(0);
    }
  }
}
