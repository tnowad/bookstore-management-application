package com.bookstore.dao;

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
    public ArrayList<GenreModel> readDatabase() throws SQLException, ClassNotFoundException {
        ArrayList<GenreModel> genreList = new ArrayList<>();
        try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM genre")) {
            while (rs.next()) {
                GenreModel genreModel = createGenreModelFromResultSet(rs);
                genreList.add(genreModel);
            }
        }
        return genreList;
    }

    @Override
    public int insert(GenreModel genre) throws SQLException, ClassNotFoundException {
        String insertSql = "INSERT INTO genre (genreID, nameGenre, ISBN) VALUES (?, ?, ?)";
        Object[] args = { genre.getGenreID(), genre.getNameGenre(), genre.getISBN() };
        return DatabaseConnect.executeUpdate(insertSql, args);
    }

    @Override
    public int update(GenreModel genre) throws SQLException, ClassNotFoundException {
        String updateSql = "UPDATE genre SET nameGenre = ?, ISBN = ? WHERE genreID = ?";
        Object[] args = { genre.getNameGenre(), genre.getISBN(), genre.getGenreID() };
        return DatabaseConnect.executeUpdate(updateSql, args);
    }

    @Override
    public int delete(String genreID) throws SQLException, ClassNotFoundException {
        String deleteSql = "DELETE FROM genre WHERE genreID = ?";
        return DatabaseConnect.executeUpdate(deleteSql, new Object[] { genreID });
    }

    @Override
    public List<GenreModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM genre";
        if (condition != null && !condition.isEmpty()) {
            query += " WHERE " + condition;
        }
        try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
            List<GenreModel> genreList = new ArrayList<>();
            while (rs.next()) {
                GenreModel genreModel = createGenreModelFromResultSet(rs);
                genreList.add(genreModel);
            }
            if (genreList.isEmpty()) {
                throw new SQLException("No records found for the given condition: " + condition);
            }
            return genreList;
        } catch (SQLException e) {
            throw e;
        }
    }

    @Override
    public List<GenreModel> searchByCondition(String condition, String columnName)
            throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM genre WHERE " + columnName + " LIKE ?";
        try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
            try (ResultSet rs = pst.executeQuery()) {
                List<GenreModel> genreList = new ArrayList<>();
                while (rs.next()) {
                    GenreModel genreModel = createGenreModelFromResultSet(rs);
                    genreList.add(genreModel);
                }
                if (genreList.isEmpty()) {
                    throw new SQLException("No records found for the given condition: " + condition);
                }
                return genreList;
            }
        }
    }
}
