package com.bookstore.bus;

import com.bookstore.dao.AccountDAO;
import com.bookstore.model.AccountModel;
import com.bookstore.util.PasswordUtil;

class UserBUS {
  public static login(String username, String password) {
    AccountModel account = AccountDAO.getInstance().getAccountByUsername(username);
    if (account != null && PasswordUtil.checkPassword(password, account.getPassword())) {
      return account;
    }
    return null;
  }
}