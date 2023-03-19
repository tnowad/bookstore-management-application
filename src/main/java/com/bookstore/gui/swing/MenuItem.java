package com.bookstore.gui.swing;

import javax.swing.GroupLayout;
import javax.swing.JPanel;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import net.miginfocom.swing.MigLayout;

import com.bookstore.model.MenuModel;
import com.bookstore.gui.event.EventMenu;
import com.bookstore.gui.event.EventMenuSelected;

public class MenuItem extends JPanel {

  public MenuModel getMenu() {
    return menu;
  }

  public void setAlpha(float alpha) {
    this.alpha = alpha;
  }

  public boolean isOpen() {
    return open;
  }

  public void setOpen(boolean open) {
    this.open = open;
  }

  public EventMenuSelected getEventSelected() {
    return eventSelected;
  }

  public void setEventSelected(EventMenuSelected eventSelected) {
    this.eventSelected = eventSelected;
  }

  public int getIndex() {
    return index;
  }

  private float alpha;
  private MenuModel menu;
  private boolean open;
  private EventMenuSelected eventSelected;
  private int index;

  public MenuItem(MenuModel menu, EventMenu event, EventMenuSelected eventSelected, int index) {
    initComponents();
    this.menu = menu;
    this.eventSelected = eventSelected;
    this.index = index;
    setOpaque(false);
    setLayout(new MigLayout("wrap, fillx, insets 0", "[fill]", "[fill, 40!]0[fill, 35!]"));
    MenuButton firstItem = new MenuButton(menu.getIcon(), "      " + menu.getMenuName());
    firstItem.addActionListener(ae -> {
      if (menu.getSubMenu().length > 0) {
        if (event.menuPressed(MenuItem.this, !open)) {
          open = !open;
        }
      }
      eventSelected.menuSelected(index, -1);
    });
    add(firstItem);
    int subMenuIndex = -1;
    for (String st : menu.getSubMenu()) {
      MenuButton item = new MenuButton(st);
      item.setIndex(++subMenuIndex);
      item.addActionListener(
          actionEvent -> {
            eventSelected.menuSelected(index, item.getIndex());
          });
      add(item);
    }
  }

  private void initComponents() {

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE));
    layout.setVerticalGroup(
        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE));
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    int width = getWidth();
    int height = getPreferredSize().height;
    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    graphics2d.setColor(new Color(50, 50, 50));
    graphics2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
    graphics2d.fillRect(0, 2, width, 38);
    graphics2d.setComposite(AlphaComposite.SrcOver);
    graphics2d.fillRect(0, 40, width, height - 40);
    graphics2d.setColor(new Color(100, 100, 100));
    graphics2d.drawLine(30, 40, 30, height - 17);
    for (int i = 0; i < menu.getSubMenu().length; i++) {
      int y = ((i + 1) * 35 + 40) - 17;
      graphics2d.drawLine(30, y, 38, y);
    }
    if (menu.getSubMenu().length > 0) {
      createArrowButton(graphics2d);
    }
    super.paintComponent(graphics);
  }

  private void createArrowButton(Graphics2D graphics2d) {
    int size = 4;
    int y = 19;
    int x = 205;
    graphics2d.setColor(new Color(230, 230, 230));
    float ay = alpha * size;
    float ay1 = (1f - alpha) * size;
    graphics2d.drawLine(x, (int) (y + ay), x + 4, (int) (y + ay1));
    graphics2d.drawLine(x + 4, (int) (y + ay1), x + 8, (int) (y + ay));
  }
}
