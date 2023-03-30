package com.bookstore.gui.form.customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import com.bookstore.gui.component.GroupInput;

public class PaymentForm extends JPanel {

  private JPanel jPanelPaymentMethod;
  private JPanel jPanelPaymentCredit;
  private JPanel jPanelPaymentCash;
  private JPanel jPanelSubmit;
  private JLabel jLabelTitle;
  private JRadioButton jRadioCredit;
  private JRadioButton jRadioCash;
  private JButton jButtonSubmit;

  private GroupInput groupName;
  private GroupInput groupPhone;
  private GroupInput groupAccountName;
  private GroupInput groupCreditNumber;
  private GroupInput groupCreditCVC;
  private GroupInput groupCreditExpiration;

  private PaymentForm() {
    initPaymentMethod();
    initPaymentCredit();
    initPaymentCash();
    initSubmit();

    setLayout(new BorderLayout());
    setBackground(Color.GRAY);
    add(jPanelPaymentMethod, BorderLayout.NORTH);
    add(jPanelPaymentCredit, BorderLayout.CENTER);
    add(jPanelSubmit, BorderLayout.SOUTH);

  }

  public void initPaymentMethod() {
    jPanelPaymentMethod = new JPanel();
    jPanelPaymentMethod.setPreferredSize(new Dimension(800, 100));

    jLabelTitle = new JLabel("Payment Method");
    jLabelTitle.setFont(new Font("sansserif", 0, 20));

    jRadioCredit = new JRadioButton("Debit or Credit Card", true);
    jRadioCredit.setFont(new Font("sansserif", 0, 16));
    jRadioCredit.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        jPanelPaymentCash.setVisible(false);
        add(jPanelPaymentCredit, BorderLayout.CENTER);
        jPanelPaymentCredit.setVisible(true);
      }
    });

    jRadioCash = new JRadioButton("Cash Payment");
    jRadioCash.setFont(new Font("sansserif", 0, 16));
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
    jPanelPaymentMethod.add(jRadioCredit);
    jPanelPaymentMethod.add(jRadioCash);
    jPanelPaymentMethod.setLayout(new GridLayout(3, 1));
  }

  public void initPaymentCredit() {
    jPanelPaymentCredit = new JPanel();
    jPanelPaymentCredit.setPreferredSize(new Dimension(200, 500));

    groupAccountName = new GroupInput("Name Account", "show");
    groupCreditNumber = new GroupInput("Credit Card Number", "hide");
    groupCreditCVC = new GroupInput("Security Code", "show");
    groupCreditExpiration = new GroupInput("Card Expiration", "show");

    jPanelPaymentCredit.add(groupAccountName);
    jPanelPaymentCredit.add(groupCreditNumber);
    jPanelPaymentCredit.add(groupCreditCVC);
    jPanelPaymentCredit.add(groupCreditExpiration);

    jPanelPaymentCredit.setLayout(new GridLayout(4, 1));
  }

  public void initPaymentCash() {
    jPanelPaymentCash = new JPanel();
    jPanelPaymentCash.setPreferredSize(new Dimension(800, 500));

    groupName = new GroupInput("Name", "show");
    groupPhone = new GroupInput("Phone", "show");

    jPanelPaymentCash.add(groupName);
    jPanelPaymentCash.add(groupPhone);

    jPanelPaymentCash.setLayout(new GridLayout(2, 1));
  }

  public void initSubmit() {
    jPanelSubmit = new JPanel();
    jButtonSubmit = new JButton("Submit Payment");
    jButtonSubmit.setPreferredSize(new Dimension(200, 50));

    jPanelSubmit.add(jButtonSubmit);
  }

  public static void main(String[] args) {
    new JFrame() {
      {
        PaymentForm paymentForm = new PaymentForm();
        add(paymentForm);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setVisible(true);
      }
    };
  }
}
