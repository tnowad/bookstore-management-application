package com.bookstore.gui.main;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import net.miginfocom.swing.MigLayout;

public class Main extends JFrame {
  // Contains all components in the frame (menu, header, main)
  private JLayeredPane background;
  // Layout for background
  private MigLayout layout;
  // Menu, header, main
  private JFrame menu;
  private JFrame header;
  private JFrame main;
  // Timing and animation for menu frame
  private Animator animator;

  public Main() {
    initComponent();
    handleEvent();
    initFrame();
  }

  private void initComponent() {
    background = new JLayeredPane();

    layout = new MigLayout(
        "fill",
        "0[]0[100%, fill]0",
        "0[fill, top]0");

    background.setLayout(layout);

    menu = new JFrame();
    header = new JFrame();
    main = new JFrame();

    // Timing and animation for menu frame
    TimingTarget timingTarget = new TimingTargetAdapter() {
      @Override
      public void timingEvent(float fraction) {
        int width = (int) (fraction * 200);
        menu.setSize(width, 600);
      }
    };
    animator = new Animator(500, timingTarget);
    animator.setResolution(0);
    animator.setDeceleration(0.5f);
    animator.setAcceleration(0.5f);
  }

  private void initFrame() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    background.setBackground(new Color(245, 245, 245));
    background.setOpaque(true);
    GroupLayout backgroundLayout = new GroupLayout(background);
    background.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
        backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE));
    backgroundLayout.setVerticalGroup(
        backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 768, Short.MAX_VALUE));

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(background));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(background));
    pack();
    setLocationRelativeTo(null);
  }

  private void handleEvent() {
  }

  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        new Main().setVisible(true);
      }
    });
  }
}
