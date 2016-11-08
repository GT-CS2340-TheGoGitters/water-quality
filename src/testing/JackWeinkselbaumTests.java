package testing;

import model.Account;
import model.AccountType;
import model.AccountsHolder;
import model.Persistence;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Jack on 11/7/16.
 */
public class JackWeinkselbaumTests {
    Persistence p;
    AccountsHolder aH;
    File file;
    Account curr;
    @Before
    public void setUp() throws Exception {
        aH = new AccountsHolder();
        p = new Persistence(aH);
        curr = new Account("Jack", "user", "pass", AccountType.USR);
    }

    @Test (expected = ClassNotFoundException.class)
    public void loadFromBinaryWithNonexistentFile() {
        file = new File("/testing/TestFile.dat");
        p.loadFromBinary(file);
    }

    @Test (expected = IOException.class)
    public void loadFromBinaryWithBlankFile() {
        p.loadFromBinary(file);
    }

    @Test
    public void loadFromBinaryWithExistentFile() {
        aH.addAccount(curr);
        p.saveToBinary(file);
        p.loadFromBinary(file);
    }

}