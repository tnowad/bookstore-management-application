package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.AccountModel;

public class AccountDAO implements DAOInterface<AccountModel> {

    public static AccountDAO getÍntance() {
        return new AccountDAO();
    }

    @Override
    public ArrayList<AccountModel> readDatabase() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readDatabase'");
    }

    @Override
    public int insert(AccountModel e) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(AccountModel e) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(String id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<AccountModel> searchByCondition(String condition) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
    }

    @Override
    public List<AccountModel> searchByCondition(String condition, String columnName) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
    }

}
