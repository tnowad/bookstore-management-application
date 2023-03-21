package com.bookstore.gui.form;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class MainForm extends javax.swing.JPanel {

  public MainForm() {
    initComponents();
    setOpaque(false);
    setLayout(new BorderLayout());
    setBorder(new EmptyBorder(10, 20, 10, 20));
  }

  public void showForm(Component form) {
    removeAll();
    add(form);
    repaint();
    revalidate();
  }

  private void initComponents() {

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE));
  }

  // Test switch form
  public static void main(String[] args) {
    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new MainForm() {
          {
            this.showForm(
                new Panel() {
                  private Panel getThisPanel() {
                    return this;
                  }

                  {
                    setBackground(java.awt.Color.red);
                    addMouseListener(new java.awt.event.MouseAdapter() {
                      @Override
                      public void mouseClicked(java.awt.event.MouseEvent evt) {
                        showForm(new Panel() {
                          {
                            setBackground(java.awt.Color.blue);
                            addMouseListener(new java.awt.event.MouseAdapter() {
                              @Override
                              public void mouseClicked(java.awt.event.MouseEvent evt) {
                                showForm(getThisPanel());
                              }
                            });
                          }
                        });
                      }
                    });
                  }
                });
          }
        });
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
      }
    });
  }
}
