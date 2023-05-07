package com.bookstore.gui.forms.orders;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.OrderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.OrderStatus;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.OrderModel;
import com.bookstore.models.UserModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class OrderDetail extends JPanel {

  private Button acceptButton;
  private JPanel container;
  private JPanel groupBottomPanel;
  private JTable productListTable;
  private Button rejectButton;
  private JScrollPane tableListScrollPane;
  private Label titleLabel;
  private Label totalPriceLabel;
  private JTextField totalPriceTextField;
  private JPanel groupHeaderPanel;
  private JLabel nameCustomerLabel;
  private JLabel emailCustomerLabel;
  private JLabel phoneCustomerLabel;

  private int customerId;
  private java.util.List<OrderModel> ordersList;
  private java.util.List<CartModel> cartList;
  private java.util.List<CartItemsModel> cartItemList;
  private List<BookModel> bookList;
  private OrderBUS orderBUS;
  private OrderModel orderModel;
  private CartBUS cartBUS;
  private CartItemsBUS cartItemsBUS;
  private BookBUS bookBUS;
  private UserBUS userBUS;
  private UserModel userModel;

  public OrderDetail(int customerId) {
    this.customerId = customerId;
    updateData();
    initComponents();
    listOrder();
    handleEvent();
  }

  private void handleEvent() {
    acceptButton.addActionListener(acceptButtonActionListener);
    rejectButton.addActionListener(rejectButtonActionListener);
    backToPreviousButton.addActionListener(e -> {
      MainPanel.getInstance().backToPreviousForm();
    });
  }

  private ActionListener acceptButtonActionListener = e -> {
    int answer = JOptionPane.showConfirmDialog(
      this,
      "Do you want to click accept this order?",
      "Confirm",
      JOptionPane.YES_NO_OPTION
    );
    if (answer == JOptionPane.YES_OPTION) {
      orderModel.setStatus(OrderStatus.SOLVED);
      orderBUS.updateModel(orderModel);
      JOptionPane.showMessageDialog(
        this,
        "Order Accepted",
        "Success",
        JOptionPane.INFORMATION_MESSAGE
      );
    }
  };

  private ActionListener rejectButtonActionListener = e -> {
    // The message says if you want to refuse this order?
    int answer = JOptionPane.showConfirmDialog(
      this,
      "Do you want to click reject this order?",
      "Confirm",
      JOptionPane.YES_NO_OPTION
    );
    if (answer == JOptionPane.YES_OPTION) {
      orderModel.setStatus(OrderStatus.REJECTED);
      orderBUS.updateModel(orderModel);
      JOptionPane.showMessageDialog(
        this,
        "Order Rejected",
        "Success",
        JOptionPane.INFORMATION_MESSAGE
      );
    }
  };
  private JPanel backToPreviousPanel;
  private JButton backToPreviousButton;

  private void updateData() {
    userBUS = UserBUS.getInstance();
    if (customerId != 1) {
      userModel = userBUS.getModelById(this.customerId);
      orderBUS = OrderBUS.getInstance();
      ordersList = orderBUS.getAllModels();
      orderModel =
        ordersList
          .stream()
          .filter(order -> order.getCustomerId() == this.customerId)
          .findFirst()
          .orElse(null);
      cartBUS = CartBUS.getInstance();
      cartList = cartBUS.getAllModels();
      cartItemsBUS = CartItemsBUS.getInstance();
      cartItemList = cartItemsBUS.getAllModels();
    } else {}
    bookBUS = BookBUS.getInstance();
    bookList = bookBUS.getAllModels();
  }

  private void listOrder() {
    DefaultTableModel model = new DefaultTableModel() {
      @Override
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    model.addColumn("ISBN");
    model.addColumn("Name");
    model.addColumn("Price");
    model.addColumn("Quantity");
    model.addColumn("Status");
    for (CartModel cartModel : cartList) {
      if (cartModel.getId() == orderModel.getCartId()) {
        for (CartItemsModel cartItemModel : cartItemList) {
          if (cartItemModel.getCartId() == cartModel.getId()) {
            BookModel bookModel = bookList
              .stream()
              .filter(book -> book.getIsbn().equals(cartItemModel.getBookIsbn())
              )
              .findFirst()
              .orElse(null);

            model.addRow(
              new Object[] {
                cartItemModel.getBookIsbn(),
                bookModel.getTitle(),
                bookModel.getPrice(),
                bookModel.getQuantity(),
                bookModel.getStatus(),
              }
            );
            productListTable.setModel(model);
          }
        }
      }
    }
    tableListScrollPane.setViewportView(productListTable);

    container.add(tableListScrollPane, BorderLayout.CENTER);
    tableListScrollPane.setMaximumSize(new Dimension(500, 200));
  }

  private void initComponents() {
    container = new JPanel();
    titleLabel = new Label("Order Details");
    tableListScrollPane = new JScrollPane();
    productListTable = new JTable();
    groupHeaderPanel = new JPanel();
    groupBottomPanel = new JPanel();
    totalPriceLabel = new Label("Total Price");
    nameCustomerLabel = new Label("Name : " + userModel.getName());
    emailCustomerLabel = new Label("Email : " + userModel.getEmail());
    phoneCustomerLabel = new Label("Phone : " + userModel.getPhone());
    totalPriceTextField = new JTextField();
    int totalPrice = orderModel.getTotal();
    totalPriceTextField.setText(String.valueOf(totalPrice));
    totalPriceTextField.setEditable(false);
    acceptButton = new Button();
    rejectButton = new Button();
    backToPreviousPanel = new JPanel();
    backToPreviousButton = new JButton("Back to Previous");

    backToPreviousPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
    backToPreviousPanel.add(backToPreviousButton);

    container.setLayout(new BorderLayout());
    groupHeaderPanel.setLayout(
      new BoxLayout(groupHeaderPanel, javax.swing.BoxLayout.Y_AXIS)
    );

    groupHeaderPanel.add(backToPreviousPanel);
    titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    titleLabel.setText("Order detail");
    groupHeaderPanel.add(titleLabel);

    groupHeaderPanel.setPreferredSize(new Dimension(500, 100));
    groupHeaderPanel.add(nameCustomerLabel);
    groupHeaderPanel.add(emailCustomerLabel);
    groupHeaderPanel.add(phoneCustomerLabel);

    container.add(groupHeaderPanel, BorderLayout.PAGE_START);

    groupBottomPanel.add(totalPriceLabel);
    groupBottomPanel.add(totalPriceTextField);

    acceptButton.setText("Accept");

    groupBottomPanel.add(acceptButton);

    rejectButton.setText("Reject");
    groupBottomPanel.add(rejectButton);

    container.add(groupBottomPanel, BorderLayout.PAGE_END);

    add(container);
  }
}
