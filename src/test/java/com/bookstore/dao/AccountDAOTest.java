package com.bookstore.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.bookstore.model.AccountModel;

@DisplayName("AccountDAO Test")
public class AccountDAOTest {
    private static AccountDAO dao;

    @BeforeAll
    public void setup() {
        dao = AccountDAO.getInstance();
    }

    @Test
    void testReadDatabase() throws SQLException {
        List<AccountModel> accountList = dao.readDatabase();
        assertTrue(accountList.size() > 0);
    }

    @Test
    void testInsert() throws SQLException {
        AccountModel accountModel = new AccountModel("user_test", "pass_test", "active", "id_test");
        int result = dao.insert(accountModel);
        assertEquals(1, result);
        dao.delete("id_test"); // Cleanup after test (remove inserted record)
    }

    @Test
    void testUpdate() throws SQLException {
        String accountId = "4Kq3lffYQ67IzBwC"; // Existing record in database
        String newUsername = "updated_username";

        // Get current account model for comparison after updating
        List<AccountModel> accountList = dao.searchByCondition("accountId = '" + accountId + "'");
        AccountModel beforeUpdate = accountList.get(0);

        // Update account with new username
        beforeUpdate.setUsername(newUsername);
        int result = dao.update(beforeUpdate);
        assertEquals(1, result);

        // Get updated account from database and compare with the one before updating
        accountList = dao.searchByCondition("accountId = '" + accountId + "'");
        AccountModel afterUpdate = accountList.get(0);
        assertEquals(newUsername, afterUpdate.getUsername());

        // Revert changes made by test (update the account again)
        afterUpdate.setUsername(beforeUpdate.getUsername());
        dao.update(afterUpdate);
    }

    @Test
    void testDelete() throws SQLException {
        AccountModel accountModel = new AccountModel("user_test", "pass_test", "active", "id_test");
        dao.insert(accountModel); // Insert test data

        // Check if account was inserted successfully
        List<AccountModel> accountList = dao.searchByCondition("accountId = 'id_test'");
        assertTrue(accountList.size() > 0);

        // Delete the account and check if it was deleted successfully
        int result = dao.delete("id_test");
        assertEquals(1, result);

        accountList = dao.searchByCondition("accountId = 'id_test'");
        assertTrue(accountList.size() == 0); // No account found, record was deleted
    }

    @Test
    void testSearchByCondition() throws SQLException {
        // Search for all active accounts
        String condition = "status = 'active'";
        List<AccountModel> accountList = dao.searchByCondition(condition);
        assertTrue(accountList.size() > 0);

        // Search for an account by its ID
        condition = "accountId = '4Kq3lffYQ67IzBwC'";
        accountList = dao.searchByCondition(condition);
        assertTrue(accountList.size() == 1);

        // Search for accounts that have a certain username
        condition = "username LIKE '%test%'";
        accountList = dao.searchByCondition(condition, "username");
        assertTrue(accountList.size() > 0);
    }
}
