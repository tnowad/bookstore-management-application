package com.bookstore.gui.form.salesman.view.Account;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.CurrentUserBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.Theme.ThemeFont;
import com.bookstore.gui.component.button.Button;
import com.bookstore.gui.component.button.Label;
import com.bookstore.models.AddressModel;
import com.bookstore.models.CurrentUserModel;
import com.bookstore.models.UserModel;

public class ProfileSettings extends JPanel {

        private JPanel getInformationPanel;
        private JPasswordField confirmPasswordFld;
        private JTextField getCityTxtFld;
        private JTextField getEmailInEditTxtFld;
        private JTextField getNameInEditTxtFld;
        private JTextField getPhoneTxtFld;
        private JTextField getStateTxtFld;
        private JTextField getStreetTxtFld;
        private JTextField getUserNameTxtFld;
        private JTextField getZipTxtFld;
        private Label editLabel;
        private Label editLabel1;
        private Label editnameLabel;
        private Label usernameLabel;
        private Label zipLabel;
        private Label nameLabel;
        private Label emailLabel;
        private Label phoneNumberLabel;
        private Label streetLabel;
        private Label cityLabel;
        private Label stateLabel;
        private Button resetUserInformation;
        private Button updateUserInformationBtn;

        UserBUS userBus = UserBUS.getInstance();
        CurrentUserBUS currentUserBus = CurrentUserBUS.getInstance();
        AddressBUS addressBus = AddressBUS.getInstance();
        List<CurrentUserModel> currentUser = currentUserBus.getAllModels();
        int idCurrent = currentUser.get(0).getCurrentUserId();
        UserModel userModel = userBus.getModelById(idCurrent);
        AddressModel addressModel = addressBus.getModelById(idCurrent);

        public ProfileSettings() {
                initComponents();
                updateInformation();
        }

        private void updateInformation() {

            
                getCityTxtFld.setText(addressModel.getCity());
                getStreetTxtFld.setText(addressModel.getStreet());
                getZipTxtFld.setText(addressModel.getZip());
                getStateTxtFld.setText(addressModel.getState());
                getUserNameTxtFld.setText(userModel.getUsername());
                getNameInEditTxtFld.setText(userModel.getName());
                getEmailInEditTxtFld.setText(userModel.getEmail());
                getPhoneTxtFld.setText(userModel.getPhone());
        }

        private void initComponents() {

                editLabel = new Label("Edit Your Personal Settings");
                editLabel.setFont(new ThemeFont().getMediumFont());
                getInformationPanel = new JPanel();
                nameLabel = new Label("Name");
                getNameInEditTxtFld = new JTextField();
                emailLabel = new Label("Email");
                getEmailInEditTxtFld = new JTextField();
                phoneNumberLabel = new Label("Phone number");
                getPhoneTxtFld = new JTextField();
                usernameLabel = new Label("Username");
                getUserNameTxtFld = new JTextField();
                updateUserInformationBtn = new Button("Update");
                resetUserInformation = new Button("Reset");
                streetLabel = new Label("Street");
                getCityTxtFld = new JTextField();
                cityLabel = new Label("City");
                getStreetTxtFld = new JTextField();
                stateLabel = new Label("State");
                getZipTxtFld = new JTextField();
                editLabel1 = new Label("Confirm Password");
                editnameLabel = new Label("Update Address Informations");
                editnameLabel.setFont(new ThemeFont().getMediumFont());
                getStateTxtFld = new JTextField();
                zipLabel = new Label("Zip");
                confirmPasswordFld = new JPasswordField();

                setPreferredSize(new java.awt.Dimension(821, 548));
                getPhoneTxtFld.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                getPhoneTxtFldActionPerformed(evt);
                        }
                });

                getUserNameTxtFld.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                getUserNameTxtFldActionPerformed(evt);
                        }
                });

                GroupLayout getInformationPanelLayout = new GroupLayout(getInformationPanel);
                getInformationPanel.setLayout(getInformationPanelLayout);
                getInformationPanelLayout.setHorizontalGroup(
                                getInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(getInformationPanelLayout.createSequentialGroup()
                                                                .addContainerGap()
                                                                .addGroup(
                                                                                getInformationPanelLayout
                                                                                                .createParallelGroup(
                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                false)
                                                                                                .addComponent(emailLabel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                50,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(nameLabel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                50,
                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                .addComponent(usernameLabel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(phoneNumberLabel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                104,
                                                                                                                Short.MAX_VALUE))
                                                                .addGap(18, 18, 18)
                                                                .addGroup(getInformationPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(getNameInEditTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                300,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(getEmailInEditTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                300,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(getUserNameTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                300,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(getPhoneTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                150,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addContainerGap(106, Short.MAX_VALUE)));
                getInformationPanelLayout.setVerticalGroup(
                                getInformationPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(getInformationPanelLayout.createSequentialGroup()
                                                                .addGap(17, 17, 17)
                                                                .addGroup(getInformationPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(nameLabel)
                                                                                .addComponent(getNameInEditTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(getInformationPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addComponent(usernameLabel)
                                                                                .addComponent(getUserNameTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
                                                                                14, Short.MAX_VALUE)
                                                                .addGroup(getInformationPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(getEmailInEditTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(emailLabel))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(getInformationPanelLayout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(getPhoneTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(phoneNumberLabel))
                                                                .addGap(17, 17, 17)));

                updateUserInformationBtn.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                updateUserInformationBtnActionPerformed(evt);
                        }
                });

                resetUserInformation.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                resetUserInformationActionPerformed(evt);
                        }
                });

                getZipTxtFld.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                getZipTxtFldActionPerformed(evt);
                        }
                });

                getStateTxtFld.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                                getStateTxtFldActionPerformed(evt);
                        }
                });

                GroupLayout layout = new GroupLayout(this);
                this.setLayout(layout);
                layout.setHorizontalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                                .addContainerGap(147, Short.MAX_VALUE)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createParallelGroup(
                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                false)
                                                                                                .addComponent(cityLabel,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                Short.MAX_VALUE)
                                                                                                .addComponent(streetLabel,
                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                140,
                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                .addComponent(editnameLabel)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGroup(
                                                                                                                layout.createParallelGroup(
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                false)
                                                                                                                                .addComponent(stateLabel,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                                Short.MAX_VALUE)
                                                                                                                                .addComponent(zipLabel,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                39,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addGroup(layout
                                                                                                                .createParallelGroup(
                                                                                                                                GroupLayout.Alignment.TRAILING,
                                                                                                                                false)
                                                                                                                .addComponent(getCityTxtFld,
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(getZipTxtFld,
                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                .addComponent(getStreetTxtFld,
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                430,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(getStateTxtFld,
                                                                                                                                GroupLayout.Alignment.LEADING,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                430,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addGroup(layout
                                                                                                                                .createParallelGroup(
                                                                                                                                                GroupLayout.Alignment.LEADING)
                                                                                                                                .addComponent(editLabel1)
                                                                                                                                .addComponent(confirmPasswordFld,
                                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                                200,
                                                                                                                                                GroupLayout.PREFERRED_SIZE))))
                                                                                .addComponent(editLabel)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addGroup(layout.createParallelGroup(
                                                                                                                GroupLayout.Alignment.TRAILING)
                                                                                                                .addComponent(getInformationPanel,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addComponent(updateUserInformationBtn))
                                                                                                .addPreferredGap(
                                                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                                                .addComponent(resetUserInformation)))
                                                                .addGap(53, 53, 53)));
                layout.setVerticalGroup(
                                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                                .addGap(21, 21, 21)
                                                                .addComponent(editLabel, GroupLayout.PREFERRED_SIZE, 32,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(getInformationPanel,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(24, 24, 24)
                                                                .addComponent(editnameLabel, GroupLayout.PREFERRED_SIZE,
                                                                                32,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(streetLabel)
                                                                                .addComponent(getStreetTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(cityLabel)
                                                                                .addComponent(getCityTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.LEADING)
                                                                                .addGroup(layout.createSequentialGroup()
                                                                                                .addComponent(stateLabel)
                                                                                                .addGap(8, 8, 8))
                                                                                .addGroup(GroupLayout.Alignment.TRAILING,
                                                                                                layout.createSequentialGroup()
                                                                                                                .addComponent(getStateTxtFld,
                                                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                                                .addPreferredGap(
                                                                                                                                LayoutStyle.ComponentPlacement.UNRELATED)))
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(getZipTxtFld,
                                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                                GroupLayout.PREFERRED_SIZE)
                                                                                .addComponent(zipLabel))
                                                                .addPreferredGap(
                                                                                LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(editLabel1)
                                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(confirmPasswordFld,
                                                                                GroupLayout.PREFERRED_SIZE,
                                                                                GroupLayout.DEFAULT_SIZE,
                                                                                GroupLayout.PREFERRED_SIZE)
                                                                .addGap(30, 30, 30)
                                                                .addGroup(layout.createParallelGroup(
                                                                                GroupLayout.Alignment.BASELINE)
                                                                                .addComponent(updateUserInformationBtn)
                                                                                .addComponent(resetUserInformation))
                                                                .addContainerGap(26, Short.MAX_VALUE)));
        }

        private void getPhoneTxtFldActionPerformed(ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void updateUserInformationBtnActionPerformed(ActionEvent evt) {
                String confirmPassword = new String(confirmPasswordFld.getPassword());
                if (confirmPassword.equals(userModel.getPassword())) {
                        userModel.setName(getNameInEditTxtFld.getText());
                        userModel.setUsername(getUserNameTxtFld.getText());
                        userModel.setEmail(getEmailInEditTxtFld.getText());
                        userModel.setPhone(getPhoneTxtFld.getText());
                        addressModel.setCity(getCityTxtFld.getText());
                        addressModel.setState(getStateTxtFld.getText());
                        addressModel.setStreet(getStreetTxtFld.getText());
                        addressModel.setZip(getZipTxtFld.getText());

                        userBus.updateModel(userModel);
                        addressBus.updateModel(addressModel);
                        confirmPasswordFld.setText("");
                        JOptionPane.showMessageDialog(null, "Confirm Password Successfully");
                } else {
                        JOptionPane.showMessageDialog(null, "Passwords do not match");
                }

        }

        private void getZipTxtFldActionPerformed(ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void getUserNameTxtFldActionPerformed(ActionEvent evt) {
                // TODO add your handling code here:
        }

        private void resetUserInformationActionPerformed(ActionEvent evt) {
                confirmPasswordFld.setText("");
                getCityTxtFld.setText("");
                getEmailInEditTxtFld.setText("");
                getNameInEditTxtFld.setText("");
                getPhoneTxtFld.setText("");
                getStateTxtFld.setText("");
                getStreetTxtFld.setText("");
                getUserNameTxtFld.setText("");
                getZipTxtFld.setText("");
        }

        private void getStateTxtFldActionPerformed(ActionEvent evt) {
                // TODO add your handling code here:
        }

}
