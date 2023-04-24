package com.bookstore.gui.component.menu;

import com.bookstore.gui.component.scrollbar.ScrollBarCustom;
import com.bookstore.models.MenuItemModel;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.LayoutStyle;
import javax.swing.ScrollPaneConstants;
import net.miginfocom.swing.MigLayout;

public class DrawerMenu extends JPanel {

  private JPanel panel;
  private JScrollPane scrollPane;

  private final MigLayout layout;

  public DrawerMenu() {
    initComponents();
    setOpaque(false);
    scrollPane.getViewport().setOpaque(false);
    scrollPane.setVerticalScrollBar(new ScrollBarCustom());
    layout = new MigLayout("wrap, fillx, insets 0", "[fill]", "[]0[]");
    panel.setLayout(layout);
  }

  public void addMenu(MenuItemModel menuItemModel) {
    panel.add(new DrawerMenuItem(menuItemModel), "h 40!");
  }

  private void initComponents() {
    // Profile profile;

    JPanel profile;

    scrollPane = new JScrollPane();
    panel = new JPanel();
    // profile = new com.bookstore.gui.component.Profile();
    profile = new JPanel();
    scrollPane.setBorder(null);
    scrollPane.setHorizontalScrollBarPolicy(
      ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER
    );
    scrollPane.setViewportBorder(null);

    panel.setOpaque(false);

    GroupLayout panelLayout = new GroupLayout(panel);
    panel.setLayout(panelLayout);
    panelLayout.setHorizontalGroup(
      panelLayout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 312, Short.MAX_VALUE)
    );
    panelLayout.setVerticalGroup(
      panelLayout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGap(0, 523, Short.MAX_VALUE)
    );

    scrollPane.setViewportView(panel);

    GroupLayout menuLayout = new GroupLayout(this);
    this.setLayout(menuLayout);
    menuLayout.setHorizontalGroup(
      menuLayout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(
          scrollPane,
          GroupLayout.DEFAULT_SIZE,
          230,
          Short.MAX_VALUE
        )
        .addComponent(profile, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
    );
    menuLayout.setVerticalGroup(
      menuLayout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          GroupLayout.Alignment.TRAILING,
          menuLayout
            .createSequentialGroup()
            .addComponent(
              profile,
              GroupLayout.PREFERRED_SIZE,
              GroupLayout.DEFAULT_SIZE,
              GroupLayout.PREFERRED_SIZE
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              scrollPane,
              GroupLayout.DEFAULT_SIZE,
              523,
              Short.MAX_VALUE
            )
        )
    );
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON
    );
    GradientPaint gradientPaint = new GradientPaint(
      0,
      0,
      new Color(33, 105, 249),
      getWidth(),
      0,
      new Color(93, 58, 196)
    );
    graphics2d.setPaint(gradientPaint);
    graphics2d.fillRect(0, 0, getWidth(), getHeight());
    super.paintComponent(graphics);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setSize(300, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(new DrawerMenu());
    frame.setVisible(true);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
