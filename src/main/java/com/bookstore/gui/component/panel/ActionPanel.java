package com.bookstore.gui.component.panel;

import com.bookstore.gui.component.button.ActionButton;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ActionPanel extends JPanel {

  private LayoutManager layout;
  private ActionButton buttonView;
  private ActionButton buttonUpdate;
  private ActionButton buttonDelete;

  public ActionPanel() {
    initComponents();
  }

  private void initComponents() {
    layout = new FlowLayout(FlowLayout.CENTER, 10, 10);

    buttonView = new ActionButton();
    buttonUpdate = new ActionButton();
    buttonDelete = new ActionButton();

    buttonView.setIcon(new ImageIcon("src/main/java/resources/icons/view.png"));
    buttonUpdate.setIcon(
      new ImageIcon("src/main/java/resources/icons/update.png")
    );
    buttonDelete.setIcon(
      new ImageIcon("src/main/java/resources/icons/delete.png")
    );

    add(buttonView);
    add(buttonUpdate);
    add(buttonDelete);
  }

  public static void main(String[] args) {
    FlatMacLightLaf.setup();
    JFrame frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(new ActionPanel());
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
