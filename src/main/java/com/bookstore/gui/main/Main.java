package com.bookstore.gui.main;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.WindowConstants;

import com.bookstore.gui.component.Header;
import com.bookstore.gui.component.Menu;
import com.bookstore.gui.form.MainForm;
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

    background = new JLayeredPane();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    GroupLayout backgroundLayout = new GroupLayout(background);
    background.setLayout(backgroundLayout);
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
            .addComponent(background));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(background));

    pack();
  }

}