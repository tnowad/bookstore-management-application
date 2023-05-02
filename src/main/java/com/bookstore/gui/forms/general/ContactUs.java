package com.bookstore.gui.forms.general;

import java.awt.*;
import javax.swing.*;

public class ContactUs extends JPanel {
    private static ContactUs instance;

    private JLabel nameLabel, emailLabel, messageLabel;
    private JTextField nameField, emailField;
    private JTextArea messageArea;
    private JButton submitButton;

    public static ContactUs getInstance() {
        if (instance == null) {
            instance = new ContactUs();
        }
        return instance;
    }

    private ContactUs() {
        setLayout(new GridLayout(4, 2));

        nameLabel = new JLabel("Name:");
        add(nameLabel);

        nameField = new JTextField();
        add(nameField);

        emailLabel = new JLabel("Email:");
        add(emailLabel);

        emailField = new JTextField();
        add(emailField);

        messageLabel = new JLabel("Message:");
        add(messageLabel);

        messageArea = new JTextArea();
        add(messageArea);

        submitButton = new JButton("Submit");
        add(submitButton);
    }

}
