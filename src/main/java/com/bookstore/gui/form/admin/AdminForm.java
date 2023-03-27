package com.bookstore.gui.form.admin;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.bookstore.gui.factory.MenuFactory;

public class AdminForm {
  public AdminForm() {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    frame.setLayout(new BorderLayout());
    frame.add(MenuFactory.createMenu("admin"), BorderLayout.WEST);
    frame.add(panel, BorderLayout.EAST);
    frame.pack();
    frame.setVisible(true);
    initComponent();
  }

  private void initComponent() {
  }

  public static void main(String[] args) {
    new AdminForm();
  }
}

// import java.awt.*;
// import java.awt.event.*;
// import javax.swing.*;

// public class AdminForm extends JFrame implements ActionListener {
// JPanel cards;
// final static String BUTTON1PANEL = "Panel with Button 1";
// final static String BUTTON2PANEL = "Panel with Button 2";
// final static String BUTTON3PANEL = "Panel with Button 3";

// public AdminForm() {
// // Create the buttons
// JButton button1 = new JButton("Button 1");
// button1.addActionListener(this);
// JButton button2 = new JButton("Button 2");
// button2.addActionListener(this);
// JButton button3 = new JButton("Button 3");
// button3.addActionListener(this);

// // Create the card layout
// cards = new JPanel(new CardLayout());
// JPanel card1 = new JPanel();
// card1.add(new JLabel("Panel with Button 1"));
// JPanel card2 = new JPanel();
// card2.add(new JLabel("Panel with Button 2"));
// JPanel card3 = new JPanel();
// card3.add(new JLabel("Panel with Button 3"));
// cards.add(card1, BUTTON1PANEL);
// cards.add(card2, BUTTON2PANEL);
// cards.add(card3, BUTTON3PANEL);

// // Add the buttons and card layout to the frame
// JPanel panel = new JPanel();
// panel.setLayout(new BorderLayout());
// panel.add(button1, BorderLayout.WEST);
// panel.add(button2, BorderLayout.EAST);
// panel.add(button3, BorderLayout.SOUTH);
// panel.add(cards, BorderLayout.CENTER);
// Container contentPane = getContentPane();
// // contentPane.add(button1, BorderLayout.WEST);
// // contentPane.add(cards, BorderLayout.CENTER);
// // contentPane.add(button2, BorderLayout.EAST);
// // contentPane.add(button3, BorderLayout.SOUTH);
// contentPane.add(panel);
// }

// public void actionPerformed(ActionEvent e) {
// CardLayout cl = (CardLayout) (cards.getLayout());
// if (e.getActionCommand().equals("Button 1")) {
// cl.show(cards, BUTTON1PANEL);
// } else if (e.getActionCommand().equals("Button 2")) {
// cl.show(cards, BUTTON2PANEL);
// } else if (e.getActionCommand().equals("Button 3")) {
// cl.show(cards, BUTTON3PANEL);
// }
// }

// private static void createAndShowGUI() {
// // Create and set up the window.
// AdminForm frame = new AdminForm();
// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

// // Display the window.
// frame.pack();
// frame.setVisible(true);
// }

// public static void main(String[] args) {
// // Schedule a job for the event-dispatching thread:
// // creating and showing this application's GUI.
// javax.swing.SwingUtilities.invokeLater(new Runnable() {
// public void run() {
// createAndShowGUI();
// }
// });
// }
// }
