package com.bookstore.gui.component.button;

import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JLabel;

import com.bookstore.gui.Theme.ThemeColor;
import com.bookstore.gui.Theme.ThemeFont;

public class Label extends JLabel {
    public Label(String name) {
        initComponents(name);
    }

    private void initComponents(String name) {
        setText(name);
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
