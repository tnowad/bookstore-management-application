package com.bookstore.gui.form.salesman.view.Account;

import javax.swing.*;

import com.bookstore.gui.Theme.ThemeColor;
import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;

public class ProfileSettings extends JPanel {

    public ProfileSettings() {
        initComponents();
        // setBackground();
    }

    private void setBackground() {
        confirmPasswordFld.setBackground(new ThemeColor().getBackground());
        getCityTxtFld.setBackground(new ThemeColor().getBackground());
        getEmailInEditTxtFld.setBackground(new ThemeColor().getBackground());
        getInformationPanel.setBackground(new ThemeColor().getBackground());
        getNameInEditTxtFld.setBackground(new ThemeColor().getBackground());
        getPhoneTxtFld.setBackground(new ThemeColor().getBackground());
        getStateTxtFld.setBackground(new ThemeColor().getBackground());
        getStreetTxtFld.setBackground(new ThemeColor().getBackground());
        getUserNameTxtFld.setBackground(new ThemeColor().getBackground());
        getZipTxtFld.setBackground(new ThemeColor().getBackground());
        jLabel1.setBackground(new ThemeColor().getBackground());
        jLabel11.setBackground(new ThemeColor().getBackground());
        jLabel12.setBackground(new ThemeColor().getBackground());
        jLabel14.setBackground(new ThemeColor().getBackground());
        jLabel15.setBackground(new ThemeColor().getBackground());
        jLabel2.setBackground(new ThemeColor().getBackground());
        jLabel3.setBackground(new ThemeColor().getBackground());
        jLabel4.setBackground(new ThemeColor().getBackground());
        jLabel7.setBackground(new ThemeColor().getBackground());
        jLabel8.setBackground(new ThemeColor().getBackground());
        jLabel9.setBackground(new ThemeColor().getBackground());
    }

    private void initComponents() {

        jLabel1 = new JLabel();
        getInformationPanel = new JPanel();
        jLabel2 = new JLabel();
        getEmailInEditTxtFld = new JTextField();
        jLabel3 = new JLabel();
        getNameInEditTxtFld = new JTextField();
        jLabel4 = new JLabel();
        getPhoneTxtFld = new JTextField();
        getUserNameTxtFld = new JTextField();
        jLabel14 = new JLabel();
        updateUserInformationBtn = new Button("Update");
        resetUserInformation = new Button("Reset");
        jLabel7 = new JLabel();
        getCityTxtFld = new JTextField();
        jLabel8 = new JLabel();
        getStreetTxtFld = new JTextField();
        jLabel9 = new JLabel();
        getZipTxtFld = new JTextField();
        jLabel11 = new JLabel();
        jLabel12 = new JLabel();
        getStateTxtFld = new JTextField();
        jLabel15 = new JLabel();
        confirmPasswordFld = new JPasswordField();

        setPreferredSize(new java.awt.Dimension(821, 548));

        jLabel1.setFont(new ThemeFont().getSmallFont());
        jLabel1.setText("Edit Your Personal Settings");

        jLabel2.setFont(new ThemeFont().getSmallFont());
        jLabel2.setText("Name");

        jLabel3.setFont(new ThemeFont().getSmallFont());
        jLabel3.setText("Email");

        jLabel4.setFont(new ThemeFont().getSmallFont());
        jLabel4.setText("Phone Number");

        getPhoneTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getPhoneTxtFldActionPerformed(evt);
            }
        });

        getUserNameTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getUserNameTxtFldActionPerformed(evt);
            }
        });

        jLabel14.setFont(new ThemeFont().getSmallFont());
        jLabel14.setText("Username");

        GroupLayout getInformationPanelLayout = new GroupLayout(getInformationPanel);
        getInformationPanel.setLayout(getInformationPanelLayout);
        getInformationPanelLayout.setHorizontalGroup(
                getInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(getInformationPanelLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(
                                        getInformationPanelLayout
                                                .createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 50,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 50,
                                                        GroupLayout.PREFERRED_SIZE)
                                                .addComponent(jLabel14, GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel4, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(getInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(getNameInEditTxtFld, GroupLayout.PREFERRED_SIZE, 300,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(getEmailInEditTxtFld, GroupLayout.PREFERRED_SIZE, 300,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(getUserNameTxtFld, GroupLayout.PREFERRED_SIZE, 300,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addComponent(getPhoneTxtFld, GroupLayout.PREFERRED_SIZE, 150,
                                                GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(106, Short.MAX_VALUE)));
        getInformationPanelLayout.setVerticalGroup(
                getInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(getInformationPanelLayout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(getInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(getNameInEditTxtFld, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(getInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel14)
                                        .addComponent(getUserNameTxtFld, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                                .addGroup(getInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(getEmailInEditTxtFld, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel3))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(getInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(getPhoneTxtFld, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4))
                                .addGap(17, 17, 17)));

        updateUserInformationBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserInformationBtnActionPerformed(evt);
            }
        });

        resetUserInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetUserInformationActionPerformed(evt);
            }
        });

        jLabel7.setFont(new ThemeFont().getSmallFont());
        jLabel7.setText("Street");

        jLabel8.setFont(new ThemeFont().getSmallFont());
        jLabel8.setText("City");

        jLabel9.setFont(new ThemeFont().getSmallFont());
        jLabel9.setText("State");

        getZipTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getZipTxtFldActionPerformed(evt);
            }
        });

        jLabel11.setFont(new ThemeFont().getSmallFont());
        jLabel11.setText("Confirm Password");

        jLabel12.setFont(new ThemeFont().getSmallFont());
        jLabel12.setText("Update Address Informations");

        getStateTxtFld.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getStateTxtFldActionPerformed(evt);
            }
        });

        jLabel15.setFont(new ThemeFont().getSmallFont());
        jLabel15.setText("Zip");

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(147, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel8, GroupLayout.DEFAULT_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 140,
                                                        GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jLabel12)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(
                                                        layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                                .addComponent(jLabel9, GroupLayout.DEFAULT_SIZE,
                                                                        GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(jLabel15, GroupLayout.PREFERRED_SIZE, 39,
                                                                        GroupLayout.PREFERRED_SIZE))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout
                                                        .createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(getCityTxtFld, GroupLayout.Alignment.LEADING)
                                                        .addComponent(getZipTxtFld, GroupLayout.Alignment.LEADING)
                                                        .addComponent(getStreetTxtFld, GroupLayout.Alignment.LEADING,
                                                                GroupLayout.PREFERRED_SIZE, 430,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(getStateTxtFld, GroupLayout.Alignment.LEADING,
                                                                GroupLayout.PREFERRED_SIZE, 430,
                                                                GroupLayout.PREFERRED_SIZE)
                                                        .addGroup(layout
                                                                .createParallelGroup(GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel11)
                                                                .addComponent(confirmPasswordFld,
                                                                        GroupLayout.PREFERRED_SIZE, 200,
                                                                        GroupLayout.PREFERRED_SIZE))))
                                        .addComponent(jLabel1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                                        .addComponent(getInformationPanel, GroupLayout.PREFERRED_SIZE,
                                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(updateUserInformationBtn))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(resetUserInformation)))
                                .addGap(53, 53, 53)));
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(21, 21, 21)
                                .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 32,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(getInformationPanel, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(24, 24, 24)
                                .addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 32,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(getStreetTxtFld, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(getCityTxtFld, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(8, 8, 8))
                                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(getStateTxtFld, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(getZipTxtFld, GroupLayout.PREFERRED_SIZE,
                                                GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel15))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel11)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(confirmPasswordFld, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(30, 30, 30)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(updateUserInformationBtn)
                                        .addComponent(resetUserInformation))
                                .addContainerGap(26, Short.MAX_VALUE)));
    }// </editor-fold>

    private void getPhoneTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void updateUserInformationBtnActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void getZipTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void getUserNameTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void resetUserInformationActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    private void getStateTxtFldActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }

    // Variables declaration - do not modify
    private JPasswordField confirmPasswordFld;
    private JTextField getCityTxtFld;
    private JTextField getEmailInEditTxtFld;
    private JPanel getInformationPanel;
    private JTextField getNameInEditTxtFld;
    private JTextField getPhoneTxtFld;
    private JTextField getStateTxtFld;
    private JTextField getStreetTxtFld;
    private JTextField getUserNameTxtFld;
    private JTextField getZipTxtFld;
    private JLabel jLabel1;
    private JLabel jLabel11;
    private JLabel jLabel12;
    private JLabel jLabel14;
    private JLabel jLabel15;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel7;
    private JLabel jLabel8;
    private JLabel jLabel9;
    private Button resetUserInformation;
    private Button updateUserInformationBtn;

}
