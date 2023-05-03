package com.bookstore.gui.components.panels;

import com.bookstore.gui.components.dialogs.Dialog;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel {

  private static MainPanel instance;

  public static MainPanel getInstance() {
    if (instance == null) {
      instance = new MainPanel();
    }
    return instance;
  }

  public MainPanel() {
    initializeComponents();
    setLayoutProperties();
  }

  public void showForm(Component formComponent) {
    removeAll();
    if (formComponent != null) {
      add(formComponent);
      refreshFrame();
    }
  }

  private void initializeComponents() {
    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    addHorizontalGap(layout);
    addVerticalGap(layout);
  }

  private void setLayoutProperties() {
    setOpaque(false);
    setBorder(new EmptyBorder(10, 20, 10, 20));
    setLayout(new BorderLayout());
  }

  private void addHorizontalGap(GroupLayout layout) {
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 400, Short.MAX_VALUE)
    );
  }

  private void addVerticalGap(GroupLayout layout) {
    layout.setVerticalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 300, Short.MAX_VALUE)
    );
  }

  private void refreshFrame() {
    repaint();
    revalidate();
  }

  public void search(String text) {
    Component currentForm = null;
    try {
      currentForm = getComponent(0);
    } catch (ArrayIndexOutOfBoundsException e) {
      JOptionPane.showMessageDialog(
        null,
        "No form is currently displayed",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
      return;
    }
    if (currentForm != null && currentForm instanceof ISearchable) {
      ((ISearchable) currentForm).search(text);
    } else {
      JOptionPane.showMessageDialog(
        null,
        "The current form does not support searching",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
    }
    refreshFrame();
  }
}
