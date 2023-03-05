package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.PublisherModel;

public class PublisherDAO implements DAOInterface<PublisherModel> {

    public static PublisherDAO getInstance() {
        return new PublisherDAO();
    }

    private PublisherModel createPublisherModelFromResultSet(ResultSet rs) throws SQLException {
        return new PublisherModel(
                rs.getString("location"),
                rs.getString("name"),
                rs.getString("ISBN"),
                rs.getString("publisher_id"));
    }

    @Override
    public ArrayList<PublisherModel> readDatabase() throws SQLException {
        ArrayList<PublisherModel> publisherModelList = new ArrayList<>();
        try (
                Connection con = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `publisher`"); // SQL Statement to execute
                ResultSet rs = pst.executeQuery() // Executing the SQL Statement
        ) {
            while (rs.next()) {
                PublisherModel publisherModel = createPublisherModelFromResultSet(rs); // Creating PublisherModel object
                                                                                       // from
                                                                                       // ResultSet
                publisherModelList.add(publisherModel); // Adding PublisherModel object into ArrayList
            }
        } catch (SQLException e) {
            throw e;
        }
        return publisherModelList; // Returning ArrayList of PublisherModel objects
    }

    @Override
    public int insert(PublisherModel publisherModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO `publisher` (`location`, `name`, `ISBN`, `publisher_id`) VALUES (?,?,?,?)")) {
            pstmt.setString(1, publisherModel.getLocation()); // Setting the value of parameters in the query
            pstmt.setString(2, publisherModel.getName());
            pstmt.setString(3, publisherModel.getISBN());
            pstmt.setString(4, publisherModel.getPublisherID());
            result = pstmt.executeUpdate(); // Return number of rows inserted
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public int update(PublisherModel publisherModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pstmt = conn.prepareStatement(
                        "UPDATE `publisher` SET `location`=?, `name`=?, `ISBN`=? WHERE `publisher_id`=?")) {
            // Setting the value of parameters in the query
            pstmt.setString(1, publisherModel.getLocation());
            pstmt.setString(2, publisherModel.getName());
            pstmt.setString(3, publisherModel.getISBN());
            pstmt.setString(4, publisherModel.getPublisherID());
            result = pstmt.executeUpdate(); // Return number of rows updated
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public int delete(String publisherID) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection(); // Establish connection with Database
                PreparedStatement pstmt = conn.prepareStatement(
                        "DELETE FROM `publisher` WHERE `publisher_id`=?")) {
            pstmt.setString(1, publisherID); // Set the value of parameters in the query
            result = pstmt.executeUpdate(); // Return number of rows deleted
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public List<PublisherModel> searchByCondition(String condition) throws SQLException {
        List<PublisherModel> publishers = new ArrayList<>();
        try (
                Connection conn = DatabaseConnect.getConnection(); // Get a database connection
                PreparedStatement pstmt = conn.prepareStatement(
                        "SELECT `location`, `name`, `ISBN`, `publisher_id` FROM `publisher` WHERE " + condition)) {
            ResultSet rs = pstmt.executeQuery(); // Execute the SQL statement and get the result set of publishers
            while (rs.next()) {
                PublisherModel publisher = new PublisherModel(rs.getString("location"), rs.getString("name"),
                        rs.getString("ISBN"), rs.getString("publisher_id"));
                publishers.add(publisher);
            }
            // Print a message if no records are found for the given search criteria
            if (publishers.isEmpty()) {
                System.out.println("No records found for the given condition: " + condition);
            }
        } catch (SQLException e) {
            throw e;
        }
        return publishers;
    }

    @Override
    public List<PublisherModel> searchByCondition(String columnName, String condition) throws SQLException {
        String query = "SELECT * FROM `publisher` WHERE " + columnName + " LIKE ?";
        try (
                Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query);) {
            pstmt.setString(1, "%" + condition + "%");
            ResultSet rs = pstmt.executeQuery();
            List<PublisherModel> publishers = new ArrayList<>();
            while (rs.next()) {
                PublisherModel publisher = new PublisherModel(
                        rs.getString("location"),
                        rs.getString("name"),
                        rs.getString("ISBN"),
                        rs.getString("publisher_id"));
                publishers.add(publisher);
            }
            if (publishers.isEmpty()) {
                System.out.println("No records found for the given condition: " + condition);
            }
            return publishers;
        } catch (SQLException e) {
            throw e; // pass on the SQL exception to the caller
        }
    }

}
