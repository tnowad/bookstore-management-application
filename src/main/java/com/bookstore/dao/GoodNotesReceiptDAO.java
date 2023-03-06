package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.GoodNotesReceiptModel;

public class GoodNotesReceiptDAO implements DAOInterface<GoodNotesReceiptModel> {

    public static GoodNotesReceiptDAO getInstance() {
        return new GoodNotesReceiptDAO();
    }

    // Helper method to create UserModel object from ResultSet
    private GoodNotesReceiptModel createGNRFromResultSet(ResultSet rs) throws SQLException {
        return new GoodNotesReceiptModel(
                rs.getDate("importDate"),
                rs.getString("gnrId"));
    }

    @Override
    public ArrayList<GoodNotesReceiptModel> readDatabase() throws SQLException {
        ArrayList<GoodNotesReceiptModel> gnrList = new ArrayList<>();
        try (
                Connection con = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `goodnotesreceipt`"); // SQL Statement to
                                                                                                  // execute
                ResultSet rs = pst.executeQuery() // Executing the SQL Statement
        ) {
            while (rs.next()) {
                GoodNotesReceiptModel user = createGNRFromResultSet(rs); // Creating UserModel object from
                                                                         // ResultSet
                gnrList.add(user); // Adding UserModel object into ArrayList
            }
        } catch (SQLException e) {
            throw e;
        }
        return gnrList; // Returning ArrayList of UserModel objects
    }

    @Override
    public int insert(GoodNotesReceiptModel gNotesReceiptModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO `goodnotesreceipt` (`gnrId`, `importDate`) VALUES (?,?)")) {
            pstmt.setString(1, gNotesReceiptModel.getGnrId()); // Setting the value of parameters in the query
            pstmt.setDate(2, gNotesReceiptModel.getImportDate());
            result = pstmt.executeUpdate(); // Return number of rows inserted
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public int update(GoodNotesReceiptModel goodNotesReceiptModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pstmt = conn.prepareStatement(
                        "UPDATE `goodnotesreceipt` SET `importDate`=? WHERE `gnrId`=?"); // Prepares an UPDATE query
                                                                                         // with placeholders
        ) {
            pstmt.setDate(1, goodNotesReceiptModel.getImportDate()); // Sets the value of the first parameter in the
                                                                     // query
            pstmt.setString(2, goodNotesReceiptModel.getGnrId()); // Sets the value of the second parameter in the query
            result = pstmt.executeUpdate(); // Executes the UPDATE query and returns the number of rows updated
        } catch (SQLException e) { // Catches any SQLExceptions that occur
            throw e;
        }
        return result; // Returns the number of rows updated
    }

    @Override
    public int delete(String gnrId) throws SQLException {
        try (
                Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement("DELETE FROM `goodnotesreceipt` WHERE `gnrId`=?")) {
            pst.setString(1, gnrId);
            return pst.executeUpdate();
        }
    }

    @Override
    public List<GoodNotesReceiptModel> searchByCondition(String condition) throws SQLException {
        String query = "SELECT * FROM `goodnotesreceipt` WHERE " + condition;
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query); // Prepares a SELECT query with a WHERE clause
                ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
            List<GoodNotesReceiptModel> gnrList = new ArrayList<>();
            while (rs.next()) { // Loops through the ResultSet and creates BookModel objects from each row
                GoodNotesReceiptModel goodNotesReceiptModel = createGNRFromResultSet(rs);
                gnrList.add(goodNotesReceiptModel); // Adds each BookModel object to the ArrayList
            }
            if (gnrList.isEmpty()) { // Prints a message if no records were found
                System.out.println("No records found for the given condition: " + condition);
            }
            return gnrList; // Returns the ArrayList of BookModels
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }

    @Override
    public List<GoodNotesReceiptModel> searchByCondition(String condition, String columnName) throws SQLException {
        String query = "SELECT * FROM `goodnotesreceipt` WHERE " + columnName + " LIKE ?";
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query)) { // Prepares a SELECT query with a WHERE clause
                                                                        // using a LIKE operator
            pst.setString(1, "%" + condition + "%"); // Sets the value of the placeholder in the query with the given
                                                     // condition
            try (ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
                List<GoodNotesReceiptModel> invoiceList = new ArrayList<>();
                while (rs.next()) { // Loops through the ResultSet and creates InvoiceModel objects from each row
                    GoodNotesReceiptModel invoiceModel = createGNRFromResultSet(rs);
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
