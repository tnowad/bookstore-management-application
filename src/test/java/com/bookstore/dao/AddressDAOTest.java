package com.bookstore.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.AddressModel;

public class AddressDAOTest {
  private static AddressDAO addressDAO;

  @BeforeAll
  public static void setUp() {
    addressDAO = AddressDAO.getInstance();
  }

  @AfterAll
  public static void tearDown() {
    addressDAO = null;
  }

  @Test
  public void testGetInstance() {
    Assertions.assertNotNull(addressDAO);
  }

  @Test
  public void testReadDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<AddressModel> addressList = addressDAO.readDatabase();
    // Assertions.assertFalse(addressList.isEmpty());
    Assertions.assertNotNull(addressList);
    Assertions.assertEquals(121, addressList.size());
  }

  @Test
  public void testInsert() throws SQLException, ClassNotFoundException {
    AddressModel address = new AddressModel(21, 121, "123 Main St", "Anytown", "CA", "12345");
    int rowsAffected = addressDAO.insert(address);
    Assertions.assertEquals(1, rowsAffected);
  }

  @Test
  public void testUpdate() throws SQLException, ClassNotFoundException {
    AddressModel address = new AddressModel(2, 1, "456 Elm St", "Othertown", "NY", "67890");
    int rowsAffected = addressDAO.update(address);
    Assertions.assertEquals(1, rowsAffected);
  }

  @Test
  public void testDelete() throws SQLException, ClassNotFoundException {
    int rowsAffected = addressDAO.delete(34);
    Assertions.assertEquals(1, rowsAffected);
  }

  @Test
  public void testSearchWithNullCondition() {
    AddressDAO dao = new AddressDAO();
    String[] columns = { "id", "user_id" };
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      dao.search(null, columns);
    });
  }

  @Test
  public void testSearchWithEmptyCondition() {
    AddressDAO dao = new AddressDAO();
    String[] columns = { "id", "user_id" };
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      dao.search("", columns);
    });
  }

  @Test
  public void testSearchAllColumns() throws SQLException, ClassNotFoundException {
    AddressDAO dao = new AddressDAO();
    List<AddressModel> result = dao.search("123 Main St", null);
    Assertions.assertFalse(result.isEmpty());

  }

  @Test
  public void testSearchSpecificColumns() throws SQLException, ClassNotFoundException {
    AddressDAO dao = new AddressDAO();
    String[] columns = { "street", "city", "state" };
    List<AddressModel> result = dao.search("123 Main St", columns);
    Assertions.assertFalse(result.isEmpty());
  }

  @Test
  public void testSearchNoResults() {
    AddressDAO dao = new AddressDAO();
    String[] columns = { "id", "user_id" };
    Assertions.assertThrows(SQLException.class, () -> {
      dao.search("nonexistent address", columns);
    });
  }

  @Test
  public void testSearchSpecificColumn() throws SQLException, ClassNotFoundException {
    AddressDAO dao = new AddressDAO();
    String[] columns = { "street", "city" };
    List<AddressModel> result = dao.search("Los Angeles", columns);
    // Assertions.assertFalse(result.isEmpty());
    Assertions.assertEquals(1, result.size());
  }
}
