package com.bookstore.gui.component.passwordfield;

import javax.swing.JPasswordField;
import java.awt.Cursor;
import java.awt.Dimension;

import com.bookstore.gui.Theme.ThemeColor;
import com.bookstore.gui.Theme.ThemeFont;

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
