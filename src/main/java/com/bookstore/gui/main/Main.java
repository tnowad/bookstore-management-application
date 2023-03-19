package com.bookstore.gui.main;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.JLayeredPane;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import com.bookstore.gui.component.Header;
import com.bookstore.gui.component.Menu;
import com.bookstore.gui.event.EventMenuSelected;
import com.bookstore.gui.event.EventShowPopupMenu;
import com.bookstore.gui.form.Form1;
import com.bookstore.gui.form.Form_Home;
import com.bookstore.gui.form.MainForm;
import com.bookstore.gui.swing.MenuItem;
import com.bookstore.gui.swing.PopupMenu;
import com.bookstore.gui.swing.icon.GoogleMaterialDesignIcons;
import com.bookstore.gui.swing.icon.IconFontSwing;

public class Main extends JFrame {
  private JLayeredPane background;
  private MigLayout layout;
  private Menu menu;
  private Header header;
  private MainForm main;
  private Animator animator;

  public Main() {
    initComponents();
    init();
  }

  private void init() {
    layout = new MigLayout(
        "fill",
        "0[]0[100%, fill]0",
        "0[fill, top]0");
    background.setLayout(layout);
    menu = new Menu();
    header = new Header();
    main = new MainForm();
    menu.addEvent(new EventMenuSelected() {
      @Override
      public void menuSelected(int menuIndex, int subMenuIndex) {
        System.out.println("Menu Index : " + menuIndex + " SubMenu Index " + subMenuIndex);
        if (menuIndex == 0) {
          if (subMenuIndex == 0) {
            main.showForm(new Form_Home());
          } else if (subMenuIndex == 1) {
            main.showForm(new Form1());
          }
        }
      }
    });
    menu.addEventShowPopup(new EventShowPopupMenu() {
      @Override
      public void showPopup(Component com) {
        MenuItem item = (MenuItem) com;
        PopupMenu popup = new PopupMenu(Main.this, item.getIndex(), item.getEventSelected(),
            item.getMenu().getSubMenu());
        int x = Main.this.getX() + 52;
        int y = Main.this.getY() + com.getY() + 86;
        popup.setLocation(x, y);
        popup.setVisible(true);
      }
    });
    menu.initMenuItem();
    background.add(menu, "w 230!, spany 2"); // Span Y 2cell
    background.add(header, "h 50!, wrap");
    background.add(main, "w 100%, h 100%");
    TimingTarget target = new TimingTargetAdapter() {
      @Override
      public void timingEvent(float fraction) {
        double width;
        if (menu.isShowMenu()) {
          width = 60 + (170 * (1f - fraction));
        } else {
          width = 60 + (170 * fraction);
        }
        layout.setComponentConstraints(menu, "w " + width + "!, spany2");
        menu.revalidate();
      }

      @Override
      public void end() {
        menu.setShowMenu(!menu.isShowMenu());
        menu.setEnableMenu(true);
      }

    };
    animator = new Animator(500, target);
    animator.setResolution(0);
    animator.setDeceleration(0.5f);
    animator.setAcceleration(0.5f);
    header.addMenuEvent(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent ae) {
        if (!animator.isRunning()) {
          animator.start();
        }
        menu.setEnableMenu(false);
        if (menu.isShowMenu()) {
          menu.hideallMenu();
        }
      }
    });
    IconFontSwing.register(GoogleMaterialDesignIcons.getIconFont());
    main.showForm(new Form_Home());
  }

  private void initComponents() {
    background = new JLayeredPane();

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setUndecorated(true);

    background.setBackground(new java.awt.Color(245, 245, 245));
    background.setOpaque(true);

    GroupLayout bgLayout = new GroupLayout(background);
    background.setLayout(bgLayout);
    bgLayout.setHorizontalGroup(
        bgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 1366, Short.MAX_VALUE));
    bgLayout.setVerticalGroup(
        bgLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 783, Short.MAX_VALUE));

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

  public static void main(String args[]) {
    try {
      for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
        if ("Nimbus".equals(info.getName())) {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    EventQueue.invokeLater(new Runnable() {
      @Override
      public void run() {
        new Main().setVisible(true);
      }
    });
  }

}
