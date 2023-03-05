// Add the following lines to import required packages and classes
package com.bookstore.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.BookModel;

public class BookDAO implements DAOInterface<BookModel> {
    // A static getInstance method that returns a new instance of BookDAO
    public static BookDAO getInstance() {
        return new BookDAO();
    }

    // Creates a BookModel object from the ResultSet returned by a SELECT query
    private BookModel createBookModelFromResultSet(ResultSet rs) throws SQLException {
        return new BookModel(
                rs.getString("Title"),
                rs.getString("ISBN"),
                rs.getBoolean("Status"),
                rs.getDate("PublicationDate"),
                rs.getInt("Quantity"),
                rs.getInt("Quantity_in_stock"),
                rs.getFloat("Price"));
    }

    // Read all records from the book table
    @Override
    public ArrayList<BookModel> readDatabase() throws SQLException {
        ArrayList<BookModel> books = new ArrayList<BookModel>();
        try (
                Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement("SELECT * FROM `book`"); // Prepares a SELECT query
                ResultSet rs = pst.executeQuery();) { // Executes the SELECT query
            while (rs.next()) { // Loops through the ResultSet and creates BookModel objects from each row
                BookModel book = createBookModelFromResultSet(rs);
                books.add(book); // Adds each BookModel object to the ArrayList
            }
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
        return books; // Returns the ArrayList of BookModels
    }

    // Updates an existing record in the book table
    @Override
    public int update(BookModel book) throws SQLException {
        try (
                Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(
                        "UPDATE `book` SET `title` = ?, `ISBN` = ?, `Status` =?, `PublicationDate` =?, `Quantity` =?, `Quantity_in_stock` =?, `Price` =? WHERE `id` =?")) { // Prepares
                                                                                                                                                                            // an
                                                                                                                                                                            // UPDATE
                                                                                                                                                                            // query
            // Sets the values of the placeholders in the query with data from the BookModel
            // object
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getISBN());
            pst.setBoolean(3, book.getStatus());
            pst.setDate(4, book.getPublicationDate());
            pst.setInt(5, book.getQuantity());
            pst.setInt(6, book.getQuantityinStock());
            pst.setFloat(7, book.getPrice());
            pst.setString(8, book.getISBN());
            return pst.executeUpdate(); // Executes the UPDATE query and returns the number of affected rows
        }
    }

    // Deletes a record from the book table with the specified id
    @Override
    public int delete(String isbn) throws SQLException {
        if (isbn == null || isbn.isEmpty()) { // Throws an IllegalArgumentException if the id is null or empty
            throw new IllegalArgumentException("ID cannot be null or empty");
        }
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement("DELETE FROM `book` WHERE `isbn` = ?")) { // Prepares a
                                                                                                        // DELETE query
            pst.setString(1, isbn);
            return pst.executeUpdate(); // Executes the DELETE query and returns the number of affected rows
        }
    }

    // Searches the book table for records that match a given condition
    @Override
    public List<BookModel> searchByCondition(String condition) throws SQLException {
        String query = "SELECT * FROM `book` WHERE " + condition;
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query); // Prepares a SELECT query with a WHERE clause
                ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
            ArrayList<BookModel> books = new ArrayList<>();
            while (rs.next()) { // Loops through the ResultSet and creates BookModel objects from each row
                BookModel book = createBookModelFromResultSet(rs);
                books.add(book); // Adds each BookModel object to the ArrayList
            }
            if (books.isEmpty()) { // Prints a message if no records were found
                System.out.println("No records found for the given condition: " + condition);
            }
            return books; // Returns the ArrayList of BookModels
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }

    // Searches the book table for records where the given column contains the given
    // condition
    @Override
    public List<BookModel> searchByCondition(String condition, String columnName) throws SQLException {
        String query = "SELECT * FROM `book` WHERE " + columnName + " LIKE ?";
        try (Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
                PreparedStatement pst = conn.prepareStatement(query)) { // Prepares a SELECT query with a WHERE clause
                                                                        // using a LIKE operator
            pst.setString(1, "%" + condition + "%"); // Sets the value of the placeholder in the query with the given
                                                     // condition
            try (ResultSet rs = pst.executeQuery()) { // Executes the SELECT query
                ArrayList<BookModel> books = new ArrayList<>();
                while (rs.next()) { // Loops through the ResultSet and creates BookModel objects from each row
                    BookModel book = createBookModelFromResultSet(rs);
                    books.add(book); // Adds each BookModel object to the ArrayList
                }
                if (books.isEmpty()) { // Throws a SQLException if no records were found
                    throw new SQLException("No records found for the given condition: " + condition);
                }
                return books; // Returns the ArrayList of BookModels
            }
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        }
    }

    // Inserts a new record into the book table with the values from the given
    // BookModel object
    @Override
    public int insert(BookModel book) throws SQLException {
        Connection conn = DatabaseConnect.getConnection(); // Establishes a connection to the database
        try (PreparedStatement pst = conn.prepareStatement(
                "INSERT INTO `book` (`isbn`,`status`, `title`, `publication_date`, `price`, `quantity_in_stock`, `quantity`) VALUES (?, ?, ?, ?, ?, ?, ?)")) { // Prepares
                                                                                                                                                               // an
                                                                                                                                                               // INSERT
                                                                                                                                                               // query
            // Sets the values of the placeholders in the query with data from the BookModel
            // object
            pst.setString(1, book.getISBN());
            pst.setBoolean(2, book.getStatus());
            pst.setString(3, book.getTitle());
            pst.setDate(4, book.getPublicationDate());
            pst.setFloat(5, book.getPrice());
            pst.setInt(6, book.getQuantityinStock());
            pst.setInt(7, book.getQuantity());
            return pst.executeUpdate(); // Executes the INSERT query and returns the number of affected rows
        } catch (SQLException e) { // Catches and re-throws any SQLExceptions that occur
            throw e;
        } finally { // Closes the database connection
            conn.close();
        }
    }

}
