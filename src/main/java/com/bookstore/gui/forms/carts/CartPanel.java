package com.bookstore.gui.forms.carts;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.enums.CartStatus;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;

public class CartPanel extends JPanel {
  private static CartPanel instance;
  private UserModel userModel;
  private CartBUS cartBUS;
  private CartModel cartModel;
  private CartItemsBUS cartItemsBUS;
  private List<CartItemsModel> cartItemList;
  private List<CartItemsModel> myCartList;
  private BookBUS bookBUS;
  private List<BookModel> bookList;
  private List<BookModel> myBookList;

  private CartPanel() {
    updateData();
    initComponents();
    listOrder();
    listCart();
  }

  public static CartPanel getInstance() {
    if (instance == null) {
      instance = new CartPanel();
    }
    return instance;
  }

  private void updateData() {
    userModel = Authentication.getCurrentUser();
    cartBUS = CartBUS.getInstance();
    cartModel = cartBUS.getModelById(userModel.getId());
    if (cartModel.getStatus() == CartStatus.PENDING) {
      cartItemsBUS = CartItemsBUS.getInstance();
      cartItemList = cartItemsBUS.getAllModels();
      bookBUS = BookBUS.getInstance();
      bookList = bookBUS.getAllModels();
      for (CartItemsModel cartItemsModel : cartItemList) {
        if (cartItemsModel.getCartId() == cartModel.getId()) {
          myCartList.add(cartItemsModel);
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
    for (BookModel book : bookList) {
      model.addRow(
          new Object[] {
              book.getIsbn(),
              book.getTitle(),
              book.getDescription(),
              book.getPrice(),
              book.getQuantity(),
              book.getStatus(),
              book.getPublisherId(),
              book.getAuthorId(),
          });
      listCartTable.setModel(model);
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
