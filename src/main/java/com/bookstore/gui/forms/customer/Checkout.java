package com.bookstore.gui.forms.customer;

import javax.swing.JPanel;

public class Checkout extends JPanel {
    private static Checkout instance;

    private Checkout() {

    }

    public static Checkout getInstance() {
        if (instance == null) {
            instance = new Checkout();
        }
        return instance;
    }
}
