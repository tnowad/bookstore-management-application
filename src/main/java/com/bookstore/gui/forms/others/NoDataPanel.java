package com.bookstore.gui.forms.others;

import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.theme.ThemeFont;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class NoDataPanel extends JPanel {

  private Label noDataLabel;
  private JLabel noDataIconLabel;

  public NoDataPanel(String name) {
    initComponents(name);
  }

  private void initComponents(String name) {
    noDataIconLabel = new JLabel();
    try {
      BufferedImage logoImage = ImageIO.read(
        getClass().getResource("/resources/images/logo.png")
      );
      noDataIconLabel.setIcon(new ImageIcon(logoImage));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    noDataIconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(noDataIconLabel);

    noDataLabel = new Label();
    noDataLabel.setFont(new ThemeFont().getLargeFont());
    noDataLabel.setText(name);
    noDataLabel.setPreferredSize(new Dimension(400, 100));
    noDataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(noDataLabel);

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
  }

  public static void main(String[] args) {
    JFrame frame = new JFrame();
    NoDataPanel noData = new NoDataPanel("test");
    frame.add(noData);
    frame.setSize(1000, 1000);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
}
