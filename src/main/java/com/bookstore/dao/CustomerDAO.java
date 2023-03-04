package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bookstore.model.CustomerModel;
import com.bookstore.model.UserModel;
import com.mysql.cj.protocol.Resultset;

public class CustomerDAO<CustomerModel> implements DAOInterface {
    public static CustomerDAO getInstance() {
        return new CustomerDAO();
    }

    private CustomerModel createCustomerModelFromResultSet(Resultset rs) throws SQLException {
    }

    @Override
    public int insert(Object e) throws SQLException {

    }

    @Override
    public int update(Object e) throws SQLException {

    }

    @Override
    public int delete(int id) throws SQLException {

    }

    @Override
    public ArrayList selectAll() throws SQLException {

    }

    @Override
    public ArrayList searchByCondition(String condition) throws SQLException {

    }

    @Override
    public ArrayList searchByCondition(String condition, String columnName) throws SQLException {

    }

    @Override
    public ArrayList<CustomerModel> readDatabase() throws SQLException {
        ArrayList<CustomerModel> cus = new ArrayList<>();
        try (Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `Customer`");
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                CustomerModel customerModel = createCustomerModelFromResultSet(customerModel);
                users.add(user);
            }
        } catch (SQLException e) {
            throw e;
        }
        return users;
    }

}
