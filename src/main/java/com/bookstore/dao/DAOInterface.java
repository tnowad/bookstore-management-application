package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DAOInterface<Entity> {
    public int insert(Entity e) throws SQLException;

    public int update(Entity e) throws SQLException;

    public int delete(String id) throws SQLException;

    public ArrayList<Entity> selectAll() throws SQLException;

    public ArrayList<Entity> searchByCondition(String condition) throws SQLException;

    public ArrayList<Entity> searchByCondition(String condition, String columnName) throws SQLException;

    public ArrayList<Entity> readDatabase() throws SQLException;
}