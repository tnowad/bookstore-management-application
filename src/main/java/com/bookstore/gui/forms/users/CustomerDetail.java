package com.bookstore.gui.forms.users;

import javax.swing.JFrame;

public class CustomerDetail extends JFrame {
    private static CustomerDetail instance;

    public static CustomerDetail getInstance() {
        if (instance == null) {
            instance = new CustomerDetail();
        }
        return instance;
    }
}
