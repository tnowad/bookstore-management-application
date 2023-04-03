package com.bookstore.gui.form.customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;

import com.bookstore.gui.component.GroupInput;

public class PaymentForm extends JPanel {

  private JPanel jPanelPaymentMethod;
  private JPanel jPanelPaymentCredit;
  private JPanel jPanelPaymentCash;
  private JPanel jPanelSubmit;
  private JPanel groupOptionMethod;
  private JLabel jLabelTitle;
  private JRadioButton jRadioCredit;
  private JRadioButton jRadioCash;
  private Button jButtonSubmit;

  private GroupInput groupName;
  private GroupInput groupPhone;
  private GroupInput groupAccountName;
  private GroupInput groupCreditNumber;
  private GroupInput groupCreditCVC;
  private GroupInput groupCreditExpiration;
  private JCheckBox jCheckBox;
  private JPanel containerSubmit;

  private PaymentForm() {
    initComponents();
    initPaymentMethod();
    initPaymentCredit();
    initPaymentCash();
    initSubmit();
    setBackground();

    setLayout(new BorderLayout());
    add(jPanelPaymentMethod, BorderLayout.NORTH);
    add(jPanelPaymentCredit, BorderLayout.CENTER);
    add(jPanelSubmit, BorderLayout.SOUTH);

  }

  private void initComponents() {
    jPanelPaymentMethod = new JPanel();
    groupOptionMethod = new JPanel();
    jLabelTitle = new JLabel("Payment Method");
    jLabelTitle.setFont(new Font("Arial", 0, 48));
    jLabelTitle.setHorizontalAlignment(JLabel.CENTER);
    jRadioCredit = new JRadioButton("Debit or Credit Card", true);
    jRadioCash = new JRadioButton("Cash Payment");
    // group payment credit
    jPanelPaymentCredit = new JPanel();
    groupAccountName = new GroupInput("Username", "show");
    groupCreditNumber = new GroupInput("Credit Card Number", "show");
    groupCreditCVC = new GroupInput("Security Code", "hide");
    groupCreditExpiration = new GroupInput("Card Expiration", "show");
    // group payment cash
    jPanelPaymentCash = new JPanel();
    groupName = new GroupInput("Name", "show");
    groupPhone = new GroupInput("Phone", "show");
    // group submit
    jPanelSubmit = new JPanel();
    jButtonSubmit = new Button("Submit Payment");
    jCheckBox = new JCheckBox("I accept the term  and conditions");
    containerSubmit = new JPanel();

  }

  private void setBackground() {
    setBackground(Color.white);
    List<Object> components = Arrays.asList(
        jPanelPaymentMethod, groupOptionMethod, jPanelPaymentCash,
        jPanelSubmit, containerSubmit, jRadioCash, jRadioCredit, jCheckBox);
    for (Object component : components) {
      ((JComponent) component).setBackground(Color.white);
    }
  }

  public void initPaymentMethod() {
    jPanelPaymentMethod.setPreferredSize(new Dimension(800, 100));

    jLabelTitle.setFont(new Font("Arial", 0, 30));

    jRadioCredit.setFont(new Font("Arial", 0, 16));
    jRadioCredit.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        jPanelPaymentCash.setVisible(false);
        add(jPanelPaymentCredit, BorderLayout.CENTER);
        jPanelPaymentCredit.setVisible(true);
      }
    });

    jRadioCash.setFont(new Font("Arial", 0, 16));
    jRadioCash.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        jPanelPaymentCredit.setVisible(false);
        add(jPanelPaymentCash, BorderLayout.CENTER);
        jPanelPaymentCash.setVisible(true);
      }

    });

    ButtonGroup genderGroup = new ButtonGroup();
    genderGroup.add(jRadioCash);
    genderGroup.add(jRadioCredit);

    jPanelPaymentMethod.add(jLabelTitle);
    groupOptionMethod.add(jRadioCredit);
    groupOptionMethod.add(jRadioCash);
    jPanelPaymentMethod.add(groupOptionMethod);
    jPanelPaymentMethod.setLayout(new GridLayout(2, 1));
  }

  public void initPaymentCredit() {
    jPanelPaymentCredit.setPreferredSize(new Dimension(200, 600));

    groupAccountName.getLabel().setPreferredSize(new Dimension(200, 50));
    groupAccountName.getLabel().setFont(new Font("Arial", 0, 18));

    groupCreditNumber.getLabel().setPreferredSize(new Dimension(200, 50));
    groupCreditNumber.getLabel().setFont(new Font("Arial", 0, 18));

    groupCreditCVC.getLabel().setPreferredSize(new Dimension(200, 50));
    groupCreditCVC.getLabel().setFont(new Font("Arial", 0, 18));

    groupCreditExpiration.getLabel().setPreferredSize(new Dimension(200, 50));
    groupCreditExpiration.getLabel().setFont(new Font("Arial", 0, 18));

    jPanelPaymentCredit.add(groupAccountName);
    jPanelPaymentCredit.add(groupCreditNumber);
    jPanelPaymentCredit.add(groupCreditCVC);
    jPanelPaymentCredit.add(groupCreditExpiration);

    jPanelPaymentCredit.setLayout(new GridLayout(4, 1, -10, 0));
  }

  public void initPaymentCash() {
    jPanelPaymentCash.setPreferredSize(new Dimension(800, 600));

    jPanelPaymentCash.add(groupName);
    jPanelPaymentCash.add(groupPhone);

    jPanelPaymentCash.setLayout(new GridLayout(2, 1));
  }

  public void initSubmit() {
    containerSubmit.add(jButtonSubmit);
    jPanelSubmit.setLayout(new BorderLayout());
    jButtonSubmit.setPreferredSize(new Dimension(150, 50));
    jPanelSubmit.add(jCheckBox, BorderLayout.NORTH);
    jPanelSubmit.add(containerSubmit, BorderLayout.CENTER);
  }

  public static void main(String[] args) {
    new JFrame() {
      {
        PaymentForm paymentForm = new PaymentForm();
        add(paymentForm);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);
      }
    };
  }
}
