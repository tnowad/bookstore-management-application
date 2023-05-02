package com.bookstore.gui.forms.users;

import javax.swing.JFrame;

public class EmployeeDetail extends JFrame {
    private static EmployeeDetail instance;

    public static EmployeeDetail getInstance() {
        if (instance == null) {
            instance = new EmployeeDetail();
        }
        return instance;
    }
}
