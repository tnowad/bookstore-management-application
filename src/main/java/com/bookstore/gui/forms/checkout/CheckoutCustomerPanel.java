package com.bookstore.gui.forms.checkout;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.enums.CartStatus;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.AddressModel;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CheckoutCustomerPanel extends JPanel {

  private JPanel actionBackPreviousPanel;
  private JLabel addressLabel;
  private JTextField addressTextField;
  private JButton backPreviousButton;
  private JRadioButton cashRadioButton;
  private JButton checkoutButton;
  private JPanel creditDetailPanel;
  private JRadioButton creditRadioButton;
  private JLabel cardNumberLabel;
  private JLabel emailLabel;
  private JTextField emailTextField;
  private JButton exitButton;
  private JRadioButton expressShippingRadioButton;
  private JPanel groupButtonPanel;
  private JPanel groupCheckoutInfoPanel;
  private JPanel infoUserPanel;
  private JRadioButton internationalShippingRadioButton;
  private JLabel expirationDateLabel;
  private JLabel cvvLabel;
  private JTextField cardNumberTextField;
  private JTextField expirationDateTextField;
  private JTextField cvvTextField;
  private JLabel nameLabel;
  private JTextField nameTextField;
  private JRadioButton nextDayShippingRadioButton;
  private JPanel optionPaymentMethodPanel;
  private JLabel paymentMethodLabel;
  private JLabel phoneLabel;
  private JTextField phoneTextField;
  private JTable productListTable;
  private JPanel productListTablePanel;
  private JScrollPane productTableScrollPane;
  private JLabel shippingMethodLabel;
  private JRadioButton standardShippingRadioButton;

  private CartModel cartModel;
  private List<CartItemsModel> cartItemList;
  private UserModel userModel;
  private AddressModel addressModel;

  private List<BookModel> bookList;
  private List<CartItemsModel> myCartItemList;

  public CheckoutCustomerPanel(CartModel cartModel) {
    this.cartModel = cartModel;
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
    creditRadioButton.addActionListener(e -> {
      // groupCheckoutInfoPanel.add(creditDetailPanel);

      creditDetailPanel.add(cvvLabel);
      creditDetailPanel.add(cardNumberTextField);

      creditDetailPanel.add(expirationDateLabel);
      creditDetailPanel.add(expirationDateTextField);

      creditDetailPanel.add(cvvLabel);
      creditDetailPanel.add(cvvTextField);

      creditDetailPanel.revalidate();
      creditDetailPanel.repaint();
    });
    cashRadioButton.addActionListener(e -> {
      // groupCheckoutInfoPanel.remove(creditDetailPanel);
      creditDetailPanel.removeAll();
      creditDetailPanel.revalidate();
      creditDetailPanel.repaint();
    });
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
    productTableScrollPane.setViewportView(productListTable);
    productListTablePanel.add(productTableScrollPane, BorderLayout.CENTER);
  }

  private void initComponents() {
    actionBackPreviousPanel = new JPanel();
    backPreviousButton = new JButton();
    groupCheckoutInfoPanel = new JPanel();
    infoUserPanel = new JPanel();
    new JPanel();
    nameLabel = new JLabel();
    nameTextField = new JTextField();
    emailLabel = new JLabel();
    emailTextField = new JTextField();
    phoneLabel = new JLabel();
    phoneTextField = new JTextField();
    addressLabel = new JLabel();
    addressTextField = new JTextField();
    optionPaymentMethodPanel = new JPanel();
    paymentMethodLabel = new JLabel();
    cashRadioButton = new JRadioButton();
    creditRadioButton = new JRadioButton();
    shippingMethodLabel = new JLabel();
    internationalShippingRadioButton = new JRadioButton();
    standardShippingRadioButton = new JRadioButton();
    expressShippingRadioButton = new JRadioButton();
    nextDayShippingRadioButton = new JRadioButton();
    creditDetailPanel = new JPanel();
    cardNumberLabel = new JLabel();
    cardNumberTextField = new JTextField();
    expirationDateLabel = new JLabel();
    expirationDateTextField = new JTextField();
    cvvLabel = new JLabel();
    cvvTextField = new JTextField();
    productListTablePanel = new JPanel();
    productTableScrollPane = new JScrollPane();
    productListTable = new JTable();
    groupButtonPanel = new JPanel();
    checkoutButton = new JButton();
    exitButton = new JButton();
    ButtonGroup groupPaymentMethodRadio = new ButtonGroup();
    groupPaymentMethodRadio.add(cashRadioButton);
    groupPaymentMethodRadio.add(creditRadioButton);

    ButtonGroup groupShippingMethodRadio = new ButtonGroup();
    groupShippingMethodRadio.add(internationalShippingRadioButton);
    groupShippingMethodRadio.add(standardShippingRadioButton);
    groupShippingMethodRadio.add(expressShippingRadioButton);
    groupShippingMethodRadio.add(nextDayShippingRadioButton);

    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    actionBackPreviousPanel.setLayout(
      new java.awt.FlowLayout(java.awt.FlowLayout.LEFT)
    );

    backPreviousButton.setText("Back Previous");

    actionBackPreviousPanel.add(backPreviousButton);

    add(actionBackPreviousPanel);

    groupCheckoutInfoPanel.setLayout(
      new BoxLayout(groupCheckoutInfoPanel, BoxLayout.X_AXIS)
    );

    infoUserPanel.setLayout(new FlowLayout());
    infoUserPanel.setPreferredSize(new java.awt.Dimension(200, 20));
    // infoUserPanel.setLayout(new java.awt.GridLayout(4, 2));

    nameLabel.setText("Name : ");
    nameLabel.setPreferredSize(new Dimension(70, 20));
    infoUserPanel.add(nameLabel);

    nameTextField.setPreferredSize(new Dimension(200, 20));
    infoUserPanel.add(nameTextField);

    emailLabel.setText("Email :");
    emailLabel.setPreferredSize(new Dimension(70, 20));
    infoUserPanel.add(emailLabel);
    emailTextField.setPreferredSize(new Dimension(200, 20));
    infoUserPanel.add(emailTextField);

    phoneLabel.setText("Phone :");
    phoneLabel.setPreferredSize(new Dimension(70, 20));
    infoUserPanel.add(phoneLabel);
    phoneTextField.setPreferredSize(new Dimension(200, 20));
    infoUserPanel.add(phoneTextField);

    addressLabel.setText("Address :");
    addressLabel.setPreferredSize(new Dimension(70, 20));
    infoUserPanel.add(addressLabel);
    addressTextField.setPreferredSize(new Dimension(200, 20));
    infoUserPanel.add(addressTextField);

    groupCheckoutInfoPanel.add(infoUserPanel);

    optionPaymentMethodPanel.setMaximumSize(new java.awt.Dimension(150, 158));
    optionPaymentMethodPanel.setLayout(
      new BoxLayout(optionPaymentMethodPanel, BoxLayout.Y_AXIS)
    );

    paymentMethodLabel.setText("Payment Method");
    optionPaymentMethodPanel.add(paymentMethodLabel);

    cashRadioButton.setText("Cash");
    optionPaymentMethodPanel.add(cashRadioButton);

    creditRadioButton.setText("Credit");
    optionPaymentMethodPanel.add(creditRadioButton);

    shippingMethodLabel.setText("Shipping Method");
    optionPaymentMethodPanel.add(shippingMethodLabel);

    internationalShippingRadioButton.setText("International Shipping  ");
    optionPaymentMethodPanel.add(internationalShippingRadioButton);

    standardShippingRadioButton.setText("Standard Shipping");
    optionPaymentMethodPanel.add(standardShippingRadioButton);

    expressShippingRadioButton.setText("Express Shipping");
    optionPaymentMethodPanel.add(expressShippingRadioButton);

    nextDayShippingRadioButton.setText(" Next Day Shipping");
    optionPaymentMethodPanel.add(nextDayShippingRadioButton);

    groupCheckoutInfoPanel.add(optionPaymentMethodPanel);

    creditDetailPanel.setLayout(new FlowLayout());
    creditDetailPanel.setPreferredSize(new Dimension(230, 20));

    cardNumberLabel.setText("Card Number");
    cardNumberLabel.setPreferredSize(new Dimension(50, 20));
    creditDetailPanel.add(cvvLabel);
    cardNumberTextField.setPreferredSize(new Dimension(170, 20));
    creditDetailPanel.add(cardNumberTextField);

    expirationDateLabel.setText("Expiration date");
    expirationDateLabel.setPreferredSize(new Dimension(50, 20));
    creditDetailPanel.add(expirationDateLabel);
    expirationDateTextField.setPreferredSize(new Dimension(170, 20));
    creditDetailPanel.add(expirationDateTextField);

    cvvLabel.setText("Cvv");
    cvvLabel.setPreferredSize(new Dimension(50, 20));
    creditDetailPanel.add(cvvLabel);
    cvvTextField.setPreferredSize(new Dimension(170, 20));
    creditDetailPanel.add(cvvTextField);

    groupCheckoutInfoPanel.add(creditDetailPanel);

    add(groupCheckoutInfoPanel);

    add(productListTablePanel);

    checkoutButton.setText("Checkout");
    groupButtonPanel.add(checkoutButton);

    exitButton.setText("Exit");
    groupButtonPanel.add(exitButton);

    add(groupButtonPanel);
  }
}
