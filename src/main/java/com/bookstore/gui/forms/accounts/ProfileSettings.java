package com.bookstore.gui.forms.accounts;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.models.AddressModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
import com.bookstore.util.InputValidator;
import com.bookstore.util.PasswordUtils;
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ProfileSettings extends JPanel {

  private JLabel cityLabel;
  private JTextField cityTextField;
  private JLabel confirmPasswordLabel;
  private JPasswordField confirmPasswordField;
  private JLabel editLabel;
  private JLabel emailLabel;
  private JTextField emailTextField;
  private JPanel actionPanel;
  private JLabel nameLabel;
  private JTextField nameTextField;
  private JLabel phoneLabel;
  private JTextField phoneTextField;
  private JLabel stateLabel;
  private JTextField stateTextField;
  private JLabel streetLabel;
  private JTextField streetTextField;
  private Button updateButton;
  private JLabel updateLabel;
  private JLabel usernameLabel;
  private JTextField usernameTextField;
  private JLabel zipLabel;
  private JTextField zipTextField;
  private static ProfileSettings instance;

  UserBUS userBus = UserBUS.getInstance();
  AddressBUS addressBus = AddressBUS.getInstance();
  UserModel currentUser = Authentication.getCurrentUser();
  AddressModel addressModel = addressBus.getModelById(currentUser.getId());

  public ProfileSettings() {
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
    setLayout(new BorderLayout());
    setBackground(Color.WHITE);

    editLabel = new JLabel("Edit profile information");
    nameLabel = new JLabel("Name");
    nameTextField = new JTextField();

    usernameLabel = new JLabel("Username");
    usernameTextField = new JTextField();
    usernameTextField.setEditable(false);

    emailLabel = new JLabel("Email");
    emailTextField = new JTextField();

    phoneLabel = new JLabel("Phone");
    phoneTextField = new JTextField();

    updateLabel = new JLabel("Update address information");
    streetLabel = new JLabel("Street");
    streetTextField = new JTextField();

    cityLabel = new JLabel("City");
    cityTextField = new JTextField();

    stateLabel = new JLabel("State");
    stateTextField = new JTextField();

    zipLabel = new JLabel("Zip");
    zipTextField = new JTextField();

    confirmPasswordLabel = new JLabel("Confirm password");
    confirmPasswordField = new JPasswordField();
    JPanel formPanel = new JPanel(new GridLayout(0, 2));
    formPanel.setBackground(Color.WHITE);

    formPanel.add(editLabel);
    formPanel.add(new JLabel());
    formPanel.add(nameLabel);
    formPanel.add(nameTextField);
    formPanel.add(usernameLabel);
    formPanel.add(usernameTextField);
    formPanel.add(emailLabel);
    formPanel.add(emailTextField);
    formPanel.add(phoneLabel);
    formPanel.add(phoneTextField);
    formPanel.add(updateLabel);
    formPanel.add(new JLabel());
    formPanel.add(streetLabel);
    formPanel.add(streetTextField);
    formPanel.add(cityLabel);
    formPanel.add(cityTextField);
    formPanel.add(stateLabel);
    formPanel.add(stateTextField);
    formPanel.add(zipLabel);
    formPanel.add(zipTextField);
    formPanel.add(confirmPasswordLabel);
    formPanel.add(confirmPasswordField);

    JScrollPane scrollPane = new JScrollPane(
      new JPanel() {
        {
          setLayout(new BorderLayout());
          setBackground(Color.WHITE);
          add(formPanel, BorderLayout.NORTH);
        }
      },
      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );
    scrollPane.setBorder(null);
    scrollPane.setBackground(Color.WHITE);
    add(scrollPane, BorderLayout.CENTER);

    actionPanel = new JPanel();

    actionPanel.setBackground(Color.WHITE);

    actionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

    updateButton = new Button("Update");
    updateButton.addActionListener(updateUserInformationActionListener);
    actionPanel.add(updateButton);

    add(actionPanel, BorderLayout.PAGE_END);
  }

  private void updateInformation() {
    if (addressModel != null) {
      cityTextField.setText(addressModel.getCity());
      streetTextField.setText(addressModel.getStreet());
      zipTextField.setText(addressModel.getZip());
      stateTextField.setText(addressModel.getState());
    }
    usernameTextField.setText(currentUser.getUsername());
    nameTextField.setText(currentUser.getName());
    emailTextField.setText(currentUser.getEmail());
    phoneTextField.setText(currentUser.getPhone());
  }

  private void handleEvent() {}

  private ActionListener updateUserInformationActionListener = e -> {
    String confirmPassword = new String(confirmPasswordField.getPassword());
    if (
      PasswordUtils.checkPassword(confirmPassword, currentUser.getPassword())
    ) {
      try {
        currentUser.setName(
          InputValidator.validateName(nameTextField.getText())
        );
        currentUser.setUsername(
          InputValidator.validateUsername(usernameTextField.getText())
        );
        currentUser.setEmail(
          InputValidator.validateEmail(emailTextField.getText())
        );
        currentUser.setPhone(
          InputValidator.validatePhone(phoneTextField.getText())
        );
        addressModel.setCity(
          InputValidator.validateCity(cityTextField.getText())
        );
        addressModel.setState(
          InputValidator.validateState(stateTextField.getText())
        );
        addressModel.setStreet(
          InputValidator.validateStreet(streetTextField.getText())
        );
        addressModel.setZip(InputValidator.validateZip(zipTextField.getText()));

        userBus.updateModel(currentUser);
        addressBus.updateModel(addressModel);

        confirmPasswordField.setText("");
        JOptionPane.showMessageDialog(
          null,
          "Update information successfully",
          "Success",
          JOptionPane.INFORMATION_MESSAGE
        );
      } catch (Exception exception) {
        JOptionPane.showMessageDialog(
          null,
          exception.getMessage(),
          "Error",
          JOptionPane.ERROR_MESSAGE
        );
      }
    } else {
      JOptionPane.showMessageDialog(
        null,
        "Confirm password is not correct",
        "Error",
        JOptionPane.ERROR_MESSAGE
      );
    }
  };
}
