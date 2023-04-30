package com.bookstore.gui.form.salesman.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.label.Label;

public class OrderDetail extends JFrame {

    public OrderDetail() {
        initComponents();
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
        acceptButton = new Button();
        rejectButton = new Button();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        container.setLayout(new BorderLayout());
        groupHeaderPanel.setLayout(new javax.swing.BoxLayout(groupHeaderPanel, javax.swing.BoxLayout.Y_AXIS));

        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Order detail");
        groupHeaderPanel.add(titleLabel);

        nameCustomerLabel.setText("Name : ");
        groupHeaderPanel.add(nameCustomerLabel);

        container.add(groupHeaderPanel, java.awt.BorderLayout.PAGE_START);

        productListTable.setModel(new DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Id", "Name", "Image", "Price", "Quantity"
                }));
        tableListScrollPane.setViewportView(productListTable);

        container.add(tableListScrollPane, BorderLayout.CENTER);

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
                new OrderDetail().setVisible(true);
            }
        });
    }

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

}
