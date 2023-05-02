package com.bookstore.gui.forms.carts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.enums.CartStatus;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;

public class Cart extends JPanel {
  private static Cart instance;
  private UserModel userModel;
  private CartBUS cartBUS;
  private List<CartModel> cartList;
  private CartModel cartModel;
  private CartItemsBUS cartItemsBUS;
  private List<CartItemsModel> cartItemList;
  private List<CartItemsModel> myCartList;
  private BookBUS bookBUS;
  private List<BookModel> bookList;
  private List<BookModel> myBookList;

  private Cart() {
    updateData();
    initComponents();
    listOrder();
    listCart();
  }

  public static Cart getInstance() {
    if (instance == null) {
      instance = new Cart();
    }
    return instance;
  }

  private void updateData() {
    userModel = Authentication.getCurrentUser();
    cartBUS = CartBUS.getInstance();
    cartList = cartBUS.getAllModels();
    for (CartModel cartModel : cartList) {
      if (cartModel.getUserId() == userModel.getId()) {
        this.cartModel = cartModel;
      }
    }
    myCartList = new ArrayList<CartItemsModel>();
    cartItemsBUS = CartItemsBUS.getInstance();
    System.out.println(cartModel.getStatus());
    if (cartModel.getStatus() == CartStatus.PENDING) {
      cartItemList = cartItemsBUS.getAllModels();
      bookBUS = BookBUS.getInstance();
      bookList = bookBUS.getAllModels();
      for (CartItemsModel cartItemsModel : cartItemList) {
        if (cartItemsModel.getCartId() == cartModel.getId()) {
          System.out.println(cartModel.getId());
          myCartList.add(cartItemsModel);
          System.out.println(myCartList.size());
        }
      }
    }
  }

  private void initComponents() {
    listCartPanel = new JPanel();
    listCartScrollPane = new JScrollPane();
    listCartTable = new JTable();
    groupBottomPanel = new JPanel();
    groupTotalCostPanel = new JPanel();
    totalCostLabel = new JLabel();
    totalPriceTextField = new JTextField();
    groupActionPanel = new JPanel();
    chooseAllCheckBox = new JCheckBox();
    deleteAllProductsButton = new JButton();
    proceedToCheckoutButton = new JButton();

    setLayout(new BorderLayout());

    listCartPanel.setLayout(new BorderLayout());

    listCartTable.setPreferredSize(new Dimension(350, 80));

    add(listCartPanel, BorderLayout.CENTER);

    groupBottomPanel.setLayout(new GridLayout(2, 1));

    groupTotalCostPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    totalCostLabel.setFont(new Font("Arial", 0, 14)); // NOI18N
    totalCostLabel.setText("Total cost:");
    totalCostLabel.setPreferredSize(new Dimension(75, 30));
    groupTotalCostPanel.add(totalCostLabel);

    totalPriceTextField.setEditable(false);
    totalPriceTextField.setPreferredSize(new Dimension(200, 30));
    totalPriceTextField.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            totalPriceTextFieldActionPerformed(evt);
          }
        });
    groupTotalCostPanel.add(totalPriceTextField);

    groupBottomPanel.add(groupTotalCostPanel);

    groupActionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    chooseAllCheckBox.setFont(new Font("Arial", 0, 14)); // NOI18N
    chooseAllCheckBox.setText("Choose all");
    chooseAllCheckBox.setPreferredSize(new Dimension(100, 30));
    chooseAllCheckBox.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            chooseAllCheckBoxActionPerformed(evt);
          }
        });
    groupActionPanel.add(chooseAllCheckBox);

    deleteAllProductsButton.setFont(new Font("Arial", 0, 18)); // NOI18N
    deleteAllProductsButton.setText("Delete all products");
    deleteAllProductsButton.setMinimumSize(new Dimension(179, 30));
    deleteAllProductsButton.setPreferredSize(new Dimension(190, 30));
    deleteAllProductsButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            deleteAllProductsButtonActionPerformed(evt);
          }
        });
    groupActionPanel.add(deleteAllProductsButton);

    proceedToCheckoutButton.setFont(new Font("Arial", 0, 18)); // NOI18N
    proceedToCheckoutButton.setText("Proceed to checkout");
    proceedToCheckoutButton.setMaximumSize(new Dimension(200, 30));
    proceedToCheckoutButton.setMinimumSize(new Dimension(200, 30));
    proceedToCheckoutButton.setPreferredSize(new Dimension(200, 30));
    proceedToCheckoutButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            proceedToCheckoutButtonActionPerformed(evt);
          }
        });
    groupActionPanel.add(proceedToCheckoutButton);

    groupBottomPanel.add(groupActionPanel);

    add(groupBottomPanel, BorderLayout.SOUTH);
  }

  private void listOrder() {
  }

  private void listCart() {
    DefaultTableModel model = new DefaultTableModel();
    // "ISBN", "Title", "Quantity", "Price", "Status"
    model.addColumn("ISBN");
    model.addColumn("Title");
    model.addColumn("Price");
    model.addColumn("Quantity");
    for (CartItemsModel cartItemsModel : myCartList) {
      System.out.println("My isbn of cart items: " + cartItemsModel.getBookIsbn());
      for (BookModel bookModel : bookList) {
        System.out.println("My book isbn " + bookModel.getIsbn());
        if (cartItemsModel.getBookIsbn().equalsIgnoreCase(bookModel.getIsbn())) {
          System.out.println(1);
          model.addRow(new Object[] { bookModel.getIsbn(), bookModel.getTitle(), bookModel.getPrice(),
              cartItemsModel.getQuantity() });
              listCartTable.setModel(model);
        }
      }
    }
    listCartScrollPane.setViewportView(listCartTable);
    listCartPanel.add(listCartScrollPane, BorderLayout.CENTER);
  }

  private void totalPriceTextFieldActionPerformed(ActionEvent evt) {
  }

  private void deleteAllProductsButtonActionPerformed(ActionEvent evt) {
  }

  private void proceedToCheckoutButtonActionPerformed(ActionEvent evt) {
  }

  private void chooseAllCheckBoxActionPerformed(ActionEvent evt) {
  }

  private JCheckBox chooseAllCheckBox;
  private JButton deleteAllProductsButton;
  private JPanel groupActionPanel;
  private JPanel groupBottomPanel;
  private JPanel groupTotalCostPanel;
  private JPanel listCartPanel;
  private JScrollPane listCartScrollPane;
  private JTable listCartTable;
  private JButton proceedToCheckoutButton;
  private JLabel totalCostLabel;
  private JTextField totalPriceTextField;
}
