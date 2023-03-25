package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.bookstore.model.UserModel;

public class UserDAOTest {

  private static UserDAO userDAO;

  @BeforeAll
  public static void setUp() {
    userDAO = UserDAO.getInstance();
  }

  @Test
  public void testReadDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<UserModel> userList = userDAO.readDatabase();
    Assertions.assertNotNull(userList);
    Assertions.assertEquals(12, userList.size());
  }

  @Test
  public void testInsert() throws SQLException, ClassNotFoundException {
    UserModel user = new UserModel(11, "testuser", "password", UserModel.Status.ACTIVE, "Test User",
        "testuser@example.com", "1234567890", null, null, UserModel.Role.CUSTOMER);
    int result = userDAO.insert(user);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void testUpdate() throws SQLException, ClassNotFoundException {
    UserModel user = userDAO.getUserById(2);
    user.setName("Updated Name");
    int result = userDAO.update(user);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void testDelete() throws SQLException, ClassNotFoundException {
    int result = userDAO.delete(6);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void testSearchByCondition() throws SQLException, ClassNotFoundException {
    List<UserModel> userList = userDAO.search("ngoctu", "username");
    Assertions.assertNotNull(userList);
    Assertions.assertEquals(1, userList.size());
  }

  @Test
  public void testGetUserByUsername() throws SQLException, ClassNotFoundException {
    UserModel user = userDAO.getUserByUsername("lethianh");
    Assertions.assertNotNull(user);
    Assertions.assertEquals(2, user.getId());
  }

  @Test
  public void testGetUserById() throws SQLException, ClassNotFoundException {
    UserModel user = userDAO.getUserById(3);
    Assertions.assertNotNull(user);
    Assertions.assertEquals("phuongthanh", user.getUsername());
  }
}
