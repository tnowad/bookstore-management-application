package com.bookstore.gui.forms.users;

import javax.swing.JFrame;

public class SalaryDetail extends JFrame {
    private static SalaryDetail instance;

    public static SalaryDetail getInstance() {
        if (instance == null) {
            instance = new SalaryDetail();
        }
        return instance;
    }

}
