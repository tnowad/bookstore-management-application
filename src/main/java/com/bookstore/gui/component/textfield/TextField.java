package com.bookstore.gui.component.textfield;

import javax.swing.JTextField;

import java.awt.Cursor;
import java.awt.Dimension;

import com.bookstore.gui.Theme.ThemeColor;
import com.bookstore.gui.Theme.ThemeFont;

public class TextField extends JTextField {
    public TextField() {
        initComponents();
    }

    private void initComponents() {
        setFont(new ThemeFont().getSmallFont());
        setLabelSize(100, 50);
        setForeground(new ThemeColor().getButtonForeground());
        setBorder(null);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setLabelSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }
}
