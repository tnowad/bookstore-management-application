package com.bookstore.gui.components.salary;

import com.bookstore.bus.UserBUS;
import com.bookstore.models.EmployeeModel;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SalaryPanel extends javax.swing.JPanel {

  public SalaryPanel(int serial, EmployeeModel employee) {
    initComponents(serial, employee);
  }

  private void initComponents(int serial, EmployeeModel employee) {
    panel = new JPanel();
    setSerial = new JLabel();
    setId = new JLabel();
    setName = new JLabel();
    setSalary = new JLabel();
    setType = new JLabel();
    setDescription = new JLabel();

    setLayout(new GridLayout(1, 4));

    panel.setLayout(new GridLayout(1, 2));

    setSerial.setText("" + serial);
    panel.add(setSerial);

    setId.setText("" + employee.getUserId());
    panel.add(setId);

    add(panel);

    setName.setText(
      UserBUS.getInstance().getModelById(employee.getUserId()).getName()
    );
    setName.setPreferredSize(new Dimension(50, 16));
    add(setName);

    setSalary.setText("" + employee.getSalary());
    setSalary.setPreferredSize(new Dimension(50, 16));
    add(setSalary);

    setType.setText("" + employee.getEmployeeType());
    setType.setPreferredSize(new Dimension(50, 16));
    add(setType);

    setDescription.setText(employee.getContactInformation());
    setDescription.setPreferredSize(new Dimension(50, 16));
    add(setDescription);
  }

  private JPanel panel;
  private JLabel setDescription;
  private JLabel setId;
  private JLabel setName;
  private JLabel setSalary;
  private JLabel setType;
  private JLabel setSerial;
}
