package com.bookstore.gui.components.header;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import com.bookstore.gui.components.input.SearchText;

public class HeaderDashboard extends JPanel {

  private JLabel labelSearch;
  private JLabel labelMenu;
  private SearchText searchText;

  public HeaderDashboard() {
    initComponents();
    setOpaque(false);
  }

  private void initComponents() {
    labelSearch = new JLabel();
    searchText = new SearchText();
    labelMenu = new JLabel();

    setBackground(new java.awt.Color(255, 255, 255));

    labelSearch.setIcon(
      new ImageIcon("src/main/java/resources/icons/search.png")
    );

    labelMenu.setIcon(new ImageIcon("src/main/java/resources/icons/menu.png"));
    labelMenu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    GroupLayout layout = new GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addContainerGap()
            .addComponent(labelSearch)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              searchText,
              GroupLayout.DEFAULT_SIZE,
              606,
              Short.MAX_VALUE
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labelMenu)
            .addContainerGap()
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addComponent(
          labelSearch,
          GroupLayout.DEFAULT_SIZE,
          GroupLayout.DEFAULT_SIZE,
          Short.MAX_VALUE
        )
        .addComponent(
          searchText,
          GroupLayout.DEFAULT_SIZE,
          GroupLayout.DEFAULT_SIZE,
          Short.MAX_VALUE
        )
        .addComponent(labelMenu, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
    );
  }

  @Override
  protected void paintComponent(Graphics graphics) {
    Graphics2D graphics2d = (Graphics2D) graphics;
    graphics2d.setRenderingHint(
      RenderingHints.KEY_ANTIALIASING,
      RenderingHints.VALUE_ANTIALIAS_ON
    );
    graphics2d.setColor(getBackground());
    graphics2d.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
    graphics2d.fillRect(0, 0, 25, getHeight());
    graphics2d.fillRect(
      getWidth() - 25,
      getHeight() - 25,
      getWidth(),
      getHeight()
    );
    super.paintComponent(graphics);
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new HeaderDashboard());
    frame.pack();
    frame.setVisible(true);
  }
}
