package com.bookstore.gui.components.salary;


import com.bookstore.bus.EmployeeBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.models.EmployeeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class EmployeeDetail extends JFrame {

  private JPanel buttonPanel;
  private JButton buttonBack;
  private JButton buttonSave;
  private JPanel contend;
  private JLabel idText;
  private JLabel nameText;
  private JTextField setId;
  private JTextField setName;
  private JLabel title;
  private JLabel typeText;
  private JTextField setType;
  private JLabel salaryText;
  private JTextField setSalary;
  private JLabel contactText;
  private JLabel statusText;
  private JTextField setStatus;
  private JTextField setContact;

  EmployeeBUS providerBUS = EmployeeBUS.getInstance();
  EmployeeModel employee;

  public EmployeeDetail(EmployeeModel employee) {
    initComponents(employee);
    this.employee = employee;
    setLocationRelativeTo(null);
    setResizable(false);
    this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
  }

  private void initComponents(EmployeeModel employee) {
    contend = new JPanel();
    idText = new JLabel();
    setId = new JTextField();
    nameText = new JLabel();
    setName = new JTextField();
    buttonPanel = new JPanel();
    buttonBack = new JButton();
    buttonSave = new JButton();
    title = new JLabel();
    typeText = new JLabel();
    setType = new JTextField();
    salaryText = new JLabel();
    setSalary = new JTextField();
    contactText = new JLabel();
    statusText = new JLabel();
    setStatus = new JTextField();
    setContact = new JTextField();

    contend.setMinimumSize(new Dimension(400, 100));
    contend.setPreferredSize(new Dimension(390, 250));
    contend.setLayout(new FlowLayout(FlowLayout.LEFT));

    title.setFont(new Font("Segoe UI", 1, 18));
    title.setHorizontalAlignment(SwingConstants.LEFT);
    title.setText("Employee ");
    title.setForeground(Color.BLUE);
    title.setPreferredSize(new Dimension(390, 25));
    contend.add(title);

    idText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    idText.setHorizontalAlignment(SwingConstants.RIGHT);
    idText.setText("ID:");
    idText.setPreferredSize(new Dimension(130, 16));
    contend.add(idText);

    setId.setPreferredSize(new Dimension(200, 25));
    setId.setEditable(false);
    setId.setText(""+employee.getUserId());
    contend.add(setId);

    nameText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    nameText.setHorizontalAlignment(SwingConstants.RIGHT);
    nameText.setText("Name:");
    nameText.setPreferredSize(new Dimension(130, 16));
    contend.add(nameText);

    setName.setPreferredSize(new Dimension(200, 25));
    setName.setEditable(false);
    setName.setText(UserBUS.getInstance().getModelById(employee.getUserId()).getName());
    contend.add(setName);

    typeText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    typeText.setHorizontalAlignment(SwingConstants.RIGHT);
    typeText.setText("Employee Type:");
    typeText.setPreferredSize(new Dimension(130, 16));
    contend.add(typeText);

    setType.setPreferredSize(new Dimension(200, 25));
    setType.setEditable(false);
    setType.setText(""+employee.getEmployeeType());
    contend.add(setType);

    
    contactText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    contactText.setHorizontalAlignment(SwingConstants.RIGHT);
    contactText.setText("Contact:");
    contactText.setPreferredSize(new Dimension(130, 16));
    contend.add(contactText);
    
    setContact.setPreferredSize(new Dimension(200, 25));
    setContact.setEditable(false);
    setContact.setText(employee.getContactInformation());
    contend.add(setContact);
    
    statusText.setFont(new Font("Segoe UI", 1, 14)); // NOI18N
    statusText.setHorizontalAlignment(SwingConstants.RIGHT);
    statusText.setText("Status:");
    statusText.setPreferredSize(new Dimension(130, 16));
    contend.add(statusText);
    
    setStatus.setPreferredSize(new Dimension(200, 25));
    setStatus.setEditable(false);
    setStatus.setText(""+UserBUS.getInstance().getModelById(employee.getUserId()).getStatus());
    contend.add(setStatus);
    
    salaryText.setFont(new Font("Segoe UI", 1, 17)); // NOI18N
    salaryText.setForeground(Color.YELLOW);
    salaryText.setHorizontalAlignment(SwingConstants.RIGHT);
    salaryText.setText("Salary:");
    salaryText.setPreferredSize(new Dimension(130, 17));
    contend.add(salaryText);

    setSalary.setPreferredSize(new Dimension(200, 25));
    setSalary.setText(""+employee.getSalary());
    contend.add(setSalary);


    buttonPanel.setPreferredSize(new Dimension(50, 50));
    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 5));
    buttonBack.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/back.png"))
    );
    buttonBack.setPreferredSize(new Dimension(80, 30));
    buttonBack.addActionListener(actionBack);
    buttonPanel.add(buttonBack);

    buttonSave.setIcon(
      new ImageIcon(getClass().getResource("/resources/icons/save.png"))
    );
    buttonSave.setPreferredSize(new Dimension(80, 30));
    buttonSave.addActionListener(actionSave);
    buttonPanel.add(buttonSave);

    getContentPane().add(contend, BorderLayout.PAGE_START);
    getContentPane().add(buttonPanel, BorderLayout.SOUTH);

    pack();
  }

  public ActionListener actionSave = new ActionListener() {

    @Override
    public void actionPerformed(ActionEvent evt) {
      int salary;
      try {
        salary = Integer.parseInt(setSalary.getText().trim());
        if (salary <= 0) {
          throw new NumberFormatException();
        }
      } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Salary is not valid!");
        return;
      }
      int choice = JOptionPane.showConfirmDialog(
        null,
        "Do you want to save?",
        "Confirmation",
        JOptionPane.YES_NO_OPTION
      );
      if (choice == JOptionPane.YES_OPTION) {
        EmployeeBUS.getInstance().updateSalary(employee.getUserId(),salary);
        EmployeeBUS.getInstance().refreshData();
        JOptionPane.showMessageDialog(null, "Complete");
      }
    }
  };
  public ActionListener actionBack = new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
      JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(buttonBack);
      frame.dispose();
    }
  };

}
