package com.bookstore.gui.swing.table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableCellAction extends DefaultCellEditor {

  private ModelAction data;

  public TableCellAction() {
    super(new JCheckBox());
  }

  @Override
  public Component getTableCellEditorComponent(JTable jTable, Object object, boolean bln, int i, int i1) {
    data = (ModelAction) object;
    Action cell = new Action(data);
    cell.setBackground(new Color(239, 244, 255));
    return cell;
  }

  // This method to pass data to cell render when focus lose in cell
  @Override
  public Object getCellEditorValue() {
    return data;
  }
}
