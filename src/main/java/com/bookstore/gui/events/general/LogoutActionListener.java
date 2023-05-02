package com.bookstore.gui.events.general;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogoutActionListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    System.out.println("Logout");
  }
}
