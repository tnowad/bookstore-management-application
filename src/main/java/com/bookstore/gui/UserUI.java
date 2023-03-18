package com.bookstore.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;

public class UserUI extends JFrame {
    private JPanel list;
    private JPanel topUser;
    private JLabel title_Now;
    private JButton buttonUser;
    private JPanel topEAST;
    private JTable jTableCart;
    private Component scrollPane;
    private JLabel endUser;
    private JButton button_User;
    private JPanel button;

    public JPanel User(){
        list = new JPanel(); // tổng thể dashboard
        list.setLayout(new FlowLayout());

        topUser = new JPanel();
        topUser.setLayout(new BorderLayout());
        topUser.setPreferredSize(new Dimension(1000,30));
        

        title_Now = new JLabel("User"); // cái chữ dashboard 
        title_Now.setFont(new Font("sansserif", 0, 18));
        title_Now.setBorder(new EmptyBorder(0, 10, 0, 0));

        topUser.add(title_Now,BorderLayout.WEST );

        topEAST = new JPanel();
        topEAST.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
        buttonUser = new JButton("Xuất file");
        topEAST.add(buttonUser );
        buttonUser = new JButton("Tạo Mới");
        topEAST.add(buttonUser );

        topUser.add(topEAST, BorderLayout.EAST);

        list.add(topUser);

        endUser = new JLabel();
        endUser.setLayout(new FlowLayout());
        endUser.setPreferredSize(new Dimension(1000,350));

        Object[][] data = {
            {"001", "admin", "12345", "Online","admin","admin@gmail.com","113","31-2-2001","31-2-2002","employee" },
            {"001", "admin", "12345", "Online","admin","admin@gmail.com","113","31-2-2001","31-2-2002","employee" }};
          String[] columnNames = { "Id", "username", "password", "status","name","email","phone","createdAt","updatedAt","role" };
          jTableCart = new JTable(data, columnNames);
          jTableCart.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
          scrollPane = new JScrollPane(jTableCart);
          scrollPane.setPreferredSize(new Dimension(900,350));
          endUser.add(scrollPane );

          button = new JPanel();
          button.setPreferredSize(new Dimension(300,20));
          
          button.setLayout(new GridLayout(1,3));
          buttonUser= new JButton("Thêm");
          button.add(buttonUser);
          buttonUser= new JButton("Lưu");
          button.add(buttonUser);

          list.add(endUser);
          list.add(button);

        return list;
    
    }
}
