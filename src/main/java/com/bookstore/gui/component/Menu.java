package com.bookstore.gui.component;

import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class Menu extends JPanel {
  private JScrollPane scrollPane;

  private MigLayout layout;
  private boolean enableMenu;
  private boolean showMenu;

  public Menu() {
    initComponent();
    handleEvent();
    initFrame();
  }

  private void initComponent() {
    scrollPane = new JScrollPane();

    setOpaque(false);

    scrollPane.getViewport().setOpaque(false);
    // Todo: custom scroll bar
    scrollPane.setVerticalScrollBar(new JScrollBar());
    layout = new MigLayout(
        "wrap, fillx, insets 0",
        "[fill]",
        "[]0[]");
  }

  private void handleEvent() {
  }

  private void initFrame() {
  }
}
