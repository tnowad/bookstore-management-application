package com.bookstore.gui.forms.customer;

import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.enums.CartStatus;
import com.bookstore.gui.components.labels.Label;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.image.ImageUtils;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class BookItemPanel extends JPanel {

  private JButton addToCartButton;
  private JButton detailButton;
  private JLabel titleLabel;
  private JPanel footerPanel;
  private Label priceLabel;
  private Label imageLabel;

  private UserModel userModel = Authentication.getCurrentUser();
  private List<CartModel> cartList = CartBUS.getInstance().getAllModels();
  private CartModel myCartModel;
  private List<CartItemsModel> cartItemList = CartItemsBUS
    .getInstance()
    .getAllModels();

  private CartItemsModel cartItemsModel;

  private BookModel bookModel;

  public BookItemPanel(BookModel bookModel) {
    this.bookModel = bookModel;
    initComponents();
    updateData();
    handleEvent();
  }

  private void updateData() {
    CartItemsBUS.getInstance().refreshData();
  }

  private void handleEvent() {
    detailButton.addActionListener(e -> {
      MainPanel.getInstance().showFormStack(new BookDetail(bookModel));
    });

    addToCartButton.addActionListener(e -> {
      updateData();
      boolean bookAlreadyInCart = false;
      for (CartModel cartModel : cartList) {
        if (
          cartModel.getStatus().equals(CartStatus.PENDING) &&
          cartModel.getUserId() == userModel.getId()
        ) {
          myCartModel = cartModel;
          break;
        }
      }
      for (CartItemsModel cartItemModel : cartItemList) {
        if (
          cartItemModel.getBookIsbn().equals(bookModel.getIsbn()) &&
          cartItemModel.getCartId() == myCartModel.getId()
        ) {
          // JOptionPane.showMessageDialog(
          //   null,
          //   "This book is already in your cart!"
          // );
          bookAlreadyInCart = true;
          break;
        }
      }
      if (!bookAlreadyInCart) {
        if (myCartModel == null) {
          myCartModel = new CartModel();
          myCartModel.setUserId(userModel.getId());
          myCartModel.setPromotionId(1);
          myCartModel.setStatus(CartStatus.PENDING);
          CartBUS.getInstance().addModel(myCartModel);
        }
        cartItemsModel = new CartItemsModel();
        cartItemsModel.setBookIsbn(bookModel.getIsbn());
        cartItemsModel.setCartId(myCartModel.getId());
        cartItemsModel.setDiscount(ABORT);
        cartItemsModel.setPrice(bookModel.getPrice());
        cartItemsModel.setQuantity(1);
        CartItemsBUS.getInstance().addModel(cartItemsModel);
      }
      JOptionPane.showMessageDialog(null, "This book is add too cart");
    });
  }

  private void initComponents() {
    setLayout(new BorderLayout());
    titleLabel = new JLabel(bookModel.getTitle());
    titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    add(titleLabel, BorderLayout.PAGE_START);

    imageLabel = new Label();
    Image image = null;
    try {
      image = ImageUtils.decodeFromBase64(bookModel.getImage());
    } catch (Exception ex) {
      image =
        new ImageIcon("src/main/java/resources/images/product-placeholder.png")
          .getImage();
    }
    image = image.getScaledInstance(200, 150, Image.SCALE_SMOOTH);
    imageLabel.setIcon(new ImageIcon(image));

    imageLabel.setPreferredSize(new Dimension(200, 150));
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
    add(imageLabel, BorderLayout.CENTER);

    footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
    priceLabel = new Label();
    detailButton = new JButton();
    addToCartButton = new JButton();
    priceLabel.setText(bookModel.getPrice() + " $");
    footerPanel.add(priceLabel);
    detailButton.setText("Book detail");
    footerPanel.add(detailButton);
    addToCartButton.setText("Add to cart");
    footerPanel.add(addToCartButton);
    add(footerPanel, BorderLayout.SOUTH);
  }
}
