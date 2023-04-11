package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bookstore.interfaces.IDAO;
import com.bookstore.models.CategoryModel;

public class CategoryDAO implements IDAO<CategoryModel> {
  private static CategoryDAO instance;

  public static CategoryDAO getInstance() {
    if (instance == null) {
      instance = new CategoryDAO();
    }
    return instance;
  }

  private CategoryModel createCategoryModelFromResultSet(ResultSet rs) throws SQLException {
    return new CategoryModel(
        rs.getInt("id"),
        rs.getString("name"));
  }

  @Override
  public ArrayList<CategoryModel> readDatabase() {
    ArrayList<CategoryModel> categoryList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM categories")) {
      while (rs.next()) {
        CategoryModel categoryModel = createCategoryModelFromResultSet(rs);
        categoryList.add(categoryModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return categoryList;
  }

  @Override
  public int insert(CategoryModel category) {
    String insertSql = "INSERT INTO categories (name) VALUES (?)";
    Object[] args = { category.getName() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(CategoryModel category) {
    String updateSql = "UPDATE categories SET name = ? WHERE id = ?";
    Object[] args = { category.getName(), category.getId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM categories WHERE id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<CategoryModel> search(String condition, String[] columnNames) {
    if (condition == null || condition.trim().isEmpty()) {
      throw new IllegalArgumentException("Search condition cannot be empty or null");
    }

    String query;
    if (columnNames == null || columnNames.length == 0) {
      // Search all columns
      query = "SELECT * FROM categories WHERE CONCAT(id, name) LIKE ?";
    } else if (columnNames.length == 1) {
      // Search specific column in categories table
      String column = columnNames[0];
      query = "SELECT * FROM categories WHERE " + column + " LIKE ?";
    } else {
      // Search specific columns in categories table
      query = "SELECT id, name FROM categories WHERE CONCAT("
          + String.join(", ", columnNames) + ") LIKE ?";
    }

    try (PreparedStatement pst = DatabaseConnection.getPreparedStatement(query, "%" + condition + "%")) {
      try (ResultSet rs = pst.executeQuery()) {
        List<CategoryModel> categoryList = new ArrayList<>();
        while (rs.next()) {
          CategoryModel categoryModel = createCategoryModelFromResultSet(rs);
          categoryList.add(categoryModel);
        }
        if (categoryList.isEmpty()) {
          throw new SQLException("No records found for the given condition: " + condition);
        }
        return categoryList;
      }
    } catch (SQLException e) {
      e.printStackTrace();
      return Collections.emptyList();
    }
  }
}
