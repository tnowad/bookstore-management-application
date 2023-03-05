package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.OrderModel;

public class OrderDAO implements DAOInterface<OrderModel> {

    public static OrderDAO getInstance() {
        return new OrderDAO();
    }

    private OrderModel createOrderModelFromResultSet(ResultSet rs) throws SQLException {
        return new OrderModel(
                rs.getString("shipppingInformation"),
                rs.getDate("orderDate"),
                rs.getString("invoiceID"),
                rs.getString("ISBN"),
                rs.getString("userID"));
    }

    @Override
    public ArrayList<OrderModel> readDatabase() throws SQLException {
        ArrayList<OrderModel> orderList = new ArrayList<>();
        try (
                Connection con = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `Order`"); // SQL Statement to execute
                ResultSet rs = pst.executeQuery() // Executing the SQL Statement
        ) {
            while (rs.next()) {
                OrderModel orderModel = createOrderModelFromResultSet(rs); // Creating UserModel object from ResultSet
                orderList.add(orderModel); // Adding OrderModel object into ArrayList
            }
        } catch (SQLException e) {
            throw e;
        }
        return orderList; // Returning ArrayList of OrderModel objects
    }

    @Override
    public int insert(OrderModel e) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    @Override
    public int update(OrderModel e) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public int delete(String id) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<OrderModel> searchByCondition(String condition) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
    }

    @Override
    public List<OrderModel> searchByCondition(String condition, String columnName) throws SQLException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByCondition'");
    }

}
