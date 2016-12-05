package model.logging.security;

import model.Account;

public class AccountDeleteEntry extends SecurityLogEntry {
    protected String deletedUsername;

    public AccountDeleteEntry(Account actingAccount, String deletedUsername) {
        super(actingAccount);

        this.deletedUsername = deletedUsername;
    }

    public String getLabel() {
        return "Deleted Account";
    }

    public String getMessage() {
        return "Username: " + deletedUsername;
    }
}