package com.bookstore.gui.components.label;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.bookstore.gui.theme.ThemeColor;
import com.bookstore.gui.theme.ThemeFont;

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