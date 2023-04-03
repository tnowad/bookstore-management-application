package com.bookstore.gui.form.cart;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.swing.*;
import javax.swing.border.*;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.model.BookModel;
import com.bookstore.model.CartItemsModel;
import com.bookstore.model.CartModel;
import com.bookstore.model.UserModel;

public class CartUI extends javax.swing.JPanel {

  private JPanel table;
  private JPanel actionForm;
  private int height;
  private int width;
  private JLabel JText;
  private CartSection cartSection;

  public CartUI() throws ClassNotFoundException, SQLException {
    initComponents();
    table();
    actionForm();
    actionForm.setBorder(new LineBorder(Color.red));
    setBorder(new EmptyBorder(10, 10, 10, 10));
    actionForm.add(topAction);
    actionForm.add(centerAction);
    actionForm.add(endAction);
    repaint();
  }

  private void initComponents() {
    setPreferredSize(new Dimension(750, 500));
    height = (int) this.getPreferredSize().getHeight();
    width = (int) this.getPreferredSize().getWidth();

  }

  private JScrollPane scrollPane;

  public void table() throws ClassNotFoundException, SQLException {
    table = new JPanel();
    int rows = 0;
    UserModel user1 = UserBUS.getInstance().getModelById(330);

    CartBUS cartBUS = CartBUS.getInstance();

    Optional<CartModel> optionalUser = cartBUS.getAllModels().stream()
        .filter(cart -> cart.getUserId() == user1.getId() && cart.getStatus().toString().equals("SHOPPING"))
        .findFirst();
    if (optionalUser.isPresent()) {
      CartItemsBUS cartItemsBUS = CartItemsBUS.getInstance();
      List<CartItemsModel> cartItems = cartItemsBUS.getAllModels();

      for (CartItemsModel cart : cartItems) {
        if (cart.getCartId() == optionalUser.get().getId()) {
          BookBUS bookBUS = BookBUS.getInstance();
          List<BookModel> bookList = bookBUS.getAllModels();
          for (BookModel book : bookList) {
            if (book.getIsbn().equals(cart.getBookIsbn())) {
              cartSection = new CartSection(book.getTitle(), book.getPrice(), cart.getQuantity(),
                  cart.getBookIsbn(), cart.getCartId());
              table.add(cartSection);
              rows++;
            }
          }
        }
      }
    }

    table.setLayout(new GridLayout(rows, 1));
    scrollPane = new JScrollPane(table);
    scrollPane.setPreferredSize(new Dimension(width, height - 170));
    add(scrollPane);
  }

  public JPanel getTable() {
    return table;
  }

  private JLabel selectVoucher;
  private JLabel selectAll;
  private JButton delete;
  private JLabel totalAmount;
  private JButton purchase;

  private JPanel topAction;
  private JPanel centerAction;
  private JPanel endAction;

  public void actionForm() {
    actionForm = new JPanel();
    add(actionForm);
    actionForm.setPreferredSize(new Dimension(width, height - 370));
    actionForm.setLayout(new GridLayout(3, 1));

    selectVoucher = new JLabel("Chọn hoặc nhập mã");
    selectAll = new JLabel("Chọn tất cả");
    delete = new JButton("Xóa");
    totalAmount = new JLabel("0");
    purchase = new JButton("Thanh Toán");

    topAction = new JPanel();
    centerAction = new JPanel();
    endAction = new JPanel();

    selectVoucher.setPreferredSize(new Dimension(130, 50));
    selectAll.setPreferredSize(new Dimension(90, 50));
    delete.setPreferredSize(new Dimension(90, 20));
    totalAmount.setPreferredSize(new Dimension(200, 50));
    purchase.setPreferredSize(new Dimension(110, 20));

    topAction.setLayout(new FlowLayout(0, 240, FlowLayout.RIGHT));
    JText = new JLabel("Voucher Shop");
    JText.setPreferredSize(new Dimension(100, 50));
    topAction.add(JText);
    topAction.add(selectVoucher);

    centerAction.setLayout(new FlowLayout());

    endAction.setLayout(new FlowLayout(FlowLayout.CENTER));
    endAction.add(selectAll);
    endAction.add(delete);
    JText = new JLabel("Tổng Thanh Toán:");
    JText.setPreferredSize(new Dimension(110, 20));
    endAction.add(JText);
    endAction.add(totalAmount);
    endAction.add(purchase);

  }

}
