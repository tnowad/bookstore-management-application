package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.GoodNotesReceiptDetailModel;

public class GoodNotesReceiptDetailDAO implements DAOInterface<GoodNotesReceiptDetailModel> {

    public static GoodNotesReceiptDAO getInstance() {
        return new GoodNotesReceiptDAO();
    }

    private GoodNotesReceiptDetailModel createDGNRFromResultSet(ResultSet rs) throws SQLException {
        return new GoodNotesReceiptDetailModel(
                rs.getString("dgnrId"),
                rs.getString("productId"),
                rs.getInt("amount"),
                rs.getFloat("price"));
    }

    @Override
    public ArrayList<GoodNotesReceiptDetailModel> readDatabase() throws SQLException {
        ArrayList<GoodNotesReceiptDetailModel> dgnrList = new ArrayList<>();
        try (
                Connection con = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `GoodNotesReceiptDeatail`"); // SQL
                                                                                                         // Statement to
                // execute
                ResultSet rs = pst.executeQuery() // Executing the SQL Statement
        ) {
            while (rs.next()) {
                GoodNotesReceiptDetailModel goodNotesReceiptDeatailModel = createDGNRFromResultSet(rs); // Creating
                                                                                                        // UserModel
                                                                                                        // object
                                                                                                        // from
                // ResultSet
                dgnrList.add(goodNotesReceiptDeatailModel); // Adding UserModel object into ArrayList
            }
        } catch (SQLException e) {
            throw e;
        }
        return dgnrList; // Returning ArrayList of UserModel objects
    }

    @Override
    public int insert(GoodNotesReceiptDetailModel goodNotesReceiptDeatailModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO `GoodNotesReceiptDetail` (`dgnrId`, `productId`, `amount`, `price`) VALUES (?,?,?,?)")) {
            pstmt.setString(1, goodNotesReceiptDeatailModel.getDgnrId()); // Setting the value of parameters in the
                                                                          // query
            pstmt.setString(2, goodNotesReceiptDeatailModel.getProductId());
            pstmt.setInt(3, goodNotesReceiptDeatailModel.getAmount());
            pstmt.setFloat(4, goodNotesReceiptDeatailModel.getPrice());
            result = pstmt.executeUpdate(); // Return number of rows inserted
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public int update(GoodNotesReceiptDetailModel goodNotesReceiptDeatailModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "UPDATE `GoodNotesReceiptDetail` SET `productId`=?, `amount`=?, `price`=? WHERE `dgnrId`=?")) {
            pstmt.setString(1, goodNotesReceiptDeatailModel.getProductId());
            pstmt.setInt(2, goodNotesReceiptDeatailModel.getAmount());
            pstmt.setFloat(3, goodNotesReceiptDeatailModel.getPrice());
            pstmt.setString(4, goodNotesReceiptDeatailModel.getDgnrId());

            result = pstmt.executeUpdate(); // Return number of rows updated
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    @Override
    public int delete(String dgnrId) throws SQLException {
        try (
                Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement("DELETE FROM `GoodNotesReceiptDetail` WHERE `dgnrId`=?")) {
            pst.setString(1, dgnrId);
            return pst.executeUpdate();
        }
    }

    @Override
    public List<GoodNotesReceiptDetailModel> searchByCondition(String condition) throws SQLException {
        String query = "SELECT * FROM `GoodNotesReceiptDetail` WHERE " + condition;
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query); // Prepares a SELECT query with a WHERE clause
                ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
            List<GoodNotesReceiptDetailModel> dgnrList = new ArrayList<>();
            while (rs.next()) { // Loops through the ResultSet and creates BookModel objects from each row
                GoodNotesReceiptDetailModel goodNotesReceiptDetailModel = createDGNRFromResultSet(rs);
                dgnrList.add(goodNotesReceiptDetailModel); // Adds each BookModel object to the ArrayList
            }
            if (dgnrList.isEmpty()) { // Prints a message if no records were found
                System.out.println("No records found for the given condition: " + condition);
            }
            return dgnrList; // Returns the ArrayList of BookModels
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }

    @Override
    public List<GoodNotesReceiptDetailModel> searchByCondition(String condition, String columnName)
            throws SQLException {
        String query = "SELECT * FROM `GoodNotesReceiptDetail` WHERE " + columnName + " LIKE ?";
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query)) { // Prepares a SELECT query with a WHERE clause
                                                                        // using a LIKE operator
            pst.setString(1, "%" + condition + "%"); // Sets the value of the placeholder in the query with the given
                                                     // condition
            try (ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
                List<GoodNotesReceiptDetailModel> dgnrList = new ArrayList<>();
                while (rs.next()) { // Loops through the ResultSet and creates InvoiceModel objects from each row
                    GoodNotesReceiptDetailModel goodNotesReceiptDeatailModel = createDGNRFromResultSet(rs);
                    dgnrList.add(goodNotesReceiptDeatailModel); // Adds each InvoiceModel object to the List
                }
                if (dgnrList.isEmpty()) { // Throws a SQLException if no records were found
                    throw new SQLException("No records found for the given condition: " + condition);
                }
                return dgnrList; // Returns the List of InvoiceModels
            }
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }

}
