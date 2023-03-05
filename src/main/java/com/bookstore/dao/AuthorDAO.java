package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.AuthorModel;

public class AuthorDAO implements DAOInterface<AuthorModel> {

    public static AuthorDAO getInstance() {
        return new AuthorDAO();
    }

    private AuthorModel createAuthorModelFromResultSet(ResultSet rs) throws SQLException {
        return new AuthorModel(
                rs.getString("name"),
                rs.getString("nationality"),
                rs.getString("authorID"),
                rs.getString("ISBN"));
    }

    @Override
    public ArrayList<AuthorModel> readDatabase() throws SQLException {
        ArrayList<AuthorModel> authorModelsList = new ArrayList<>();
        try (
                Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `author`");
                ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                AuthorModel authorModel = createAuthorModelFromResultSet(rs);
                authorModelsList.add(authorModel);
            }
        } catch (SQLException e) {
            throw e;
        }
        return authorModelsList;
    }

    @Override
    public int insert(AuthorModel authorModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO `author` (`name`, `nationality`, `authorID`, `ISBN`) VALUES (?,?,?,?)")) {
            pstmt.setString(1, authorModel.getName()); // Setting the value of parameters in the query
            pstmt.setString(2, authorModel.getNationality());
            pstmt.setString(3, authorModel.getAuthorID());
            pstmt.setString(4, authorModel.getISBN());
            result = pstmt.executeUpdate(); // Return number of rows inserted
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public int update(AuthorModel authorModel) throws SQLException {
        try (
                Connection conn = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pst = conn.prepareStatement(
                        "UPDATE `author` SET `name` = ?, `nationality` = ?, `authorID` =?, `ISBN`=? WHERE `authorID` = ?")) {
            pst.setString(1, authorModel.getName()); // Setting the value of parameters in the query
            pst.setString(2, authorModel.getNationality());
            pst.setString(3, authorModel.getAuthorID());
            pst.setString(4, authorModel.getISBN());
            return pst.executeUpdate(); // Returns number of rows updated
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public int delete(String id) throws SQLException {
        try (
                Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pst = conn.prepareStatement("DELETE FROM `author` WHERE `authorID` = ?")) {
            pst.setString(1, id); // set parameter value
            return pst.executeUpdate(); // Returns number of rows deleted
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<AuthorModel> searchByCondition(String condition) throws SQLException {
        // Build the SQL query with passed condition for searching author data
        String query = "SELECT * FROM `auther` WHERE " + condition;

        try (
                // Get a database connection
                Connection con = DatabaseConnect.getConnection();
                // Prepare the SQL statement with the built query
                PreparedStatement pst = con.prepareStatement(query);
                // Execute the SQL statement and get result set
                ResultSet resultSet = pst.executeQuery()) {

            // Create an ArrayList of authors to hold the retrieved ones
            ArrayList<AuthorModel> authorList = new ArrayList<>();

            // Loop through result set and retrieve author data into AuthorModel class
            while (resultSet.next()) {
                AuthorModel author = new AuthorModel();
                author.setName(resultSet.getString("name"));
                author.setNationality(resultSet.getString("nationality"));
                author.setAuthorID(resultSet.getString("authorID"));
                author.setISBN(resultSet.getString("ISBN"));
                authorList.add(author);
            }

            // Print a message if no records are found for the given search criteria
            if (authorList.isEmpty()) {
                System.out.println("No records found for the given condition: " + condition);
            }

            // Return the ArrayList of authors that meet the search criteria
            return authorList;
        }
    }

    @Override
    public List<AuthorModel> searchByCondition(String condition, String columnName) throws SQLException {
        // Build the SQL query with passed condition and column name for searching
        // author data
        String query = "SELECT * FROM `author` WHERE `" + columnName + "` = ?";

        try (
                // Get a database connection
                Connection con = DatabaseConnect.getConnection();
                // Prepare the SQL statement with the built query
                PreparedStatement pst = con.prepareStatement(query);) {
            // Set the parameter for the column value in the query
            pst.setString(1, condition);

            // Execute the SQL statement and get result set
            ResultSet resultSet = pst.executeQuery();

            // Create an ArrayList of authors to hold the retrieved ones
            ArrayList<AuthorModel> authorList = new ArrayList<>();

            // Loop through result set and retrieve author data into AuthorModel class
            while (resultSet.next()) {
                AuthorModel author = new AuthorModel();
                author.setName(resultSet.getString("name"));
                author.setNationality(resultSet.getString("nationality"));
                author.setAuthorID(resultSet.getString("authorID"));
                author.setISBN(resultSet.getString("ISBN"));
                authorList.add(author);
            }

            // Print a message if no records are found for the given search criteria
            if (authorList.isEmpty()) {
                System.out.println("No records found for the given condition: " + condition);
            }

            // Return the ArrayList of authors that meet the search criteria
            return authorList;
        }
    }

}
