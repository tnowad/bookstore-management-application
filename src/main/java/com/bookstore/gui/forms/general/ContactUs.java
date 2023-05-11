package com.bookstore.gui.forms.general;

import java.awt.*;
import javax.swing.*;

public class ContactUs extends JPanel {

  private static ContactUs instance;
  boolean isFormNewCaseVisible = false;

  private JLabel title;
  private JPanel titlePanel;
  private JPanel contactPhone;
  private JPanel contactNewCase;
  private JPanel contactChat;
  private JPanel formNewCase;
  private JButton jButtonNewCase;
  private JButton jButtonChat;

  private JLabel contactPhoneLabel;
  private JLabel contactChatLabel;
  private JLabel contactNewCaseLabel;
  private JLabel nameLabel;
  private JLabel emailLabel;
  private JLabel messageLabel;
  private JTextField nameField;
  private JTextField emailField;
  private JTextArea messageArea;
  private JButton submitButton;

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
    initFormNewCase();
    setLayout(new BorderLayout());
    add(titlePanel, BorderLayout.NORTH);
    add(contactPhone, BorderLayout.WEST);
    add(contactNewCase, BorderLayout.CENTER);
    add(contactChat, BorderLayout.EAST);
  }

  private void initPhone() {
    setBackground(Color.WHITE);
    titlePanel = new JPanel();
    titlePanel.setBackground(Color.WHITE);
    titlePanel.setLayout(new BorderLayout());
    titlePanel.setPreferredSize(new Dimension(1500, 200));
    title = new JLabel("Contact Us");
    title.setFont(new Font("Arial", Font.BOLD, 28));
    title.setHorizontalAlignment(JLabel.CENTER);
    titlePanel.add(title, BorderLayout.CENTER);

    contactPhone = new JPanel();
    contactPhone.setBackground(Color.WHITE);
    contactPhone.setPreferredSize(new Dimension(250, ABORT));
    JLabel titlePhone = new JLabel("BY PHONE");
    titlePhone.setFont(new Font("Arial", Font.BOLD, 16));
    titlePhone.setAlignmentX(Component.CENTER_ALIGNMENT);
    contactPhoneLabel = new JLabel();
    contactPhoneLabel.setIcon(
      new ImageIcon("src/main/java/resources/images/contactphone.png")
    );
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
    contactChat.setPreferredSize(new Dimension(250, ABORT));
    JLabel titleChat = new JLabel("CHAT");
    titleChat.setFont(new Font("Arial", Font.BOLD, 16));
    titleChat.setAlignmentX(Component.CENTER_ALIGNMENT);

    contactChatLabel = new JLabel();
    contactChatLabel.setIcon(
      new ImageIcon("src/main/java/resources/images/contactmess.png")
    );
    contactChatLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    jButtonChat = new JButton("START CHAT");
    jButtonChat.setAlignmentX(Component.CENTER_ALIGNMENT);

    contactChat.add(contactChatLabel);
    contactChat.add(Box.createVerticalStrut(80));
    contactChat.add(titleChat);
    contactChat.add(Box.createVerticalStrut(40));
    contactChat.add(jButtonChat);

    contactChat.setLayout(new BoxLayout(contactChat, BoxLayout.Y_AXIS));
  }

  private void initNewCase() {
    contactNewCase = new JPanel();
    JLabel titleNewCase = new JLabel("START A NEW CASE");
    titleNewCase.setFont(new Font("Arial", Font.BOLD, 16));
    titleNewCase.setAlignmentX(Component.CENTER_ALIGNMENT);

    contactNewCaseLabel = new JLabel();
    contactNewCaseLabel.setIcon(
      new ImageIcon("src/main/java/resources/images/contactnewcase.png")
    );
    contactNewCaseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    jButtonNewCase = new JButton("START HERE");
    jButtonNewCase.setAlignmentX(Component.CENTER_ALIGNMENT);

    jButtonNewCase.addActionListener(arg0 -> {
      if (!isFormNewCaseVisible) {
        add(formNewCase, BorderLayout.SOUTH);
        isFormNewCaseVisible = true;
      } else {
        remove(formNewCase);
        isFormNewCaseVisible = false;
      }
      revalidate();
      repaint();
    });

    contactNewCase.add(contactNewCaseLabel);
    contactNewCase.add(Box.createVerticalStrut(80));
    contactNewCase.add(titleNewCase);
    contactNewCase.add(Box.createVerticalStrut(40));
    contactNewCase.add(jButtonNewCase);

    contactNewCase.setLayout(new BoxLayout(contactNewCase, BoxLayout.Y_AXIS));
  }

  private void initFormNewCase() {
    formNewCase = new JPanel();
    formNewCase.setBackground(Color.WHITE);
    formNewCase.setPreferredSize(new Dimension(ABORT, 400));
    JPanel formPanel = new JPanel();
    formPanel.setBackground(Color.WHITE);
    nameLabel = new JLabel("Name");
    emailLabel = new JLabel("Email");
    messageLabel = new JLabel("Message");
    nameField = new JTextField();
    emailField = new JTextField();
    messageArea = new JTextArea();
    submitButton = new JButton("Submit");
    formPanel.setLayout(new GridLayout(0, 2, 10, 10));
    formPanel.add(nameLabel);
    formPanel.add(nameField);
    formPanel.add(emailLabel);
    formPanel.add(emailField);
    formPanel.add(messageLabel);
    formPanel.add(messageArea);
    formPanel.add(submitButton);
    formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    formPanel.setPreferredSize(new Dimension(700, 200));

    formNewCase.add(formPanel);
  }
}
