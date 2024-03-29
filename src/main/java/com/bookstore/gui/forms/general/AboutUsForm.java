package com.bookstore.gui.forms.general;

import java.awt.*;
import javax.swing.*;

public class AboutUsForm extends JPanel {

  private static AboutUsForm instance;
  private JLabel titleLabel;
  private JTextArea descriptionArea;

  public static AboutUsForm getInstance() {
    if (instance == null) {
      instance = new AboutUsForm();
    }
    return instance;
  }

  public AboutUsForm() {
    setBackground(Color.WHITE);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    titleLabel = new JLabel("About Us");
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    add(titleLabel);

    descriptionArea = new JTextArea();
    descriptionArea.setBackground(Color.WHITE);
    descriptionArea.setLineWrap(true);
    descriptionArea.setWrapStyleWord(true);
    descriptionArea.setEditable(false);
    descriptionArea.setText(
      "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."
    );
    add(descriptionArea);
  }
}
