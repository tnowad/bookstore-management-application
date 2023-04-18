package com.bookstore.gui.component.button;
import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class IconButton extends JButton {


    public IconButton() {
        super();
        setBackground(new Color(237, 244, 255));
        setForeground(new Color(5, 97, 252));
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public IconButton(String text, Icon icon) {
        super(text, icon);
        setBackground(new Color(237, 244, 255));
        setForeground(new Color(5, 97, 252));
        setFont(new Font("Segoe UI", Font.PLAIN, 14));
        setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(true);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public void setBackgroundColor(Color color) {
        setBackground(color);
    }

    public void setTextColor(Color color) {
        setForeground(color);
    }

    public void setRounded(boolean rounded) {
        if (rounded) {
            setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        } else {
            setBorder(BorderFactory.createEmptyBorder(8, 16, 8, 16));
        }
    }

    public void setHoverColor(Color color) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(color);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(new Color(237, 244, 255));
            }
        });
    }

    public void setHoverTextColor(Color color) {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(color);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(new Color(5, 97, 252));
            }
        });
    }

    public static void main(String[] args) {
        IconButton button = new IconButton("Button", new ImageIcon("icon.png"));
        button.setBackgroundColor(new Color(237, 244, 255));
        button.setTextColor(new Color(5, 97, 252));
        button.setRounded(true);
        button.setHoverColor(new Color(199, 226, 255));
        button.setHoverTextColor(new Color(5, 97, 252));

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());
        frame.add(button);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
