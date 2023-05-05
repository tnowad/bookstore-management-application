package com.bookstore.gui.forms.checkout;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.OrderBUS;
import com.bookstore.enums.CartStatus;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.AddressModel;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.InputValidator;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.validation.Validator;

public class CheckoutCustomerPanel extends JPanel {

  private JLabel addressLabel;
  private JTextField addressTextField;
  private JButton backPreviousButton;
  private JButton checkoutButton;
  private JLabel emailLabel;
  private JTextField emailTextField;
  private JButton cancelButton;
  private JPanel actionPanel;
  private JTextField cardNumberTextField;
  private JTextField expirationDateTextField;
  private JComboBox<String> paymentMethodComboBox;
  private JTextField cvvTextField;
  private JLabel nameLabel;
  private JTextField nameTextField;
  private JLabel paymentMethodLabel;
  private JLabel phoneLabel;
  private JTextField phoneTextField;
  private JTable productListTable;
  private JComboBox<String> shippingMethodComboBox;

  private JScrollPane productTableScrollPane;
  private JLabel shippingMethodLabel;

  private CartModel cartModel;
  private List<CartItemsModel> cartItemList;
  private UserModel userModel;
  private AddressModel addressModel;

  private List<BookModel> bookList;
  private List<CartItemsModel> myCartItemList;
  private JTextField cardHolderTextField;

  public CheckoutCustomerPanel(CartModel cartModel) {
    this.cartModel = cartModel;
    userModel = Authentication.getCurrentUser();
    addressModel = AddressBUS.getInstance().getModelById(userModel.getId());
    initComponents();
    updateData();
    handleEvent();
    showProductListTable();
  }

  private void updateData() {
    userModel = Authentication.getCurrentUser();
    addressModel = AddressBUS.getInstance().getModelById(userModel.getId());
    cartItemList = new ArrayList<CartItemsModel>();
    for (CartItemsModel cartItemModel : CartItemsBUS
      .getInstance()
      .getAllModels()) {
      if (cartItemModel.getCartId() == cartModel.getId()) {
        cartItemList.add(cartItemModel);
      }
    }

    myCartItemList = new ArrayList<CartItemsModel>();
    if (cartModel.getStatus() == CartStatus.PENDING) {
      cartItemList = CartItemsBUS.getInstance().getAllModels();
      bookList = BookBUS.getInstance().getAllModels();
      for (CartItemsModel cartItemsModel : cartItemList) {
        if (cartItemsModel.getCartId() == cartModel.getId()) {
          myCartItemList.add(cartItemsModel);
        }
      }
    }

    nameTextField.setText(userModel.getName());
    emailTextField.setText(userModel.getEmail());
    addressTextField.setText(
      addressModel.getStreet() +
      ", " +
      addressModel.getState() +
      ", " +
      addressModel.getCity() +
      ", " +
      addressModel.getZip()
    );
    phoneTextField.setText(userModel.getPhone());
  }

  private void handleEvent() {
    backPreviousButton.addActionListener(e -> {
      MainPanel.getInstance().backToPreviousForm();
    });
  }

  private void showProductListTable() {
    CartItemsBUS.getInstance().refreshData();
    updateData();
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ISBN");
    model.addColumn("Title");
    model.addColumn("Price");
    model.addColumn("Quantity");
    for (CartItemsModel cartItemsModel : myCartItemList) {
      for (BookModel bookModel : bookList) {
        if (
          cartItemsModel.getBookIsbn().equalsIgnoreCase(bookModel.getIsbn())
        ) {
          model.addRow(
            new Object[] {
              bookModel.getIsbn(),
              bookModel.getTitle(),
              bookModel.getPrice(),
              cartItemsModel.getQuantity(),
            }
          );
        }
      }
    }
    productListTable.setPreferredSize(new Dimension(400, 300));
    productListTable.setModel(model);
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    backPreviousButton = new JButton("Back Previous");
    backPreviousButton.addActionListener(backToPreviousActionListener);
    topPanel.add(backPreviousButton);
    add(topPanel, BorderLayout.NORTH);

    nameTextField = new JTextField(userModel.getName());
    nameTextField.setEditable(false);
    nameLabel = new JLabel("Name");
    nameLabel.setLabelFor(nameTextField);

    emailTextField = new JTextField(userModel.getEmail());
    emailTextField.setEditable(false);
    emailLabel = new JLabel("Email");
    emailLabel.setLabelFor(emailTextField);

    addressTextField =
      new JTextField(
        addressModel.getStreet() +
        ", " +
        addressModel.getState() +
        ", " +
        addressModel.getCity() +
        ", " +
        addressModel.getZip()
      );
    addressTextField.setEditable(false);
    addressLabel = new JLabel("Address");
    addressLabel.setLabelFor(addressTextField);

    phoneTextField = new JTextField(userModel.getPhone());
    phoneTextField.setEditable(false);
    phoneLabel = new JLabel("Phone");
    phoneLabel.setLabelFor(phoneTextField);

    shippingMethodComboBox =
      new JComboBox<String>(
        new String[] {
          "Standard Shipping",
          "Express Shipping",
          "Next Day Shipping",
          "International Shipping",
        }
      );
    shippingMethodLabel = new JLabel("Shipping Method");
    shippingMethodLabel.setLabelFor(shippingMethodComboBox);

    paymentMethodComboBox =
      new JComboBox<String>(new String[] { "Credit", "Cash" });
    paymentMethodComboBox.addActionListener(paymentMethodActionListener);

    paymentMethodLabel = new JLabel("Payment Method");
    paymentMethodLabel.setLabelFor(paymentMethodComboBox);

    JPanel checkoutDetailPanel = new JPanel(new GridLayout(0, 2));
    checkoutDetailPanel.add(nameLabel);
    checkoutDetailPanel.add(nameTextField);
    checkoutDetailPanel.add(emailLabel);
    checkoutDetailPanel.add(emailTextField);
    checkoutDetailPanel.add(addressLabel);
    checkoutDetailPanel.add(addressTextField);
    checkoutDetailPanel.add(phoneLabel);
    checkoutDetailPanel.add(phoneTextField);
    checkoutDetailPanel.add(shippingMethodLabel);
    checkoutDetailPanel.add(shippingMethodComboBox);
    checkoutDetailPanel.add(paymentMethodLabel);
    checkoutDetailPanel.add(paymentMethodComboBox);

    JLabel cardNumberLabel = new JLabel("Card Number");
    cardNumberTextField = new JTextField();
    JLabel cardHolderLabel = new JLabel("Card Holder");
    cardHolderTextField = new JTextField();
    JLabel expirationDateLabel = new JLabel("Expiration Date");
    expirationDateTextField = new JTextField();
    JLabel cvvLabel = new JLabel("CVV");
    cvvTextField = new JTextField();

    JPanel creditDetailPanel = new JPanel(new GridLayout(0, 2));

    creditDetailPanel.add(cardNumberLabel);
    creditDetailPanel.add(cardNumberTextField);
    creditDetailPanel.add(cardHolderLabel);
    creditDetailPanel.add(cardHolderTextField);
    creditDetailPanel.add(expirationDateLabel);
    creditDetailPanel.add(expirationDateTextField);
    creditDetailPanel.add(cvvLabel);
    creditDetailPanel.add(cvvTextField);

    productListTable = new JTable();
    productTableScrollPane = new JScrollPane(productListTable);

    JPanel mainPanel = new JPanel(new GridBagLayout());

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    mainPanel.add(checkoutDetailPanel, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.weightx = 0.5;
    mainPanel.add(creditDetailPanel, gridBagConstraints);
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 1;
    gridBagConstraints.weightx = 1;
    gridBagConstraints.gridwidth = 2;
    mainPanel.add(productTableScrollPane, gridBagConstraints);
    add(mainPanel, BorderLayout.CENTER);

    actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    checkoutButton = new JButton("Checkout");
    checkoutButton.addActionListener(checkoutActionListener);
    cancelButton = new JButton("Cancel");
    cancelButton.addActionListener(backToPreviousActionListener);
    actionPanel.add(checkoutButton);

    actionPanel.add(cancelButton);
    add(actionPanel, BorderLayout.SOUTH);
  }

  private ActionListener paymentMethodActionListener = e -> {
    if (e.getActionCommand().equals("comboBoxChanged")) {
      if (e.getSource() instanceof JComboBox) {
        JComboBox<String> cb = (JComboBox<String>) e.getSource();
        String paymentMethod = (String) cb.getSelectedItem();
        if (paymentMethod.equals("Cash")) {
          cardNumberTextField.setEditable(false);
          cardHolderTextField.setEditable(false);
          expirationDateTextField.setEditable(false);
          cvvTextField.setEditable(false);
        } else {
          cardNumberTextField.setEditable(true);
          cardHolderTextField.setEditable(true);
          expirationDateTextField.setEditable(true);
          cvvTextField.setEditable(true);
        }
      }
    }
  };

  private ActionListener checkoutActionListener = e -> {
    try {
      int cartId = cartModel.getId();
      int customerId = userModel.getId();

      String paymentMethod = paymentMethodComboBox.getSelectedItem().toString();
      String shippingMethod = shippingMethodComboBox
        .getSelectedItem()
        .toString();
      if (paymentMethod.equals("Cash")) {
        OrderBUS
          .getInstance()
          .createCustomerOrder(
            cartId,
            customerId,
            shippingMethod,
            paymentMethod,
            null,
            null,
            null,
            null
          );
        MainPanel
          .getInstance()
          .showForm(new CompletedOrderForm(cartModel.getId()));
        return;
      }
      String cardNumber = InputValidator.validateCardNumber(
        cardNumberTextField.getText()
      );
      String cardHolder = InputValidator.validateCardHolder(
        cardHolderTextField.getText()
      );
      String expirationDate = InputValidator.validateExpirationDate(
        expirationDateTextField.getText()
      );
      String cvv = InputValidator.validateCvv(cvvTextField.getText());

      OrderBUS
        .getInstance()
        .createCustomerOrder(
          cartId,
          customerId,
          shippingMethod,
          paymentMethod,
          cardNumber,
          cardHolder,
          expirationDate,
          cvv
        );
      MainPanel
        .getInstance()
        .showForm(new CompletedOrderForm(cartModel.getId()));
    } catch (Exception exception) {
      JOptionPane.showMessageDialog(
        null,
        exception.getMessage(),
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
    }
  };
  private ActionListener backToPreviousActionListener = e -> {
    MainPanel.getInstance().backToPreviousForm();
  };
}
