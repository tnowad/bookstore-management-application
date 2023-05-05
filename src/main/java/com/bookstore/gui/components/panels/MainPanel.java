package com.bookstore.gui.components.panels;

import com.bookstore.interfaces.ISearchable;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainPanel extends JPanel {

  private static MainPanel instance;

  private Deque<Component> formStack = new ArrayDeque<>();

  public static MainPanel getInstance() {
    if (instance == null) {
      instance = new MainPanel();
    }
    return instance;
  }

  public MainPanel() {
    formStack = new ArrayDeque<>();
    initializeComponents();
  }

  public void showForm(Component formComponent) {
    endFormStack();
    if (formComponent != null) {
      formStack.push(formComponent);
      removeAll();
      add(formComponent);
      refreshFrame();
    }
  }

  public void showFormStack(Component formComponent) {
    if (formComponent != null) {
      formStack.push(formComponent);
      removeAll();
      add(formComponent);
      refreshFrame();
    }
  }

  public void endFormStack() {
    formStack.clear();
  }

  public void backToPreviousForm() {
    formStack.pop();
    Component previousForm = formStack.peek();
    removeAll();
    add(previousForm);
    refreshFrame();
  }

  private void initializeComponents() {
    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    setBorder(new EmptyBorder(5, 5, 5, 5));
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);
  }

  private void refreshFrame() {
    repaint();
    revalidate();
  }

  public void search(String text) {
    if (getComponentCount() == 0) {
      JOptionPane.showMessageDialog(
        null,
        "No form is currently displayed",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
      return;
    }

    Component currentForm = getComponent(0);
    if (!(currentForm instanceof ISearchable)) {
      JOptionPane.showMessageDialog(
        null,
        "The current form does not support searching",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
      return;
    }

    ISearchable searchableForm = (ISearchable) currentForm;
    searchableForm.search(text);

    refreshFrame();
  }

  public static void destroyInstance() {
    instance = null;
  }
}
