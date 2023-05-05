package com.bookstore.gui.forms.customer;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.enums.CartStatus;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.models.AddressModel;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CheckoutCustomerPanel extends JFrame {

  private Label addressLabel;
  private JTextField addressTextField;
  private Label cardNumberLabel;
  private JTextField cardNumberTextField;
  private JButton checkoutButton;
  private Label cvvLabel;
  private JTextField cvvTextField;
  private Label emailLabel;
  private JTextField emailTextField;
  private JButton exitButton;
  private Label expirationDateLabel;
  private JTextField expirationDateTextField;
  private JRadioButton expressShippingRadioButton;
  private JPanel groupButtonPanel;
  private JPanel groupContentPanel;
  private JPanel groupHeaderPanel;
  private JPanel groupCreditCardPanel;
  private JPanel groupPaymentMethodPanel;
  private JPanel groupTableProductPanel;
  private JRadioButton internationalShippingRadioButton;
  private Label paymentMethodLabel;
  private JRadioButton jRadioButton1;
  private JRadioButton jRadioButton2;
  private JScrollPane jScrollPane1;
  private JTable productListTable;
  private JTextField jTextField6;
  private Label nameLabel;
  private JTextField nameTextField;
  private JRadioButton nextDayShippingRadioButton;
  private Label phoneLabel;
  private JTextField phoneTextField;
  private Label shippingMethodLabel;
  private JRadioButton standardShippingRadioButton;

  // private int cartId;
  private CartBUS cartBUS;
  private CartModel cartModel;
  private CartItemsBUS cartItemsBUS;
  private java.util.List<CartItemsModel> cartItemList;
  private UserModel userModel = Authentication.getCurrentUser();
  private AddressBUS addressBUS;
  private AddressModel addressModel;
  private BookBUS bookBUS;

  private java.util.List<BookModel> bookList;
  private java.util.List<CartItemsModel> myCartList;
  private java.util.List<CartModel> cartList;

  public CheckoutCustomerPanel(CartModel cartModel) {
    this.cartModel = cartModel;
    initComponents();
    updateData();
    handleEvent();
    showProductListTable();
  }

  private void updateData() {
    addressBUS = AddressBUS.getInstance();
    addressModel = addressBUS.getModelById(userModel.getId());
    cartBUS = CartBUS.getInstance();
    // cartModel = cartBUS.getModelById(cartId);
    cartItemsBUS = CartItemsBUS.getInstance();
    cartItemList = new ArrayList<CartItemsModel>();
    for (CartItemsModel cartItemModel : cartItemsBUS.getAllModels()) {
      if (cartItemModel.getCartId() == cartModel.getId()) {
        cartItemList.add(cartItemModel);
      }
      //

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

  private void initComponents() {
    jTextField6 = new JTextField();
    groupHeaderPanel = new JPanel();
    nameLabel = new Label("Name");
    nameTextField = new JTextField();
    emailLabel = new Label("Email");
    emailTextField = new JTextField();
    addressLabel = new Label("Address");
    addressTextField = new JTextField();
    phoneLabel = new Label("Phone");
    phoneTextField = new JTextField();
    groupContentPanel = new JPanel();
    groupPaymentMethodPanel = new JPanel();
    paymentMethodLabel = new Label("Payment Method");
    jRadioButton1 = new JRadioButton();
    jRadioButton2 = new JRadioButton();
    shippingMethodLabel = new Label("Shipping method");
    internationalShippingRadioButton = new JRadioButton();
    standardShippingRadioButton = new JRadioButton();
    expressShippingRadioButton = new JRadioButton();
    nextDayShippingRadioButton = new JRadioButton();
    groupCreditCardPanel = new JPanel();
    cardNumberLabel = new Label("Card number");
    cardNumberTextField = new JTextField();
    expirationDateLabel = new Label("Expiration");
    expirationDateTextField = new JTextField();
    cvvLabel = new Label("Cvv");
    cvvTextField = new JTextField();
    groupTableProductPanel = new JPanel();
    jScrollPane1 = new JScrollPane();
    productListTable = new JTable();
    groupButtonPanel = new JPanel();
    checkoutButton = new JButton();
    exitButton = new JButton();

    jTextField6.setText("jTextField6");

    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    setPreferredSize(new Dimension(500, 409));

    groupHeaderPanel.setLayout(new GridLayout(4, 2));

    groupHeaderPanel.add(nameLabel);

    nameTextField.setPreferredSize(new Dimension(50, 22));

    groupHeaderPanel.add(nameTextField);

    groupHeaderPanel.add(emailLabel);
    groupHeaderPanel.add(emailTextField);

    groupHeaderPanel.add(addressLabel);
    groupHeaderPanel.add(addressTextField);

    groupHeaderPanel.add(phoneLabel);

    groupHeaderPanel.add(phoneTextField);

    getContentPane().add(groupHeaderPanel, BorderLayout.PAGE_START);

    groupContentPanel.setPreferredSize(new Dimension(100, 277));
    groupContentPanel.setLayout(
      new BoxLayout(groupContentPanel, BoxLayout.LINE_AXIS)
    );

    groupPaymentMethodPanel.setLayout(
      new BoxLayout(groupPaymentMethodPanel, BoxLayout.Y_AXIS)
    );

    groupPaymentMethodPanel.add(paymentMethodLabel);

    ButtonGroup groupPaymentMethodRadio = new ButtonGroup();

    jRadioButton1.setText("Cash");
    groupPaymentMethodPanel.add(jRadioButton1);

    jRadioButton2.setText("Credit");
    groupPaymentMethodPanel.add(jRadioButton2);

    groupPaymentMethodRadio.add(jRadioButton1);
    groupPaymentMethodRadio.add(jRadioButton2);

    groupPaymentMethodPanel.add(shippingMethodLabel);

    ButtonGroup groupShippingMethodRadio = new ButtonGroup();

    groupShippingMethodRadio.add(internationalShippingRadioButton);
    groupShippingMethodRadio.add(standardShippingRadioButton);
    groupShippingMethodRadio.add(expressShippingRadioButton);
    groupShippingMethodRadio.add(nextDayShippingRadioButton);

    groupPaymentMethodPanel.add(internationalShippingRadioButton);
    groupPaymentMethodPanel.add(standardShippingRadioButton);
    groupPaymentMethodPanel.add(expressShippingRadioButton);
    groupPaymentMethodPanel.add(nextDayShippingRadioButton);

    internationalShippingRadioButton.setText("5$-International Shipping  ");

    standardShippingRadioButton.setText("10$-Standard Shipping");

    expressShippingRadioButton.setText("15$-Express Shipping");

    nextDayShippingRadioButton.setText("20$-Next Day Shipping");

    groupContentPanel.add(groupPaymentMethodPanel);

    groupCreditCardPanel.setMaximumSize(new Dimension(500, 200));
    groupCreditCardPanel.setLayout(
      new BoxLayout(groupCreditCardPanel, BoxLayout.Y_AXIS)
    );

    groupCreditCardPanel.add(cardNumberLabel);

    cardNumberTextField.setPreferredSize(new Dimension(73, 10));

    groupCreditCardPanel.add(cardNumberTextField);

    groupCreditCardPanel.add(expirationDateLabel);

    expirationDateTextField.setPreferredSize(new Dimension(73, 10));
    groupCreditCardPanel.add(expirationDateTextField);

    groupCreditCardPanel.add(cvvLabel);

    cvvTextField.setPreferredSize(new Dimension(73, 10));
    groupCreditCardPanel.add(cvvTextField);

    // groupContentPanel.add(groupInforCreditCardPanel);

    getContentPane().add(groupContentPanel, BorderLayout.CENTER);

    productListTable.setPreferredSize(new Dimension(250, 50));
    jScrollPane1.setViewportView(productListTable);

    groupTableProductPanel.add(jScrollPane1);

    getContentPane().add(groupTableProductPanel, BorderLayout.LINE_END);

    checkoutButton.setText("Checkout");
    groupButtonPanel.add(checkoutButton);

    exitButton.setText("Exit");
    groupButtonPanel.add(exitButton);

    getContentPane().add(groupButtonPanel, BorderLayout.PAGE_END);

    pack();
  }

  private void showProductListTable() {
    CartItemsBUS.getInstance().refreshData();
    updateData();
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ISBN");
    model.addColumn("Title");
    model.addColumn("Price");
    model.addColumn("Quantity");
    for (CartItemsModel cartItemsModel : myCartList) {
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
    productListTable.setModel(model);
    jScrollPane1.setViewportView(productListTable);
    groupTableProductPanel.add(jScrollPane1, BorderLayout.CENTER);
  }

  private void handleEvent() {
    jRadioButton2.addActionListener(e -> {
      groupContentPanel.add(groupCreditCardPanel);
    });
    jRadioButton1.addActionListener(e -> {
      groupContentPanel.remove(groupCreditCardPanel);
      groupButtonPanel.revalidate();
      groupButtonPanel.repaint();
    });
  }
}
