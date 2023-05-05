package com.bookstore.gui.forms.customer;

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
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CheckoutCustomerPanel extends JPanel {

  private JPanel acctionBackPreviousPanel;
  private JLabel addressLabel;
  private JTextField addressTextField;
  private JButton backPreviousButton;
  private JRadioButton cashRadioButton;
  private JButton checkoutButton;
  private JPanel creditDetailPanel;
  private JRadioButton creditRadioButton;
  private JLabel cvvLabel;
  private JLabel emailLabel;
  private JTextField emailTextField;
  private JButton exitButton;
  private JRadioButton expressShippingRadioButton;
  private JPanel groupButtonPanel;
  private JPanel groupCheckoutInfoPanel;
  private JPanel infoUserPanel;
  private JRadioButton internationalShippingRadioButton;
  private JLabel jLabel8;
  private JLabel jLabel9;
  private JTextField jTextField5;
  private JTextField jTextField6;
  private JTextField jTextField7;
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
      groupCheckoutInfoPanel.add(creditDetailPanel);
      groupButtonPanel.revalidate();
      groupButtonPanel.repaint();
    });
    cashRadioButton.addActionListener(e -> {
      groupCheckoutInfoPanel.remove(creditDetailPanel);
      groupButtonPanel.revalidate();
      groupButtonPanel.repaint();
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
          System.out.println(cartItemsModel.getCartId());
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
    acctionBackPreviousPanel = new JPanel();
    backPreviousButton = new JButton();
    groupCheckoutInfoPanel = new JPanel();
    infoUserPanel = new JPanel();
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
    cvvLabel = new JLabel();
    jTextField5 = new JTextField();
    jLabel8 = new JLabel();
    jTextField6 = new JTextField();
    jLabel9 = new JLabel();
    jTextField7 = new JTextField();
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

    acctionBackPreviousPanel.setLayout(
      new java.awt.FlowLayout(java.awt.FlowLayout.LEFT)
    );

    backPreviousButton.setText("Back Previous");

    acctionBackPreviousPanel.add(backPreviousButton);

    add(acctionBackPreviousPanel);

    groupCheckoutInfoPanel.setLayout(
      new BoxLayout(groupCheckoutInfoPanel, BoxLayout.X_AXIS)
    );

    infoUserPanel.setPreferredSize(new java.awt.Dimension(500, 83));
    infoUserPanel.setLayout(new java.awt.GridLayout(4, 2));

    nameLabel.setText("Name : ");
    infoUserPanel.add(nameLabel);

    infoUserPanel.add(nameTextField);

    emailLabel.setText("Email :");
    infoUserPanel.add(emailLabel);
    infoUserPanel.add(emailTextField);

    phoneLabel.setText("Phone :");
    infoUserPanel.add(phoneLabel);

    infoUserPanel.add(phoneTextField);

    addressLabel.setText("Address :");
    infoUserPanel.add(addressLabel);
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

    creditDetailPanel.setLayout(
      new BoxLayout(creditDetailPanel, BoxLayout.Y_AXIS)
    );

    cvvLabel.setText("jLabel7");
    creditDetailPanel.add(cvvLabel);

    jTextField5.setText("jTextField5");
    creditDetailPanel.add(jTextField5);

    jLabel8.setText("jLabel8");
    creditDetailPanel.add(jLabel8);

    jTextField6.setText("jTextField6");
    creditDetailPanel.add(jTextField6);

    jLabel9.setText("jLabel9");
    creditDetailPanel.add(jLabel9);

    jTextField7.setText("jTextField7");
    creditDetailPanel.add(jTextField7);

    groupCheckoutInfoPanel.add(creditDetailPanel);

    add(groupCheckoutInfoPanel);

    // productTableScrollPane.setViewportView(productListTable);

    // productListTablePanel.add(productTableScrollPane);

    add(productListTablePanel);

    checkoutButton.setText("Checkout");
    groupButtonPanel.add(checkoutButton);

    exitButton.setText("Exit");
    groupButtonPanel.add(exitButton);

    add(groupButtonPanel);
  }
}
