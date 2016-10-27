package model;

import com.sun.javaws.exceptions.InvalidArgumentException;
//import com.sun.tools.corba.se.idl.InvalidArgument;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Joshua on 9/29/16.
 */
public class AccountsHolder {
    private static HashMap<String, Account> accountList = new HashMap<>();

    private static File accountsFile = new File("AccountsSaveState.dat");


    public static void addAccount(Account account){
        if (accountList.containsKey(account.getUsername())) {
            throw new IllegalArgumentException("Duplicate username");
        }

        accountList.put(account.getUsername(), account);
    }

    public static Account getAccountByUsername(String username) {
        return accountList.get(username);
    }

    public static Account[] getValues(){
        return accountList.values().toArray(new Account[accountList.size()]);
    }

    public static HashMap getAccountList() {
        return accountList;
    }

    public static void saveAccountsToBinary() {
        Persistence p = new Persistence(accountList);
        p.saveToBinary(accountsFile);
    }

    public static void loadAccountsFromBinary() {
        Persistence p = new Persistence(accountList);
        p.loadFromBinary(accountsFile);
        accountList = (HashMap) p.getBacking();
    }
}
