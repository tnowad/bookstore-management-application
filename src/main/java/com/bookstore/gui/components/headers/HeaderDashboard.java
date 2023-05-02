package com.bookstore.gui.components.headers;

import com.bookstore.gui.components.inputs.SearchText;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle;

public class HeaderDashboard extends JPanel {

  private static HeaderDashboard instance = null;

  public static HeaderDashboard getInstance() {
    if (instance == null) {
      instance = new HeaderDashboard();
    }
    return instance;
  }

  private JLabel labelSearch;
  private JLabel labelMenu;
  private SearchText searchText;

  public HeaderDashboard() {
    initComponents();
    setOpaque(false);
  }

  private void initComponents() {
    labelMenu = new JLabel();
    labelSearch = new JLabel();
    searchText = new SearchText();

    setBackground(new java.awt.Color(255, 255, 255));

    labelSearch.setIcon(
      new ImageIcon("src/main/java/resources/icons/search.png")
    );

    labelMenu.setIcon(new ImageIcon("src/main/java/resources/icons/menu.png"));
    labelMenu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    // Create layout for header with labelMenu, searchText, and labelSearch
    // search text is in the center of the header
    GroupLayout layout = new GroupLayout(this);
    setLayout(layout);
    layout.setHorizontalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addComponent(labelMenu)
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(
              searchText,
              GroupLayout.DEFAULT_SIZE,
              300,
              Short.MAX_VALUE
            )
            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(labelSearch)
        )
    );
    layout.setVerticalGroup(
      layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(
          layout
            .createSequentialGroup()
            .addGroup(
              layout
                .createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(labelMenu)
                .addComponent(
                  searchText,
                  GroupLayout.PREFERRED_SIZE,
                  GroupLayout.DEFAULT_SIZE,
                  GroupLayout.PREFERRED_SIZE
                )
                .addComponent(labelSearch)
            )
        )
    );
    searchText.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    labelMenu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    labelSearch.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    labelMenu.setOpaque(false);
    labelSearch.setOpaque(false);
    searchText.setOpaque(false);
    labelMenu.setBackground(new java.awt.Color(255, 255, 255));
    labelSearch.setBackground(new java.awt.Color(255, 255, 255));
    searchText.setBackground(new java.awt.Color(255, 255, 255));
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
