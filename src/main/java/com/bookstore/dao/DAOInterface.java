package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface defining common database operations for entities.
 *
 * @param <Entity> the entity type that this DAO interface will handle
 */
public interface DAOInterface<Entity> {

  /**
   * Reads all entries from the database table associated with this DAO.
   *
   * @return an ArrayList of all Entity objects in the table
   * @throws SQLException           on any exception arising from database access
   *                                errors
   * @throws ClassNotFoundException if driver class not found
   */
  public ArrayList<Entity> readDatabase() throws SQLException, ClassNotFoundException;

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
  public int insert(Entity e) throws SQLException, ClassNotFoundException;

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
  public int update(Entity e) throws SQLException, ClassNotFoundException;

  /**
   * Deletes a given entity from the database table associated with this DAO.
   *
   * @param id the primary key of the entry to delete
   * @return the number of rows affected by the deletion statement
   * @throws SQLException           on any exception arising from database access
   *                                errors
   * @throws ClassNotFoundException if driver class not found
   */
  public int delete(String id) throws SQLException, ClassNotFoundException;

  /**
   * Searches the database table associated with this DAO for rows matching a
   * particular condition.
   *
   * @param condition the SQL WHERE clause used in the SELECT query, without the
   *                  WHERE keyword itself
   * @return a List of Entity objects representing all rows that match the search
   *         condition
   * @throws SQLException           on any exception arising from database access
   *                                errors
   * @throws ClassNotFoundException if driver class not found
   */
  public List<Entity> searchByCondition(String condition) throws SQLException, ClassNotFoundException;

  /**
   * Searches the database table associated with this DAO for rows matching a
   * particular condition and column.
   *
   * @param condition  the SQL WHERE clause used in the SELECT query, without the
   *                   WHERE keyword itself
   * @param columnName the name of the database column to restrict the search to
   * @return a List of Entity objects representing all rows that match the search
   *         condition and have the
   *         specified column value
   * @throws SQLException           on any exception arising from database access
   *                                errors
   * @throws ClassNotFoundException if driver class not found
   */
  public List<Entity> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException;
}
