package com.bookstore.gui.components.headers;

import com.bookstore.gui.components.inputs.SearchTextField;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.main.MainFrame;
import com.bookstore.interfaces.IFilterAble;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
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

    HeaderPanel.instance = this;
  }

  private void initComponents() {
    setBackground(Color.WHITE);
    toggleMenuButton = new JButton();
    toggleMenuButton.addActionListener(toggleMenuActionListener);
    toggleMenuButton.setIcon(
      new ImageIcon("src/main/java/resources/icons/menu.png")
    );
    toggleMenuButton.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    searchTextField = new SearchTextField();
    searchTextField.addActionListener(searchActionListener);

    searchLabel = new JLabel();
    searchLabel.setIcon(
      new ImageIcon("src/main/java/resources/icons/search.png")
    );
    searchLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

    searchLabel.addMouseListener(
      new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
          if (MainPanel.getInstance().getComponent(0) instanceof IFilterAble) {
            ((IFilterAble) MainPanel.getInstance().getComponent(0)).filter();
          }
        }
      }
    );

    setLayout(new GridBagLayout());

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.fill = GridBagConstraints.NONE;
    gridBagConstraints.anchor = GridBagConstraints.WEST;
    add(toggleMenuButton, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.weightx = 1;
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.anchor = GridBagConstraints.CENTER;
    add(searchTextField, gridBagConstraints);
    gridBagConstraints.gridx = 2;
    gridBagConstraints.weightx = 0;
    gridBagConstraints.fill = GridBagConstraints.NONE;
    gridBagConstraints.anchor = GridBagConstraints.EAST;
    add(searchLabel, gridBagConstraints);
  }

  private ActionListener toggleMenuActionListener = e -> {
    MainFrame mainUI = (MainFrame) getRootPane().getParent();
    mainUI.toggleMenu();
  };

  private ActionListener searchActionListener = e -> {
    MainPanel.getInstance().search(searchTextField.getText());
  };
}
