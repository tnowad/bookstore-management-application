package com.bookstore.gui.form.customer.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import com.bookstore.gui.component.button.Button; 

public class PaymentPanel extends JPanel {

    private JLabel cardHolderLabel;
    private JPanel cardHolderPanel;
    private JTextField cardHolderTextField;
    private JLabel cardNumberLabel;
    private JPanel cardNumberPanel;
    private JTextField cardNumberTextField;
    private JLabel confirmLabel;
    private JPasswordField confirmPasswordField;
    private JPanel confirmPasswordPanel;
    private JLabel dateLabel;
    private JLabel expirationDateLabel;
    private JPanel expirationDatePanel;
    private JTextField expirationTextField;
    private JPanel groupButton;
    private JPanel groupContent;
    private JButton resetButton;
    private JButton updateButton;
    private JLabel updatePaymentHeaderLabel;

    public PaymentPanel() {
        initComponents();
    }

    private void initComponents() {

        updatePaymentHeaderLabel = new JLabel();
        groupContent = new JPanel();
        cardNumberPanel = new JPanel();
        cardNumberLabel = new JLabel();
        cardNumberTextField = new JTextField();
        cardHolderPanel = new JPanel();
        cardHolderLabel = new JLabel();
        cardHolderTextField = new JTextField();
        expirationDatePanel = new JPanel();
        expirationDateLabel = new JLabel();
        expirationTextField = new JTextField();
        dateLabel = new JLabel();
        confirmPasswordPanel = new JPanel();
        confirmLabel = new JLabel();
        confirmPasswordField = new JPasswordField();
        groupButton = new JPanel();
        updateButton = new JButton();
        resetButton = new JButton();

        setMaximumSize(new Dimension(427, 208));
        setMinimumSize(new Dimension(427, 208));
        setPreferredSize(new Dimension(427, 208));
        setLayout(new BorderLayout());

        updatePaymentHeaderLabel.setFont(new Font("Arial", 0, 18));
        updatePaymentHeaderLabel.setText("Update Payment Informations");
        updatePaymentHeaderLabel.setEnabled(false);
        updatePaymentHeaderLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        updatePaymentHeaderLabel.setMaximumSize(new Dimension(37, 30));
        updatePaymentHeaderLabel.setMinimumSize(new Dimension(37, 30));
        updatePaymentHeaderLabel.setPreferredSize(new Dimension(37, 30));
        add(updatePaymentHeaderLabel, BorderLayout.PAGE_START);

        groupContent.setLayout(new BoxLayout(groupContent, BoxLayout.Y_AXIS));

        cardNumberPanel.setMaximumSize(new Dimension(500, 50));
        cardNumberPanel.setMinimumSize(new Dimension(101, 40));
        cardNumberPanel.setLayout(new BoxLayout(cardNumberPanel, BoxLayout.LINE_AXIS));

        cardNumberLabel.setFont(new Font("Arial", 0, 14));
        cardNumberLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardNumberLabel.setText("Card Number");
        cardNumberLabel.setToolTipText("");
        cardNumberLabel.setMaximumSize(new Dimension(100, 30));
        cardNumberLabel.setPreferredSize(new Dimension(150, 30));
        cardNumberLabel.setRequestFocusEnabled(false);
        cardNumberPanel.add(cardNumberLabel);

        cardNumberTextField.setFont(new Font("Arial", 0, 14));
        cardNumberTextField.setPreferredSize(new Dimension(150, 22));
        cardNumberPanel.add(cardNumberTextField);

        groupContent.add(cardNumberPanel);

        cardHolderPanel.setMaximumSize(new Dimension(500, 50));
        cardHolderPanel.setLayout(new BoxLayout(cardHolderPanel, BoxLayout.LINE_AXIS));

        cardHolderLabel.setFont(new Font("Arial", 0, 14));
        cardHolderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cardHolderLabel.setText("Card Holder");
        cardHolderLabel.setToolTipText("");
        cardHolderLabel.setMaximumSize(new Dimension(150, 30));
        cardHolderLabel.setMinimumSize(new Dimension(100, 30));
        cardHolderLabel.setPreferredSize(new Dimension(150, 30));
        cardHolderPanel.add(cardHolderLabel);

        cardHolderTextField.setFont(new Font("Arial", 0, 14));
        cardHolderPanel.add(cardHolderTextField);

        groupContent.add(cardHolderPanel);

        expirationDatePanel.setMaximumSize(new Dimension(500, 50));
        expirationDatePanel.setPreferredSize(new Dimension(438, 40));
        expirationDatePanel.setLayout(new BoxLayout(expirationDatePanel, BoxLayout.LINE_AXIS));

        expirationDateLabel.setFont(new Font("Arial", 0, 14));
        expirationDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        expirationDateLabel.setText("Expiration Date");
        expirationDateLabel.setMaximumSize(new Dimension(150, 30));
        expirationDateLabel.setMinimumSize(new Dimension(100, 30));
        expirationDateLabel.setPreferredSize(new Dimension(150, 30));
        expirationDatePanel.add(expirationDateLabel);

        expirationTextField.setFont(new Font("Arial", 0, 14));
        expirationTextField.setPreferredSize(new Dimension(100, 30));
        expirationDatePanel.add(expirationTextField);

        dateLabel.setFont(new Font("Arial", 0, 14));
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setText("dd/MM/yyyy");
        dateLabel.setMaximumSize(new Dimension(100, 30));
        dateLabel.setMinimumSize(new Dimension(100, 30));
        dateLabel.setPreferredSize(new Dimension(100, 30));
        expirationDatePanel.add(dateLabel);

        groupContent.add(expirationDatePanel);

        confirmPasswordPanel.setMaximumSize(new Dimension(500, 50));
        confirmPasswordPanel
                .setLayout(new BoxLayout(confirmPasswordPanel, BoxLayout.LINE_AXIS));

        confirmLabel.setFont(new Font("Arial", 0, 14));
        confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmLabel.setText("Confirm Password");
        confirmLabel.setMaximumSize(new Dimension(150, 30));
        confirmLabel.setMinimumSize(new Dimension(150, 30));
        confirmLabel.setPreferredSize(new Dimension(150, 30));
        confirmPasswordPanel.add(confirmLabel);

        confirmPasswordField.setFont(new Font("Arial", 0, 14));
        confirmPasswordField.setPreferredSize(new Dimension(100, 23));
        confirmPasswordPanel.add(confirmPasswordField);

        groupContent.add(confirmPasswordPanel);

        add(groupContent, BorderLayout.CENTER);

        updateButton.setText("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        groupButton.add(updateButton);

        resetButton.setText("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        groupButton.add(resetButton);

        add(groupButton, BorderLayout.PAGE_END);
    }

    private void updateButtonActionPerformed(ActionEvent evt) {
    }

    private void resetButtonActionPerformed(ActionEvent evt) {
    }

}
