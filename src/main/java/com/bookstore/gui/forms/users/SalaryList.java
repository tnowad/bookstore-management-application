package com.bookstore.gui.forms.users;

public class SalaryList {
    private static SalaryList instance;

    public static SalaryList getInstance() {
        if (instance == null) {
            instance = new SalaryList();
        }
        return instance;
    }
}
