package com.bookstore.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.PromotionsModel;

public class PromotionDAO implements DAOInterface<PromotionsModel> {

    @Override
    public ArrayList<PromotionsModel> readDatabase() throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'readDatabase'");
    }

    @Override
    public int insert(PromotionsModel e) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(PromotionsModel e) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(String id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<PromotionsModel> searchByCondition(String condition) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
    }

    @Override
    public List<PromotionsModel> searchByCondition(String condition, String columnName) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
    }

}
