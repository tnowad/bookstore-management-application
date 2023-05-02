package com.bookstore.gui.forms.general;

import java.awt.*;
import javax.swing.*;

public class AboutUs extends JPanel {

    private static AboutUs instance;
    private JLabel titleLabel, descriptionLabel;
    private JTextArea descriptionArea;

    public static AboutUs getInstance() {
        if (instance == null) {
            instance = new AboutUs();
        }
        return instance;
    }

    private AboutUs() {
        setLayout(new GridLayout(2, 1));

        titleLabel = new JLabel("About Us");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(titleLabel);

        descriptionLabel = new JLabel("Our company is dedicated to providing the best services and products.");
        add(descriptionLabel);

        descriptionArea = new JTextArea();
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setText(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
        add(descriptionArea);
    }

}
