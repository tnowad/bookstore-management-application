package com.bookstore.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface defining common database operations for entities.
 *
 * @param <T> the entity type that this DAO interface will handle
 */
public interface IDAO<T> {

  /**
   * Reads all entries from the database table associated with this DAO.
   *
   * @return an ArrayList of all Entity objects in the table
   * @throws SQLException           on any exception arising from database access
   *                                errors
   * @throws ClassNotFoundException if driver class not found
   */
  ArrayList<T> readDatabase() throws SQLException, ClassNotFoundException;

  /**
   * Inserts an entity to the database table associated with this DAO.
   *
   * @param e the entity to insert
   * @return the number of rows affected by the insert statement
   * @throws SQLException           on any exception arising from database access
   *                                errors or non-unique primary key
   *                                constraints violated
   * @throws ClassNotFoundException if driver class not found
   */
  int insert(T e) throws SQLException, ClassNotFoundException;

  /**
   * Updates an existing entry in the database table associated with this DAO.
   *
   * @param e the entity to update
   * @return the number of rows affected by the update statement
   * @throws SQLException           on any exception arising from database access
   *                                errors or identical primary key
   *                                constraints violated
   * @throws ClassNotFoundException if driver class not found
   */
  int update(T e) throws SQLException, ClassNotFoundException;

  /**
   * Deletes a given entity from the database table associated with this DAO.
   *
   * @param id the primary key of the entry to delete
   * @return the number of rows affected by the deletion statement
   * @throws SQLException           on any exception arising from database access
   *                                errors
   * @throws ClassNotFoundException if driver class not found
   */
  int delete(int id) throws SQLException, ClassNotFoundException;

  /**
   * Searches the database table associated with this DAO for entities that match
   * the given condition
   * in the specified columns.
   *
   * @param condition   the search condition to use
   * @param columnNames the names of the columns to search in
   * @return a list of entities that match the search condition
   * @throws SQLException           if there is any error accessing the database
   * @throws ClassNotFoundException if the database driver class cannot be found
   */
  List<T> search(String condition, String[] columnName)
      throws SQLException, ClassNotFoundException;
}
