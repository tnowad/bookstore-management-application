package com.bookstore.dao;

import java.util.ArrayList;

public interface DAOInterface<Entity> {
    public boolean insert(Entity e);

    public boolean update(Entity e);

    public boolean delete(Entity e);

    public ArrayList<Entity> selectAll();

    public Entity selectById(String id);

    public ArrayList<Entity> selectByCondition(String condition);

    public ArrayList<Entity> readDatabase();
}
