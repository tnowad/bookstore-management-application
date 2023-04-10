/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.bookstore.gui.form.admin.component;

import com.bookstore.bus.UserBUS;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.swing.JCheckBox;
import javax.swing.border.Border;
import javax.swing.*;

import com.bookstore.model.UserModel;

/**
 *
 * @author yanti
 */
public class UserComponent extends javax.swing.JPanel implements MouseListener {

  /**
   * Creates new form UserComponent
   * 
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  public UserComponent() throws ClassNotFoundException, SQLException {
    initComponents();

  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   * 
   * @throws SQLException
   * @throws ClassNotFoundException
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
  private void initComponents() throws ClassNotFoundException, SQLException {

    AdminCart = new javax.swing.JPanel();
    IconAdmin = new javax.swing.JLabel();
    TextAdmin = new javax.swing.JLabel();
    NewAdmin = new javax.swing.JLabel();
    QuatityAdmin = new javax.swing.JLabel();
    Customer = new javax.swing.JPanel();
    IconCustomer = new javax.swing.JLabel();
    TextCustomer = new javax.swing.JLabel();
    NewCustomer = new javax.swing.JLabel();
    QuatityCustomer = new javax.swing.JLabel();
    Employee = new javax.swing.JPanel();
    IconEmployee = new javax.swing.JLabel();
    TextEmployee = new javax.swing.JLabel();
    NewEmployee = new javax.swing.JLabel();
    QuatityEmployee = new javax.swing.JLabel();
    Title = new javax.swing.JLabel();
    ButtonBaned = new javax.swing.JButton();
    ButtonCreate = new javax.swing.JButton();
    scrollPane1 = new java.awt.ScrollPane();
    table = new javax.swing.JPanel();
    AllUser = new javax.swing.JLabel();
    FindAdmin = new javax.swing.JLabel();
    FindCustomer = new javax.swing.JLabel();
    FindEmployee = new javax.swing.JLabel();
    jLabel5 = new javax.swing.JLabel();
    ValueSearch = new javax.swing.JTextField();

    AdminCart.setBackground(new java.awt.Color(220, 207, 189));
    AdminCart.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    AdminCart.setPreferredSize(new java.awt.Dimension(145, 100));

    IconAdmin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/gui/admin/icon/admin.png"))); // NOI18N

    TextAdmin.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    TextAdmin.setForeground(new java.awt.Color(255, 51, 51));
    TextAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    TextAdmin.setText("ADMIN");

    NewAdmin.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
    NewAdmin.setText("New User: 100");

    QuatityAdmin.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
    QuatityAdmin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    QuatityAdmin.setText("10000");

    javax.swing.GroupLayout AdminCartLayout = new javax.swing.GroupLayout(AdminCart);
    AdminCart.setLayout(AdminCartLayout);
    AdminCartLayout.setHorizontalGroup(
        AdminCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminCartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdminCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(QuatityAdmin, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextAdmin, javax.swing.GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
                .addContainerGap(23, Short.MAX_VALUE))
            .addGroup(AdminCartLayout.createSequentialGroup()
                .addComponent(NewAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 114,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)));
    AdminCartLayout.setVerticalGroup(
        AdminCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminCartLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdminCartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IconAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AdminCartLayout.createSequentialGroup()
                        .addComponent(TextAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QuatityAdmin)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NewAdmin)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    Customer.setBackground(new java.awt.Color(242, 149, 158));
    Customer.setBorder(javax.swing.BorderFactory.createEtchedBorder());
    Customer.setPreferredSize(new java.awt.Dimension(145, 100));

    IconCustomer
        .setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/gui/admin/icon/customer.png"))); // NOI18N

    TextCustomer.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    TextCustomer.setForeground(new java.awt.Color(255, 51, 51));
    TextCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    TextCustomer.setText("CUSTOMER");

    NewCustomer.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
    NewCustomer.setText("New User: 100");

    QuatityCustomer.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
    QuatityCustomer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    QuatityCustomer.setText("10000");

    javax.swing.GroupLayout CustomerLayout = new javax.swing.GroupLayout(Customer);
    Customer.setLayout(CustomerLayout);
    CustomerLayout.setHorizontalGroup(
        CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(QuatityCustomer, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextCustomer, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(CustomerLayout.createSequentialGroup()
                .addComponent(NewCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 114,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)));
    CustomerLayout.setVerticalGroup(
        CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CustomerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CustomerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IconCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(CustomerLayout.createSequentialGroup()
                        .addComponent(TextCustomer, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QuatityCustomer)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NewCustomer)
                .addContainerGap(10, Short.MAX_VALUE)));

    Employee.setBackground(new java.awt.Color(255, 204, 255));
    Employee.setBorder(javax.swing.BorderFactory.createEtchedBorder());

    IconEmployee
        .setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/gui/admin/icon/employee.png"))); // NOI18N

    TextEmployee.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    TextEmployee.setForeground(new java.awt.Color(255, 51, 51));
    TextEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    TextEmployee.setText("EMPLOYEE");

    NewEmployee.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
    NewEmployee.setText("New User: 100");

    QuatityEmployee.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
    QuatityEmployee.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    QuatityEmployee.setText("10000");

    javax.swing.GroupLayout EmployeeLayout = new javax.swing.GroupLayout(Employee);
    Employee.setLayout(EmployeeLayout);
    EmployeeLayout.setHorizontalGroup(
        EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, EmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(IconEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 52,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(QuatityEmployee, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TextEmployee, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(EmployeeLayout.createSequentialGroup()
                .addComponent(NewEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 114,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE)));
    EmployeeLayout.setVerticalGroup(
        EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EmployeeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(EmployeeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(IconEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(EmployeeLayout.createSequentialGroup()
                        .addComponent(TextEmployee, javax.swing.GroupLayout.PREFERRED_SIZE, 28,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(QuatityEmployee)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NewEmployee)
                .addContainerGap(10, Short.MAX_VALUE)));

    Title.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
    Title.setText("UserList");

    ButtonBaned.setForeground(new java.awt.Color(255, 51, 0));
    ButtonBaned.setText("Banned");
    ButtonBaned.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          actionDelete();
        } catch (ClassNotFoundException | SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }

    });

    ButtonCreate.setText("Create");
    ButtonCreate.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        CreateUserForm createUserForm = new CreateUserForm();
        createUserForm.setVisible(true);
      }

    });

    table.setLayout(new java.awt.GridLayout(quantityUser, 1));
    scrollPane1.add(table);
    findAllUser();

    AllUser.setBackground(new java.awt.Color(204, 255, 204));
    AllUser.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
    AllUser.setForeground(new java.awt.Color(51, 0, 0));
    AllUser.setText("All User");
    AllUser.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          findAllUser();
        } catch (ClassNotFoundException | SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }

    });

    FindAdmin.setForeground(new java.awt.Color(255, 51, 51));
    FindAdmin.setText("Admin");
    FindAdmin.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          findAdmin();
        } catch (ClassNotFoundException | SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }

    });

    FindCustomer.setForeground(new java.awt.Color(0, 255, 102));
    FindCustomer.setText("Customer");
    FindCustomer.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          findCustomer();
        } catch (ClassNotFoundException | SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }

    });

    FindEmployee.setForeground(new java.awt.Color(51, 0, 204));
    FindEmployee.setText("Employee");
    FindEmployee.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        try {
          findEmployee();
        } catch (ClassNotFoundException | SQLException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        }
      }

    });

    jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/bookstore/gui/admin/icon/search.png"))); // NOI18N

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AdminCart, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Title, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                                    .addComponent(AllUser, javax.swing.GroupLayout.DEFAULT_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FindAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(FindCustomer)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 58, Short.MAX_VALUE)
                                .addComponent(Customer, javax.swing.GroupLayout.PREFERRED_SIZE, 149,
                                    javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(73, 73, 73))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(FindEmployee)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ValueSearch)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(Employee, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(36, 36, 36))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(ButtonCreate)
                                    .addGap(18, 18, 18)
                                    .addComponent(ButtonBaned)
                                    .addGap(1, 1, 1))))))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Employee, javax.swing.GroupLayout.Alignment.TRAILING,
                        javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(Customer, javax.swing.GroupLayout.Alignment.LEADING,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                        .addComponent(AdminCart, javax.swing.GroupLayout.Alignment.LEADING,
                            javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(ValueSearch, javax.swing.GroupLayout.PREFERRED_SIZE,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(Title, javax.swing.GroupLayout.PREFERRED_SIZE, 26,
                            javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ButtonCreate)
                        .addComponent(ButtonBaned))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AllUser)
                            .addComponent(FindAdmin)
                            .addComponent(FindCustomer)
                            .addComponent(FindEmployee)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                .addContainerGap()));
  }// </editor-fold>//GEN-END:initComponents

  public void actionCard() throws ClassNotFoundException, SQLException {
    int QuantityAdminNew = 0;
    int QuantityCustomerNew = 0;
    int QuantityEmployeeNew = 0;
    quantityAdmin = 0;
    quantityCustomer = 0;
    quantityEmployee = 0;
    quantityUser = 0;
    List<UserModel> userList = UserBUS.getInstance().getAllModels();
    for (UserModel user : userList) {
      Timestamp now = new Timestamp(System.currentTimeMillis());
      long currentTime = now.getTime();
      long getTime = (long) user.getCreatedAt().getTime();
      long timeDiff = currentTime - getTime;
      long daysDiff = TimeUnit.MILLISECONDS.toDays(timeDiff);
      if (!user.getStatus().toString().equals("BANNED")) {
        quantityUser = quantityUser + 1;
        if (user.getRole().toString().equals("ADMIN")) {
          quantityAdmin = quantityAdmin + 1;
          if (daysDiff <= 9) {
            QuantityAdminNew = QuantityAdminNew + 1;
          }
        } else if (user.getRole().toString().equals("EMPLOYEE")) {
          quantityEmployee = quantityEmployee + 1;
          if (daysDiff <= 9) {
            QuantityEmployeeNew = QuantityEmployeeNew + 1;
          }
        } else {
          quantityCustomer = quantityCustomer + 1;
          if (daysDiff <= 9) {
            QuantityCustomerNew = QuantityCustomerNew + 1;
          }
        }
      }
    }
    NewAdmin.setText("New User: " + QuantityAdminNew);
    NewCustomer.setText("New User: " + QuantityCustomerNew);
    NewEmployee.setText("New User: " + QuantityEmployeeNew);

    QuatityAdmin.setText("" + quantityAdmin);
    QuatityCustomer.setText("" + quantityCustomer);
    QuatityEmployee.setText("" + quantityEmployee);

  }

  public void actionDelete() throws ClassNotFoundException, SQLException {
    for (Component component : table.getComponents()) {
      JPanel subPanel = (JPanel) component;
      for (Component subComponent : subPanel.getComponents()) {
        if (subComponent instanceof JCheckBox && ((JCheckBox) subComponent).isSelected()) {
          Component[] components = subPanel.getComponents();
          boolean foundTextField = false;
          for (Component c : components) {
            if (c instanceof JTextField && !foundTextField) {
              foundTextField = true;
              int id = Integer.parseInt(((JTextField) c).getText());
              int deletedRows = UserBUS.getInstance().deleteModel(id);
              if (deletedRows == 1) {
                JOptionPane.showMessageDialog(null, " Khóa Tài Khoản Thành Công !");
              }
            }
          }
        }
      }
    }
    table.revalidate();
    table.repaint();
  }

  public void findAdmin() throws ClassNotFoundException, SQLException {

    table.removeAll();
    actionCard();
    table.setLayout(new GridLayout(quantityAdmin, 1));
    int serial = 0;
    List<UserModel> userList = UserBUS.getInstance().getAllModels();
    for (UserModel user : userList) {
      if (user.getRole().toString().equals("ADMIN") && !user.getStatus().toString().equals("BANNED")) {
        UserForm userForm = new UserForm(serial, user.getId(), user.getUsername(), user.getPassword(), user.getStatus(),
            user.getName(), user.getEmail(), user.getPhone(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
        table.add(userForm);
        serial++;
      }
    }
    table.revalidate();
    table.repaint();
  }

  public void findCustomer() throws ClassNotFoundException, SQLException {
    table.removeAll();
    actionCard();
    table.setLayout(new GridLayout(quantityCustomer, 1));
    int serial = 0;
    List<UserModel> userList = UserBUS.getInstance().getAllModels();
    for (UserModel user : userList) {
      if (user.getRole().toString().equals("CUSTOMER") && !user.getStatus().toString().equals("BANNED")) {
        UserForm userForm = new UserForm(serial, user.getId(), user.getUsername(), user.getPassword(), user.getStatus(),
            user.getName(), user.getEmail(), user.getPhone(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
        table.add(userForm);
        serial++;
      }
    }
    table.revalidate();
    table.repaint();
  }

  public void findEmployee() throws ClassNotFoundException, SQLException {
    table.removeAll();
    actionCard();
    table.setLayout(new GridLayout(quantityEmployee, 1));
    int serial = 0;
    List<UserModel> userList = UserBUS.getInstance().getAllModels();
    for (UserModel user : userList) {
      if (user.getRole().toString().equals("EMPLOYEE") && !user.getStatus().toString().equals("BANNED")) {
        UserForm userForm = new UserForm(serial, user.getId(), user.getUsername(), user.getPassword(), user.getStatus(),
            user.getName(), user.getEmail(), user.getPhone(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
        table.add(userForm);
        serial++;
      }
    }
    table.revalidate();
    table.repaint();
  }

  public void findAllUser() throws ClassNotFoundException, SQLException {
    table.removeAll();
    actionCard();
    table.setLayout(new GridLayout(quantityUser, 1));
    List<UserModel> userList = UserBUS.getInstance().getAllModels();
    int serial = 0;
    for (UserModel user : userList) {
      if (!user.getStatus().toString().equals("BANNED")) {
        UserForm userForm = new UserForm(serial, user.getId(), user.getUsername(), user.getPassword(), user.getStatus(),
            user.getName(), user.getEmail(), user.getPhone(), user.getRole(), user.getCreatedAt(), user.getUpdatedAt());
        table.add(userForm);
        serial++;
      }
    }
    table.revalidate();
    table.repaint();
  }

  private void ButtonBanedActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ButtonBanedActionPerformed
    // TODO add your handling code here:
  }// GEN-LAST:event_ButtonBanedActionPerformed

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel AdminCart;
  private javax.swing.JLabel AllUser;
  private javax.swing.JButton ButtonBaned;
  private javax.swing.JButton ButtonCreate;
  private javax.swing.JPanel Customer;
  private javax.swing.JPanel Employee;
  private javax.swing.JLabel FindAdmin;
  private javax.swing.JLabel FindCustomer;
  private javax.swing.JLabel FindEmployee;
  private javax.swing.JLabel IconAdmin;
  private javax.swing.JLabel IconCustomer;
  private javax.swing.JLabel IconEmployee;
  private javax.swing.JLabel NewAdmin;
  private javax.swing.JLabel NewCustomer;
  private javax.swing.JLabel NewEmployee;
  private javax.swing.JLabel QuatityAdmin;
  private javax.swing.JLabel QuatityCustomer;
  private javax.swing.JLabel QuatityEmployee;
  private javax.swing.JLabel TextAdmin;
  private javax.swing.JLabel TextCustomer;
  private javax.swing.JLabel TextEmployee;
  private javax.swing.JLabel Title;
  private javax.swing.JTextField ValueSearch;
  private javax.swing.JLabel jLabel5;
  private java.awt.ScrollPane scrollPane1;
  private javax.swing.JPanel table;
  private int quantityAdmin;
  private int quantityCustomer;
  private int quantityEmployee;
  private int quantityUser;

  // End of variables declaration//GEN-END:variables
  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }
}
