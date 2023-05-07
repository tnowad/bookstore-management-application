package com.bookstore.gui.forms.general;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class ContactUs extends JPanel {
    private static ContactUs instance;

    private JLabel title;
    private JPanel titlePanel;
    private JPanel contactPhone;
    private JPanel contactNewCase;
    private JPanel contactChat;
    private JButton jButtonNewCase;
    private JButton jButtonChat;

    private JLabel contactPhoneLabel;

    private JLabel contactChatLabel;

    private JLabel contactNewCaseLabel;

    public static ContactUs getInstance() {
        if (instance == null) {
            instance = new ContactUs();
        }
        return instance;
    }

    public ContactUs() {
        init();
    }

    private void init() {
        initPhone();
        initNewCase();
        initChat();
        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(contactPhone, BorderLayout.WEST);
        add(contactNewCase, BorderLayout.CENTER);
        add(contactChat, BorderLayout.EAST);
    }

    private void initPhone() {
        titlePanel = new JPanel();
        titlePanel.setLayout(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(1500, 100));
        title = new JLabel("Contact Us");
        title.setFont(new Font("Arial", Font.BOLD, 28));
        title.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(title, BorderLayout.CENTER);

        contactPhone = new JPanel();
        contactPhone.setPreferredSize(new Dimension(400, ABORT));
        JLabel titlePhone = new JLabel("BY PHONE");
        titlePhone.setFont(new Font("Arial", Font.BOLD, 16));
        titlePhone.setAlignmentX(Component.CENTER_ALIGNMENT);
        contactPhoneLabel = new JLabel();
        contactPhoneLabel.setIcon(new ImageIcon("src/main/java/resources/images/contactphone.png"));
        contactPhoneLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel contentJLabel = new JLabel("Sai Gon University");
        contentJLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contactPhone.add(contactPhoneLabel);
        contactPhone.add(Box.createVerticalStrut(80));
        contactPhone.add(titlePhone);
        contactPhone.add(Box.createVerticalStrut(40));
        contactPhone.add(contentJLabel);

        contactPhone.setLayout(new BoxLayout(contactPhone, BoxLayout.Y_AXIS));
    }

    private void initChat() {
        contactChat = new JPanel();
        contactChat.setPreferredSize(new Dimension(400, ABORT));
        JLabel titleChat = new JLabel("CHAT");
        titleChat.setFont(new Font("Arial", Font.BOLD, 16));
        titleChat.setAlignmentX(Component.CENTER_ALIGNMENT);

        contactChatLabel = new JLabel();
        contactChatLabel.setIcon(new ImageIcon("src/main/java/resources/images/contactmess.png"));
        contactChatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contactChat.add(contactChatLabel);
        contactChat.add(Box.createVerticalStrut(80));
        contactChat.add(titleChat);

        contactChat.setLayout(new BoxLayout(contactChat, BoxLayout.Y_AXIS));
    }

    private void initNewCase() {
        contactNewCase = new JPanel();
        JLabel titleNewCase = new JLabel("START A NEW CASE");
        titleNewCase.setFont(new Font("Arial", Font.BOLD, 16));
        titleNewCase.setAlignmentX(Component.CENTER_ALIGNMENT);

        contactNewCaseLabel = new JLabel();
        contactNewCaseLabel.setIcon(new ImageIcon("src/main/java/resources/images/contactnewcase.png"));
        contactNewCaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        contactNewCase.add(contactNewCaseLabel);
        contactNewCase.add(Box.createVerticalStrut(80));
        contactNewCase.add(titleNewCase);

        contactNewCase.setLayout(new BoxLayout(contactNewCase, BoxLayout.Y_AXIS));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        ContactUs noData = new ContactUs();
        frame.add(noData);
        frame.setSize(1000, 1000);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
