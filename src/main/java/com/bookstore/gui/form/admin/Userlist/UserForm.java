package com.bookstore.gui.form.admin.Userlist;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class UserForm extends JFrame {
    public UserForm(){
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        TableUser tableUser = new TableUser();
        container.add(tableUser);
        add(container);
        initComponents();
    }
    public void initComponents(){   
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Dashboard");
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        setLocationRelativeTo(null);
        
    }

    public static void main(String[] args) {
        new UserForm().setVisible(true);
    }
    
}
