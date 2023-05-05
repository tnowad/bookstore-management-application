package com.bookstore.gui.components.orders;

import com.bookstore.bus.UserBUS;
import com.bookstore.gui.forms.orders.OrderDetail;
import com.bookstore.models.OrderModel;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class ItemOrderPanel extends JPanel {

  private JCheckBox checkBox;
  private JLabel setIdEmployee;
  private JLabel setIdOrder;
  private JLabel setNameCustomer;
  private JLabel setSerial;
  private JTextField setStatus;
  private JLabel setTotal;

  public ItemOrderPanel(int serial, OrderModel orderModel) {
    initComponents(serial, orderModel);
    addMouseListener(
      (MouseListener) new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
          OrderDetail orderDetail = new OrderDetail(orderModel.getCustomerId());
          orderDetail.setSize(new Dimension(530, 600));
          orderDetail.setLocationRelativeTo(null);
          orderDetail.setDefaultCloseOperation(
            WindowConstants.DISPOSE_ON_CLOSE
          );
          orderDetail.setVisible(true);
        }
      }
    );
  }

  private void initComponents(int serial, OrderModel orderModel) {
    java.awt.GridBagConstraints gridBagConstraints;

    setSerial = new JLabel();
    setIdOrder = new JLabel();
    setNameCustomer = new JLabel();
    setIdEmployee = new JLabel();
    setStatus = new JTextField();
    checkBox = new JCheckBox();
    setTotal = new JLabel();

    setPreferredSize(new Dimension(611, 53));
    java.awt.GridBagLayout layout = new GridBagLayout();
    layout.columnWeights = new double[] { 1.0, 2.0, 3.0, 4.0, 5.0, 6.0 };
    setLayout(layout);

    setSerial.setFont(new Font("Segoe UI", 0, 14));
    setSerial.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    setSerial.setText("" + serial);
    setSerial.setMaximumSize(new Dimension(12, 18));
    setSerial.setMinimumSize(new Dimension(12, 18));
    setSerial.setPreferredSize(new Dimension(25, 19));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 1;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 18;
    gridBagConstraints.ipady = 35;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
    gridBagConstraints.insets = new Insets(0, 6, 0, 0);
    add(setSerial, gridBagConstraints);

    setIdOrder.setFont(new Font("Segoe UI", 0, 14));
    setIdOrder.setText("" + orderModel.getId());
    setIdOrder.setMinimumSize(new Dimension(15, 20));
    setIdOrder.setPreferredSize(new Dimension(30, 20));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 2;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 41;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
    gridBagConstraints.insets = new Insets(0, 29, 0, 0);
    add(setIdOrder, gridBagConstraints);

    setNameCustomer.setFont(new Font("Segoe UI", 0, 14));
    setNameCustomer.setText(
      UserBUS.getInstance().getModelById(orderModel.getCustomerId()).getName()
    );
    setNameCustomer.setMaximumSize(new Dimension(50, 20));
    setNameCustomer.setPreferredSize(new Dimension(200, 20));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 3;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 105;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
    gridBagConstraints.insets = new Insets(0, 27, 0, 0);
    add(setNameCustomer, gridBagConstraints);

    setIdEmployee.setFont(new Font("Segoe UI", 0, 14));
    setIdEmployee.setText("ok");
    setIdEmployee.setPreferredSize(new Dimension(50, 20));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 4;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 90;
    gridBagConstraints.ipady = 33;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
    gridBagConstraints.insets = new Insets(0, 32, 0, 0);
    add(setIdEmployee, gridBagConstraints);

    setStatus.setEditable(false);
    setStatus.setFont(new Font("Segoe UI", 0, 14));
    setStatus.setHorizontalAlignment(javax.swing.JTextField.CENTER);
    setStatus.setText("BANNED");
    setStatus.addActionListener(
      new ActionListener() {
        public void actionPerformed(ActionEvent evt) {
          setStatusActionPerformed(evt);
        }
      }
    );
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 6;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.ipadx = 25;
    gridBagConstraints.ipady = 10;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
    gridBagConstraints.insets = new Insets(6, 12, 0, 6);
    add(setStatus, gridBagConstraints);

    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 0;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 2;
    gridBagConstraints.ipady = 20;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
    gridBagConstraints.insets = new Insets(6, 0, 0, 0);
    add(checkBox, gridBagConstraints);

    setTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    setTotal.setText("ok");
    setTotal.setPreferredSize(new Dimension(20, 16));
    gridBagConstraints = new GridBagConstraints();
    gridBagConstraints.gridx = 5;
    gridBagConstraints.gridy = 0;
    gridBagConstraints.gridheight = 3;
    gridBagConstraints.ipadx = 67;
    gridBagConstraints.ipady = 37;
    gridBagConstraints.anchor = java.awt.GridBagConstraints.FIRST_LINE_END;
    gridBagConstraints.insets = new Insets(0, 18, 0, 0);
    add(setTotal, gridBagConstraints);
  }

  private void setStatusActionPerformed(ActionEvent evt) {}
}
