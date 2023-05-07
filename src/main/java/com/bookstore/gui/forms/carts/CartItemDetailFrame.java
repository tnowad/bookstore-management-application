package com.bookstore.gui.forms.carts;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.util.image.ImageUtils;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CartItemDetailFrame extends JFrame {

  private JLabel imageLabel;
  private JLabel priceLabel;
  private JLabel titleLabel;
  private Button removeButton;
  private Button updateButton;
  private JTextArea descriptionTextArea;
  private JSpinner quantitySpinner;

  private BookModel bookModel;
  private CartItemsModel cartItemsModel;

  public CartItemDetailFrame(int cartId, String isbn) {
    this.bookModel = BookBUS.getInstance().getBookByIsbn(isbn);

    this.cartItemsModel =
      CartItemsBUS.getInstance().getModelByIdAndIsbn(cartId, isbn);

    initComponents();
    setPreferredSize(new Dimension(400, 500));
    setResizable(false);
    pack();
    setLocationRelativeTo(null);
  }

  private void initComponents() {
    setLayout(new BorderLayout());

    imageLabel = new JLabel();
    imageLabel.setIcon(
      new ImageIcon(
        ImageUtils
          .decodeFromBase64(
            bookModel.getImage(),
            "src/main/java/resources/images/product-placeholder.png"
          )
          .getScaledInstance(200, 200, Image.SCALE_SMOOTH)
      )
    );
    imageLabel.setHorizontalAlignment(SwingConstants.CENTER);

    titleLabel = new JLabel(bookModel.getTitle());

    descriptionTextArea = new JTextArea(bookModel.getDescription());
    descriptionTextArea.setLineWrap(true);
    descriptionTextArea.setWrapStyleWord(true);
    descriptionTextArea.setEditable(false);

    priceLabel = new JLabel(String.valueOf(bookModel.getPrice()));

    quantitySpinner =
      new JSpinner(
        new SpinnerNumberModel(
          cartItemsModel == null ? 0 : cartItemsModel.getQuantity(),
          0,
          bookModel.getQuantity(),
          1
        )
      );

    JPanel contentPanel = new JPanel();
    contentPanel.setLayout(new GridBagLayout());
    GridBagConstraints gridBagConstraints = new GridBagConstraints();

    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridwidth = 2;
    gridBagConstraints.gridheight = 1;
    gridBagConstraints.weightx = 1;
    gridBagConstraints.weighty = 1;
    gridBagConstraints.fill = GridBagConstraints.BOTH;
    gridBagConstraints.insets = new Insets(10, 10, 10, 10);
    contentPanel.add(imageLabel, gridBagConstraints);
    gridBagConstraints.gridy = 1;
    contentPanel.add(titleLabel, gridBagConstraints);
    gridBagConstraints.gridy = 2;
    contentPanel.add(descriptionTextArea, gridBagConstraints);
    gridBagConstraints.gridy = 3;
    gridBagConstraints.gridwidth = 1;
    contentPanel.add(priceLabel, gridBagConstraints);
    gridBagConstraints.gridx = 1;
    contentPanel.add(quantitySpinner, gridBagConstraints);

    add(contentPanel, BorderLayout.CENTER);

    JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

    removeButton = new Button("Delete");
    removeButton.addActionListener(removeButtonListener);
    updateButton = new Button("Save");
    updateButton.addActionListener(updateButtonListener);
    actionPanel.add(removeButton);
    actionPanel.add(updateButton);

    add(actionPanel, BorderLayout.SOUTH);
  }

  private ActionListener removeButtonListener = e -> {
    int option = JOptionPane.showConfirmDialog(
      null,
      "Are you sure you want to delete this item?",
      "Confirm",
      JOptionPane.YES_NO_OPTION
    );
    if (option == JOptionPane.YES_OPTION) {
      CartItemsBUS.getInstance().deleteModel(cartItemsModel);
      dispose();
    }
  };

  private ActionListener updateButtonListener = e -> {
    int quantity = (int) quantitySpinner.getValue();
    if (quantity <= 0) {
      JOptionPane.showMessageDialog(
        null,
        "Quantity can't go below 1!",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
      quantity = 1;
    }
    cartItemsModel.setQuantity(quantity);
    CartItemsBUS.getInstance().update(cartItemsModel);
    dispose();
  };
}
