package com.bookstore.gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminUI extends JFrame implements MouseListener{

  Dashboard db = new Dashboard();
  UserUI us = new UserUI();
  JFrame frame = new JFrame();
  private JPanel top;
  private JPanel homeMenu;

  
  private JLabel jButton_ActionMenu;
  private JTextField jText_search;
  private JLabel jButton_search;
  private JLabel jButton_Menu;
  private JLabel jButton_announcement;

 
  private JLabel jButton_account;
  private JPanel topWest;
  private JLabel jLogo;
  private JPanel topEAST;
  private JLabel jButton_MenuItem;
  private JLabel jButton_MenuTable;



  public AdminUI(){    
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }
    initComponent();
    initFrame();
    
  }
    
  public void initComponent(){
    frame.getContentPane().setLayout(new BorderLayout());
    initMenu();
    initContend();    
    frame.getContentPane().add(db.initDashboard(),BorderLayout.CENTER);
  }
  public void initContend() {
    //khởi tạo home top
    top = new JPanel();
    top.setLayout(new BorderLayout());
    
    //set font home top
    top.setPreferredSize(new Dimension(1000, 50));
    top.setFont(new Font("sansserif", 0, 16));
    
    // Các button trong top
    topWest = new JPanel();
    topWest.setLayout(new FlowLayout());
    jLogo = new JLabel("Book Shop");
    jLogo.setFont(new Font("sansserif", 0, 24));
    jLogo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminUI.class.getResource("/resources/image/cart.png"))));
    topWest.add(jLogo);

    jButton_ActionMenu = new JLabel();
    jButton_ActionMenu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminUI.class.getResource("/resources/image/menu.png"))));
    jButton_ActionMenu.setPreferredSize(new Dimension(30,30));
    jButton_ActionMenu.addMouseListener(this);
    topWest.add(jButton_ActionMenu);

    
    topEAST = new JPanel();
    topEAST.setLayout(new FlowLayout(FlowLayout.CENTER,10,5));
    jButton_account = new JLabel();
    jButton_account.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminUI.class.getResource("/resources/image/account.png"))));
    jButton_account.setPreferredSize(new Dimension(30,30));

    jButton_search = new JLabel();
    jButton_search.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminUI.class.getResource("/resources/image/search.png"))));
    jButton_search.setPreferredSize(new Dimension(30,30));
    jButton_search.addMouseListener(this);

    jButton_announcement = new JLabel();
    jButton_announcement.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminUI.class.getResource("/resources/image/announcement.png"))));
    jButton_announcement.setPreferredSize(new Dimension(30,30));

    jText_search = new JTextField("Nhập tại đây nhó");
    jText_search.setPreferredSize(new Dimension(300,20));

    topEAST.add(jText_search );
    topEAST.add(jButton_search);
    topEAST.add(jButton_announcement);
    topEAST.add(jButton_account);
    



    //add các button vào home top
    top.add(topWest,BorderLayout.WEST);
    top.add(topEAST,BorderLayout.EAST);
    
    
    frame.getContentPane().add(top,BorderLayout.NORTH);
    
  }
  private void initFrame() {
    frame.setPreferredSize(new Dimension(1160, 550));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  
  private void initMenu(){
    homeMenu = new JPanel();
    homeMenu.setPreferredSize(new Dimension(150, 1000));
    homeMenu.setLayout(new GridLayout(10,1));


    jButton_Menu = new JLabel("Dashboard");
    jButton_Menu.setName("Dashboard");
    jButton_Menu.setFont(new Font("sansserif", 0, 12));
    jButton_Menu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminUI.class.getResource("/resources/image/dashboard.png"))));
    jButton_Menu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
    jButton_Menu.addMouseListener(this);
    homeMenu.add(jButton_Menu);

    jButton_Menu = new JLabel("Table");
    jButton_Menu.setName("Table");
    jButton_Menu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminUI.class.getResource("/resources/image/notif.png"))));
    jButton_Menu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
    jButton_Menu.setFont(new Font("sansserif", 0, 12));
    jButton_Menu.addMouseListener(this);
    homeMenu.add(jButton_Menu);

    jButton_MenuTable = new JLabel();
    jButton_MenuTable.setLayout(new GridLayout(2,1));

    jButton_MenuItem = new JLabel("User");
    jButton_MenuItem.setName("User");
    jButton_MenuItem.setFont(new Font("sansserif", 0, 11));
    jButton_MenuItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminUI.class.getResource("/resources/image/dot.png"))));
    jButton_MenuItem.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    jButton_MenuItem.addMouseListener(this);
    jButton_MenuTable.add(jButton_MenuItem);

    jButton_MenuItem = new JLabel("Order");
    jButton_MenuItem.setFont(new Font("sansserif", 0, 11));
    jButton_MenuItem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminUI.class.getResource("/resources/image/dot.png"))));
    jButton_MenuItem.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 0));
    jButton_MenuTable.add(jButton_MenuItem);

  
    jButton_Menu = new JLabel("Messenger");
    jButton_Menu.setName("Messenger");
    jButton_Menu.setFont(new Font("sansserif", 0, 12));
    jButton_Menu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(AdminUI.class.getResource("/resources/image/dashboard.png"))));
    jButton_Menu.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.GRAY));
    homeMenu.add(jButton_Menu);
    
    
    homeMenu.setBackground(Color.BLUE);
    frame.getContentPane().add(homeMenu,BorderLayout.WEST);
  }

  public void actionMenu(MouseEvent e){
    Component component = (Component) e.getSource();
    String name = component.getName();

    Container contentPane = frame.getContentPane(); // frame là một đối tượng JFrame
    Component[] components = contentPane.getComponents(); // Lấy tất cả các thành phần con
    if(name == "User"){
      frame.getContentPane().remove(components[2]);
      frame.getContentPane().add(us.User(),BorderLayout.CENTER);
      System.out.println(components[2].getName()); 
      frame.revalidate();
      frame.repaint();
    }
    if(name == "Dashboard"){
      frame.getContentPane().remove(components[2]);
      frame.getContentPane().add(db.initDashboard(),BorderLayout.CENTER);
      System.out.println(components[2].getName()); 
      frame.revalidate();
      frame.repaint();
    }
  }
 
  public void setMenuItem(MouseEvent e){
    Component component = (Component) e.getSource();
    String name = component.getName();

    Component[] components = homeMenu.getComponents();
    System.out.print(components[2].getName());
    String name2 = components[2].getName();

    if(name == "Table"){
      if(name2 == "Messenger"){
        homeMenu.add(jButton_MenuTable,2 );
        homeMenu.revalidate();
        homeMenu.repaint();
      }
      else{
        homeMenu.remove(jButton_MenuTable);
        homeMenu.revalidate();
        homeMenu.repaint();
      }
    }
  }
  

  public static void main(String[] args) {
    new AdminUI();
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    setMenuItem(e);
    actionMenu(e);
    if(e.getSource()== jButton_ActionMenu){
      if(homeMenu.getWidth()>50){
        ActionListener shrinkListener = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int newWidth = homeMenu.getWidth() - 5;
            int newHeight = homeMenu.getHeight() - 5;
            if (homeMenu.getWidth()==50) {
              ((Timer) e.getSource()).stop();
            } else {
              homeMenu.setPreferredSize(new Dimension(newWidth,newHeight));
              homeMenu.revalidate();
              homeMenu.repaint();
            }
          }
        };
        Timer timer = new Timer(10, shrinkListener);
        timer.start();
      }
      else{
        ActionListener shrinkListener = new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            int newWidth = homeMenu.getWidth() + 5;
            int newHeight = homeMenu.getHeight() + 5;
            if (homeMenu.getWidth()==150) {
              ((Timer) e.getSource()).stop();
            } else {
              homeMenu.setPreferredSize(new Dimension(newWidth,newHeight));
              homeMenu.revalidate();
              homeMenu.repaint();
            }
          }
        };
        Timer timer = new Timer(10, shrinkListener);
        timer.start();
      }
    }
    if(e.getSource()==jButton_search){
      if(jText_search.isVisible()){
        jText_search.setVisible(false);
      }
      else jText_search.setVisible(true);
    }
  }

  @Override
  public void mousePressed(MouseEvent e) {
   
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    
  }

  @Override
  public void mouseExited(MouseEvent e) {
   
  }

}
