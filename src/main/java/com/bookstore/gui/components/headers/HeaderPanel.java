package com.bookstore.gui.components.headers;

import com.bookstore.gui.components.inputs.SearchTextField;
import com.bookstore.gui.main.MainFrame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {

  private static HeaderPanel instance = null;

  public static HeaderPanel getInstance() {
    if (instance == null) {
      instance = new HeaderPanel();
    }
    return instance;
  }

  private JLabel searchLabel;
  private JButton toggleMenuButton;
  private SearchTextField searchTextField;

  public HeaderPanel() {
    initComponents();
    setOpaque(false);

    HeaderPanel.instance = this;
  }

  private void initComponents() {
    toggleMenuButton = new JButton();
    searchLabel = new JLabel();
    searchTextField = new SearchTextField();

    setBackground(new java.awt.Color(255, 255, 255));

    searchLabel.setIcon(
      new ImageIcon("src/main/java/resources/icons/search.png")
    );

    toggleMenuButton.setIcon(
      new ImageIcon("src/main/java/resources/icons/menu.png")
    );
    toggleMenuButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
    toggleMenuButton.addActionListener(e -> {
      MainFrame mainUI = (MainFrame) getRootPane().getParent();
      mainUI.toggleMenu();
    });
    setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.NONE;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    add(toggleMenuButton, gridBagConstraints);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.weightx = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.CENTER;
    add(searchTextField, gridBagConstraints);
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.weightx = 0;
    gridBagConstraints.fill = GridBagConstraints.NONE;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    add(searchLabel, gridBagConstraints);
  }
}