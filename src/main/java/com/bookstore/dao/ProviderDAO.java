package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.ProviderModel;

public class ProviderDAO implements DAOInterface<ProviderModel> {
    public static ProviderDAO getInstance() {
        return new ProviderDAO();
    }

    // Helper method to create ProviderModel object from ResultSet
    private ProviderModel createProviderModelFromResultSet(ResultSet rs) throws SQLException {
        return new ProviderModel(
                rs.getString("providerID"),
                rs.getString("nameProvider"),
                rs.getString("gnrID"));
    }

    @Override
    public ArrayList<ProviderModel> readDatabase() throws SQLException {
        ArrayList<ProviderModel> providerModelsList = new ArrayList<>();
        try (
                Connection con = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `provider`"); // SQL Statement to execute
                ResultSet rs = pst.executeQuery() // Executing the SQL Statement
        ) {
            while (rs.next()) {
                ProviderModel providerModel = createProviderModelFromResultSet(rs); // Creating UserModel object from
                                                                                    // ResultSet
                providerModelsList.add(providerModel); // Adding UserModel object into ArrayList
            }
        } catch (SQLException e) {
            throw e;
        }
        return providerModelsList; // Returning ArrayList of UserModel objects
    }

    @Override
    public int insert(ProviderModel providerModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO `provider` (`providerID`, `nameProvider`, `gnrID`) VALUES (?,?,?)")) {
            pstmt.setString(1, providerModel.getProviderID()); // Setting the value of parameters in the query
            pstmt.setString(2, providerModel.getNameProvider());
            pstmt.setString(3, providerModel.getGnr_id());
            result = pstmt.executeUpdate(); // Return number of rows inserted
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public int update(ProviderModel providerModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection(); // Establishing the database connection
                PreparedStatement pstmt = conn.prepareStatement(
                        "UPDATE `provider` SET `nameProvider`=?, `gnrID`=? WHERE `providerID`=?")) {
            pstmt.setString(1, providerModel.getNameProvider()); // Setting the value of parameters in the query
            pstmt.setString(2, providerModel.getGnr_id());
            pstmt.setString(3, providerModel.getProviderID());
            result = pstmt.executeUpdate(); // Return number of rows updated
        } catch (SQLException ex) {
            throw ex;
        }
        return result;
    }

    @Override
    public int delete(String id) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection(); // Establishing the database connection
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM `provider` WHERE `providerID`=?");) {
            pstmt.setString(1, id);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public List<ProviderModel> searchByCondition(String condition, String columnName) throws SQLException {
        String query = "SELECT providerID, nameProvider, gnrID FROM provider WHERE " + columnName + " LIKE ?";

        List<ProviderModel> providerList = new ArrayList<>();

        try (Connection con = DatabaseConnect.getConnection();
                PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, "%" + condition + "%");
            ResultSet resultSet = pst.executeQuery();

            while (resultSet.next()) {
                ProviderModel provider = new ProviderModel();
                provider.setProviderID(resultSet.getString("providerID"));
                provider.setNameProvider(resultSet.getString("nameProvider"));
                provider.setGnr_id(resultSet.getString("gnrID"));
                providerList.add(provider);
            }
        } catch (SQLException e) {
            // Proper error handling
            throw e;
        }

        return providerList;
    }

    @Override
    public List<ProviderModel> searchByCondition(String condition) throws SQLException {
        // Build the SQL query with passed condition
        String query = "SELECT providerID, nameProvider, gnrID FROM provider WHERE " + condition;

        try (
                // Get a database connection
                Connection con = DatabaseConnect.getConnection();
                // Prepare the SQL statement with the built query
                PreparedStatement pst = con.prepareStatement(query);
                // Execute the SQL statement and get result set
                ResultSet resultSet = pst.executeQuery()) {

            // Create an ArrayList of providers to hold the retrieved ones
            ArrayList<ProviderModel> providerList = new ArrayList<>();

            // Loop through result set and retrieve provider data into ProviderModel class
            while (resultSet.next()) {
                ProviderModel provider = new ProviderModel();
                provider.setProviderID(resultSet.getString("providerID"));
                provider.setNameProvider(resultSet.getString("nameProvider"));
                provider.setGnr_id(resultSet.getString("gnrID"));
                providerList.add(provider);
            }

            // Return the ArrayList of providers that meet the search criteria
            return providerList;
        }
    }

}
