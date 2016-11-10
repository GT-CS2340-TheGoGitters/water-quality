package testing;

import model.Account;
import model.AccountType;
import model.AccountsHolder;
import model.Persistence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import controller.EditAccountController;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by Ashwin on 11/9/2016.
 */
public class AshwinIgnatiusTests {
    HashMap<String, Account> accountList;
    // Tests for addAccount in AccountsHolder

    @Before
    public void setUp() throws Exception {
        AccountsHolder.resetAccountList();
        accountList = new HashMap<>();
    }

    @Test
    public void emptyAccountsHolder() { assertEquals(accountList, AccountsHolder.getAccountList()); }

    @Test
    public void addSingleAccount() throws Exception {
        AccountsHolder.resetAccountList();

        Account account1 = new Account("Ashwin", "aignatius3", "password", AccountType.USR);
        AccountsHolder.addAccount(account1);
        assertEquals(account1, AccountsHolder.getAccountByUsername("aignatius3"));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addDifferentAccountsWithSameUsername() throws Exception {
        AccountsHolder.resetAccountList();

        Account account1 = new Account("Ashwin", "aignatius3", "password", AccountType.USR);
        Account account2 = new Account("Ash", "aignatius3", "pass", AccountType.USR.WKR);
        AccountsHolder.addAccount(account1);
        AccountsHolder.addAccount(account2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void addSameAccountTwice() throws Exception {
        AccountsHolder.resetAccountList();

        Account account1 = new Account("Ashwin", "aignatius3", "password", AccountType.USR);
        AccountsHolder.addAccount(account1);
        AccountsHolder.addAccount(account1);
    }

    @Test
    public void addMultipleAccounts() throws Exception {
        AccountsHolder.resetAccountList();

        Account account1 = new Account("Ashwin", "ai", "pass", AccountType.USR);
        Account account2 = new Account("Jack", "jw", "pass", AccountType.USR);
        Account account3 = new Account("Marisa", "mh", "pass", AccountType.USR);
        Account account4 = new Account("Josh", "jd", "pass", AccountType.USR);
        Account account5 = new Account("Allison", "ar", "pass", AccountType.USR);

        AccountsHolder.addAccount(account1);
        AccountsHolder.addAccount(account2);
        AccountsHolder.addAccount(account3);
        AccountsHolder.addAccount(account4);
        AccountsHolder.addAccount(account5);

        accountList.put("ai", account1);
        accountList.put("jw", account2);
        accountList.put("mh", account3);
        accountList.put("jd", account4);
        accountList.put("ar", account5);

        assertEquals(accountList, AccountsHolder.getAccountList());
    }
}
