package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.PaymentModel;

public class PaymentDAO implements DAOInterface<PaymentModel> {

    public static PaymentDAO getInstance() {
        return new PaymentDAO();
    }

    private PaymentModel createPaymentModelFromResultSet(ResultSet rs) throws SQLException {
        return new PaymentModel(
                rs.getString("paymentId"),
                rs.getString("orderId"),
                rs.getDate("paymentDate"),
                rs.getFloat("paymentAmount"));
    }

    @Override
    public ArrayList<PaymentModel> readDatabase() throws SQLException {
        ArrayList<PaymentModel> paymentList = new ArrayList<>();
        try (Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `payment`");
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                PaymentModel paymentModel = createPaymentModelFromResultSet(rs);
                paymentList.add(paymentModel);
            }
        }
        return paymentList;
    }

    @Override
    public int insert(PaymentModel paymentModel) throws SQLException {
        int result = 0;
        try (
                Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement(
                        "INSERT INTO payment (paymentId, orderId, paymentDate, PaymentAmount) VALUES (?, ?, ?, ?)")) {
            pst.setString(1, paymentModel.getPaymentId());
            pst.setString(2, paymentModel.getOrderId());
            // convert java.util.Date to java.sql.Date using getTime() and setTime() methods
            pst.setObject(3, paymentModel.getPaymentDate().getTime());
            pst.setFloat(4, paymentModel.getPaymentAmount());
            result = pst.executeUpdate();
        } catch (SQLException sqle) {
            // Handle the exception appropriately or re-throw it for higher-level error
            // handlers.
            sqle.printStackTrace();
        }
        return result;
    }

    @Override
    public int update(PaymentModel paymentModel) throws SQLException {
        try (
                Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pst = conn.prepareStatement(
                        "UPDATE `payment` SET `paymentId` =?, `orderId` =?, `paymentDate` =?, `paymentAmount` =?, `GiftcardID` =? WHERE `InvoiceID` =?")) {
            pst.setObject(1, paymentModel.getPaymentDate(), Types.TIMESTAMP);
            pst.setFloat(2, paymentModel.getPaymentAmount());
            pst.setString(3, paymentModel.getOrderId());
            pst.setString(4, paymentModel.getPaymentId());
            return pst.executeUpdate();
        }
    }

    @Override
    public int delete(String paymentId) throws SQLException {
        if (paymentId == null || paymentId.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        try (
                Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pst = conn.prepareStatement(
                        "DELETE FROM `payment` WHERE `paymentId` = ?")) {
            pst.setString(1, paymentId);
            return pst.executeUpdate();
        }
    }

    @Override
    public List<PaymentModel> searchByCondition(String condition) throws SQLException {
        String query = "SELECT * FROM `payment` WHERE " + condition;
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query); // Prepares a SELECT query with a WHERE clause
                ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
            List<PaymentModel> paymentList = new ArrayList<>();
            while (rs.next()) { // Loops through the ResultSet and creates BookModel objects from each row
                PaymentModel paymentModel = createPaymentModelFromResultSet(rs);
                paymentList.add(paymentModel); // Adds each BookModel object to the ArrayList
            }
            if (paymentList.isEmpty()) { // Prints a message if no records were found
                System.out.println("No records found for the given condition: " + condition);
            }
            return paymentList; // Returns the ArrayList of BookModels
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }

    @Override
    public List<PaymentModel> searchByCondition(String condition, String columnName) throws SQLException {
        String query = "SELECT * FROM `payment` WHERE " + columnName + " LIKE ?";
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query)) { // Prepares a SELECT query with a WHERE clause
                                                                        // using a LIKE operator
            pst.setString(1, "%" + condition + "%"); // Sets the value of the placeholder in the query with the given
                                                     // condition
            try (ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
                List<PaymentModel> paymentList = new ArrayList<>();
                while (rs.next()) { // Loops through the ResultSet and creates InvoiceModel objects from each row
                    PaymentModel paymentModel = createPaymentModelFromResultSet(rs);
                    paymentList.add(paymentModel); // Adds each InvoiceModel object to the List
                }
                if (paymentList.isEmpty()) { // Throws a SQLException if no records were found
                    throw new SQLException("No records found for the given condition: " + condition);
                }
                return paymentList; // Returns the List of InvoiceModels
            }
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }

}
