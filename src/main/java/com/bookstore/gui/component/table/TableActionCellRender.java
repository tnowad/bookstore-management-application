package com.bookstore.gui.component.table;

import com.bookstore.gui.component.panel.ActionPanel;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class TableActionCellRender extends DefaultTableCellRenderer {

  @Override
  public Component getTableCellRendererComponent(
    JTable table,
    Object value,
    boolean isSelected,
    boolean hasFocus,
    int row,
    int column
  ) {
    Component component = super.getTableCellRendererComponent(
      table,
      value,
      isSelected,
      hasFocus,
      row,
      column
    );
    ActionPanel actionPanel = (ActionPanel) new ActionPanel();

    actionPanel.addViewListener(e -> {
      System.out.println("View");
    });
    actionPanel.addUpdateListener(e -> {
      System.out.println("Edit");
    });
    actionPanel.addDeleteListener(e -> {
      System.out.println("Delete");
    });

    if (isSelected == false && row % 2 == 0) {
      actionPanel.setBackground(Color.WHITE);
    } else {
      actionPanel.setBackground(component.getBackground());
    }

    return actionPanel;
  }
}
