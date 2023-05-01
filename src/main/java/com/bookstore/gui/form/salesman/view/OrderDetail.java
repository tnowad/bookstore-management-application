package com.bookstore.gui.form.salesman.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import org.apache.commons.math3.geometry.partitioning.BSPTreeVisitor.Order;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.bookstore.bus.BookBUS;
import com.bookstore.bus.CartBUS;
import com.bookstore.bus.CartItemsBUS;
import com.bookstore.bus.OrderBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.dao.CartDAO;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.label.Label;
import com.bookstore.models.BookModel;
import com.bookstore.models.CartItemsModel;
import com.bookstore.models.CartModel;
import com.bookstore.models.OrderModel;
import com.bookstore.models.UserModel;
import com.bookstore.models.OrderModel.Status;

public class OrderDetail extends JFrame {
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
    }

    private ActionListener acceptButtonActionListener = e -> {
        // the message says do you want to click accept this order?
        int answer = JOptionPane.showConfirmDialog(this, "Do you want to click accept this order?", "Confirm",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            orderModel.setStatus(Status.SOLVED);
            orderBUS.updateModel(orderModel);
            JOptionPane.showMessageDialog(this, "Order Accepted", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    };

    private ActionListener rejectButtonActionListener = e -> {
        // The message says if you want to refuse this order?
        int answer = JOptionPane.showConfirmDialog(this, "Do you want to click reject this order?", "Confirm",
                JOptionPane.YES_NO_OPTION);
        if (answer == JOptionPane.YES_OPTION) {
            orderModel.setStatus(Status.REJECTED);
            orderBUS.updateModel(orderModel);
            JOptionPane.showMessageDialog(this, "Order Rejected", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        }
    };

    private void updateData() {
        userBUS = UserBUS.getInstance();
        userModel = userBUS.getModelById(this.customerId);
        orderBUS = OrderBUS.getInstance();
        ordersList = orderBUS.getAllModels();
        orderModel = ordersList.stream()
                .filter(order -> order.getCustomerId() == this.customerId)
                .findFirst()
                .orElse(null);
        cartBUS = CartBUS.getInstance();
        cartList = cartBUS.getAllModels();
        cartItemsBUS = CartItemsBUS.getInstance();
        cartItemList = cartItemsBUS.getAllModels();
        bookBUS = BookBUS.getInstance();
        bookList = bookBUS.getAllModels();

        // int totalPrice = orderModel.getTotal();
        // totalPriceTextField.setText(String.valueOf(totalPrice));
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
                CartItemsModel cartItemsModel = cartItemList.stream()
                        .filter(carttItemModel -> carttItemModel.getCartId() == cartModel.getId())
                        .findFirst()
                        .orElse(null);
                BookModel bookModel = bookList.stream()
                        .filter(book -> book.getIsbn().equals(cartItemsModel.getBookIsbn()))
                        .findFirst()
                        .orElse(null);

                model.addRow(
                        new Object[] { cartItemsModel.getBookIsbn(), bookModel.getTitle(),
                                bookModel.getPrice(),
                                bookModel.getQuantity(),
                                bookModel.getStatus()
                        });
                productListTable.setModel(model);
            }
        }
        tableListScrollPane.setViewportView(productListTable);

        container.add(tableListScrollPane, BorderLayout.CENTER);

    }

    private void initComponents() {

        container = new JPanel();
        titleLabel = new Label("Order Details");
        tableListScrollPane = new JScrollPane();
        productListTable = new JTable();
        groupHeaderPanel = new JPanel();
        groupBottomPanel = new JPanel();
        totalPriceLabel = new Label("Total Price");
        nameCustomerLabel = new Label("Customer");
        totalPriceTextField = new JTextField();
        int totalPrice = orderModel.getTotal();
        totalPriceTextField.setText(String.valueOf(totalPrice));
        totalPriceTextField.setEditable(false);
        acceptButton = new Button();
        rejectButton = new Button();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        container.setLayout(new BorderLayout());
        groupHeaderPanel.setLayout(new javax.swing.BoxLayout(groupHeaderPanel, javax.swing.BoxLayout.Y_AXIS));

        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Order detail");
        groupHeaderPanel.add(titleLabel);

        nameCustomerLabel.setText("Name : " + userModel.getName());
        groupHeaderPanel.add(nameCustomerLabel);

        container.add(groupHeaderPanel, java.awt.BorderLayout.PAGE_START);

        groupBottomPanel.add(totalPriceLabel);
        groupBottomPanel.add(totalPriceTextField);

        acceptButton.setText("Accept");
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                acceptButtonActionPerformed(evt);
            }
        });
        groupBottomPanel.add(acceptButton);

        rejectButton.setText("Reject");
        groupBottomPanel.add(rejectButton);

        container.add(groupBottomPanel, BorderLayout.PAGE_END);

        getContentPane().add(container);

        pack();
    }

    private void acceptButtonActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }

    public static void main(String args[]) {

        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(OrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(OrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(OrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(OrderDetail.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new OrderDetail(1).setVisible(true);
            }
        });
    }

}
