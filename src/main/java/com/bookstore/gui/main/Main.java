package com.bookstore.gui.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;

import com.bookstore.gui.component.Header;
import com.bookstore.gui.component.Menu;
import com.bookstore.gui.form.MainForm;
import com.bookstore.gui.model.MenuItemModel;
import com.bookstore.gui.model.MenuModel;
import com.formdev.flatlaf.FlatLightLaf;

import net.miginfocom.swing.MigLayout;

public class Main extends JFrame {
  private MigLayout layout;
  private JLayeredPane background;

  private Menu menu;
  private Header header;
  private MainForm main;

  public Main() {
    initComponents();
    initFrame();
  }

  public Main(Menu menu, Header header, MainForm main) {

    this.menu = menu;
    this.header = header;
    this.main = main;

    initComponents();
    initFrame();
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          Thread.sleep(3000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        menu.hideAllMenu();
      }
    }).start();
  }

  private void initFrame() {
    layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
    background.setLayout(layout);
    if (menu == null)
      menu = new Menu();
    if (header == null)
      header = new Header();
    if (main == null)
      main = new MainForm();

    background.add(menu, "w 230!");
    background.add(header, "h 50!, w 1050!");
    background.add(main, "w 100%, h 100%");
  }

  private void initComponents() {

    background = new javax.swing.JLayeredPane();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

    javax.swing.GroupLayout backgroundLayout = new javax.swing.GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
        backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1345, Short.MAX_VALUE));
    backgroundLayout.setVerticalGroup(
        backgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 763, Short.MAX_VALUE));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(background));

    pack();
  }

  public static void main(String args[]) {
    try {
      UIManager.setLookAndFeel(new FlatLightLaf());
    } catch (Exception ex) {
      System.err.println("Failed to initialize LaF");
    }
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        MenuItemModel menuItemModel = new MenuItemModel(
            "Home",
            new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                System.out.println("Home");
              }
            });

        MenuModel menuModel = new MenuModel(
            new Icon() {
              @Override
              public void paintIcon(Component c, Graphics g, int x, int y) {
                g.setColor(Color.BLACK);
                g.fillRect(x, y, getIconWidth(), getIconHeight());
              }

              @Override
              public int getIconWidth() {
                return 20;
              }

              @Override
              public int getIconHeight() {
                return 20;
              }
            },
            "Home",
<<<<<<< HEAD
            new String[] { "1", "2" });
        for (int i = 0; i < 10; i++) {
          menu.addMenu(modelMenu);
        }
        background.add(menu, "w 230!");
        background.add(header, "h 50!, w 340!");
        background.add(main, "w 100%, h 100%");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
=======
            new MenuItemModel[] {
                menuItemModel,
                menuItemModel,
                menuItemModel,
                menuItemModel,
                menuItemModel
            });
>>>>>>> 563e04fb5c081508ef69d168f535bf84dcec4cc4

        Main main = new Main();
        main.setVisible(true);
      }
    });
  }
}