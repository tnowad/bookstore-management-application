package com.bookstore.bus;

import java.util.List;

public interface BUSInterface<T> {

  /**
   * Retrieves all models from the database.
   *
   * @return a list of all models in the database.
   */
  List<T> getAllModels();

  /**
   * Retrieves a specific model from the database based on its ID.
   *
   * @param id the ID of the model to retrieve.
   * @return the model with the specified ID if it exists; otherwise null.
   */
  T getModelById(int id);

  /**
   * Adds a new model to the database.
   *
   * @param model the model to add.
   * @return true if the model was added successfully; otherwise false.
   */
  boolean addModel(T model);

  /**
   * Updates an existing model in the database.
   *
   * @param model the updated model to replace the old one.
   * @return true if the model was updated successfully; otherwise false.
   */
  boolean updateModel(T model);

  /**
   * Deletes a model from the database based on its ID.
   *
   * @param id the ID of the model to delete.
   * @return true if the model was deleted successfully; otherwise false.
   */
  boolean deleteModel(int id);

  /**
   * Searches for models that contain a specific value in specific columns.
   *
   * @param value   the value to search for.
   * @param columns the columns to include in the search.
   * @return a list of models that matched the search criteria.
   */
  List<T> search(String value, String... columns);
}
