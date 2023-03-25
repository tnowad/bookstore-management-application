package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.model.AuthorModel;

public class AuthorDAOTest {

  private static AuthorDAO authorDAO;

  @BeforeAll
  public static void setUp() {
    authorDAO = AuthorDAO.getInstance();
  }

  @AfterAll
  public static void tearDown() {
    authorDAO = null;
  }

  @Test
  public void testReadDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<AuthorModel> authorList = authorDAO.readDatabase();
    Assertions.assertNotNull(authorList);
    Assertions.assertEquals(20, authorList.size());
  }

  @Test
  public void testInsert() throws SQLException, ClassNotFoundException {
    AuthorModel author = new AuthorModel(21, "Test Author", "A test author");
    int result = authorDAO.insert(author);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void testUpdate() throws SQLException, ClassNotFoundException {
    AuthorModel authorModel = new AuthorModel(20, "Danny", "Testing");
    int rowsAffected = authorDAO.update(authorModel);
    Assertions.assertEquals(1, rowsAffected);
  }

  @Test
  public void testDelete() throws SQLException, ClassNotFoundException {
    int result = authorDAO.delete(20);
    Assertions.assertEquals(1, result);
  }

  @Test
  public void testSearchByCondition() throws SQLException, ClassNotFoundException {
    List<AuthorModel> authorList = authorDAO.search("Harper", "name");
    Assertions.assertNotNull(authorList);
    Assertions.assertEquals(1, authorList.size());
  }
}
