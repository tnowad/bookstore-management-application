package com.bookstore.bus;

import com.bookstore.dao.AccountDAO;

public class AccountBUS {
  private AccountDAO accountDAO;

  public AccountBUS() {
    accountDAO = AccountDAO.getInstance();
  }

  public boolean login(String username, String password) {
    return accountDAO.login(username, password);
  }
}
