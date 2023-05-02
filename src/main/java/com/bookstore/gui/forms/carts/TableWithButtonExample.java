package com.bookstore.gui.forms.carts;

import java.awt.Component;

import javax.swing.*;
import javax.swing.table.*;

public class TableWithButtonExample extends JFrame {
    private JTable table;

    public TableWithButtonExample() {
        // Tạo bảng với 3 cột
        String[] columnNames = {"ID", "Name", "Action"};
        Object[][] data = {
            {1, "Alice", ""},
            {2, "Bob", ""},
            {3, "Charlie", ""}
        };
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        table = new JTable(model);

        // Đặt renderer cho cột "Action"
        TableColumn actionColumn = table.getColumn("Action");
        actionColumn.setCellRenderer(new JButtonRenderer());

        // Đặt editor cho cột "Action"
        actionColumn.setCellEditor(new JButtonEditor(new JCheckBox()));

        // Thêm bảng vào frame và hiển thị
        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args) {
        new TableWithButtonExample();
    }
}

// class JButtonRenderer implements TableCellRenderer {
//     private JButton button;

//     public JButtonRenderer() {
//         button = new JButton();
//     }

//     public Component getTableCellRendererComponent(JTable table, Object value,
//         boolean isSelected, boolean hasFocus, int row, int column) {
//         button.setText((value == null) ? "" : value.toString());
//         return button;
//     }
// }

// class JButtonEditor extends DefaultCellEditor {
//     private JButton button;

//     public JButtonEditor(JCheckBox checkBox) {
//         super(checkBox);
//         button = new JButton();
//         button.setOpaque(true);
//         button.addActionListener(e -> fireEditingStopped());
//     }

//     public Component getTableCellEditorComponent(JTable table, Object value,
//         boolean isSelected, int row, int column) {
//         button.setText((value == null) ? "" : value.toString());
//         return button;
//     }

//     public Object getCellEditorValue() {
//         return button.getText();
//     }
// }

