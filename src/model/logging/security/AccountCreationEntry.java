package model.logging.security;

import model.Account;

public class AccountCreationEntry extends SecurityLogEntry {
    public AccountCreationEntry(Account account) {
        super(account);
    }

    public String getLabel() {
        return "Account Created";
    }

    public String getMessage() {
        return "Username: " + actingAccount.getUsername();
    }
}
