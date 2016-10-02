package model;

import com.sun.javaws.exceptions.InvalidArgumentException;
//import com.sun.tools.corba.se.idl.InvalidArgument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joshua on 9/29/16.
 */
public class AccountsHolder {
    private static HashMap<String, Account> accountList = new HashMap<>();

    public static void addAccount(Account account){
        if (accountList.containsKey(account.getUsername())) {
            throw new IllegalArgumentException("Duplicate username");
        }

        accountList.put(account.getUsername(), account);
    }

    public static Account getAccountByUsername(String username) {
        return accountList.get(username);
    }

    public static Account[] getAccounts(){
        return accountList.values().toArray(new Account[accountList.size()]);
    }

}
