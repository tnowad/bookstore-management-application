package com.bookstore.gui.components.passwordfields;

import javax.swing.JPasswordField;

import com.bookstore.gui.theme.ThemeColor;
import com.bookstore.gui.theme.ThemeFont;

import java.awt.Cursor;
import java.awt.Dimension;

public class PasswordField extends JPasswordField {
    public PasswordField() {
        initComponents();
    }

    private void initComponents() {
        setFont(new ThemeFont().getSmallFont());
        setLabelSize(100, 20);
        setForeground(new ThemeColor().getButtonForeground());
        setBorder(null);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setLabelSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
    }
}
