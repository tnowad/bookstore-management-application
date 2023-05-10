package com.bookstore.gui.forms.checkout;

import com.bookstore.bus.CartBUS;
import com.bookstore.gui.components.panels.MainPanel;
import com.bookstore.gui.forms.shop.ShopCustomerPanel;
import com.bookstore.models.CartModel;
import java.awt.*;
import javax.swing.*;

public class CompletedOrderForm extends JPanel {

  CartModel cartModel;

  public CompletedOrderForm(int cartId) {
    cartModel = CartBUS.getInstance().getModelById(cartId);
    initComponents();
  }

  private void initComponents() {
    setBackground(Color.WHITE);
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    JPanel iconPanel = new JPanel();
    iconPanel.setBackground(getBackground());
    iconPanel.setPreferredSize(new Dimension(200, 200));
    iconPanel.setMaximumSize(new Dimension(400, 400));
    iconPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel checkedIconLabel = new JLabel();
    checkedIconLabel.setIcon(
      new ImageIcon("src/main/java/resources/icons/order-completed.png")
    );
    iconPanel.add(checkedIconLabel);

    JPanel messagePanel = new JPanel();
    messagePanel.setBackground(getBackground());
    messagePanel.setLayout(new BoxLayout(messagePanel, BoxLayout.Y_AXIS));
    messagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel titleLabel = new JLabel("Your order is completed!");
    titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel messageLabel = new JLabel("Thank you for your order!");
    messageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

    messagePanel.add(Box.createVerticalGlue());
    messagePanel.add(titleLabel);
    messagePanel.add(Box.createRigidArea(new Dimension(0, 10)));
    messagePanel.add(messageLabel);
    messagePanel.add(Box.createVerticalGlue());

    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(getBackground());
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
    buttonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JButton continueButton = new JButton("Continue Shopping");
    continueButton.addActionListener(e -> {
      MainPanel.getInstance().showForm(new ShopCustomerPanel());
    });
    continueButton.setPreferredSize(new Dimension(200, 50));
    continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    buttonPanel.add(continueButton);

    add(Box.createVerticalGlue());
    add(iconPanel);
    add(Box.createRigidArea(new Dimension(0, 20)));
    add(messagePanel);
    add(Box.createRigidArea(new Dimension(0, 20)));
    add(buttonPanel);
    add(Box.createVerticalGlue());
  }
}
