package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.GenreModel;

public class GenreDAO implements DAOInterface<GenreModel> {

    public static GenreDAO getInstance() {
        return new GenreDAO();
    }

    private GenreModel createGenreModelFromResultSet(ResultSet rs) throws SQLException {
        return new GenreModel(
                rs.getString("genreID"),
                rs.getString("nameGenre"),
                rs.getString("ISBN"));
    }

    @Override
    public ArrayList<GenreModel> readDatabase() throws SQLException {
        ArrayList<GenreModel> genreModelList = new ArrayList<>();
        try (
                Connection con = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `genre`"); // SQL Statement to execute
                ResultSet rs = pst.executeQuery() // Executing the SQL Statement
        ) {
            while (rs.next()) {
                GenreModel genreModel = createGenreModelFromResultSet(rs); // Creating GenreModel object from ResultSet
                genreModelList.add(genreModel); // Adding GenreModel object into ArrayList
            }
        } catch (SQLException e) {
            throw e;
        }
        return genreModelList; // Returning ArrayList of GenreModel objects
    }

    @Override
    public int insert(GenreModel genreModel) throws SQLException {
        int result = 0;
        try (
                // Get a database connection
                Connection con = DatabaseConnect.getConnection();
                // Prepare the SQL statement with the built query
                PreparedStatement pst = con
                        .prepareStatement("INSERT INTO `genre`(`genreID`, `nameGenre`, `ISBN`) VALUES (?, ?, ?)");) {
            // Set attribute values to the prepared statement
            pst.setString(1, genreModel.getGenreID());
            pst.setString(2, genreModel.getNameGenre());
            pst.setString(3, genreModel.getISBN());
            // Execute the SQL statement and get count of rows affected.
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return result; // Return the count of rows affected
    }

    @Override
    public int update(GenreModel genreModel) throws SQLException {
        int result = 0;
        try (
                // Get a database connection
                Connection con = DatabaseConnect.getConnection();
                // Prepare the SQL statement with the built query
                PreparedStatement pst = con
                        .prepareStatement("UPDATE `genre` SET `nameGenre`=?, `ISBN`=? WHERE `genreID`=?");) {
            // Set attribute values to the prepared statement
            pst.setString(1, genreModel.getNameGenre());
            pst.setString(2, genreModel.getISBN());
            pst.setString(3, genreModel.getGenreID());
            // Execute the SQL statement and get count of rows affected.
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return result; // Return the count of rows affected
    }

    @Override
    public int delete(String genreID) throws SQLException {
        int result = 0;
        try (
                // Get a database connection
                Connection con = DatabaseConnect.getConnection();
                // Prepare the SQL statement with the built query
                PreparedStatement pst = con
                        .prepareStatement("DELETE FROM `genre` WHERE `genreID`=?");) {
            // Set attribute values to the prepared statement
            pst.setString(1, genreID);
            // Execute the SQL statement and get count of rows affected.
            result = pst.executeUpdate();
        } catch (SQLException ex) {
            throw ex;
        }
        return result; // Return the count of rows affected
    }

    @Override
    public List<GenreModel> searchByCondition(String condition) throws SQLException {
        List<GenreModel> genreModelList = new ArrayList<>();
        try (
                Connection con = DatabaseConnect.getConnection();
                // Prepare the SQL statement with the built query
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `genre` WHERE " + condition);
                ResultSet rs = pst.executeQuery();) {
            while (rs.next()) {
                GenreModel genreModel = createGenreModelFromResultSet(rs);
                genreModelList.add(genreModel);
            }
        } catch (SQLException e) {
            throw e;
        }
        return genreModelList;
    }

    @Override
    public List<GenreModel> searchByCondition(String condition, String columnName) throws SQLException {
        List<GenreModel> genreModelList = new ArrayList<>();
        try (Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `genre` WHERE `" + columnName + "` = ?");) {
            pst.setString(1, condition);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                GenreModel genreModel = createGenreModelFromResultSet(rs);
                genreModelList.add(genreModel);
            }
        } catch (SQLException ex) {
            throw ex;
        }
        return genreModelList;
    }
}
