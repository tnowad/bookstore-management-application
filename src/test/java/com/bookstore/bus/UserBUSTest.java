package com.bookstore.bus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.Role;
import com.bookstore.model.UserModel.Status;

import java.sql.SQLException;
import java.util.List;

public class UserBUSTest {

  private UserBUS userBUS;

  @BeforeEach
  public void setUp() throws ClassNotFoundException, SQLException {
    userBUS = new UserBUS();
  }

  @Test
  public void testGetAllModels() {
    List<UserModel> models = userBUS.getAllModels();
    Assertions.assertNotNull(models);
    Assertions.assertEquals(9, models.size());
  }

  @Test
  public void testGetModelById() throws SQLException, ClassNotFoundException {
    int id = 2;
    UserModel model = userBUS.getModelById(id);
    Assertions.assertNotNull(model);
    Assertions.assertEquals(id, model.getId());
  }

  @Test
  public void testAddModel() throws SQLException, ClassNotFoundException {
    UserModel model = new UserModel();
    model.setUsername("testuser");
    model.setName("Test User");
    model.setPassword("password");
    model.setEmail("testuser@example.com");
    model.setPhone("1234567890");
    model.setRole(Role.admin);
    model.setStatus(Status.active);

    int id = userBUS.addModel(model);
    Assertions.assertTrue(id > 0);
  }

  @Test
  public void testUpdateModel() throws SQLException, ClassNotFoundException {
    int id = 2;
    UserModel model = userBUS.getModelById(id);
    model.setName("12345");

    int result = userBUS.updateModel(model);
    Assertions.assertEquals(1, result);

    UserModel updatedModel = userBUS.getModelById(id);
    Assertions.assertEquals(model.getName(), updatedModel.getName());
  }

  @Test
  public void testDeleteModel() throws SQLException, ClassNotFoundException {
    int result = userBUS.deleteModel(9);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void testSearchModel() throws SQLException, ClassNotFoundException {
    String value = "Lê Thị Anh";
    String columns = "name";

    List<UserModel> models = userBUS.searchModel(value, columns);
    Assertions.assertNotNull(models);
    Assertions.assertFalse(models.isEmpty());
    Assertions.assertEquals(1, models.size());
  }

  @Test
  public void testAddModelSuccess() throws SQLException, ClassNotFoundException {
    UserModel userModel = new UserModel();
    userModel.setUsername("Testing");
    userModel.setName("John Doe");
    userModel.setPassword("123456");
    userModel.setEmail("email@example.com");
    int id = userBUS.addModel(userModel);
    Assertions.assertEquals(id, userModel.getId());
  }

  @Test
  public void testAddModelWithEmptyUsername() {
    UserModel userModel = new UserModel();
    userModel.setUsername("");

    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> userBUS.addModel(userModel));

    Assertions.assertEquals("Username, name and password cannot be empty. Please check the input and try again.",
        exception.getMessage());
  }

  @Test
  public void testAddModelWithEmptyName() {
    UserModel userModel = new UserModel();
    userModel.setName("");

    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> userBUS.addModel(userModel));

    Assertions.assertEquals("Username, name and password cannot be empty. Please check the input and try again.",
        exception.getMessage());
  }

  @Test
  public void testAddModelWithEmptyPassword() {
    UserModel userModel = new UserModel();
    userModel.setPassword("");

    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> userBUS.addModel(userModel));

    Assertions.assertEquals("Username, name and password cannot be empty. Please check the input and try again.",
        exception.getMessage());
  }

  @Test
  public void testAddModelWithNoPhoneOrEmail() {
    UserModel userModel = new UserModel();
    userModel.setUsername("Testing");
    userModel.setName("John Doe");
    userModel.setPassword("123456");
    userModel.setPhone("");
    userModel.setEmail("");

    IllegalArgumentException exception = Assertions.assertThrows(IllegalArgumentException.class,
        () -> userBUS.addModel(userModel));

    Assertions.assertEquals("At least one of 'phone' or 'email' is required.", exception.getMessage());
  }

  // Uncomment this test if you want to validate email addresses
  /*
   * @Test
   * public void testAddModelWithInvalidEmail() {
   * userModel.setEmail("invalid-email");
   * 
   * IllegalArgumentException exception =
   * Assertions.assertThrows(IllegalArgumentException.class,
   * () -> userBUS.addModel(userModel));
   * 
   * Assertions.assertEquals("Invalid email address.", exception.getMessage());
   * }
   */

}
