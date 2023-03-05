package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.WarehouseModel;

public class WarehouseDAO implements DAOInterface<WarehouseModel> {
    public static WarehouseDAO getInstance() {
        return new WarehouseDAO();
    }

    // Helper method to create UserModel object from ResultSet
    private WarehouseModel creatWarehouseModelFromResultSet(ResultSet rs) throws SQLException {
        return new WarehouseModel(
                rs.getString("warehouseID"),
                rs.getString("ISBN"));
    }

    @Override
    public ArrayList<WarehouseModel> readDatabase() throws SQLException {
        ArrayList<WarehouseModel> warehouseModelsList = new ArrayList<>();
        try (
                Connection con = DatabaseConnect.getConnection(); // Established connection with Database
                PreparedStatement pst = con.prepareStatement("SELECT * FROM `warehouse`"); // SQL Statement to execute
                ResultSet rs = pst.executeQuery() // Executing the SQL Statement
        ) {
            while (rs.next()) {
                WarehouseModel warehouseModel = creatWarehouseModelFromResultSet(rs); // Creating UserModel object from
                                                                                      // ResultSet
                warehouseModelsList.add(warehouseModel); // Adding UserModel object into ArrayList
            }
        } catch (SQLException e) {
            throw e;
        }
        return warehouseModelsList; // Returning ArrayList of UserModel objects
    }

    @Override
    public int insert(WarehouseModel warehouseModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "INSERT INTO `warehouse` (`warehouseID`, `ISBN`) VALUES (?,?)")) {
            pstmt.setString(1, warehouseModel.getWarehouseID());
            pstmt.setString(2, warehouseModel.getISBN());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public int update(WarehouseModel warehouseModel) throws SQLException {
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(
                        "UPDATE `warehouse` SET `ISBN`=? WHERE `warehouseID`=?")) {
            // Set parameters for prepared statement
            pstmt.setString(1, warehouseModel.getISBN());
            pstmt.setString(2, warehouseModel.getWarehouseID());
            // Execute update operation
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public int delete(String id) throws SQLException {
        // Initialize a variable to store the result of the delete operation
        int result = 0;
        try (
                Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("DELETE FROM `warehouse` WHERE `warehouseID`=?")) {
            // Set parameter for prepared statement
            pstmt.setString(1, id);
            // Execute delete operation
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    @Override
    public List<WarehouseModel> searchByCondition(String condition) throws SQLException {
        List<WarehouseModel> warehouseList = new ArrayList<>(); // Create empty list of warehouses
        String query = "SELECT * FROM `warehouse` WHERE " + condition; // Build the SQL query by concatenating string
        try (Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) { // Using try-with-resources

            try (ResultSet resultSet = pstmt.executeQuery()) { // Nested try-with-resources
                while (resultSet.next()) {
                    WarehouseModel warehouse = new WarehouseModel();
                    warehouse.setWarehouseID(resultSet.getString("warehouseID"));
                    warehouse.setISBN(resultSet.getString("warehouseName"));
                    warehouseList.add(warehouse);
                }
            }

            if (warehouseList.isEmpty()) {
                System.out.println("No records found for the given condition: " + condition);
            }
        }

        return warehouseList;
    }

    @Override
    public List<WarehouseModel> searchByCondition(String condition, String columnName) throws SQLException {
        // Build the SQL query with given condition and column name to search only for
        // warehouse data
        String query = "SELECT * FROM `warehouse` WHERE " + columnName + " LIKE ?";
        try (Connection conn = DatabaseConnect.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(query)) {
            // Set wildcarded value to the prepared statement
            pstmt.setString(1, "%" + condition + "%");
            // Execute the SQL statement and get result set
            try (ResultSet resultSet = pstmt.executeQuery()) {
                // Create an ArrayList of warehouses to hold the retrieved ones
                ArrayList<WarehouseModel> warehouseList = new ArrayList<>();
                // Loop through result set and retrieve warehouse data into WarehouseModel class
                while (resultSet.next()) {
                    WarehouseModel warehouse = new WarehouseModel();
                    warehouse.setWarehouseID(resultSet.getString("warehouseID"));
                    warehouse.setISBN(resultSet.getString("ISBN"));
                    warehouseList.add(warehouse);
                }
                // Print a message if no records are found for the given search criteria
                if (warehouseList.isEmpty()) {
                    System.out.println("No records found for the given condition: " + condition);
                }
                // Return the ArrayList of warehouses that meet the search criteria
                return warehouseList;
            }
        } catch (SQLException e) {
            throw e;
        }
    }

}
