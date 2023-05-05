package com.bookstore.gui.components.search;

import com.bookstore.models.BookModel;
import com.bookstore.models.UserModel;
import java.util.ArrayList;
import java.util.List;

public class Search {

  private List<BookModel> bookList;
  private List<UserModel> userList;

  public Search(List<BookModel> bookList, List<UserModel> userList) {
    this.bookList = bookList;
    this.userList = userList;
  }

  public List<BookModel> BookModels(String searchText) {
    List<BookModel> searchResults = new ArrayList<>();
    for (BookModel bookModel : bookList) {
      if (
        bookModel.getTitle().toLowerCase().contains(searchText.toLowerCase())
      ) {
        searchResults.add(bookModel);
      }
    }
    return searchResults;
  }

  public List<UserModel> searchUsers(String searchText) {
    List<UserModel> searchResults = new ArrayList<>();
    for (UserModel user : userList) {
      if (user.getName().toLowerCase().contains(searchText.toLowerCase())) {
        searchResults.add(user);
      }
    }
    return searchResults;
  }
}
