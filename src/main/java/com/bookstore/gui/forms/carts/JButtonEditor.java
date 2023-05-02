package com.bookstore.gui.forms.carts;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;

class JButtonEditor extends DefaultCellEditor {
    private final JButton deleteButton;

    public JButtonEditor(JCheckBox checkBox) {
        super(checkBox);
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> fireEditingStopped());
    }

    public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
        return deleteButton;
    }

    public Object getCellEditorValue() {
        return "Delete";
    }
}
