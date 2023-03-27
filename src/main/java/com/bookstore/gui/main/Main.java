package com.bookstore.gui.main;

import java.awt.BorderLayout;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

import com.bookstore.gui.component.Header;
import com.bookstore.gui.component.Menu;
import com.bookstore.gui.form.MainForm;
import net.miginfocom.swing.MigLayout;

public class Main extends JFrame {

  private JLayeredPane backgroundLayer;

  private Menu menu;
  private Header header;
  private MainForm mainForm;

  public Main() {
    initComponents();
    initFrame();
  }

  public Main(Menu menu, Header header, MainForm main) {

    this.menu = menu;
    this.header = header;
    this.mainForm = main;

    initComponents();
    initFrame();
  }

  private void initFrame() {
    backgroundLayer.setLayout(new BorderLayout());
    if (menu == null)
      menu = new Menu();
    if (header == null)
      header = Header.getInstance();
    if (mainForm == null)
      mainForm = MainForm.getInstance();

    backgroundLayer.add(menu, BorderLayout.WEST);
    backgroundLayer.add(header, BorderLayout.NORTH);
    backgroundLayer.add(mainForm, BorderLayout.CENTER);
  }

  private void initComponents() {

    backgroundLayer = new JLayeredPane();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    GroupLayout backgroundLayout = new GroupLayout(backgroundLayer);
    backgroundLayer.setLayout(backgroundLayout);
    backgroundLayout.setHorizontalGroup(
        backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 1345, Short.MAX_VALUE));
    backgroundLayout.setVerticalGroup(
        backgroundLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 763, Short.MAX_VALUE));

    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(backgroundLayer));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(backgroundLayer));

    pack();
  }

}