package com.bookstore.bus;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class BUSAbstract<T> {

  private ArrayList<T> models; // list of models fetched from the database

  /**
   * Constructor. Initializes models ArrayList with results of readDatabase().
   *
   * @throws ClassNotFoundException If the JDBC driver cannot be found.
   * @throws SQLException           If there is an error connecting to the
   *                                database or executing a query.
   */
  protected BUSAbstract() throws ClassNotFoundException, SQLException {
    this.models = readDatabase();
  }

  /**
   * Abstract method. Fetches all models from database and returns them as an
   * ArrayList.
   *
   * @return ArrayList of all models in database.
   * @throws ClassNotFoundException If the JDBC driver cannot be found.
   * @throws SQLException           If there is an error connecting to the
   *                                database or executing a query.
   */
  protected abstract ArrayList<T> readDatabase() throws ClassNotFoundException, SQLException;

  /**
   * Abstract method. Gets the ID of a model object.
   *
   * @param t Model object.
   * @return ID of the model object.
   */
  protected abstract int getId(T t);

  /**
   * Abstract method. Creates a new model object.
   *
   * @param t Model object.
   * @return Newly created model object.
   */
  protected abstract T createModel(T t);

  /**
   * Abstract method. Inserts a new row into the database for a given model.
   *
   * @param model Model object.
   * @return Number of rows affected by the insert statement.
   * @throws ClassNotFoundException If the JDBC driver cannot be found.
   * @throws SQLException           If there is an error connecting to the
   *                                database or executing a query.
   */
  protected abstract int insert(T model) throws ClassNotFoundException, SQLException;

  /**
   * Abstract method. Updates an existing model row in the database.
   *
   * @param model Model object.
   * @return Number of rows affected by the update statement.
   * @throws ClassNotFoundException If the JDBC driver cannot be found.
   * @throws SQLException           If there is an error connecting to the
   *                                database or executing a query.
   */
  protected abstract int updateModel(T model) throws ClassNotFoundException, SQLException;

  /**
   * Abstract method. Deletes a model row from the database.
   *
   * @param id ID of the model to delete.
   * @return Number of rows affected by the delete statement.
   * @throws ClassNotFoundException If the JDBC driver cannot be found.
   * @throws SQLException           If there is an error connecting to the
   *                                database or executing a query.
   */
  protected abstract int deleteModel(String id) throws ClassNotFoundException, SQLException;

  /**
   * Checks whether a given value exists in a specified column for a model.
   *
   * @param t       Model object.
   * @param value   Value to check for.
   * @param columns Comma-separated list of columns to search.
   * @return True if the value exists, false otherwise.
   */
  protected boolean checkValue(T t, String value, String columns) {
    return false;
  }

  /**
   * Copies properties from one model object to another.
   *
   * @param currentModel Current model object.
   * @param newModel     New model object.
   */
  protected void copyProperties(T currentModel, T newModel) {
  }

  /**
   * Adds a new model to the database and models ArrayList.
   *
   * @param t New model object.
   * @return Number of rows affected by the insert statement.
   * @throws ClassNotFoundException If the JDBC driver cannot be found.
   * @throws SQLException           If there is an error connecting to the
   *                                database or executing a query.
   */
  public int add(T t) throws ClassNotFoundException, SQLException {
    int added = insert(t);
    if (added == 1) {
      models.add(t);
    }
    return added;
  }

  /**
   * Updates an existing model in the database and models ArrayList.
   *
   * @param t Model object to update.
   * @return Number of rows affected by the update statement.
   * @throws ClassNotFoundException If the JDBC driver cannot be found.
   * @throws SQLException           If there is an error connecting to the
   *                                database or executing a query.
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
   * Deletes a model from the database and models ArrayList.
   *
   * @param modelId ID of the model to delete.
   * @return Number of rows affected by the delete statement.
   * @throws ClassNotFoundException If the JDBC driver cannot be found.
   * @throws SQLException           If there is an error connecting to the
   *                                database or executing a query.
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
   * Searches for models with a given value in a specified column.
   *
   * @param value   Value to search for.
   * @param columns Comma-separated list of columns to search in.
   * @return ArrayList of all matching models.
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
   * Gets the model with a given ID from models ArrayList.
   *
   * @param modelId ID of the model to retrieve.
   * @return Model object with the given ID, or null if not found.
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
   * Gets all models from models ArrayList.
   *
   * @return ArrayList of all models.
   */
  public ArrayList<T> getModels() {
    return models;
  }
}
