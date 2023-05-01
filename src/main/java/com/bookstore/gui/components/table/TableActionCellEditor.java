package com.bookstore.gui.components.table;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

import com.bookstore.gui.components.panel.ActionPanel;

public class TableActionCellEditor extends DefaultCellEditor {

  public TableActionCellEditor() {
    super(new JCheckBox());
  }

  @Override
  public Component getTableCellEditorComponent(
    JTable table,
    Object value,
    boolean isSelected,
    int row,
    int column
  ) {
    ActionPanel actionPanel = (ActionPanel) new ActionPanel();
    actionPanel.addViewListener(e -> {
      System.out.println("View");
    });
    return actionPanel;
  }
}
