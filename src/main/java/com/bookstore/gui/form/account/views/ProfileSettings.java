package com.bookstore.gui.form.account.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.models.AddressModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;

public class ProfileSettings extends JPanel {
    private JLabel cityLabel;
    private JTextField cityTextField;
    private JLabel confirmLabel;
    private JPasswordField confirmPasswordField;
    private JLabel editLabel;
    private JLabel emailLabel;
    private JTextField emailTextField;
    private JPanel groupButtonPanel;
    private JPanel groupEditAddressPanel;
    private JPanel groupEditPanel;
    private JPanel groupEditProfilePanel;
    private JPanel groupUpdatePanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JButton resetButton;
    private JLabel stateLabel;
    private JTextField stateTextField;
    private JLabel streetLabel;
    private JTextField streetTextField;
    private JButton updateButton;
    private JLabel updateLabel;
    private JLabel usernameLabel;
    private JTextField usernameTextField;
    private JLabel zipLabel;
    private JTextField zipTextField;

    UserBUS userBus = UserBUS.getInstance();
    AddressBUS addressBus = AddressBUS.getInstance();
    UserModel currentUser = Authentication.getCurrentUser();
    AddressModel addressModel = addressBus.getModelById(currentUser.getId());

    public ProfileSettings() {
        initComponents();
        updateInformation();
        handleEvent();
    }

    private void handleEvent() {

        updateButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        updateUserInformationButtonActionPerformed(evt);
                    }
                });
        resetButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        resetUserInformationButtonActionPerformed(evt);
                    }
                });
    }

    protected void updateUserInformationButtonActionPerformed(ActionEvent evt) {
        String confirmPassword = new String(confirmPasswordField.getPassword());
        if (confirmPassword.equals(currentUser.getPassword())) {
            currentUser.setName(nameTextField.getText());
            currentUser.setUsername(usernameTextField.getText());
            currentUser.setEmail(emailTextField.getText());
            currentUser.setPhone(phoneTextField.getText());
            addressModel.setCity(cityTextField.getText());
            addressModel.setState(stateTextField.getText());
            addressModel.setStreet(stateTextField.getText());
            addressModel.setZip(zipTextField.getText());

            userBus.updateModel(currentUser);
            addressBus.updateModel(addressModel);
            confirmPasswordField.setText("");
            JOptionPane.showMessageDialog(null, "Confirm Password Successfully");
        } else {
            JOptionPane.showMessageDialog(null, "Passwords do not match");
        }
    }

    protected void resetUserInformationButtonActionPerformed(ActionEvent evt) {
        confirmPasswordField.setText("");
        cityTextField.setText("");
        emailTextField.setText("");
        nameTextField.setText("");
        phoneTextField.setText("");
        stateTextField.setText("");
        stateTextField.setText("");
        usernameTextField.setText("");
        zipTextField.setText("");
    }

    private void updateInformation() {
        cityTextField.setText(addressModel.getCity());
        streetTextField.setText(addressModel.getStreet());
        zipTextField.setText(addressModel.getZip());
        stateTextField.setText(addressModel.getState());
        usernameTextField.setText(currentUser.getUsername());
        nameTextField.setText(currentUser.getName());
        emailTextField.setText(currentUser.getEmail());
        phoneTextField.setText(currentUser.getPhone());
    }

    private void initComponents() {

        groupEditPanel = new JPanel();
        editLabel = new JLabel();
        groupEditProfilePanel = new JPanel();
        nameLabel = new JLabel();
        nameTextField = new JTextField();
        usernameLabel = new JLabel();
        usernameTextField = new JTextField();
        emailLabel = new JLabel();
        emailTextField = new JTextField();
        phoneLabel = new JLabel();
        phoneTextField = new JTextField();
        groupUpdatePanel = new JPanel();
        updateLabel = new JLabel();
        groupEditAddressPanel = new JPanel();
        streetLabel = new JLabel();
        streetTextField = new JTextField();
        cityLabel = new JLabel();
        cityTextField = new JTextField();
        stateLabel = new JLabel();
        stateTextField = new JTextField();
        zipLabel = new JLabel();
        zipTextField = new JTextField();
        confirmLabel = new JLabel();
        confirmPasswordField = new JPasswordField();
        groupButtonPanel = new JPanel();
        updateButton = new JButton();
        resetButton = new JButton();

        setPreferredSize(new Dimension(550, 500));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        groupEditPanel.setLayout(new BorderLayout());

        editLabel.setHorizontalAlignment(SwingConstants.CENTER);
        editLabel.setText("Edit personal settings");
        groupEditPanel.add(editLabel, BorderLayout.PAGE_START);

        groupEditProfilePanel.setLayout(new GridLayout(4, 2));

        nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        nameLabel.setText("Name");
        groupEditProfilePanel.add(nameLabel);

        nameTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                nameTextFieldActionPerformed(evt);
            }
        });
        groupEditProfilePanel.add(nameTextField);

        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setText("Username");
        groupEditProfilePanel.add(usernameLabel);
        groupEditProfilePanel.add(usernameTextField);

        emailLabel.setHorizontalAlignment(SwingConstants.CENTER);
        emailLabel.setText("Email");
        groupEditProfilePanel.add(emailLabel);

        emailTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });
        groupEditProfilePanel.add(emailTextField);

        phoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
        phoneLabel.setText("Phone");
        groupEditProfilePanel.add(phoneLabel);

        phoneTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                phoneTextFieldActionPerformed(evt);
            }
        });
        groupEditProfilePanel.add(phoneTextField);

        groupEditPanel.add(groupEditProfilePanel, BorderLayout.CENTER);

        add(groupEditPanel);

        groupUpdatePanel.setLayout(new BorderLayout());

        updateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        updateLabel.setText("Update address information");
        groupUpdatePanel.add(updateLabel, BorderLayout.PAGE_START);

        groupEditAddressPanel.setLayout(new GridLayout(5, 2));

        streetLabel.setHorizontalAlignment(SwingConstants.CENTER);
        streetLabel.setText("Street");
        groupEditAddressPanel.add(streetLabel);
        groupEditAddressPanel.add(streetTextField);

        cityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cityLabel.setText("City");
        groupEditAddressPanel.add(cityLabel);

        cityTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                cityTextFieldActionPerformed(evt);
            }
        });
        groupEditAddressPanel.add(cityTextField);

        stateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        stateLabel.setText("State");
        groupEditAddressPanel.add(stateLabel);
        groupEditAddressPanel.add(stateTextField);

        zipLabel.setHorizontalAlignment(SwingConstants.CENTER);
        zipLabel.setText("Zip");
        groupEditAddressPanel.add(zipLabel);

        zipTextField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                zipTextFieldActionPerformed(evt);
            }
        });
        groupEditAddressPanel.add(zipTextField);

        confirmLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmLabel.setText("Confirm password");
        groupEditAddressPanel.add(confirmLabel);
        groupEditAddressPanel.add(confirmPasswordField);

        groupUpdatePanel.add(groupEditAddressPanel, BorderLayout.CENTER);

        add(groupUpdatePanel);

        groupButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

        updateButton.setText("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updateButtonActionPerformed(evt);
            }
        });
        groupButtonPanel.add(updateButton);

        resetButton.setText("Reset");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });
        groupButtonPanel.add(resetButton);

        add(groupButtonPanel);
    }

    private void nameTextFieldActionPerformed(ActionEvent evt) {

    }

    private void emailTextFieldActionPerformed(ActionEvent evt) {

    }

    private void phoneTextFieldActionPerformed(ActionEvent evt) {

    }

    private void zipTextFieldActionPerformed(ActionEvent evt) {

    }

    private void updateButtonActionPerformed(ActionEvent evt) {

    }

    private void resetButtonActionPerformed(ActionEvent evt) {

    }

    private void cityTextFieldActionPerformed(ActionEvent evt) {

    }
}
