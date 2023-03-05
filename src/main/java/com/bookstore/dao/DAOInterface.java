package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DAOInterface<Entity> {
  public ArrayList<Entity> readDatabase() throws SQLException;

  public int insert(Entity e) throws SQLException;

  public int update(Entity e) throws SQLException;

  public int delete(String id) throws SQLException;

  public List<Entity> searchByCondition(String condition) throws SQLException;

  public List<Entity> searchByCondition(String condition, String columnName) throws SQLException;

}