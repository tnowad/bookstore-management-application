package com.bookstore.bus;

import com.bookstore.dao.DatabaseConnect;
import com.bookstore.model.UserModel;
import com.bookstore.model.UserModel.Role;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class UserBUSTest {
  private static UserBUS userBUS;

  @BeforeAll
  public static void setUp() throws SQLException, ClassNotFoundException {
    userBUS = new UserBUS();
  }

  @AfterAll
  public static void tearDown() {
    DatabaseConnect.closeConnection();
  }

  @Test
  public void testLogin() throws SQLException, ClassNotFoundException {
    UserModel userModel = userBUS.login("testuser", "password");
    Assertions.assertNotNull(userModel);
    Assertions.assertEquals(Role.ADMIN, userModel.getRole());
  }

  @Test
  public void testLoginWithInvalidCredentials() throws SQLException, ClassNotFoundException {
    UserBUS userBUS = new UserBUS();
    UserModel userModel = userBUS.login("testuser", "wrongpassword");
    Assertions.assertNull(userModel);
  }

  @Test
  public void testGetUserModel() {
    UserModel userModel = userBUS.getUserModel(31);
    Assertions.assertNotNull(userModel);
    Assertions.assertEquals("testuser", userModel.getUsername());
    Assertions.assertEquals("ACTIVE", userModel.getStatus().toString().toUpperCase());
  }

  @Test
  public void testGetUserList() {
    List<UserModel> userList = userBUS.getUserList();
    Assertions.assertNotNull(userList);
    Assertions.assertEquals(10, userList.size());
  }

  @Test
  public void testInsertModel() throws SQLException, ClassNotFoundException {
    UserModel userModel = new UserModel();
    userModel.setUsername("tester");
    userModel.setPassword("password");
    userModel.setName("Test User");
    userModel.setEmail("testuser@example.com");
    userModel.setPhone("1234567890");
    userModel.setRole(null);
    userModel.setStatus(null);
    int id = userBUS.insertModel(userModel);

    UserModel insertedUser = userBUS.getUserModel(id);
    Assertions.assertNotNull(insertedUser);
    Assertions.assertEquals(userModel.getUsername(), insertedUser.getUsername());
    Assertions.assertEquals(userModel.getName(), insertedUser.getName());
    Assertions.assertEquals(userModel.getEmail(), insertedUser.getEmail());
    Assertions.assertEquals(userModel.getPhone(), insertedUser.getPhone());
    Assertions.assertEquals(userModel.getRole(), insertedUser.getRole());
  }

  @Test
  public void testInsertModelWithInvalidInput() throws SQLException, ClassNotFoundException {
    UserModel userModel = new UserModel();
    userModel.setUsername("");
    userModel.setPassword("password");
    userModel.setName("Test User");
    userModel.setEmail("");
    userModel.setPhone("");
    userModel.setRole(Role.CUSTOMER);

    userBUS.insertModel(userModel);
  }

  @Test
  public void testUpdateModel() throws SQLException, ClassNotFoundException {
    UserModel userModel = userBUS.getUserModel(1);
    userModel.setName("Updated Name");

    int rowsAffected = userBUS.updateModel(userModel);
    Assertions.assertEquals(1, rowsAffected);

    UserModel updatedUser = userBUS.getUserModel(1);
    Assertions.assertNotNull(updatedUser);
    Assertions.assertEquals(userModel.getName(), updatedUser.getName());
  }

  @Test
  public void testDeleteModel() throws SQLException, ClassNotFoundException {
    int rowsAffected = userBUS.deleteModel(2);
    Assertions.assertEquals(1, rowsAffected);

    UserModel deletedUser = userBUS.getUserModel(2);
    Assertions.assertNull(deletedUser);
  }

  @Test
  public void testDeleteModelWithValidInput() throws SQLException, ClassNotFoundException {
    UserBUS userBUS = new UserBUS();
    int result = userBUS.deleteModel(1);
    Assertions.assertTrue(result > 0);
  }

  @Test
  public void testDeleteModelWithInvalidInput() throws SQLException, ClassNotFoundException {
    UserBUS userBUS = new UserBUS();
    int result = userBUS.deleteModel(-1);
    Assertions.assertEquals(0, result);
  }

  @Test
  public void testSearchModel() {
    List<UserModel> searchResults = userBUS.searchModel("admin", "username");
    Assertions.assertNotNull(searchResults);
    Assertions.assertEquals(1, searchResults.size());

    UserModel searchResult = searchResults.get(0);
    Assertions.assertEquals("admin", searchResult.getUsername());
  }
}
