package com.bookstore.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.bookstore.dao.BookDAO;
import com.bookstore.model.BookModel;

public class TableReaderUtil {

  public void bookReaderUtil() throws ClassNotFoundException, SQLException {
    String filePath = "path/to/Books.xlsx"; // Replace with your file path
    try {
      List<List<String>> bookData = ExcelReaderUtil.readExcel(filePath, 1);
      BookDAO.getInstance().insert((BookModel) bookData);
      System.out.println("Data from Books.xlsx has been inserted successfully.");
    } catch (IOException e) {
      System.err.println(e.getMessage());
    }
  }
}
