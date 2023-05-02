package com.bookstore.gui.components.headers;

import com.bookstore.gui.components.inputs.SearchText;
import com.bookstore.gui.main.MainUI;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel {

  private static Header instance = null;

  public static Header getInstance() {
    if (instance == null) {
      instance = new Header();
    }
    return instance;
  }

  private JLabel labelSearch;
  private JButton buttonMenu;
  private SearchText searchText;

  public Header() {
    initComponents();
    setOpaque(false);
  }

  private void initComponents() {
    buttonMenu = new JButton();
    labelSearch = new JLabel();
    searchText = new SearchText();

    setBackground(new java.awt.Color(255, 255, 255));

    labelSearch.setIcon(
      new ImageIcon("src/main/java/resources/icons/search.png")
    );

    buttonMenu.setIcon(new ImageIcon("src/main/java/resources/icons/menu.png"));
    buttonMenu.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    buttonMenu.addActionListener(e -> {
      MainUI mainUI = (MainUI) getRootPane().getParent();
      mainUI.toggleMenu();
    });
    setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.NONE;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    add(buttonMenu, gridBagConstraints);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.weightx = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.CENTER;
    add(searchText, gridBagConstraints);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.weightx = 0;
    gridBagConstraints.fill = GridBagConstraints.NONE;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    add(labelSearch, gridBagConstraints);
  }
}
