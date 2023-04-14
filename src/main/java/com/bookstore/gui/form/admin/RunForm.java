package com.bookstore.gui.form.admin;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.sql.SQLException;

import javax.swing.*;

import com.bookstore.gui.form.admin.component.bookListComponent.BrowseProductPanel;
import com.bookstore.gui.form.admin.component.dashboardComponent.DashboardPanel;
import com.bookstore.gui.form.admin.component.userListComponent.UserComponent;
import com.bookstore.gui.form.admin.menu.MenuForm;

public class RunForm extends JFrame {
  private JPanel MenuAdmin;
  private JPanel Construct;
  private JPanel HeaderAdmin;
  private JPanel Contend;

  public RunForm() throws ClassNotFoundException, SQLException {
    initComponents();
    setLocationRelativeTo(null);
    setVisible(true);
  }

  public void initComponents() throws ClassNotFoundException, SQLException {

    setLayout(new FlowLayout());
    setSize(new Dimension(980, 560));
    setPreferredSize(new Dimension(980, 560));
    // MenuAdmin = new JPanel();
    // MenuAdmin.setPreferredSize(new Dimension(155, 500));
    MenuForm menuForm = new MenuForm();
<<<<<<< HEAD
    // MenuAdmin.add(menuForm);
    add(menuForm);
=======
    MenuAdmin.add(menuForm);
    add(MenuAdmin);
>>>>>>> 3f0ef734313566efc8b2716b60f649c843951974

    Construct = new JPanel();
    Construct.setPreferredSize(new Dimension(795, 500));
    Construct.setLayout(new FlowLayout());
    HeaderAdmin = new JPanel();
    Contend = new JPanel();

    HeaderAdmin.setBackground(Color.red);
    HeaderAdmin.setPreferredSize(new Dimension(680, 40));

    UserComponent userComponent = new UserComponent();
    BrowseProductPanel browseProductPanel = new BrowseProductPanel();
<<<<<<< HEAD
    DashboardPanel dashboardPanel = new DashboardPanel();
=======
>>>>>>> 3f0ef734313566efc8b2716b60f649c843951974
    Contend.add(userComponent);

    Construct.add(HeaderAdmin);
    Construct.add(Contend);

    add(Construct);
    revalidate();
    repaint();
    pack();

    addComponentListener(new ComponentListener() {

      @Override
      public void componentResized(ComponentEvent e) {
        int height = getContentPane().getHeight();
        int width = getContentPane().getWidth();
        // MenuAdmin.setPreferredSize(new Dimension(155, height));
        menuForm.setPreferredSize(new Dimension(width*25/100, height));


      }

      @Override
      public void componentMoved(ComponentEvent e) {

      }

      @Override
      public void componentShown(ComponentEvent e) {

      }

      @Override
      public void componentHidden(ComponentEvent e) {

      }

    });

  }

  public static void main(String[] args) throws ClassNotFoundException, SQLException {
    try {
      UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
      UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
    } catch (Exception e) {
      new RunForm();
    }

  }

}
