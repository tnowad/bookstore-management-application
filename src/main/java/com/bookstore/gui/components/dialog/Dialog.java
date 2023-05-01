package com.bookstore.gui.components.dialog;

import javax.swing.JDialog;
import javax.swing.JFrame;

public class Dialog extends JDialog {

    public Dialog(JFrame parent) {
        super(parent, true);
        getContentPane().add(parent.getContentPane());
        setModal(true);
        setSize(400, 700);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
}
