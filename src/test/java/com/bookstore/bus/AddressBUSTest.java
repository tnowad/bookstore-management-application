package com.bookstore.bus;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.bookstore.model.AddressModel;

import java.sql.SQLException;
import java.util.List;

public class AddressBUSTest {

  private static AddressBUS addressBUS;

  @BeforeAll
  public static void setUp() throws ClassNotFoundException, SQLException {
    addressBUS = AddressBUS.getInstance();
  }

  @AfterAll
  public static void tearDown() {
    addressBUS = null;
  }

  @Test
  public void testGetInstance() {
    Assertions.assertNotNull(addressBUS);
  }

  @Test
  public void testAddModel() throws SQLException, ClassNotFoundException {
    AddressModel model = new AddressModel(200, 50, "123 Main St", "Anytown", "CA", "12345");
    int id = addressBUS.addModel(model);
    Assertions.assertTrue(id > 0);
  }

  @Test
  public void testAddModelWithNullStreet() {
    AddressModel model = new AddressModel(201, 60, null, "Anytown", "CA", null);
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      addressBUS.addModel(model);
    });
  }

  @Test
  public void testUpdateModel() throws SQLException, ClassNotFoundException {
    AddressModel model = new AddressModel(2, 1, "123 Main St", "Anytown", "CA", "2345");

    // Set the city field to "OtherCity"
    model.setCity("OtherCity");

    // Call the updateModel method on the AddressBUS instance and pass in the
    // AddressModel object
    int updatedRows = addressBUS.updateModel(model);

    // Assert that the return value of updateModel is 1
    Assertions.assertEquals(1, updatedRows);

    // Retrieve the updated AddressModel from the database using its ID
    AddressModel updatedModel = addressBUS.getModelById(2);

    // Assert that the city field of the updated model is "OtherCity"
    Assertions.assertEquals("OtherCity", updatedModel.getCity());
  }

  @Test
  public void testDeleteModel() throws SQLException, ClassNotFoundException {

    int id = 3;

    int deletedRows = addressBUS.deleteModel(id);
    Assertions.assertEquals(1, deletedRows);

    AddressModel deletedModel = addressBUS.getModelById(id);
    Assertions.assertNull(deletedModel);
  }

  @Test
  public void testDeleteNonExistingModel() {
    int id = -1;
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      addressBUS.deleteModel(id);
    });
  }

  @Test
  public void testSearchModel() throws SQLException, ClassNotFoundException {

    String value = "OtherCity";
    String[] columns = { "city" };
    List<AddressModel> results = addressBUS.searchModel(value, columns);

    Assertions.assertEquals(2, results.size());
    Assertions.assertEquals("123 Main St", results.get(0).getStreet());
  }

  @Test
  public void testSearchModelWithNoResults() throws SQLException {
    String value = "Nonexistent";
    String[] columns = { "street", "city", "state" };
    Assertions.assertThrows(SQLException.class, () -> {
      addressBUS.searchModel(value, columns);
    });
  }

  @Test
  public void testSearchModelWithMultipleColumns() throws SQLException, ClassNotFoundException {
    String value = "Anytown";
    String[] columns = { "city", "street" };
    List<AddressModel> results = addressBUS.searchModel(value, columns);

    Assertions.assertEquals(6, results.size());
    Assertions.assertEquals("123 Main St", results.get(0).getStreet());
  }

  // @Test
  // public void testSearchWithIntDatatype() throws ClassNotFoundException,
  // SQLException {
  // String value = "66";
  // String[] columns = {};
  // // Assertions.assertThrows(IllegalArgumentException.class, () -> {
  // // addressBUS.searchModel(value, columns);
  // // });
  // List<AddressModel> results = addressBUS.searchModel(value, columns);
  // // Assertions.assertEquals(1, results.size());
  // // Assertions.assertEquals("1267 Jaleel Branch", results.get(0).getStreet());
  // Assertions.assertEquals(2, results.size());
  // }

}
