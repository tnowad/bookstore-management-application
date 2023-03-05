package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.InvoiceModel;

public class InvoiceDAO implements DAOInterface<InvoiceModel> {

    public static InvoiceDAO getInstance() {
        return new InvoiceDAO();
    }

    private InvoiceModel createInvoiceModelFromResultSet(ResultSet rs) throws SQLException {
        return new InvoiceModel(
                rs.getDate("InvoiceDate").toLocalDate(),
                rs.getBoolean("PaymentStatus"),
                rs.getFloat("TotalAmountDue"),
                rs.getString("InvoiceID"),
                rs.getString("GiftcardID"));
    }

    @Override
    public ArrayList<InvoiceModel> readDatabase() throws SQLException {
        ArrayList<InvoiceModel> invoices = new ArrayList<>();
        try (Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `invoice`");
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                InvoiceModel invoice = createInvoiceModelFromResultSet(rs);
                invoices.add(invoice);
            }
        }
        return invoices;
    }

    @Override
    public int insert(InvoiceModel invoiceModel) throws SQLException {
        int result = 0;
        try (
                Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement(
                        "INSERT INTO invoice (InvoiceDate, PaymentStatus, TotalAmountDue, InvoiceID, GiftcardID) VALUES (?, ?, ?, ?, ?)")) {
            pst.setObject(1, invoiceModel.getInvoiceDate(), Types.TIMESTAMP);
            pst.setBoolean(2, invoiceModel.isPaymentStatus());
            pst.setFloat(3, invoiceModel.getTotalAmountDue());
            pst.setString(4, invoiceModel.getInvoiceID());
            pst.setString(5, invoiceModel.getGiftcardID());
            result = pst.executeUpdate();
        }
        return result;
    }

    @Override
    public int update(InvoiceModel invoiceModel) throws SQLException {
        try (
                Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pst = conn.prepareStatement(
                        "UPDATE `invoice` SET    `InvoiceDate` =?, `PaymentStatus` =?, `TotalAmountDue` =?, `InvoiceID` =?, `GiftcardID` =? WHERE `InvoiceID` =?")) {
            pst.setObject(1, invoiceModel.getInvoiceDate(), Types.TIMESTAMP);
            pst.setBoolean(2, invoiceModel.isPaymentStatus());
            pst.setFloat(3, invoiceModel.getTotalAmountDue());
            pst.setString(4, invoiceModel.getInvoiceID());
            pst.setString(5, invoiceModel.getGiftcardID());
            return pst.executeUpdate();
        }
    }

    @Override
    public int delete(String invoiceID) throws SQLException {
        if (invoiceID == null || invoiceID.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        try (
                Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement("DELETE FROM `invoice` WHERE `InvoiceID` = ?")) {
            pst.setString(1, invoiceID);
            return pst.executeUpdate();
        }
    }

    @Override
    public List<InvoiceModel> searchByCondition(String condition) throws SQLException {
        String query = "SELECT * FROM `invoice` WHERE " + condition;
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query); // Prepares a SELECT query with a WHERE clause
                ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
            List<InvoiceModel> invoiceList = new ArrayList<>();
            while (rs.next()) { // Loops through the ResultSet and creates BookModel objects from each row
                InvoiceModel invoiceModel = createInvoiceModelFromResultSet(rs);
                invoiceList.add(invoiceModel); // Adds each BookModel object to the ArrayList
            }
            if (invoiceList.isEmpty()) { // Prints a message if no records were found
                System.out.println("No records found for the given condition: " + condition);
            }
            return invoiceList; // Returns the ArrayList of BookModels
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }

    @Override
    public List<InvoiceModel> searchByCondition(String condition, String columnName) throws SQLException {
        String query = "SELECT * FROM `invoice` WHERE " + columnName + " LIKE ?";
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query)) { // Prepares a SELECT query with a WHERE clause
                                                                        // using a LIKE operator
            pst.setString(1, "%" + condition + "%"); // Sets the value of the placeholder in the query with the given
                                                     // condition
            try (ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
                List<InvoiceModel> invoiceList = new ArrayList<>();
                while (rs.next()) { // Loops through the ResultSet and creates InvoiceModel objects from each row
                    InvoiceModel invoiceModel = createInvoiceModelFromResultSet(rs);
                    invoiceList.add(invoiceModel); // Adds each InvoiceModel object to the List
                }
                if (invoiceList.isEmpty()) { // Throws a SQLException if no records were found
                    throw new SQLException("No records found for the given condition: " + condition);
                }
                return invoiceList; // Returns the List of InvoiceModels
            }
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }
}