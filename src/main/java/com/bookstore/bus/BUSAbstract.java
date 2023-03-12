package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BUSAbstract<T> {
  protected List<T> models;

  /**
   * Constructor for creating a new instance of the BUSAbstract class
   * 
   * @throws SQLException           if database error occurs
   * @throws ClassNotFoundException if class cannot be loaded from name
   */
  protected BUSAbstract() throws SQLException, ClassNotFoundException {
    this.models = readFromDatabase();
  }

  /**
   * Reads data from the database and returns it as an ArrayList
   * 
   * @return ArrayList of type T containing model data
   * @throws SQLException           if database error occurs
   * @throws ClassNotFoundException if class cannot be loaded from name
   */
  protected abstract ArrayList<T> readFromDatabase() throws SQLException, ClassNotFoundException;

  /**
   * Retrieves the ID field of a given model
   * 
   * @param t the model of which to retrieve the ID
   * @return integer value of the ID field of the model
   */
  protected abstract int getId(T t);

  /**
   * Transforms T-objects to Entities understandable by the database
   * 
   * @param t the model to transform
   * @return the entity transformed from the given model
   */
  protected abstract T mapToEntity(T t);

  /**
   * Inserts a new model into the database
   * 
   * @param model the model to insert
   * @return integer value indicating the resulting status of insertion
   * @throws SQLException           if database error occurs
   * @throws ClassNotFoundException if class cannot be loaded from name
   */
  protected abstract int insertModel(T model) throws SQLException, ClassNotFoundException;

  /**
   * Updates a model in the database
   * 
   * @param model the model to update
   * @return integer value indicating the resulting status of update
   * @throws SQLException           if database error occurs
   * @throws ClassNotFoundException if class cannot be loaded from name
   */
  protected abstract int updateModel(T model) throws SQLException, ClassNotFoundException;

  /**
   * Deletes a model from the database
   * 
   * @param id the ID of the model to delete
   * @return integer value indicating the resulting status of deletion
   * @throws SQLException           if database error occurs
   * @throws ClassNotFoundException if class cannot be loaded from name
   */
  protected abstract int deleteModel(int id) throws SQLException, ClassNotFoundException;

  /**
   * Checks if the given model passes a certain filter criteria
   * 
   * @param t       the model to check against the filter
   * @param value   the string value for the filter criteria
   * @param columns the column or attribute to filter on
   * @return true if the model passes the filter criteria, false otherwise
   */
  protected abstract boolean checkFilter(T t, String value, String columns);

  /**
   * Copies data from one model object to another
   * 
   * @param from the Model object to copy data from
   * @param to   the Model object to copy data to
   */
  protected abstract void updateEntityFields(T from, T to);

  /**
   * Adds a new model to the database
   * 
   * @param t The model to add
   * @return integer value indicating the resulting status of the operation
   * @throws SQLException           if database error occurs
   * @throws ClassNotFoundException if class cannot be loaded from name
   */
  public int add(T t) throws SQLException, ClassNotFoundException {
    int added = insertModel(t);
    if (added == 1) {
      models.add(t);
    }
    return added;
  }

  /**
   * Updates an existing model in the database
   * 
   * @param t The updated model to replace the old one
   * @return integer value indicating the resulting status of the operation
   * @throws SQLException           if database error occurs
   * @throws ClassNotFoundException if class cannot be loaded from name
   */
  public int update(T t) throws SQLException, ClassNotFoundException {
    T currentModelToUpdate = getModel(getId(t));
    if (currentModelToUpdate == null) {
      return 0;
    } else {
      updateEntityFields(currentModelToUpdate, t);
      int updated = updateModel(currentModelToUpdate);
      if (updated == 1) {
        setCurrentModel(currentModelToUpdate);
        return 1;
      } else {
        return 0;
      }
    }
  }

  /**
   * Sets the current model with the specified modelToUpdate object by replacing
   * it in the models list if it exists.
   * Otherwise, an IllegalArgumentException is thrown.
   * 
   * @param modelToUpdate The model object to set as the current model.
   */
  private void setCurrentModel(T modelToUpdate) {
    int index = models.indexOf(modelToUpdate);
    if (index > -1) {
      models.set(index, modelToUpdate);
    } else {
      throw new IllegalArgumentException("The object to update does not exist in the model list.");
    }
  }

  /**
   * Deletes a particular representation of the model from the database
   * 
   * @param entityId the unique identifier of represented model to delete
   * @return integer value indicating the resulting status of the operation
   * @throws SQLException           if database error occurs
   * @throws ClassNotFoundException if class cannot be loaded from name
   */
  public int delete(int entityId) throws SQLException, ClassNotFoundException {
    T entityToRemove = getModel(entityId);
    if (entityToRemove == null) {
      return 0;
    }
    int deleted = deleteModel(getId(entityToRemove));
    if (deleted != 1) {
      return 0;
    }
    models.removeIf(m -> getId(m) == entityId);
    return 1;
  }

  /**
   * Searches for models that contain a specific value in specific columns.
   * 
   * @param value   the value to search for.
   * @param columns the columns to include in the search.
   * @return a list of models that matched the search criteria.
   * @throws IllegalArgumentException if either value or columns are null or
   *                                  empty.
   */
  public List<T> search(String value, String columns) {
    if (value == null || value.isEmpty()) {
      throw new IllegalArgumentException(
          "Invalid input. Please make sure 'value' are not null or empty.");
    }
    List<T> results = new ArrayList<>();
    for (T t : models) {
      if (checkFilter(t, value, columns)) {
        results.add(t);
      }
    }
    return results;
  }

  /**
   * Gets the details of a specific Model entity based on its ID
   * 
   * @param modelId the identifier of desired Entity
   * @return the entity with the specified identifier if exists;otherwise null
   */
  public T getModel(int modelId) {
    for (T m : models) {
      if (getId(m) == modelId) {
        return m;
      }
    }
    return null;
  }

  /**
   * Returns the ArrayList of values entries used to represent the Models in
   * memory
   * 
   * @return an ArrayList of models
   */
  public List<T> getModels() {
    return models;
  }
}
