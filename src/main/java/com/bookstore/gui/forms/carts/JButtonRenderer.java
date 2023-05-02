package com.bookstore.gui.forms.carts;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

class JButtonRenderer implements TableCellRenderer {
    private final JButton deleteButton;

    public JButtonRenderer() {
        deleteButton = new JButton("Delete");
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        return deleteButton;
    }
}
