package com.bookstore.gui.forms.carts;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.gui.components.dialogs.Dialog;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.forms.checkout.CheckoutCustomerForm;
import com.bookstore.gui.forms.general.NoDataPanel;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class CartCustomerForm extends JPanel {

  private JButton deleteAllProductsButton;
  private JButton proceedToCheckoutButton;
  private JLabel totalCostLabel;
  private JPanel groupActionPanel;
  private JPanel groupBottomPanel;
  private JPanel groupTotalCostPanel;
  private JPanel listCartPanel;
  private JScrollPane listCartScrollPane;
  private JTable listCartTable;

  private CartModel cartModel;
  private List<CartItemsModel> cartItemsList;
  private UserModel userModel;

  public CartCustomerForm() {
    updateData();
    initComponents();
    if (cartItemsList.size() > 0) {
      listCart();
    } else {
      listCartPanel.add(new NoDataPanel("Cart is empty"));
    }
    handleEvent();
  }

  private void updateData() {
    userModel = Authentication.getCurrentUser();
    cartModel =
      CartBUS.getInstance().getShoppingCartByUserId(userModel.getId());
    cartItemsList =
      CartItemsBUS.getInstance().getListCartItemsByCartId(cartModel.getId());
  }

  private void initComponents() {
    listCartPanel = new JPanel();
    listCartScrollPane = new JScrollPane();
    listCartTable = new JTable();
    groupBottomPanel = new JPanel();
    groupTotalCostPanel = new JPanel();
    totalCostLabel = new JLabel();

    groupActionPanel = new JPanel();
    deleteAllProductsButton = new JButton();
    proceedToCheckoutButton = new JButton();

    setLayout(new BorderLayout());

    listCartPanel.setLayout(new BorderLayout());

    listCartTable.setPreferredSize(new Dimension(350, 400));

    add(listCartPanel, BorderLayout.CENTER);

    groupBottomPanel.setLayout(new GridLayout(2, 1));

    groupTotalCostPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    totalCostLabel.setFont(new Font("Arial", 0, 14));

    NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(
      new Locale("vi", "VN")
    );

    totalCostLabel.setText(
      "Total cost: " +
      currencyFormatter.format(
        CartBUS.getInstance().getTotalPrice(cartModel.getId())
      )
    );

    totalCostLabel.setPreferredSize(new Dimension(150, 30));
    groupTotalCostPanel.add(totalCostLabel);

    groupBottomPanel.add(groupTotalCostPanel);

    groupActionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    deleteAllProductsButton.setFont(new Font("Arial", 0, 18));
    deleteAllProductsButton.setText("Delete all products");
    deleteAllProductsButton.setMinimumSize(new Dimension(179, 30));
    deleteAllProductsButton.setPreferredSize(new Dimension(190, 30));

    groupActionPanel.add(deleteAllProductsButton);

    proceedToCheckoutButton.setFont(new Font("Arial", 0, 18));
    proceedToCheckoutButton.setText("Proceed to checkout");
    proceedToCheckoutButton.setMaximumSize(new Dimension(200, 30));
    proceedToCheckoutButton.setMinimumSize(new Dimension(200, 30));
    proceedToCheckoutButton.setPreferredSize(new Dimension(200, 30));

    groupActionPanel.add(proceedToCheckoutButton);

    groupBottomPanel.add(groupActionPanel);

    add(groupBottomPanel, BorderLayout.SOUTH);
  }

  private void listCart() {
    CartItemsBUS.getInstance().refreshData();
    updateData();
    DefaultTableModel model = new DefaultTableModel();
    model.addColumn("ISBN");
    model.addColumn("Title");
    model.addColumn("Price");
    model.addColumn("Quantity");
    for (CartItemsModel cartItemsModel : cartItemsList) {
      BookModel bookModel = BookBUS
        .getInstance()
        .getBookByIsbn(cartItemsModel.getBookIsbn());
      model.addRow(
        new Object[] {
          bookModel.getIsbn(),
          bookModel.getTitle(),
          bookModel.getPrice(),
          cartItemsModel.getQuantity(),
        }
      );
    }
    listCartTable.setModel(model);
    listCartScrollPane.setViewportView(listCartTable);
    listCartPanel.add(listCartScrollPane, BorderLayout.CENTER);
    try {
      NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(
        new Locale("vi", "VN")
      );

      totalCostLabel.setText(
        "Total cost: " +
        currencyFormatter.format(
          CartBUS.getInstance().getTotalPrice(cartModel.getId())
        )
      );
    } catch (Exception e) {
      totalCostLabel.setText("0");
    }
  }

  private void handleEvent() {
    listCartTable
      .getSelectionModel()
      .addListSelectionListener(
        new ListSelectionListener() {
          public void valueChanged(ListSelectionEvent event) {
            int selectedRowIndex = listCartTable.getSelectedRow();
            if (selectedRowIndex != -1) {
              String bookIsbn = listCartTable
                .getValueAt(selectedRowIndex, 0)
                .toString();
              new Dialog(new CartItemDetailFrame(cartModel.getId(), bookIsbn));
              listCart();
            }
          }
        }
      );

    proceedToCheckoutButton.addActionListener(e -> {
      if (cartItemsList.isEmpty()) {
        JOptionPane.showMessageDialog(
          null,
          "You have no items in your cart",
          "Warning",
          JOptionPane.WARNING_MESSAGE
        );
      } else {
        int option = JOptionPane.showConfirmDialog(
          null,
          "Do you want to checkout ?",
          "Confirm",
          JOptionPane.YES_NO_OPTION
        );
        if (option == JOptionPane.YES_OPTION) {
          MainPanel
            .getInstance()
            .showFormStack(new CheckoutCustomerForm(cartModel));
        }
      }
    });

    deleteAllProductsButton.addActionListener(e -> {
      if (cartItemsList.isEmpty()) {
        JOptionPane.showMessageDialog(
          null,
          "You have no items in your cart",
          "Warning",
          JOptionPane.WARNING_MESSAGE
        );
      } else {
        int result = JOptionPane.showConfirmDialog(
          null,
          "Are you sure you want to delete all products?",
          "Warning",
          JOptionPane.YES_NO_OPTION,
          JOptionPane.QUESTION_MESSAGE
        );
        if (result == JOptionPane.YES_OPTION) {
          for (CartItemsModel cartItemsModel : cartItemsList) {
            CartItemsBUS.getInstance().deleteModel(cartItemsModel);
          }
          listCart();
        }
      }
    });
  }
}
