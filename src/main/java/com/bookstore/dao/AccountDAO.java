package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.AccountModel;

public class AccountDAO implements DAOInterface<AccountModel> {

    public static AccountDAO getInstance() {
        return new AccountDAO();
    }

    private AccountModel createAccountModelFromResultSet(ResultSet rs) throws SQLException {
        return new AccountModel(
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("status"),
                rs.getString("accountId"));
    }

    @Override
    public ArrayList<AccountModel> readDatabase() throws SQLException {
        ArrayList<AccountModel> accountList = new ArrayList<>();
        // try-with-resources statement will automatically close Connection,
        // PreparedStatement and ResultSet objects.
        try (
                Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `account`");
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                AccountModel accountModel = createAccountModelFromResultSet(rs);
                accountList.add(accountModel);
            }
        }
        return accountList;
    }

    @Override
    public int insert(AccountModel accountModel) throws SQLException {
        try (Connection conn = DatabaseConnect.getConnection()) { // Establish connection with database using
                                                                  // try-with-resources
            String query = "INSERT INTO `account` (`accountId`, `username`, `password`, `status`) VALUES (?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, accountModel.getAccountID());
                pstmt.setString(2, accountModel.getUsername());
                pstmt.setString(3, accountModel.getPassword());
                pstmt.setString(4, accountModel.getStatus());

                return pstmt.executeUpdate(); // return number of rows affected by the update
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public int update(AccountModel accountModel) throws SQLException {
        try (Connection conn = DatabaseConnect.getConnection()) {
            String query = "UPDATE `account` SET `username`=?, `password`=?, `status`=? WHERE `accountId`=?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, accountModel.getUsername());
                pstmt.setString(2, accountModel.getPassword());
                pstmt.setString(3, accountModel.getStatus());
                pstmt.setString(4, accountModel.getAccountID());

                return pstmt.executeUpdate();
            }
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public int delete(String accountId) throws SQLException {
        try (
                Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement("DELETE FROM `account` WHERE `accountId`=?")) {
            pst.setString(1, accountId);
            return pst.executeUpdate();
        }
    }

    @Override
    public List<AccountModel> searchByCondition(String condition) throws SQLException {
        String query = "SELECT * FROM `account` WHERE " + condition;
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query); // Prepares a SELECT query with a WHERE clause
                ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
            List<AccountModel> accountList = new ArrayList<>();
            while (rs.next()) { // Loops through the ResultSet and creates BookModel objects from each row
                AccountModel accountModel = createAccountModelFromResultSet(rs);
                accountList.add(accountModel); // Adds each BookModel object to the ArrayList
            }
            if (accountList.isEmpty()) { // Prints a message if no records were found
                System.out.println("No records found for the given condition: " + condition);
            }
            return accountList; // Returns the ArrayList of BookModels
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }

    @Override
    public List<AccountModel> searchByCondition(String condition, String columnName) throws SQLException {
        String query = "SELECT * FROM `account` WHERE " + columnName + " LIKE ?";
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query)) { // Prepares a SELECT query with a WHERE clause
                                                                        // using a LIKE operator
            pst.setString(1, "%" + condition + "%"); // Sets the value of the placeholder in the query with the given
                                                     // condition
            try (ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
                List<AccountModel> accountList = new ArrayList<>();
                while (rs.next()) { // Loops through the ResultSet and creates InvoiceModel objects from each row
                    AccountModel accountModel = createAccountModelFromResultSet(rs);
                    accountList.add(accountModel); // Adds each InvoiceModel object to the List
                }
                if (accountList.isEmpty()) { // Throws a SQLException if no records were found
                    throw new SQLException("No records found for the given condition: " + condition);
                }
                return accountList; // Returns the List of InvoiceModels
            }
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }
}