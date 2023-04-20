package com.bookstore.dao;

import com.bookstore.models.CurrentUserModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bookstore.interfaces.IDAO;

public class CurrentUserDAO implements IDAO<CurrentUserModel> {
  private static CurrentUserDAO instance;

  public static CurrentUserDAO getInstance() {
    if (instance == null) {
      instance = new CurrentUserDAO();
    }
    return instance;
  }

  private CurrentUserModel createCurrentUserModelFromResultSet(ResultSet rs) throws SQLException {
    return new CurrentUserModel(rs.getInt("user_id"));
  }

  @Override
  public ArrayList<CurrentUserModel> readDatabase() {
    ArrayList<CurrentUserModel> currentUserList = new ArrayList<>();
    try (ResultSet rs = DatabaseConnection.executeQuery("SELECT * FROM current_user_id")) {
      while (rs.next()) {
        CurrentUserModel currentUserModel = createCurrentUserModelFromResultSet(rs);
        currentUserList.add(currentUserModel);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return currentUserList;
  }

  @Override
  public int insert(CurrentUserModel currentUser) {
    String insertSql = "INSERT INTO current_user_id (user_id) VALUES (?)";
    Object[] args = { currentUser.getCurrentUserId() };
    try {
      return DatabaseConnection.executeUpdate(insertSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int update(CurrentUserModel currentUser) {
    String updateSql = "UPDATE current_user_id SET user_id = ?";
    Object[] args = { currentUser.getCurrentUserId() };
    try {
      return DatabaseConnection.executeUpdate(updateSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public int delete(int id) {
    String deleteSql = "DELETE FROM current_user_id WHERE user_id = ?";
    Object[] args = { id };
    try {
      return DatabaseConnection.executeUpdate(deleteSql, args);
    } catch (SQLException e) {
      e.printStackTrace();
      return -1;
    }
  }

  @Override
  public List<CurrentUserModel> search(String condition, String[] columnNames) {
    throw new UnsupportedOperationException("Search method is not supported in CurrentUserDAO");
  }
}
