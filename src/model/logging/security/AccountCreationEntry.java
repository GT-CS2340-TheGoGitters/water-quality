package model.logging.security;

import model.Account;

/**
 * Created by Joshua on 10/21/16.
 */
public class AccountCreationEntry extends SecurityLogEntry {
    public AccountCreationEntry(Account account) {
        super(account);
    }

    public String getLabel() {
        return "Account Created";
    }

    public String getMessage() {
        return actingAccount.getUsername();
    }
}
