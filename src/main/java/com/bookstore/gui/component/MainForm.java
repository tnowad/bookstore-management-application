package com.bookstore.gui.component;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.border.EmptyBorder;
import java.awt.Component;

/**
 * The MainForm class represents the main frame of the Bookstore
 * application.
 * It extends the JPanel class and provides methods to initialize the frame,
 * show a form inside the main frame, and set layout properties.
 */
public class MainForm extends JPanel {

  /**
   * Initializes the main frame by calling the initComponents() and
   * setLayoutProperties() methods.
   */
  public MainForm() {
    initializeComponents();
    setLayoutProperties();
  }

  /**
   * Shows a form inside the main frame. Removes any existing components
   * and adds the specified form if it is not null.
   *
   * @param formComponent The component to show inside the main frame.
   */
  public void showForm(Component formComponent) {
    removeAll();
    if (formComponent != null) {
      add(formComponent);
      refreshFrame();
    }
  }

  /**
   * Initializes the main frame components using the GroupLayout layout manager.
   */
  private void initializeComponents() {
    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    addHorizontalGap(layout);
    addVerticalGap(layout);
  }

  /**
   * Sets the layout properties of the main frame to be transparent,
   * to have a border, and to use the BorderLayout layout manager.
   */
  private void setLayoutProperties() {
    setOpaque(false);
    setBorder(new EmptyBorder(10, 20, 10, 20));
    setLayout(new BorderLayout());
  }

  /**
   * Adds a horizontal gap to the specified GroupLayout layout.
   *
   * @param layout The layout to add the gap to.
   */
  private void addHorizontalGap(GroupLayout layout) {
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE));
  }

  /**
   * Adds a vertical gap to the specified GroupLayout layout.
   *
   * @param layout The layout to add the gap to.
   */
  private void addVerticalGap(GroupLayout layout) {
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE));
  }

  /**
   * Refreshes the main frame by calling the repaint() and revalidate() methods.
   */
  private void refreshFrame() {
    repaint();
    revalidate();
  }
}
