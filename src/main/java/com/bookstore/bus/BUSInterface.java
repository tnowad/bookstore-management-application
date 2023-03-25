package com.bookstore.bus;

import java.sql.SQLException;
import java.util.List;

public interface BUSInterface<T> {

  /**
   * Returns a list of all models.
   *
   * @return a list of all models
   */
  List<T> getAllModels();

  /**
   * Returns the model with the given id.
   *
   * @param id the id of the model to retrieve
   * @return the model with the given id, or null if not found
   * @throws SQLException           if there is an error accessing the database
   * @throws ClassNotFoundException if the driver class cannot be found
   */
  T getModelById(int id) throws SQLException, ClassNotFoundException;

  /**
   * Adds the given model to the database.
   *
   * @param model the model to add
   * @return the number of rows affected by the operation
   * @throws SQLException           if there is an error accessing the database
   * @throws ClassNotFoundException if the driver class cannot be found
   */
  int addModel(T model) throws SQLException, ClassNotFoundException;

  /**
   * Updates the given model in the database.
   *
   * @param model the model to update
   * @return the number of rows affected by the operation
   * @throws SQLException           if there is an error accessing the database
   * @throws ClassNotFoundException if the driver class cannot be found
   */
  int updateModel(T model) throws SQLException, ClassNotFoundException;

  /**
   * Deletes the model with the given id from the database.
   *
   * @param id the id of the model to delete
   * @return the number of rows affected by the operation
   * @throws SQLException           if there is an error accessing the database
   * @throws ClassNotFoundException if the driver class cannot be found
   */
  int deleteModel(int id) throws SQLException, ClassNotFoundException;

  /**
   * Searches for models that match the given value in the specified columns.
   *
   * @param value   the value to search for
   * @param columns the columns to search in
   * @return a list of models that match the search criteria
   * @throws SQLException           if there is an error accessing the database
   * @throws ClassNotFoundException if the driver class cannot be found
   */
  List<T> searchModel(String value, String columns) throws SQLException, ClassNotFoundException;
}
