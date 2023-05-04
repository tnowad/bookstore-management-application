
package com.bookstore.gui.components.salary;

import com.bookstore.bus.UserBUS;
import com.bookstore.models.EmployeeModel;


public class SalaryPanel extends javax.swing.JPanel {

    public SalaryPanel(int serial,EmployeeModel employee) {
        initComponents(serial,employee);
    }

    
    private void initComponents(int serial,EmployeeModel employee) {

        panel = new javax.swing.JPanel();
        setSerial = new javax.swing.JLabel();
        setId = new javax.swing.JLabel();
        setName = new javax.swing.JLabel();
        setSalary = new javax.swing.JLabel();
        setType = new javax.swing.JLabel();
        setDescription = new javax.swing.JLabel();

        setLayout(new java.awt.GridLayout(1, 4));

        panel.setLayout(new java.awt.GridLayout(1, 2));

        setSerial.setText(""+serial);
        panel.add(setSerial);

        setId.setText(""+employee.getUserId());
        panel.add(setId);

        add(panel);

        setName.setText(UserBUS.getInstance().getModelById(employee.getUserId()).getName());
        setName.setPreferredSize(new java.awt.Dimension(50, 16));
        add(setName);

        setSalary.setText(""+employee.getSalary());
        setSalary.setPreferredSize(new java.awt.Dimension(50, 16));
        add(setSalary);

        setType.setText(""+employee.getEmployeeType());
        setType.setPreferredSize(new java.awt.Dimension(50, 16));
        add(setType);
        

        setDescription.setText(employee.getContactInformation());
        setDescription.setPreferredSize(new java.awt.Dimension(50, 16));
        add(setDescription);
    }


    private javax.swing.JPanel panel;
    private javax.swing.JLabel setDescription;
    private javax.swing.JLabel setId;
    private javax.swing.JLabel setName;
    private javax.swing.JLabel setSalary;
    private javax.swing.JLabel setType;
    private javax.swing.JLabel setSerial;
}
