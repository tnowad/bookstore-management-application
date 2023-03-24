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
    Assertions.assertFalse(addressList.isEmpty());
  }

  @Test
  public void testInsert() throws SQLException, ClassNotFoundException {
    AddressModel address = new AddressModel(1, 0, "123 Main St", "Anytown", "CA", "12345");
    int rowsAffected = addressDAO.insert(address);
    Assertions.assertEquals(1, rowsAffected);
  }

  @Test
  public void testUpdate() throws SQLException, ClassNotFoundException {
    AddressModel address = new AddressModel(1, 1, "456 Oak St", "Othertown", "NY", "67890");
    int rowsAffected = addressDAO.update(address);
    Assertions.assertEquals(1, rowsAffected);
  }

  @Test
  public void testDelete() throws SQLException, ClassNotFoundException {
    int rowsAffected = addressDAO.delete(1);
    Assertions.assertEquals(1, rowsAffected);
  }

  @Test
  public void testSearchByCondition() throws SQLException, ClassNotFoundException {
    List<AddressModel> addressList = addressDAO.searchByCondition("Anytown", "city");
    Assertions.assertFalse(addressList.isEmpty());
  }
}
