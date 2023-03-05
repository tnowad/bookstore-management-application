package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DAOInterface<Entity> {
  // fetches all records from database and returns them as an ArrayList of
  // entities.
  public ArrayList<Entity> readDatabase() throws SQLException;

  // inserts a single entity record into the database.
  public int insert(Entity e) throws SQLException;

  // updates an existing entity record in the database.
  public int update(Entity e) throws SQLException;

  // deletes an existing entity record from the database based on its ID.
  public int delete(String id) throws SQLException;

  // searches for entity records in the database based on given search condition
  // and returns them as a List of entities.
  public List<Entity> searchByCondition(String condition) throws SQLException;

  // searches for entity records in the database based on given search condition
  // and column name and returns them as a List of entities.
  public List<Entity> searchByCondition(String condition, String columnName) throws SQLException;
}