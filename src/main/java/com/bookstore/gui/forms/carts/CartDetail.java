package com.bookstore.gui.forms.carts;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import java.awt.*;
import javax.swing.*;

public class CartDetail extends JFrame {

  private int cartId;
  private String bookIsbn;
  private BookModel bookModel;
  private java.util.List<CartItemsModel> cartItemList;
  private CartItemsModel cartItemsModel;

  public CartDetail(int cartId, String bookIsbn) {
    this.cartId = cartId;
    this.bookIsbn = bookIsbn;
    System.out.println(cartId);
    System.out.println(bookIsbn);
    initComponents();
    updateData();
    handleEvent();
  }

  private void updateData() {
    bookModel = BookBUS.getInstance().getBookByIsbn(bookIsbn);
    cartItemList = CartItemsBUS.getInstance().getAllModels();
    for (CartItemsModel cartItemModel : cartItemList) {
      if (
        cartItemModel.getCartId() == cartId &&
        cartItemModel.getBookIsbn().equals(bookIsbn)
      ) {
        cartItemsModel = cartItemModel;
        break;
      }
    }

    if (cartItemsModel != null) {
      bookTitleTextField.setText(bookModel.getTitle());
      bookPriceLabel.setText(String.valueOf(bookModel.getPrice()));
      quantitySpinner.setValue(cartItemsModel.getQuantity());
      descriptionTextArea.setText(bookModel.getDescription());
    }
  }

  private void handleEvent() {
    // chooseBookCheckBox.addItemListener(
    //     e -> {
    //       if (e.getStateChange() == ItemEvent.SELECTED) {
    //         bookImagePanel.setVisible(true);
    //       } else {
    //         bookImagePanel.setVisible(false);
    //       }
    //     }
    // );
    acceptButton.addActionListener(e -> {
      if (cartItemsModel == null) {
        JOptionPane.showMessageDialog(
          this,
          "This book is not in the cart",
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      } else {
        if ((int) quantitySpinner.getValue() <= 0) {
          JOptionPane.showMessageDialog(
            this,
            "Quantity must be greater than 0",
            "Error",
            JOptionPane.ERROR_MESSAGE
          );
        } else {
          cartItemsModel.setQuantity((int) quantitySpinner.getValue());
          System.out.println(cartItemsModel.getQuantity());
          CartItemsBUS.getInstance().updateModel(cartItemsModel);
          JOptionPane.showMessageDialog(
            this,
            "Quantity updated",
            "Success",
            JOptionPane.INFORMATION_MESSAGE
          );
          updateData();
        }
      }
    });
  }

  private void initComponents() {
    cententProductPanel = new JPanel();
    checkboxPanel = new JPanel();
    chooseBookCheckBox = new JCheckBox();
    bookImagePanel = new JPanel();
    contentCartPanel = new JPanel();
    groupHeaderPanel = new JPanel();
    bookTitleTextField = new JTextField();
    acceptButton = new Button("Accept");
    infoDetailPanel = new JPanel();
    groupBottomPanel = new JPanel();
    bookPriceLabel = new JLabel();
    quantitySpinner = new JSpinner();
    descriptionScrollPane = new JScrollPane();
    descriptionPanel = new JPanel();
    descriptionTextArea = new JTextArea();

    setFont(new Font("Arial", 0, 14));
    setMinimumSize(new Dimension(500, 100));
    setPreferredSize(new Dimension(500, 250));
    setLayout(new BorderLayout());

    cententProductPanel.setMaximumSize(new Dimension(155, 100));
    cententProductPanel.setMinimumSize(new Dimension(155, 100));
    cententProductPanel.setPreferredSize(new Dimension(155, 100));
    cententProductPanel.setLayout(new BorderLayout());

    checkboxPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    checkboxPanel.add(chooseBookCheckBox);

    cententProductPanel.add(checkboxPanel, BorderLayout.PAGE_START);

    bookImagePanel.setLayout(new BorderLayout());
    cententProductPanel.add(bookImagePanel, BorderLayout.CENTER);

    add(cententProductPanel, BorderLayout.LINE_START);

    contentCartPanel.setLayout(new BorderLayout());

    bookTitleTextField.setEditable(false);
    bookTitleTextField.setFont(new Font("Arial", 0, 14));
    bookTitleTextField.setHorizontalAlignment(JTextField.LEFT);
    bookTitleTextField.setPreferredSize(new Dimension(250, 30));
    bookTitleTextField.setEditable(false);

    groupHeaderPanel.add(bookTitleTextField);

    groupHeaderPanel.add(acceptButton);

    contentCartPanel.add(groupHeaderPanel, BorderLayout.PAGE_START);

    infoDetailPanel.setLayout(new BorderLayout());

    groupBottomPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

    bookPriceLabel.setFont(new Font("Arial", 0, 18));
    bookPriceLabel.setText("$");
    bookPriceLabel.setMaximumSize(new Dimension(200, 50));
    bookPriceLabel.setMinimumSize(new Dimension(200, 22));
    bookPriceLabel.setPreferredSize(new Dimension(230, 30));
    groupBottomPanel.add(bookPriceLabel);

    quantitySpinner.setFont(new Font("Arial", 0, 18));
    quantitySpinner.setPreferredSize(new Dimension(100, 30));
    groupBottomPanel.add(quantitySpinner);

    // groupBottomPanel.add(deleteProductButton);

    infoDetailPanel.add(groupBottomPanel, BorderLayout.PAGE_END);

    descriptionPanel.setLayout(new GridLayout(1, 0));

    descriptionTextArea.setEditable(false);
    descriptionTextArea.setColumns(20);
    descriptionTextArea.setRows(5);
    descriptionTextArea.setMaximumSize(new Dimension(232, 160));
    descriptionTextArea.setMinimumSize(new Dimension(232, 160));
    descriptionTextArea.setPreferredSize(new Dimension(232, 160));
    descriptionPanel.add(descriptionTextArea);

    descriptionScrollPane.setViewportView(descriptionPanel);

    infoDetailPanel.add(descriptionScrollPane, BorderLayout.CENTER);

    contentCartPanel.add(infoDetailPanel, BorderLayout.CENTER);

    add(contentCartPanel, BorderLayout.CENTER);

    // setTitle("Cart Detail");
    // setLocationRelativeTo(null);
    // setVisible(true);
    pack();
  }

  private JPanel bookImagePanel;
  private JLabel bookPriceLabel;
  private JTextField bookTitleTextField;
  private JPanel cententProductPanel;
  private JPanel checkboxPanel;
  private JCheckBox chooseBookCheckBox;
  private JPanel contentCartPanel;
  private Button acceptButton;
  private JPanel descriptionPanel;
  private JScrollPane descriptionScrollPane;
  private JTextArea descriptionTextArea;
  private JPanel groupBottomPanel;
  private JPanel groupHeaderPanel;
  private JPanel infoDetailPanel;
  private JSpinner quantitySpinner;
}
