package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.model.CustomerModel;

public class CustomerDAO<CustomerModel> implements DAOInterface {
    public static CustomerDAO getInstance() {
        return new CustomerDAO();
    }

    @Override
    public int insert(Object e) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(Object e) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(int id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public ArrayList selectAll() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectAll'");
    }

    @Override
    public Object selectById(String id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectById'");
    }

    @Override
    public ArrayList selectByCondition(String condition) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'selectByCondition'");
    }

    @Override
    public ArrayList readDatabase() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readDatabase'");
    }

}
