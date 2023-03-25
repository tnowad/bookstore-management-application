package com.bookstore.gui.component;

import javax.swing.GroupLayout;
import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import net.miginfocom.swing.MigLayout;

import com.bookstore.gui.event.EventMenu;
import com.bookstore.gui.event.EventMenuSelected;
import com.bookstore.gui.event.EventShowPopupMenu;
import com.bookstore.gui.form.Form;
import com.bookstore.gui.model.MenuItemModel;
import com.bookstore.gui.model.MenuModel;
import com.bookstore.gui.swing.MenuAnimation;
import com.bookstore.gui.swing.MenuItem;
import com.bookstore.gui.swing.scrollbar.ScrollBarCustom;

public class Menu extends JPanel {
  private JPanel panel;
  private Profile profile;
  private JScrollPane scrollPane;

  public boolean isShowMenu() {
    return showMenu;
  }

  public void addEvent(EventMenuSelected event) {
    this.event = event;
  }

  public void setEnableMenu(boolean enableMenu) {
    this.enableMenu = enableMenu;
  }

  public void setShowMenu(boolean showMenu) {
    this.showMenu = showMenu;
  }

  public void addEventShowPopup(EventShowPopupMenu eventShowPopup) {
    this.eventShowPopup = eventShowPopup;
  }

  private final MigLayout layout;
  private EventMenuSelected event;
  private EventShowPopupMenu eventShowPopup;
  private boolean enableMenu = true;
  private boolean showMenu = true;

  public Menu() {
    initComponents();
    setOpaque(false);
    scrollPane.getViewport().setOpaque(false);
    scrollPane.setVerticalScrollBar(new ScrollBarCustom());
    layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
    panel.setLayout(layout);
  }

  public void addMenu(MenuModel menu) {
    panel.add(new MenuItem(menu, getEventMenu(), event, panel.getComponentCount()), "h 40!");
  }

  private EventMenu getEventMenu() {
    return new EventMenu() {
      @Override
      public boolean menuPressed(Component com, boolean open) {
        if (enableMenu) {
          if (isShowMenu()) {
            if (open) {
              new MenuAnimation(layout, com).openMenu();
            } else {
              new MenuAnimation(layout, com).closeMenu();
            }
            return true;
          } else {
            eventShowPopup.showPopup(com);
          }
        }
        return false;
      }
    };
  }

  public void hideAllMenu() {
    for (Component com : panel.getComponents()) {
      MenuItem item = (MenuItem) com;
      if (item.isOpen()) {
        new MenuAnimation(layout, com, 500).closeMenu();
        item.setOpen(false);
      }
    }
  }

  private void initComponents() {

    scrollPane = new JScrollPane();
    panel = new JPanel();
    profile = new com.bookstore.gui.component.Profile();

    scrollPane.setBorder(null);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.setViewportBorder(null);

    panel.setOpaque(false);

    GroupLayout panelLayout = new GroupLayout(panel);
    panel.setLayout(panelLayout);
    panelLayout.setHorizontalGroup(
        panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 312, Short.MAX_VALUE));
    panelLayout.setVerticalGroup(
        panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE));

    scrollPane.setViewportView(panel);

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
            .addComponent(profile, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(profile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                    GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)));
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    GradientPaint gradientPaint = new GradientPaint(0, 0, new Color(33, 105, 249), getWidth(), 0,
        new Color(93, 58, 196));
    graphics2d.setPaint(gradientPaint);
    graphics2d.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(graphics);
  }

  public static void main(String[] args) {
    // Set look and feel
    try {
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    } catch (Exception e) {
      e.printStackTrace();
    }
    new JFrame() {
      {
        Menu menu = new Menu();
        // Create icon with square shape
        Icon icon = new Icon() {
          @Override
          public void paintIcon(Component c, Graphics g, int x, int y) {
            g.setColor(Color.WHITE);
            g.fillRect(x, y, getIconWidth(), getIconHeight());
          }

          @Override
          public int getIconWidth() {
            return 16;
          }

          @Override
          public int getIconHeight() {
            return 16;
          }
        };
        MenuModel modelMenu = new MenuModel(icon, "Menu", new MenuItemModel[] {
            new MenuItemModel("Home", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                System.out.println("Home");
              }
            }),
            new MenuItemModel("About", new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
                System.out.println("About");
              }
            })
        });
        for (int i = 0; i < 10; i++) {
          menu.addMenu(modelMenu);
        }
        add(menu);
        setSize(300, 600);
        setLocationRelativeTo(null);
        setVisible(true);
      }
    };
  }
}
