package com.bookstore.gui.forms.accounts;

import com.bookstore.bus.AddressBUS;
import com.bookstore.bus.UserBUS;
import com.bookstore.gui.components.buttons.Button;
import com.bookstore.models.AddressModel;
import com.bookstore.models.UserModel;
import com.bookstore.services.Authentication;
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
  // private Button resetButton;
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
          setBackground(Color.WHITE);
          add(wrapperPanel, BorderLayout.NORTH);
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
