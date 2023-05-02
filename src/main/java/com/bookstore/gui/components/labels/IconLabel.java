package com.bookstore.gui.components.labels;

import java.awt.Cursor;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.bookstore.gui.theme.ThemeColor;

public class IconLabel extends JLabel {
    public IconLabel(String path) {
        initComponents(path);
    }

    private void initComponents(String path) {

        this.setIcon(new ImageIcon(path));
        this.setLabelSize(20, 20);
        this.setForeground(new ThemeColor().getButtonForeground());
        this.setBorder(null);
        this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setLabelSize(int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
    }

}