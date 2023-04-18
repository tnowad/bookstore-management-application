package com.bookstore.gui.component.frame;

import javax.swing.*;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanelExample extends JFrame implements ActionListener {

    private JPanel cards;
    private final static String LOGIN_PANEL = "Login Panel";
    private final static String SIGNUP_PANEL = "Signup Panel";
    private final static String FORGOT_PANEL = "Forgot Password Panel";
    private final static String[] PANEL_NAMES = { LOGIN_PANEL, SIGNUP_PANEL, FORGOT_PANEL };

    private JPanel listPanel;
    private DefaultListModel<String> listModel;
    private JList<String> list;

    public MainPanelExample() {
        setTitle("Main Panel Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);

        // Create the list panel
        listModel = new DefaultListModel<>();
        listModel.addElement(LOGIN_PANEL);
        listModel.addElement(SIGNUP_PANEL);
        listModel.addElement(FORGOT_PANEL);

        list = new JList<>(listModel);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL);
        list.setVisibleRowCount(-1);

        JScrollPane listScrollPane = new JScrollPane(list);

        JButton addButton = new JButton("New Tab");
        addButton.addActionListener(e -> {
            String componentName = JOptionPane.showInputDialog(this, "Enter component name:");
            if (componentName != null && !componentName.trim().isEmpty()) {
                addTab(componentName);
            }
        });

        listPanel = new JPanel(new BorderLayout());
        listPanel.add(listScrollPane, BorderLayout.CENTER);
        listPanel.add(addButton, BorderLayout.SOUTH);

        // Create the card layout panel
        cards = new JPanel(new CardLayout());

        // Create the login panel
        JPanel loginPanel = new JPanel();
        loginPanel.add(new JLabel("Username: "));
        loginPanel.add(new JTextField(10));
        loginPanel.add(new JLabel("Password: "));
        loginPanel.add(new JPasswordField(10));
        loginPanel.add(new JButton("Login"));

        // Create the signup panel
        JPanel signupPanel = new JPanel();
        signupPanel.add(new JLabel("Name: "));
        signupPanel.add(new JTextField(10));
        signupPanel.add(new JLabel("Email: "));
        signupPanel.add(new JTextField(10));
        signupPanel.add(new JLabel("Password: "));
        signupPanel.add(new JPasswordField(10));
        signupPanel.add(new JButton("Signup"));

        // Create the forgot password panel
        JPanel forgotPanel = new JPanel();
        forgotPanel.add(new JLabel("Email: "));
        forgotPanel.add(new JTextField(10));
        forgotPanel.add(new JButton("Send Password Reset Link"));

        // Add the panels to the card layout panel
        cards.add(loginPanel, LOGIN_PANEL);
        cards.add(signupPanel, SIGNUP_PANEL);
        cards.add(forgotPanel, FORGOT_PANEL);

        // Add the card layout panel to the tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("", null, cards, null);

        // Add the action listeners to the tabbed pane
        for (int i = 0; i < tabbedPane.getTabCount() - 1; i++) {
            JButton closeButton = createCloseButton(i);
            tabbedPane.setTabComponentAt(i, createTabComponent(tabbedPane.getTitleAt(i), closeButton));
        }

        // Add the list selection listener
        list.addListSelectionListener(e -> {
            String selected = list.getSelectedValue();
            if (selected != null) {
                showComponent(selected);
            }
        });

        // Create the main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(listPanel, BorderLayout.WEST);
        mainPanel.add(tabbedPane, BorderLayout.CENTER);

        // Show the main panel
        add(mainPanel);
        setVisible(true);
    }

    // Method to add a new tab with the specified component name
    private void addTab(String componentName) {
        // Check if the component already exists in the tabbed pane
        int index = getTabIndex(componentName);
        if (index >= 0) {
            showComponent(componentName);
        } else {
            // Create a new panel with the specified component name
            JPanel panel = new JPanel();
            JLabel label = new JLabel(componentName);
            panel.add(label);

            // Add the panel to the card layout panel and the tabbed pane
            cards.add(panel, componentName);
            JTabbedPane tabbedPane = (JTabbedPane) cards.getParent();
            JButton closeButton = createCloseButton(tabbedPane.getTabCount() - 2);
            tabbedPane.addTab("", null, panel, componentName);
            tabbedPane.setTabComponentAt(tabbedPane.getTabCount() - 2, createTabComponent(componentName, closeButton));

            // Select the new tab
            showComponent(componentName);
        }
    }

    // Method to show the component with the specified name
    private void showComponent(String componentName) {
        int index = getTabIndex(componentName);
        if (index >= 0) {
            JTabbedPane tabbedPane = (JTabbedPane) cards.getParent();
            tabbedPane.setSelectedIndex(index);
        }
    }

    // Method to get the index of the tab with the specified component name
    private int getTabIndex(String componentName) {
        JTabbedPane tabbedPane = (JTabbedPane) cards.getParent();
        for (int i = 0; i < tabbedPane.getTabCount(); i++) {
            if (tabbedPane.getTitleAt(i).equals(componentName)) {
                return i;
            }
        }
        return -1;
    }

    // Method to create the tab component with the specified title and close button
    private JPanel createTabComponent(String title, JButton closeButton) {
        JPanel tabComponent = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        tabComponent.setOpaque(false);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 5));
        tabComponent.add(titleLabel);
        tabComponent.add(closeButton);

        return tabComponent;
    }

    // Method to create the close button for the tab
    private JButton createCloseButton(int index) {
        JButton closeButton = new JButton("x");
        closeButton.setBorder(BorderFactory.createEmptyBorder());
        closeButton.setFocusable(false);
        closeButton.addActionListener(e -> {
            JTabbedPane tabbedPane = (JTabbedPane) cards.getParent();
            tabbedPane.remove(index);
            listModel.remove(index);
        });
        return closeButton;
    }

    public void actionPerformed(ActionEvent e) {
        // Get the card layout of the card panel
        CardLayout cl = (CardLayout) (cards.getLayout());

        // Get the source of the action
        String source = e.getActionCommand();

        // Show the appropriate card based on the action
        switch (source) {
            case "Login":
                cl.show(cards, LOGIN_PANEL);
                break;
            case "Signup":
                cl.show(cards, SIGNUP_PANEL);
                break;
            case "Send Password Reset Link":
                cl.show(cards, FORGOT_PANEL);
                break;
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        }catch (Exception e) {
            e.printStackTrace();
        }
        new MainPanelExample();
    }
}