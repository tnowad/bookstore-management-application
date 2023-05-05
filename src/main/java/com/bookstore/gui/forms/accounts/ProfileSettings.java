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
  private Label confirmPasswordLabel;
  private JPasswordField confirmPasswordField;
  private Label editLabel;
  private Label emailLabel;
  private JTextField emailTextField;
  private JPanel actionPanel;
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

    editLabel = new Label("Edit profile information");
    nameLabel = new Label("Name");
    nameTextField = new JTextField();

    usernameLabel = new Label("Username");
    usernameTextField = new JTextField();
    usernameTextField.setEditable(false);

    emailLabel = new Label("Email");
    emailTextField = new JTextField();

    phoneLabel = new Label("Phone");
    phoneTextField = new JTextField();

    updateLabel = new Label("Update address information");
    streetLabel = new Label("Street");
    streetTextField = new JTextField();

    cityLabel = new Label("City");
    cityTextField = new JTextField();

    stateLabel = new Label("State");
    stateTextField = new JTextField();

    zipLabel = new Label("Zip");
    zipTextField = new JTextField();

    confirmPasswordLabel = new Label("Confirm password");
    confirmPasswordField = new JPasswordField();
    JPanel wrapperPanel = new JPanel(new GridLayout(0, 2));
    wrapperPanel.setBackground(Color.WHITE);

    wrapperPanel.add(editLabel);
    wrapperPanel.add(new JLabel());
    wrapperPanel.add(nameLabel);
    wrapperPanel.add(nameTextField);
    wrapperPanel.add(usernameLabel);
    wrapperPanel.add(usernameTextField);
    wrapperPanel.add(emailLabel);
    wrapperPanel.add(emailTextField);
    wrapperPanel.add(phoneLabel);
    wrapperPanel.add(phoneTextField);
    wrapperPanel.add(updateLabel);
    wrapperPanel.add(new JLabel());
    wrapperPanel.add(streetLabel);
    wrapperPanel.add(streetTextField);
    wrapperPanel.add(cityLabel);
    wrapperPanel.add(cityTextField);
    wrapperPanel.add(stateLabel);
    wrapperPanel.add(stateTextField);
    wrapperPanel.add(zipLabel);
    wrapperPanel.add(zipTextField);
    wrapperPanel.add(confirmPasswordLabel);
    wrapperPanel.add(confirmPasswordField);

    JScrollPane scrollPane = new JScrollPane(
      new JPanel() {
        {
          setLayout(new BorderLayout());
          add(wrapperPanel, BorderLayout.NORTH);
        }
      },
      JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
      JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    );

    add(scrollPane, BorderLayout.CENTER);

    actionPanel = new JPanel();
    actionPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 30, 5));

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
  };
}
