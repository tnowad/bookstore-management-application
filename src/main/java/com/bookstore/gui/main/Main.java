/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.bookstore.gui.main;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.UIManager;

import com.bookstore.gui.component.Header;
import com.bookstore.gui.component.Menu;
import com.bookstore.gui.form.MainForm;
import com.bookstore.gui.model.MenuModel;
import com.formdev.flatlaf.FlatLightLaf;

import net.miginfocom.swing.MigLayout;

/**
 *
 * @author alex
 */
public class Main extends javax.swing.JFrame {

    private MigLayout layout;
    private Menu menu;
    private Header header;
    private MainForm main;

    public Main() {
        initComponents();
        initFrame();
    }

    private void initFrame() {
        layout = new MigLayout("fill", "0[]0[100%, fill]0", "0[fill, top]0");
        background.setLayout(layout);
        menu = new Menu();
        header = new Header();
        main = new MainForm();

        MenuModel modelMenuHome = new MenuModel(
                new Icon() {
                    @Override
                    public void paintIcon(Component c, Graphics g, int x, int y) {
                        g.setColor(Color.WHITE);
                        g.fillRect(x, y, getIconWidth(), getIconHeight());
                        g.setColor(Color.BLACK);
                        g.fillRect(x + 5, y + 5, getIconWidth() - 10, getIconHeight() - 10);
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
                new String[] { "1", "2" });

        MenuModel modelMenuNotification = new MenuModel(
                new Icon() {
                    @Override
                    public void paintIcon(Component c, Graphics g, int x, int y) {
                        g.setColor(Color.WHITE);
                        g.fillRect(x, y, getIconWidth(), getIconHeight());
                        g.setColor(Color.BLACK);
                        g.fillRect(x + 5, y + 5, getIconWidth() - 10, getIconHeight() - 10);
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
                "Notification",
                new String[] { "1", "2" });

        MenuModel modelMenuCart = new MenuModel(
                new Icon() {
                    @Override
                    public void paintIcon(Component c, Graphics g, int x, int y) {
                        g.setColor(Color.WHITE);
                        g.fillRect(x, y, getIconWidth(), getIconHeight());
                        g.setColor(Color.BLACK);
                        g.fillRect(x + 5, y + 5, getIconWidth() - 10, getIconHeight() - 10);
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
                "Cart",
                new String[] { "1", "2" });

        MenuModel modelMenuHelp = new MenuModel(
                new Icon() {
                    @Override
                    public void paintIcon(Component c, Graphics g, int x, int y) {
                        g.setColor(Color.WHITE);
                        g.fillRect(x, y, getIconWidth(), getIconHeight());
                        g.setColor(Color.BLACK);
                        g.fillRect(x + 5, y + 5, getIconWidth() - 10, getIconHeight() - 10);
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
                "Help",
                new String[] { "1", "2" });

        menu.addMenu(modelMenuHome);
        menu.addMenu(modelMenuNotification);
        menu.addMenu(modelMenuCart);
        menu.addMenu(modelMenuHelp);
        background.add(menu, "w 230!");
        background.add(header, "h 50!, w 1050!");
        background.add(main, "w 100%, h 100%");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
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
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane background;
    // End of variables declaration//GEN-END:variables
}
