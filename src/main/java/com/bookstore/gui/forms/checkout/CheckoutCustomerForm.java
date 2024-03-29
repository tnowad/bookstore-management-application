package com.bookstore.gui.forms.checkout;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.OrderBUS;
import com.bookstore.bus.PaymentBUS;
import com.bookstore.bus.PaymentMethodBUS;
import com.bookstore.bus.ShippingBUS;
import com.bookstore.enums.BookStatus;
import com.bookstore.enums.CartStatus;
import com.bookstore.enums.OrderStatus;
import com.bookstore.enums.PaymentStatus;
import com.bookstore.enums.ShippingStatus;
import com.bookstore.enums.UserRole;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.AddressModel;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.OrderModel;
import com.bookstore.models.PaymentMethodModel;
import com.bookstore.models.PaymentModel;
import com.bookstore.models.ShippingModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.InputValidator;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CheckoutCustomerForm extends JPanel {

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
  private JTable booksTable;
  private JComboBox<String> shippingMethodComboBox;

  private JScrollPane booksTableScrollPane;
  private JLabel shippingMethodLabel;

  private CartModel cartModel;
  private List<CartItemsModel> cartItemList;
  private UserModel userModel;
  private AddressModel addressModel;

  private List<BookModel> bookList;
  private List<CartItemsModel> myCartItemList;
  private JTextField cardHolderTextField;

  public CheckoutCustomerForm(CartModel cartModel) {
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
    cartItemList = new ArrayList<>();

    for (CartItemsModel cartItemModel : CartItemsBUS
      .getInstance()
      .getAllModels()) {
      if (cartItemModel.getCartId() == cartModel.getId()) {
        cartItemList.add(cartItemModel);
      }
    }

    myCartItemList = new ArrayList<>();

    if (CartStatus.SHOPPING == cartModel.getStatus()) {
      bookList = BookBUS.getInstance().getAllModels();

      for (CartItemsModel cartItemModel : cartItemList) {
        if (cartItemModel.getCartId() == cartModel.getId()) {
          myCartItemList.add(cartItemModel);
        }
      }
    } else {
      cartItemList = CartItemsBUS.getInstance().getAllModels();
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

    switch (userModel.getRole()) {
      case CUSTOMER:
        nameTextField.setEditable(false);
        emailTextField.setEditable(false);
        addressTextField.setEditable(false);
        phoneTextField.setEditable(false);
        break;
      default:
        paymentMethodComboBox.setSelectedItem("Cash");
        paymentMethodComboBox.setEnabled(false);
        break;
    }
  }

  private void handleEvent() {
    backPreviousButton.addActionListener(e -> {
      MainPanel.getInstance().backToPreviousForm();
    });

    paymentMethodComboBox.addActionListener(e -> {
      if (e.getActionCommand().equals("comboBoxChanged")) {
        if (e.getSource() instanceof JComboBox) {
          @SuppressWarnings("unchecked")
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
    });

    checkoutButton.addActionListener(e -> {
      try {
        int cartId = cartModel.getId();
        int customerId = userModel.getId();
        int totalPrice = CartBUS.getInstance().getTotalPrice(cartModel.getId());

        String paymentMethod = paymentMethodComboBox
          .getSelectedItem()
          .toString();
        String shippingMethod = shippingMethodComboBox
          .getSelectedItem()
          .toString();

        OrderModel myOrderModel = new OrderModel();
        myOrderModel.setCartId(cartId);
        if (userModel.getRole().equals(UserRole.EMPLOYEE)) {
          myOrderModel.setEmployeeId(userModel.getId());
          myOrderModel.setStatus(OrderStatus.SOLVED);
        } else {
          myOrderModel.setCustomerId(customerId);
          myOrderModel.setEmployeeId(2);
          myOrderModel.setStatus(OrderStatus.PENDING);
        }
        myOrderModel.setTotal(CartBUS.getInstance().calculateTotal(cartId));
        myOrderModel.setTotal(totalPrice);
        myOrderModel.setPaid(0);
        OrderBUS.getInstance().addModel(myOrderModel);
        OrderBUS.getInstance().refreshData();
        for (OrderModel orderModel : OrderBUS.getInstance().getAllModels()) {
          if (orderModel.getCartId() == cartModel.getId()) {
            myOrderModel = orderModel;
          }
        }
        //Handle quantity for books when checkout:
        for (CartItemsModel cartItemsModel : myCartItemList) {
          BookModel bookModel = BookBUS
            .getInstance()
            .getBookByIsbn(cartItemsModel.getBookIsbn());
          bookModel.setQuantity(
            bookModel.getQuantity() - cartItemsModel.getQuantity()
          );
          if (bookModel.getQuantity() <= 0) {
            bookModel.setStatus(BookStatus.UNAVAILABLE);
          }
          BookBUS.getInstance().updateModel(bookModel);
        }

        PaymentModel myPaymentModel = new PaymentModel();
        myPaymentModel.setOrderId(myOrderModel.getId());
        myPaymentModel.setUserId(customerId);
        myPaymentModel.setAmount(1);
        myPaymentModel.setPaymentMethodId(1);
        myPaymentModel.setStatus(PaymentStatus.PENDING);
        PaymentBUS.getInstance().addModel(myPaymentModel);
        for (PaymentModel paymentModel : PaymentBUS
          .getInstance()
          .getAllModels()) {
          if (paymentModel.getOrderId() == myOrderModel.getId()) {
            myPaymentModel = paymentModel;
          }
        }
        ShippingModel myShippingModel = new ShippingModel();
        myShippingModel.setOrderId(myOrderModel.getId());
        myShippingModel.setShippingMethod(shippingMethod);
        myShippingModel.setAddressId(
          AddressBUS.getInstance().getModelById(customerId).getId()
        );
        myShippingModel.setStatus(ShippingStatus.PENDING);
        ShippingBUS.getInstance().addModel(myShippingModel);

        for (ShippingModel shippingModel : ShippingBUS
          .getInstance()
          .getAllModels()) {
          if (shippingModel.getOrderId() == myOrderModel.getId()) {
            myShippingModel = shippingModel;
          }
        }
        cartModel.setStatus(CartStatus.PENDING);
        CartBUS.getInstance().updateModel(cartModel);
        if (paymentMethod.equals("Cash")) {
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
        PaymentMethodModel paymentMethodModel = new PaymentMethodModel();
        paymentMethodModel.setPaymentId(myPaymentModel.getId());
        paymentMethodModel.setCustomerId(customerId);

        paymentMethodModel.setCardHolder(cardHolder);
        paymentMethodModel.setCardNumber(cardNumber);
        paymentMethodModel.setExpirationDate(expirationDate);
        PaymentMethodBUS.getInstance().addModel(paymentMethodModel);

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
    });
  }

  private void showProductListTable() {
    CartItemsBUS.getInstance().refreshData();
    updateData();
    DefaultTableModel model = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
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
    booksTable.setPreferredSize(new Dimension(400, 300));
    booksTable.setModel(model);
  }

  private void initComponents() {
    setBackground(Color.WHITE);
    setLayout(new BorderLayout());
    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    topPanel.setBackground(Color.WHITE);
    backPreviousButton = new JButton("Back Previous");
    topPanel.add(backPreviousButton);
    add(topPanel, BorderLayout.NORTH);

    nameTextField = new JTextField();
    nameLabel = new JLabel("Name");
    nameLabel.setLabelFor(nameTextField);

    emailTextField = new JTextField();
    emailLabel = new JLabel("Email");
    emailLabel.setLabelFor(emailTextField);

    addressTextField = new JTextField();
    addressLabel = new JLabel("Address");
    addressLabel.setLabelFor(addressTextField);

    phoneTextField = new JTextField();
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

    paymentMethodLabel = new JLabel("Payment Method");
    paymentMethodLabel.setLabelFor(paymentMethodComboBox);

    JPanel checkoutDetailPanel = new JPanel(new GridLayout(0, 2));
    checkoutDetailPanel.setBackground(Color.WHITE);
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
    creditDetailPanel.setBackground(Color.WHITE);

    creditDetailPanel.add(cardNumberLabel);
    creditDetailPanel.add(cardNumberTextField);
    creditDetailPanel.add(cardHolderLabel);
    creditDetailPanel.add(cardHolderTextField);
    creditDetailPanel.add(expirationDateLabel);
    creditDetailPanel.add(expirationDateTextField);
    creditDetailPanel.add(cvvLabel);
    creditDetailPanel.add(cvvTextField);

    booksTable = new JTable();
    booksTableScrollPane = new JScrollPane(booksTable);
    booksTableScrollPane.getVerticalScrollBar().setUnitIncrement(16);

    JPanel mainPanel = new JPanel(new GridBagLayout());
    mainPanel.setBackground(Color.WHITE);

    GridBagConstraints gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    gridBagConstraints.fill = GridBagConstraints.BOTH;
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
    mainPanel.add(booksTableScrollPane, gridBagConstraints);
    add(mainPanel, BorderLayout.CENTER);

    actionPanel =
      new JPanel(new FlowLayout(FlowLayout.RIGHT)) {
        {
          setBackground(Color.WHITE);
        }
      };

    checkoutButton = new JButton("Checkout");
    cancelButton = new JButton("Cancel");
    actionPanel.add(checkoutButton);

    actionPanel.add(cancelButton);
    add(actionPanel, BorderLayout.SOUTH);
  }
}
