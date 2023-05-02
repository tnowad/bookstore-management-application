package com.bookstore.gui.forms.books;

import javax.swing.JFrame;

public class BookDetail extends JFrame {
    private static BookDetail instance;

    public static BookDetail getInstance() {
        if (instance == null) {
            instance = new BookDetail();
        }
        return instance;
    }
}
