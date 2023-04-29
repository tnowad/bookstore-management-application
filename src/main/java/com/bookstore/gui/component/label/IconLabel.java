package com.bookstore.gui.component.label;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.bookstore.gui.Theme.ThemeColor;
import com.bookstore.gui.Theme.ThemeFont;

public class IconLabel extends JLabel {
    public IconLabel(String path) {
        initComponents(path);
    }

    private void initComponents(String path) {

        setIcon(new ImageIcon(path));
        setLabelSize(100, 50);
        setForeground(new ThemeColor().getButtonForeground());
        setBorder(null);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setLabelSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }

}