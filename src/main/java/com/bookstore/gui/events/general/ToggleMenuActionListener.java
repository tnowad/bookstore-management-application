package com.bookstore.gui.events.general;

import com.bookstore.gui.main.MainUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToggleMenuActionListener implements ActionListener {

  @Override
  public void actionPerformed(ActionEvent e) {
    MainUI mainUI = MainUI.getInstance();
    mainUI.toggleMenu();
  }
}
