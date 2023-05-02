package com.bookstore.gui.forms.accounts;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.models.AddressModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.PasswordUtils;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProfileSettings extends JPanel {

  private Label cityLabel;
  private JTextField cityTextField;
  private Label confirmLabel;
  private JPasswordField confirmPasswordField;
  private Label editLabel;
  private Label emailLabel;
  private JTextField emailTextField;
  private JPanel groupButtonPanel;
  private JPanel groupEditAddressPanel;
  private JPanel groupEditPanel;
  private JPanel groupEditProfilePanel;
  private JPanel groupUpdatePanel;
  private Label nameLabel;
  private JTextField nameTextField;
  private Label phoneLabel;
  private JTextField phoneTextField;
  // private Button resetButton;
  private Label stateLabel;
  private JTextField stateTextField;
  private Label streetLabel;
  private JTextField streetTextField;
  private Button updateButton;
  private Label updateLabel;
  private Label usernameLabel;
  private JTextField usernameTextField;
  private Label zipLabel;
  private JTextField zipTextField;
  private static ProfileSettings instance;

  UserBUS userBus = UserBUS.getInstance();
  AddressBUS addressBus = AddressBUS.getInstance();
  UserModel currentUser = Authentication.getCurrentUser();
  AddressModel addressModel = addressBus.getModelById(currentUser.getId());

  private ProfileSettings() {
    initComponents();
    updateInformation();
    handleEvent();
  }

  public static ProfileSettings getInstance() {
    if (instance == null) {
      instance = new ProfileSettings();
    }
    return instance;
  }

  private void initComponents() {
    groupEditPanel = new JPanel();
    editLabel = new Label();
    groupEditProfilePanel = new JPanel();
    nameLabel = new Label();
    nameTextField = new JTextField();
    usernameLabel = new Label();
    usernameTextField = new JTextField();
    emailLabel = new Label();
    emailTextField = new JTextField();
    phoneLabel = new Label();
    phoneTextField = new JTextField();
    groupUpdatePanel = new JPanel();
    updateLabel = new Label();
    groupEditAddressPanel = new JPanel();
    streetLabel = new Label();
    streetTextField = new JTextField();
    cityLabel = new Label();
    cityTextField = new JTextField();
    stateLabel = new Label();
    stateTextField = new JTextField();
    zipLabel = new Label();
    zipTextField = new JTextField();
    confirmLabel = new Label();
    confirmPasswordField = new JPasswordField();
    groupButtonPanel = new JPanel();
    updateButton = new Button("Update");
    // resetButton = new Button("Reset");

    setPreferredSize(new Dimension(550, 500));
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    groupEditPanel.setLayout(new BorderLayout());

    editLabel.setText("Edit personal settings");
    groupEditPanel.add(editLabel, BorderLayout.PAGE_START);

    groupEditProfilePanel.setLayout(new GridLayout(4, 2));

    nameLabel.setText("Name");
    groupEditProfilePanel.add(nameLabel);

    groupEditProfilePanel.add(nameTextField);

    usernameLabel.setText("Username");
    groupEditProfilePanel.add(usernameLabel);
    groupEditProfilePanel.add(usernameTextField);

    emailLabel.setText("Email");
    groupEditProfilePanel.add(emailLabel);

    groupEditProfilePanel.add(emailTextField);

    phoneLabel.setText("Phone");
    groupEditProfilePanel.add(phoneLabel);

    groupEditProfilePanel.add(phoneTextField);

    groupEditPanel.add(groupEditProfilePanel, BorderLayout.CENTER);

    add(groupEditPanel);

    groupUpdatePanel.setLayout(new BorderLayout());

    updateLabel.setText("Update address information");
    groupUpdatePanel.add(updateLabel, BorderLayout.PAGE_START);

    groupEditAddressPanel.setLayout(new GridLayout(5, 2));

    streetLabel.setText("Street");
    groupEditAddressPanel.add(streetLabel);
    groupEditAddressPanel.add(streetTextField);

    cityLabel.setText("City");
    groupEditAddressPanel.add(cityLabel);

    groupEditAddressPanel.add(cityTextField);

    stateLabel.setText("State");
    groupEditAddressPanel.add(stateLabel);
    groupEditAddressPanel.add(stateTextField);

    zipLabel.setText("Zip");
    groupEditAddressPanel.add(zipLabel);

    groupEditAddressPanel.add(zipTextField);

    confirmLabel.setText("Confirm password");
    groupEditAddressPanel.add(confirmLabel);
    groupEditAddressPanel.add(confirmPasswordField);

    groupUpdatePanel.add(groupEditAddressPanel, BorderLayout.CENTER);

    add(groupUpdatePanel);

    groupButtonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

    groupButtonPanel.add(updateButton);

    // groupButtonPanel.add(resetButton);

    add(groupButtonPanel);
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

  private void handleEvent() {
    updateButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            updateUserInformationButtonActionPerformed(evt);
          }
        });
    // resetButton.addActionListener(
        // new ActionListener() {
        //   public void actionPerformed(ActionEvent evt) {
        //     resetUserInformationButtonActionPerformed(evt);
        //   }
        // });
  }

  protected void updateUserInformationButtonActionPerformed(ActionEvent evt) {
    String confirmPassword = new String(confirmPasswordField.getPassword());
    if (PasswordUtils.checkPassword(confirmPassword, currentUser.getPassword())) {
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

}
