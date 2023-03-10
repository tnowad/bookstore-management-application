package com.bookstore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.model.CategoryModel;

public class CategoryDAO implements DAOInterface<CategoryModel> {

  public static CategoryDAO getInstance() {
    return new CategoryDAO();
  }

  private CategoryModel createCategoryModelFromResultSet(ResultSet rs) throws SQLException {
    return new CategoryModel(
        rs.getInt("id"),
        rs.getString("name"));
  }

  @Override
  public ArrayList<CategoryModel> readDatabase() throws SQLException, ClassNotFoundException {
    ArrayList<CategoryModel> categoryList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnect.executeQuery("SELECT * FROM categories")) {
      while (rs.next()) {
        CategoryModel categoryModel = createCategoryModelFromResultSet(rs);
        categoryList.add(categoryModel);
      }
    }
    return categoryList;
  }

  @Override
  public int insert(CategoryModel category) throws SQLException, ClassNotFoundException {
    String insertSql = "INSERT INTO categories (name) VALUES (?)";
    Object[] args = { category.getName() };
    return DatabaseConnect.executeUpdate(insertSql, args);
  }

  @Override
  public int update(CategoryModel category) throws SQLException, ClassNotFoundException {
    String updateSql = "UPDATE categories SET name = ? WHERE id = ?";
    Object[] args = { category.getName(), category.getId() };
    return DatabaseConnect.executeUpdate(updateSql, args);
  }

  @Override
  public int delete(int id) throws SQLException, ClassNotFoundException {
    String deleteSql = "DELETE FROM categories WHERE id = ?";
    Object[] args = { id };
    return DatabaseConnect.executeUpdate(deleteSql, args);
  }

  @Override
  public List<CategoryModel> searchByCondition(String condition) throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM categories";
    if (condition != null && !condition.isEmpty()) {
      query += " WHERE " + condition;
    }
    try (ResultSet rs = DatabaseConnect.executeQuery(query)) {
      List<CategoryModel> categoryList = new ArrayList<>();
      while (rs.next()) {
        CategoryModel categoryModel = createCategoryModelFromResultSet(rs);
        categoryList.add(categoryModel);
      }
      if (categoryList.isEmpty()) {
        System.out.println("No records found for the given condition: " + condition);
      }
      return categoryList;
    }
  }

  @Override
  public List<CategoryModel> searchByCondition(String condition, String columnName)
      throws SQLException, ClassNotFoundException {
    String query = "SELECT * FROM categories WHERE " + columnName + " LIKE ?";
    try (PreparedStatement pst = DatabaseConnect.getPreparedStatement(query, "%" + condition + "%")) {
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
    }
  }
}
