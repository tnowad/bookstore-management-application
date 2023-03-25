package com.bookstore.bus;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bookstore.model.AddressModel;

import java.sql.SQLException;
import java.util.List;

public class AddressBUSTest {

  private AddressBUS addressBUS;

  @BeforeEach
  public void setUp() throws SQLException, ClassNotFoundException {
    addressBUS = new AddressBUS();
  }

  @Test
  public void testGetAllModels() {
    List<AddressModel> addressList = addressBUS.getAllModels();
    Assertions.assertNotNull(addressList);
    Assertions.assertFalse(addressList.isEmpty());
    Assertions.assertEquals(21, addressList.size());
  }

  @Test
  public void testGetModelById() throws SQLException, ClassNotFoundException {
    AddressModel addressModel = addressBUS.getModelById(2);
    Assertions.assertNotNull(addressModel);
    Assertions.assertEquals(2, addressModel.getId());
  }

  @Test
  public void testAddModel() throws SQLException, ClassNotFoundException {
    AddressModel addressModel = new AddressModel();
    addressModel.setUserId(18);
    addressModel.setStreet("123 Main St");
    addressModel.setCity("Anytown");
    addressModel.setState("CA");
    addressModel.setZip("12345");

    int id = addressBUS.addModel(addressModel);
    Assertions.assertTrue(id > 0);

    AddressModel addedAddress = addressBUS.getModelById(id);
    Assertions.assertNotNull(addedAddress);
    Assertions.assertEquals(addressModel.getStreet(), addedAddress.getStreet());
    Assertions.assertEquals(addressModel.getCity(), addedAddress.getCity());
    Assertions.assertEquals(addressModel.getState(), addedAddress.getState());
    Assertions.assertEquals(addressModel.getZip(), addedAddress.getZip());
  }

  @Test
  public void testUpdateModel() throws SQLException, ClassNotFoundException {
    // Create a new address model and add it to the database
    int id = 20;
    AddressModel addressModel = addressBUS.getModelById(id);
    // addressModel.setId(20);
    // addressModel.setStreet("123 Main St");
    // addressModel.setCity("Anytown");
    // addressModel.setState("CA");
    // addressModel.setZip("12345");
    // int id = addressBUS.addModel(addressModel);

    // Update the address model
    addressModel.setStreet("456789 Oak St");
    // addressModel.setCity("Othertown");
    // addressModel.setState("NY");
    // addressModel.setZip("67890");
    int result = addressBUS.updateModel(addressModel);

    // Check that the update was successful
    Assertions.assertEquals(1, result);

    // Get the updated address model from the database
    AddressModel updatedAddressModel = addressBUS.getModelById(id);

    // Check that the fields were updated correctly
    Assertions.assertEquals("456789 Oak St", updatedAddressModel.getStreet());
    // Assertions.assertEquals("Othertown", updatedAddressModel.getCity());
    // Assertions.assertEquals("NY", updatedAddressModel.getState());
    // Assertions.assertEquals("67890", updatedAddressModel.getZip());
  }

  @Test
  public void testDeleteModel() throws SQLException, ClassNotFoundException {
    int idToDelete = 23;
    int rowsDeleted = addressBUS.deleteModel(idToDelete);
    Assertions.assertEquals(1, rowsDeleted);

    AddressModel deletedAddress = addressBUS.getModelById(idToDelete);
    Assertions.assertNull(deletedAddress);
  }

  @Test
  public void testSearchModel() throws SQLException, ClassNotFoundException {
    String searchValue = "Anytown";
    String searchColumns = "city";

    List<AddressModel> results = addressBUS.searchModel(searchValue, searchColumns);
    Assertions.assertNotNull(results);
    Assertions.assertFalse(results.isEmpty());

    for (AddressModel result : results) {
      Assertions.assertTrue(result.getCity().toLowerCase().contains(searchValue.toLowerCase()));
    }
    Assertions.assertEquals(1, results.size());
  }
}
