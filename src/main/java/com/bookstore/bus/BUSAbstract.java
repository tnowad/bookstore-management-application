package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BUSAbstract<T> {
  private ArrayList<T> models;

  /**
   * Constructor for BUSAbstract class that initializes models ArrayList
   * with the data imported from readDatabase function.
   *
   * @throws ClassNotFoundException if the specified database driver cannot be
   *                                found
   * @throws SQLException           if a database access error occurs
   */
  protected BUSAbstract() throws ClassNotFoundException, SQLException {
    this.models = readDatabase();
  }

  /**
   * This method is used to read data from the database and store it into an
   * ArrayList of T type.
   *
   * @return an ArrayList containing objects of T type
   * @throws ClassNotFoundException if the specified database driver cannot be
   *                                found
   * @throws SQLException           if a database access error occurs
   */
  protected abstract ArrayList<T> readDatabase() throws ClassNotFoundException, SQLException;

  /**
   * Method to get the T object's id value.
   *
   * @param t The T object which the id is to be returned
   * @return the id value of the provided model
   */
  protected abstract int getId(T t);

  /**
   * Add new object of T type to the database using an object T.
   *
   * @param t object of T type to be added
   * @return 1 if record added successfully otherwise 0
   * @throws ClassNotFoundException if the specified database driver cannot be
   *                                found
   * @throws SQLException           if a database access error occurs
   */
  public int add(T t) throws ClassNotFoundException, SQLException {
    int added = insert(t);
    if (added == 1) {
      models.add(t);
    }
    return added;
  }

  /**
   * Creates a new T object from the given T instance.
   * This method should be implemented by subclasses to initialize the properties
   * of
   * the created model based on the provided arguments.
   * 
   * @param t an instance of T to use as the basis for creating a new object
   * @return a newly created and fully initialized instance of T
   */
  protected abstract T createModel(T t);

  /**
   * Inserts the provided model into database, returns 1 if successful
   * 0 or negative value on failure.
   *
   * @param model object of T type to be inserted
   * @return 1 if record inserted successfully otherwise 0 or negative values
   * @throws ClassNotFoundException if the specified database driver cannot be
   *                                found
   * @throws SQLException           if a database access error occurs
   */
  protected abstract int insert(T model) throws ClassNotFoundException, SQLException;

  /**
   * Update an existing entry in the database using an object T.
   *
   * @param t object of T type to update an existing record
   * @return 1 if a record updated successfully, 0 otherwise
   * @throws ClassNotFoundException if the specified database driver cannot be
   *                                found
   * @throws SQLException           if a database access error occurs
   */
  public int update(T t) throws ClassNotFoundException, SQLException {
    T currentModelToUpdate = getModel(getId(t));
    if (currentModelToUpdate == null) {
      return 0;
    } else {
      copyProperties(currentModelToUpdate, t);
      int updated = updateModel(currentModelToUpdate);
      if (updated == 1) {
        for (int i = 0; i < models.size(); i++) {
          T model = models.get(i);
          if (getId(model) == getId(currentModelToUpdate)) {
            models.set(i, currentModelToUpdate);
          }
        }
        return 1;
      } else {
        return 0;
      }
    }
  }

  /**
   * Copy property values from updated entity into existing entity.
   *
   * @param currentModel Existing entity model
   * @param newModle     Updated entity model
   */
  protected void copyProperties(T currentModel, T newModel) {
  }

  /**
   * Updates a record in the database based on provided object T.
   *
   * @param model An object of T type referring the content to modify record
   * @return 1 if a record updated successfully otherwise 0
   * @throws ClassNotFoundException if the specified database driver cannot be
   *                                found
   * @throws SQLException           if a database access error occurs
   */
  protected abstract int updateModel(T model) throws ClassNotFoundException, SQLException;

  /**
   * Delete a record in the database based on the provide object identifier
   *
   * @param modelId identifier of specific object T that holds content to delete.
   * @return 1 if deleted successfully otherwise 0
   * @throws ClassNotFoundException if the specified database driver cannot be
   *                                found
   * @throws SQLException           if a database access error occurs
   */
  public int delete(int modelId) throws ClassNotFoundException, SQLException {
    T model = getModel(modelId);
    if (model == null) {
      return 0;
    }
    int deleted = deleteModel(Integer.toString(getId(model)));
    if (deleted != 1) {
      return 0;
    }
    models.removeIf(m -> getId(m) == modelId);
    return deleted;
  }

  /**
   * Deletes a record from the database based on supplied identifier.
   *
   * @param id identifier of the record to be deleted
   * @return 1 if record deleted successfully otherwise 0
   * @throws ClassNotFoundException if the specified database driver cannot be
   *                                found
   * @throws SQLException           if a database access error occurs
   */
  protected abstract int deleteModel(String id) throws ClassNotFoundException, SQLException;

  /**
   * Search records in the database matching provided value
   * with given columns.
   *
   * @param value   String of search value e.g a name
   * @param columns String of comma-separated column names to search within e,g
   *                "name, author"
   * @return an array list of matching records (ArrayList<T>) from the database
   */
  public ArrayList<T> search(String value, String columns) {
    ArrayList<T> results = new ArrayList<>();
    for (T t : models) {
      if (checkValue(t, value, columns)) {
        results.add(t);
      }
    }
    return results;
  }

  /**
   * Check if a record has provided value exists in given column(s).
   *
   * @param t       the object to check string against
   * @param value   value to search
   * @param columns columns to look into.
   * @return true if the record matches the search criteria, false otherwise
   */
  protected boolean checkValue(T t, String value, String columns) {
    return false;
  };

  /**
   * Retrieve an object of T type from the provided model identifier.
   *
   * @param modelId identifier for the model to retrieve
   * @return a T object if found and null if not found
   */
  public T getModel(int modelId) {
    if (models != null) {
      for (T t : models) {
        if (getId(t) == modelId) {
          return t;
        }
      }
    }
    return null;
  }

  /**
   * Get all object models from ArrayList <T>.
   *
   * @return ArrayList<T> containing all model instances.
   */
  public ArrayList<T> getModels() {
    return models;
  }
}
